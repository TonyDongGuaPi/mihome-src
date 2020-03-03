package com.sina.weibo.sdk.statistic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WBAgentExecutor {

    /* renamed from: a  reason: collision with root package name */
    private static ExecutorService f8836a = Executors.newSingleThreadExecutor();
    private static long b = 5;

    WBAgentExecutor() {
    }

    public static synchronized void a(Runnable runnable) {
        synchronized (WBAgentExecutor.class) {
            if (f8836a.isShutdown()) {
                f8836a = Executors.newSingleThreadExecutor();
            }
            f8836a.execute(runnable);
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a() {
        /*
            java.lang.Class<com.sina.weibo.sdk.statistic.WBAgentExecutor> r0 = com.sina.weibo.sdk.statistic.WBAgentExecutor.class
            monitor-enter(r0)
            java.util.concurrent.ExecutorService r1 = f8836a     // Catch:{ Exception -> 0x001d, all -> 0x001a }
            boolean r1 = r1.isShutdown()     // Catch:{ Exception -> 0x001d, all -> 0x001a }
            if (r1 != 0) goto L_0x0010
            java.util.concurrent.ExecutorService r1 = f8836a     // Catch:{ Exception -> 0x001d, all -> 0x001a }
            r1.shutdown()     // Catch:{ Exception -> 0x001d, all -> 0x001a }
        L_0x0010:
            java.util.concurrent.ExecutorService r1 = f8836a     // Catch:{ Exception -> 0x001d, all -> 0x001a }
            long r2 = b     // Catch:{ Exception -> 0x001d, all -> 0x001a }
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ Exception -> 0x001d, all -> 0x001a }
            r1.awaitTermination(r2, r4)     // Catch:{ Exception -> 0x001d, all -> 0x001a }
            goto L_0x001d
        L_0x001a:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        L_0x001d:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.statistic.WBAgentExecutor.a():void");
    }
}
