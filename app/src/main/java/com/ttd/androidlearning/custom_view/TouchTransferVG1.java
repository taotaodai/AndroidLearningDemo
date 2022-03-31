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

    /**
     * 事件分发
     * 当有监听到事件时，首先由Activity进行捕获，进入事件分发处理流程。（因为activity没有事件拦截，View和ViewGroup有）会将事件传递给最外层View的dispatchTouchEvent(MotionEvent ev)方法，该方法对事件进行分发
     * return true ：表示该View内部消化掉了所有事件。
     * return false ：事件在本层不再继续进行分发，并交由上层控件的onTouchEvent方法进行消费（如果本层控件已经是Activity，那么事件将被系统消费或处理）。　
     * 如果事件分发返回系统默认的 super.dispatchTouchEvent(ev)，事件将分发给本层的事件拦截onInterceptTouchEvent 方法进行处理
     * 总结：dispatchTouchEvent无论返回true还是false，事件都不再进行分发，只有当其返回super.dispatchTouchEvent(ev)，才表明其具有向下层分发的愿望，但是是否能够分发成功，则需要经过事件拦截onInterceptTouchEvent的审核。
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 事件拦截
     * return true ：表示将事件进行拦截，并将拦截到的事件交由本层控件 的 onTouchEvent 进行处理；
     * return false ：则表示不对事件进行拦截，事件得以成功分发到子View。并由子View的dispatchTouchEvent进行处理。　
     * 如果返回super.onInterceptTouchEvent(ev)，默认表示拦截该事件，并将事件传递给当前View的onTouchEvent方法，和return true一样。
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isIntercept || super.onInterceptTouchEvent(ev);
    }

    /**
     * 事件响应
     * 在dispatchTouchEvent（事件分发）返回super.dispatchTouchEvent(ev)并且onInterceptTouchEvent（事件拦截返回true或super.onInterceptTouchEvent(ev)的情况下，那么事件会传递到onTouchEvent方法，该方法对事件进行响应。
     * 如果return true，表示onTouchEvent处理完事件后消费了此次事件。此时事件终结；
     * 如果return false，则表示不响应事件，那么该事件将会不断向上层View的onTouchEvent方法传递，直到某个View的onTouchEvent方法返回true，如果到了最顶层View还是返回false，那么认为该事件不消耗，则在同一个事件系列中，当前View无法再次接收到事件，该事件会交由Activity的onTouchEvent进行处理；　　
     * 如果return super.dispatchTouchEvent(ev)，则表示不响应事件，结果与return false一样。
     * 总结：事件是否向上传递处理是由onTouchEvent的返回值决定的。
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(getContext(), "事件传递到了ViewGroup", Toast.LENGTH_SHORT).show();
        return isConsume || super.onTouchEvent(event);
    }
}
