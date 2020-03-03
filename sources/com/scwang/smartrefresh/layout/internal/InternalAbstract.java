package com.scwang.smartrefresh.layout.internal;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshInternal;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

public abstract class InternalAbstract extends RelativeLayout implements RefreshInternal {
    protected SpinnerStyle mSpinnerStyle;
    protected View mWrapperView;

    protected InternalAbstract(@NonNull View view) {
        super(view.getContext(), (AttributeSet) null, 0);
        this.mWrapperView = view;
    }

    protected InternalAbstract(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @NonNull
    public View getView() {
        return this.mWrapperView == null ? this : this.mWrapperView;
    }

    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean z) {
        if (this.mWrapperView instanceof RefreshInternal) {
            return ((RefreshInternal) this.mWrapperView).onFinish(refreshLayout, z);
        }
        return 0;
    }

    @Deprecated
    public void setPrimaryColors(@ColorInt int... iArr) {
        if (this.mWrapperView instanceof RefreshInternal) {
            ((RefreshInternal) this.mWrapperView).setPrimaryColors(iArr);
        }
    }

    @NonNull
    public SpinnerStyle getSpinnerStyle() {
        if (this.mSpinnerStyle != null) {
            return this.mSpinnerStyle;
        }
        if (this.mWrapperView instanceof RefreshInternal) {
            return ((RefreshInternal) this.mWrapperView).getSpinnerStyle();
        }
        if (this.mWrapperView != null) {
            ViewGroup.LayoutParams layoutParams = this.mWrapperView.getLayoutParams();
            if (layoutParams instanceof SmartRefreshLayout.LayoutParams) {
                this.mSpinnerStyle = ((SmartRefreshLayout.LayoutParams) layoutParams).b;
                if (this.mSpinnerStyle != null) {
                    return this.mSpinnerStyle;
                }
            }
            if (layoutParams != null && (layoutParams.height == 0 || layoutParams.height == -1)) {
                SpinnerStyle spinnerStyle = SpinnerStyle.Scale;
                this.mSpinnerStyle = spinnerStyle;
                return spinnerStyle;
            }
        }
        SpinnerStyle spinnerStyle2 = SpinnerStyle.Translate;
        this.mSpinnerStyle = spinnerStyle2;
        return spinnerStyle2;
    }

    public void onInitialized(@NonNull RefreshKernel refreshKernel, int i, int i2) {
        if (this.mWrapperView instanceof RefreshInternal) {
            ((RefreshInternal) this.mWrapperView).onInitialized(refreshKernel, i, i2);
        } else if (this.mWrapperView != null) {
            ViewGroup.LayoutParams layoutParams = this.mWrapperView.getLayoutParams();
            if (layoutParams instanceof SmartRefreshLayout.LayoutParams) {
                refreshKernel.a((RefreshInternal) this, ((SmartRefreshLayout.LayoutParams) layoutParams).f8786a);
            }
        }
    }

    public boolean isSupportHorizontalDrag() {
        return (this.mWrapperView instanceof RefreshInternal) && ((RefreshInternal) this.mWrapperView).isSupportHorizontalDrag();
    }

    public void onHorizontalDrag(float f, int i, int i2) {
        if (this.mWrapperView instanceof RefreshInternal) {
            ((RefreshInternal) this.mWrapperView).onHorizontalDrag(f, i, i2);
        }
    }

    public void onMoving(boolean z, float f, int i, int i2, int i3) {
        if (this.mWrapperView instanceof RefreshInternal) {
            ((RefreshInternal) this.mWrapperView).onMoving(z, f, i, i2, i3);
        }
    }

    public void onReleased(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        if (this.mWrapperView instanceof RefreshInternal) {
            ((RefreshInternal) this.mWrapperView).onReleased(refreshLayout, i, i2);
        }
    }

    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        if (this.mWrapperView instanceof RefreshInternal) {
            ((RefreshInternal) this.mWrapperView).onStartAnimator(refreshLayout, i, i2);
        }
    }

    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
        if (this.mWrapperView instanceof RefreshInternal) {
            ((RefreshInternal) this.mWrapperView).onStateChanged(refreshLayout, refreshState, refreshState2);
        }
    }
}
