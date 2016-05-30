package com.guohui.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.dikaros.simplifyfindwidget.SimpifyUtil;
import com.dikaros.simplifyfindwidget.annotation.FindView;

public class CityActivity extends AppCompatActivity {

    @FindView(R.id.ll_regiseted_city_list)
    LinearLayout llCityList;

    @FindView(R.id.ibtn_choose_city)
    ImageButton ibtnChooseCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        SimpifyUtil.findAllViews(this);
        ibtnChooseCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CityActivity.this,ChooseCityActivity.class);
                startActivity(intent);
            }
        });
    }

}
