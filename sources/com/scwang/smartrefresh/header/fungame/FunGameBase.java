package com.scwang.smartrefresh.header.fungame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.scwang.smartrefresh.layout.api.RefreshContent;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshInternal;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;
import com.scwang.smartrefresh.layout.util.DensityUtil;

@SuppressLint({"RestrictedApi"})
public abstract class FunGameBase extends InternalAbstract implements RefreshHeader {
    boolean enableLoadMore;
    protected int mHeaderHeight;
    protected boolean mIsFinish;
    protected boolean mLastFinish;
    protected boolean mManualOperation;
    protected int mOffset;
    protected RefreshContent mRefreshContent;
    protected RefreshKernel mRefreshKernel;
    protected int mScreenHeightPixels = getResources().getDisplayMetrics().heightPixels;
    protected RefreshState mState;
    protected float mTouchY;

    /* access modifiers changed from: protected */
    public abstract void onManualOperationMove(float f, int i, int i2, int i3);

    public FunGameBase(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setMinimumHeight(DensityUtil.a(100.0f));
        this.mSpinnerStyle = SpinnerStyle.MatchLayout;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mState == RefreshState.Refreshing || super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mState != RefreshState.Refreshing && this.mState != RefreshState.RefreshFinish) {
            return super.onTouchEvent(motionEvent);
        }
        if (!this.mManualOperation) {
            onManualOperationStart();
        }
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.mTouchY = motionEvent.getRawY();
                this.mRefreshKernel.a(0, true);
                return true;
            case 1:
            case 3:
                onManualOperationRelease();
                this.mTouchY = -1.0f;
                if (this.mIsFinish) {
                    this.mRefreshKernel.a(this.mHeaderHeight, true);
                    return true;
                }
                break;
            case 2:
                float rawY = motionEvent.getRawY() - this.mTouchY;
                if (rawY < 0.0f) {
                    double d = (double) (this.mHeaderHeight * 2);
                    double d2 = (double) ((this.mScreenHeightPixels * 2) / 3);
                    double d3 = (double) rawY;
                    Double.isNaN(d3);
                    double d4 = -Math.min(0.0d, d3 * 0.5d);
                    Double.isNaN(d2);
                    Double.isNaN(d);
                    this.mRefreshKernel.a((int) (-Math.min(d * (1.0d - Math.pow(100.0d, (-d4) / d2)), d4)), false);
                    break;
                } else {
                    double d5 = (double) (this.mHeaderHeight * 2);
                    double d6 = (double) ((this.mScreenHeightPixels * 2) / 3);
                    double d7 = (double) rawY;
                    Double.isNaN(d7);
                    double max = Math.max(0.0d, d7 * 0.5d);
                    Double.isNaN(d6);
                    Double.isNaN(d5);
                    this.mRefreshKernel.a((int) Math.min(d5 * (1.0d - Math.pow(100.0d, (-max) / d6)), max), false);
                    break;
                }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onManualOperationStart() {
        if (!this.mManualOperation) {
            this.mManualOperation = true;
            this.mRefreshContent = this.mRefreshKernel.b();
            this.enableLoadMore = this.mRefreshKernel.a().isEnableLoadMore();
            this.mRefreshKernel.a().setEnableLoadMore(false);
            View a2 = this.mRefreshContent.a();
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) a2.getLayoutParams();
            marginLayoutParams.topMargin += this.mHeaderHeight;
            a2.setLayoutParams(marginLayoutParams);
        }
    }

    /* access modifiers changed from: protected */
    public void onManualOperationRelease() {
        if (this.mIsFinish) {
            this.mManualOperation = false;
            this.mRefreshKernel.a().setEnableLoadMore(this.enableLoadMore);
            if (this.mTouchY != -1.0f) {
                onFinish(this.mRefreshKernel.a(), this.mLastFinish);
                this.mRefreshKernel.a(RefreshState.RefreshFinish);
                this.mRefreshKernel.a(0);
            } else {
                this.mRefreshKernel.a(this.mHeaderHeight, true);
            }
            View a2 = this.mRefreshContent.a();
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) a2.getLayoutParams();
            marginLayoutParams.topMargin -= this.mHeaderHeight;
            a2.setLayoutParams(marginLayoutParams);
            return;
        }
        this.mRefreshKernel.a(0, true);
    }

    public void onMoving(boolean z, float f, int i, int i2, int i3) {
        if (this.mManualOperation) {
            onManualOperationMove(f, i, i2, i3);
            return;
        }
        this.mOffset = i;
        setTranslationY((float) (this.mOffset - this.mHeaderHeight));
    }

    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        this.mIsFinish = false;
        setTranslationY(0.0f);
    }

    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
        this.mState = refreshState2;
    }

    public void onInitialized(@NonNull RefreshKernel refreshKernel, int i, int i2) {
        this.mRefreshKernel = refreshKernel;
        this.mHeaderHeight = i;
        if (!isInEditMode()) {
            setTranslationY((float) (this.mOffset - this.mHeaderHeight));
            refreshKernel.a((RefreshInternal) this, true);
        }
    }

    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean z) {
        this.mLastFinish = z;
        if (!this.mIsFinish) {
            this.mIsFinish = true;
            if (this.mManualOperation) {
                if (this.mTouchY != -1.0f) {
                    return Integer.MAX_VALUE;
                }
                onManualOperationRelease();
                onFinish(refreshLayout, z);
                return 0;
            }
        }
        return 0;
    }
}
