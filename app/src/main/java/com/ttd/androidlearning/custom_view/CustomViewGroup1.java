package com.ttd.androidlearning.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author wt
 * @time 2020/5/16
 * 自定义ViewGroup。实现一个流式布局
 * 1.重写{@link #onLayout(boolean, int, int, int, int)} 为childView设定其在ViewGroup中的位置。
 * 2.重写{@link #onMeasure(int, int)} 根据父ViewGroup为其设定的测量规格来测量自己的宽和高，以及给childView传递测量规格和测量它的childView的宽和高。
 */
public class CustomViewGroup1 extends ViewGroup {
    public CustomViewGroup1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //给Child传递测量规格
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        /*
         * 根据childView所占宽度/高度，计算ViewGroup宽度/高度
         */
        int leftPadding = getPaddingLeft();
        int topPadding = getPaddingTop();
        int rightPadding = getPaddingRight();
        int bottomPadding = getPaddingBottom();

        int x = leftPadding;
        int y = topPadding;
        int maxChildHeight = y;
        int containerWidth = getMeasuredWidth();//GroupView的父控容器的宽度
        int width = 0;//childView占用的宽度(这里只要取最大那行的宽度即可)
        int height = 0;//childView占用的高度(childView每行的高度和)
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
            int leftMargin = 0;
            int topMargin = 0;
            int rightMargin = 0;
            int bottomMargin = 0;
            if (layoutParams instanceof MarginLayoutParams) {
                leftMargin = ((MarginLayoutParams) layoutParams).leftMargin;
                topMargin = ((MarginLayoutParams) layoutParams).topMargin;
                rightMargin = ((MarginLayoutParams) layoutParams).rightMargin;
                bottomMargin = ((MarginLayoutParams) layoutParams).bottomMargin;
            }

            /*
             * 计算子控件占用的宽/高
             */
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            //子控件占用宽度
            int childWidthUsed = childWidth + leftMargin + rightMargin;
            int childHeightUsed = childHeight + topMargin + bottomMargin;

            //宽度超出一行，需要换行。
            if (x + childWidthUsed >= containerWidth) {
                x = leftPadding;
                y += maxChildHeight;
                width = containerWidth;//此时ViewGroup的宽度也达到最大
                height += maxChildHeight;
                maxChildHeight = childHeight;
            }

            //取最高的子控件高度作为行高
            maxChildHeight = Math.max(maxChildHeight, childHeightUsed);
            if(width < containerWidth){
                width += childWidthUsed;
            }
            if (i == childCount - 1) {
                height += maxChildHeight;
                height += (topPadding + bottomPadding);

                width += (leftPadding + rightPadding);
            }


            x += childWidthUsed;
        }
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth : width, (heightMode == MeasureSpec.EXACTLY) ? sizeHeight : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        /*
         * Padding
         */
        int leftPadding = getPaddingLeft();
        int topPadding = getPaddingTop();
//        int rightPadding = getPaddingRight();
//        int bottomPadding = getPaddingBottom();

        int x = leftPadding;//当前子控件的x坐标
        int y = topPadding;//当前行的y坐标
        int maxChildHeight = y;//用来记录子控件最高高度，作为行高

        int containerWidth = getMeasuredWidth();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            /*
             * Margin
             */
            ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
            int leftMargin = 0;
            int topMargin = 0;
            int rightMargin = 0;
            int bottomMargin = 0;
            if (layoutParams instanceof MarginLayoutParams) {
                leftMargin = ((MarginLayoutParams) layoutParams).leftMargin;
                topMargin = ((MarginLayoutParams) layoutParams).topMargin;
                rightMargin = ((MarginLayoutParams) layoutParams).rightMargin;
                bottomMargin = ((MarginLayoutParams) layoutParams).bottomMargin;
            }

            /*
             * 计算子控件占用的宽/高
             */
            int childWidth = child.getMeasuredWidth();//子控件实际宽度
            int childHeight = child.getMeasuredHeight();

            //子控件占用宽度
            int childWidthUsed = childWidth + leftMargin + rightMargin;
            int childHeightUsed = childHeight + topMargin + bottomMargin;

            //宽度超出一行，需要换行
            if (x + childWidthUsed >= containerWidth) {
                x = leftPadding;
                y += maxChildHeight;
                maxChildHeight = childHeight;
            }

            //取最高的子控件高度作为行高
            maxChildHeight = Math.max(maxChildHeight, childHeightUsed);

            child.layout(x + leftMargin,
                    y + topMargin,
                    x + childWidth + leftMargin,
                    y + childHeight + topMargin);

            x += childWidthUsed;
        }
    }
}
