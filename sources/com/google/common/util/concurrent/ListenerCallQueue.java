package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.concurrent.GuardedBy;

final class ListenerCallQueue<L> implements Runnable {
    private static final Logger logger = Logger.getLogger(ListenerCallQueue.class.getName());
    private final Executor executor;
    @GuardedBy("this")
    private boolean isThreadScheduled;
    private final L listener;
    @GuardedBy("this")
    private final Queue<Callback<L>> waitQueue = Queues.newArrayDeque();

    static abstract class Callback<L> {
        /* access modifiers changed from: private */
        public final String methodCall;

        /* access modifiers changed from: package-private */
        public abstract void call(L l);

        Callback(String str) {
            this.methodCall = str;
        }

        /* access modifiers changed from: package-private */
        public void enqueueOn(Iterable<ListenerCallQueue<L>> iterable) {
            for (ListenerCallQueue<L> add : iterable) {
                add.add(this);
            }
        }
    }

    ListenerCallQueue(L l, Executor executor2) {
        this.listener = Preconditions.checkNotNull(l);
        this.executor = (Executor) Preconditions.checkNotNull(executor2);
    }

    /* access modifiers changed from: package-private */
    public synchronized void add(Callback<L> callback) {
        this.waitQueue.add(callback);
    }

    /* access modifiers changed from: package-private */
    public void execute() {
        boolean z;
        synchronized (this) {
            z = true;
            if (!this.isThreadScheduled) {
                this.isThreadScheduled = true;
            } else {
                z = false;
            }
        }
        if (z) {
            try {
                this.executor.execute(this);
            } catch (RuntimeException e) {
                synchronized (this) {
                    this.isThreadScheduled = false;
                    Logger logger2 = logger;
                    Level level = Level.SEVERE;
                    String valueOf = String.valueOf(String.valueOf(this.listener));
                    String valueOf2 = String.valueOf(String.valueOf(this.executor));
                    StringBuilder sb = new StringBuilder(valueOf.length() + 42 + valueOf2.length());
                    sb.append("Exception while running callbacks for ");
                    sb.append(valueOf);
                    sb.append(" on ");
                    sb.append(valueOf2);
                    logger2.log(level, sb.toString(), e);
                    throw e;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r2.call(r10.listener);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r10 = this;
        L_0x0000:
            r0 = 0
            r1 = 1
            monitor-enter(r10)     // Catch:{ all -> 0x0067 }
            boolean r2 = r10.isThreadScheduled     // Catch:{ all -> 0x0064 }
            com.google.common.base.Preconditions.checkState(r2)     // Catch:{ all -> 0x0064 }
            java.util.Queue<com.google.common.util.concurrent.ListenerCallQueue$Callback<L>> r2 = r10.waitQueue     // Catch:{ all -> 0x0064 }
            java.lang.Object r2 = r2.poll()     // Catch:{ all -> 0x0064 }
            com.google.common.util.concurrent.ListenerCallQueue$Callback r2 = (com.google.common.util.concurrent.ListenerCallQueue.Callback) r2     // Catch:{ all -> 0x0064 }
            if (r2 != 0) goto L_0x001a
            r10.isThreadScheduled = r0     // Catch:{ all -> 0x0064 }
            monitor-exit(r10)     // Catch:{ all -> 0x0016 }
            return
        L_0x0016:
            r1 = move-exception
            r2 = r1
            r1 = 0
            goto L_0x0065
        L_0x001a:
            monitor-exit(r10)     // Catch:{ all -> 0x0064 }
            L r3 = r10.listener     // Catch:{ RuntimeException -> 0x0021 }
            r2.call(r3)     // Catch:{ RuntimeException -> 0x0021 }
            goto L_0x0000
        L_0x0021:
            r3 = move-exception
            java.util.logging.Logger r4 = logger     // Catch:{ all -> 0x0067 }
            java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch:{ all -> 0x0067 }
            L r6 = r10.listener     // Catch:{ all -> 0x0067 }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x0067 }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x0067 }
            java.lang.String r2 = r2.methodCall     // Catch:{ all -> 0x0067 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x0067 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x0067 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0067 }
            int r8 = r6.length()     // Catch:{ all -> 0x0067 }
            int r8 = r8 + 37
            int r9 = r2.length()     // Catch:{ all -> 0x0067 }
            int r8 = r8 + r9
            r7.<init>(r8)     // Catch:{ all -> 0x0067 }
            java.lang.String r8 = "Exception while executing callback: "
            r7.append(r8)     // Catch:{ all -> 0x0067 }
            r7.append(r6)     // Catch:{ all -> 0x0067 }
            java.lang.String r6 = "."
            r7.append(r6)     // Catch:{ all -> 0x0067 }
            r7.append(r2)     // Catch:{ all -> 0x0067 }
            java.lang.String r2 = r7.toString()     // Catch:{ all -> 0x0067 }
            r4.log(r5, r2, r3)     // Catch:{ all -> 0x0067 }
            goto L_0x0000
        L_0x0064:
            r2 = move-exception
        L_0x0065:
            monitor-exit(r10)     // Catch:{ all -> 0x0064 }
            throw r2     // Catch:{ all -> 0x0067 }
        L_0x0067:
            r2 = move-exception
            if (r1 == 0) goto L_0x0072
            monitor-enter(r10)
            r10.isThreadScheduled = r0     // Catch:{ all -> 0x006f }
            monitor-exit(r10)     // Catch:{ all -> 0x006f }
            goto L_0x0072
        L_0x006f:
            r0 = move-exception
            monitor-exit(r10)     // Catch:{ all -> 0x006f }
            throw r0
        L_0x0072:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ListenerCallQueue.run():void");
    }
}
