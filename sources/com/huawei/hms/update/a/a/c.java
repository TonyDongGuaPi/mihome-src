package com.huawei.hms.update.a.a;

import android.content.Context;
import android.content.SharedPreferences;

public class c {

    /* renamed from: a  reason: collision with root package name */
    public int f5902a = 0;
    public String b = "";
    public int c = 0;
    public String d = "";

    public c() {
    }

    public c(int i, String str, int i2, String str2) {
        this.f5902a = i;
        this.b = str;
        this.c = i2;
        this.d = str2;
    }

    public void a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.huawei.hms.update.UPDATE_INFO", 0);
        this.f5902a = sharedPreferences.getInt("mNewVersionCode", 0);
        this.b = sharedPreferences.getString("mUri", "");
        this.c = sharedPreferences.getInt("mSize", 0);
        this.d = sharedPreferences.getString("mHash", "");
    }

    public void b(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("com.huawei.hms.update.UPDATE_INFO", 0).edit();
        edit.putInt("mNewVersionCode", this.f5902a);
        edit.putString("mUri", this.b);
        edit.putInt("mSize", this.c);
        edit.putString("mHash", this.d);
        edit.commit();
    }

    public void c(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("com.huawei.hms.update.UPDATE_INFO", 0).edit();
        edit.clear();
        edit.commit();
    }

    public boolean a() {
        return this.f5902a > 0 && this.c > 0 && this.b != null && !this.b.isEmpty();
    }
}
