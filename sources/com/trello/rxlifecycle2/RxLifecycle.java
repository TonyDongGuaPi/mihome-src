package com.trello.rxlifecycle2;

import com.trello.rxlifecycle2.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

public class RxLifecycle {
    private RxLifecycle() {
        throw new AssertionError("No instances");
    }

    @CheckReturnValue
    @Nonnull
    public static <T, R> LifecycleTransformer<T> a(@Nonnull Observable<R> observable, @Nonnull R r) {
        Preconditions.a(observable, "lifecycle == null");
        Preconditions.a(r, "event == null");
        return a(b(observable, r));
    }

    private static <R> Observable<R> b(Observable<R> observable, final R r) {
        return observable.filter(new Predicate<R>() {
            public boolean test(R r) throws Exception {
                return r.equals(r);
            }
        });
    }

    @CheckReturnValue
    @Nonnull
    public static <T, R> LifecycleTransformer<T> a(@Nonnull Observable<R> observable) {
        return new LifecycleTransformer<>(observable);
    }

    @CheckReturnValue
    @Nonnull
    public static <T, R> LifecycleTransformer<T> a(@Nonnull Observable<R> observable, @Nonnull Function<R, R> function) {
        Preconditions.a(observable, "lifecycle == null");
        Preconditions.a(function, "correspondingEvents == null");
        return a(b(observable.share(), function));
    }

    private static <R> Observable<Boolean> b(Observable<R> observable, Function<R, R> function) {
        return Observable.combineLatest(observable.take(1).map(function), observable.skip(1), new BiFunction<R, R, Boolean>() {
            /* renamed from: a */
            public Boolean apply(R r, R r2) throws Exception {
                return Boolean.valueOf(r2.equals(r));
            }
        }).onErrorReturn(Functions.f9514a).filter(Functions.b);
    }
}
