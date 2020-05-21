package com.ttd.androidlearning.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;

import com.ttd.androidlearning.R;

/**
 * @author wt
 * @time 2020/5/21
 */
public class TouchTransferVG1 extends FrameLayout {
    private boolean isIntercept;
    private boolean isConsume;
    private Paint paint = new Paint();

    public TouchTransferVG1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setIntercept(boolean intercept) {
        isIntercept = intercept;
    }

    public void setConsume(boolean consume) {
        isConsume = consume;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(ResourcesCompat.getColor(getResources(), R.color.green_900, null));
        canvas.drawText("ViewGroup", 0, 0, paint);
        canvas.save();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isIntercept || super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(getContext(), "事件传递到了ViewGroup", Toast.LENGTH_SHORT).show();
        return isConsume || super.onTouchEvent(event);
    }
}
