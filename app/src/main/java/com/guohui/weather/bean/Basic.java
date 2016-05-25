package com.guohui.weather.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dikaros on 2016/5/25.
 * 基本情况
 */
public class Basic {

    /*json数据形式

     "basic": {
     "city": "长沙",
     "cnty": "中国",
     "id": "CN101250101",
     "lat": "28.197000",
     "lon": "112.967000",
     "update": {
     "loc": "2016-05-25 16:52",
     "utc": "2016-05-25 08:52"
     }
     }

     */

    public Basic(String jsonFile) throws JSONException {
            JSONObject root = new JSONObject(jsonFile);
            city = root.getString("city");
            cnty = root.getString("cnty");
            id = root.getString("id");
            lon = root.getString("lon");
            lat = root.getString("lat");
            update = new Update(root.getJSONObject("update").toString());
    }

    //城市名
    String city;
    //所属国家
    String cnty;
    //id
    String id;
    //经度
    String lon;
    //纬度
    String lat;

    //更新时间
    Update update;

    class Update{

        //loc 数据更新的当地时间
        String loc;
        //utc 数据更新的utc时间
        String utc;

        public Update(String jsonFile) throws JSONException {
            JSONObject root = new JSONObject(jsonFile);
            loc = root.getString("loc");
            utc = root.getString("utc");
        }

        public String getLoc() {
            return loc;
        }

        public void setLoc(String loc) {
            this.loc = loc;
        }

        public String getUtc() {
            return utc;
        }

        public void setUtc(String utc) {
            this.utc = utc;
        }
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }

    @Override
    public String toString() {
        return "Basic{" +
                "city='" + city + '\'' +
                ", cnty='" + cnty + '\'' +
                ", id='" + id + '\'' +
                ", lon='" + lon + '\'' +
                ", lat='" + lat + '\'' +
                ", update=" + update +
                '}';
    }
}
