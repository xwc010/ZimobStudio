package cc.yujie.dataplugs.http;

import java.util.HashMap;

/**
 * Created by xwc on 2017/12/25.
 */

public interface IHttp {

    public void doGet(String reqTag, String url, CallBack callBack);
    public void doPost(String reqTag, String url, ReqParams params, CallBack callBack);
    public void doPost(String reqTag, String url, String json, CallBack callBack);
    public void cancel(String tag);
}
