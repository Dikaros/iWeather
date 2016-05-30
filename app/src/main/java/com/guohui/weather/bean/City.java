package com.guohui.weather.bean;

/**
 * Created by Dikaros on 2016/5/30.
 */
public class City {
    /*
     {
      "city": "临洮",
      "cnty": "中国",
      "id": "CN101160205",
      "lat": "35.220000",
      "lon": "103.520000",
      "prov": "甘肃"
    },
     */

    String city;
    String cnty;
    String id;
    String lat;
    String lon;
    String prov;


    @Override
    public String toString() {
        return "City{" +
                "city='" + city + '\'' +
                ", cnty='" + cnty + '\'' +
                ", id='" + id + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", prov='" + prov + '\'' +
                '}';
    }

    public String getCity() {
        return city;
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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
