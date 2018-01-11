package cc.yujie.libs.model;

import java.io.Serializable;
import java.util.List;

/**
 * App 推荐
 * Created by xwc on 2018/1/10.
 */

public class App implements Serializable {
    private long id;
    private String title; // 标题
    private String resume; // 摘要
    private String describe; // 描述，纯文本或html文本
    private String logo; // logo url
    private List<String> album; // 相册url
    private String link; // action 相应的url
    private int action; // 0 下载，1 web 浏览
    private String type; // app类型，如社交、游戏
    private int versionCode; // 最新versionCode
    private String versionName; // 最新versionName
    private String developer; // 开发者
    private int star; // app评分

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

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<String> getAlbum() {
        return album;
    }

    public void setAlbum(List<String> album) {
        this.album = album;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
