package org.greenrobot.greendao.async;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.DaoLog;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.Query;

class AsyncOperationExecutor implements Handler.Callback, Runnable {

    /* renamed from: a  reason: collision with root package name */
    private static ExecutorService f3517a = Executors.newCachedThreadPool();
    private final BlockingQueue<AsyncOperation> b = new LinkedBlockingQueue();
    private volatile boolean c;
    private volatile int d = 50;
    private volatile AsyncOperationListener e;
    private volatile AsyncOperationListener f;
    private volatile int g = 50;
    private int h;
    private int i;
    private Handler j;
    private int k;

    AsyncOperationExecutor() {
    }

    public void a(AsyncOperation asyncOperation) {
        synchronized (this) {
            int i2 = this.k + 1;
            this.k = i2;
            asyncOperation.n = i2;
            this.b.add(asyncOperation);
            this.h++;
            if (!this.c) {
                this.c = true;
                f3517a.execute(this);
            }
        }
    }

    public int a() {
        return this.d;
    }

    public void a(int i2) {
        this.d = i2;
    }

    public int b() {
        return this.g;
    }

    public void b(int i2) {
        this.g = i2;
    }

    public AsyncOperationListener c() {
        return this.e;
    }

    public void a(AsyncOperationListener asyncOperationListener) {
        this.e = asyncOperationListener;
    }

    public AsyncOperationListener d() {
        return this.f;
    }

    public void b(AsyncOperationListener asyncOperationListener) {
        this.f = asyncOperationListener;
    }

    public synchronized boolean e() {
        return this.h == this.i;
    }

    public synchronized void f() {
        while (!e()) {
            try {
                wait();
            } catch (InterruptedException e2) {
                throw new DaoException("Interrupted while waiting for all operations to complete", e2);
            }
        }
    }

    public synchronized boolean c(int i2) {
        if (!e()) {
            try {
                wait((long) i2);
            } catch (InterruptedException e2) {
                throw new DaoException("Interrupted while waiting for all operations to complete", e2);
            }
        }
        return e();
    }

