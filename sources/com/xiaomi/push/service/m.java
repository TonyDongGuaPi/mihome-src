package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.push.bf;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class m {

    /* renamed from: a  reason: collision with root package name */
    private static m f12932a;

    /* renamed from: a  reason: collision with other field name */
    private Context f348a;

    /* renamed from: a  reason: collision with other field name */
    private List<String> f349a = new ArrayList();
    private final List<String> b = new ArrayList();
    private final List<String> c = new ArrayList();

    private m(Context context) {
        this.f348a = context.getApplicationContext();
        if (this.f348a == null) {
            this.f348a = context;
        }
        SharedPreferences sharedPreferences = this.f348a.getSharedPreferences("mipush_app_info", 0);
        for (String str : sharedPreferences.getString("unregistered_pkg_names", "").split(",")) {
            if (TextUtils.isEmpty(str)) {
                this.f349a.add(str);
            }
        }
        for (String str2 : sharedPreferences.getString("disable_push_pkg_names", "").split(",")) {
            if (!TextUtils.isEmpty(str2)) {
                this.b.add(str2);
            }
        }
        for (String str3 : sharedPreferences.getString("disable_push_pkg_names_cache", "").split(",")) {
            if (!TextUtils.isEmpty(str3)) {
                this.c.add(str3);
            }
        }
    }

    public static m a(Context context) {
        if (f12932a == null) {
            f12932a = new m(context);
        }
        return f12932a;
    }

    public void a(String str) {
        synchronized (this.f349a) {
            if (!this.f349a.contains(str)) {
                this.f349a.add(str);
                this.f348a.getSharedPreferences("mipush_app_info", 0).edit().putString("unregistered_pkg_names", bf.a((Collection<?>) this.f349a, ",")).commit();
            }
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m338a(String str) {
        boolean contains;
        synchronized (this.f349a) {
            contains = this.f349a.contains(str);
        }
        return contains;
    }

    public void b(String str) {
        synchronized (this.b) {
            if (!this.b.contains(str)) {
                this.b.add(str);
                this.f348a.getSharedPreferences("mipush_app_info", 0).edit().putString("disable_push_pkg_names", bf.a((Collection<?>) this.b, ",")).commit();
            }
        }
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m339b(String str) {
        boolean contains;
        synchronized (this.b) {
            contains = this.b.contains(str);
        }
        return contains;
    }

    public void c(String str) {
        synchronized (this.c) {
            if (!this.c.contains(str)) {
                this.c.add(str);
                this.f348a.getSharedPreferences("mipush_app_info", 0).edit().putString("disable_push_pkg_names_cache", bf.a((Collection<?>) this.c, ",")).commit();
            }
        }
    }

    /* renamed from: c  reason: collision with other method in class */
    public boolean m340c(String str) {
        boolean contains;
        synchronized (this.c) {
            contains = this.c.contains(str);
        }
        return contains;
    }

    public void d(String str) {
        synchronized (this.f349a) {
            if (this.f349a.contains(str)) {
                this.f349a.remove(str);
                this.f348a.getSharedPreferences("mipush_app_info", 0).edit().putString("unregistered_pkg_names", bf.a((Collection<?>) this.f349a, ",")).commit();
            }
        }
    }

    public void e(String str) {
        synchronized (this.b) {
            if (this.b.contains(str)) {
                this.b.remove(str);
                this.f348a.getSharedPreferences("mipush_app_info", 0).edit().putString("disable_push_pkg_names", bf.a((Collection<?>) this.b, ",")).commit();
            }
        }
    }

    public void f(String str) {
        synchronized (this.c) {
            if (this.c.contains(str)) {
                this.c.remove(str);
                this.f348a.getSharedPreferences("mipush_app_info", 0).edit().putString("disable_push_pkg_names_cache", bf.a((Collection<?>) this.c, ",")).commit();
            }
        }
    }
}
