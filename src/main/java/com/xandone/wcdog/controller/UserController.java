package com.xandone.wcdog.controller;

import com.xandone.wcdog.config.Config;
import com.xandone.wcdog.pojo.Base.BaseResult;
import com.xandone.wcdog.pojo.LoginBean;
import com.xandone.wcdog.pojo.UserBean;
import com.xandone.wcdog.service.UserService;
import com.xandone.wcdog.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/regist", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult regist(@RequestParam(value = "name") String name,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "nickname") String nickname) {
        BaseResult baseResult = new BaseResult();
        try {
            UserBean user = new UserBean();
            user.setName(name);
            user.setPassword(password);
            user.setNickname(nickname);
            user.setUserId(IDUtils.RandomId());

            UserBean tempBean1 = userService.getUserByName(name);
            if (tempBean1 == null) {
                baseResult.setCode(Config.ERROR_CODE);
                baseResult.setMsg("该邮箱已注册");
                return baseResult;
            }

            UserBean tempBean2 = userService.getUserByNick(nickname);
            if (tempBean2 == null) {
                baseResult.setCode(Config.ERROR_CODE);
                baseResult.setMsg("该昵称已存在");
                return baseResult;
            }

            userService.addUser(user);
            baseResult.setCode(Config.SUCCESS_CODE);
            baseResult.setMsg("注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            baseResult.setMsg(Config.MES_SERVER_ERROR);
        }
        return baseResult;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult login(@RequestParam(value = "name") String name,
                            @RequestParam(value = "psw") String psw) {
        BaseResult baseResult = new BaseResult();
        List list = new ArrayList();
        UserBean userBean = null;
        try {
            userBean = userService.getUserByName(name);
            if (userBean == null) {
                baseResult.setMsg("不存在该用户");
                baseResult.setCode(Config.ERROR_CODE);
                return baseResult;
            } else if (!userBean.getPassword().equals(psw)) {
                baseResult.setMsg("密码错误");
                baseResult.setCode(Config.ERROR_CODE);
                return baseResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setMsg(Config.MES_SERVER_ERROR);
            baseResult.setCode(Config.ERROR_CODE);
        }
        LoginBean loginBean = new LoginBean();
        loginBean.setUserBean(userBean);
        list.add(loginBean);
        baseResult.setData(list);
        baseResult.setCode(Config.SUCCESS_CODE);
        baseResult.setMsg("登录成功");
        return baseResult;
    }

    @RequestMapping(value = "/userlist")
    @ResponseBody
    public BaseResult getAllUser(Integer page, Integer row) {

        BaseResult baseResult = new BaseResult();
        List<UserBean> list = null;
        try {
            list = userService.getAllUser(page, row);
            if (list != null && list.isEmpty()) {
                baseResult.setCode(Config.SUCCESS_CODE);
                baseResult.setMsg(Config.MES_REQUEST_SUCCESS);
            } else {
                baseResult.setCode(Config.ERROR_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            baseResult.setMsg(Config.MES_SERVER_ERROR);
        }
        baseResult.setData(list);

        return baseResult;
    }

}
