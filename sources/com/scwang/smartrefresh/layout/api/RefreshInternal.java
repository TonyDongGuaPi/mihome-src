package com.scwang.smartrefresh.layout.api;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.view.View;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.listener.OnStateChangedListener;

@RestrictTo({RestrictTo.Scope.LIBRARY, RestrictTo.Scope.LIBRARY_GROUP, RestrictTo.Scope.SUBCLASSES})
public interface RefreshInternal extends OnStateChangedListener {
    @NonNull
    SpinnerStyle getSpinnerStyle();

    @NonNull
    View getView();

    boolean isSupportHorizontalDrag();

    int onFinish(@NonNull RefreshLayout refreshLayout, boolean z);

    void onHorizontalDrag(float f, int i, int i2);

    void onInitialized(@NonNull RefreshKernel refreshKernel, int i, int i2);

    void onMoving(boolean z, float f, int i, int i2, int i3);

    void onReleased(@NonNull RefreshLayout refreshLayout, int i, int i2);

    void onStartAnimator(@NonNull RefreshLayout refreshLayout, int i, int i2);

    void setPrimaryColors(@ColorInt int... iArr);
}
