package com.guohui.weather.view;

import android.content.Context;
import android.content.pm.LauncherApps;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Dikaros on 2016/5/22.
 * 由于ScrollView本身没有向外界提供滑动改变的回调，所以继承这个类并设置对外的回调方法
 */
public class CustomScrollView extends ScrollView {
    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //回调接口
    OnScrollChangedCallback scrollChangedCallback;


    /**
     * 设置回调对象
     *
     * @param scrollChangedCallback 回调对象
     */
    public void setScrollChangedCallback(OnScrollChangedCallback scrollChangedCallback) {
        this.scrollChangedCallback = scrollChangedCallback;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        //如果callback不为空则调用其中的方法
        if (scrollChangedCallback != null) {
            scrollChangedCallback.onScrollChanged(this, l, t, oldl, oldt);
        }

    }

    /**
     * 当scrollView改变时使用
     */
    public interface OnScrollChangedCallback {
        public void onScrollChanged(CustomScrollView scrollView, int x, int y, int oldX, int oldY);
    }

    boolean intercept = false;


    public void setIntercept(boolean intercept) {
        this.intercept = intercept;
    }

    float lastX;
    float lastY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (intercept) {

            return intercept;
        }

        return super.onInterceptTouchEvent(ev);
    }


}
