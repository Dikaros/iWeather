package com.guohui.weather.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.guohui.weather.db.WeatherDbHelper;

/**
 * Created by Dikaros on 2016/6/1.
 */
public class DbUtil {
    private SQLiteDatabase db;

    private static DbUtil instance;

    private DbUtil(Context context) {
        WeatherDbHelper dbHelper = new WeatherDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean addCityWeather(String city, String weather) {
        boolean result = true;
        if (!contains(city)) {
            ContentValues cv = new ContentValues();
            cv.put("city", city);
            cv.put("weather_msg", weather);
            result = db.insert("weather", null, cv) != -1 ? true : false;
        }
        return result;
    }

    public boolean removeCityWeather(String city) {

        return db.delete("weather", "city=?", new String[]{city}) != 0 ? true : false;
    }

    public boolean updateCityWeather(String city,String message) {
        //修改SQL语句
        String sql = "update weather set weather_msg = "+message+" where city = "+city;
        db.execSQL(sql);
        return true;
    }

    public boolean contains(String city) {
        boolean result = false;
        Cursor cursor = db.query("weather", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            String c = cursor.getString(cursor.getColumnIndex("city"));
            if (c.equals(city)) {
                result = true;
            }
        }
        return result;
    }

    public String getCityWeather(String city) {
        String result = null;
        Cursor cursor = db.query("weather", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            String c = cursor.getString(cursor.getColumnIndex("city"));
            if (c.equals(city)) {
                result = cursor.getString(cursor.getColumnIndex("weather_msg"));
            }
        }

        return result;
    }


    public static DbUtil getInstance(Context context) {
        if (instance == null) {
            instance = new DbUtil(context);
        }
        return instance;
    }
}
