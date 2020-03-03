package com.yanzhenjie.permission.source;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;

public class FragmentSource extends Source {

    /* renamed from: a  reason: collision with root package name */
    private Fragment f2425a;

    public FragmentSource(Fragment fragment) {
        this.f2425a = fragment;
    }

    public Context a() {
        return this.f2425a.getActivity();
    }

    public void a(Intent intent) {
        this.f2425a.startActivity(intent);
    }

    public void a(Intent intent, int i) {
        this.f2425a.startActivityForResult(intent, i);
    }
}
