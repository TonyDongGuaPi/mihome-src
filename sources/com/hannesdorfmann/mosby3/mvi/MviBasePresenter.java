package com.hannesdorfmann.mosby3.mvi;

import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import io.reactivex.subjects.UnicastSubject;
import java.util.ArrayList;
import java.util.List;

public abstract class MviBasePresenter<V extends MvpView, VS> implements MviPresenter<V, VS> {

    /* renamed from: a  reason: collision with root package name */
    private final BehaviorSubject<VS> f5751a;
    private boolean b;
    private List<MviBasePresenter<V, VS>.IntentRelayBinderPair<?>> c;
    private CompositeDisposable d;
    private Disposable e;
    private Disposable f;
    private boolean g;
    /* access modifiers changed from: private */
    public ViewStateConsumer<V, VS> h;

    protected interface ViewIntentBinder<V extends MvpView, I> {
        @NonNull
        Observable<I> bind(@NonNull V v);
    }

    protected interface ViewStateConsumer<V extends MvpView, VS> {
        void accept(@NonNull V v, @NonNull VS vs);
    }

    @Deprecated
    @CallSuper
    public void a(boolean z) {
    }

    /* access modifiers changed from: protected */
    @MainThread
    public abstract void d();

    /* access modifiers changed from: protected */
    public void e() {
    }

    private class IntentRelayBinderPair<I> {
        /* access modifiers changed from: private */
        public final Subject<I> b;
        /* access modifiers changed from: private */
        public final ViewIntentBinder<V, I> c;

        public IntentRelayBinderPair(Subject<I> subject, ViewIntentBinder<V, I> viewIntentBinder) {
            this.b = subject;
            this.c = viewIntentBinder;
        }
    }

    public MviBasePresenter() {
        this.b = false;
        this.c = new ArrayList(4);
        this.g = true;
        this.f5751a = BehaviorSubject.create();
        f();
    }

    public MviBasePresenter(@NonNull VS vs) {
        this.b = false;
        this.c = new ArrayList(4);
        this.g = true;
        if (vs != null) {
            this.f5751a = BehaviorSubject.createDefault(vs);
            f();
            return;
        }
        throw new NullPointerException("Initial ViewState == null");
    }

    /* access modifiers changed from: protected */
    public Observable<VS> a() {
        return this.f5751a;
    }

    @CallSuper
    public void a(@NonNull V v) {
        if (this.g) {
            d();
        }
        if (this.h != null) {
            b(v);
        }
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            a(v, this.c.get(i));
        }
        this.g = false;
    }

    @CallSuper
    public void b() {
        a(true);
        if (this.e != null) {
            this.e.dispose();
            this.e = null;
        }
        if (this.d != null) {
            this.d.dispose();
            this.d = null;
        }
    }

    @CallSuper
    public void c() {
        a(false);
        if (this.f != null) {
            this.f.dispose();
        }
        e();
        f();
    }

    private void f() {
        this.g = true;
        this.c.clear();
        this.b = false;
    }

    /* access modifiers changed from: protected */
    @MainThread
    public void a(@NonNull Observable<VS> observable, @NonNull ViewStateConsumer<V, VS> viewStateConsumer) {
        if (!this.b) {
            this.b = true;
            if (observable == null) {
                throw new NullPointerException("ViewState Observable is null");
            } else if (viewStateConsumer != null) {
                this.h = viewStateConsumer;
                this.f = (Disposable) observable.subscribeWith(new DisposableViewStateObserver(this.f5751a));
            } else {
                throw new NullPointerException("ViewStateBinder is null");
            }
        } else {
            throw new IllegalStateException("subscribeViewState() method is only allowed to be called once");
        }
    }

    @MainThread
    private void b(@NonNull final V v) {
        if (v == null) {
            throw new NullPointerException("View is null");
        } else if (this.h != null) {
            this.e = this.f5751a.subscribe(new Consumer<VS>() {
                public void accept(VS vs) throws Exception {
                    MviBasePresenter.this.h.accept(v, vs);
                }
            });
        } else {
            throw new NullPointerException(ViewStateConsumer.class.getSimpleName() + " is null. This is a Mosby internal bug. Please file an issue at https://github.com/sockeqwe/mosby/issues");
        }
    }

    /* access modifiers changed from: protected */
    @MainThread
    public <I> Observable<I> a(ViewIntentBinder<V, I> viewIntentBinder) {
        UnicastSubject create = UnicastSubject.create();
        this.c.add(new IntentRelayBinderPair(create, viewIntentBinder));
        return create;
    }

    @MainThread
    private <I> Observable<I> a(@NonNull V v, @NonNull MviBasePresenter<V, VS>.IntentRelayBinderPair<?> intentRelayBinderPair) {
        if (v == null) {
            throw new NullPointerException("View is null. This is a Mosby internal bug. Please file an issue at https://github.com/sockeqwe/mosby/issues");
        } else if (intentRelayBinderPair != null) {
            Subject a2 = intentRelayBinderPair.b;
            if (a2 != null) {
                ViewIntentBinder b2 = intentRelayBinderPair.c;
                if (b2 != null) {
                    Observable bind = b2.bind(v);
                    if (bind != null) {
                        if (this.d == null) {
                            this.d = new CompositeDisposable();
                        }
                        this.d.add((Disposable) bind.subscribeWith(new DisposableIntentObserver(a2)));
                        return a2;
                    }
                    throw new NullPointerException("Intent Observable returned from Binder " + b2 + " is null");
                }
                throw new NullPointerException(ViewIntentBinder.class.getSimpleName() + " is null. This is a Mosby internal bug. Please file an issue at https://github.com/sockeqwe/mosby/issues");
            }
            throw new NullPointerException("IntentRelay from binderPair is null. This is a Mosby internal bug. Please file an issue at https://github.com/sockeqwe/mosby/issues");
        } else {
            throw new NullPointerException("IntentRelayBinderPair is null. This is a Mosby internal bug. Please file an issue at https://github.com/sockeqwe/mosby/issues");
        }
    }
}
