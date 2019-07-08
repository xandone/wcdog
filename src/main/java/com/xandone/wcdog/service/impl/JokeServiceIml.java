package com.xandone.wcdog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xandone.wcdog.mapper.JokeMapper;
import com.xandone.wcdog.mapper.UserMapper;
import com.xandone.wcdog.pojo.*;
import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.service.JokeService;
import com.xandone.wcdog.utils.IDUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.util.ArrayList;
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
    public BaseListResult getAllJoke(Integer page, Integer row, String tag) throws Exception {
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
            dealJokeBean(bean);
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
        JokeLikeBean jokeLikeBean = new JokeLikeBean(jokeId, userId, new Date());
        jokeMapper.thumbsJoke(jokeLikeBean);
    }

    public List<JokeLikeBean> selectJokeLikeById(String jokeId) throws Exception {
        return jokeMapper.selectJokeLikeById(jokeId);
    }

    @Override
    public void changeJokeLikeCount(Map<String, Object> map) throws Exception {
        jokeMapper.changeJokeLikeCount(map);
    }

    @Override
    public BaseListResult getUserSelfJokes(Integer page, Integer row, String userId) throws Exception {
        BaseListResult base = new BaseListResult();
        PageHelper.startPage(page, row);
        List<JokeBean> list;
        if (TextUtils.isEmpty(userId) || "-1".equals(userId)) {
            list = new ArrayList<>();
        } else {
            list = jokeMapper.getUserSelfJokes(userId);
        }
        int total = (int) new PageInfo<>(list).getTotal();

        for (JokeBean bean : list) {
            dealJokeBean(bean);
        }

        base.setData(list);
        base.setTotal(total);
        return base;
    }

    @Override
    public BaseListResult getJokeLikeByUserId(Integer page, Integer row, String userId) throws Exception {
        BaseListResult base = new BaseListResult();
        PageHelper.startPage(page, row);
        List<JokeBean> datas = new ArrayList<>();
        List<JokeLikeBean> list = jokeMapper.selectJokeLikeByUserId(userId);
        for (JokeLikeBean bean : list) {
            JokeBean jokeBean = jokeMapper.getJokeBeanById(bean.getJoke_id());
            jokeBean.setSelfApprovalTime(bean.getApprovalTime());
            dealJokeBean(jokeBean);
            datas.add(jokeBean);
        }
        int total = (int) new PageInfo<>(list).getTotal();
        base.setData(datas);
        base.setTotal(total);
        return base;
    }

    private JokeBean dealJokeBean(JokeBean bean) throws Exception {
        UserBean user = userMapper.getUserById(bean.getJokeUserId());
        List<JokeLikeBean> likeBeans = selectJokeLikeById(bean.getJokeId());
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
        return bean;
    }

    @Override
    public JokeBean getJokeById(String jokeId) throws Exception {
        JokeBean jokeBean = jokeMapper.getJokeBeanById(jokeId);
        dealJokeBean(jokeBean);
        return jokeBean;
    }

    @Override
    public BaseListResult getJokeListFog(Integer page, Integer row, String key) throws Exception {
        BaseListResult base = new BaseListResult();
        PageHelper.startPage(page, row);
        List<JokeBean> list;
        key = URLDecoder.decode(key, "utf-8");
        list = jokeMapper.getJokeListFog(key);
        int total = (int) new PageInfo<>(list).getTotal();

        for (JokeBean bean : list) {
            dealJokeBean(bean);
        }

        base.setData(list);
        base.setTotal(total);
        return base;
    }

    @Override
    public BaseListResult searchJokeList(Integer page, Integer row, JokeBean jokeBean) throws Exception {
        BaseListResult base = new BaseListResult();
        PageHelper.startPage(page, row);
        List<JokeBean> list = jokeMapper.searchJokeList(jokeBean);
        int total = (int) new PageInfo<>(list).getTotal();

        for (JokeBean bean : list) {
            dealJokeBean(bean);
        }

        base.setData(list);
        base.setTotal(total);
        return base;
    }
}
