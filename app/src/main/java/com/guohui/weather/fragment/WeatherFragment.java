package com.guohui.weather.fragment;


import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dikaros.asynet.AsyNet;
import com.dikaros.asynet.NormalAsyNet;
import com.dikaros.simplifyfindwidget.SimpifyUtil;
import com.dikaros.simplifyfindwidget.annotation.FindView;
import com.guohui.weather.Config;
import com.guohui.weather.R;
import com.guohui.weather.bean.CondIcons;
import com.guohui.weather.bean.DailyForecast;
import com.guohui.weather.bean.HourlyForecast;
import com.guohui.weather.bean.Weather;
import com.guohui.weather.util.AlertUtil;
import com.guohui.weather.util.Util;
import com.guohui.weather.view.CustomScrollView;
import com.guohui.weather.view.DailyForecastView;
import com.guohui.weather.view.HourlyForecastView;
import com.guohui.weather.view.InnerScrollView;
import com.guohui.weather.view.LineView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Handler;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment {


    //主滑动
    @FindView(R.id.sv_main)
    CustomScrollView svMain;

    //title布局
    @FindView(R.id.ll_city)
    LinearLayout llCityTitle;

    //当前温度
    @FindView(R.id.tv_current_temp)
    TextView tvCurrentTemp;

    //当前温度单位
    @FindView(R.id.tv_current_temp_unit)
    TextView tvCurrentTempUnit;

    //横向的滑动条，分时段显示当天天气
    @FindView(R.id.rl_weather_by_hour)
    RelativeLayout rlWeatherByHour;

    //今日天气信息布局
    @FindView(R.id.rl_today_temp_mess)
    RelativeLayout rlTodayTempMess;

    //未来天气预报信息
    @FindView(R.id.ll_weather_forcast)
    LinearLayout llWeatherForcast;

    //未来几个小时的天气信息（按小时）
    @FindView(R.id.ll_weather_hourly)
    LinearLayout llWeatherHourly;

    //小时的图片
    @FindView(R.id.iv_hourly_back)
    ImageView ivHourly;

    //空白补间
    View viewBlank;

    @FindView(R.id.sv_more)
    InnerScrollView svMore;


    View viewHourlyBlank;

    @FindView(R.id.rl_root)
    RelativeLayout rlRoot;

    //城市名
    @FindView(R.id.tv_city_name)
    TextView tvCityName;

    //城市状态
    @FindView(R.id.tv_city_stat)
    TextView tvCityStat;

    //今日星期
    @FindView(R.id.tv_date_day_of_week)
    TextView tvDateOfWeek;

    //最高温度
    @FindView(R.id.tv_today_temp_max)
    TextView tvTodayTmpMax;
    //最低温度
    @FindView(R.id.tv_today_temp_min)
    TextView tvTodayTmpMin;

    //今日提醒消息
    @FindView(R.id.tv_today_message)
    TextView tvTodayMessage;

    //日出
    @FindView(R.id.tv_detail_sr)
    TextView tvDetailSr;

    //日落
    @FindView(R.id.tv_detail_ss)
    TextView tvDetailSs;

    //降水率
    @FindView(R.id.tv_detail_pop)
    TextView tvDetailPop;

    //湿度
    @FindView(R.id.tv_detail_hum)
    TextView tvDetailHum;

    //风速
    @FindView(R.id.tv_detail_wind)
    TextView tvDetailWind;

    //体感温度
    @FindView(R.id.tv_detail_tmp)
    TextView tvDetailTmp;

    //降水量
    @FindView(R.id.tv_detail_pcpn)
    TextView tvDetailPcpn;

    //气压
    @FindView(R.id.tv_detail_pres)
    TextView tvDetailPres;

    //能见度
    @FindView(R.id.tv_detail_vis)
    TextView tvDetailVis;

    //空气质量
    @FindView(R.id.tv_detail_airq)
    TextView tvDetailAirq;


    @FindView(R.id.srl_main)
    SwipeRefreshLayout srlMain;

    @FindView(R.id.lineViewMax)
    LineView lineViewMax;



    //当前的天气情况
    Weather currentWeather;

    //网络访问器
    NormalAsyNet weatherNetHelper;


    //主ScrollView滑动的距离
    int mainScrollY;

    //正在刷新中
    boolean refreshing = false;


    public WeatherFragment() {
        // Required empty public constructor
    }

    //默认城市
    String city = "长沙";

    public void setCity(int index,String city) {
        this.city = city;
        this.index = index;
    }

    int index = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for getContext() fragment
        View v = inflater.inflate(R.layout.fragment_weather, container, false);
        SimpifyUtil.findAllViews(this,v);
        //为声明的view findId
        viewHourlyBlank = v.findViewById(R.id.view_blank_hourly);
        viewBlank = v.findViewById(R.id.view_blank);
        //初始化日期
        initDate();

        initSwipeLayout();
        //加载滑动布局
        initScrollView();

        //初始化标题
        initTitleLayout();

        //初始化天气预报
        initWeatherForecast();

        //初始化动画效果
//        initAnimation();

        Set<String> stringSet = Util.getPreferenceSet(getContext(),Config.KEY_REMEMBERED_WEATHER);
        if (stringSet!=null){
//            stringSet.iterator()
            while (stringSet.iterator().hasNext()){
                String w = stringSet.iterator().next();
                Weather wea = new Weather(w);
                if (wea.getBasic().getCity().equals(city)){
                    currentWeather = wea;
                    updateWeatherUi();
                    break;
                }
            }
        }

        updateWeatherFromNet();

        return v;
    }

    /**
     * 初始化刷新器
     */
    private void initSwipeLayout() {
        srlMain.setColorSchemeColors(Color.RED, Color.YELLOW, Color.CYAN, Color.GREEN);

        srlMain.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //access net
                if (!refreshing) {
                    updateWeatherFromNet();
                }else {
                    srlMain.setRefreshing(false);
                }
            }
        });

        srlMain.setEnabled(false);
    }


    /**
     * 访问天气
     */
    public void updateWeatherFromNet() {
        weatherNetHelper = new NormalAsyNet(Config.BAIDU_WEATHER_DETAIL_URL + city, AsyNet.NetMethod.GET);
        weatherNetHelper.addHeader("apikey", Config.BAIDU_API_KEY);
        weatherNetHelper.setOnNetStateChangedListener(new AsyNet.OnNetStateChangedListener<String>() {
            @Override
            public void beforeAccessNet() {
                refreshing = true;
            }

            @Override
            public void afterAccessNet(final String result) {
                srlMain.setRefreshing(false);
                refreshing = false;
//                Log.e("weather", result);
                if (result != null) {
                    if (result.startsWith("{")) {
                        new Thread(){
                            @Override
                            public void run() {
                                currentWeather = new Weather(result);
                                Config.currentCityMap.put(index,currentWeather);
//                        Log.e("hour",currentWeather.getHourly_forecast().toString());
                                Set<String> weatherSet = Util.getPreferenceSet(getContext(),Config.KEY_REMEMBERED_WEATHER);
                                if (weatherSet==null){
                                    weatherSet = new HashSet<String>();
                                    weatherSet.add(result);
                                }
                                Util.setPreferenceSet(getContext(),Config.KEY_REMEMBERED_WEATHER,weatherSet);
                                handler.sendEmptyMessage(1);
                            }
                        }.start();


                    } else {
                        AlertUtil.toastMess(getContext(), "远程服务器异常");
                    }


//                    updateWeatherUi();

                }
            }

            @Override
            public void whenException() {
                refreshing = false;
            }

            @Override
            public void onProgress(Integer progress) {

            }
        });

        weatherNetHelper.execute();
    }


     android.os.Handler handler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                updateWeatherUi();
            }
        }
    };
    private void initAnimation() {
        AnimationDrawable d = (AnimationDrawable) rlRoot.getBackground();
        d.start();
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

    /**
     * 更新天气UI
     */
    public void updateWeatherUi() {
        //设置天气预报
        initWeatherForecast();
        //设置城市名称
        tvCityName.setText(currentWeather.getBasic().getCity());
        //设置当前天气情况
        tvCityStat.setText(currentWeather.getDaily_forecast().get(0).getCond().getTxt_d());
        //设置当前温度
        tvCurrentTemp.setText(currentWeather.getHourly_forecast().get(0).getTmp());
        //设置今日星期
        tvDateOfWeek.setText(Util.getWeekDay(calendar.get(Calendar.DAY_OF_WEEK)));
        //设置今日最高温度
        tvTodayTmpMax.setText(currentWeather.getDaily_forecast().get(0).getTmp().getMax());
        //设置今日最低温度
        tvTodayTmpMin.setText(currentWeather.getDaily_forecast().get(0).getTmp().getMin());
        //设置今日提醒消息
        tvTodayMessage.setText("今日最高温度:" + tvTodayTmpMax.getText() + "今日最低温度:" + tvTodayTmpMin.getText() + "," + currentWeather.getSuggestion().getComf().getTxt());
        //日出
        tvDetailSr.setText(currentWeather.getDaily_forecast().get(0).getAstro().getSr());

        //日落
        tvDetailSs.setText(currentWeather.getDaily_forecast().get(0).getAstro().getSs());

        //降水率
        tvDetailPop.setText(currentWeather.getHourly_forecast().get(0).getPop() + "%");

        //湿度
        tvDetailHum.setText(currentWeather.getHourly_forecast().get(0).getHum() + "%");
        //风速
        tvDetailWind.setText(currentWeather.getHourly_forecast().get(0).getWind().toString());

        //体感温度
        tvDetailTmp.setText(currentWeather.getHourly_forecast().get(0).getTmp() + "°");

        //降水量
        tvDetailPcpn.setText(currentWeather.getDaily_forecast().get(0).getPcpn() + "厘米");

        //气压
        tvDetailPres.setText(currentWeather.getHourly_forecast().get(0).getPres() + "百帕");
        //能见度
        tvDetailVis.setText(currentWeather.getDaily_forecast().get(0).getVis() + "千米");

        //空气质量
        tvDetailAirq.setText(currentWeather.getAqi().getQlty());



    }


    private void initWeatherForecast() {

        llWeatherHourly.removeAllViews();
        llWeatherForcast.removeAllViews();

        if (currentWeather == null) {

            for (int i = 1; i <= 7; i++) {
                llWeatherForcast.addView(new DailyForecastView(getContext(), Util.getWeekDay(i), (20 + i) + "", (20 - i) + "").getView());
            }

            //增加小时播报
            for (int i = 0; i < 24; i++) {

//            llWeatherForcast.addView(new DailyForecastView(getContext(),"星期"+i,(20+i)+"",(20-i)+"").getView());
                if (i == 0) {
                    llWeatherHourly.addView(new HourlyForecastView(getContext(), "现在", 20 - i + "°").getView());

                } else {

                    llWeatherHourly.addView(new HourlyForecastView(getContext(), String.format("%02d:00", (calendar.get(Calendar.HOUR_OF_DAY) + i) % 24), 20 - i + "°").getView());
                }
            }
        } else {
            lineViewMax.clear();
            int maxPY = Util.dip2px(getContext(),60);
            for (int i = 1; i < currentWeather.getDaily_forecast().size(); i++) {
                DailyForecast f = currentWeather.getDaily_forecast().get(i);
                llWeatherForcast.addView(new DailyForecastView(getContext(), Util.getWeekDay((calendar.get(Calendar.DAY_OF_WEEK) + i) % 7), f.getTmp().getMax(), f.getTmp().getMin(), CondIcons.getIconDrawable(getContext(), currentWeather.getDaily_forecast().get(i).getCond().getCode_d())).getView());
            }

            int mX = getActivity().getWindowManager().getDefaultDisplay().getWidth()/24;
            int mY =0;

            int max = -100;
            int min = 100;
            for (int i = 0; i < currentWeather.getHourly_forecast().size(); i++) {
                HourlyForecast h = currentWeather.getHourly_forecast().get(i);
//                lineViewMax.setLinePoint(Util.dip2px(getContext(),50)*(i-1), (int) (maxPY- maxPY/40f*Integer.parseInt(h.getTmp())));
                int tmp = Integer.parseInt(h.getTmp());
                if (tmp>max){
                    max=tmp;
                }

                if (tmp<min){
                    min = tmp;
                }
                HourlyForecastView v = null;
                if (i == 0) {
                    v = new HourlyForecastView(getContext(),"现在",h.getTmp());
                }else {
                    v = new HourlyForecastView(getContext(), h.getDate().substring(h.getDate().length() - 5, h.getDate().length()),h.getTmp());
                }
                v.setImage(CondIcons.getIconDrawable(getContext(), currentWeather.getDaily_forecast().get(0).getCond().getCode_d()));
                llWeatherHourly.addView(v.getView());
            }

            //最大温度和最小温度之间的差值
            int offset = max-min;
            for (int index=0; index<currentWeather.getHourly_forecast().size(); index++)
            {
                HourlyForecast h = currentWeather.getHourly_forecast().get(index);
                int l = Integer.parseInt(h.getTmp());
//                Log.d(index+"温度",l+"--"+min+"--"+max+"/"+(l-min)*maxPY/offset);
                //max =31 min = 20  12

                lineViewMax.setLinePoint(mX*(index-1),(maxPY- (l-min)*maxPY/offset));
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

    float startHsvY = -1;


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



    /**
     * 加载滑动布局
     */
    private void initScrollView() {
        svMain.setIntercept(true);
        svMain.setInnerScrollView(svMore);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(0, Util.sp2px(getContext(), 18) + Util.dip2px(getContext(), 140 + 96), 0, 0);
        rlWeatherByHour.setLayoutParams(layoutParams);

        svMain.setScrollChangedCallback(new CustomScrollView.OnScrollChangedCallback() {
            @Override
            public void onScrollChanged(CustomScrollView scrollView, int x, int y, int oldX, int oldY) {
                if (scrollView.getScrollY()==0){
                    srlMain.setEnabled(true);
                }else {
                    srlMain.setEnabled(false);
                }
                ((RelativeLayout.LayoutParams) rlWeatherByHour.getLayoutParams()).setMargins(0, 0, 0, 0);
//                svMore.
                if (startHsvY == -1) {
                    startHsvY = viewHourlyBlank.getY();
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
                        llCityTitle.setPadding(0, (int) (Util.dip2px(getContext(), 15) + (Util.dip2px(getContext(), 10)) * alpha), 0, 0);
                    }
                    //向上滑
                    else {
                        rlTodayTempMess.setAlpha(alpha);
                        tvCurrentTemp.setAlpha(alpha);
                        tvCurrentTempUnit.setAlpha(alpha);
                        llCityTitle.setPadding(0, (int) (Util.dip2px(getContext(), 15) + (Util.dip2px(getContext(), 10)) * alpha), 0, 0);


                    }
                } else {
                    rlTodayTempMess.setAlpha(0);
                    tvCurrentTemp.setAlpha(0);
                    tvCurrentTempUnit.setAlpha(0);
                    llCityTitle.setPadding(0, Util.dip2px(getContext(), 15), 0, 0);

                }

                /*
                当水平栏滑动到主滑动栏位置的时候
                停止其随着主滑动栏的滑动而滑动
                 */
                viewHourlyBlank.getLocationOnScreen(location);


                int left = location[0];
                int top = location[1];


                if (mainScrollY > startHsvY) {
                    viewHourlyBlank.setY(mainScrollY);
                    if (!seted) {
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getActivity().getWindowManager().getDefaultDisplay().getHeight() - top - rlWeatherByHour.getHeight()-Util.dip2px(getContext(),44));
                        svMore.setLayoutParams(params);
                        seted = true;
                        svMain.setIntercept(false);
                        svMore.setY(startHsvY + viewHourlyBlank.getHeight());
                    }
                } else {
                    ivHourly.setImageBitmap(null);
                    viewBlank.setVisibility(View.VISIBLE);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getActivity().getWindowManager().getDefaultDisplay().getHeight() - top);
                    svMore.setLayoutParams(params);
                    seted = false;
                    svMain.setIntercept(true);
                }

                if (mainScrollY == 0) {
                    viewHourlyBlank.setY(startHsvY);
                }
//                    rlWeatherByHour.setY(absoluteMainScrollY);
//                rlWeatherByHour.scrollTo(0,100);
//                rlWeatherByHour.setY(100);
//                setImageRegion(rlWeatherByHour,left,top);

                viewHourlyBlank.getLocationOnScreen(location);


                int new2 = location[1];

                rlWeatherByHour.setY(new2);

                lastMainScrollY = mainScrollY;

            }
        });
    }

    boolean seted = false;




}
