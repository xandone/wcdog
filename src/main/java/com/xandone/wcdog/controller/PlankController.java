package com.xandone.wcdog.controller;

import com.xandone.wcdog.config.Config;
import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.pojo.Base.BaseResult;
import com.xandone.wcdog.pojo.PlankTalkBean;
import com.xandone.wcdog.pojo.TalkBean;
import com.xandone.wcdog.pojo.UserBean;
import com.xandone.wcdog.service.PlankService;
import com.xandone.wcdog.service.UserService;
import com.xandone.wcdog.utils.ResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.xandone.wcdog.config.Config.SUCCESS_CODE;

/**
 * @author ：xandone
 * created on  ：2019/7/1 17:17
 * description：
 */
@Controller
@RequestMapping(value = "/plank")
public class PlankController {
    @Autowired
    PlankService plankService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addTalk(@RequestParam(value = "userId") String userId, @RequestParam(value = "talk") String talk) {
        BaseResult baseResult = new BaseResult();
        try {
            UserBean user = userService.getUserById(userId);
            if (user == null) {
                return ResultHelper.getResult(Config.ERROR_CODE);
            }
            if (user.getBanned() == 1) {
                return ResultHelper.getResult(Config.ERROR_BANNED_CODE);
            }
            TalkBean talkBean = plankService.addTalk(userId, talk);
            List<TalkBean> list = new ArrayList<>();
            list.add(talkBean);
            baseResult.setData(list);
            baseResult.setCode(SUCCESS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            return baseResult;
        }

        return baseResult;
    }

    @RequestMapping(value = "/talkList")
    @ResponseBody
    public BaseListResult getTalkList(Integer size) {
        BaseListResult baseResult = new BaseListResult();
        try {
            BaseListResult result = plankService.getTalkList(size);
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

    @RequestMapping(value = "/planktalk/list")
    @ResponseBody
    public BaseListResult getPlankList(@RequestParam(value = "page") Integer page,
                                       @RequestParam(value = "row") Integer row) {
        BaseListResult baseResult = new BaseListResult();
        try {
            BaseListResult result = plankService.getPlankList(page, row);
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


    @RequestMapping(value = "/planktalk/lastplank")
    @ResponseBody
    public BaseResult getLastPlank() {
        BaseResult baseResult = new BaseResult();
        try {
            PlankTalkBean plankTalkBean = plankService.getLastPlank();
            List<PlankTalkBean> list = new ArrayList<>();
            list.add(plankTalkBean);
            baseResult.setData(list);
            baseResult.setCode(SUCCESS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            return baseResult;
        }

        return baseResult;
    }
}
