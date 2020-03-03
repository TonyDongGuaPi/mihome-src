package org.greenrobot.greendao.rx;

import java.util.concurrent.Callable;
import org.greenrobot.greendao.annotation.apihint.Experimental;
import org.greenrobot.greendao.annotation.apihint.Internal;
import rx.Observable;
import rx.Scheduler;

@Internal
class RxBase {

    /* renamed from: a  reason: collision with root package name */
    protected final Scheduler f3541a;

    RxBase() {
        this.f3541a = null;
    }

    @Experimental
    RxBase(Scheduler scheduler) {
        this.f3541a = scheduler;
    }

    @Experimental
    public Scheduler a() {
        return this.f3541a;
    }

    /* access modifiers changed from: protected */
    public <R> Observable<R> a(Callable<R> callable) {
        return a(RxUtils.a(callable));
    }

    /* access modifiers changed from: protected */
    public <R> Observable<R> a(Observable<R> observable) {
        return this.f3541a != null ? observable.subscribeOn(this.f3541a) : observable;
    }
}
