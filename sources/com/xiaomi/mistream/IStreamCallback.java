package com.xiaomi.mistream;

public interface IStreamCallback<T> {
    void onFailed(int i, String str);

    void onProgress(int i);

    void onSuccess(T t, Object obj);
}
