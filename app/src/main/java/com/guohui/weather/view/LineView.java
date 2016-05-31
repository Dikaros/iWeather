package com.guohui.weather.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dikaros on 2016/5/31.
 */
public class LineView extends View {
    private final static String X_KEY = "Xpos";
    private final static String Y_KEY = "Ypos";

    private List<Map<String, Integer>> mListPoint = new ArrayList<Map<String, Integer>>();

    Paint mPaint = new Paint();

    public LineView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public LineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LineView(Context context) {
        super(context);
    }

    public void clear()
    {
        mListPoint.clear();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);

        for (int index = 0; index < mListPoint.size()-1; index++) {
            if (index > 0) {
                canvas.drawLine(mListPoint.get(index - 1).get(X_KEY),
                        mListPoint.get(index - 1).get(Y_KEY),
                        mListPoint.get(index).get(X_KEY), mListPoint.get(index)
                                .get(Y_KEY), mPaint);
                canvas.setDrawFilter(new PaintFlagsDrawFilter(0,
                        Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
            }
        }
    }

    /**
     * @param curX
     *            which x position you want to draw.
     * @param curY
     *            which y position you want to draw.
     */
    public void setLinePoint(int curX, int curY) {
        Map<String, Integer> temp = new HashMap<String, Integer>();
        temp.put(X_KEY, curX);
        temp.put(Y_KEY, curY);
        mListPoint.add(temp);
        invalidate();
    }


}
