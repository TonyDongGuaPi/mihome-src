package com.xiaomi.shopviews.widget.view;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.xiaomi.shopviews.widget.R;

public final class SimpleLoadMoreView extends LoadMoreView {
    public int d() {
        return R.layout.load_more_view;
    }

    /* access modifiers changed from: protected */
    public int e() {
        return R.id.load_more_loading_view;
    }

    /* access modifiers changed from: protected */
    public int f() {
        return R.id.load_more_load_fail_view;
    }

    /* access modifiers changed from: protected */
    public int g() {
        return R.id.load_more_load_end_view;
    }
}
