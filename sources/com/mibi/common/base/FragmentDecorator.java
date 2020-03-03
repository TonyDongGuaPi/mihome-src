package com.mibi.common.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public interface FragmentDecorator extends Decorator {
    void a();

    void a(int i, int i2, Intent intent);

    void a(Activity activity);

    void a(Bundle bundle);

    void a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    void a(DecoratableFragment decoratableFragment);

    void b();

    void b(Bundle bundle);

    void c();

    void c(Bundle bundle);

    void d();

    void e();

    void f();
}
