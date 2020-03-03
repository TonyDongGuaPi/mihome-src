package com.hannesdorfmann.mosby3.mvi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import com.hannesdorfmann.mosby3.ActivityMviDelegate;
import com.hannesdorfmann.mosby3.ActivityMviDelegateImpl;
import com.hannesdorfmann.mosby3.MviDelegateCallback;
import com.hannesdorfmann.mosby3.mvi.MviPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public abstract class MviActivity<V extends MvpView, P extends MviPresenter<V, ?>> extends AppCompatActivity implements MviDelegateCallback<V, P>, MvpView {

    /* renamed from: a  reason: collision with root package name */
    private boolean f5750a = false;
    protected ActivityMviDelegate<V, P> mvpDelegate;

    @NonNull
    public abstract P createPresenter();

    @NonNull
    public V getMvpView() {
        return this;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getMvpDelegate().a(bundle);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        getMvpDelegate().a();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        getMvpDelegate().b(bundle);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        getMvpDelegate().b();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getMvpDelegate().c();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        getMvpDelegate().d();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        getMvpDelegate().e();
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        getMvpDelegate().f();
    }

    public void onContentChanged() {
        super.onContentChanged();
        getMvpDelegate().g();
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        getMvpDelegate().c(bundle);
    }

    /* access modifiers changed from: protected */
    @NonNull
    public ActivityMviDelegate<V, P> getMvpDelegate() {
        if (this.mvpDelegate == null) {
            this.mvpDelegate = new ActivityMviDelegateImpl(this, this);
        }
        return this.mvpDelegate;
    }

    public final Object onRetainCustomNonConfigurationInstance() {
        return getMvpDelegate().h();
    }

    public void setRestoringViewState(boolean z) {
        this.f5750a = z;
    }

    /* access modifiers changed from: protected */
    public boolean isRestoringViewState() {
        return this.f5750a;
    }
}
