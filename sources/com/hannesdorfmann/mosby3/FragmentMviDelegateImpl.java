package com.hannesdorfmann.mosby3;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.BackstackAccessor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import com.hannesdorfmann.mosby3.mvi.MviPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import java.util.UUID;

public class FragmentMviDelegateImpl<V extends MvpView, P extends MviPresenter<V, ?>> implements FragmentMviDelegate<V, P> {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f5743a = false;
    private static final String b = "FragmentMviDelegateImpl";
    private static final String c = "com.hannesdorfmann.mosby3.fragment.mvi.id";
    private String d;
    private MviDelegateCallback<V, P> e;
    private Fragment f;
    private boolean g;
    private final boolean h;
    private final boolean i;
    private P j;
    private boolean k;

    public void a(Activity activity) {
    }

    public void a(Context context) {
    }

    public void a(Configuration configuration) {
    }

    public void a(Fragment fragment) {
    }

    public void c() {
    }

    public void d() {
    }

    public void g() {
    }

    public FragmentMviDelegateImpl(@NonNull MviDelegateCallback<V, P> mviDelegateCallback, @NonNull Fragment fragment) {
        this(mviDelegateCallback, fragment, true, true);
    }

    public FragmentMviDelegateImpl(@NonNull MviDelegateCallback<V, P> mviDelegateCallback, @NonNull Fragment fragment, boolean z, boolean z2) {
        this.d = null;
        this.g = false;
        if (mviDelegateCallback == null) {
            throw new NullPointerException("delegateCallback == null");
        } else if (fragment == null) {
            throw new NullPointerException("fragment == null");
        } else if (z || !z2) {
            this.e = mviDelegateCallback;
            this.f = fragment;
            this.h = z;
            this.i = z2;
        } else {
            throw new IllegalArgumentException("It is not possible to keep the presenter on backstack, but NOT keep presenter through screen orientation changes. Keep presenter on backstack also requires keep presenter through screen orientation changes to be enabled");
        }
    }

    public void a(@Nullable Bundle bundle) {
        if ((this.h || this.i) && bundle != null) {
            this.d = bundle.getString(c);
        }
        if (f5743a) {
            Log.d(b, "MosbyView ID = " + this.d + " for MvpView: " + this.e.getMvpView());
        }
        if (this.d == null) {
            this.j = i();
            this.k = false;
            if (f5743a) {
                Log.d(b, "new Presenter instance created: " + this.j);
            }
        } else {
            this.j = (MviPresenter) PresenterManager.a(h(), this.d);
            if (this.j == null) {
                this.j = i();
                this.k = false;
                if (f5743a) {
                    Log.d(b, "No Presenter instance found in cache, although MosbyView ID present. This was caused by process death, therefore new Presenter instance created: " + this.j);
                }
            } else {
                this.k = true;
                if (f5743a) {
                    Log.d(b, "Presenter instance reused from internal cache: " + this.j);
                }
            }
        }
        if (this.j == null) {
            throw new IllegalStateException("Oops, Presenter is null. This seems to be a Mosby internal bug. Please report this issue here: https://github.com/sockeqwe/mosby/issues");
        }
    }

    public void a(View view, @Nullable Bundle bundle) {
        this.g = true;
    }

    public void e() {
        V mvpView = this.e.getMvpView();
        if (mvpView == null) {
            throw new NullPointerException("MvpView returned from getMvpView() is null. Returned by " + this.f);
        } else if (this.j != null) {
            if (this.k) {
                this.e.setRestoringViewState(true);
            }
            this.j.a(mvpView);
            if (this.k) {
                this.e.setRestoringViewState(false);
            }
            if (f5743a) {
                Log.d(b, "MvpView attached to Presenter. MvpView: " + mvpView + "   Presenter: " + this.j);
            }
        } else {
            throw new IllegalStateException("Oops, Presenter is null. This seems to be a Mosby internal bug. Please report this issue here: https://github.com/sockeqwe/mosby/issues");
        }
    }

    public void b(Bundle bundle) {
        if (!this.g) {
            throw new IllegalStateException("It seems that onCreateView() has never been called (or has returned null). This means that your fragment is headless (no UI). That is not allowed because it doesn't make sense to use Mosby with a Fragment without View.");
        }
    }

    private boolean a(boolean z, Activity activity, Fragment fragment) {
        if (activity.isChangingConfigurations()) {
            return this.h;
        }
        if (activity.isFinishing()) {
            return false;
        }
        if (!z || !BackstackAccessor.isFragmentOnBackStack(fragment)) {
            return !fragment.isRemoving();
        }
        return true;
    }

    public void b() {
        this.g = false;
    }

    public void f() {
        this.j.b();
        this.k = true;
        if (f5743a) {
            Log.d(b, "detached MvpView from Presenter. MvpView " + this.e.getMvpView() + "   Presenter: " + this.j);
        }
    }

    public void a() {
        Activity h2 = h();
        boolean a2 = a(this.i, h2, this.f);
        if (!a2) {
            this.j.c();
            if (this.d != null) {
                PresenterManager.c(h2, this.d);
            }
            if (f5743a) {
                Log.d(b, "Presenter destroyed");
            }
        } else if (f5743a) {
            Log.d(b, "Retaining presenter instance: " + Boolean.toString(a2) + " " + this.j);
        }
        this.j = null;
        this.e = null;
        this.f = null;
    }

    @NonNull
    private Activity h() {
        FragmentActivity activity = this.f.getActivity();
        if (activity != null) {
            return activity;
        }
        throw new NullPointerException("Activity returned by Fragment.getActivity() is null. Fragment is " + this.f);
    }

    private P i() {
        P createPresenter = this.e.createPresenter();
        if (createPresenter != null) {
            if (this.h || this.i) {
                Activity h2 = h();
                this.d = UUID.randomUUID().toString();
                PresenterManager.a(h2, this.d, (MvpPresenter<? extends MvpView>) createPresenter);
            }
            return createPresenter;
        }
        throw new NullPointerException("Presenter returned from createPresenter() is null. Fragment is " + this.f);
    }

    public void c(Bundle bundle) {
        if ((this.h || this.i) && bundle != null) {
            bundle.putString(c, this.d);
            a(this.i, h(), this.f);
            if (f5743a) {
                Log.d(b, "Saving MosbyViewId into Bundle. ViewId: " + this.d);
            }
        }
    }
}
