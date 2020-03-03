package com.xiaomi.youpin.core.net;

import android.support.annotation.WorkerThread;
import com.xiaomi.youpin.core.Error;

public interface NetCallback<R, E extends Error> {
    @WorkerThread
    void a(long j, long j2, boolean z);

    void a(E e);

    void a(R r);

    void b(R r);
}
