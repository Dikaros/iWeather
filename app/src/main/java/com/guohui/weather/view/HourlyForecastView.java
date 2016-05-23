package com.guohui.weather.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
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


    View view;

    public HourlyForecastView(Context context, String time, String temp){
        view= LayoutInflater.from(context).inflate(R.layout.hourly_forecast_item_view,null);
        SimpifyUtil.findAllViews(this,view);
        tvDay.setText(time);
        tvTemp.setText(temp);
    }

    public View getView() {
        return view;
    }
}
