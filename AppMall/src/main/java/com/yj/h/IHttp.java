package com.yj.h;

public interface IHttp {

    public void doGet(String reqTag, String url, CallBack callBack);
    public void doPost(String reqTag, String url, ReqParams params, CallBack callBack);
    public void doPost(String reqTag, String url, String json, CallBack callBack);
    public void cancel(String tag);
}
