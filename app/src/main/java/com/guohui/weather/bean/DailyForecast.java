package com.guohui.weather.bean;

/**
 * Created by Dikaros on 2016/5/22.
 */
public class DailyForecast {

    String date;

    //天气状况
    String cond;

    //天文时间
    class Astro{
        String sr;
        String ss;
    }




    /*
     "daily_forecast": [
                        {
                            "date": "2015-07-15",
                            "astro": {
                                "sr": "04:40",
                                "ss": "19:19"
                            },
                            "cond": {
                                "code_d": "100",白天天气代码
                                "code_n": "101",
                                "txt_d": "晴",白天天气描述
                                "txt_n": "多云"
                            },
                            "hum": "48",湿度
                            "pcpn": "0.0",降雨量
                            "pop": "0",降水概率
                            "pres": "1005",气压
                            "tmp": {温度
                                "max": "33",
                                "min": "24"
                            },
                            "vis": "10",能见度
                            "wind": {
                                "deg": "192",风向
                                "dir": "东南风",
                                "sc": "4-5",风力等级
                                "spd": "11"风速（kmph)
                            }
                        },
     */
}
