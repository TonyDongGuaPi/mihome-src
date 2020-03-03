package com.hannesdorfmann.mosby3;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.hannesdorfmann.mosby3.mvi.MviPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import java.util.UUID;

public class ActivityMviDelegateImpl<V extends MvpView, P extends MviPresenter<V, ?>> implements ActivityMviDelegate {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f5740a = false;
    private static final String b = "ActivityMviDelegateImpl";
    private static final String c = "com.hannesdorfmann.mosby3.activity.mvi.id";
    private String d;
    private MviDelegateCallback<V, P> e;
    private Activity f;
    private boolean g;
    private P h;

    public void b() {
    }

    public void c() {
    }

    public void c(Bundle bundle) {
    }

    public void f() {
    }

    public void g() {
    }

    public Object h() {
        return null;
    }

    public ActivityMviDelegateImpl(@NonNull Activity activity, @NonNull MviDelegateCallback<V, P> mviDelegateCallback) {
        this(activity, mviDelegateCallback, true);
    }

    public ActivityMviDelegateImpl(@NonNull Activity activity, @NonNull MviDelegateCallback<V, P> mviDelegateCallback, boolean z) {
        this.d = null;
        if (activity == null) {
            throw new NullPointerException("Activity is null");
        } else if (mviDelegateCallback != null) {
            this.e = mviDelegateCallback;
            this.f = activity;
            this.g = z;
        } else {
            throw new NullPointerException("MvpDelegateCallback is null!");
        }
    }

    public void a(@Nullable Bundle bundle) {
        if (this.g && bundle != null) {
            this.d = bundle.getString(c);
        }
        if (f5740a) {
            Log.d(b, "MosbyView ID = " + this.d + " for MvpView: " + this.e.getMvpView());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00c8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d() {
        /*
            r5 = this;
            java.lang.String r0 = r5.d
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0037
            com.hannesdorfmann.mosby3.mvi.MviPresenter r0 = r5.i()
            r5.h = r0
            boolean r0 = f5740a
            if (r0 == 0) goto L_0x0069
            java.lang.String r0 = "ActivityMviDelegateImpl"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "new Presenter instance created: "
            r3.append(r4)
            P r4 = r5.h
            r3.append(r4)
            java.lang.String r4 = " for "
            r3.append(r4)
            com.hannesdorfmann.mosby3.MviDelegateCallback<V, P> r4 = r5.e
            com.hannesdorfmann.mosby3.mvp.MvpView r4 = r4.getMvpView()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r0, r3)
            goto L_0x0069
        L_0x0037:
            android.app.Activity r0 = r5.f
            java.lang.String r3 = r5.d
            java.lang.Object r0 = com.hannesdorfmann.mosby3.PresenterManager.a(r0, r3)
            com.hannesdorfmann.mosby3.mvi.MviPresenter r0 = (com.hannesdorfmann.mosby3.mvi.MviPresenter) r0
            r5.h = r0
            P r0 = r5.h
            if (r0 != 0) goto L_0x006b
            com.hannesdorfmann.mosby3.mvi.MviPresenter r0 = r5.i()
            r5.h = r0
            boolean r0 = f5740a
            if (r0 == 0) goto L_0x0069
            java.lang.String r0 = "ActivityMviDelegateImpl"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "No Presenter instance found in cache, although MosbyView ID present. This was caused by process death, therefore new Presenter instance created: "
            r3.append(r4)
            P r4 = r5.h
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r0, r3)
        L_0x0069:
            r0 = 0
            goto L_0x0088
        L_0x006b:
            boolean r0 = f5740a
            if (r0 == 0) goto L_0x0087
            java.lang.String r0 = "ActivityMviDelegateImpl"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Presenter instance reused from internal cache: "
            r3.append(r4)
            P r4 = r5.h
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r0, r3)
        L_0x0087:
            r0 = 1
        L_0x0088:
            com.hannesdorfmann.mosby3.MviDelegateCallback<V, P> r3 = r5.e
            com.hannesdorfmann.mosby3.mvp.MvpView r3 = r3.getMvpView()
            if (r3 == 0) goto L_0x00c8
            if (r0 == 0) goto L_0x0097
            com.hannesdorfmann.mosby3.MviDelegateCallback<V, P> r4 = r5.e
            r4.setRestoringViewState(r1)
        L_0x0097:
            P r1 = r5.h
            r1.a(r3)
            if (r0 == 0) goto L_0x00a3
            com.hannesdorfmann.mosby3.MviDelegateCallback<V, P> r0 = r5.e
            r0.setRestoringViewState(r2)
        L_0x00a3:
            boolean r0 = f5740a
            if (r0 == 0) goto L_0x00c7
            java.lang.String r0 = "ActivityMviDelegateImpl"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "MvpView attached to Presenter. MvpView: "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r2 = "   Presenter: "
            r1.append(r2)
            P r2 = r5.h
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r0, r1)
        L_0x00c7:
            return
        L_0x00c8:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "MvpView returned from getMvpView() is null. Returned by "
            r1.append(r2)
            android.app.Activity r2 = r5.f
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hannesdorfmann.mosby3.ActivityMviDelegateImpl.d():void");
    }

    private P i() {
        P createPresenter = this.e.createPresenter();
        if (createPresenter != null) {
            if (this.g) {
                this.d = UUID.randomUUID().toString();
                PresenterManager.a(this.f, this.d, (MvpPresenter<? extends MvpView>) createPresenter);
            }
            return createPresenter;
        }
        throw new NullPointerException("Presenter returned from createPresenter() is null. Activity is " + this.f);
    }

    public void b(Bundle bundle) {
        if (this.g && bundle != null) {
            bundle.putString(c, this.d);
            if (f5740a) {
                Log.d(b, "Saving MosbyViewId into Bundle. ViewId: " + this.d);
            }
        }
    }

    static boolean a(boolean z, Activity activity) {
        return z && (activity.isChangingConfigurations() || !activity.isFinishing());
    }

    public void e() {
        this.h.b();
        if (f5740a) {
            Log.d(b, "detached MvpView from Presenter. MvpView " + this.e.getMvpView() + "   Presenter: " + this.h);
        }
    }

    public void a() {
        if (this.h != null && !a(this.g, this.f)) {
            this.h.c();
            if (this.d != null) {
                PresenterManager.c(this.f, this.d);
            }
            Log.d(b, "Destroying Presenter permanently " + this.h);
        }
        this.h = null;
        this.f = null;
        this.e = null;
    }
}
