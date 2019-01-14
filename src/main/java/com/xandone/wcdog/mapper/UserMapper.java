package com.xandone.wcdog.mapper;

import com.xandone.wcdog.pojo.UserBean;

import java.util.List;

public interface UserMapper {
    void addUser(UserBean user);

    UserBean getUserByName(String name);

    List<UserBean> getUserList();
}
