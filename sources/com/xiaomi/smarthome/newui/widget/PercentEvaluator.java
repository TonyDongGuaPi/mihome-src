package com.xiaomi.smarthome.newui.widget;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayoutSpringBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.xiaomi.smarthome.R;

class PercentEvaluator implements AppBarLayout.OnOffsetChangedListener, AppBarLayoutSpringBehavior.SpringOffsetCallback {
    private static final String e = "PercentEvaluator";

    /* renamed from: a  reason: collision with root package name */
    float f20876a = 0.5f;
    private AppBarLayout b;
    private OnPercentChangeListener c;
    private int d = -1;

    interface OnPercentChangeListener {
        void onPercentChange(float f);
    }

    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i != 0) {
            a(i);
            this.d = i;
        } else if (this.d < 0) {
            this.d = i;
            a(i);
        }
    }

    public void springCallback(int i) {
        this.d = i;
        a(i);
    }

    private void a(int i) {
        if (this.b != null) {
            if (i == 0) {
                this.f20876a = 0.5f;
            } else if (i > 0) {
                this.f20876a = (((float) i) / ((float) this.b.getTotalScrollRange())) + 0.5f;
            } else {
                this.f20876a = (((float) i) / ((float) this.b.getTotalScrollRange())) + 0.5f;
            }
            if (this.c != null) {
                this.c.onPercentChange(this.f20876a);
            }
        }
    }

    public void a(ReactiveWallpaper reactiveWallpaper, OnPercentChangeListener onPercentChangeListener) {
        this.c = onPercentChangeListener;
        try {
            this.b = a((View) reactiveWallpaper);
            this.b.addOnOffsetChangedListener((AppBarLayout.OnOffsetChangedListener) this);
            ((AppBarLayoutSpringBehavior) ((CoordinatorLayout.LayoutParams) this.b.getLayoutParams()).getBehavior()).addSpringOffsetCallback(this);
        } catch (Exception unused) {
        }
    }

    public void a() {
        try {
            if (this.b != null) {
                this.b.removeOnOffsetChangedListener((AppBarLayout.OnOffsetChangedListener) this);
                ((AppBarLayoutSpringBehavior) ((CoordinatorLayout.LayoutParams) this.b.getLayoutParams()).getBehavior()).removeSpringOffsetCallback(this);
            }
        } catch (Exception unused) {
        }
    }

    public AppBarLayout a(View view) {
        View findViewById;
        if (view == null) {
            return null;
        }
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if ((parent instanceof ViewGroup) && (findViewById = ((ViewGroup) parent).findViewById(R.id.main_appbar)) != null && (findViewById instanceof AppBarLayout)) {
                return (AppBarLayout) findViewById;
            }
        }
        return null;
    }
}
