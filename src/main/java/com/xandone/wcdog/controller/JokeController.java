package com.xandone.wcdog.controller;

import com.xandone.wcdog.config.Config;
import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.pojo.Base.BaseResult;
import com.xandone.wcdog.pojo.JokeBean;
import com.xandone.wcdog.pojo.UserBean;
import com.xandone.wcdog.service.JokeService;
import com.xandone.wcdog.service.UserService;
import com.xandone.wcdog.utils.SimpleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/joke")
public class JokeController {
    @Autowired
    JokeService jokeService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addJoke(String title, String jokeUserId, String content) {
        BaseResult baseResult = new BaseResult();
        try {
            UserBean userBean = userService.getUserById(jokeUserId);
            if (userBean == null) {
                baseResult.setCode(Config.ERROR_CODE);
                return baseResult;
            }
            JokeBean jokeBean = jokeService.addJoke(title, jokeUserId, content);
            jokeBean.setJokeUserIcon(userBean.getUserIcon());
            jokeBean.setJokeUserNick(userBean.getNickname());
            List<JokeBean> list = new ArrayList<>();
            list.add(jokeBean);
            baseResult.setData(list);
            baseResult.setCode(Config.SUCCESS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            return baseResult;
        }

        return baseResult;
    }

    @RequestMapping(value = "/jokelist")
    @ResponseBody
    public BaseListResult getAllUser(Integer page, Integer row) {
        BaseListResult baseResult = new BaseListResult();
        try {
            BaseListResult result = jokeService.getAllJoke(page, row);
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
    public BaseResult deleteJokeById(String jokeId, String adminId) {
        BaseListResult baseResult = new BaseListResult();
        System.out.println("delete:.." + jokeId);
        try {
            jokeService.deleteJokeById(jokeId);
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
    public BaseResult deleteJokeById(String jokeIds) {
        BaseListResult baseResult = new BaseListResult();
        System.out.println("joke:" + jokeIds);
        try {
            jokeService.deleteJokeByList(SimpleUtils.toList(jokeIds));
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
