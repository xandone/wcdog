package com.xandone.wcdog.service;

import com.xandone.wcdog.pojo.BannerBean;
import com.xandone.wcdog.pojo.Base.BaseResult;

/**
 * @author ：xandone
 * created on  ：2019/1/17 16:32
 * description：
 */
public interface BannerService {
    BaseResult getBannerData();

    BannerBean addBanner(BannerBean bean) throws Exception;

    void deleteBannerById(String articelId) throws Exception;
}
