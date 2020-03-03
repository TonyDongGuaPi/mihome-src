package com.xiaomi.youpin.youpin_common.api;

public interface IStoreCallback<T> {
    void onFailed(int i, String str);

    void onSuccess(T t);
}
