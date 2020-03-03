package com.xiaomi.payment.task.rxjava;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxCountDown {

    public interface ICountDownListener {
        void a();

        void a(long j);

        void b();

        void b(long j);
    }

    public static void a(final int i, final ICountDownListener iCountDownListener) {
        Observable.interval(0, 1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(new Func1<Long, Long>() {
            /* renamed from: a */
            public Long call(Long l) {
                return Long.valueOf(((long) i) - l.longValue());
            }
        }).take(i + 1).subscribe(new Subscriber<Long>() {
            public void onStart() {
                iCountDownListener.a((long) i);
            }

            public void onCompleted() {
                iCountDownListener.b();
            }

            public void onError(Throwable th) {
                iCountDownListener.a();
            }

            /* renamed from: a */
            public void onNext(Long l) {
                iCountDownListener.b(l.longValue());
            }
        });
    }
}
