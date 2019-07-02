package com.xandone.wcdog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author ：xandone
 * created on  ：2019/7/1 17:51
 * description：
 */
public class PlankTalkBean {
    private String plankId;
    private String content;
    private Date sendTime;
    private String sendTimeStr;


    public PlankTalkBean(String content) {
        this.content = content;
    }

    public PlankTalkBean() {
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

    public String getSendTimeStr() {
        return sendTimeStr;
    }

    public void setSendTimeStr(String sendTimeStr) {
        this.sendTimeStr = sendTimeStr;
    }

    public String getPlankId() {
        return plankId;
    }

    public void setPlankId(String plankId) {
        this.plankId = plankId;
    }
}
