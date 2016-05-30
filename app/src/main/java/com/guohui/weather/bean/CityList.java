package com.guohui.weather.bean;

import android.content.Context;

import com.google.gson.Gson;
import com.guohui.weather.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Dikaros on 2016/5/30.
 */
public class CityList {
    ArrayList<City> cities = new ArrayList<>();

    private CityList(Context c) {
        context = c;
        InputStream is = context.getResources().openRawResource(R.raw.city_code_mapping);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder b = new StringBuilder();
        String temp;
        try {
            while ((temp = reader.readLine()) != null) {
                b.append(temp);
            }
            JSONObject root = new JSONObject(b.toString());
            JSONArray array = root.getJSONArray("city_info");
            Gson g = new Gson();
            for (int i = 0; i < array.length(); i++) {
                JSONObject o = array.getJSONObject(i);
                City ct = g.fromJson(o.toString(), City.class);
                cities.add(ct);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public boolean contains(String name) {
        boolean result = false;

        return  false;
    }

    Context context;
    static CityList instance;

    public static CityList getInstance(Context c) {
        if (instance == null) {
            instance = new CityList(c);
        }
        return instance;
    }

}
