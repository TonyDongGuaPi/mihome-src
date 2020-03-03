package com.xiaomi.youpin.youpin_common.api;

public interface StoreBaseCallback<T> {
    void onFail(int i, String str);

    void onSuccess(T t);
}
