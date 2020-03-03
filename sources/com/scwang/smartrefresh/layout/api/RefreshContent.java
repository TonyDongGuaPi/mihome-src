package com.scwang.smartrefresh.layout.api;

import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

public interface RefreshContent {
    ValueAnimator.AnimatorUpdateListener a(int i);

    @NonNull
    View a();

    void a(int i, int i2, int i3);

    void a(MotionEvent motionEvent);

    void a(RefreshKernel refreshKernel, View view, View view2);

    void a(ScrollBoundaryDecider scrollBoundaryDecider);

    void a(boolean z);

    @NonNull
    View b();

    boolean c();

    boolean d();
}
