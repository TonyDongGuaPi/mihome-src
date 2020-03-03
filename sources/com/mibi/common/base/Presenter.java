package com.mibi.common.base;

import android.content.Context;
import android.os.Bundle;
import com.mibi.common.base.IView;
import com.mibi.common.data.Session;
import com.mibi.common.data.Utils;
import com.mibi.common.utils.AutoSaveUtil;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.subjects.BehaviorSubject;

public class Presenter<T extends IView> implements IPresenter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7462a = "SAVE_ID";
    private String b = UUID.randomUUID().toString();
    private final Class<T> c;
    private final Presenter<T>.PendingEventInvocationHandler d;
    private Context e;
    private Session f;
    /* access modifiers changed from: private */
    public T g;
    private T h;
    private Bundle i;
    private final BehaviorSubject<LifecycleEvent> j = BehaviorSubject.create();

    public enum LifecycleEvent {
        INIT,
        ATTACH,
        DETACH,
        RELEASE
    }

    /* access modifiers changed from: protected */
    public void C_() {
    }

    /* access modifiers changed from: protected */
    public void D_() {
    }

    /* access modifiers changed from: protected */
    public void E_() {
    }

    public void a(int i2, int i3, Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void b(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void c(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void c(IView iView) {
    }

    /* access modifiers changed from: protected */
    public void o_() {
    }

    public Presenter(Class<T> cls) {
        this.c = cls;
        this.d = new PendingEventInvocationHandler();
    }

    public final void a(Context context, Session session, Bundle bundle, Bundle bundle2) {
        this.e = context.getApplicationContext();
        this.f = session;
        this.i = bundle;
        if (bundle2 != null) {
            this.b = bundle2.getString(f7462a);
            AutoSaveUtil.b(this, bundle2);
        }
        b(bundle2);
        this.j.onNext(LifecycleEvent.INIT);
    }

    /* access modifiers changed from: protected */
    public Bundle n_() {
        n();
        return this.i;
    }

    public final void a(IView iView) {
        this.g = iView;
        this.d.a();
        o_();
        this.j.onNext(LifecycleEvent.ATTACH);
    }

    public final void a() {
        this.j.onNext(LifecycleEvent.DETACH);
        D_();
        this.g = null;
    }

    public void b(IView iView) {
        c(iView);
    }

    public void b() {
        C_();
    }

    public final void a(Bundle bundle) {
        bundle.putString(f7462a, this.b);
        AutoSaveUtil.a(this, bundle);
        c(bundle);
    }

    public final void c() {
        this.j.onNext(LifecycleEvent.RELEASE);
        E_();
        this.g = null;
        this.d.b();
    }

    public String d() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public T l() {
        Utils.c();
        if (this.h == null) {
            this.h = (IView) Proxy.newProxyInstance(this.c.getClassLoader(), new Class[]{this.c}, this.d);
        }
        return this.h;
    }

    public Context e() {
        n();
        return this.e;
    }

    public Session f() {
        n();
        return this.f;
    }

    public void a(Session session) {
        this.f = session;
    }

    private void n() {
        if (this.e == null) {
            throw new IllegalStateException("Do not call this method before onInit");
        }
    }

    /* access modifiers changed from: protected */
    public final <T> Observable.Transformer<T, T> m() {
        return new PresenterLifecycleTransformer(this.j);
    }

    private class PresenterLifecycleTransformer<T> implements Observable.Transformer<T, T> {
        private final Observable<LifecycleEvent> b;

        public PresenterLifecycleTransformer(Observable<LifecycleEvent> observable) {
            this.b = observable;
        }

        /* renamed from: a */
        public Observable<T> call(Observable<T> observable) {
            return observable.observeOn(AndroidSchedulers.mainThread()).takeUntil(this.b.takeFirst(new Func1<LifecycleEvent, Boolean>() {
                /* renamed from: a */
                public Boolean call(LifecycleEvent lifecycleEvent) {
                    return Boolean.valueOf(lifecycleEvent.equals(LifecycleEvent.RELEASE));
                }
            }));
        }
    }

    private class PendingEventInvocationHandler implements InvocationHandler {
        private Queue<Presenter<T>.PendingEventInvocationHandler.ViewEvent> b;

        private PendingEventInvocationHandler() {
            this.b = new LinkedList();
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            Utils.c();
            ViewEvent viewEvent = new ViewEvent();
            viewEvent.f7464a = method;
            viewEvent.b = objArr;
            this.b.offer(viewEvent);
            a();
            return null;
        }

        public void a() {
            if (Presenter.this.g != null) {
                while (!this.b.isEmpty()) {
                    ViewEvent poll = this.b.poll();
                    try {
                        poll.f7464a.invoke(Presenter.this.g, poll.b);
                    } catch (Exception e) {
                        throw new IllegalStateException("apply pending method invocation error when view attached", e);
                    }
                }
            }
        }

        public void b() {
            this.b.clear();
        }

        private class ViewEvent {

            /* renamed from: a  reason: collision with root package name */
            Method f7464a;
            Object[] b;

            private ViewEvent() {
            }
        }
    }
}
