package com.guohui.weather.bean;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dikaros on 2016/5/25.
 * 空气质量指数
 */
public class Aqi {
/*
    "aqi": {
                "city": {
                    "aqi": "28",
                    "co": "1",
                    "no2": "46",
                    "o3": "16",
                    "pm10": "26",
                    "pm25": "19",
                    "qlty": "优",
                    "so2": "11"
                }
            },
 */

    //空气质量指数
    String aqi;

    //一氧化碳
    String co;

    //二氧化氮
    String no2;

    //臭氧
    String o3;

    //PM10
    String pm10;

    //PM25
    String pm25;

    //空气质量类别
    String qlty;

    //二氧化硫
    String so2;

    public Aqi(){

    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getQlty() {
        return qlty;
    }

    public void setQlty(String qlty) {
        this.qlty = qlty;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    @Override
    public String toString() {
        return "Aqi{" +
                "aqi='" + aqi + '\'' +
                ", co='" + co + '\'' +
                ", no2='" + no2 + '\'' +
                ", o3='" + o3 + '\'' +
                ", pm10='" + pm10 + '\'' +
                ", pm25='" + pm25 + '\'' +
                ", qlty='" + qlty + '\'' +
                ", so2='" + so2 + '\'' +
                '}';
    }
}
