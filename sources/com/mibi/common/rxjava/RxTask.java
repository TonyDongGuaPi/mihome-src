package com.mibi.common.rxjava;

import com.mibi.common.exception.PaymentException;
import rx.Observable;
import rx.Subscriber;

public abstract class RxTask<R> implements Observable.OnSubscribe<R> {

    /* renamed from: a  reason: collision with root package name */
    private Class<R> f7588a;

    /* access modifiers changed from: protected */
    public abstract void a(R r) throws PaymentException;

    public RxTask(Class<R> cls) {
        this.f7588a = cls;
        if (this.f7588a != null && Void.class.equals(this.f7588a)) {
            throw new IllegalArgumentException();
        }
    }

    public Observable<R> f() {
        return Observable.create(this);
    }

    /* renamed from: a */
    public void call(Subscriber<? super R> subscriber) {
        try {
            R newInstance = this.f7588a.newInstance();
            a(newInstance);
            subscriber.onNext(newInstance);
            subscriber.onCompleted();
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        } catch (IllegalAccessException e2) {
            throw new IllegalStateException(e2);
        } catch (Exception e3) {
            subscriber.onError(e3);
        }
    }
}
