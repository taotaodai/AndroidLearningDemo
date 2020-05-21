package com.ttd.androidlearning.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * @author wt
 * @time 2020/5/21
 */
public class TouchTransferView1 extends androidx.appcompat.widget.AppCompatTextView {
    private boolean isConsume;

    public TouchTransferView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setConsume(boolean consume) {
        isConsume = consume;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Toast.makeText(getContext(),"我被点击了",Toast.LENGTH_SHORT).show();
                break;
        }
        return isConsume || super.onTouchEvent(event);
    }
}
