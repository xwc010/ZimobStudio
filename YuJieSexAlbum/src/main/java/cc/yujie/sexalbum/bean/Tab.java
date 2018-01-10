package cc.yujie.sexalbum.bean;

import java.io.Serializable;

/**
 * Created by xwc on 2017/12/22.
 */

public class Tab implements Serializable{
    private long id;
    private String name; // Tab 名称
    private String icon; // Tab Icon url
    private String datasUrl; // Tab 接口Url

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDatasUrl() {
        return datasUrl;
    }

    public void setDatasUrl(String datasUrl) {
        this.datasUrl = datasUrl;
    }
}
