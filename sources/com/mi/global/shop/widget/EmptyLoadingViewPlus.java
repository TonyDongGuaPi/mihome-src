package com.mi.global.shop.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.mi.global.shop.widget.ptr.PtrClassicFrameLayout;
import com.mi.global.shop.widget.pulltorefresh.SimplePullToRefreshLayout;
import java.lang.ref.WeakReference;

public class EmptyLoadingViewPlus extends EmptyLoadingView {
    public WeakReference<PtrClassicFrameLayout> mRefreshLayoutReference;
    public WeakReference<SimplePullToRefreshLayout> mSimpleRefreshLayoutReference;

    public EmptyLoadingViewPlus(Context context) {
        super(context);
        a();
    }

    public EmptyLoadingViewPlus(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.dontShowBg = true;
    }

    public void startLoading(boolean z) {
        super.startLoading(z);
        if (z) {
            this.mProgressContainer.setVisibility(8);
        } else {
            this.mProgressContainer.setVisibility(0);
        }
    }

    public void onFinish() {
        SimplePullToRefreshLayout simplePullToRefreshLayout;
        PtrClassicFrameLayout ptrClassicFrameLayout;
        if (this.mRefreshLayoutReference != null || this.mSimpleRefreshLayoutReference != null) {
            if (!(this.mRefreshLayoutReference == null || (ptrClassicFrameLayout = (PtrClassicFrameLayout) this.mRefreshLayoutReference.get()) == null)) {
                ptrClassicFrameLayout.refreshComplete();
            }
            if (this.mSimpleRefreshLayoutReference != null && (simplePullToRefreshLayout = (SimplePullToRefreshLayout) this.mSimpleRefreshLayoutReference.get()) != null) {
                simplePullToRefreshLayout.onRefreshComplete();
            }
        }
    }

    public void setPullToRefreshLayout(PtrClassicFrameLayout ptrClassicFrameLayout) {
        this.mRefreshLayoutReference = new WeakReference<>(ptrClassicFrameLayout);
    }

    public void setPullToRefreshLayout(SimplePullToRefreshLayout simplePullToRefreshLayout) {
        this.mSimpleRefreshLayoutReference = new WeakReference<>(simplePullToRefreshLayout);
    }
}
