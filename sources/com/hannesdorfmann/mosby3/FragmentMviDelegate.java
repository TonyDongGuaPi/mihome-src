package com.hannesdorfmann.mosby3;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import com.hannesdorfmann.mosby3.mvi.MviPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface FragmentMviDelegate<V extends MvpView, P extends MviPresenter<V, ?>> {
    void a();

    void a(Activity activity);

    void a(Context context);

    void a(Configuration configuration);

    void a(Bundle bundle);

    void a(Fragment fragment);

    void a(View view, @Nullable Bundle bundle);

    void b();

    void b(Bundle bundle);

    void c();

    void c(Bundle bundle);

    void d();

    void e();

    void f();

    void g();
}
