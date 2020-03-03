package com.trello.rxlifecycle2.android;

import android.view.View;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.MainThreadDisposable;

final class ViewDetachesOnSubscribe implements ObservableOnSubscribe<Object> {

    /* renamed from: a  reason: collision with root package name */
    static final Object f9520a = new Object();
    final View b;

    public ViewDetachesOnSubscribe(View view) {
        this.b = view;
    }

    public void subscribe(ObservableEmitter<Object> observableEmitter) throws Exception {
        MainThreadDisposable.verifyMainThread();
        EmitterListener emitterListener = new EmitterListener(observableEmitter);
        observableEmitter.setDisposable(emitterListener);
        this.b.addOnAttachStateChangeListener(emitterListener);
    }

    class EmitterListener extends MainThreadDisposable implements View.OnAttachStateChangeListener {

        /* renamed from: a  reason: collision with root package name */
        final ObservableEmitter<Object> f9521a;

        public void onViewAttachedToWindow(View view) {
        }

        public EmitterListener(ObservableEmitter<Object> observableEmitter) {
            this.f9521a = observableEmitter;
        }

        public void onViewDetachedFromWindow(View view) {
            this.f9521a.onNext(ViewDetachesOnSubscribe.f9520a);
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            ViewDetachesOnSubscribe.this.b.removeOnAttachStateChangeListener(this);
        }
    }
}
