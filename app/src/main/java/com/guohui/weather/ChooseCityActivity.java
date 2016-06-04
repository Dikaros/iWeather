package com.guohui.weather;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.dikaros.asynet.AsyNet;
import com.dikaros.asynet.NormalAsyNet;
import com.dikaros.simplifyfindwidget.SimpifyUtil;
import com.dikaros.simplifyfindwidget.annotation.FindView;
import com.guohui.weather.bean.Weather;
import com.guohui.weather.util.AlertUtil;
import com.guohui.weather.util.Util;

import org.json.JSONArray;
import org.json.JSONException;

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

    List<String> list;

    //网络访问器
    NormalAsyNet weatherNetHelper;

    @FindView(R.id.pBar)
    ProgressBar pbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);
        initWindow();
        SimpifyUtil.findAllViews(this);
        pbar.setVisibility(View.GONE);
        list = new ArrayList<>();
        adapter = new ArrayAdapter(this, R.layout.simple_list_view_item, R.id.tv_item_name_1,list);
        lvCity.setAdapter(adapter);
        etCityArea.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_SEARCH) {
                    AlertUtil.toastMess(ChooseCityActivity.this, "点击了search");
                }
                return true;
            }
        });

        etCityArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() != 0) {
                    btnCancle.setText("搜索");
                } else {
                    btnCancle.setText("取消");
                }
            }
        });



        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btnCancle.getText().equals("搜索")) {
                    if (weatherNetHelper != null) {
                        weatherNetHelper.cancel(true);
                    }
                    finish();
                } else {
                    weatherNetHelper = new NormalAsyNet(Config.BAIDU_WEATHER_DETAIL_URL + etCityArea.getText().toString(), AsyNet.NetMethod.GET);
                    weatherNetHelper.addHeader("apikey", Config.BAIDU_API_KEY);
                    weatherNetHelper.setOnNetStateChangedListener(new AsyNet.OnNetStateChangedListener<String>() {
                        @Override
                        public void beforeAccessNet() {
                            pbar.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void afterAccessNet(String result) {
                            pbar.setVisibility(View.GONE);
                            if (result != null) {
                                if (result.startsWith("{")) {
                                    Weather currentWeather = new Weather(result);
                                    String cityName = currentWeather.getBasic().getCity();
                                    if (cityName!=null) {
                                        list.add(cityName);
//                                        adapter.add(cityName);
                                        adapter.notifyDataSetChanged();
                                    }
                                } else {
                                    AlertUtil.toastMess(ChooseCityActivity.this, "远程服务器异常");
                                }
                            }
                        }

                        @Override
                        public void whenException() {
                            pbar.setVisibility(View.GONE);

                        }

                        @Override
                        public void onProgress(Integer progress) {
                            pbar.setVisibility(View.GONE);

                        }
                    });

                    weatherNetHelper.execute();
                }
            }
        });

        lvCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String city = list.get(position);
                String fo = Util.getPreference(ChooseCityActivity.this,Config.KEY_REGISTED_WEATHER);
                if (fo==null) {
                    JSONArray array = new JSONArray();
                    array.put(city);
                    Util.setPreference(ChooseCityActivity.this, Config.KEY_REGISTED_WEATHER, array.toString());
                }else {
                    try {
                        JSONArray array = new JSONArray(fo);
                        for (int i = 0; i <array.length() ; i++) {
                            if (city.equals(array.getString(i))){
                                finish();
                                return;
                            }
                        }
                        array.put(city);
                        Util.setPreference(ChooseCityActivity.this, Config.KEY_REGISTED_WEATHER, array.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                Intent intent = new Intent(ChooseCityActivity.this,MainActivity.class);
                intent.putExtra("restart",true);
                intent.putExtra("city",city);
                startActivity(intent);
                finish();
            }
        });


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
