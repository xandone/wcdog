package com.xandone.wcdog.controller;

import com.xandone.wcdog.config.Config;
import com.xandone.wcdog.pojo.ApkBean;
import com.xandone.wcdog.pojo.Base.BaseResult;
import com.xandone.wcdog.service.ApkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static com.xandone.wcdog.config.Config.SUCCESS_CODE;

/**
 * @author ：xandone
 * created on  ：2019/7/3 11:33
 * description：
 */
@Controller
@RequestMapping(value = "apk")
public class ApkController {
    @Autowired
    ApkService apkService;

    @RequestMapping(value = "/checkversion")
    @ResponseBody
    public BaseResult getLastApk() {
        BaseResult baseResult = new BaseResult();
        try {
            ApkBean apkBean = apkService.getLastApk();
            List<ApkBean> list = new ArrayList<>();
            list.add(apkBean);
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
