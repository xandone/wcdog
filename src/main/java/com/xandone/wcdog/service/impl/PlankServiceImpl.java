package com.xandone.wcdog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xandone.wcdog.mapper.PlankMapper;
import com.xandone.wcdog.mapper.UserMapper;
import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.pojo.PlankTalkBean;
import com.xandone.wcdog.pojo.TalkBean;
import com.xandone.wcdog.pojo.UserBean;
import com.xandone.wcdog.service.PlankService;
import com.xandone.wcdog.utils.IDUtils;
import com.xandone.wcdog.utils.SimpleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ：xandone
 * created on  ：2019/7/1 17:09
 * description：
 */
@Service
public class PlankServiceImpl implements PlankService {
    @Autowired
    PlankMapper plankMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public TalkBean addTalk(String userId, String talkStr) throws Exception {
        TalkBean talkBean = new TalkBean(userId, talkStr);
        talkBean.setSendTime(new Date());
        talkBean.setTalkId(IDUtils.RandomId());
        UserBean userBean = userMapper.getUserById(userId);
        talkBean.setUserNick(userBean.getNickname());
        talkBean.setUserIcon(userBean.getUserIcon());
        plankMapper.addTalk(talkBean);
        return talkBean;
    }

    @Override
    public PlankTalkBean addPlankTalk(String content) throws Exception {
        PlankTalkBean plankTalkBean = new PlankTalkBean(content);
        plankTalkBean.setSendTime(new Date());
        plankTalkBean.setPlankId(IDUtils.RandomId());
        plankMapper.addPlankTalk(plankTalkBean);
        return plankTalkBean;
    }

    @Override
    public BaseListResult getTalkList(Integer size) throws Exception {
        BaseListResult base = new BaseListResult();
        size = size == null ? 10 : size;
        PageHelper.startPage(1, size);
        List<TalkBean> list = plankMapper.getTalkList();
        for (TalkBean bean : list) {
            UserBean userBean = userMapper.getUserById(bean.getUserId());
            bean.setUserNick(userBean.getNickname());
            bean.setUserIcon(userBean.getUserIcon());
            bean.setSendTimeStr(SimpleUtils.getFriendlyTime(bean.getSendTime()));
        }
        int total = (int) new PageInfo<>(list).getTotal();
        base.setData(list);
        base.setTotal(total);
        return base;
    }

    @Override
    public BaseListResult getPlankList(Integer page, Integer row) throws Exception {
        BaseListResult base = new BaseListResult();
        PageHelper.startPage(page, row);
        List<PlankTalkBean> list = plankMapper.getPlankList();
        int total = (int) new PageInfo<>(list).getTotal();
        base.setData(list);
        base.setTotal(total);
        return base;
    }

    @Override
    public PlankTalkBean getLastPlank() throws Exception {
        PlankTalkBean bean = plankMapper.getLastPlank();
        bean.setSendTimeStr(SimpleUtils.getFriendlyTime(bean.getSendTime()));
        return bean;
    }

    @Override
    public void deletePlankById(String id) throws Exception {
        plankMapper.deletePlankById(id);
    }

    @Override
    public void deleteTalkById(String id) throws Exception {
        plankMapper.deleteTalkById(id);

    }
}
