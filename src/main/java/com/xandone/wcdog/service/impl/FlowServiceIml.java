package com.xandone.wcdog.service.impl;

import com.xandone.wcdog.mapper.FlowMapper;
import com.xandone.wcdog.pojo.FlowBean;
import com.xandone.wcdog.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author ：xandone
 * created on  ：2019/6/12 16:12
 * description：
 */
@Service
public class FlowServiceIml implements FlowService {
    @Autowired
    FlowMapper flowMapper;

    @Override
    public FlowBean getFlowData(String adminId) {
        return flowMapper.getFlowData();
    }


}
