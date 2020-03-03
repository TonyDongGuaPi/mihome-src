package com.trello.rxlifecycle2;

import com.taobao.weex.el.parse.Operators;
import com.trello.rxlifecycle2.internal.Preconditions;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import javax.annotation.ParametersAreNonnullByDefault;
import org.reactivestreams.Publisher;

@ParametersAreNonnullByDefault
public final class LifecycleTransformer<T> implements CompletableTransformer, FlowableTransformer<T, T>, MaybeTransformer<T, T>, ObservableTransformer<T, T>, SingleTransformer<T, T> {

    /* renamed from: a  reason: collision with root package name */
    final Observable<?> f9515a;

    LifecycleTransformer(Observable<?> observable) {
        Preconditions.a(observable, "observable == null");
        this.f9515a = observable;
    }

    public ObservableSource<T> apply(Observable<T> observable) {
        return observable.takeUntil((ObservableSource<U>) this.f9515a);
    }

    public Publisher<T> apply(Flowable<T> flowable) {
        return flowable.takeUntil((Publisher<U>) this.f9515a.toFlowable(BackpressureStrategy.LATEST));
    }

    public SingleSource<T> apply(Single<T> single) {
        return single.takeUntil((SingleSource<? extends E>) this.f9515a.firstOrError());
    }

    public MaybeSource<T> apply(Maybe<T> maybe) {
        return maybe.takeUntil((MaybeSource<U>) this.f9515a.firstElement());
    }

    public CompletableSource apply(Completable completable) {
        return Completable.ambArray(completable, this.f9515a.flatMapCompletable(Functions.c));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f9515a.equals(((LifecycleTransformer) obj).f9515a);
    }

    public int hashCode() {
        return this.f9515a.hashCode();
    }

    public String toString() {
        return "LifecycleTransformer{observable=" + this.f9515a + Operators.BLOCK_END;
    }
}
