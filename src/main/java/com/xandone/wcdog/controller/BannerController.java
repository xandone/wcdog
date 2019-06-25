package com.xandone.wcdog.controller;

import com.xandone.wcdog.config.Config;
import com.xandone.wcdog.pojo.BannerBean;
import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.pojo.Base.BaseResult;
import com.xandone.wcdog.pojo.UserBean;
import com.xandone.wcdog.service.BannerService;
import com.xandone.wcdog.service.UserService;
import com.xandone.wcdog.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @ResponseBody
    public BaseResult getBannerData() {
        BaseResult baseResult = new BaseResult();
        try {
            BaseResult temp = bannerService.getBannerData();
            if (temp != null) {
                temp.setCode(Config.SUCCESS_CODE);
                return temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            return baseResult;
        }
        baseResult.setCode(Config.ERROR_CODE);
        return baseResult;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addBanner(@RequestBody Map<String, String> map) {
        BaseResult baseResult = new BaseResult();
        try {
            String userId = map.get("userId");
            String title = map.get("title");
            String imgUrl = map.get("imgUrl");
            String articleUrl = map.get("articleUrl");
            UserBean userBean = userService.getUserById(userId);
            if (userBean == null) {
                baseResult.setCode(Config.ERROR_CODE);
                return baseResult;
            }
            BannerBean temp = new BannerBean();
            temp.setTitle(title);
            temp.setUserId(userId);
            temp.setImgUrl(imgUrl);
            temp.setArticleUrl(articleUrl);
            temp.setArticelId(IDUtils.RandomId());
            temp.setUpTime(new Date());
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

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteBannerById(@RequestBody Map<String, String> map) {
        BaseResult baseResult = new BaseResult();
        try {
            String articelId = map.get("articelId");
            String adminId = map.get("adminId");
            if (!Config.ADMIN_ID.equals(adminId)) {
                baseResult.setCode(Config.ERROR_CODE);
                baseResult.setMsg("没有权限");
                return baseResult;
            }
            bannerService.deleteBannerById(articelId);
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
