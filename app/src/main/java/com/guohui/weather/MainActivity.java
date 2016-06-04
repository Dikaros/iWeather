package com.guohui.weather;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import com.dikaros.simplifyfindwidget.SimpifyUtil;
import com.dikaros.simplifyfindwidget.annotation.FindView;
import com.guohui.weather.fragment.WeatherFragment;
import com.guohui.weather.util.Util;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

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

        viewPager.setCurrentItem(jumpPage,false);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        boolean restart = intent.getBooleanExtra("restart",false);
        if (restart){
            Log.i("restart","重启");
            fragments.clear();
            String cityJson = Util.getPreference(this,Config.KEY_REGISTED_WEATHER);
            if (cityJson==null){
                WeatherFragment changsha = WeatherFragment.getInstance(0,"长沙");
                fragments.add(changsha);
                fPagerAdapter.notifyDataSetChanged();

            }else {
                JSONArray array = null;
                try {
                    array = new JSONArray(cityJson);
                    for (int i=0;i<array.length();i++){
                        WeatherFragment f = WeatherFragment.getInstance(i,array.getString(i));
                        fragments.add(f);
                    }
                    fPagerAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
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
            WeatherFragment changsha = WeatherFragment.getInstance(0,"长沙");
            fragments.add(changsha);
        }else {
            JSONArray array = new JSONArray(cityJson);
            for (int i=0;i<array.length();i++){
                WeatherFragment f =WeatherFragment.getInstance(i,array.getString(i));
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
