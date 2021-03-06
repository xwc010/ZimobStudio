package cc.zimo.dataplugs.http;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by xwc on 2017/12/25.
 */

public class ReqParams {

    private HashMap<String, String> map = new HashMap<>();

    public void add(String key, String value){
        map.put(key, value);
    }

    public void remove(String key){
        map.remove(key);
    }

    public boolean isNull(){
        return map.isEmpty();
    }

    public Iterator<Map.Entry<String,String>> iterator(){
        return map.entrySet().iterator();
    }
}
