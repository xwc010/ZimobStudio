package com.yj.h;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
