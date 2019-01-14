package com.xandone.wcdog.pojo;

import java.util.Date;

public class UserBean {
    private String name;
    private String password;
    private String nickname;
    private String userId;
    private String userIcon;
    private String token;
    private Date registTime;
    private Date lastLoginTime;

    public UserBean() {

    }

    public UserBean(String name, String password, String nickname, String userId, Date registTime) {
        this.name = name;
        this.password = password;
        this.nickname = nickname;
        this.userId = userId;
        this.registTime = registTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", userId=" + userId +
                ", userIcon=" + userIcon +
                ", token=" + token +
                ", registTime=" + registTime +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
