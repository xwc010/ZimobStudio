package cc.yujie.libs.model;

import java.io.Serializable;

/**
 * 第三方社交账号
 * Created by xwc on 2018/1/10.
 */

public class SocialAccount implements Serializable {

    private String platform; // 平台名称
    private String name; // 用户名称
    private String token; // 唯一标识
    private String expiry_date; // 有效期

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }
}
