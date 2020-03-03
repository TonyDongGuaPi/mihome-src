package com.swmansion.rnscreens;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.uimanager.PixelUtil;

@SuppressLint({"ValidFragment"})
public class ScreenStackFragment extends ScreenFragment {
    private static final float b = PixelUtil.toPixelFromDIP(4.0f);
    private AppBarLayout c;
    private Toolbar d;
    private boolean e;

    @SuppressLint({"ValidFragment"})
    public ScreenStackFragment(Screen screen) {
        super(screen);
    }

    public void b() {
        if (this.c != null) {
            ((CoordinatorLayout) getView()).removeView(this.c);
        }
    }

    public void a(Toolbar toolbar) {
        if (this.c != null) {
            this.c.addView(toolbar);
        }
        this.d = toolbar;
        AppBarLayout.LayoutParams layoutParams = new AppBarLayout.LayoutParams(-1, -2);
        layoutParams.setScrollFlags(0);
        this.d.setLayoutParams(layoutParams);
    }

    public void a(boolean z) {
        if (this.e != z) {
            this.c.setTargetElevation(z ? 0.0f : b);
            this.e = z;
        }
    }

    public void c() {
        View childAt = this.f8951a.getChildAt(0);
        if (childAt instanceof ScreenStackHeaderConfig) {
            ((ScreenStackHeaderConfig) childAt).onUpdate();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        CoordinatorLayout coordinatorLayout = new CoordinatorLayout(getContext());
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(-1, -1);
        layoutParams.setBehavior(new AppBarLayout.ScrollingViewBehavior());
        this.f8951a.setLayoutParams(layoutParams);
        coordinatorLayout.addView(this.f8951a);
        this.c = new AppBarLayout(getContext());
        this.c.setBackgroundColor(0);
        this.c.setLayoutParams(new AppBarLayout.LayoutParams(-1, -2));
        coordinatorLayout.addView(this.c);
        if (this.d != null) {
            this.c.addView(this.d);
        }
        return coordinatorLayout;
    }

    public boolean d() {
        View childAt = this.f8951a.getChildAt(0);
        if (childAt instanceof ScreenStackHeaderConfig) {
            return ((ScreenStackHeaderConfig) childAt).isDismissable();
        }
        return true;
    }
}
