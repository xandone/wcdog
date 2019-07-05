package com.xandone.wcdog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author ：xandone
 * created on  ：2019/7/3 11:27
 * description：
 */
public class ApkBean {
    private String apkId;
    private String apkVersion;
    private int apkCode;
    private String content;
    private String apkUrl;
    private Date sendTime;

    public String getApkId() {
        return apkId;
    }

    public void setApkId(String apkId) {
        this.apkId = apkId;
    }

    public String getApkVersion() {
        return apkVersion;
    }

    public void setApkVersion(String apkVersion) {
        this.apkVersion = apkVersion;
    }

    public int getApkCode() {
        return apkCode;
    }

    public void setApkCode(int apkCode) {
        this.apkCode = apkCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }
}
