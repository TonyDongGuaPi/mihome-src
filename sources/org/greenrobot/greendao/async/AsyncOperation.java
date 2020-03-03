package org.greenrobot.greendao.async;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.database.Database;

public class AsyncOperation {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3516a = 1;
    public static final int b = 2;
    public static final int c = 4;
    final OperationType d;
    final AbstractDao<Object, Object> e;
    final Object f;
    final int g;
    volatile long h;
    volatile long i;
    volatile Throwable j;
    final Exception k;
    volatile Object l;
    volatile int m;
    int n;
    private final Database o;
    private volatile boolean p;

    public enum OperationType {
        Insert,
        InsertInTxIterable,
        InsertInTxArray,
        InsertOrReplace,
        InsertOrReplaceInTxIterable,
        InsertOrReplaceInTxArray,
        Update,
        UpdateInTxIterable,
        UpdateInTxArray,
        Delete,
        DeleteInTxIterable,
        DeleteInTxArray,
        DeleteByKey,
        DeleteAll,
        TransactionRunnable,
        TransactionCallable,
        QueryList,
        QueryUnique,
        Load,
        LoadAll,
        Count,
        Refresh
    }

    AsyncOperation(OperationType operationType, AbstractDao<?, ?> abstractDao, Database database, Object obj, int i2) {
        this.d = operationType;
        this.g = i2;
        this.e = abstractDao;
        this.o = database;
        this.f = obj;
        this.k = (i2 & 4) != 0 ? new Exception("AsyncOperation was created here") : null;
    }

    public Throwable a() {
        return this.j;
    }

    public void a(Throwable th) {
        this.j = th;
    }

    public OperationType b() {
        return this.d;
    }

    public Object c() {
        return this.f;
    }

    public synchronized Object d() {
        if (!this.p) {
            l();
        }
        if (this.j == null) {
        } else {
            throw new AsyncDaoException(this, this.j);
        }
        return this.l;
    }

    public boolean e() {
        return (this.g & 1) != 0;
    }

    /* access modifiers changed from: package-private */
    public Database f() {
        return this.o != null ? this.o : this.e.getDatabase();
    }

    /* access modifiers changed from: package-private */
    public boolean a(AsyncOperation asyncOperation) {
        return asyncOperation != null && e() && asyncOperation.e() && f() == asyncOperation.f();
    }

    public long g() {
        return this.h;
    }

    public long h() {
        return this.i;
    }

    public long i() {
        if (this.i != 0) {
            return this.i - this.h;
        }
        throw new DaoException("This operation did not yet complete");
    }

    public boolean j() {
        return this.j != null;
    }

    public boolean k() {
        return this.p;
    }

    public synchronized Object l() {
        while (!this.p) {
            try {
                wait();
            } catch (InterruptedException e2) {
                throw new DaoException("Interrupted while waiting for operation to complete", e2);
            }
        }
        return this.l;
    }

    public synchronized boolean a(int i2) {
        if (!this.p) {
            try {
                wait((long) i2);
            } catch (InterruptedException e2) {
                throw new DaoException("Interrupted while waiting for operation to complete", e2);
            }
        }
        return this.p;
    }

    /* access modifiers changed from: package-private */
    public synchronized void m() {
        this.p = true;
        notifyAll();
    }

    public boolean n() {
        return this.p && this.j == null;
    }

    public int o() {
        return this.m;
    }

    public int p() {
        return this.n;
    }

    /* access modifiers changed from: package-private */
    public void q() {
        this.h = 0;
        this.i = 0;
        this.p = false;
        this.j = null;
        this.l = null;
        this.m = 0;
    }

    public Exception r() {
        return this.k;
    }
}
