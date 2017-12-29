package cc.yujie.sexalbum.bean;

/**
 * Created by xwc on 2017/12/22.
 */

public enum AdAction {
    DOWNLOAD("下载"), VIEW("网页浏览"), ACTIVITY("Activity跳转");

    private String name;

    AdAction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
