package com.ximalaya.ting.android.opensdk.player.service;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.FloatRange;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;

public class XmPlayerConfig {

    /* renamed from: a  reason: collision with root package name */
    private static final int f2210a = 0;
    private static XmPlayerConfig b;
    private static byte[] c = new byte[0];
    private static boolean d = false;
    private static boolean e = true;
    private Context f;
    private SharedPreferences g;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private float k = 0.0f;

    private XmPlayerConfig(Context context) {
        this.f = context.getApplicationContext();
        m();
    }

    public static XmPlayerConfig a(Context context) {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new XmPlayerConfig(context);
                }
            }
        }
        return b;
    }

    private void m() {
        this.g = this.f.getSharedPreferences(PreferenceConstantsInOpenSdk.j, 0);
    }

    public boolean a() {
        return this.g.getBoolean(PreferenceConstantsInOpenSdk.l, true);
    }

    public void a(boolean z) {
        a(this.g.edit().putBoolean(PreferenceConstantsInOpenSdk.l, z));
    }

    public boolean b() {
        return this.g.getBoolean(PreferenceConstantsInOpenSdk.m, false);
    }

    public void b(boolean z) {
        a(this.g.edit().putBoolean(PreferenceConstantsInOpenSdk.m, z));
    }

    public boolean c() {
        return this.g.getBoolean(PreferenceConstantsInOpenSdk.n, false);
    }

    public void c(boolean z) {
        a(this.g.edit().putBoolean(PreferenceConstantsInOpenSdk.n, z));
    }

    /* access modifiers changed from: protected */
    public boolean d() {
        return this.g.getBoolean(PreferenceConstantsInOpenSdk.k, false);
    }

    /* access modifiers changed from: protected */
    public void d(boolean z) {
        a(this.g.edit().putBoolean(PreferenceConstantsInOpenSdk.k, z));
    }

    public boolean e() {
        return d();
    }

    public boolean f() {
        if (!this.h) {
            this.h = this.g.getBoolean(PreferenceConstantsInOpenSdk.o, true);
        }
        return this.h;
    }

    public void e(boolean z) {
        a(this.g.edit().putBoolean(PreferenceConstantsInOpenSdk.o, z));
    }

    public boolean g() {
        if (!this.j) {
            this.j = this.g.getBoolean(PreferenceConstantsInOpenSdk.p, true);
        }
        return this.i;
    }

    public void f(boolean z) {
        a(this.g.edit().putBoolean(PreferenceConstantsInOpenSdk.p, z));
    }

    public boolean h() {
        if (!this.j) {
            this.j = this.g.getBoolean(PreferenceConstantsInOpenSdk.q, true);
        }
        return this.j;
    }

    public void g(boolean z) {
        a(this.g.edit().putBoolean(PreferenceConstantsInOpenSdk.q, z));
    }

    public float i() {
        if (this.k != 0.0f) {
            return this.k;
        }
        this.k = this.g.getFloat(PreferenceConstantsInOpenSdk.r, 0.3f);
        return this.k;
    }

    public void a(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        a(this.g.edit().putFloat(PreferenceConstantsInOpenSdk.r, f2));
    }

    public void h(boolean z) {
        d = z;
        a(this.g.edit().putBoolean(PreferenceConstantsInOpenSdk.s, z));
    }

    public boolean j() {
        if (!d) {
            d = this.g.getBoolean(PreferenceConstantsInOpenSdk.s, false);
        }
        return d;
    }

    public boolean k() {
        if (e) {
            e = this.g.getBoolean(PreferenceConstantsInOpenSdk.t, true);
        }
        return e;
    }

    public void i(boolean z) {
        e = z;
        a(this.g.edit().putBoolean(PreferenceConstantsInOpenSdk.t, z));
    }

    @TargetApi(9)
    private void a(SharedPreferences.Editor editor) {
        if (Build.VERSION.SDK_INT >= 9) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public void l() {
        synchronized (c) {
            b = null;
        }
    }
}
