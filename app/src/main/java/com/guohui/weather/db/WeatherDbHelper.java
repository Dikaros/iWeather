package com.guohui.weather.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dikaros on 2016/6/1.
 */
public class WeatherDbHelper extends SQLiteOpenHelper {

    public static final String CREATE_TABLE="CREATE TABLE weather (_id INTEGER PRIMARY KEY AUTOINCREMENT, city TEXT UNIQUE, weather_msg TEXT)";

    public WeatherDbHelper(Context context) {
        super(context, "weather.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
