package org.greenrobot.greendao.rx;

import java.util.concurrent.Callable;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.annotation.apihint.Experimental;
import rx.Observable;
import rx.Scheduler;

@Experimental
public class RxTransaction extends RxBase {
    /* access modifiers changed from: private */
    public final AbstractDaoSession b;

    @Experimental
    public /* bridge */ /* synthetic */ Scheduler a() {
        return super.a();
    }

    public RxTransaction(AbstractDaoSession abstractDaoSession) {
        this.b = abstractDaoSession;
    }

    public RxTransaction(AbstractDaoSession abstractDaoSession, Scheduler scheduler) {
        super(scheduler);
        this.b = abstractDaoSession;
    }

    @Experimental
    public Observable<Void> a(final Runnable runnable) {
        return a(new Callable<Void>() {
            /* renamed from: a */
            public Void call() throws Exception {
                RxTransaction.this.b.runInTx(runnable);
                return null;
            }
        });
    }

    @Experimental
    public <T> Observable<T> b(final Callable<T> callable) {
        return a(new Callable<T>() {
            public T call() throws Exception {
                return RxTransaction.this.b.callInTx(callable);
            }
        });
    }

    @Experimental
    public AbstractDaoSession b() {
        return this.b;
    }
}
