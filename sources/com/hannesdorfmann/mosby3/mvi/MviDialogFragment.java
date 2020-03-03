package com.hannesdorfmann.mosby3.mvi;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.View;
import com.hannesdorfmann.mosby3.FragmentMviDelegate;
import com.hannesdorfmann.mosby3.FragmentMviDelegateImpl;
import com.hannesdorfmann.mosby3.MviDelegateCallback;
import com.hannesdorfmann.mosby3.mvi.MviPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public abstract class MviDialogFragment<V extends MvpView, P extends MviPresenter<V, ?>> extends DialogFragment implements MviDelegateCallback<V, P>, MvpView {

    /* renamed from: a  reason: collision with root package name */
    protected FragmentMviDelegate<V, P> f5754a;
    private boolean b = false;

    @NonNull
    public abstract P createPresenter();

    @NonNull
    public V getMvpView() {
        return this;
    }

    @CallSuper
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a().a(bundle);
    }

    @CallSuper
    public void onDestroy() {
        super.onDestroy();
        a().a();
    }

    @CallSuper
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        a().c(bundle);
    }

    @CallSuper
    public void onPause() {
        super.onPause();
        a().c();
    }

    @CallSuper
    public void onResume() {
        super.onResume();
        a().d();
    }

    @CallSuper
    public void onStart() {
        super.onStart();
        a().e();
    }

    @CallSuper
    public void onStop() {
        super.onStop();
        a().f();
    }

    @CallSuper
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        a().a(view, bundle);
    }

    @CallSuper
    public void onDestroyView() {
        super.onDestroyView();
        a().b();
    }

    @CallSuper
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a().b(bundle);
    }

    @CallSuper
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        a().a(activity);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        a().a(context);
    }

    @CallSuper
    public void onDetach() {
        super.onDetach();
        a().g();
    }

    @CallSuper
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        a().a(fragment);
    }

    @CallSuper
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a().a(configuration);
    }

    @NonNull
    public FragmentMviDelegate<V, P> a() {
        if (this.f5754a == null) {
            this.f5754a = new FragmentMviDelegateImpl(this, this);
        }
        return this.f5754a;
    }

    public void setRestoringViewState(boolean z) {
        this.b = z;
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        return this.b;
    }
}
