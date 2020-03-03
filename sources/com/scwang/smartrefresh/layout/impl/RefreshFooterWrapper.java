package com.scwang.smartrefresh.layout.impl;

import android.annotation.SuppressLint;
import android.view.View;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;

@SuppressLint({"ViewConstructor"})
public class RefreshFooterWrapper extends InternalAbstract implements RefreshFooter {
    public RefreshFooterWrapper(View view) {
        super(view);
    }

    public boolean setNoMoreData(boolean z) {
        return (this.mWrapperView instanceof RefreshFooter) && ((RefreshFooter) this.mWrapperView).setNoMoreData(z);
    }
}
