package com.xandone.wcdog.mapper;

import com.xandone.wcdog.pojo.ApkBean;

/**
 * @author ：xandone
 * created on  ：2019/7/3 11:25
 * description：
 */
public interface ApkMapper {
    ApkBean getLastApk();

    void addLastApk(ApkBean bean);
}
