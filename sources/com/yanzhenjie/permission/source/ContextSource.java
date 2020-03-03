package com.yanzhenjie.permission.source;

import android.content.Context;
import android.content.Intent;

public class ContextSource extends Source {

    /* renamed from: a  reason: collision with root package name */
    private Context f2424a;

    public ContextSource(Context context) {
        this.f2424a = context;
    }

    public Context a() {
        return this.f2424a;
    }

    public void a(Intent intent) {
        this.f2424a.startActivity(intent);
    }

    public void a(Intent intent, int i) {
        this.f2424a.startActivity(intent);
    }
}
