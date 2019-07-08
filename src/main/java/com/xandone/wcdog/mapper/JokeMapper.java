package com.xandone.wcdog.mapper;


import com.xandone.wcdog.pojo.CommentBean;
import com.xandone.wcdog.pojo.JokeBean;
import com.xandone.wcdog.pojo.JokeLikeBean;

import java.util.List;
import java.util.Map;

public interface JokeMapper {
    List<JokeBean> getJokeList();

    void addJoke(JokeBean jokeBean);

    JokeBean getJokeBeanById(String jokeId);

    void thumbsJoke(JokeLikeBean jokeLikeBean);

    List<JokeLikeBean> selectJokeLikeById(String jokeId);

    void addComment(CommentBean commentBean);

    List<CommentBean> getJokeCommentById(String jokeId);

    void changeJokeLikeCount(Map<String, Object> map);

    void deleteJokeById(String id);

    void deleteJokeByList(List<String> jokeIds);

    void deleteJokeCommentById(String id);

    void deleteCommentByJokeId(String jokeId);

    void deleteCommentList(List<String> commentsId);

    void deleteCommentByJokeIdList(List<String> jokeIds);

    List<JokeBean> getJokeListTags(String tag);

    List<JokeBean> getUserSelfJokes(String userId);

    List<JokeLikeBean> selectJokeLikeByUserId(String userId);

    List<JokeBean> getJokeListFog(String key);

    List<JokeBean> searchJokeList(JokeBean jokeBean);
}
