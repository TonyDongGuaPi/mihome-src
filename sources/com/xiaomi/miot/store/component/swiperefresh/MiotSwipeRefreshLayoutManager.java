package com.xiaomi.miot.store.component.swiperefresh;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.views.swiperefresh.SwipeRefreshLayoutManager;

public class MiotSwipeRefreshLayoutManager extends SwipeRefreshLayoutManager {
    public String getName() {
        return "MiotRefreshableLayoutAndroid";
    }

    /* access modifiers changed from: protected */
    public MiotReactSwipeRefreshLayout createViewInstance(ThemedReactContext themedReactContext) {
        return new MiotReactSwipeRefreshLayout(themedReactContext);
    }
}
