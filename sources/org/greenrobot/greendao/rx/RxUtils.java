package org.greenrobot.greendao.rx;

import java.util.concurrent.Callable;
import org.greenrobot.greendao.annotation.apihint.Internal;
import rx.Observable;
import rx.functions.Func0;

@Internal
class RxUtils {
    RxUtils() {
    }

    @Internal
    static <T> Observable<T> a(final Callable<T> callable) {
        return Observable.defer(new Func0<Observable<T>>() {
            /* renamed from: a */
            public Observable<T> call() {
                try {
                    return Observable.just(callable.call());
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }
}
