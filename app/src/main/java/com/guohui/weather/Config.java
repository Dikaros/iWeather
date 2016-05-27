package com.guohui.weather;

import java.util.HashMap;

/**
 * 配置信息类
 * Created by Dikaros on 2016/5/25.
 */
public class Config {

    public static HashMap<Integer,Integer> WEATHER_ICON_MAP = new HashMap<>();

    static {

    }


    /**
     * 百度API_KEY
     */
    public final static String BAIDU_API_KEY = "db642b2fac4fafe26849179ad8883592";


    /**
     * 和风天气API_KEY
     */
    public final static String HEFENG_API_KEY ="ae70cfa684db4844bdcd06ddde94f324";

    /**
     * 百度天气详情请求地址
     */
    public final static String BAIDU_WEATHER_DETAIL_URL = "http://apis.baidu.com/heweather/pro/weather?city=";

}
