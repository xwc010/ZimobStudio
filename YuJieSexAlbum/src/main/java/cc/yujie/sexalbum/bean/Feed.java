package cc.yujie.sexalbum.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xwc on 2017/12/22.
 */

public class Feed implements Serializable{
    private long id;
    private String title; // 标题
    private String describe; // 描述
    private String thumbnail; // 缩略图url
    private List<String> album; // 相册/视频集urls
    private String link; // action 相应的url
    private int action; // 0 activity间跳转，1 下载，2 web 浏览
    private int type; // 0 图集，1 视频，2 Feed广告，3 Html文本
    private int display_mode; // 0 大图模式，1 左小图模式，2 右小图模式，3 画廊模式
    private int like_num;
    private int unlike_num;
    private int comment_num;
    private int share_num;
    private int save_num;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDisplay_mode() {
        return display_mode;
    }

    public void setDisplay_mode(int display_mode) {
        this.display_mode = display_mode;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getUnlike_num() {
        return unlike_num;
    }

    public void setUnlike_num(int unlike_num) {
        this.unlike_num = unlike_num;
    }

    public int getComment_num() {
        return comment_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }

    public int getShare_num() {
        return share_num;
    }

    public void setShare_num(int share_num) {
        this.share_num = share_num;
    }

    public int getSave_num() {
        return save_num;
    }

    public void setSave_num(int save_num) {
        this.save_num = save_num;
    }
}
