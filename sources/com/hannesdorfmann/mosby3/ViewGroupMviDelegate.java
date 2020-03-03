package com.hannesdorfmann.mosby3;

import android.os.Parcelable;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface ViewGroupMviDelegate<V extends MvpView, P extends MvpPresenter<V>> {
    void a();

    void a(Parcelable parcelable);

    void b();

    Parcelable c();
}
