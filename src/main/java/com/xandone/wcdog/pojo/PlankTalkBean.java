package com.xandone.wcdog.pojo;

import java.util.Date;

/**
 * @author ：xandone
 * created on  ：2019/7/1 17:51
 * description：
 */
public class PlankTalkBean {
    private String content;
    private Date sendTime;

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

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
