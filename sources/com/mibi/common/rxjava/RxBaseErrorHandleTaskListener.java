package com.mibi.common.rxjava;

import android.content.Context;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;
import rx.Subscriber;

public abstract class RxBaseErrorHandleTaskListener<R> extends Subscriber<R> {

    /* renamed from: a  reason: collision with root package name */
    private Context f7583a;
    private RxBaseErrorHandler b = new RxBaseErrorHandler(this.f7583a) {
        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            RxBaseErrorHandleTaskListener.this.a(i, str, th);
        }
    };
    private RxBaseSuccessHandler<R> c = new RxBaseSuccessHandler<R>() {
        /* access modifiers changed from: protected */
        public void a(R r) {
            RxBaseErrorHandleTaskListener.this.a(r);
        }
    };

    /* access modifiers changed from: protected */
    public void a(int i, String str, Throwable th) {
    }

    /* access modifiers changed from: protected */
    public void a(R r) {
    }

    public void onCompleted() {
    }

    public RxBaseErrorHandleTaskListener(Context context) {
        this.f7583a = context.getApplicationContext();
    }

    public void onError(Throwable th) {
        this.b.call(th);
    }

    public void onNext(R r) {
        this.c.call(r);
    }

    public ExceptionDispatcher a() {
        return this.b.a();
    }
}
