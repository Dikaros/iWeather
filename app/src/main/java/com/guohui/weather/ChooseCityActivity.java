package com.guohui.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.dikaros.asynet.NormalAsyNet;
import com.dikaros.simplifyfindwidget.SimpifyUtil;
import com.dikaros.simplifyfindwidget.annotation.FindView;
import com.guohui.weather.util.AlertUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChooseCityActivity extends AppCompatActivity {

    @FindView(R.id.et_city)
    EditText etCityArea;

    @FindView(R.id.btn_choose_cancle)
    Button btnCancle;

    @FindView(R.id.lv_city_result)
    ListView lvCity;


    ArrayAdapter adapter;

    //网络访问器
    NormalAsyNet asyNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);
        SimpifyUtil.findAllViews(this);
        adapter = new ArrayAdapter(this,R.layout.simple_list_view_item,R.id.tv_item_name_1);
        lvCity.setAdapter(adapter);
        etCityArea.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event.getKeyCode()==KeyEvent.KEYCODE_SEARCH){
                    AlertUtil.toastMess(ChooseCityActivity.this,"点击了search");
                }
                return true;
            }
        });

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (asyNet!=null){
                    asyNet.cancel(true);
                }
                finish();
            }
        });
    }
}
