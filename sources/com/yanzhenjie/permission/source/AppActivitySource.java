package com.yanzhenjie.permission.source;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class AppActivitySource extends Source {

    /* renamed from: a  reason: collision with root package name */
    private Activity f2423a;

    public AppActivitySource(Activity activity) {
        this.f2423a = activity;
    }

    public Context a() {
        return this.f2423a;
    }

    public void a(Intent intent) {
        try {
            this.f2423a.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(Intent intent, int i) {
        try {
            this.f2423a.startActivityForResult(intent, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
