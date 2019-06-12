package com.xandone.wcdog.pojo;

import java.util.Date;

/**
 * @author ：xandone
 * created on  ：2019/6/12 16:20
 * description：
 */
public class FlowBean {
    private int classicCount;
    private int yellowCount;
    private int mindCount;
    private int shiteCount;
    private int coldCount;
    private Date postTime;

    public int getClassicCount() {
        return classicCount;
    }

    public void setClassicCount(int classicCount) {
        this.classicCount = classicCount;
    }

    public int getYellowCount() {
        return yellowCount;
    }

    public void setYellowCount(int yellowCount) {
        this.yellowCount = yellowCount;
    }

    public int getMindCount() {
        return mindCount;
    }

    public void setMindCount(int mindCount) {
        this.mindCount = mindCount;
    }

    public int getShiteCount() {
        return shiteCount;
    }

    public void setShiteCount(int shiteCount) {
        this.shiteCount = shiteCount;
    }

    public int getColdCount() {
        return coldCount;
    }

    public void setColdCount(int coldCount) {
        this.coldCount = coldCount;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }
}
