package com.guohui.weather.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dikaros.simplifyfindwidget.SimpifyUtil;
import com.dikaros.simplifyfindwidget.annotation.FindView;
import com.guohui.weather.R;

/**
 * Created by Dikaros on 2016/5/22.
 */
public class HourlyForecastView {

    @FindView(R.id.tv_hour_item_hour)
    TextView tvDay;

    @FindView(R.id.tv_hour_item_temp)
    TextView tvTemp;

    @FindView(R.id.iv_hour_item_image)
    ImageView ivDay;


    View view;

    public HourlyForecastView(Context context, String time, String temp,int resourceId){
        this(context,time,temp);
        ivDay.setImageResource(resourceId);
    }
    public HourlyForecastView(Context context, String time, String temp){
        view= LayoutInflater.from(context).inflate(R.layout.hourly_forecast_item_view,null);
        SimpifyUtil.findAllViews(this,view);
        tvDay.setText(time);
        tvTemp.setText(temp);
    }

    public void setImage(int resourceId){
        ivDay.setImageResource(resourceId);
    }


    public View getView() {
        return view;
    }
}
