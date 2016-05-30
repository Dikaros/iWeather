package com.guohui.weather.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dikaros.simplifyfindwidget.SimpifyUtil;
import com.dikaros.simplifyfindwidget.annotation.FindView;
import com.guohui.weather.R;

/**
 * Created by Dikaros on 2016/5/30.
 */
public class CitySimpleView {

    @FindView(R.id.tv_item_update_time)
    TextView tvUpdateTime;

    @FindView(R.id.tv_item_city)
    TextView tvCityName;

    @FindView(R.id.tv_city_item_tmp)
    TextView tvCurrentTmp;

    View view;

    public CitySimpleView(Context context, String city, String updateTime, String tmp) {
        view = LayoutInflater.from(context).inflate(R.layout.simple_city_item_view, null);
        SimpifyUtil.findAllViews(this, view);
        tvCityName.setText(city);
        tvCurrentTmp.setText(tmp);
        tvUpdateTime.setText(updateTime);

    }

    public View getView() {
        return view;
    }
}
