package com.xiaomi.smarthome.newui.mainpage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import com.hannesdorfmann.mosby3.ViewGroupMviDelegate;
import com.hannesdorfmann.mosby3.ViewGroupMviDelegateCallback;
import com.hannesdorfmann.mosby3.ViewGroupMviDelegateImpl;
import com.hannesdorfmann.mosby3.mvi.MviPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public abstract class MviRecyclerView<V extends MvpView, P extends MviPresenter<V, ?>> extends RecyclerView implements ViewGroupMviDelegateCallback<V, P>, MvpView {

    /* renamed from: a  reason: collision with root package name */
    private boolean f20680a = false;
    protected ViewGroupMviDelegate<V, P> mvpDelegate;

    public abstract P createPresenter();

    public V getMvpView() {
        return this;
    }

    public MviRecyclerView(Context context) {
        super(context);
    }

    public MviRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MviRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    @NonNull
    public ViewGroupMviDelegate<V, P> getMvpDelegate() {
        if (this.mvpDelegate == null) {
            this.mvpDelegate = new ViewGroupMviDelegateImpl(this, this, true);
        }
        return this.mvpDelegate;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getMvpDelegate().a();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getMvpDelegate().b();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"MissingSuperCall"})
    public Parcelable onSaveInstanceState() {
        return getMvpDelegate().c();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"MissingSuperCall"})
    public void onRestoreInstanceState(Parcelable parcelable) {
        getMvpDelegate().a(parcelable);
    }

    public final Parcelable superOnSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    public final void superOnRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(parcelable);
    }

    public void setRestoringViewState(boolean z) {
        this.f20680a = z;
    }

    /* access modifiers changed from: protected */
    public boolean isRestoringViewState() {
        return this.f20680a;
    }
}
