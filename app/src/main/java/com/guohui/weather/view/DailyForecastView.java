package com.guohui.weather.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dikaros.simplifyfindwidget.SimpifyUtil;
import com.dikaros.simplifyfindwidget.annotation.FindView;
import com.guohui.weather.R;

/**
 * Created by Dikaros on 2016/5/22.
 */
public class DailyForecastView {

    @FindView(R.id.tv_item_day)
    TextView tvDay;

    @FindView(R.id.tv_item_temp_max)
    TextView tvTempMax;

    @FindView(R.id.tv_item_temp_min)
    TextView tvTempMin;

    @FindView(R.id.iv_item_image)
    ImageView ivItemImage;

    View view;

    public DailyForecastView(Context context, String day, String max, String min,int resourceId){
        view= LayoutInflater.from(context).inflate(R.layout.brodcast_item_view,null);
        SimpifyUtil.findAllViews(this,view);
        tvDay.setText(day);
        tvTempMax.setText(max);
        tvTempMin.setText(min);
        ivItemImage.setImageResource(resourceId);
    }
    public DailyForecastView(Context context, String day, String max, String min){
        view= LayoutInflater.from(context).inflate(R.layout.brodcast_item_view,null);
        SimpifyUtil.findAllViews(this,view);
        tvDay.setText(day);
        tvTempMax.setText(max);
        tvTempMin.setText(min);
    }

    public View getView() {
        return view;
    }
}
