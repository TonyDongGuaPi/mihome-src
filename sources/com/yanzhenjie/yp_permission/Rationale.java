package com.yanzhenjie.yp_permission;

import android.content.Context;

public interface Rationale<T> {
    void a(Context context, T t, RequestExecutor requestExecutor);
}
