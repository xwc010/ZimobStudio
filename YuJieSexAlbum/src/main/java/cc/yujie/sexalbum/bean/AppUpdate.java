package cc.yujie.sexalbum.bean;

import java.io.Serializable;

/**
 * App 更新实体
 * Created by xwc on 2018/1/10.
 */

public class AppUpdate implements Serializable {

    private int versionCode; // 最新versionCode
    private String versionName; // "最新versionName
    private String link; // apk 下载地址
    private String content; // 更新说明

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
