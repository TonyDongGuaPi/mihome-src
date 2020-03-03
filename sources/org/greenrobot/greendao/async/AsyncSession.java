package org.greenrobot.greendao.async;

import java.util.concurrent.Callable;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.async.AsyncOperation;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.Query;

public class AsyncSession {

    /* renamed from: a  reason: collision with root package name */
    private final AbstractDaoSession f3519a;
    private final AsyncOperationExecutor b = new AsyncOperationExecutor();
    private int c;

    public AsyncSession(AbstractDaoSession abstractDaoSession) {
        this.f3519a = abstractDaoSession;
    }

    public int a() {
        return this.b.a();
    }

    public void a(int i) {
        this.b.a(i);
    }

    public int b() {
        return this.b.b();
    }

    public void b(int i) {
        this.b.b(i);
    }

    public AsyncOperationListener c() {
        return this.b.c();
    }

    public void a(AsyncOperationListener asyncOperationListener) {
        this.b.a(asyncOperationListener);
    }

    public AsyncOperationListener d() {
        return this.b.d();
    }

    public void b(AsyncOperationListener asyncOperationListener) {
        this.b.b(asyncOperationListener);
    }

    public boolean e() {
        return this.b.e();
    }

    public void f() {
        this.b.f();
    }

    public boolean c(int i) {
        return this.b.c(i);
    }

    public AsyncOperation a(Object obj) {
        return a(obj, 0);
    }

    public AsyncOperation a(Object obj, int i) {
        return b(AsyncOperation.OperationType.Insert, obj, i);
    }

    public <E> AsyncOperation a(Class<E> cls, E... eArr) {
        return a(cls, 0, eArr);
    }

    public <E> AsyncOperation a(Class<E> cls, int i, E... eArr) {
        return a(AsyncOperation.OperationType.InsertInTxArray, cls, eArr, i);
    }

    public <E> AsyncOperation a(Class<E> cls, Iterable<E> iterable) {
        return a(cls, iterable, 0);
    }

    public <E> AsyncOperation a(Class<E> cls, Iterable<E> iterable, int i) {
        return a(AsyncOperation.OperationType.InsertInTxIterable, cls, iterable, i);
    }

    public AsyncOperation b(Object obj) {
        return b(obj, 0);
    }

    public AsyncOperation b(Object obj, int i) {
        return b(AsyncOperation.OperationType.InsertOrReplace, obj, i);
    }

    public <E> AsyncOperation b(Class<E> cls, E... eArr) {
        return b(cls, 0, eArr);
    }

    public <E> AsyncOperation b(Class<E> cls, int i, E... eArr) {
        return a(AsyncOperation.OperationType.InsertOrReplaceInTxArray, cls, eArr, i);
    }

    public <E> AsyncOperation b(Class<E> cls, Iterable<E> iterable) {
        return b(cls, iterable, 0);
    }

    public <E> AsyncOperation b(Class<E> cls, Iterable<E> iterable, int i) {
        return a(AsyncOperation.OperationType.InsertOrReplaceInTxIterable, cls, iterable, i);
    }

    public AsyncOperation c(Object obj) {
        return c(obj, 0);
    }

    public AsyncOperation c(Object obj, int i) {
        return b(AsyncOperation.OperationType.Update, obj, i);
    }

    public <E> AsyncOperation c(Class<E> cls, E... eArr) {
        return c(cls, 0, eArr);
    }

    public <E> AsyncOperation c(Class<E> cls, int i, E... eArr) {
        return a(AsyncOperation.OperationType.UpdateInTxArray, cls, eArr, i);
    }

    public <E> AsyncOperation c(Class<E> cls, Iterable<E> iterable) {
        return c(cls, iterable, 0);
    }

    public <E> AsyncOperation c(Class<E> cls, Iterable<E> iterable, int i) {
        return a(AsyncOperation.OperationType.UpdateInTxIterable, cls, iterable, i);
    }

    public AsyncOperation d(Object obj) {
        return d(obj, 0);
    }

