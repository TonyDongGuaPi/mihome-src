package com.hannesdorfmann.mosby3;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import com.hannesdorfmann.mosby3.mvi.MviPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import java.util.UUID;

public class ViewGroupMviDelegateImpl<V extends MvpView, P extends MviPresenter<V, ?>> implements Application.ActivityLifecycleCallbacks, ViewGroupMviDelegate<V, P> {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f5746a = false;
    private static final String b = "ViewGroupMviDelegateImp";
    private ViewGroupMviDelegateCallback<V, P> c;
    private String d;
    private final boolean e;
    private final boolean f;
    private P g;
    private final Activity h;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public ViewGroupMviDelegateImpl(@NonNull View view, @NonNull ViewGroupMviDelegateCallback<V, P> viewGroupMviDelegateCallback, boolean z) {
        if (view == null) {
            throw new NullPointerException("View is null!");
        } else if (viewGroupMviDelegateCallback != null) {
            this.c = viewGroupMviDelegateCallback;
            this.e = z;
            this.f = view.isInEditMode();
            if (!this.f) {
                this.h = PresenterManager.a(viewGroupMviDelegateCallback.getContext());
                this.h.getApplication().registerActivityLifecycleCallbacks(this);
                return;
            }
            this.h = null;
        } else {
            throw new NullPointerException("MvpDelegateCallback is null!");
        }
    }

    private P d() {
        P createPresenter = this.c.createPresenter();
        if (createPresenter != null) {
            if (this.e) {
                Context context = this.c.getContext();
                this.d = UUID.randomUUID().toString();
                PresenterManager.a(PresenterManager.a(context), this.d, (MvpPresenter<? extends MvpView>) createPresenter);
            }
            return createPresenter;
        }
        throw new NullPointerException("Presenter returned from createPresenter() is null.");
    }

    @NonNull
    private Context e() {
        Context context = this.c.getContext();
        if (context != null) {
            return context;
        }
        throw new NullPointerException("Context returned from " + this.c + " is null");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00c2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /*
            r5 = this;
            boolean r0 = r5.f
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            java.lang.String r0 = r5.d
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x002e
            com.hannesdorfmann.mosby3.mvi.MviPresenter r0 = r5.d()
            r5.g = r0
            boolean r0 = f5746a
            if (r0 == 0) goto L_0x0063
            java.lang.String r0 = "ViewGroupMviDelegateImp"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "new Presenter instance created: "
            r3.append(r4)
            P r4 = r5.g
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r0, r3)
            goto L_0x0063
        L_0x002e:
            r5.e()
            android.app.Activity r0 = r5.h
            java.lang.String r3 = r5.d
            java.lang.Object r0 = com.hannesdorfmann.mosby3.PresenterManager.a(r0, r3)
            com.hannesdorfmann.mosby3.mvi.MviPresenter r0 = (com.hannesdorfmann.mosby3.mvi.MviPresenter) r0
            r5.g = r0
            P r0 = r5.g
            if (r0 != 0) goto L_0x0065
            com.hannesdorfmann.mosby3.mvi.MviPresenter r0 = r5.d()
            r5.g = r0
            boolean r0 = f5746a
            if (r0 == 0) goto L_0x0063
            java.lang.String r0 = "ViewGroupMviDelegateImp"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "No Presenter instance found in cache, although MosbyView ID present. This was caused by process death, therefore new Presenter instance created: "
            r3.append(r4)
            P r4 = r5.g
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r0, r3)
        L_0x0063:
            r0 = 0
            goto L_0x0082
        L_0x0065:
            boolean r0 = f5746a
            if (r0 == 0) goto L_0x0081
            java.lang.String r0 = "ViewGroupMviDelegateImp"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Presenter instance reused from internal cache: "
            r3.append(r4)
            P r4 = r5.g
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r0, r3)
        L_0x0081:
            r0 = 1
        L_0x0082:
            com.hannesdorfmann.mosby3.ViewGroupMviDelegateCallback<V, P> r3 = r5.c
            com.hannesdorfmann.mosby3.mvp.MvpView r3 = r3.getMvpView()
            if (r3 == 0) goto L_0x00c2
            if (r0 == 0) goto L_0x0091
            com.hannesdorfmann.mosby3.ViewGroupMviDelegateCallback<V, P> r4 = r5.c
            r4.setRestoringViewState(r1)
        L_0x0091:
            P r1 = r5.g
            r1.a(r3)
            if (r0 == 0) goto L_0x009d
            com.hannesdorfmann.mosby3.ViewGroupMviDelegateCallback<V, P> r0 = r5.c
            r0.setRestoringViewState(r2)
        L_0x009d:
            boolean r0 = f5746a
            if (r0 == 0) goto L_0x00c1
            java.lang.String r0 = "ViewGroupMviDelegateImp"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "MvpView attached to Presenter. MvpView: "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r2 = "   Presenter: "
            r1.append(r2)
            P r2 = r5.g
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r0, r1)
        L_0x00c1:
            return
        L_0x00c2:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "MvpView returned from getMvpView() is null. Returned by "
            r1.append(r2)
            com.hannesdorfmann.mosby3.ViewGroupMviDelegateCallback<V, P> r2 = r5.c
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hannesdorfmann.mosby3.ViewGroupMviDelegateImpl.a():void");
    }

    public void b() {
        if (!this.f) {
            g();
            if (this.i) {
                return;
            }
            if (!ActivityMviDelegateImpl.a(this.e, this.h)) {
                f();
            } else if (!this.h.isFinishing()) {
                f();
            }
        }
    }

    public Parcelable c() {
        if (this.f) {
            return null;
        }
        Parcelable superOnSaveInstanceState = this.c.superOnSaveInstanceState();
        return this.e ? new MosbySavedState(superOnSaveInstanceState, this.d) : superOnSaveInstanceState;
    }

    private void a(MosbySavedState mosbySavedState) {
        this.d = mosbySavedState.a();
    }

    public void a(Parcelable parcelable) {
        if (!this.f) {
            if (!(parcelable instanceof MosbySavedState)) {
                this.c.superOnRestoreInstanceState(parcelable);
                return;
            }
            MosbySavedState mosbySavedState = (MosbySavedState) parcelable;
            a(mosbySavedState);
            this.c.superOnRestoreInstanceState(mosbySavedState.getSuperState());
        }
    }

    public void onActivityDestroyed(Activity activity) {
        if (activity == this.h) {
            activity.getApplication().unregisterActivityLifecycleCallbacks(this);
            this.i = true;
            if (!ActivityMviDelegateImpl.a(this.e, activity)) {
                g();
                f();
            }
        }
    }

    private void f() {
        if (!this.k) {
            this.g.c();
            this.k = true;
            if (f5746a) {
                Log.d(b, "Presenter destroyed: " + this.g);
            }
            if (this.d != null) {
                PresenterManager.c(this.h, this.d);
            }
            this.d = null;
        }
    }

    private void g() {
        if (!this.j) {
            this.g.b();
            this.j = true;
            if (f5746a) {
                Log.d(b, "view " + this.c.getMvpView() + " detached from Presenter " + this.g);
            }
        }
    }
}
