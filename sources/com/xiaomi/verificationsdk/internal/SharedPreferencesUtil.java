package com.xiaomi.verificationsdk.internal;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    /* renamed from: a  reason: collision with root package name */
    private final SharedPreferences f23130a;

    public SharedPreferencesUtil(Context context, String str) {
        this.f23130a = context.getSharedPreferences(str, 0);
    }

    public String a(String str) {
        return this.f23130a.getString(str, (String) null);
    }

    public long a(String str, long j) {
        return this.f23130a.getLong(str, j);
    }

    public boolean a(String str, boolean z) {
        return this.f23130a.getBoolean(str, z);
    }

    public int a(String str, int i) {
        return this.f23130a.getInt(str, i);
    }

    public void a(String str, String str2) {
        this.f23130a.edit().putString(str, str2).commit();
    }

    public void b(String str, long j) {
        this.f23130a.edit().putLong(str, j).commit();
    }

    public void b(String str, boolean z) {
        this.f23130a.edit().putBoolean(str, z).commit();
    }

    public void b(String str, int i) {
        this.f23130a.edit().putInt(str, i).commit();
    }

    public void b(String str) {
        this.f23130a.edit().remove(str).commit();
    }

    public void a() {
        this.f23130a.edit().clear().commit();
    }
}
