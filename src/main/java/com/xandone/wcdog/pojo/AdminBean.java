package com.xandone.wcdog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AdminBean {
    private String name;
    private String password;
    private String nickname;
    private String adminId;
    private String adminIcon;
    private String token;
    private String permission;
    private Date registTime;
    private Date lastLoginTime;

    public AdminBean() {

    }

    public AdminBean(String name, String password, String nickname, String adminId, Date registTime) {
        this.name = name;
        this.password = password;
        this.nickname = nickname;
        this.adminId = adminId;
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

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminIcon() {
        return adminIcon;
    }

    public void setAdminIcon(String adminIcon) {
        this.adminIcon = adminIcon;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
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

    @Override
    public String toString() {
        return "UserBean{" +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", adminId=" + adminId +
                ", adminIcon=" + adminIcon +
                ", token=" + token +
                ", registTime=" + registTime +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
