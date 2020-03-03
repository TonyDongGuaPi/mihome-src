package com.xiaomi.youpin.mimcmsg.api;

public interface IHttpCallback<T> {
    void a(int i, String str);

    void a(T t);
}
