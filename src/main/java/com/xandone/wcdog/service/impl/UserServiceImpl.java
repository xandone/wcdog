package com.xandone.wcdog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xandone.wcdog.mapper.UserMapper;
import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.pojo.UserBean;
import com.xandone.wcdog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserBean addUser(UserBean userBean) {
        userMapper.addUser(userBean);
        return userBean;
    }

    @Override
    public UserBean getUserByName(String name) {
        UserBean userBean = userMapper.getUserByName(name);
        return userBean;
    }

    @Override
    public UserBean getUserById(String userId) throws Exception {
        UserBean userBean = userMapper.getUserById(userId);
        return userBean;
    }

    @Override
    public UserBean getUserByNick(String userId) throws Exception {
        UserBean userBean = userMapper.getUserByNick(userId);
        return userBean;
    }

    @Override
    public void deleteUserById(String userId) throws Exception {
        userMapper.deleteUserById(userId);
    }

    @Override
    public void deleteUserByList(List<String> userIds) throws Exception {
        userMapper.deleteUserByList(userIds);
    }


    @Override
    public BaseListResult getAllUser(Integer page, Integer row) {
        BaseListResult base = new BaseListResult();
        PageHelper.startPage(page, row);
        List<UserBean> list = userMapper.getUserList();
        int total = (int) new PageInfo<>(list).getTotal();
        base.setData(list);
        base.setTotal(total);
        return base;
    }
}
