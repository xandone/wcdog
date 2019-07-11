package com.xandone.wcdog.config;

/**
 * @author ：xandone
 * created on  ：2019/1/15 9:52
 * description：
 */
public class Config {
    public static final String ADMIN_ID = "250";

    //	public static final String FTP_HOST_PATH = "http://192.168.117.128/images/";
    public static final String FTP_HOST_PATH = "http://192.168.191.1:8060/images/";//访问主机虚拟机中的nginx，主机端口转发
    public static final String FTP_IMAGE_PATH = "www/images/";// 存在ftp图片服务器的路径

    public static final String FTP_IP = "192.168.74.128";//重装系统会变化

    /**
     * apk路径
     */
    public static final String APK_LOAD_URL = "http://192.168.191.1:8081/wcdog/apk/app_wcdog_debug.apk";


    //成功
    public static final int SUCCESS_CODE = 200;
    //失败
    public static final int ERROR_CODE = -1;
    //禁言状态
    public static final int ERROR_BANNED_CODE = 201;


    public static final String MES_REQUEST_SUCCESS = "数据请求成功";
    public static final String MES_SERVER_ERROR = "系统出现异常，请稍后再试..";
    public static final String MES_REQUEST_BANNED = "用户处于禁言状态";

}