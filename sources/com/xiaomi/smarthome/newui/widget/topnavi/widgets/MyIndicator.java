package com.xiaomi.smarthome.newui.widget.topnavi.widgets;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.ViewPagerHelper;
import java.util.ArrayList;
import java.util.List;

public class MyIndicator extends ConstraintLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20950a = "MyIndicator";
    private final List<Integer> b;
    private PageIndicator c;
    private boolean d;

    public boolean isTransitionGroup() {
        return true;
    }

    public MyIndicator(Context context) {
        this(context, (AttributeSet) null);
    }

    public MyIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MyIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new ArrayList();
        this.d = false;
        inflate(context, R.layout.top_navi_layout, this);
        this.c = (PageIndicator) findViewById(R.id.page_indicator);
        this.b.add(Integer.valueOf(R.string.family_device));
        this.b.add(Integer.valueOf(R.string.room_name));
        this.c.refreshData(this.b);
    }

    public void selectDevicePage() {
        this.c.onPageSelected(0);
    }

    public void selectRoomPage() {
        this.c.onPageSelected(1);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.d || super.onInterceptTouchEvent(motionEvent);
    }

    public void onEnterEditMode() {
        this.d = true;
    }

    public void onExitEditMode() {
        this.d = false;
    }

    public void attachViewPager(ViewPager viewPager) {
        this.c.attachViewPager(viewPager);
        ViewPagerHelper.a(this.c, viewPager);
    }
}
