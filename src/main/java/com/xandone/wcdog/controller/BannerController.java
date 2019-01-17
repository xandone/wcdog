package com.xandone.wcdog.controller;

import com.xandone.wcdog.config.Config;
import com.xandone.wcdog.pojo.BannerBean;
import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.pojo.Base.BaseResult;
import com.xandone.wcdog.pojo.JokeBean;
import com.xandone.wcdog.pojo.UserBean;
import com.xandone.wcdog.service.BannerService;
import com.xandone.wcdog.service.UserService;
import com.xandone.wcdog.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：xandone
 * created on  ：2019/1/17 16:30
 * description：
 */
@Controller
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    BannerService bannerService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/list")
    public BaseResult getBannerData() {
        BaseResult result = bannerService.getBannerData();
        return result;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addJoke(@RequestParam(value = "title") String title,
                              @RequestParam(value = "userId") String userId,
                              @RequestParam(value = "imgUrl") String imgUrl) {
        BaseResult baseResult = new BaseResult();
        try {
            UserBean userBean = userService.getUserById(userId);
            if (userBean == null) {
                baseResult.setCode(Config.ERROR_CODE);
                return baseResult;
            }
            BannerBean temp = new BannerBean();
            temp.setTitle(title);
            temp.setUserId("1");
            temp.setImgUrl(imgUrl);
            temp.setArticelId(IDUtils.RandomId());
            BannerBean bannerBean = bannerService.addBanner(temp);
            List<BannerBean> list = new ArrayList<>();
            list.add(bannerBean);
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
