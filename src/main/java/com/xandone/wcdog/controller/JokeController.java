package com.xandone.wcdog.controller;

import com.xandone.wcdog.pojo.Base.BaseResult;
import com.xandone.wcdog.pojo.JokeBean;
import com.xandone.wcdog.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/joke")
public class JokeController {
    @Autowired
    JokeService jokeService;

    @RequestMapping("/add")
    @ResponseBody
    public BaseResult addJoke(String title, String jokeUserId, String content) throws Exception {
        JokeBean jokeBean = jokeService.addJoke(title, jokeUserId, content);
        BaseResult baseResult = new BaseResult();
        List<JokeBean> list = new ArrayList<>();
        list.add(jokeBean);
        baseResult.setData(list);
        baseResult.setCode(200);
        
        return baseResult;

    }

}
