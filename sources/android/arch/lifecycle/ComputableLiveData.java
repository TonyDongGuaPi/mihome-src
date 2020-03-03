package android.arch.lifecycle;

import android.arch.core.executor.ArchTaskExecutor;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class ComputableLiveData<T> {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    final Runnable f434a;
    @VisibleForTesting
    final Runnable b;
    /* access modifiers changed from: private */
    public final Executor c;
    /* access modifiers changed from: private */
    public final LiveData<T> d;
    /* access modifiers changed from: private */
    public AtomicBoolean e;
    /* access modifiers changed from: private */
    public AtomicBoolean f;

    /* access modifiers changed from: protected */
    @WorkerThread
    public abstract T c();

    public ComputableLiveData() {
        this(ArchTaskExecutor.c());
    }

    public ComputableLiveData(@NonNull Executor executor) {
        this.e = new AtomicBoolean(true);
        this.f = new AtomicBoolean(false);
        this.f434a = new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
            @android.support.annotation.WorkerThread
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r5 = this;
                L_0x0000:
                    android.arch.lifecycle.ComputableLiveData r0 = android.arch.lifecycle.ComputableLiveData.this
                    java.util.concurrent.atomic.AtomicBoolean r0 = r0.f
                    r1 = 1
                    r2 = 0
                    boolean r0 = r0.compareAndSet(r2, r1)
                    if (r0 == 0) goto L_0x0045
                    r0 = 0
                    r3 = r0
                    r0 = 0
                L_0x0011:
                    android.arch.lifecycle.ComputableLiveData r4 = android.arch.lifecycle.ComputableLiveData.this     // Catch:{ all -> 0x003a }
                    java.util.concurrent.atomic.AtomicBoolean r4 = r4.e     // Catch:{ all -> 0x003a }
                    boolean r4 = r4.compareAndSet(r1, r2)     // Catch:{ all -> 0x003a }
                    if (r4 == 0) goto L_0x0025
                    android.arch.lifecycle.ComputableLiveData r0 = android.arch.lifecycle.ComputableLiveData.this     // Catch:{ all -> 0x003a }
                    java.lang.Object r3 = r0.c()     // Catch:{ all -> 0x003a }
                    r0 = 1
                    goto L_0x0011
                L_0x0025:
                    if (r0 == 0) goto L_0x0030
                    android.arch.lifecycle.ComputableLiveData r1 = android.arch.lifecycle.ComputableLiveData.this     // Catch:{ all -> 0x003a }
                    android.arch.lifecycle.LiveData r1 = r1.d     // Catch:{ all -> 0x003a }
                    r1.postValue(r3)     // Catch:{ all -> 0x003a }
                L_0x0030:
                    android.arch.lifecycle.ComputableLiveData r1 = android.arch.lifecycle.ComputableLiveData.this
                    java.util.concurrent.atomic.AtomicBoolean r1 = r1.f
                    r1.set(r2)
                    goto L_0x0046
                L_0x003a:
                    r0 = move-exception
                    android.arch.lifecycle.ComputableLiveData r1 = android.arch.lifecycle.ComputableLiveData.this
                    java.util.concurrent.atomic.AtomicBoolean r1 = r1.f
                    r1.set(r2)
                    throw r0
                L_0x0045:
                    r0 = 0
                L_0x0046:
                    if (r0 == 0) goto L_0x0054
                    android.arch.lifecycle.ComputableLiveData r0 = android.arch.lifecycle.ComputableLiveData.this
                    java.util.concurrent.atomic.AtomicBoolean r0 = r0.e
                    boolean r0 = r0.get()
                    if (r0 != 0) goto L_0x0000
                L_0x0054:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: android.arch.lifecycle.ComputableLiveData.AnonymousClass2.run():void");
            }
        };
        this.b = new Runnable() {
            @MainThread
            public void run() {
                boolean hasActiveObservers = ComputableLiveData.this.d.hasActiveObservers();
                if (ComputableLiveData.this.e.compareAndSet(false, true) && hasActiveObservers) {
                    ComputableLiveData.this.c.execute(ComputableLiveData.this.f434a);
                }
            }
        };
        this.c = executor;
        this.d = new LiveData<T>() {
            /* access modifiers changed from: protected */
            public void onActive() {
                ComputableLiveData.this.c.execute(ComputableLiveData.this.f434a);
            }
        };
    }

    @NonNull
    public LiveData<T> a() {
        return this.d;
    }

    public void b() {
        ArchTaskExecutor.a().c(this.b);
    }
}
