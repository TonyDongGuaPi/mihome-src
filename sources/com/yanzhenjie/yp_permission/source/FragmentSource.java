package com.yanzhenjie.yp_permission.source;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class FragmentSource extends Source {

    /* renamed from: a  reason: collision with root package name */
    private Fragment f2472a;

    public FragmentSource(Fragment fragment) {
        this.f2472a = fragment;
    }

    public Context a() {
        return this.f2472a.getActivity();
    }

    public void a(Intent intent) {
        this.f2472a.startActivity(intent);
    }

    public void a(Intent intent, int i) {
        this.f2472a.startActivityForResult(intent, i);
    }

    public boolean a(String str) {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        return this.f2472a.shouldShowRequestPermissionRationale(str);
    }
}
