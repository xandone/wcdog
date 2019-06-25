package com.xandone.wcdog.controller;

import com.xandone.wcdog.config.Config;
import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.pojo.Base.BaseResult;
import com.xandone.wcdog.pojo.UserBean;
import com.xandone.wcdog.service.JokeService;
import com.xandone.wcdog.service.UserService;
import com.xandone.wcdog.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.xandone.wcdog.config.Config.SUCCESS_CODE;

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
    @Autowired
    JokeService jokeService;

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult regist(@RequestParam(value = "name") String name,
                             @RequestParam(value = "psw") String psw,
                             @RequestParam(value = "nickname") String nickname) {
        BaseResult baseResult = new BaseResult();
        List<UserBean> list = new ArrayList<>();
        try {
            UserBean user = new UserBean();
            user.setName(name);
            user.setPassword(psw);
            user.setNickname(nickname);
            user.setUserId(IDUtils.RandomId());
            user.setRegistTime(new Date());

            UserBean tempBean1 = userService.getUserByName(name);
            if (tempBean1 != null) {
                baseResult.setCode(Config.ERROR_CODE);
                baseResult.setMsg("该用户名已注册");
                return baseResult;
            }

            UserBean tempBean2 = userService.getUserByNick(nickname);
            if (tempBean2 != null) {
                baseResult.setCode(Config.ERROR_CODE);
                baseResult.setMsg("该昵称已存在");
                return baseResult;
            }

            userService.addUser(user);
            list.add(user);
            baseResult.setData(list);
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
        List<UserBean> list = new ArrayList<>();
        UserBean userBean;
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
            } else {
                list.add(userBean);
                baseResult.setData(list);
                baseResult.setCode(Config.SUCCESS_CODE);
                baseResult.setMsg("登录成功");

                userBean.setLastLoginTime(new Date());
                userService.updateUser(userBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setMsg(Config.MES_SERVER_ERROR);
            baseResult.setCode(Config.ERROR_CODE);
        }
        return baseResult;
    }

    @RequestMapping(value = "/selfJokes")
    @ResponseBody
    public BaseListResult getSelfJokes(@RequestParam(value = "page") Integer page,
                                       @RequestParam(value = "row") Integer row,
                                       @RequestParam(value = "userId") String userId) {
        BaseListResult baseResult = new BaseListResult();
        try {
            BaseListResult result = jokeService.getUserSelfJokes(page, row, userId);
            if (result != null) {
                result.setCode(SUCCESS_CODE);
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

    @RequestMapping(value = "/thumb")
    @ResponseBody
    public BaseListResult getSelfLikeJokesById(@RequestParam(value = "page") Integer page,
                                               @RequestParam(value = "row") Integer row,
                                               @RequestParam(value = "userId") String userId) {
        BaseListResult baseResult = new BaseListResult();
        try {
            BaseListResult result = jokeService.getJokeLikeByUserId(page, row, userId);
            if (result != null) {
                result.setCode(SUCCESS_CODE);
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

    @RequestMapping(value = "/userInfo")
    @ResponseBody
    public BaseResult getUserInfo(@RequestParam(value = "userId") String userId) {
        BaseResult baseResult = new BaseResult();
        List<UserBean> list = new ArrayList<>();
        try {
            UserBean userBean = userService.getUserById(userId);
            list.add(userBean);
            baseResult.setData(list);
            baseResult.setCode(SUCCESS_CODE);
            baseResult.setMsg(Config.MES_REQUEST_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            baseResult.setMsg(Config.MES_SERVER_ERROR);
        }
        return baseResult;
    }

    @RequestMapping(value = "/userInfo/modify")
    @ResponseBody
    public BaseResult modifyUserInfo(@RequestParam(value = "userId") String userId,
                                     @RequestParam(value = "talk") String talk,
                                     @RequestParam(value = "address") String address) {
        BaseResult baseResult = new BaseResult();
        List<UserBean> list = new ArrayList<>();
        try {
            UserBean userBean = userService.getUserById(userId);
            userBean.setTalk(talk);
            userBean.setAddress(address);
            userService.updateUser(userBean);
            list.add(userBean);
            baseResult.setData(list);
            baseResult.setCode(SUCCESS_CODE);
            baseResult.setMsg(Config.MES_REQUEST_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            baseResult.setMsg(Config.MES_SERVER_ERROR);
        }
        return baseResult;
    }

}
