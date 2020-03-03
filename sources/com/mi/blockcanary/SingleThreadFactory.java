package com.mi.blockcanary;

import java.util.concurrent.ThreadFactory;

final class SingleThreadFactory implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    private final String f6750a;

    SingleThreadFactory(String str) {
        this.f6750a = "BlockCanary-" + str;
    }

    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, this.f6750a);
    }
}
