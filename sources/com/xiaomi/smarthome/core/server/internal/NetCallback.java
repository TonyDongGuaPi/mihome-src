package com.xiaomi.smarthome.core.server.internal;

import com.xiaomi.smarthome.core.entity.Error;

public interface NetCallback<R, E extends Error> {
    void a(E e);

    void a(R r);

    void b(R r);
}
