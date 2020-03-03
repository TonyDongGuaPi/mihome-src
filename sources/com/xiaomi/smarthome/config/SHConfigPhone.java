package com.xiaomi.smarthome.config;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.smarthome.application.SHApplication;

public class SHConfigPhone {
    private static SHConfigPhone c;

    /* renamed from: a  reason: collision with root package name */
    private SharedPreferences f13953a;
    private Context b = SHApplication.getAppContext();

    public static SHConfigPhone a() {
        if (c == null) {
            c = new SHConfigPhone();
        }
        return c;
    }

    private SHConfigPhone() {
    }

    private void b() {
        if (this.f13953a == null) {
            this.f13953a = this.b.getSharedPreferences("SHConfigPhone", 0);
        }
    }

    public int a(String str) {
        b();
        return this.f13953a.getInt(str, -1);
    }

    public void a(String str, int i) {
        b();
        SharedPreferences.Editor edit = this.f13953a.edit();
        edit.remove(str);
        edit.putInt(str, i);
        edit.apply();
    }

    public long b(String str) {
        b();
        return this.f13953a.getLong(str, -1);
    }

    public void a(String str, long j) {
        b();
        SharedPreferences.Editor edit = this.f13953a.edit();
        edit.remove(str);
        edit.putLong(str, j);
        edit.apply();
    }

    public String c(String str) {
        b();
        return this.f13953a.getString(str, "");
    }

    public void a(String str, String str2) {
        b();
        SharedPreferences.Editor edit = this.f13953a.edit();
        edit.remove(str);
        edit.putString(str, str2);
        edit.apply();
    }
}