    public AsyncOperation d(Object obj, int i) {
        return b(AsyncOperation.OperationType.Delete, obj, i);
    }

    public AsyncOperation e(Object obj) {
        return e(obj, 0);
    }

    public AsyncOperation e(Object obj, int i) {
        return b(AsyncOperation.OperationType.DeleteByKey, obj, i);
    }

    public <E> AsyncOperation d(Class<E> cls, E... eArr) {
        return d(cls, 0, eArr);
    }

    public <E> AsyncOperation d(Class<E> cls, int i, E... eArr) {
        return a(AsyncOperation.OperationType.DeleteInTxArray, cls, eArr, i);
    }

    public <E> AsyncOperation d(Class<E> cls, Iterable<E> iterable) {
        return d(cls, iterable, 0);
    }

    public <E> AsyncOperation d(Class<E> cls, Iterable<E> iterable, int i) {
        return a(AsyncOperation.OperationType.DeleteInTxIterable, cls, iterable, i);
    }

    public <E> AsyncOperation a(Class<E> cls) {
        return a(cls, 0);
    }

    public <E> AsyncOperation a(Class<E> cls, int i) {
        return a(AsyncOperation.OperationType.DeleteAll, cls, (Object) null, i);
    }

    public AsyncOperation a(Runnable runnable) {
        return a(runnable, 0);
    }

    public AsyncOperation a(Runnable runnable, int i) {
        return a(AsyncOperation.OperationType.TransactionRunnable, (Object) runnable, i);
    }

    public AsyncOperation a(Callable<?> callable) {
        return a(callable, 0);
    }

    public AsyncOperation a(Callable<?> callable, int i) {
        return a(AsyncOperation.OperationType.TransactionCallable, (Object) callable, i);
    }

    public AsyncOperation a(Query<?> query) {
        return a(query, 0);
    }

    public AsyncOperation a(Query<?> query, int i) {
        return a(AsyncOperation.OperationType.QueryList, (Object) query, i);
    }

    public AsyncOperation b(Query<?> query) {
        return b(query, 0);
    }

    public AsyncOperation b(Query<?> query, int i) {
        return a(AsyncOperation.OperationType.QueryUnique, (Object) query, i);
    }

    public AsyncOperation a(Class<?> cls, Object obj) {
        return a(cls, obj, 0);
    }

    public AsyncOperation a(Class<?> cls, Object obj, int i) {
        return a(AsyncOperation.OperationType.Load, cls, obj, i);
    }

    public AsyncOperation b(Class<?> cls) {
        return b(cls, 0);
    }

    public AsyncOperation b(Class<?> cls, int i) {
        return a(AsyncOperation.OperationType.LoadAll, cls, (Object) null, i);
    }

    public AsyncOperation c(Class<?> cls) {
        return c(cls, 0);
    }

    public AsyncOperation c(Class<?> cls, int i) {
        return a(AsyncOperation.OperationType.Count, cls, (Object) null, i);
    }

    public AsyncOperation f(Object obj) {
        return f(obj, 0);
    }

    public AsyncOperation f(Object obj, int i) {
        return b(AsyncOperation.OperationType.Refresh, obj, i);
    }

    private AsyncOperation a(AsyncOperation.OperationType operationType, Object obj, int i) {
        AsyncOperation asyncOperation = new AsyncOperation(operationType, (AbstractDao<?, ?>) null, this.f3519a.getDatabase(), obj, i | this.c);
        this.b.a(asyncOperation);
        return asyncOperation;
    }

    private AsyncOperation b(AsyncOperation.OperationType operationType, Object obj, int i) {
        return a(operationType, obj.getClass(), obj, i);
    }

    private <E> AsyncOperation a(AsyncOperation.OperationType operationType, Class<E> cls, Object obj, int i) {
        AsyncOperation asyncOperation = new AsyncOperation(operationType, this.f3519a.getDao(cls), (Database) null, obj, i | this.c);
        this.b.a(asyncOperation);
        return asyncOperation;
    }

    public int g() {
        return this.c;
    }

    public void d(int i) {
        this.c = i;
    }
}
