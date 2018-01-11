package cc.zimo.dataplugs.aliyunsdk;

/**
 * Created by xwc on 2017/9/29.
 */

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.zip.Deflater;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class LOGClient {
    private String mEndPoint;
    private String mAccessKeyID;
    private String mAccessKeySecret;
    private String mAccessToken;
    private String mProject;

    public LOGClient(String endPoint, String accessKeyID, String accessKeySecret, String projectName) {
        if (TextUtils.isEmpty(endPoint)) {
            throw new NullPointerException("endpoint is null");
        } else {
            this.mEndPoint = endPoint;
            if (this.mEndPoint.startsWith("http://")) {
                this.mEndPoint = this.mEndPoint.substring(7);
            } else if (this.mEndPoint.startsWith("https://")) {
                this.mEndPoint = this.mEndPoint.substring(8);
            }

            while (this.mEndPoint.endsWith("/")) {
                this.mEndPoint = this.mEndPoint.substring(0, this.mEndPoint.length() - 1);
            }

            if (!TextUtils.isEmpty(accessKeyID)) {
                this.mAccessKeyID = accessKeyID;
                if (!TextUtils.isEmpty(accessKeySecret)) {
                    this.mAccessKeySecret = accessKeySecret;
                    if (!TextUtils.isEmpty(projectName)) {
                        this.mProject = projectName;
                        this.mAccessToken = "";
                    } else {
                        throw new NullPointerException("projectName is null");
                    }
                } else {
                    throw new NullPointerException("accessKeySecret is null");
                }
            } else {
                throw new NullPointerException("accessKeyID is null");
            }
        }
    }

    public void setToken(String token) {
        this.mAccessToken = token;
    }

    public String getToken() {
        return this.mAccessToken;
    }

    public String getEndPoint() {
        return this.mEndPoint;
    }

    public String getKeyID() {
        return this.mAccessKeyID;
    }

    public String getKeySecret() {
        return this.mAccessKeySecret;
    }

    public void postLog(LogGroup logGroup, String logStoreName) throws LogException {
        String httpUrl = "http://" + this.mProject + "." + this.mEndPoint + "/logstores/" + logStoreName + "/shards/lb";

        byte[] httpPostBody;
        try {
            httpPostBody = logGroup.logGroupToJsonString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException var7) {
            throw new LogException("LogClientError", "Failed to pass log to utf-8 bytes", var7, "");
        }

        byte[] httpPostBodyZipped = this.gzipFrom(httpPostBody);
        Map<String, String> httpHeaders = this.getHttpHeadersFrom(logStoreName, httpPostBody, httpPostBodyZipped);
        this.httpPostRequest(httpUrl, httpHeaders, httpPostBodyZipped);
    }

    public void httpPostRequest(String url, Map<String, String> headers, byte[] body) throws LogException {
        URL u;
        try {
            u = new URL(url);
        } catch (MalformedURLException var15) {
            throw new LogException("LogClientError", "illegal post url", var15, "");
        }

        HttpURLConnection conn;
        try {
            conn = (HttpURLConnection) u.openConnection();
        } catch (IOException var14) {
            throw new LogException("LogClientError", "fail to create HttpURLConnection", var14, "");
        }

        try {
            conn.setRequestMethod("POST");
        } catch (ProtocolException var13) {
            throw new LogException("LogClientError", "fail to set http request method to  POST", var13, "");
        }

        try {
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(15000);
        } catch (Exception e) {
            throw new LogException("LogClientError", "fail to set Timeout", e, "");
        }

        conn.setDoOutput(true);
        Iterator request_id = headers.entrySet().iterator();

        while (request_id.hasNext()) {
            Entry e = (Entry) request_id.next();
            conn.setRequestProperty((String) e.getKey(), (String) e.getValue());
        }

        try {
            DataOutputStream e1 = new DataOutputStream(conn.getOutputStream());
            e1.write(body);
            e1.flush();
            e1.close();
        } catch (IOException var12) {
            throw new LogException("LogClientError", "fail to post data to URL:" + url, var12, "");
        }

        try {
            int e2 = conn.getResponseCode();
            String request_id1 = conn.getHeaderField("x-log-requestid");
            if (request_id1 == null) {
                request_id1 = "";
            }

            if (e2 != 200) {
                InputStream error_stream = conn.getErrorStream();
                if (error_stream == null) {
                    throw new LogException("LogServerError", "Response code:" + String.valueOf(e2) + "\nMessage: fail to connect to the server", request_id1);
                } else {
                    BufferedReader in = new BufferedReader(new InputStreamReader(error_stream, "utf-8"));
                    StringBuffer response = new StringBuffer();

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    in.close();
                    this.checkError(response.toString(), request_id1);
                    throw new LogException("LogServerError", "Response code:" + String.valueOf(e2) + "\nMessage:" + response.toString(), request_id1);
                }
            }
        } catch (IOException var16) {
            throw new LogException("LogServerError", "Failed to parse response data", "");
        }
    }

    private void checkError(String error_message, String request_id) throws LogException {
        try {
            JSONObject obj = new JSONObject(error_message);// JSON.parseObject(error_message);
            if (obj != null && obj.has("errorCode") && obj.has("errorMessage")) {
                throw new LogException(obj.getString("errorCode"), obj.getString("errorMessage"), request_id);
            }
        } catch (JSONException var4) {
            ;
        }

    }

    public Map<String, String> getHttpHeadersFrom(String logStoreName, byte[] body, byte[] bodyZipped) throws LogException {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("x-log-apiversion", "0.6.0");
        headers.put("x-log-signaturemethod", "hmac-sha1");
        headers.put("Content-Type", "application/json");
        headers.put("Date", getMGTTime());
        headers.put("Content-MD5", this.parseToMd5U32From(bodyZipped));
        headers.put("Content-Length", String.valueOf(bodyZipped.length));
        headers.put("x-log-bodyrawsize", String.valueOf(body.length));
        headers.put("x-log-compresstype", "deflate");
        headers.put("Host", this.mProject + "." + this.mEndPoint);
        StringBuilder signStringBuf = (new StringBuilder("POST\n")).append((String) headers.get("Content-MD5") + "\n").append((String) headers.get("Content-Type") + "\n").append((String) headers.get("Date") + "\n");
        String token = this.mAccessToken;
        if (!TextUtils.isEmpty(token)) { // token != null && token != ""
            headers.put("x-acs-security-token", token);
            signStringBuf.append("x-acs-security-token:" + (String) headers.get("x-acs-security-token") + "\n");
        }

        signStringBuf.append("x-log-apiversion:0.6.0\n").append("x-log-bodyrawsize:" + (String) headers.get("x-log-bodyrawsize") + "\n").append("x-log-compresstype:deflate\n").append("x-log-signaturemethod:hmac-sha1\n").append("/logstores/" + logStoreName + "/shards/lb");
        String signString = signStringBuf.toString();

        try {
            String e = hmac_sha1(signString, this.mAccessKeySecret);
            headers.put("Authorization", "LOG " + this.mAccessKeyID + ":" + e);
            return headers;
        } catch (Exception var9) {
            throw new LogException("LogClientError", "fail to get encode signature", var9, "");
        }
    }

    public static String getMGTTime() {
        Calendar cd = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String str = sdf.format(cd.getTime());
        return str;
    }

    public static String hmac_sha1(String encryptText, String encryptKey) throws Exception {
        byte[] keyBytes = encryptKey.getBytes("UTF-8");
        byte[] dataBytes = encryptText.getBytes("UTF-8");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(new SecretKeySpec(keyBytes, "HmacSHA1"));
        return new String(Base64Kit.encode(mac.doFinal(dataBytes)));
    }

    private String parseToMd5U32From(byte[] bytes) throws LogException {
        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            String res = (new BigInteger(1, e.digest(bytes))).toString(16).toUpperCase();
            StringBuilder zeros = new StringBuilder();

            for (int i = 0; i + res.length() < 32; ++i) {
                zeros.append("0");
            }

            return zeros.toString() + res;
        } catch (NoSuchAlgorithmException var6) {
            throw new LogException("LogClientError", "Not Supported signature method MD5", var6, "");
        }
    }

    private byte[] gzipFrom(byte[] jsonByte) throws LogException {
        ByteArrayOutputStream out = null;

        try {
            out = new ByteArrayOutputStream(jsonByte.length);
            Deflater e = new Deflater();
            e.setInput(jsonByte);
            e.finish();
            byte[] buf = new byte[10240];

            while (!e.finished()) {
                int count = e.deflate(buf);
                out.write(buf, 0, count);
            }

            byte[] var7 = out.toByteArray();
            return var7;
        } catch (Exception var14) {
            throw new LogException("LogClientError", "fail to zip data", "");
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException var13) {
                var13.printStackTrace();
            }
        }
    }
}
