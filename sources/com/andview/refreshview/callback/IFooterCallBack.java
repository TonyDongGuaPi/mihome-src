package com.andview.refreshview.callback;

import com.andview.refreshview.XRefreshView;

public interface IFooterCallBack {
    void callWhenNotAutoLoadMore(XRefreshView xRefreshView);

    int getFooterHeight();

    boolean isShowing();

    void onReleaseToLoadMore();

    void onStateComplete();

    void onStateFinish(boolean z);

    void onStateReady();

    void onStateRefreshing();

    void show(boolean z);
}
