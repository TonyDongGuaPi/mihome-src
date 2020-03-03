package com.xiaomi.smarthome.camera.activity.alarm2.util;

import android.content.Context;
import android.support.v7.widget.LinearSmoothScroller;

public class TopSmoothScroller extends LinearSmoothScroller {
    /* access modifiers changed from: protected */
    public int getHorizontalSnapPreference() {
        return -1;
    }

    /* access modifiers changed from: protected */
    public int getVerticalSnapPreference() {
        return -1;
    }

    public TopSmoothScroller(Context context) {
        super(context);
    }
}
