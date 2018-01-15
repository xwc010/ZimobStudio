package cc.yujie.libs.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

import cc.yujie.libs.constant.DesplayMode;
import cc.yujie.libs.constant.EFeedAction;
import cc.yujie.libs.constant.EFeedFlag;
import cc.yujie.libs.constant.EFeedType;

/**
 * Created by xwc on 2017/12/22.
 */

public class Feed implements Serializable, MultiItemEntity {

    private long id;
    private String title; // 标题
    private String describe; // 描述
    private String thumbnail; // 缩略图url
    private List<String> album; // 相册/视频集urls
    private String link; // action 相应的url
    private EFeedAction action; // 0 activity间跳转，1 下载，2 web 浏览
    private EFeedType type; // 0 图文，1 视频，2 Feed广告，3 Html文本
    private int display_mode; // 0 大图模式，1 左小图模式，2 右小图模式，3 九宫格多图模式，4 纯文本
    private int like_num;
    private int unlike_num;
    private int comment_num;
    private int share_num;
    private int save_num;
    private int browse_num;
    private long time; // Feed 发布时间
    private Publisher publisher; // Feed 发布者
    private String tags; // Feed 标签： 每个便签以空格" " 分隔
    private EFeedFlag flag; // 0 默认，1 置顶，2 热，3 最新，4 广告

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

    public EFeedAction getAction() {
        return action;
    }

    public void setAction(EFeedAction action) {
        this.action = action;
    }

    public EFeedType getType() {
        return type;
    }

    public void setType(EFeedType type) {
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

    public int getBrowse_num() {
        return browse_num;
    }

    public void setBrowse_num(int browse_num) {
        this.browse_num = browse_num;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public EFeedFlag getFlag() {
        return flag;
    }

    public void setFlag(EFeedFlag flag) {
        this.flag = flag;
    }

    @Override
    public int getItemType() {
        return display_mode;
    }
}
