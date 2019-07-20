package com.xandone.wcdog.controller;

import com.xandone.wcdog.config.Config;
import com.xandone.wcdog.pojo.Base.BaseResult;
import com.xandone.wcdog.pojo.FlowBean;
import com.xandone.wcdog.service.AdminService;
import com.xandone.wcdog.service.FlowService;
import com.xandone.wcdog.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：xandone
 * created on  ：2019/6/12 16:11
 * description：统计流量
 */
@Controller
@RequestMapping("/flow")
public class FlowController {
    @Autowired
    FlowService flowService;
    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/flowData")
    @ResponseBody
    public BaseResult getFlowData(@RequestParam(value = "adminId") String adminId) {
        BaseResult baseResult = new BaseResult();
        List<FlowBean> list = new ArrayList<>();
        try {
            FlowBean result = flowService.getFlowData(adminId);
            list.add(result);
            baseResult.setData(list);
            if (result != null) {
                baseResult.setCode(Config.SUCCESS_CODE);
                baseResult.setMsg(Config.MES_REQUEST_SUCCESS);
                return baseResult;
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
