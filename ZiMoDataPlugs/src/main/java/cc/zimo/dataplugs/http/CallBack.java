package cc.zimo.dataplugs.http;

/**
 * Created by xwc on 2017/12/25.
 */

public abstract class CallBack{
    public abstract void onSuccess(String reqTag, int resultCode, String response);
    public void onFaile(String reqTag, int resultCode, String msg){};
    public abstract void onError(String reqTag, int resultCode, Throwable erro);
}