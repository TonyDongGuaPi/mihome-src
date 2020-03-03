package com.xiaomi.youpin.network.bean;

import com.xiaomi.youpin.network.bean.NetError;

public interface NetCallback<R, E extends NetError> {
    void a(long j, long j2, boolean z);

    void a(E e);

    void a(R r);

    void b(long j, long j2, boolean z);

    void b(R r);
}
