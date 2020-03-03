package com.mibi.common.rxjava;

import rx.functions.Action1;

public abstract class RxBaseSuccessHandler<R> implements Action1<R> {
    /* access modifiers changed from: protected */
    public abstract void a(R r);

    public void call(R r) {
        try {
            a(r);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("exception occurs in onNext", e);
        }
    }
}
