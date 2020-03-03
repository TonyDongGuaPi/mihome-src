package com.xiaomi.youpin.login.other.common;

import android.os.Process;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class PriorityThreadFactory implements ThreadFactory {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final int f23570a;
    private final AtomicInteger b = new AtomicInteger(1);
    private final String c;

    public PriorityThreadFactory(int i, String str) {
        this.f23570a = i;
        this.c = str;
    }

    public Thread newThread(final Runnable runnable) {
        AnonymousClass1 r0 = new Runnable() {
            public void run() {
                try {
                    Process.setThreadPriority(PriorityThreadFactory.this.f23570a);
                } catch (Throwable unused) {
                }
                runnable.run();
            }
        };
        return new Thread(r0, this.c + " #" + this.b.getAndIncrement());
    }
}
