package com.xiaomi.jr.web.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import com.xiaomi.jr.base.pulltorefresh.PullToRefreshBase;
import com.xiaomi.jr.web.ObservableWebView;

public class PullToRefreshWebView extends PullToRefreshBase<ObservableWebView> {
    public PullToRefreshWebView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PullToRefreshWebView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PullToRefreshWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public ObservableWebView createRefreshableView(Context context, AttributeSet attributeSet) {
        return new ObservableWebView(context);
    }

    /* access modifiers changed from: protected */
    public boolean isReadyForPullDown() {
        return ((ObservableWebView) this.mRefreshableView).getScrollY() == 0;
    }
}
