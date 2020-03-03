package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import com.xiaomi.push.ab;
import com.xiaomi.youpin.UserMode;

public class at {

    /* renamed from: a  reason: collision with root package name */
    private static at f12890a;

    /* renamed from: a  reason: collision with other field name */
    private int f300a = 0;

    /* renamed from: a  reason: collision with other field name */
    private Context f301a;

    private at(Context context) {
        this.f301a = context.getApplicationContext();
    }

    public static at a(Context context) {
        if (f12890a == null) {
            f12890a = new at(context);
        }
        return f12890a;
    }

    @SuppressLint({"NewApi"})
    public int a() {
        if (this.f300a != 0) {
            return this.f300a;
        }
        this.f300a = Build.VERSION.SDK_INT >= 17 ? Settings.Global.getInt(this.f301a.getContentResolver(), "device_provisioned", 0) : Settings.Secure.getInt(this.f301a.getContentResolver(), "device_provisioned", 0);
        return this.f300a;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a  reason: collision with other method in class */
    public Uri m309a() {
        return Build.VERSION.SDK_INT >= 17 ? Settings.Global.getUriFor("device_provisioned") : Settings.Secure.getUriFor("device_provisioned");
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m310a() {
        return ab.f12622a.contains("xmsf") || ab.f12622a.contains(UserMode.f23179a) || ab.f12622a.contains("miui");
    }
}
