package com.guohui.weather.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Dikaros on 2016/5/29.
 */
public class CustomSwipeLayout extends SwipeRefreshLayout {
    public CustomSwipeLayout(Context context) {
        super(context);
    }

    public CustomSwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    boolean refreshable = true;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    public boolean isRefreshable() {
        return refreshable;
    }

    public void setRefreshable(boolean refreshable) {

        this.refreshable = refreshable;
    }

}
