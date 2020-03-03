package com.scwang.smartrefresh.layout.listener;

import android.support.annotation.NonNull;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;

public class SimpleMultiPurposeListener implements OnMultiPurposeListener {
    public void a(RefreshFooter refreshFooter, int i, int i2) {
    }

    public void a(RefreshFooter refreshFooter, boolean z) {
    }

    public void a(RefreshFooter refreshFooter, boolean z, float f, int i, int i2, int i3) {
    }

    public void a(RefreshHeader refreshHeader, int i, int i2) {
    }

    public void a(RefreshHeader refreshHeader, boolean z) {
    }

    public void a(RefreshHeader refreshHeader, boolean z, float f, int i, int i2, int i3) {
    }

    public void a(@NonNull RefreshLayout refreshLayout) {
    }

    public void b(RefreshFooter refreshFooter, int i, int i2) {
    }

    public void b(RefreshHeader refreshHeader, int i, int i2) {
    }

    public void b(@NonNull RefreshLayout refreshLayout) {
    }

    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
    }
}
