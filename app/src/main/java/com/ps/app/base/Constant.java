package com.ps.app.base;


public class Constant {

    // 短信SDK的APPKEY,PPSECRET
    public static String APPSECRET = "1e85092208cae3046cd8bfb1c0c7e6ea";//76ebbea54422c6a93a7be1464bb6aa94
    public static String APPKEY = "124d6a9f89af8";//  124d12d1d49d8
    //fir
    public static String FIRTOKEN = "525ef6d95a86dafcf4fe6c21974bce08";
    // 默认使用中国区号
    public static final String DEFAULT_COUNTRY_ID = "86";
    public static final String BASE_URL = "http://211.149.197.241:8080/posec";//http://posec.zpy.cn
    //登录
    public static final String LOGIN_URL = BASE_URL+"/user/login.action";
    //退出
    public static final String LOGOUT_URL = BASE_URL+"/user/logout.action";
    //找回密码
    public static final String FIND_PASSWORD_URL = BASE_URL+"/user/findpwd.action";
    //获取推送消息
    public static final String GET_MSG_URL = BASE_URL+"/msg/list.action";
    //获取查封资产列表
    public static final String GET_ASET_URL = BASE_URL+"/aset/list.action";
    //查封资产搜索
    public static final String ASET_SEARCH_URL = BASE_URL+"/aset/search.action";
    //查封资产详情
    public static final String ASET_DETAIL_URL = BASE_URL+"/aset/detail.action";
    //保外人员列表
    public static final String FREE_MAN_URL = BASE_URL+"/m/list.action";
    //保外人员详情
    public static final String FREE_MAN_DETAIL_URL = BASE_URL+"/m/detail.action";
    //保外人员当前位置
    public static final String FREE_MAN_LOCATION_URL = BASE_URL+"/m/trace/getNewLocation.action";
    //保外人员的轨迹搜索
    public static final String FREE_MAN_LOCATION_SEARCH_URL = BASE_URL+"/m/trace/getRangeLocations.action";
    //警员注册
    public static final String POLICE_REGISTER_URL = BASE_URL+"/p/reg.action";
    //警员修改信息
    public static final String POLICE_MODIFY_INFO_URL = BASE_URL+"/p/modify.action";
}
