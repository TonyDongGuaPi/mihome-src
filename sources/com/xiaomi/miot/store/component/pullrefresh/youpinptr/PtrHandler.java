package com.xiaomi.miot.store.component.pullrefresh.youpinptr;

import android.view.View;

public interface PtrHandler {
    boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2);

    void onRefreshBegin(PtrFrameLayout ptrFrameLayout);
}
