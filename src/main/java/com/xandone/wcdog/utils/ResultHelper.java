package com.xandone.wcdog.utils;

import com.xandone.wcdog.config.Config;
import com.xandone.wcdog.pojo.Base.BaseResult;

/**
 * @author ：xandone
 * created on  ：2019/7/11 11:13
 * description：
 */
public class ResultHelper {
    public static BaseResult getResult(int code) {
        BaseResult baseResult = new BaseResult();
        switch (code) {
            case Config.ERROR_CODE:
                baseResult.setCode(Config.ERROR_CODE);
                baseResult.setMsg(Config.MES_SERVER_ERROR);
                break;
            case Config.ERROR_BANNED_CODE:
                baseResult.setCode(Config.ERROR_BANNED_CODE);
                baseResult.setMsg(Config.MES_REQUEST_BANNED);
                break;
            default:
                break;
        }
        return baseResult;
    }
}
