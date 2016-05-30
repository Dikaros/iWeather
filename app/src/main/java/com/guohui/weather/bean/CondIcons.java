package com.guohui.weather.bean;

import android.content.Context;

import com.guohui.weather.R;
import com.guohui.weather.util.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by Dikaros on 2016/5/27.
 * 天气的图标
 */
public class CondIcons {

    Context context;

    static CondIcons instance;

    private CondIcons(Context context) throws Exception {
        InputStream is = context.getResources().openRawResource(R.raw.weather_icon_mapping);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String temp = null;
        while ((temp =reader.readLine())!=null){
            builder.append(temp);
        }
        JSONObject root = new JSONObject(builder.toString());
        JSONArray array = root.getJSONArray("cond_info");

        for (int i = 0; i <array.length() ; i++) {
            JSONObject codeIcon = array.getJSONObject(i);
            iconMap.put(codeIcon.getString("code"), Util.getDrawableByName(codeIcon.getString("icon")));
        }
    }

    HashMap<String,Integer> iconMap = new HashMap<>();

    public HashMap<String, Integer> getIconMap() {
        return iconMap;
    }

    /**
     * 获取ICondDrawable 的id
     * @param context
     * @param weatherCode
     * @return
     */
    public static int getIconDrawable(Context context, String weatherCode) {
         if (instance==null){
             try {
                 instance = new CondIcons(context);
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }


        return instance.getIconMap().get(weatherCode);
    }


}
