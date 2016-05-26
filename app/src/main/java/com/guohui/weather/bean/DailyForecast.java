package com.guohui.weather.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dikaros on 2016/5/22.
 */
public class DailyForecast {

    /*
            {
                    "astro": {
                        "sr": "05:33",
                        "ss": "19:16"
                    },
                    "cond": {
                        "code_d": "306",
                        "code_n": "306",
                        "txt_d": "中雨",
                        "txt_n": "中雨"
                    },
                    "date": "2016-05-25",
                    "hum": "97",
                    "pcpn": "12.0",
                    "pop": "96",
                    "pres": "1006",
                    "tmp": {
                        "max": "24",
                        "min": "19"
                    },
                    "vis": "7",
                    "wind": {
                        "deg": "174",
                        "dir": "南风",
                        "sc": "微风",
                        "spd": "2"
                    }
                },
                {
                    "astro": {
                        "sr": "05:32",
                        "ss": "19:17"
                    },
                    "cond": {
                        "code_d": "306",
                        "code_n": "300",
                        "txt_d": "中雨",
                        "txt_n": "阵雨"
                    },
    */

    public DailyForecast(String jsonFile) throws JSONException {
        JSONObject root = new JSONObject(jsonFile);
        date = root.getString("date");
        hum = root.getString("hum");
        pcpn = root.getString("pcpn");
        pop = root.getString("pop");
        vis = root.getString("vis");
        pres =root.getString("pres");
        astro = new Astro(root.getJSONObject("astro").toString());
        cond = new Cond(root.getJSONObject("cond").toString());
        tmp = new Tmp(root.getJSONObject("tmp").toString());
        wind = new Wind(root.getJSONObject("wind").toString());
    }

    //日期
    String date;

    //气压
    String pres;

    //湿度
    String hum;

    //降雨量
    String pcpn;

    //降水概率
    String pop;

    //能见度
    String vis;

    //天文数值
    Astro astro;

    //天气状况
    Cond cond;

    //温度
    Tmp tmp;

    //风
    Wind wind;

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

