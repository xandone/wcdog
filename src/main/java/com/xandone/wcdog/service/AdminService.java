package com.xandone.wcdog.service;

import com.xandone.wcdog.pojo.AdminBean;
import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.pojo.UserBean;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2019/1/15 22:30
 * description：
 */
public interface AdminService {
    AdminBean getAdminByName(String name) throws Exception;

    void updateAdmin(AdminBean userBean) throws Exception;

    void deleteUserById(String userId) throws Exception;

    void deleteUserByList(List<String> userIds) throws Exception;

    BaseListResult getAllUser(Integer page, Integer row) throws Exception;

    BaseListResult searchUserList(Integer page, Integer row, UserBean userBean) throws Exception;

    void updateUserByBean(UserBean userBean) throws Exception;

}
