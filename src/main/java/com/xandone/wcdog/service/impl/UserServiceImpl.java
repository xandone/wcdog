package com.xandone.wcdog.service.impl;

import com.github.pagehelper.PageHelper;
import com.xandone.wcdog.mapper.UserMapper;
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
    public List<UserBean> getAllUser(Integer page, Integer row) {
        PageHelper.startPage(page, row);
        List<UserBean> list = userMapper.getUserList();
        return list;
    }
}
