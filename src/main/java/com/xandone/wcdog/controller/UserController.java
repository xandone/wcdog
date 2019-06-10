package com.xandone.wcdog.controller;

import com.xandone.wcdog.config.Config;
import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.pojo.Base.BaseResult;
import com.xandone.wcdog.pojo.LoginBean;
import com.xandone.wcdog.pojo.UserBean;
import com.xandone.wcdog.service.UserService;
import com.xandone.wcdog.utils.IDUtils;
import com.xandone.wcdog.utils.SimpleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：xandone
 * created on  ：2019/1/13 10:06
 * description：
 */
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
    public BaseResult login(@RequestBody Map<String, String> map) {
        BaseResult baseResult = new BaseResult();
        List list = new ArrayList();
        UserBean userBean = null;
        String name = map.get("name");
        String psw = map.get("psw");
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
    public BaseListResult getAllUser(@RequestParam(value = "page") Integer page,
                                     @RequestParam(value = "row") Integer row) {
        BaseListResult baseResult = new BaseListResult();
        try {
            BaseListResult result = userService.getAllUser(page, row);
            if (result != null) {
                result.setCode(Config.SUCCESS_CODE);
                result.setMsg(Config.MES_REQUEST_SUCCESS);
                return result;
            }
            baseResult.setCode(Config.ERROR_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            baseResult.setMsg(Config.MES_SERVER_ERROR);
        }
        return baseResult;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteUserById(@RequestParam(value = "userId") String userId,
                                     @RequestParam(value = "adminId") String adminId) {
        BaseListResult baseResult = new BaseListResult();
        try {
            userService.deleteUserById(userId);
            baseResult.setCode(Config.SUCCESS_CODE);
            baseResult.setMsg("删除成功");
            return baseResult;
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            baseResult.setMsg("删除失败");
        }
        return baseResult;
    }

    @RequestMapping(value = "/deleteList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteUserByList(@RequestParam(value = "userIds") String userIds) {
        BaseListResult baseResult = new BaseListResult();
        System.out.println("user:" + userIds);
        try {
            userService.deleteUserByList(SimpleUtils.toList(userIds));
            baseResult.setCode(Config.SUCCESS_CODE);
            baseResult.setMsg("删除成功");
            return baseResult;
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            baseResult.setMsg("删除失败");
        }
        return baseResult;
    }

}
