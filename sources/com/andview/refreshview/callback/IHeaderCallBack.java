package com.andview.refreshview.callback;

public interface IHeaderCallBack {
    int getHeaderHeight();

    void hide();

    void onHeaderMove(double d, int i, int i2);

    void onStateFinish(boolean z);

    void onStateNormal();

    void onStateReady();

    void onStateRefreshing();

    void setRefreshTime(long j);

    void show();
}
