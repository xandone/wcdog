package com.xandone.wcdog.controller;

import com.xandone.wcdog.config.Config;
import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.xandone.wcdog.config.Config.SUCCESS_CODE;

/**
 * @author ：xandone
 * created on  ：2019/1/14 16:52
 * description：
 */
@Controller
@RequestMapping(value = "admin")
public class AdminController {
    @Autowired
    JokeService jokeService;

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
}
