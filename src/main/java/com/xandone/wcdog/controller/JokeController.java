package com.xandone.wcdog.controller;

import com.xandone.wcdog.config.Config;
import com.xandone.wcdog.pojo.Base.BaseResult;
import com.xandone.wcdog.pojo.JokeBean;
import com.xandone.wcdog.pojo.UserBean;
import com.xandone.wcdog.service.JokeService;
import com.xandone.wcdog.service.UserService;
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

}
