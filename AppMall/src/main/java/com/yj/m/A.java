package com.yj.m;

import org.json.JSONObject;

public class A {
    private String name;
    private String logo;
    private String size;
    private String dec;
    private String downLoad;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public String getDownLoad() {
        return downLoad;
    }

    public void setDownLoad(String downLoad) {
        this.downLoad = downLoad;
    }

    public static A parseA(JSONObject jo){
        A a = new A();
        a.setName(jo.optString("name"));
        a.setLogo(jo.optString("logo"));
        a.setSize(jo.optString("size"));
        a.setDec(jo.optString("dec"));
        a.setDownLoad(jo.optString("downLoad"));
        return a;
    }
}
