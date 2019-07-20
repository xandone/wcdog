package com.xandone.wcdog.service.impl;

import com.xandone.wcdog.mapper.AdminMapper;
import com.xandone.wcdog.mapper.FlowMapper;
import com.xandone.wcdog.pojo.FlowBean;
import com.xandone.wcdog.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @author ：xandone
 * created on  ：2019/6/12 16:12
 * description：
 */
@Service
public class FlowServiceIml implements FlowService {
    @Autowired
    FlowMapper flowMapper;
    @Autowired
    AdminMapper adminMapper;

    @Override
    public FlowBean getFlowData(String adminId) throws Exception {
        FlowBean flowBean = flowMapper.getFlowData();
        if (flowBean == null) {
            flowBean = new FlowBean();
            flowBean.setPostTime(new Date());
            addFlow(flowBean);
        }
        FlowBean temp = getAllCount();
        flowBean.setAllAdminCount(temp.getAllAdminCount());
        flowBean.setAllUserCount(temp.getAllUserCount());
        flowBean.setAllJokeCount(temp.getAllJokeCount());
        flowBean.setAllCommentCount(temp.getAllCommentCount());
        flowBean.setAllThumbCount(temp.getAllThumbCount());

        return flowBean;
    }

    @Override
    public void upDateFlow(FlowBean flowBean) throws Exception {
        flowMapper.upDateFlow(flowBean);
    }

    @Override
    public void addFlow(FlowBean flowBean) throws Exception {
        flowMapper.addFlow(flowBean);
    }

    @Override
    public FlowBean getAllCount() throws Exception {
        FlowBean flowBean = new FlowBean();
        flowBean.setAllAdminCount(adminMapper.getAdminCount());
        flowBean.setAllUserCount(adminMapper.getUserCount());
        flowBean.setAllJokeCount(adminMapper.getJokeCount());
        flowBean.setAllCommentCount(adminMapper.getCommentCount());
        flowBean.setAllThumbCount(adminMapper.getThumbCount());
        return flowBean;
    }
}
