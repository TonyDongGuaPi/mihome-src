package com.scwang.smartrefresh.layout.api;

import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import com.scwang.smartrefresh.layout.constant.RefreshState;

public interface RefreshKernel {
    ValueAnimator a(int i);

    RefreshKernel a(int i, boolean z);

    RefreshKernel a(@NonNull RefreshInternal refreshInternal);

    RefreshKernel a(RefreshInternal refreshInternal, int i);

    RefreshKernel a(@NonNull RefreshInternal refreshInternal, boolean z);

    RefreshKernel a(@NonNull RefreshState refreshState);

    RefreshKernel a(boolean z);

    @NonNull
    RefreshLayout a();

    @NonNull
    RefreshContent b();

    RefreshKernel b(int i);

    RefreshKernel b(@NonNull RefreshInternal refreshInternal, boolean z);

    RefreshKernel c();
}
