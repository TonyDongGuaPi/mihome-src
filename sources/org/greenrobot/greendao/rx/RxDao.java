package org.greenrobot.greendao.rx;

import java.util.List;
import java.util.concurrent.Callable;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.annotation.apihint.Experimental;
import rx.Observable;
import rx.Scheduler;

@Experimental
public class RxDao<T, K> extends RxBase {
    /* access modifiers changed from: private */
    public final AbstractDao<T, K> b;

    @Experimental
    public /* bridge */ /* synthetic */ Scheduler a() {
        return super.a();
    }

    @Experimental
    public RxDao(AbstractDao<T, K> abstractDao) {
        this(abstractDao, (Scheduler) null);
    }

    @Experimental
    public RxDao(AbstractDao<T, K> abstractDao, Scheduler scheduler) {
        super(scheduler);
        this.b = abstractDao;
    }

    @Experimental
    public Observable<List<T>> b() {
        return a(new Callable<List<T>>() {
            /* renamed from: a */
            public List<T> call() throws Exception {
                return RxDao.this.b.loadAll();
            }
        });
    }

    @Experimental
    public Observable<T> a(final K k) {
        return a(new Callable<T>() {
            public T call() throws Exception {
                return RxDao.this.b.load(k);
            }
        });
    }

    @Experimental
    public Observable<T> b(final T t) {
        return a(new Callable<T>() {
            public T call() throws Exception {
                RxDao.this.b.refresh(t);
                return t;
            }
        });
    }

    @Experimental
    public Observable<T> c(final T t) {
        return a(new Callable<T>() {
            public T call() throws Exception {
                RxDao.this.b.insert(t);
                return t;
            }
        });
    }

    @Experimental
    public Observable<Iterable<T>> a(final Iterable<T> iterable) {
        return a(new Callable<Iterable<T>>() {
            /* renamed from: a */
            public Iterable<T> call() throws Exception {
                RxDao.this.b.insertInTx(iterable);
                return iterable;
            }
        });
    }

    @Experimental
    public Observable<Object[]> a(final T... tArr) {
        return a(new Callable<Object[]>() {
            /* renamed from: a */
            public Object[] call() throws Exception {
                RxDao.this.b.insertInTx((T[]) tArr);
                return tArr;
            }
        });
    }

    @Experimental
    public Observable<T> d(final T t) {
        return a(new Callable<T>() {
            public T call() throws Exception {
                RxDao.this.b.insertOrReplace(t);
                return t;
            }
        });
    }

    @Experimental
    public Observable<Iterable<T>> b(final Iterable<T> iterable) {
        return a(new Callable<Iterable<T>>() {
            /* renamed from: a */
            public Iterable<T> call() throws Exception {
                RxDao.this.b.insertOrReplaceInTx(iterable);
                return iterable;
            }
        });
    }

    @Experimental
    public Observable<Object[]> b(final T... tArr) {
        return a(new Callable<Object[]>() {
            /* renamed from: a */
            public Object[] call() throws Exception {
                RxDao.this.b.insertOrReplaceInTx((T[]) tArr);
                return tArr;
            }
        });
    }

    @Experimental
    public Observable<T> e(final T t) {
        return a(new Callable<T>() {
            public T call() throws Exception {
                RxDao.this.b.save(t);
                return t;
            }
        });
    }

    @Experimental
    public Observable<Iterable<T>> c(final Iterable<T> iterable) {
        return a(new Callable<Iterable<T>>() {
            /* renamed from: a */
            public Iterable<T> call() throws Exception {
                RxDao.this.b.saveInTx(iterable);
                return iterable;
            }
        });
    }

    @Experimental
    public Observable<Object[]> c(final T... tArr) {
        return a(new Callable<Object[]>() {
            /* renamed from: a */
            public Object[] call() throws Exception {
                RxDao.this.b.saveInTx((T[]) tArr);
                return tArr;
            }
        });
    }

    @Experimental
    public Observable<T> f(final T t) {
        return a(new Callable<T>() {
            public T call() throws Exception {
                RxDao.this.b.update(t);
                return t;
            }
        });
    }

    @Experimental
    public Observable<Iterable<T>> d(final Iterable<T> iterable) {
        return a(new Callable<Iterable<T>>() {
            /* renamed from: a */
            public Iterable<T> call() throws Exception {
                RxDao.this.b.updateInTx(iterable);
                return iterable;
            }
        });
    }

    @Experimental
    public Observable<Object[]> d(final T... tArr) {
        return a(new Callable<Object[]>() {
            /* renamed from: a */
            public Object[] call() throws Exception {
                RxDao.this.b.updateInTx((T[]) tArr);
                return tArr;
            }
        });
    }

    @Experimental
    public Observable<Void> g(final T t) {
        return a(new Callable<Void>() {
            /* renamed from: a */
            public Void call() throws Exception {
                RxDao.this.b.delete(t);
                return null;
            }
        });
    }

    @Experimental
    public Observable<Void> h(final K k) {
        return a(new Callable<Void>() {
            /* renamed from: a */
            public Void call() throws Exception {
                RxDao.this.b.deleteByKey(k);
                return null;
            }
        });
    }

    @Experimental
    public Observable<Void> c() {
        return a(new Callable<Void>() {
            /* renamed from: a */
            public Void call() throws Exception {
                RxDao.this.b.deleteAll();
                return null;
            }
        });
    }

    @Experimental
    public Observable<Void> e(final Iterable<T> iterable) {
        return a(new Callable<Void>() {
            /* renamed from: a */
            public Void call() throws Exception {
                RxDao.this.b.deleteInTx(iterable);
                return null;
            }
        });
    }

    @Experimental
    public Observable<Void> e(final T... tArr) {
        return a(new Callable<Void>() {
            /* renamed from: a */
            public Void call() throws Exception {
                RxDao.this.b.deleteInTx((T[]) tArr);
                return null;
            }
        });
    }

    @Experimental
    public Observable<Void> f(final Iterable<K> iterable) {
        return a(new Callable<Void>() {
            /* renamed from: a */
            public Void call() throws Exception {
                RxDao.this.b.deleteByKeyInTx(iterable);
                return null;
            }
        });
    }

    @Experimental
    public Observable<Void> f(final K... kArr) {
        return a(new Callable<Void>() {
            /* renamed from: a */
            public Void call() throws Exception {
                RxDao.this.b.deleteByKeyInTx((K[]) kArr);
                return null;
            }
        });
    }

    @Experimental
    public Observable<Long> d() {
        return a(new Callable<Long>() {
            /* renamed from: a */
            public Long call() throws Exception {
                return Long.valueOf(RxDao.this.b.count());
            }
        });
    }

    @Experimental
    public AbstractDao<T, K> e() {
        return this.b;
    }
}
