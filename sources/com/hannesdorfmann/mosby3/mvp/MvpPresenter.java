package com.hannesdorfmann.mosby3.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface MvpPresenter<V extends MvpView> {
    @UiThread
    void a(@NonNull V v);

    @UiThread
    @Deprecated
    void a(boolean z);

    @UiThread
    void b();

    @UiThread
    void c();
}
