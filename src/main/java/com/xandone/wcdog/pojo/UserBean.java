package com.xandone.wcdog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

public class UserBean {
    private String name;
    private String password;
    private String nickname;
    private String userId;
    private String userIcon;
    private String talk;
    private String address;
    private String token;
    private Date registTime;
    private Date lastLoginTime;
    private int banned;

    public UserBean() {

    }

    public UserBean(String userId,
                    String name,
                    String nickname) {
        try {
            this.userId = userId == null ? null : URLDecoder.decode(userId, "utf-8");
            this.name = name == null ? null : URLDecoder.decode(name, "utf-8");
            this.nickname = nickname == null ? null : URLDecoder.decode(nickname, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

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

    public String getTalk() {
        return talk;
    }

    public void setTalk(String talk) {
        this.talk = talk;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
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
