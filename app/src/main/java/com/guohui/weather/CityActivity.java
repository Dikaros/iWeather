package com.guohui.weather;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.dikaros.simplifyfindwidget.SimpifyUtil;
import com.dikaros.simplifyfindwidget.annotation.FindView;
import com.guohui.weather.bean.Weather;
import com.guohui.weather.view.CitySimpleView;

public class CityActivity extends AppCompatActivity {

    @FindView(R.id.ll_regiseted_city_list)
    LinearLayout llCityList;

    @FindView(R.id.ibtn_choose_city)
    ImageButton ibtnChooseCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        initWindow();
        SimpifyUtil.findAllViews(this);
        ibtnChooseCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CityActivity.this,ChooseCityActivity.class);
                startActivity(intent);
            }
        });

        for (int i:Config.currentCityMap.keySet()){
            Weather weather = Config.currentCityMap.get(i);
            String time = weather.getHourly_forecast().get(0).getDate();
            CitySimpleView view = new CitySimpleView(this,i,weather.getBasic().getCity(),time.substring(time.length()-5,time.length()),weather.getHourly_forecast().get(0).getTmp()+"°");
            llCityList.addView(view.getView());
            view.setOnViewClickListener(new CitySimpleView.OnViewClickListener() {
                @Override
                public void onViewClicked(View v,int index) {
                    Intent intent = new Intent(CityActivity.this, MainActivity.class);
                    intent.putExtra("city",index);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onViewLongClicked(View v, int index) {
                }
            });

        }
    }

    /**
     * 加载window，以实现沉浸式状态栏
     */
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

}
