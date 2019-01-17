package com.xandone.wcdog.service;


import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.pojo.CommentBean;
import com.xandone.wcdog.pojo.JokeBean;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2019/1/15 22:30
 * description：
 */
public interface JokeService {

    JokeBean addJoke(String title, String joke_user_id, String content,String contentHtml) throws Exception;

    BaseListResult getAllJoke(Integer page, Integer row) throws Exception;

    void deleteJokeById(String jokeId) throws Exception;

    void deleteJokeByList(List<String> jokeIds) throws Exception;

    void addComment(CommentBean bean) throws Exception;

    BaseListResult getAllJokeCommentById(Integer page, Integer row, String jokeId) throws Exception;

    void deleteCommentByList(List<String> commentsId) throws Exception;

}
