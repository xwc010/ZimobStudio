package cc.yujie.libs.model;

import java.io.Serializable;

/**
 * Feed发布者
 * Created by xwc on 2018/1/11.
 */

public class Publisher implements Serializable {

    private int id;
    public String name;
    public String describe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
