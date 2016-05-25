package com.guohui.weather.bean;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dikaros on 2016/5/25.
 */
public class Weather {

    /**
     * Weather api 的版本
     */
    public static final String WEATHER_API_VERSION = "HeWeather data service 3.0";

    //空气质量
    Aqi aqi;
    //基本信息
    Basic basic;
    //天气预报日
    ArrayList<DailyForecast> daily_forecast = new ArrayList<>();
    //天气预报时
    ArrayList<HourlyForecast> hourly_forecast = new ArrayList<>();
    //建议
    Suggestion suggestion;

    public Weather(String jsonFile){
        try {
            Gson gson = new Gson();
            //root
            JSONObject root = new JSONObject(jsonFile);
            //天气的所有信息
            JSONArray rootArray = root.getJSONArray(WEATHER_API_VERSION);
            JSONObject weatherJson = rootArray.getJSONObject(0);
            //实例化aqi,并使用Gson处理json
//            aqi = new Aqi(weatherJson.getJSONObject("aqi").toString());
            aqi = gson.fromJson(weatherJson.getJSONObject("aqi").getJSONObject("city").toString(),Aqi.class);
            //实例化basic,并将其json交给自身处理
            basic = new Basic(weatherJson.getJSONObject("basic").toString());
            //实例化suggestion,并将其json交给自身处理
            suggestion = new Suggestion(weatherJson.getJSONObject("suggestion").toString());

            JSONArray dailyArray = weatherJson.getJSONArray("daily_forecast");
            for (int i = 0; i <dailyArray.length() ; i++) {
                JSONObject df = dailyArray.getJSONObject(i);
                //实例化DailyForecast并交给自身处理
                DailyForecast dailyForecast = new DailyForecast(df.toString());
                //添加到集合中
                daily_forecast.add(dailyForecast);
            }
            JSONArray hourlyArray = weatherJson.getJSONArray("hourly_forecast");
            for(int i=0;i<hourlyArray.length();i++){
                JSONObject hf = hourlyArray.getJSONObject(i);
                //实例化HourlyForecast并交给自身处理
                HourlyForecast hourlyForecast = new HourlyForecast(hf.toString());
                //添加到集合中

                hourly_forecast.add(hourlyForecast);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Aqi getAqi() {
        return aqi;
    }

    public void setAqi(Aqi aqi) {
        this.aqi = aqi;
    }

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public ArrayList<DailyForecast> getDaily_forecast() {
        return daily_forecast;
    }

    public void setDaily_forecast(ArrayList<DailyForecast> daily_forecast) {
        this.daily_forecast = daily_forecast;
    }

    public ArrayList<HourlyForecast> getHourly_forecast() {
        return hourly_forecast;
    }

    public void setHourly_forecast(ArrayList<HourlyForecast> hourly_forecast) {
        this.hourly_forecast = hourly_forecast;
    }

    public Suggestion getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "aqi=" + aqi +
                ", basic=" + basic +
                ", daily_forecast=" + daily_forecast +
                ", hourly_forecast=" + hourly_forecast +
                ", suggestion=" + suggestion +
                '}';
    }
}
