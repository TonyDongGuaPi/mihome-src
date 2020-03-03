package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;

public class ej extends ef {

    /* renamed from: a  reason: collision with root package name */
    private SharedPreferences f12709a;

    public ej(Context context, int i) {
        super(context, i);
        this.f12709a = context.getSharedPreferences("mipush_extra", 0);
    }

    public int a() {
        return 9;
    }

    public String b() {
        return null;
    }

    public hq c() {
        return hq.TopApp;
    }
}
