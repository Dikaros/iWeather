package com.guohui.weather.util;

import android.content.Context;

import com.guohui.weather.R;

import java.lang.reflect.Field;

/**
 * Created by Dikaros on 2016/5/22.
 */
public class Util {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        //px/sp =
        return (int) ((spValue - 0.5f) * fontScale);
    }


    /**
     * 通过drawable的名字获取其id（通过反射）
     * @param name drawable名
     * @return id
     *
     */
    public static int getDrawableByName(String name){
        int id = -1;
        Class drawable = R.drawable.class;
        Field field = null;
        try{
            field = drawable.getField(name);
            id = field.getInt(field.getName());
        }catch (Exception e){
            e.printStackTrace();
        }

        return  id;
    }

    /**
     * 获取星期数
     * @param dayOfWeek
     * @return
     */
    public static String getWeekDay(int dayOfWeek){
        switch (dayOfWeek){
            case 0:
                return "星期日";
            case 1:
                return "星期一";
            case 2:
                return "星期二";
            case 3:
                return "星期三";
            case 4:
                return "星期四";
            case 5:
                return "星期五";
            case 6:
                return "星期六";
        }
        return null;
    }
}