    public String getPcpn() {
        return pcpn;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public Astro getAstro() {
        return astro;
    }

    public void setAstro(Astro astro) {
        this.astro = astro;
    }

    public Cond getCond() {
        return cond;
    }

    public void setCond(Cond cond) {
        this.cond = cond;
    }

    public Tmp getTmp() {
        return tmp;
    }

    public void setTmp(Tmp tmp) {
        this.tmp = tmp;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    //天文数值
    public class Astro{

        public Astro(String jsonFile) throws JSONException {
            JSONObject root = new JSONObject(jsonFile);
            sr = root.getString("sr");
            ss = root.getString("ss");
        }
        //日出
        String sr;
        //日落
        String ss;

        public String getSr() {
            return sr;
        }

        public String getSs() {
            return ss;
        }
    }

    //天气状况
    public class Cond{

        public Cond(String jsonFile) throws JSONException {
            JSONObject root = new JSONObject(jsonFile);
            code_d = root.getString("code_d");
            code_n = root.getString("code_n");
            txt_d =root.getString("txt_d");
            txt_n = root.getString("txt_n");

        }
        //白天天气代码
        String code_d;
        //夜间天气代码
        String code_n;
        //白天天气描述
        String txt_d;
        //夜间天气描述
        String txt_n;

        public String getCode_d() {
            return code_d;
        }

        public void setCode_d(String code_d) {
            this.code_d = code_d;
        }

        public String getCode_n() {
            return code_n;
        }

        public void setCode_n(String code_n) {
            this.code_n = code_n;
        }

        public String getTxt_d() {
            return txt_d;
        }

        public void setTxt_d(String txt_d) {
            this.txt_d = txt_d;
        }

        public String getTxt_n() {
            return txt_n;
        }

        public void setTxt_n(String txt_n) {
            this.txt_n = txt_n;
        }
    }

  public    class  Tmp{

        public Tmp(String jsonFile) throws JSONException {
            JSONObject root = new JSONObject(jsonFile);
            max = root.getString("max");
            min = root.getString("min");
        }
        //最高温度
        String max;
        //最低温度
        String min;

        public String getMax() {
            return max;
        }

        public void setMax(String max) {
            this.max = max;
        }

        public String getMin() {
            return min;
        }

        public void setMin(String min) {
            this.min = min;
        }
    }

    class  Wind{

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

        public String getDeg() {
            return deg;
        }

        public void setDeg(String deg) {
            this.deg = deg;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public String getSc() {
            return sc;
        }

        public void setSc(String sc) {
            this.sc = sc;
        }

        public String getSpd() {
            return spd;
        }

        public void setSpd(String spd) {
            this.spd = spd;
        }
    }

    @Override
    public String toString() {
        return "DailyForecast{" +
                "date='" + date + '\'' +
                ", hum='" + hum + '\'' +
                ", pcpn='" + pcpn + '\'' +
                ", pop='" + pop + '\'' +
                ", vis='" + vis + '\'' +
                ", astro=" + astro +
                ", cond=" + cond +
                ", tmp=" + tmp +
                ", wind=" + wind +
                '}';
    }

//    05-25 17:27:11.699 10137-10137/? E/result: {"HeWeather data service 3.0":[{"alarms":[{}],"aqi":{"city":{"aqi":"28","co":"1","no2":"46","o3":"16","pm10":"26","pm25":"19","qlty":"优","so2":"11"}},"basic":{"city":"长沙","cnty":"中国","id":"CN101250101","lat":"28.197000","lon":"112.967000","update":{"loc":"2016-05-25 16:52","utc":"2016-05-25 08:52"}},"daily_forecast":[{"astro":{"sr":"05:33","ss":"19:16"},"cond":{"code_d":"306","code_n":"306","txt_d":"中雨","txt_n":"中雨"},"date":"2016-05-25","hum":"97","pcpn":"12.0","pop":"96","pres":"1006","tmp":{"max":"24","min":"19"},"vis":"7","wind":{"deg":"174","dir":"南风","sc":"微风","spd":"2"}},{"astro":{"sr":"05:32","ss":"19:17"},"cond":{"code_d":"306","code_n":"300","txt_d":"中雨","txt_n":"阵雨"},"date":"2016-05-26","hum":"95","pcpn":"10.1","pop":"92","pres":"1009","tmp":{"max":"24","min":"18"},"vis":"2","wind":{"deg":"318","dir":"北风","sc":"微风","spd":"10"}},{"astro":{"sr":"05:32","ss":"19:18"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2016-05-27","hum":"83","pcpn":"3.3","pop":"83","pres":"1007","tmp":{"max":"21","min":"18"},"vis":"10","wind":{"deg":"345","dir":"北风","sc":"微风","spd":"3"}},{"astro":{"sr":"05:32","ss":"19:18"},"cond":{"code_d":"305","code_n":"101","txt_d":"小雨","txt_n":"多云"},"date":"2016-05-28","hum":"84","pcpn":"0.3","pop":"52","pres":"1006","tmp":{"max":"23","min":"18"},"vis":"10","wind":{"deg":"343","dir":"北风","sc":"微风","spd":"0"}},{"astro":{"sr":"05:32","ss":"19:19"},"cond":{"code_d":"300","code_n":"300","txt_d":"阵雨","txt_n":"阵雨"},"date":"2016-05-29","hum":"87","pcpn":"4.3","pop":"67","pres":"1007","tmp":{"max":"25","min":"21"},"vis":"9","wind":{"deg":"330","dir":"南风","sc":"微风","spd":"7"}},{"astro":{"sr":"05:31","ss":"19:19"},"cond":{"code_d":"305","code_n":"101","txt_d":"小雨","txt_n":"多云"},"date":"2016-05-30","hum":"68","pcpn":"1.0","pop":"54","pres":"1006","tmp":{"max":"27","min":"21"},"vis":"10","wind":{"deg":"145","dir":"南风","sc":"微风","spd":"7"}},{"astro":{"sr":"05:31","ss":"19:20"},"cond":{"code_d":"305","code_n":"101","txt_d":"小雨","txt_n":"多云"},"date":"2016-05-31","hum":"69","pcpn":"4.9","pop":"43","pres":"1002","tmp":{"max":"31","min":"24"},"vis":"9","wind":{"deg":"201","dir":"南风","sc":"微风","spd":"9"}},{"astro":{"sr":"05:32","ss":"19:21"},"cond":{"code_d":"305","code_n":"305","txt_d":"小雨","txt_n":"小雨"},"date":"2016-06-01","hum":"90","pcpn":"32.8","pop":"41","pres":"1004","tmp":{"max":"28","min":"19"},"vis":"9","wind":{"deg":"323","dir":"西北风","sc":"微风","spd":"0"}},{"astro":{"sr":"05:32","ss":"19:21"},"cond":{"code_d":"309","code_n":"305","txt_d":"毛毛雨/细雨","txt_n":"小雨"},"date":"2016-06-02","hum":"91","pcpn":"1.5","pop":"33","pres":"1006","tmp":{"max":"23","min":"18"},"vis":"2","wind":{"deg":"338","dir":"西北风","sc":"微风","spd":"0"}},{"astro":{"sr":"05:31","ss":"19:22"},"cond":{"code_d":"306","code_n":"501","txt_d":"中雨","txt_n":"雾"},"date":"2016-06-03","hum":"95","pcpn":"9.9","pop":"28","pres":"1006","tmp":{"max":"21","min":"19"},"vis":"7","wind":{"deg":"326","dir":"西北风","sc":"微风","spd":"0"}}],"hourly_forecast":[{"date":"2016-05-25 16:00","hum":"97","pop":"95","pres":"1006","tmp":"22","wind":{"deg":"135","dir":"东南风","sc":"微风","spd":"4"}},{"date":"2016-05-25 17:00","hum":"97","pop":"95","pres":"1005","tmp":"22","wind":{"deg":"113","dir":"东南风","sc":"微风","spd":"3"}},{"date":"2016-05-25 18:00","hum":"97","pop":"83","pres":"1005","tmp":"22","wind":{"deg":"92","dir":"东风","sc":"微风","spd":"3"}},{"date":"2016-05-25 19:00","hum":"98","pop":"71","pres":"1006","tmp":"21","wind":{"deg":"71","dir":"东北风","sc":"微风","spd":"3"}},{"date":"2016-05-25 20:00","hum":"98","pop":"58","pres":"1006","tmp":"20","wind":{"deg":"50","dir":"东北风","sc":"微风","spd":"3"}},{"date":"2016-05-25 21:00","hum":"98","pop":"57","pres":"1007","tmp":"19","wind":{"deg":"36","dir":"东北风","sc":"微风","spd":"4"}},{"date":"2016-05-25 22:00","hum":"98","pop":"56","pres":"1008","tmp":"18",

}