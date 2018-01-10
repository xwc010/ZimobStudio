package cc.yujie.sexalbum.bean;

import java.io.Serializable;

/**
 * 滚动循环播放Banner
 * Created by xwc on 2018/1/10.
 */

public class Banner implements Serializable {

    private long id;
    private String title; // 标题
    private String describe; // 描述
    private String thumbnail; // 缩略图url
    private String link; // action 相应的url
    private int action; // 0 activity间跳转，1 下载，2 web 浏览
    private int type; // 0 图集，1 视频，2 Feed广告，3 Html文本

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
