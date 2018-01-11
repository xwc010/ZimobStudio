package cc.zimo.dataplugs.aliyunsdk;

/**
 * Created by xwc on 2017/9/29.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class LogGroup {
    private String mTopic = "";
    private String mSource = "";
    private List<ConcurrentHashMap<String, Object>> mContent = new CopyOnWriteArrayList<ConcurrentHashMap<String, Object>>();

    public LogGroup() {
    }

    public LogGroup(String topic, String source) {
        this.mTopic = topic;
        this.mSource = source;
    }

    public void putTopic(String topic) {
        this.mTopic = topic;
    }

    public void putSource(String source) {
        this.mSource = source;
    }

    public void putLog(Log log) {
        this.mContent.add(log.getContent());
    }

    public void clearLog() {
        this.mContent.clear();
    }

    public String logGroupToJsonString() {
        try {
            JSONObject json_log_group = new JSONObject();
            json_log_group.put("__source__", this.mSource);
            json_log_group.put("__topic__", this.mTopic);
            JSONArray log_arrays = new JSONArray();
            Iterator var4 = this.mContent.iterator();

            while (var4.hasNext()) {
                Map s = (Map) var4.next();
                JSONObject json_log = new JSONObject(s);
                //            log_arrays.add(json_log);
                log_arrays.put(json_log);
            }

            json_log_group.put("__logs__", log_arrays);
//        String s1 = json_log_group.toJSONString();
            String s1 = json_log_group.toString();
            return s1;
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}
