package cc.yujie.dataplugs.aliyunsdk;

/**
 * Created by xwc on 2017/9/29.
 */

import java.util.concurrent.ConcurrentHashMap;

public class Log {
    ConcurrentHashMap<String, Object> mContent = new ConcurrentHashMap<String, Object>();

    public Log() {
        this.mContent.put("__time__", Integer.valueOf((int) (System.currentTimeMillis() / 1000L)));
    }

    public void putTime(int time) {
        this.mContent.put("__time__", Integer.valueOf(time));
    }

    public void putContent(String key, String value) {
        if (key != null && !key.isEmpty()) {
            if (value == null) {
                this.mContent.put(key, "");
            } else {
                this.mContent.put(key, value);
            }

        }
    }

    public ConcurrentHashMap<String, Object> getContent() {
        return this.mContent;
    }
}