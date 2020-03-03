package com.scwang.smartrefresh.layout.api;

import android.content.Context;
import android.support.annotation.NonNull;

public interface DefaultRefreshHeaderCreator {
    @NonNull
    RefreshHeader a(@NonNull Context context, @NonNull RefreshLayout refreshLayout);
}
