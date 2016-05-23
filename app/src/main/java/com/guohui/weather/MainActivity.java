package com.guohui.weather;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dikaros.simplifyfindwidget.SimpifyUtil;
import com.dikaros.simplifyfindwidget.annotation.FindView;
import com.guohui.weather.view.CustomScrollView;
import com.guohui.weather.view.DailyForecastView;
import com.guohui.weather.view.HourlyForecastView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //主滑动
    @FindView(R.id.sv_main)
    CustomScrollView svMain;

    @FindView(R.id.ll_city)
    LinearLayout llCityTitle;

    @FindView(R.id.tv_current_temp)
    TextView tvCurrentTemp;

    @FindView(R.id.tv_current_temp_unit)
    TextView tvCurrentTempUnit;

    //横向的滑动条，分时段显示当天天气
    @FindView(R.id.hsv_weather_by_hour)
    HorizontalScrollView hsvWeatherByHour;

    @FindView(R.id.rl_today_temp_mess)
    RelativeLayout rlTodayTempMess;

    @FindView(R.id.ll_weather_forcast)
    LinearLayout llWeatherForcast;

    @FindView(R.id.ll_weather_hourly)
    LinearLayout llWeatherHourly;

    //主ScrollView滑动的距离
    int mainScrollY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWindow();
        //为声明的view findId
        SimpifyUtil.findAllViews(this);

        initDate();
        //加载滑动布局
        initScrollView();

        initTitleLayout();

        initWeatherForcast();

    }

    Calendar calendar;
    Date currentTime;

    /**
     * 初始化日期信息
     */
    private void initDate() {
        currentTime = new Date();
        calendar = Calendar.getInstance();
        calendar.setTime(currentTime);
    }

    private void initWeatherForcast() {

        for (int i=1;i<=7;i++){
            llWeatherForcast.addView(new DailyForecastView(this,"星期"+i,(20+i)+"",(20-i)+"").getView());
        }

        //增加小时播报
        for (int i=0;i<24;i++){

//            llWeatherForcast.addView(new DailyForecastView(this,"星期"+i,(20+i)+"",(20-i)+"").getView());
            if (i==0){
                llWeatherHourly.addView(new HourlyForecastView(this,"现在",20-i+"°").getView());

            }else {

                llWeatherHourly.addView(new HourlyForecastView(this, String.format("%02d:00", (calendar.get(Calendar.HOUR_OF_DAY) + i) % 24), 20 - i + "°").getView());
            }
        }
    }

    /**
     * 加载title的layout
     */
    private void initTitleLayout() {
    }


    //当前温度的位置
    int currentTempY;

    //今日简要信息的当前位置
    int currentRlMessY;

    //今日简要信息的开始位置
    int startRlMessY;

    //主scroll的绝对位置
    float absoluteMainScrollY;

    float startHsvY=-1;


    /**
     * 初始化控件的位置信息
     */
    private void initLocation() {
        int[] location = new int[2];
        tvCurrentTemp.getLocationOnScreen(location);
        currentTempY = location[1];
        rlTodayTempMess.getLocationOnScreen(location);
        startRlMessY = location[1];
    }

    int lastMainScrollY;

    @Override
    protected void onResume() {
        super.onResume();
        initLocation();

    }


    /**
     * 加载滑动布局
     */
    private void initScrollView() {
        svMain.setScrollChangedCallback(new CustomScrollView.OnScrollChangedCallback() {
            @Override
            public void onScrollChanged(CustomScrollView scrollView, int x, int y, int oldX, int oldY) {
                if (startHsvY==-1){
                    startHsvY = hsvWeatherByHour.getY();
                }
                absoluteMainScrollY = scrollView.getY();
                mainScrollY = scrollView.getScrollY();
                //获取rlTodayTempMess的绝对位置
                int[] location = new int[2];
                rlTodayTempMess.getLocationOnScreen(location);
                currentRlMessY = location[1];

                //简要信息栏与实时温度栏的初始距离
                int x1 = startRlMessY - currentTempY - tvCurrentTemp.getHeight();

                //如果currentTempY+其高度>currentRlMessY则显示，否则隐藏
                if ((currentTempY + tvCurrentTemp.getHeight()) < currentRlMessY) {

                    float alpha = 1 + (float) mainScrollY / x1;
                    //向下滑
                    if (lastMainScrollY > mainScrollY) {

                        rlTodayTempMess.setAlpha(alpha);
                        tvCurrentTemp.setAlpha(alpha);
                        tvCurrentTempUnit.setAlpha(alpha);
                    }
                    //向上滑
                    else {
                        rlTodayTempMess.setAlpha(alpha);
                        tvCurrentTemp.setAlpha(alpha);
                        tvCurrentTempUnit.setAlpha(alpha);

                    }
                } else {
                    rlTodayTempMess.setAlpha(0);
                    tvCurrentTemp.setAlpha(0);
                    tvCurrentTempUnit.setAlpha(0);
                }

                /*
                当水平栏滑动到主滑动栏位置的时候
                停止其随着主滑动栏的滑动而滑动
                 */
//                hsvWeatherByHour.getLocationOnScreen(location);
//                int hsv1= location[1];
                if (mainScrollY>startHsvY){
                    hsvWeatherByHour.setY(mainScrollY);
                }

                if (mainScrollY==0){
                    hsvWeatherByHour.setY(startHsvY);
                }
//                    hsvWeatherByHour.setY(absoluteMainScrollY);
//                hsvWeatherByHour.scrollTo(0,100);
//                hsvWeatherByHour.setY(100);
                lastMainScrollY = mainScrollY;

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
