package com.ttd.androidlearning.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.ttd.androidlearning.R;

/**
 * @author wt
 * @time 2020/5/15
 */
public class CustomView1 extends View {
    private Paint paint = new Paint();
    private int radius = 0;
    private static final int RADIUS_DEFAULT = 50;

    public CustomView1(Context context) {
        super(context);
    }

    public CustomView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(ResourcesCompat.getColor(getResources(), R.color.blue_900, null));
        canvas.drawCircle(radius, radius, radius, paint);
        canvas.save();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int minimumWidth = getSuggestedMinimumWidth();
        final int minimumHeight = getSuggestedMinimumHeight();
        int width = measureWidth(minimumWidth, widthMeasureSpec);
        int height = measureHeight(minimumHeight, heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * View 尺寸的大小影响因素：1.{@link MeasureSpec} 2.父容器的影响
     * MeasureSpec代表一个32位的int值，高2位代表SpecMode，低30位代表SpecSize。
     * SpecMode{@link MeasureSpec#getMode(int)}: 指的是测量模式，一共有3种测量的模式
     * SpecSize{@link MeasureSpec#getSize(int)}：指的是在某种测量模式下的规格大小
     * {@link MeasureSpec#UNSPECIFIED} 父容器不对View有任何的限制，要多大给多大。
     * {@link MeasureSpec#AT_MOST} 父容器指定了一个可用的大小即SpecSize，View的大小不能大于这个值，它对应wrap_content
     * {@link MeasureSpec#EXACTLY} 父容器已经检测出View所需要的精确大小，这个时候View的最终大小就是SpecSize所指定的值。它对应match_parent和具体的数值这二种模式
     */
    private int measureWidth(int defaultWidth, int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.AT_MOST:
                defaultWidth = RADIUS_DEFAULT * 2 + getPaddingLeft() + getPaddingRight();
                radius = RADIUS_DEFAULT;
                break;
            case MeasureSpec.EXACTLY:
                defaultWidth = specSize;
                radius = defaultWidth / 2;
                break;
            case MeasureSpec.UNSPECIFIED:
                defaultWidth = Math.max(defaultWidth, specSize);
        }
        return defaultWidth;
    }

    private int measureHeight(int defaultHeight, int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.AT_MOST:
                defaultHeight = radius * 2 + getPaddingTop() + getPaddingBottom();
                break;
            case MeasureSpec.EXACTLY:
                defaultHeight = specSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                defaultHeight = Math.max(defaultHeight, specSize);

                break;
        }
        return defaultHeight;
    }

}
