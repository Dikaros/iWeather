package com.guohui.weather.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dikaros on 2016/5/25.
 */
public class HourlyForecast {

    /*
     {
                    "date": "2016-05-25 16:00",
                    "hum": "97",
                    "pop": "95",
                    "pres": "1006",
                    "tmp": "22",
                    "wind": {
                        "deg": "135",
                        "dir": "东南风",
                        "sc": "微风",
                        "spd": "4"
                    }
                },
     */

    public HourlyForecast(String jsonFile) throws JSONException {
        JSONObject root = new JSONObject(jsonFile);
        date = root.getString("date");
        hum = root.getString("hum");
        pop = root.getString("pop");
        pres = root.getString("pres");
        tmp = root.getString("tmp");
        wind = new Wind(root.getJSONObject("wind").toString());

    }
    String date;
    //湿度
    String hum;
    //降水概率
    String pop;
    //气压
    String pres;
    //温度
    String tmp;
    //风
    Wind wind;

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

   public class  Wind{

        public Wind(String jsonFile) throws JSONException {
            JSONObject root = new JSONObject(jsonFile);
            deg = root.getString("deg");
            dir = root.getString("dir");
            sc = root.getString("sc");
            spd = root.getString("spd");
        }
        //风向角度
        String deg;
        //风向方向
        String dir;
        //风力等级
        String sc;
        //风速
        String spd;

        @Override
        public String toString() {
            return dir+" "+spd+"米/秒";
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    @Override
    public String toString() {
        return "HourlyForecast{" +
                "date='" + date + '\'' +
                ", hum='" + hum + '\'' +
                ", pop='" + pop + '\'' +
                ", pres='" + pres + '\'' +
                ", tmp='" + tmp + '\'' +
                ", wind=" + wind +
                '}';
    }
}
