package com.guohui.weather.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by Dikaros on 2016/5/30.
 */
public class CustomHorzontalScrollView extends HorizontalScrollView {

    public CustomHorzontalScrollView(Context context) {
        super(context);
    }

    public CustomHorzontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomHorzontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        super.onTouchEvent(ev);
        return true;
    }
}
