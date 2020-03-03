package com.hannesdorfmann.mosby3.mvi;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface MviPresenter<V extends MvpView, VS> extends MvpPresenter<V> {
}
