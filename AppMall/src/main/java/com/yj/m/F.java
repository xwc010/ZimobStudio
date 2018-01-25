package com.yj.m;

import android.text.TextUtils;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class F implements Serializable, MultiItemEntity {
    private String title="";
    private String imgUrl;
    private String videoUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public int getItemType() {
        return TextUtils.isEmpty(videoUrl)? 0 : 1;
    }

    public static F parseF(JSONObject jo){
        F a = new F();
        a.setTitle(jo.optString("title"));
        a.setImgUrl(jo.optString("imgUrl"));
        a.setVideoUrl(jo.optString("videoUrl"));

        return a;
    }
}
