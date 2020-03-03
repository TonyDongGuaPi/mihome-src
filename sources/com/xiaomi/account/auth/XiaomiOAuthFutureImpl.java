package com.xiaomi.account.auth;

import android.accounts.OperationCanceledException;
import android.os.Looper;
import com.xiaomi.account.openauth.XMAuthericationException;
import com.xiaomi.account.openauth.XiaomiOAuthFuture;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class XiaomiOAuthFutureImpl<V> extends FutureTask<V> implements XiaomiOAuthFuture<V> {
    private static final long DEFAULT_TIMEOUT_MINUTE = 10;

    private void ensureNotOnMainThread() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null && myLooper == Looper.getMainLooper()) {
            throw new IllegalStateException("calling this from your main thread can lead to deadlock");
        }
    }

    public XiaomiOAuthFutureImpl(Callable<V> callable) {
        super(callable);
    }

    public V getResult() throws IOException, OperationCanceledException, XMAuthericationException {
        return internalGetResult(Long.valueOf(DEFAULT_TIMEOUT_MINUTE), TimeUnit.MINUTES);
    }

    public V getResult(long j, TimeUnit timeUnit) throws IOException, OperationCanceledException, XMAuthericationException {
        return internalGetResult(Long.valueOf(j), timeUnit);
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x005a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private V internalGetResult(java.lang.Long r4, java.util.concurrent.TimeUnit r5) throws android.accounts.OperationCanceledException, java.io.IOException, com.xiaomi.account.openauth.XMAuthericationException {
        /*
            r3 = this;
            boolean r0 = r3.isDone()
            if (r0 != 0) goto L_0x0009
            r3.ensureNotOnMainThread()
        L_0x0009:
            r0 = 1
            if (r4 != 0) goto L_0x0018
            java.lang.Object r4 = r3.get()     // Catch:{ CancellationException -> 0x005a, InterruptedException | TimeoutException -> 0x0051, ExecutionException -> 0x0016 }
            r3.cancel(r0)
            return r4
        L_0x0014:
            r4 = move-exception
            goto L_0x0060
        L_0x0016:
            r4 = move-exception
            goto L_0x0024
        L_0x0018:
            long r1 = r4.longValue()     // Catch:{ CancellationException -> 0x005a, InterruptedException | TimeoutException -> 0x0051, ExecutionException -> 0x0016 }
            java.lang.Object r4 = r3.get(r1, r5)     // Catch:{ CancellationException -> 0x005a, InterruptedException | TimeoutException -> 0x0051, ExecutionException -> 0x0016 }
            r3.cancel(r0)
            return r4
        L_0x0024:
            java.lang.Throwable r4 = r4.getCause()     // Catch:{ all -> 0x0014 }
            boolean r5 = r4 instanceof java.io.IOException     // Catch:{ all -> 0x0014 }
            if (r5 != 0) goto L_0x004e
            boolean r5 = r4 instanceof java.lang.RuntimeException     // Catch:{ all -> 0x0014 }
            if (r5 != 0) goto L_0x004b
            boolean r5 = r4 instanceof java.lang.Error     // Catch:{ all -> 0x0014 }
            if (r5 != 0) goto L_0x0048
            boolean r5 = r4 instanceof com.xiaomi.account.openauth.XMAuthericationException     // Catch:{ all -> 0x0014 }
            if (r5 != 0) goto L_0x0045
            boolean r5 = r4 instanceof android.accounts.OperationCanceledException     // Catch:{ all -> 0x0014 }
            if (r5 == 0) goto L_0x003f
            android.accounts.OperationCanceledException r4 = (android.accounts.OperationCanceledException) r4     // Catch:{ all -> 0x0014 }
            throw r4     // Catch:{ all -> 0x0014 }
        L_0x003f:
            com.xiaomi.account.openauth.XMAuthericationException r5 = new com.xiaomi.account.openauth.XMAuthericationException     // Catch:{ all -> 0x0014 }
            r5.<init>((java.lang.Throwable) r4)     // Catch:{ all -> 0x0014 }
            throw r5     // Catch:{ all -> 0x0014 }
        L_0x0045:
            com.xiaomi.account.openauth.XMAuthericationException r4 = (com.xiaomi.account.openauth.XMAuthericationException) r4     // Catch:{ all -> 0x0014 }
            throw r4     // Catch:{ all -> 0x0014 }
        L_0x0048:
            java.lang.Error r4 = (java.lang.Error) r4     // Catch:{ all -> 0x0014 }
            throw r4     // Catch:{ all -> 0x0014 }
        L_0x004b:
            java.lang.RuntimeException r4 = (java.lang.RuntimeException) r4     // Catch:{ all -> 0x0014 }
            throw r4     // Catch:{ all -> 0x0014 }
        L_0x004e:
            java.io.IOException r4 = (java.io.IOException) r4     // Catch:{ all -> 0x0014 }
            throw r4     // Catch:{ all -> 0x0014 }
        L_0x0051:
            r3.cancel(r0)
            android.accounts.OperationCanceledException r4 = new android.accounts.OperationCanceledException
            r4.<init>()
            throw r4
        L_0x005a:
            android.accounts.OperationCanceledException r4 = new android.accounts.OperationCanceledException     // Catch:{ all -> 0x0014 }
            r4.<init>()     // Catch:{ all -> 0x0014 }
            throw r4     // Catch:{ all -> 0x0014 }
        L_0x0060:
            r3.cancel(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.account.auth.XiaomiOAuthFutureImpl.internalGetResult(java.lang.Long, java.util.concurrent.TimeUnit):java.lang.Object");
    }
}
