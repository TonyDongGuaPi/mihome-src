package com.xiaomi.youpin.login.api.callback;

import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;

public interface BasePassportCallback<T> {
    public static final int c = -100;

    void a(int i, String str);

    void a(MiServiceTokenInfo miServiceTokenInfo);

    void a(T t);
}
