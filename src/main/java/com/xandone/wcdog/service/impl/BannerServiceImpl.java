package com.xandone.wcdog.service.impl;

import com.xandone.wcdog.mapper.BannerMapper;
import com.xandone.wcdog.pojo.BannerBean;
import com.xandone.wcdog.pojo.Base.BaseResult;
import com.xandone.wcdog.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2019/1/17 16:32
 * description：
 */
@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    BannerMapper bannerMapper;

    @Override
    public BaseResult getBannerData() {
        BaseResult result = new BaseResult();
        List<BannerBean> list = bannerMapper.getBannerData();
        result.setData(list);
        return result;
    }

    @Override
    public BannerBean addBanner(BannerBean bean) throws Exception {
        bannerMapper.addBanner(bean);
        return bean;
    }

    @Override
    public void deleteBannerById(String articelId) throws Exception {
        bannerMapper.deleteBannerById(articelId);
    }
}
