package cc.yujie.sexalbum.modle;

/**
 * Created by xwc on 2017/12/22.
 */

public class Ad implements IData {

    private String describe;
    private String thumbnail;
    private String link;
    private AdAction action;

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

    public AdAction getAction() {
        return action;
    }

    public void setAction(AdAction action) {
        this.action = action;
    }
}
