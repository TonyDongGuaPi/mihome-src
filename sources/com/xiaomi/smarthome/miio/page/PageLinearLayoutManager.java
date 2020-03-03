package com.xiaomi.smarthome.miio.page;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.View;

public class PageLinearLayoutManager extends LinearLayoutManager {
    public PageLinearLayoutManager(Context context) {
        super(context);
    }

    public PageLinearLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
    }

    public PageLinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void addView(View view) {
        try {
            super.addView(view);
        } catch (IllegalStateException unused) {
        }
    }

    public void addView(View view, int i) {
        try {
            super.addView(view, i);
        } catch (IllegalStateException unused) {
        }
    }
}
