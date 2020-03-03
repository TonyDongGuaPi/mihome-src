package com.xiaomi.youpin.common.cache;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.youpin.log.LogUtils;
import java.util.HashSet;
import java.util.Set;

public class SharedPreferencesCache implements ICache<String> {

    /* renamed from: a  reason: collision with root package name */
    static final String f23232a = "SharedPreferencesCache";
    SharedPreferences b;
    String c;
    Context d;

    public SharedPreferencesCache(Context context, String str) {
        this.d = context.getApplicationContext();
        this.c = str;
    }

    /* access modifiers changed from: package-private */
    public void e() {
        if (this.b == null) {
            long currentTimeMillis = System.currentTimeMillis();
            this.b = this.d.getSharedPreferences(this.c, 0);
            String str = f23232a;
            LogUtils.d(str, this.c + " getSharedPreferences time:" + (System.currentTimeMillis() - currentTimeMillis));
        }
    }

    public Set<String> a() {
        e();
        return new HashSet(this.b.getAll().keySet());
    }

    /* renamed from: a */
    public String d(String str) {
        e();
        return this.b.getString(str, "");
    }

    public boolean b(String str) {
        e();
        return this.b.contains(str);
    }

    public boolean a(String str, String str2) {
        e();
        this.b.edit().putString(str, str2).apply();
        return true;
    }

    public boolean c(String str) {
        e();
        this.b.edit().remove(str).apply();
        return true;
    }

    public void b() {
        e();
        this.b.edit().clear().commit();
    }

    public void c() {
        d();
    }

    public void d() {
        if (this.b != null) {
            long currentTimeMillis = System.currentTimeMillis();
            this.b.edit().commit();
            String str = f23232a;
            LogUtils.d(str, this.c + "commit time:" + (System.currentTimeMillis() - currentTimeMillis));
        }
    }
}
