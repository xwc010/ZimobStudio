package cc.zimo.dataplugs.http;

import android.text.TextUtils;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by xwc on 2017/12/26.
 */

public class ZMHttpsClient implements IHttp {

    private final int TIME_OUT = 20;
    public static final int REQ_PARAMS_EMPTY = 444;

    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    private static ZMHttpsClient httpsClient;
    private OkHttpClient okHttpClient;

    private ZMHttpsClient() {
        okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClient.setSslSocketFactory(createSSLSocketFactory());
        okHttpClient.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
    }

    private SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }


    class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public static ZMHttpsClient getInstance() {
        if (httpsClient == null) {
            synchronized (ZMHttpsClient.class) {
                if (httpsClient == null) {
                    httpsClient = new ZMHttpsClient();
                }
            }
        }

        return httpsClient;
    }

    @Override
    public void doGet(final String reqTag, String url, final CallBack callBack) {
        Request request = new Request.Builder()
//                .header("User-Agent", "OkHttp Headers.java")
//                .addHeader("Accept", "application/json; q=0.5")
//                .addHeader("Accept", "application/vnd.github.v3+json")
                .url(url)
                .tag(reqTag)
                .build();

        action(reqTag, request, callBack);
    }

    @Override
    public void doPost(final String reqTag, String url, ReqParams params, final CallBack callBack) {
        if (params == null || params.isNull()) {
            callBack.onFail(reqTag, REQ_PARAMS_EMPTY, "Request params is empty");
            return;
        }

        RequestBody formBody = null;
        Iterator<Map.Entry<String, String>> iterator = params.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            formBody = new FormEncodingBuilder()
                    .add(entry.getKey(), entry.getValue())
                    .build();
        }

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .tag(reqTag)
                .build();

        action(reqTag, request, callBack);
    }

    @Override
    public void doPost(final String reqTag, String url, String json, final CallBack callBack) {
        if (TextUtils.isEmpty(json)) {
            callBack.onFail(reqTag, REQ_PARAMS_EMPTY, "Request json is empty");
            return;
        }

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .tag(reqTag)
                .build();

        action(reqTag, request, callBack);
    }

    public void uploadFile(final String reqTag, String url, File file, final CallBack callBack) {
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .tag(reqTag)
                .build();

        action(reqTag, request, callBack);
    }

    private void action(final String reqTag, Request request, final CallBack callBack) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                callBack.onError(reqTag, HttpParam.NETWORK_ERROR, e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    callBack.onFail(reqTag, response.code(), response.message());
                    return;
                }

                callBack.onSuccess(reqTag, response.code(), response.body().string());
            }
        });
    }

    @Override
    public void cancel(String tag) {
        okHttpClient.cancel(tag);
    }
}
