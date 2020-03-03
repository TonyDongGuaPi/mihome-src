package com.trello.rxlifecycle2;

import io.reactivex.Completable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.concurrent.CancellationException;

final class Functions {

    /* renamed from: a  reason: collision with root package name */
    static final Function<Throwable, Boolean> f9514a = new Function<Throwable, Boolean>() {
        /* renamed from: a */
        public Boolean apply(Throwable th) throws Exception {
            if (th instanceof OutsideLifecycleException) {
                return true;
            }
            Exceptions.propagate(th);
            return false;
        }
    };
    static final Predicate<Boolean> b = new Predicate<Boolean>() {
        /* renamed from: a */
        public boolean test(Boolean bool) throws Exception {
            return bool.booleanValue();
        }
    };
    static final Function<Object, Completable> c = new Function<Object, Completable>() {
        /* renamed from: a */
        public Completable apply(Object obj) throws Exception {
            return Completable.error((Throwable) new CancellationException());
        }
    };

    private Functions() {
        throw new AssertionError("No instances!");
    }
}
