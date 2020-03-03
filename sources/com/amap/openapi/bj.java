package com.amap.openapi;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.amap.location.common.util.f;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class bj {

    /* renamed from: a  reason: collision with root package name */
    private int f4631a = 0;
    private ReentrantReadWriteLock b = new ReentrantReadWriteLock();
    private int c = 0;
    /* access modifiers changed from: private */
    public Context d;
    private Handler e;
    private a f;
    private long g;
    private b h;
    private int i;
    private Executor j;
    private Handler.Callback k = new Handler.Callback() {
        public boolean handleMessage(Message message) {
            try {
                return bj.this.a(message);
            } catch (Exception unused) {
                bj.this.e();
                return true;
            }
        }
    };

    public interface a {
        Object a(long j);

        void a();

        void a(int i);

        void a(int i, Object obj);

        boolean a(Object obj);

        void b();

        void b(Object obj);

        boolean b(int i);

        long c();

        long c(int i);

        int d();

        long d(int i);

        long e();

        int f();

        void g();

        Executor h();
    }

    static class b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<a> f4634a;
        WeakReference<bj> b;
        volatile boolean c;
        Object d;
        final int e;

        b(bj bjVar, a aVar, Object obj, int i) {
            this.b = new WeakReference<>(bjVar);
            this.f4634a = new WeakReference<>(aVar);
            this.d = obj;
            this.e = i;
        }

        public void a() {
            this.c = true;
        }

        public void run() {
            boolean z;
            if (!this.c) {
                bj bjVar = (bj) this.b.get();
                a aVar = (a) this.f4634a.get();
                if (bjVar != null && aVar != null) {
                    if (f.a(bjVar.d) < this.e) {
                        bjVar.a(this, false);
                        return;
                    }
                    try {
                        z = aVar.a(this.d);
                    } catch (Throwable unused) {
                        z = false;
                    }
                    if (!this.c) {
                        bjVar.a(this, z);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(b bVar, boolean z) {
        try {
            this.b.readLock().lock();
            if (this.e != null) {
                this.e.obtainMessage(z ? 103 : 104, bVar).sendToTarget();
            }
        } finally {
            this.b.readLock().unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00c5, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00cf, code lost:
        c();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00d2, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(android.os.Message r7) {
        /*
            r6 = this;
            int r0 = r7.what
            r1 = 102(0x66, float:1.43E-43)
            r2 = 0
            r3 = 0
            r4 = 1
            switch(r0) {
                case 101: goto L_0x00c9;
                case 102: goto L_0x00a6;
                case 103: goto L_0x005e;
                case 104: goto L_0x0018;
                case 105: goto L_0x0011;
                case 106: goto L_0x000c;
                default: goto L_0x000a;
            }
        L_0x000a:
            goto L_0x00d2
        L_0x000c:
            r6.b()
            goto L_0x00d2
        L_0x0011:
            com.amap.openapi.bj$a r7 = r6.f
            r7.a()
            goto L_0x00d2
        L_0x0018:
            java.lang.Object r7 = r7.obj
            com.amap.openapi.bj$b r0 = r6.h
            if (r7 != r0) goto L_0x00d2
            com.amap.openapi.bj$a r7 = r6.f
            com.amap.openapi.bj$b r0 = r6.h
            int r0 = r0.e
            com.amap.openapi.bj$b r5 = r6.h
            java.lang.Object r5 = r5.d
            r7.a(r0, r5)
            r6.h = r3
            java.util.concurrent.locks.ReentrantReadWriteLock r7 = r6.b     // Catch:{ all -> 0x0053 }
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r7 = r7.readLock()     // Catch:{ all -> 0x0053 }
            r7.lock()     // Catch:{ all -> 0x0053 }
            android.os.Handler r7 = r6.e     // Catch:{ all -> 0x0053 }
            if (r7 == 0) goto L_0x003f
            android.os.Handler r7 = r6.e     // Catch:{ all -> 0x0053 }
            r7.removeMessages(r1)     // Catch:{ all -> 0x0053 }
        L_0x003f:
            java.util.concurrent.locks.ReentrantReadWriteLock r7 = r6.b
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r7 = r7.readLock()
            r7.unlock()
            int r7 = r6.i
            int r7 = r7 + r4
            r6.i = r7
            com.amap.openapi.bj$a r7 = r6.f
            r7.a((int) r2)
            goto L_0x00c5
        L_0x0053:
            r7 = move-exception
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r6.b
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r0 = r0.readLock()
            r0.unlock()
            throw r7
        L_0x005e:
            java.lang.Object r0 = r7.obj
            com.amap.openapi.bj$b r0 = (com.amap.openapi.bj.b) r0
            java.lang.Object r7 = r7.obj
            com.amap.openapi.bj$b r2 = r6.h
            if (r7 != r2) goto L_0x00d2
            r6.h = r3
            java.util.concurrent.locks.ReentrantReadWriteLock r7 = r6.b     // Catch:{ all -> 0x009b }
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r7 = r7.readLock()     // Catch:{ all -> 0x009b }
            r7.lock()     // Catch:{ all -> 0x009b }
            android.os.Handler r7 = r6.e     // Catch:{ all -> 0x009b }
            if (r7 == 0) goto L_0x007c
            android.os.Handler r7 = r6.e     // Catch:{ all -> 0x009b }
            r7.removeMessages(r1)     // Catch:{ all -> 0x009b }
        L_0x007c:
            java.util.concurrent.locks.ReentrantReadWriteLock r7 = r6.b
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r7 = r7.readLock()
            r7.unlock()
            com.amap.openapi.bj$a r7 = r6.f
            int r1 = r0.e
            java.lang.Object r2 = r0.d
            r7.a(r1, r2)
            com.amap.openapi.bj$a r7 = r6.f
            java.lang.Object r0 = r0.d
            r7.b((java.lang.Object) r0)
            com.amap.openapi.bj$a r7 = r6.f
            r7.a((int) r4)
            goto L_0x00cf
        L_0x009b:
            r7 = move-exception
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r6.b
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r0 = r0.readLock()
            r0.unlock()
            throw r7
        L_0x00a6:
            com.amap.openapi.bj$a r7 = r6.f
            com.amap.openapi.bj$b r0 = r6.h
            int r0 = r0.e
            com.amap.openapi.bj$b r1 = r6.h
            java.lang.Object r1 = r1.d
            r7.a(r0, r1)
            com.amap.openapi.bj$b r7 = r6.h
            r7.a()
            r6.h = r3
            int r7 = r6.i
            int r7 = r7 + r4
            r6.i = r7
            com.amap.openapi.bj$a r7 = r6.f
            r0 = -1
            r7.a((int) r0)
        L_0x00c5:
            r6.e()
            goto L_0x00d2
        L_0x00c9:
            int r7 = r7.arg1
            if (r7 != r4) goto L_0x00cf
            r6.i = r2
        L_0x00cf:
            r6.c()
        L_0x00d2:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.bj.a(android.os.Message):boolean");
    }

    private void b() {
        this.f.b();
        if (this.h != null) {
            this.h.a();
        }
        if (this.c == 2) {
            ((ExecutorService) this.j).shutdown();
        }
        this.j = null;
        this.f = null;
        this.h = null;
    }

    private void c() {
        if (this.h == null) {
            int a2 = f.a(this.d);
            if (a2 == -1) {
                e();
            } else if (!this.f.b(a2)) {
                e();
            } else {
                long c2 = this.f.c(a2);
                if (c2 <= 0) {
                    e();
                    return;
                }
                long c3 = this.f.c();
                if (c3 <= 0) {
                    e();
                    return;
                }
                long min = Math.min(this.f.d(a2), c2);
                if (c3 >= min || SystemClock.elapsedRealtime() - this.g >= this.f.e()) {
                    Object a3 = this.f.a(min);
                    if (a3 == null) {
                        e();
                        return;
                    }
                    this.g = SystemClock.elapsedRealtime();
                    if (a2 != f.a(this.d)) {
                        this.f.g();
                        e();
                        return;
                    }
                    try {
                        this.b.readLock().lock();
                        if (this.e != null) {
                            this.h = new b(this, this.f, a3, a2);
                            d().execute(this.h);
                            this.e.sendEmptyMessageDelayed(102, (long) this.f.f());
                        }
                    } catch (Throwable unused) {
                    } finally {
                        this.b.readLock().unlock();
                    }
                } else {
                    e();
                }
            }
        }
    }

    private Executor d() {
        if (this.j != null) {
            return this.j;
        }
        Executor h2 = this.f.h();
        if (h2 != null) {
            this.c = 1;
            this.j = h2;
        } else {
            this.j = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(1024), new ThreadFactory() {
                public Thread newThread(Runnable runnable) {
                    return new Thread(runnable, "UploadController");
                }
            });
            this.c = 2;
        }
        return this.j;
    }

    /* access modifiers changed from: private */
    public void e() {
        try {
            this.b.readLock().lock();
            if (this.e != null && ((this.f.d() <= 0 || this.i < this.f.d()) && !this.e.hasMessages(101))) {
                this.e.sendMessageDelayed(this.e.obtainMessage(101, 0, 0), this.f.e());
            }
        } finally {
            this.b.readLock().unlock();
        }
    }

    public void a() {
        try {
            this.b.writeLock().lock();
            if (this.f4631a == 1) {
                this.f4631a = 2;
                this.e.removeCallbacksAndMessages((Object) null);
                if (this.e.getLooper() == Looper.myLooper()) {
                    b();
                } else {
                    this.e.sendEmptyMessage(106);
                }
                this.e = null;
            }
        } finally {
            this.b.writeLock().unlock();
        }
    }

    public void a(long j2) {
        try {
            this.b.readLock().lock();
            if (this.e != null) {
                this.e.removeMessages(101);
                this.e.sendMessageDelayed(this.e.obtainMessage(101, 1, 0), Math.max(0, j2));
            }
        } finally {
            this.b.readLock().unlock();
        }
    }

    public void a(Context context, a aVar, Looper looper) {
        if (context == null || aVar == null || looper == null) {
            throw new RuntimeException("params not be null!");
        }
        try {
            this.b.writeLock().lock();
            if (this.f4631a == 0) {
                this.d = context;
                this.f = aVar;
                this.e = new Handler(looper, this.k);
                if (Looper.myLooper() == looper) {
                    this.f.a();
                } else {
                    this.e.sendEmptyMessage(105);
                }
                this.f4631a = 1;
            }
        } finally {
            this.b.writeLock().unlock();
        }
    }
}
