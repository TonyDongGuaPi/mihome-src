package com.google.common.util.concurrent;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class ExecutionList {
    @VisibleForTesting
    static final Logger log = Logger.getLogger(ExecutionList.class.getName());
    @GuardedBy("this")
    private boolean executed;
    @GuardedBy("this")
    private RunnableExecutorPair runnables;

    public void add(Runnable runnable, Executor executor) {
        Preconditions.checkNotNull(runnable, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        synchronized (this) {
            if (!this.executed) {
                this.runnables = new RunnableExecutorPair(runnable, executor, this.runnables);
            } else {
                executeListener(runnable, executor);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0015, code lost:
        r2 = r1.next;
        r1.next = r0;
        r0 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r0 == null) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        executeListener(r0.runnable, r0.executor);
        r0 = r0.next;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0010, code lost:
        r1 = r0;
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        if (r1 == null) goto L_0x001c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute() {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.executed     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r4)     // Catch:{ all -> 0x0029 }
            return
        L_0x0007:
            r0 = 1
            r4.executed = r0     // Catch:{ all -> 0x0029 }
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r0 = r4.runnables     // Catch:{ all -> 0x0029 }
            r1 = 0
            r4.runnables = r1     // Catch:{ all -> 0x0029 }
            monitor-exit(r4)     // Catch:{ all -> 0x0029 }
            r3 = r1
            r1 = r0
            r0 = r3
        L_0x0013:
            if (r1 == 0) goto L_0x001c
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r2 = r1.next
            r1.next = r0
            r0 = r1
            r1 = r2
            goto L_0x0013
        L_0x001c:
            if (r0 == 0) goto L_0x0028
            java.lang.Runnable r1 = r0.runnable
            java.util.concurrent.Executor r2 = r0.executor
            executeListener(r1, r2)
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r0 = r0.next
            goto L_0x001c
        L_0x0028:
            return
        L_0x0029:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0029 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ExecutionList.execute():void");
    }

    private static void executeListener(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = log;
            Level level = Level.SEVERE;
            String valueOf = String.valueOf(String.valueOf(runnable));
            String valueOf2 = String.valueOf(String.valueOf(executor));
            StringBuilder sb = new StringBuilder(valueOf.length() + 57 + valueOf2.length());
            sb.append("RuntimeException while executing runnable ");
            sb.append(valueOf);
            sb.append(" with executor ");
            sb.append(valueOf2);
            logger.log(level, sb.toString(), e);
        }
    }

    private static final class RunnableExecutorPair {
        final Executor executor;
        @Nullable
        RunnableExecutorPair next;
        final Runnable runnable;

        RunnableExecutorPair(Runnable runnable2, Executor executor2, RunnableExecutorPair runnableExecutorPair) {
            this.runnable = runnable2;
            this.executor = executor2;
            this.next = runnableExecutorPair;
        }
    }
}
