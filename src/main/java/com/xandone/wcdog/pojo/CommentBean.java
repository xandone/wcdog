package com.xandone.wcdog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CommentBean {
    private String commentId;
    private String jokeId;
    private String commentUserId;
    private String commentDetails;
    private Date commentDate;

    private String commentNick;
    private String commentIcon;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getJokeId() {
        return jokeId;
    }

    public void setJokeId(String jokeId) {
        this.jokeId = jokeId;
    }

    public String getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentDetails() {
        return commentDetails;
    }

    public void setCommentDetails(String commentDetails) {
        this.commentDetails = commentDetails;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentNick() {
        return commentNick;
    }

    public void setCommentNick(String commentNick) {
        this.commentNick = commentNick;
    }

    public String getCommentIcon() {
        return commentIcon;
    }

    public void setCommentIcon(String commentIcon) {
        this.commentIcon = commentIcon;
    }

}
