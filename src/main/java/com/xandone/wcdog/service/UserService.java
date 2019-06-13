package com.xandone.wcdog.service;

import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.pojo.UserBean;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2019/1/15 22:30
 * description：
 */
public interface UserService {
    UserBean addUser(UserBean userBean) throws Exception;

    UserBean getUserByName(String name) throws Exception;

    UserBean getUserById(String userId) throws Exception;

    UserBean getUserByNick(String userId) throws Exception;

    void updateUser(UserBean userBean) throws Exception;

    void deleteUserById(String userId) throws Exception;

    void deleteUserByList(List<String> userIds) throws Exception;

    BaseListResult getAllUser(Integer page, Integer row) throws Exception;

}
