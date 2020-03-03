package com.xiaomi.youpin.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

public interface ExecutorSupplier {
    ThreadPoolExecutor b();

    ThreadPoolExecutor c();

    Executor d();
}
