package com.xiaomi.mistatistic.sdk;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.mistatistic.sdk.controller.LocalEventRecorder;
import com.xiaomi.mistatistic.sdk.controller.a;
import com.xiaomi.mistatistic.sdk.controller.c;
import com.xiaomi.mistatistic.sdk.controller.d;
import com.xiaomi.mistatistic.sdk.controller.e;
import com.xiaomi.mistatistic.sdk.controller.f;
import com.xiaomi.mistatistic.sdk.controller.h;
import com.xiaomi.mistatistic.sdk.controller.l;
import com.xiaomi.mistatistic.sdk.controller.m;
import com.xiaomi.mistatistic.sdk.controller.o;
import com.xiaomi.mistatistic.sdk.controller.p;
import com.xiaomi.mistatistic.sdk.controller.q;
import com.xiaomi.mistatistic.sdk.data.AbstractEvent;
import com.xiaomi.mistatistic.sdk.data.g;
import java.util.Map;

public abstract class MiStatInterface {

    /* renamed from: a  reason: collision with root package name */
    public static final int f1493a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final long g = 60000;
    public static final long h = 86400000;
    private static boolean i = false;
    private static boolean j = false;
    private static final String k = "default_category";

    public static final void a(Context context, String str, String str2, String str3) {
        a(context, str, str2, str3, false);
    }

