package com.mi.global.shop.widget.pulltorefresh;

import android.view.View;
import com.mi.global.shop.widget.pulltorefresh.SimplePullToRefreshLayout;

public interface IPullToRefresh<T extends View> {
    SimplePullToRefreshLayout.State getState();

    boolean isPullToRefreshEnabled();

    boolean isRefreshing();

    void onRefreshComplete();

    void setOnRefreshListener(SimplePullToRefreshLayout.OnRefreshListener onRefreshListener);

    void setRefreshing();
}
