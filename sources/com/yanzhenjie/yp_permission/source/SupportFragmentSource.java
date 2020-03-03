package com.yanzhenjie.yp_permission.source;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;

public class SupportFragmentSource extends Source {

    /* renamed from: a  reason: collision with root package name */
    private Fragment f2474a;

    public SupportFragmentSource(Fragment fragment) {
        this.f2474a = fragment;
    }

    public Context a() {
        return this.f2474a.getContext();
    }

    public void a(Intent intent) {
        this.f2474a.startActivity(intent);
    }

    public void a(Intent intent, int i) {
        this.f2474a.startActivityForResult(intent, i);
    }

    public boolean a(String str) {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        return this.f2474a.shouldShowRequestPermissionRationale(str);
    }
}
