package com.xandone.wcdog.service.impl;

import com.xandone.wcdog.mapper.JokeMapper;
import com.xandone.wcdog.mapper.UserMapper;
import com.xandone.wcdog.pojo.JokeBean;
import com.xandone.wcdog.service.JokeService;
import com.xandone.wcdog.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JokeServiceIml implements JokeService {
    @Autowired
    JokeMapper jokeMapper;
    @Autowired
    UserMapper userMapper;


    public JokeBean addJoke(String title, String joke_user_id, String content) throws Exception {
        JokeBean jokeBean = new JokeBean();

        jokeBean.setJokeId(IDUtils.RandomId());
        jokeBean.setJokeUserId(joke_user_id);
        jokeBean.setTitle(title);
        jokeBean.setContent(content);
        jokeBean.setPostTime(new Date());
        jokeBean.setArticleCommentCount(0);
        jokeBean.setArticleLikeCount(0);
        jokeMapper.addJoke(jokeBean);

        return jokeBean;
    }


}
