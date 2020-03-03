package com.xiaomi.plugin;

public interface Callback<T> {
    void onCache(T t);

    void onFailure(int i, String str);

    void onSuccess(T t, boolean z);
}
