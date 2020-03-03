package com.hannesdorfmann.mosby3;

import android.os.Bundle;
import com.hannesdorfmann.mosby3.mvi.MviPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface ActivityMviDelegate<V extends MvpView, P extends MviPresenter<V, ?>> {
    void a();

    void a(Bundle bundle);

    void b();

    void b(Bundle bundle);

    void c();

    void c(Bundle bundle);

    void d();

    void e();

    void f();

    void g();

    Object h();
}
