package com.xiaomi.smarthome.newui.widget.topnavi.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.xiaomi.smarthome.library.common.widget.nestscroll.UIUtils;
import com.xiaomi.smarthome.newui.widget.topnavi.HomeNavigationDeviceEvent;
import com.xiaomi.smarthome.newui.widget.topnavi.HomeNavigationRoomEvent;
import com.xiaomi.smarthome.newui.widget.topnavi.StickEvent;
import com.xiaomi.smarthome.newui.widget.topnavi.StickLogConsumer;

public class MyViewPager extends ViewPager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20951a = "MyViewPager";
    private boolean b;

    public boolean isTransitionGroup() {
        return true;
    }

    public MyViewPager(Context context) {
        this(context, (AttributeSet) null);
    }

    public MyViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = false;
        setPageMargin(UIUtils.a(8));
        setOffscreenPageLimit(1);
        addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            public void onPageSelected(int i) {
                if (i == 0) {
                    StickLogConsumer.f20916a.a((StickEvent) HomeNavigationRoomEvent.f20909a);
                } else {
                    StickLogConsumer.f20916a.a((StickEvent) HomeNavigationDeviceEvent.f20908a);
                }
            }
        });
    }

    public void setCurrentItem(int i) {
        super.setCurrentItem(i, true);
    }

    public void onEnterEditMode() {
        this.b = true;
    }

    public void onExitEditMode() {
        this.b = false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return !this.b && super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            return !this.b && super.onTouchEvent(motionEvent);
        } catch (Exception unused) {
            return false;
        }
    }

    public void setBackground(Drawable drawable) {
        super.setBackground(drawable);
    }

    public void setBackgroundColor(int i) {
        super.setBackgroundColor(i);
    }
}
