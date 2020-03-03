package com.xiaomi.mishopsdk.widget.pulltorefresh;

import android.view.View;
import com.xiaomi.mishopsdk.widget.pulltorefresh.SimplePullToRefreshLayout;

public interface IPullToRefresh<T extends View> {
    SimplePullToRefreshLayout.State getState();

    boolean isPullToRefreshEnabled();

    boolean isRefreshing();

    void onRefreshComplete();

    void setOnRefreshListener(SimplePullToRefreshLayout.OnRefreshListener onRefreshListener);

    void setRefreshing();
}
