package com.xandone.wcdog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xandone.wcdog.mapper.AdminMapper;
import com.xandone.wcdog.mapper.UserMapper;
import com.xandone.wcdog.pojo.AdminBean;
import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.pojo.UserBean;
import com.xandone.wcdog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2019/1/15 22:30
 * description：
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public AdminBean getAdminByName(String name) throws Exception {
        return adminMapper.getAdminByName(name);
    }

    @Override
    public void updateAdmin(AdminBean userBean) throws Exception {
        adminMapper.updateAdmin(userBean);
    }

    @Override
    public void deleteUserById(String userId) throws Exception {
        adminMapper.deleteUserById(userId);
    }

    @Override
    public void deleteUserByList(List<String> userIds) throws Exception {
        adminMapper.deleteUserByList(userIds);
    }


    @Override
    public BaseListResult getAllUser(Integer page, Integer row) {
        BaseListResult base = new BaseListResult();
        PageHelper.startPage(page, row);
        List<UserBean> list = adminMapper.getUserList();
        int total = (int) new PageInfo<>(list).getTotal();
        base.setData(list);
        base.setTotal(total);
        return base;
    }
}
