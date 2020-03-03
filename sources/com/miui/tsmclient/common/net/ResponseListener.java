package com.miui.tsmclient.common.net;

public interface ResponseListener<T> {
    void onFailed(int i, String str, T t);

    void onSuccess(T t);
}
