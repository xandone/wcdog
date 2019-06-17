package com.xandone.wcdog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xandone.wcdog.mapper.JokeMapper;
import com.xandone.wcdog.mapper.UserMapper;
import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.pojo.CommentBean;
import com.xandone.wcdog.pojo.JokeBean;
import com.xandone.wcdog.pojo.JokeLikeBean;
import com.xandone.wcdog.pojo.UserBean;
import com.xandone.wcdog.service.JokeService;
import com.xandone.wcdog.utils.IDUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ：xandone
 * created on  ：2019/1/15 22:30
 * description：
 */
@Service
public class JokeServiceIml implements JokeService {
    @Autowired
    JokeMapper jokeMapper;
    @Autowired
    UserMapper userMapper;


    public JokeBean addJoke(Map<String, String> map) throws Exception {
        JokeBean jokeBean = new JokeBean();

        jokeBean.setJokeId(IDUtils.RandomId());
        jokeBean.setJokeUserId(map.get("jokeUserId"));
        jokeBean.setTitle(map.get("title"));
        jokeBean.setContent(map.get("content"));
        jokeBean.setContentHtml(map.get("contentHtml"));
        jokeBean.setPostTime(new Date());
        jokeBean.setArticleCommentCount(0);
        jokeBean.setArticleLikeCount(0);
        jokeBean.setCategory(map.get("category"));
        jokeBean.setTags(map.get("tags"));
        jokeBean.setCoverImg(map.get("coverImg"));
        jokeMapper.addJoke(jokeBean);

        UserBean userBean = userMapper.getUserById(map.get("jokeUserId"));
        jokeBean.setJokeUserNick(userBean.getNickname());
        jokeBean.setJokeUserIcon(userBean.getUserIcon());
        return jokeBean;
    }


    @Override
    public BaseListResult getAllJoke(Integer page, Integer row, String tag) {
        BaseListResult base = new BaseListResult();
        PageHelper.startPage(page, row);
        List<JokeBean> list;
        if (TextUtils.isEmpty(tag) || "-1".equals(tag)) {
            list = jokeMapper.getJokeList();
        } else {
            list = jokeMapper.getJokeListTags(tag);
        }
        int total = (int) new PageInfo<>(list).getTotal();

        for (JokeBean bean : list) {
            UserBean user = userMapper.getUserById(bean.getJokeUserId());
            List<JokeLikeBean> likeBeans = jokeMapper.selectJokeLikeById(bean.getJokeId());
            List<CommentBean> commentBeans = jokeMapper.getJokeCommentById(bean.getJokeId());
            if (user != null) {
                bean.setJokeUserNick(user.getNickname());
                bean.setJokeUserIcon(user.getUserIcon());
            }
            if (likeBeans != null) {
                bean.setArticleLikeCount(likeBeans.size());
            }
            if (commentBeans != null) {
                bean.setArticleCommentCount(commentBeans.size());
            }
        }

        base.setData(list);
        base.setTotal(total);
        return base;
    }

    @Override
    public void deleteJokeById(String jokeId) throws Exception {
        jokeMapper.deleteJokeById(jokeId);
    }

    @Override
    public void deleteJokeByList(List<String> jokeIds) throws Exception {
        jokeMapper.deleteJokeByList(jokeIds);
        jokeMapper.deleteCommentByJokeIdList(jokeIds);
    }

    @Override
    public void addComment(CommentBean bean) throws Exception {
        jokeMapper.addComment(bean);
    }

    @Override
    public BaseListResult getAllJokeCommentById(Integer page, Integer row, String jokeId) throws Exception {
        BaseListResult base = new BaseListResult();
        PageHelper.startPage(page, row);
        List<CommentBean> list = jokeMapper.getJokeCommentById(jokeId);
        int total = (int) new PageInfo<>(list).getTotal();

        for (CommentBean bean : list) {
            UserBean user = userMapper.getUserById(bean.getCommentUserId());
            if (user != null) {
                bean.setCommentNick(user.getNickname());
                bean.setCommentIcon(user.getUserIcon());
            }
        }

        base.setData(list);
        base.setTotal(total);
        return base;
    }

    @Override
    public void deleteCommentList(List<String> commentsId) throws Exception {
        jokeMapper.deleteCommentList(commentsId);
    }

    @Override
    public void deleteCommentByJokeId(String jokeId) throws Exception {
        jokeMapper.deleteCommentByJokeId(jokeId);
    }

    public void thumbsJoke(String jokeId, String userId) throws Exception {
        JokeLikeBean jokeLikeBean = new JokeLikeBean(jokeId, userId);
        jokeMapper.thumbsJoke(jokeLikeBean);
    }

    public List<JokeLikeBean> selectJokeLikeById(String jokeId) throws Exception {
        List<JokeLikeBean> likeBeans = jokeMapper.selectJokeLikeById(jokeId);
        return likeBeans;
    }

    @Override
    public void changeJokeLikeCount(Map<String, Object> map) throws Exception {
        jokeMapper.changeJokeLikeCount(map);
    }

}
