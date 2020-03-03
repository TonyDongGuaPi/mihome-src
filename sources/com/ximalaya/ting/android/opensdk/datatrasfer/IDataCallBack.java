package com.ximalaya.ting.android.opensdk.datatrasfer;

import android.support.annotation.Nullable;

public interface IDataCallBack<T> {
    void a(int i, String str);

    void a(@Nullable T t);
}
