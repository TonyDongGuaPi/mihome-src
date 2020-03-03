package com.xiaomi.accountsdk.futureservice;

import android.os.AsyncTask;
import com.xiaomi.accountsdk.futureservice.ClientFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public abstract class MiAccountManagerFuture<V> extends ClientFuture<V, V> {
    private static Executor executor = AsyncTask.THREAD_POOL_EXECUTOR;

    /* access modifiers changed from: protected */
    public V convertServerDataToClientData(V v) throws Throwable {
        return v;
    }

    public abstract V doWork();

    protected MiAccountManagerFuture() {
        super((ClientFuture.ClientCallback) null);
    }

    public void interpretExecutionException(ExecutionException executionException) throws Exception {
        throw new IllegalStateException("not going here");
    }

    public MiAccountManagerFuture<V> start() {
        executor.execute(new Runnable() {
            public void run() {
                try {
                    MiAccountManagerFuture.this.setServerData(MiAccountManagerFuture.this.doWork());
                } catch (Throwable th) {
                    MiAccountManagerFuture.this.setServerSideThrowable(th);
                }
            }
        });
        return this;
    }
}
