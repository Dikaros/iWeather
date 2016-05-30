package com.guohui.weather;

import com.guohui.weather.bean.City;
import com.guohui.weather.bean.Weather;

import java.util.ArrayList;
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

    private static ArrayList<City> cities = new ArrayList<>();

    public static ArrayList<City> getCities(){
        if (cities.size()==0){

        }
        return cities;
    }

    public static HashMap<Integer,Weather> currentCityMap = new HashMap<>();

    public static String APP_ID = "com.dikaros.iweather";

    /**
     * 注册的天气
     */
    public static String KEY_REGISTED_WEATHER = "iweather.register";
}
