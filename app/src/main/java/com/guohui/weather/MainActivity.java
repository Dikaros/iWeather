package com.guohui.weather;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dikaros.asynet.AsyNet;
import com.dikaros.asynet.NormalAsyNet;
import com.dikaros.simplifyfindwidget.SimpifyUtil;
import com.dikaros.simplifyfindwidget.annotation.FindView;
import com.guohui.weather.bean.City;
import com.guohui.weather.bean.CondIcons;
import com.guohui.weather.bean.DailyForecast;
import com.guohui.weather.bean.HourlyForecast;
import com.guohui.weather.bean.Weather;
import com.guohui.weather.fragment.WeatherFragment;
import com.guohui.weather.util.AlertUtil;
import com.guohui.weather.util.Util;
import com.guohui.weather.view.CustomScrollView;
import com.guohui.weather.view.DailyForecastView;
import com.guohui.weather.view.HourlyForecastView;
import com.guohui.weather.view.InnerScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    @FindView(R.id.ibtn_show_city_list)
    ImageButton ibtnChange;

    @FindView(R.id.vp_main)
    ViewPager viewPager;

    ArrayList<Fragment> fragments;

    FragmentPagerAdapter fPagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWindow();
        SimpifyUtil.findAllViews(this);
        ibtnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CityActivity.class);
                startActivity(intent);
            }
        });
        try {
            initViewPager();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        int jumpPage = getIntent().getIntExtra("city",0);
        boolean restart = getIntent().getBooleanExtra("restart",false);
        if (restart){
            fragments.clear();
            String cityJson = Util.getPreference(this,Config.KEY_REGISTED_WEATHER);
            if (cityJson==null){
                WeatherFragment changsha = new WeatherFragment();
                changsha.setCity(0,"changsha");
                fragments.add(changsha);
            }else {
                JSONArray array = null;
                try {
                    array = new JSONArray(cityJson);
                    for (int i=0;i<array.length();i++){
                        WeatherFragment f = new WeatherFragment();
                        f.setCity(i,array.getString(i));
                        fragments.add(f);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
        viewPager.setCurrentItem(jumpPage,false);
    }

    private void initViewPager() throws JSONException {
        fragments = new ArrayList<>();
        fPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };

        String cityJson = Util.getPreference(this,Config.KEY_REGISTED_WEATHER);
        Log.e("cityJson",cityJson+"");
        if (cityJson==null){
            WeatherFragment changsha = new WeatherFragment();
            changsha.setCity(0,"changsha");
            fragments.add(changsha);
        }else {
            JSONArray array = new JSONArray(cityJson);
            for (int i=0;i<array.length();i++){
                WeatherFragment f = new WeatherFragment();
                f.setCity(i,array.getString(i));
                fragments.add(f);
            }
        }


        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setAdapter(fPagerAdapter);
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
