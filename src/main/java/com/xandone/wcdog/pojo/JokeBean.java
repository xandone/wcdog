package com.xandone.wcdog.pojo;

import java.util.Date;

public class JokeBean {
    private String jokeId;
    private String jokeUserId;
    private String title;
    private String content;
    private String contentHtml;
    private Date postTime;
    private int articleLikeCount;
    private int articleCommentCount;

    private String jokeUserNick;
    private String jokeUserIcon;

    public String getJokeId() {
        return jokeId;
    }

    public void setJokeId(String jokeId) {
        this.jokeId = jokeId;
    }

    public String getJokeUserId() {
        return jokeUserId;
    }

    public void setJokeUserId(String jokeUserId) {
        this.jokeUserId = jokeUserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public int getArticleLikeCount() {
        return articleLikeCount;
    }

    public void setArticleLikeCount(int articleLikeCount) {
        this.articleLikeCount = articleLikeCount;
    }

    public int getArticleCommentCount() {
        return articleCommentCount;
    }

    public void setArticleCommentCount(int articleCommentCount) {
        this.articleCommentCount = articleCommentCount;
    }

    public String getJokeUserNick() {
        return jokeUserNick;
    }

    public void setJokeUserNick(String jokeUserNick) {
        this.jokeUserNick = jokeUserNick;
    }

    public String getJokeUserIcon() {
        return jokeUserIcon;
    }

    public void setJokeUserIcon(String jokeUserIcon) {
        this.jokeUserIcon = jokeUserIcon;
    }
}
