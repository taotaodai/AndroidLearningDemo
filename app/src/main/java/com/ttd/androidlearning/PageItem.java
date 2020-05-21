package com.ttd.androidlearning;

import com.ttd.androidlearning.animations.PropertyAnimationActivity;
import com.ttd.androidlearning.animations.ViewAnimationsActivity;
import com.ttd.androidlearning.custom_view.CustomViewActivity1;
import com.ttd.androidlearning.custom_view.CustomViewGroupActivity1;
import com.ttd.androidlearning.custom_view.TouchTransferActivity;

import java.util.Arrays;
import java.util.List;

/**
 * @author wt
 * @time 2020/5/15
 */
public enum PageItem {
    VIEW_ANIMATIONS("View 动画", ViewAnimationsActivity.class),
    PROPERTY_ANIMATION("属性动画", PropertyAnimationActivity.class),

    VIEW_MEASURE("View 的大小测量", CustomViewActivity1.class),
    VIEW_GROUP1("自定义ViewGroup", CustomViewGroupActivity1.class),
    TOUCH_TRANSFER("Touch事件分发", TouchTransferActivity.class),

    ANIMATIONS("动画", Arrays.asList(VIEW_ANIMATIONS, PROPERTY_ANIMATION)),

    CUSTOM_VIEW("自定义View", Arrays.asList(VIEW_MEASURE, VIEW_GROUP1,TOUCH_TRANSFER));

    String title;
    Class mClass;
    List<PageItem> next;

    PageItem(String title, Class mClass) {
        this.title = title;
        this.mClass = mClass;
    }

    PageItem(String title, List<PageItem> next) {
        this.title = title;
        this.next = next;
    }

    public static List<PageItem> getMainItems() {
        return Arrays.asList(ANIMATIONS, CUSTOM_VIEW);
    }
}
