package com.guohui.weather.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dikaros.simplifyfindwidget.SimpifyUtil;
import com.dikaros.simplifyfindwidget.annotation.FindView;
import com.guohui.weather.MainActivity;
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

    int index;

    public interface OnViewClickListener{
        public void onViewClicked(View v,int index);
        public void onViewLongClicked(View v,int index);
    }

    OnViewClickListener onViewClickListener;

    public void setOnViewClickListener(OnViewClickListener onViewClickListener) {
        this.onViewClickListener = onViewClickListener;
    }

    public CitySimpleView(final Context context, final int index, String city, String updateTime, String tmp) {
        view = LayoutInflater.from(context).inflate(R.layout.simple_city_item_view, null);
        SimpifyUtil.findAllViews(this, view);
        tvCityName.setText(city);
        tvCurrentTmp.setText(tmp);
        tvUpdateTime.setText(updateTime);
        this.index = index;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onViewClickListener!=null){
                    onViewClickListener.onViewClicked(v,index);
                }

            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onViewClickListener!=null){
                    onViewClickListener.onViewLongClicked(v,index);
                }
                return false;
            }
        });
    }

    public View getView() {
        return view;
    }
}
