package cc.zimo.dataplugs.http;

import android.text.TextUtils;

import com.blankj.utilcode.util.NetworkUtils;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xwc on 2018/1/10.
 */

public class IPUnit {

    private static String NET_IP = "";
    public static Map<String, String> IP_SN = new HashMap<>();

    private static boolean inInit = false;

    /**
     * 获取外网的IP
     *
     * @return String
     */
    public static void initNetIp() {
        if (inInit || !NetworkUtils.isConnected()) {
            return;
        }
        Thread thread = new Thread() {

            @Override
            public void run() {
                super.run();
                inInit = true;
                InputStream inStream = null;
                try {
                    String line;
                    URL infoUrl = new URL("http://ip.chinaz.com/getip.aspx"); // http://pv.sohu.com/cityjson?ie=utf-8
                    URLConnection connection = infoUrl.openConnection();
                    HttpURLConnection httpConnection = (HttpURLConnection) connection;
                    int responseCode = httpConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        inStream = httpConnection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                        StringBuilder strber = new StringBuilder();
                        while ((line = reader.readLine()) != null) {
                            strber.append(line + "\n");
                        }

                        Logger.d("initNetIp strber: " + strber);

                        // 从反馈的结果中提取出IP地址
                        int start = strber.indexOf("{");
                        int end = strber.indexOf("}");
                        String json = strber.substring(start, end + 1);
                        Logger.json(json);
                        if (json != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(json);
                                // http://pv.sohu.com/cityjson?ie=utf-8 解析模式
//                                NET_IP = jsonObject.optString("cip");
//                                DLog.d("IP NET_IP: " + NET_IP);
//                                IP_SN.clear();
//                                IP_SN.put("ip", NET_IP);
//                                IP_SN.put("id", jsonObject.optString("cid"));
//                                IP_SN.put("address", jsonObject.optString("cname"));

                                // http://ip.chinaz.com/getip.aspx 解析模式
                                NET_IP = jsonObject.optString("ip");
                                Logger.d("initNetIp NET_IP: " + NET_IP);
                                IP_SN.clear();
                                IP_SN.put("ip", NET_IP);
                                IP_SN.put("address", jsonObject.optString("address"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (inStream != null) {
                        try {
                            inStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    inInit = false;
                }
            }
        };
        thread.start();
    }

    public static String getNetIp() {
        if (TextUtils.isEmpty(NET_IP)) {
            initNetIp();
            return NetworkUtils.getIPAddress(true);
        }

        return NET_IP;
    }
}
