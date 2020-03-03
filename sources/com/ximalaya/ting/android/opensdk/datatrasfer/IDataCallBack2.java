package com.ximalaya.ting.android.opensdk.datatrasfer;

import okhttp3.Headers;

public interface IDataCallBack2<T> {
    void a(int i, String str);

    void a(T t, Headers headers);
}
