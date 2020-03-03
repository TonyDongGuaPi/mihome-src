package com.yanzhenjie.permission.source;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class SupportFragmentSource extends Source {

    /* renamed from: a  reason: collision with root package name */
    private Fragment f2426a;

    public SupportFragmentSource(Fragment fragment) {
        this.f2426a = fragment;
    }

    public Context a() {
        return this.f2426a.getContext();
    }

    public void a(Intent intent) {
        this.f2426a.startActivity(intent);
    }

    public void a(Intent intent, int i) {
        this.f2426a.startActivityForResult(intent, i);
    }
}
