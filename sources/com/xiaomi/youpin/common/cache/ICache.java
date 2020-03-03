package com.xiaomi.youpin.common.cache;

import java.util.Set;

public interface ICache<T> {
    Set<String> a();

    boolean a(String str, T t);

    void b();

    boolean b(String str);

    void c();

    boolean c(String str);

    T d(String str);

    void d();
}
