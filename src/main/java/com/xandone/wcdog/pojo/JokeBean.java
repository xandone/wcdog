package com.xandone.wcdog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

public class JokeBean {
    private String jokeId;
    private String jokeUserId;
    private String title;
    private String content;
    private String contentHtml;
    private String coverImg;
    private Date postTime;
    private int articleLikeCount;
    private int articleCommentCount;
    private String category;
    private String tags;

    private String jokeUserNick;
    private String jokeUserIcon;
    private Date selfApprovalTime;

    public JokeBean() {

    }

    public JokeBean(String key,
                    String jokeId,
                    String jokeUserId,
                    String category,
                    String tags) {
        try {
            this.title = key == null ? null : URLDecoder.decode(key, "utf-8");
            this.jokeId = jokeId == null ? null : URLDecoder.decode(jokeId, "utf-8");
            this.jokeUserId = jokeUserId == null ? null : URLDecoder.decode(jokeUserId, "utf-8");
            this.category = category == null ? null : URLDecoder.decode(category, "utf-8");
            this.tags = tags == null ? null : URLDecoder.decode(tags, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getSelfApprovalTime() {
        return selfApprovalTime;
    }

    public void setSelfApprovalTime(Date selfApprovalTime) {
        this.selfApprovalTime = selfApprovalTime;
    }
}
