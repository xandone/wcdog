package com.xandone.wcdog.service;


import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.pojo.CommentBean;
import com.xandone.wcdog.pojo.JokeBean;
import com.xandone.wcdog.pojo.JokeLikeBean;

import java.util.List;
import java.util.Map;

/**
 * @author ：xandone
 * created on  ：2019/1/15 22:30
 * description：
 */
public interface JokeService {

    JokeBean addJoke(Map<String, String> map) throws Exception;

    BaseListResult getAllJoke(Integer page, Integer row, String tag) throws Exception;

    void deleteJokeById(String jokeId) throws Exception;

    void deleteJokeByList(List<String> jokeIds) throws Exception;

    void addComment(CommentBean bean) throws Exception;

    BaseListResult getAllJokeCommentById(Integer page, Integer row, String jokeId) throws Exception;

    void deleteCommentList(List<String> commentsId) throws Exception;

    void deleteCommentByJokeId(String jokeId) throws Exception;

    void thumbsJoke(String jokeId, String userId) throws Exception;

    List<JokeLikeBean> selectJokeLikeById(String jokeId) throws Exception;

    void changeJokeLikeCount(Map<String, Object> map) throws Exception;

    BaseListResult getUserSelfJokes(Integer page, Integer row, String userId) throws Exception;

    BaseListResult getJokeLikeByUserId(Integer page, Integer row, String jokeId) throws Exception;

    JokeBean getJokeById(String jokeId) throws Exception;

    BaseListResult getJokeListFog(Integer page, Integer row, String key) throws Exception;

    BaseListResult searchJokeList(Integer page, Integer row, JokeBean jokeBean) throws Exception;

}
