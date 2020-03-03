package com.hannesdorfmann.mosby3.mvi;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.Subject;

class DisposableIntentObserver<I> extends DisposableObserver<I> {

    /* renamed from: a  reason: collision with root package name */
    private final Subject<I> f5748a;

    public DisposableIntentObserver(Subject<I> subject) {
        this.f5748a = subject;
    }

    public void onNext(I i) {
        this.f5748a.onNext(i);
    }

    public void onError(Throwable th) {
        throw new IllegalStateException("View intents must not throw errors", th);
    }

    public void onComplete() {
        this.f5748a.onComplete();
    }
}
