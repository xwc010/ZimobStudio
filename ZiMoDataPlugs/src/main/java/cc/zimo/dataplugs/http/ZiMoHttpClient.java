package cc.zimo.dataplugs.http;

import android.os.Handler;
import android.os.Looper;
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
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by xwc on 2017/12/25.
 */

public class ZiMoHttpClient implements IHttp{

    private final MediaType JSON  = MediaType.parse("application/json; charset=utf-8");
    private final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    private static ZiMoHttpClient httpClient;
    private OkHttpClient okHttpClient;

    private ZiMoHttpClient(){
        okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(HttpParam.TIME_OUT, TimeUnit.SECONDS);
    }

    public static ZiMoHttpClient getInstance(){
        if(httpClient == null){
            synchronized (ZiMoHttpClient.class){
                if(httpClient == null){
                    httpClient = new ZiMoHttpClient();
                }
            }
        }

        return httpClient;
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
        if(params == null || params.isNull()){
            callBack.onFaile(reqTag, HttpParam.REQ_PARAMS_EMPTY, "Request params is empty");
            return;
        }

        RequestBody formBody = null;
        Iterator<Map.Entry<String,String>> iterator = params.iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> entry = iterator.next();
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
        if(TextUtils.isEmpty(json)){
            callBack.onFaile(reqTag, HttpParam.REQ_PARAMS_EMPTY, "Request json is empty");
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

    public void uploadFile(final String reqTag, String url, File file, final CallBack callBack){
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .tag(reqTag)
                .build();

        action(reqTag, request, callBack);
    }

    private void action(final String reqTag, Request request, final CallBack callBack){
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, final IOException e) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(reqTag, HttpParam.NETWORK_ERROR, e);
                    }
                });
            }

            @Override
            public void onResponse(final Response response) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if(!response.isSuccessful()){
                            callBack.onFaile(reqTag, response.code(), response.message());
                        }else {
                            try {
                                callBack.onSuccess(reqTag, response.code(), response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                                callBack.onFaile(reqTag, HttpParam.RESPONSE_ERROR, "Response IOException!");
                            }
                        }
                    }
                });
            }
        });
    }

    @Override
    public void cancel(String tag) {
        okHttpClient.cancel(tag);
    }
}
