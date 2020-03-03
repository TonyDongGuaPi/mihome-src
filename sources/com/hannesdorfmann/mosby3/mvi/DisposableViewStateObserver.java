package com.hannesdorfmann.mosby3.mvi;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.BehaviorSubject;

class DisposableViewStateObserver<VS> extends DisposableObserver<VS> {

    /* renamed from: a  reason: collision with root package name */
    private final BehaviorSubject<VS> f5749a;

    public void onComplete() {
    }

    public DisposableViewStateObserver(BehaviorSubject<VS> behaviorSubject) {
        this.f5749a = behaviorSubject;
    }

    public void onNext(VS vs) {
        this.f5749a.onNext(vs);
    }

    public void onError(Throwable th) {
        throw new IllegalStateException("ViewState observable must not reach error state - onError()", th);
    }
}
