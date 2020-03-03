package rx.observers;

import java.util.Arrays;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.UnsubscribeFailedException;
import rx.internal.util.RxJavaPluginUtils;

public class SafeSubscriber<T> extends Subscriber<T> {
    private final Subscriber<? super T> actual;
    boolean done = false;

    public SafeSubscriber(Subscriber<? super T> subscriber) {
        super(subscriber);
        this.actual = subscriber;
    }

    public void onCompleted() {
        if (!this.done) {
            this.done = true;
            try {
                this.actual.onCompleted();
                try {
                    unsubscribe();
                } catch (Throwable th) {
                    RxJavaPluginUtils.handleException(th);
                    throw new UnsubscribeFailedException(th.getMessage(), th);
                }
            } catch (Throwable th2) {
                RxJavaPluginUtils.handleException(th2);
                throw new UnsubscribeFailedException(th2.getMessage(), th2);
            }
        }
    }

    public void onError(Throwable th) {
        Exceptions.throwIfFatal(th);
        if (!this.done) {
            this.done = true;
            _onError(th);
        }
    }

    public void onNext(T t) {
        try {
            if (!this.done) {
                this.actual.onNext(t);
            }
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            onError(th);
        }
    }

    /* access modifiers changed from: protected */
    public void _onError(Throwable th) {
        RxJavaPluginUtils.handleException(th);
        try {
            this.actual.onError(th);
            try {
                unsubscribe();
            } catch (RuntimeException e) {
                RxJavaPluginUtils.handleException(e);
                throw new OnErrorFailedException(e);
            }
        } catch (Throwable th2) {
            RxJavaPluginUtils.handleException(th2);
            throw new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError and during unsubscription.", new CompositeException(Arrays.asList(new Throwable[]{th, th, th2})));
        }
    }

    public Subscriber<? super T> getActual() {
        return this.actual;
    }
}
