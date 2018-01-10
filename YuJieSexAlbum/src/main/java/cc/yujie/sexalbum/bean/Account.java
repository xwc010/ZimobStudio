package cc.yujie.sexalbum.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 用户
 * Created by xwc on 2018/1/10.
 */

public class Account implements Serializable {

    public static Account currentAccount(){
        return null;
    }

    public static void saveAccount(Account account){

    }

    private long id;
    private String user_name; // 用户名
    private String user_pwd; // 用户密码
    private String nice_name; // 昵称
    private String head_icon; // 头像url
    private int gender; // 性别
    private String address;
    private String school;
    private String hobby; // 爱好
    private String follow_tags; // 用户关注的标签
    private long birthday; // 毫秒
    private String email;
    private String telphone;
    private String describe; // 描述
    private List<SocialAccount> socialAccounts; // 绑定的第三方社交账号
    private int account_state; // 账号状态：0 正常，1 禁言，2 封号
    private int login_state; // 账号登录状态：0 未登录，1 登录中
    private long login_time; // 登录时间，毫秒
    private String login_address; // 登录地址
    private float login_lat; // 登录纬度
    private float login_lng; // 登录经度

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getNice_name() {
        return nice_name;
    }

    public void setNice_name(String nice_name) {
        this.nice_name = nice_name;
    }

    public String getHead_icon() {
        return head_icon;
    }

    public void setHead_icon(String head_icon) {
        this.head_icon = head_icon;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getFollow_tags() {
        return follow_tags;
    }

    public void setFollow_tags(String follow_tags) {
        this.follow_tags = follow_tags;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<SocialAccount> getSocialAccounts() {
        return socialAccounts;
    }

    public void setSocialAccounts(List<SocialAccount> socialAccounts) {
        this.socialAccounts = socialAccounts;
    }

    public int getAccount_state() {
        return account_state;
    }

    public void setAccount_state(int account_state) {
        this.account_state = account_state;
    }

    public int getLogin_state() {
        return login_state;
    }

    public void setLogin_state(int login_state) {
        this.login_state = login_state;
    }

    public long getLogin_time() {
        return login_time;
    }

    public void setLogin_time(long login_time) {
        this.login_time = login_time;
    }

    public String getLogin_address() {
        return login_address;
    }

    public void setLogin_address(String login_address) {
        this.login_address = login_address;
    }

    public float getLogin_lat() {
        return login_lat;
    }

    public void setLogin_lat(float login_lat) {
        this.login_lat = login_lat;
    }

    public float getLogin_lng() {
        return login_lng;
    }

    public void setLogin_lng(float login_lng) {
        this.login_lng = login_lng;
    }
}