    public static final void a(Context context, String str, String str2, String str3, boolean z) {
        Log.d("MI_STAT", String.format("initialize %s, %s, %s, %s", new Object[]{context.getPackageName(), str, str3, Boolean.valueOf(z)}));
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("appID or appKey is empty.");
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = "mistats_default";
        }
        c.a(context, str, str2, str3);
        f.a();
        new e().a();
        p.a().b();
        i = true;
        if (z) {
            URLStatsRecorder.a();
        }
    }

    public static void a(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        if (!q.a(context)) {
            Log.w("MI_STAT", "ABTest is NOT allow to register in background running");
            return;
        }
        a.a().b();
        j = true;
        Log.i("MI_STAT", "ABTest register success");
    }

    public static final String b(Context context) {
        return new e().a();
    }

    public static final void a(int i2, long j2) {
        g();
        if (i2 != 4 || (j2 >= 60000 && j2 <= 86400000)) {
            p.a().a(i2, j2);
            return;
        }
        throw new IllegalArgumentException("interval should be set between 1 minutes and 1 day");
    }

    public static final int a() {
        g();
        return p.a().g();
    }

    public static final void b() {
        h.a();
    }

    private static final String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str.contains(",") ? str.replace(",", "") : str;
    }

    public static final void a(Context context, String str, String str2) {
        g();
        if (!TextUtils.isEmpty(str) || (context instanceof Activity)) {
            d.a().a((d.a) new com.xiaomi.mistatistic.sdk.controller.asyncjobs.a());
            m.a().a(context, b(str), b(str2));
            if (context instanceof Activity) {
                o a2 = o.a();
                if (a2.b()) {
                    a2.a((Activity) context);
                    return;
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException("pageName should't be null");
    }

    public static final void a(Activity activity, String str) {
        g();
        d.a().a((d.a) new com.xiaomi.mistatistic.sdk.controller.asyncjobs.a());
        m.a().a((Context) activity, b(str), "");
        o a2 = o.a();
        if (a2.b()) {
            a2.a(activity);
        }
    }

    public static final void a(Context context, String str) {
        a(context, str, "");
    }

    public static final void c() {
        b((Context) null, "");
    }

    public static final void b(Context context, String str) {
        g();
        m.a().a(context, str);
        o a2 = o.a();
        if (a2.e()) {
            a2.f();
        }
    }

    public static final void a(String str, String str2) {
        a(str, str2, (Map<String, String>) null);
    }

    public static final void a(String str, String str2, Map<String, String> map) {
        g();
        b(str, str2);
        if (TextUtils.isEmpty(str)) {
            str = k;
        }
        LocalEventRecorder.a((AbstractEvent) new com.xiaomi.mistatistic.sdk.data.e(str, str2, map));
        com.xiaomi.mistatistic.sdk.controller.asyncjobs.a.b();
    }

    public static final void a(String str, String str2, long j2) {
        a(str, str2, j2, (Map<String, String>) null);
    }

    public static final void a(String str, String str2, long j2, Map<String, String> map) {
        g();
        b(str, str2);
        if (TextUtils.isEmpty(str)) {
            str = k;
        }
        LocalEventRecorder.a((AbstractEvent) new com.xiaomi.mistatistic.sdk.data.c(str, str2, j2, map));
        com.xiaomi.mistatistic.sdk.controller.asyncjobs.a.b();
    }

    public static final void b(String str, String str2, long j2) {
        g();
        b(str, str2);
        if (TextUtils.isEmpty(str)) {
            str = k;
        }
        LocalEventRecorder.a((AbstractEvent) new g(str, str2, j2));
        com.xiaomi.mistatistic.sdk.controller.asyncjobs.a.b();
    }

    public static final void a(String str, String str2, String str3) {
        g();
        b(str, str2);
        if (TextUtils.isEmpty(str)) {
            str = k;
        }
        LocalEventRecorder.a((AbstractEvent) new com.xiaomi.mistatistic.sdk.data.h(str, str2, str3));
        com.xiaomi.mistatistic.sdk.controller.asyncjobs.a.b();
    }

    public static final void d() {
        g();
        new l().a();
    }

    private static void g() {
        if (!i) {
            throw new IllegalStateException("not initialized, do you forget to call initialize when application started?");
        }
    }

    private static boolean h() {
        if (!j) {
            h.d("ABTEST - ABTest  is not registed, do you forget to call ABTestRegister when application started?");
        }
        return j;
    }

    private static void b(String str, String str2) {
        if (!TextUtils.isEmpty(str) && str.startsWith("mistat_")) {
            throw new IllegalArgumentException("category cannot start with mistat_");
        } else if (!TextUtils.isEmpty(str2) && str2.startsWith("mistat_")) {
            throw new IllegalArgumentException("key cannot start with mistat_");
        }
    }

    public static void a(boolean z) {
        if (!e()) {
            b.a(false);
        }
        b.a(z ? 2 : 3);
    }

    public static boolean e() {
        return b.d() != 1;
    }

    public static boolean f() {
        return b.d() == 2;
    }

    private static void i() {
        h.a("log event without pageStart/pageEnd, ignore.");
        if (c(c.a())) {
            throw new RuntimeException("record pageStart/pageEnd before recording events.");
        }
    }

    private static boolean c(Context context) {
        try {
            if ((context.getApplicationInfo().flags & 2) != 0) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void b(boolean z) {
        g();
        if (!q.d(c.a())) {
            q.a(z);
        }
    }

    protected static void a(String str) {
        g();
        if (!TextUtils.isEmpty(str)) {
            LocalEventRecorder.a((AbstractEvent) new com.xiaomi.mistatistic.sdk.data.d(str));
        }
        com.xiaomi.mistatistic.sdk.controller.asyncjobs.a.b();
    }

    public static void a(final Throwable th) {
        d.b().a((d.a) new d.a() {
            public void a() {
                new b().a(Thread.currentThread(), th);
            }
        });
        com.xiaomi.mistatistic.sdk.controller.asyncjobs.a.b();
    }

    public static final void a(int i2, String str, String str2) {
        g();
        if (h()) {
            a.a().b(str, str2, i2);
        }
    }

    public static String b(int i2, String str, String str2) {
        g();
        if (!h()) {
            return str2;
        }
        return a.a().a(i2, str, str2);
    }

    public static void a(int i2, String str) {
        g();
        if (h()) {
            a.a().a(str, i2);
        }
    }

    public static void a(int i2, String str, long j2) {
        g();
        if (h()) {
            a.a().a(str, j2, i2);
            com.xiaomi.mistatistic.sdk.controller.asyncjobs.a.b();
        }
    }

    public static void c(int i2, String str, String str2) {
        g();
        if (h()) {
            a.a().a(str, str2, i2);
            com.xiaomi.mistatistic.sdk.controller.asyncjobs.a.b();
        }
    }

    public static void b(int i2, String str, long j2) {
        g();
        if (h()) {
            a.a().b(str, j2, i2);
            com.xiaomi.mistatistic.sdk.controller.asyncjobs.a.b();
        }
    }
}
