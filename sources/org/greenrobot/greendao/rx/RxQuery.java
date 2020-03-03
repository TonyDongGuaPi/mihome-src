package org.greenrobot.greendao.rx;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import org.greenrobot.greendao.annotation.apihint.Experimental;
import org.greenrobot.greendao.query.LazyList;
import org.greenrobot.greendao.query.Query;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.exceptions.Exceptions;

@Experimental
public class RxQuery<T> extends RxBase {
    /* access modifiers changed from: private */
    public final Query<T> b;

    @Experimental
    public /* bridge */ /* synthetic */ Scheduler a() {
        return super.a();
    }

    public RxQuery(Query<T> query) {
        this.b = query;
    }

    public RxQuery(Query<T> query, Scheduler scheduler) {
        super(scheduler);
        this.b = query;
    }

    @Experimental
    public Observable<List<T>> b() {
        return a(new Callable<List<T>>() {
            /* renamed from: a */
            public List<T> call() throws Exception {
                return RxQuery.this.b.b().c();
            }
        });
    }

    @Experimental
    public Observable<T> c() {
        return a(new Callable<T>() {
            public T call() throws Exception {
                return RxQuery.this.b.b().g();
            }
        });
    }

    public Observable<T> d() {
        return a(Observable.create(new Observable.OnSubscribe<T>() {
            /* renamed from: a */
            public void call(Subscriber<? super T> subscriber) {
                LazyList e;
                try {
                    e = RxQuery.this.b.b().e();
                    Iterator it = e.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            Object next = it.next();
                            if (subscriber.isUnsubscribed()) {
                                break;
                            }
                            subscriber.onNext(next);
                        }
                    }
                    e.close();
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onCompleted();
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    subscriber.onError(th);
                }
            }
        }));
    }
}
