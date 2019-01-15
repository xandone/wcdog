package com.xandone.wcdog.controller;

import com.xandone.wcdog.pojo.Base.BaseResult;
import com.xandone.wcdog.pojo.LoginBean;
import com.xandone.wcdog.pojo.UserBean;
import com.xandone.wcdog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public BaseResult login(@RequestParam(value = "name") String name,
                            @RequestParam(value = "psw") String psw) {
        BaseResult baseBean = new BaseResult();
        List list = new ArrayList();
        UserBean userBean = null;
        try {
            userBean = userService.getUserByName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoginBean loginBean = new LoginBean();
        loginBean.setUserBean(userBean);
        list.add(loginBean);
        baseBean.setData(list);
        return baseBean;
    }

    @RequestMapping(value = "/userlist")
    @ResponseBody
    public BaseResult getAllUser(Integer page, Integer row) {
        BaseResult baseBean = new BaseResult();
        List<UserBean> list = null;
        try {
            list = userService.getAllUser(page, row);
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseBean.setData(list);
        return baseBean;
    }

}
