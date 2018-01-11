package cc.zimo.dataplugs.http;

/**
 * Created by xwc on 2017/12/25.
 */

public interface FileCallBack {
    public void onSuccess(String filePath);
    public void onFail(int code);
    public void onProcess(int total, int current);
}
