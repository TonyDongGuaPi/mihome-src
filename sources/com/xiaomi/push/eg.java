package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;

public class eg extends ef {

    /* renamed from: a  reason: collision with root package name */
    private boolean f12707a;
    private boolean b;
    private boolean e;
    private boolean f;

    public eg(Context context, int i, boolean z, boolean z2, boolean z3, boolean z4) {
        super(context, i);
        this.f12707a = z;
        this.b = z2;
        if (l.g()) {
            this.b = false;
        }
        this.e = z3;
        this.f = z4;
    }

    private String a(Context context) {
        return !this.f ? "off" : "";
    }

    private String f() {
        if (!this.f12707a) {
            return "off";
        }
        try {
            String g = g();
            if (TextUtils.isEmpty(g)) {
                return "";
            }
            return bf.a(g) + "," + bf.b(g);
        } catch (Throwable unused) {
            return "";
        }
    }

    private String g() {
        return "";
    }

    private String h() {
        return !this.b ? "off" : "";
    }

    private String i() {
        return !this.e ? "off" : "";
    }

    public int a() {
        return 13;
    }

    public String b() {
        return f() + "|" + h() + "|" + i() + "|" + a(this.d);
    }

    public hq c() {
        return hq.DeviceBaseInfo;
    }
}