    public void run() {
        AsyncOperation poll;
        while (true) {
            try {
                AsyncOperation poll2 = this.b.poll(1, TimeUnit.SECONDS);
                if (poll2 == null) {
                    synchronized (this) {
                        poll2 = (AsyncOperation) this.b.poll();
                        if (poll2 == null) {
                            this.c = false;
                            this.c = false;
                            return;
                        }
                    }
                }
                if (!poll2.e() || (poll = this.b.poll((long) this.g, TimeUnit.MILLISECONDS)) == null) {
                    c(poll2);
                } else if (poll2.a(poll)) {
                    a(poll2, poll);
                } else {
                    c(poll2);
                    c(poll);
                }
            } catch (InterruptedException e2) {
                try {
                    DaoLog.d(Thread.currentThread().getName() + " was interruppted", e2);
                    return;
                } finally {
                    this.c = false;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0061, code lost:
        r3 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(org.greenrobot.greendao.async.AsyncOperation r7, org.greenrobot.greendao.async.AsyncOperation r8) {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r0.add(r7)
            r0.add(r8)
            org.greenrobot.greendao.database.Database r7 = r7.f()
            r7.a()
            r8 = 0
            r1 = 0
        L_0x0014:
            int r2 = r0.size()     // Catch:{ all -> 0x00b5 }
            r3 = 1
            if (r1 >= r2) goto L_0x0061
            java.lang.Object r2 = r0.get(r1)     // Catch:{ all -> 0x00b5 }
            org.greenrobot.greendao.async.AsyncOperation r2 = (org.greenrobot.greendao.async.AsyncOperation) r2     // Catch:{ all -> 0x00b5 }
            r6.d(r2)     // Catch:{ all -> 0x00b5 }
            boolean r4 = r2.j()     // Catch:{ all -> 0x00b5 }
            if (r4 == 0) goto L_0x002b
            goto L_0x0061
        L_0x002b:
            int r4 = r0.size()     // Catch:{ all -> 0x00b5 }
            int r4 = r4 - r3
            if (r1 != r4) goto L_0x005e
            java.util.concurrent.BlockingQueue<org.greenrobot.greendao.async.AsyncOperation> r4 = r6.b     // Catch:{ all -> 0x00b5 }
            java.lang.Object r4 = r4.peek()     // Catch:{ all -> 0x00b5 }
            org.greenrobot.greendao.async.AsyncOperation r4 = (org.greenrobot.greendao.async.AsyncOperation) r4     // Catch:{ all -> 0x00b5 }
            int r5 = r6.d     // Catch:{ all -> 0x00b5 }
            if (r1 >= r5) goto L_0x005a
            boolean r2 = r2.a((org.greenrobot.greendao.async.AsyncOperation) r4)     // Catch:{ all -> 0x00b5 }
            if (r2 == 0) goto L_0x005a
            java.util.concurrent.BlockingQueue<org.greenrobot.greendao.async.AsyncOperation> r2 = r6.b     // Catch:{ all -> 0x00b5 }
            java.lang.Object r2 = r2.remove()     // Catch:{ all -> 0x00b5 }
            org.greenrobot.greendao.async.AsyncOperation r2 = (org.greenrobot.greendao.async.AsyncOperation) r2     // Catch:{ all -> 0x00b5 }
            if (r2 != r4) goto L_0x0052
            r0.add(r2)     // Catch:{ all -> 0x00b5 }
            goto L_0x005e
        L_0x0052:
            org.greenrobot.greendao.DaoException r0 = new org.greenrobot.greendao.DaoException     // Catch:{ all -> 0x00b5 }
            java.lang.String r1 = "Internal error: peeked op did not match removed op"
            r0.<init>((java.lang.String) r1)     // Catch:{ all -> 0x00b5 }
            throw r0     // Catch:{ all -> 0x00b5 }
        L_0x005a:
            r7.d()     // Catch:{ all -> 0x00b5 }
            goto L_0x0062
        L_0x005e:
            int r1 = r1 + 1
            goto L_0x0014
        L_0x0061:
            r3 = 0
        L_0x0062:
            r7.b()     // Catch:{ RuntimeException -> 0x0067 }
            r8 = r3
            goto L_0x007c
        L_0x0067:
            r7 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Async transaction could not be ended, success so far was: "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            org.greenrobot.greendao.DaoLog.c(r1, r7)
        L_0x007c:
            if (r8 == 0) goto L_0x0098
            int r7 = r0.size()
            java.util.Iterator r8 = r0.iterator()
        L_0x0086:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x00b4
            java.lang.Object r0 = r8.next()
            org.greenrobot.greendao.async.AsyncOperation r0 = (org.greenrobot.greendao.async.AsyncOperation) r0
            r0.m = r7
            r6.b((org.greenrobot.greendao.async.AsyncOperation) r0)
            goto L_0x0086
        L_0x0098:
            java.lang.String r7 = "Reverted merged transaction because one of the operations failed. Executing operations one by one instead..."
            org.greenrobot.greendao.DaoLog.c(r7)
            java.util.Iterator r7 = r0.iterator()
        L_0x00a1:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x00b4
            java.lang.Object r8 = r7.next()
            org.greenrobot.greendao.async.AsyncOperation r8 = (org.greenrobot.greendao.async.AsyncOperation) r8
            r8.q()
            r6.c((org.greenrobot.greendao.async.AsyncOperation) r8)
            goto L_0x00a1
        L_0x00b4:
            return
        L_0x00b5:
            r0 = move-exception
            r7.b()     // Catch:{ RuntimeException -> 0x00ba }
            goto L_0x00cf
        L_0x00ba:
            r7 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Async transaction could not be ended, success so far was: "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            org.greenrobot.greendao.DaoLog.c(r8, r7)
        L_0x00cf:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.greenrobot.greendao.async.AsyncOperationExecutor.a(org.greenrobot.greendao.async.AsyncOperation, org.greenrobot.greendao.async.AsyncOperation):void");
    }

    private void b(AsyncOperation asyncOperation) {
        asyncOperation.m();
        AsyncOperationListener asyncOperationListener = this.e;
        if (asyncOperationListener != null) {
            asyncOperationListener.a(asyncOperation);
        }
        if (this.f != null) {
            if (this.j == null) {
                this.j = new Handler(Looper.getMainLooper(), this);
            }
            this.j.sendMessage(this.j.obtainMessage(1, asyncOperation));
        }
        synchronized (this) {
            this.i++;
            if (this.i == this.h) {
                notifyAll();
            }
        }
    }

    private void c(AsyncOperation asyncOperation) {
        d(asyncOperation);
        b(asyncOperation);
    }

    private void d(AsyncOperation asyncOperation) {
        asyncOperation.h = System.currentTimeMillis();
        try {
            switch (asyncOperation.d) {
                case Delete:
                    asyncOperation.e.delete(asyncOperation.f);
                    break;
                case DeleteInTxIterable:
                    asyncOperation.e.deleteInTx((Iterable) asyncOperation.f);
                    break;
                case DeleteInTxArray:
                    asyncOperation.e.deleteInTx((T[]) (Object[]) asyncOperation.f);
                    break;
                case Insert:
                    asyncOperation.e.insert(asyncOperation.f);
                    break;
                case InsertInTxIterable:
                    asyncOperation.e.insertInTx((Iterable) asyncOperation.f);
                    break;
                case InsertInTxArray:
                    asyncOperation.e.insertInTx((T[]) (Object[]) asyncOperation.f);
                    break;
                case InsertOrReplace:
                    asyncOperation.e.insertOrReplace(asyncOperation.f);
                    break;
                case InsertOrReplaceInTxIterable:
                    asyncOperation.e.insertOrReplaceInTx((Iterable) asyncOperation.f);
                    break;
                case InsertOrReplaceInTxArray:
                    asyncOperation.e.insertOrReplaceInTx((T[]) (Object[]) asyncOperation.f);
                    break;
                case Update:
                    asyncOperation.e.update(asyncOperation.f);
                    break;
                case UpdateInTxIterable:
                    asyncOperation.e.updateInTx((Iterable) asyncOperation.f);
                    break;
                case UpdateInTxArray:
                    asyncOperation.e.updateInTx((T[]) (Object[]) asyncOperation.f);
                    break;
                case TransactionRunnable:
                    e(asyncOperation);
                    break;
                case TransactionCallable:
                    f(asyncOperation);
                    break;
                case QueryList:
                    asyncOperation.l = ((Query) asyncOperation.f).b().c();
                    break;
                case QueryUnique:
                    asyncOperation.l = ((Query) asyncOperation.f).b().g();
                    break;
                case DeleteByKey:
                    asyncOperation.e.deleteByKey(asyncOperation.f);
                    break;
                case DeleteAll:
                    asyncOperation.e.deleteAll();
                    break;
                case Load:
                    asyncOperation.l = asyncOperation.e.load(asyncOperation.f);
                    break;
                case LoadAll:
                    asyncOperation.l = asyncOperation.e.loadAll();
                    break;
                case Count:
                    asyncOperation.l = Long.valueOf(asyncOperation.e.count());
                    break;
                case Refresh:
                    asyncOperation.e.refresh(asyncOperation.f);
                    break;
                default:
                    throw new DaoException("Unsupported operation: " + asyncOperation.d);
            }
        } catch (Throwable th) {
            asyncOperation.j = th;
        }
        asyncOperation.i = System.currentTimeMillis();
    }

    private void e(AsyncOperation asyncOperation) {
        Database f2 = asyncOperation.f();
        f2.a();
        try {
            ((Runnable) asyncOperation.f).run();
            f2.d();
        } finally {
            f2.b();
        }
    }

    private void f(AsyncOperation asyncOperation) throws Exception {
        Database f2 = asyncOperation.f();
        f2.a();
        try {
            asyncOperation.l = ((Callable) asyncOperation.f).call();
            f2.d();
        } finally {
            f2.b();
        }
    }

    public boolean handleMessage(Message message) {
        AsyncOperationListener asyncOperationListener = this.f;
        if (asyncOperationListener == null) {
            return false;
        }
        asyncOperationListener.a((AsyncOperation) message.obj);
        return false;
    }
}
