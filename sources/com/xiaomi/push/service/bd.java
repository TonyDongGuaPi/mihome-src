package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.push.af;
import com.xiaomi.push.ai;
import com.xiaomi.push.az;
import com.xiaomi.push.r;
import java.util.concurrent.ConcurrentHashMap;

public final class bd implements af {

    /* renamed from: a  reason: collision with root package name */
    private static volatile bd f12901a;

    /* renamed from: a  reason: collision with other field name */
    private long f319a;

    /* renamed from: a  reason: collision with other field name */
    Context f320a;

    /* renamed from: a  reason: collision with other field name */
    private SharedPreferences f321a;

    /* renamed from: a  reason: collision with other field name */
    private ConcurrentHashMap<String, a> f322a = new ConcurrentHashMap<>();
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with other field name */
    public volatile boolean f323a = false;

    public static abstract class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        long f12902a;

        /* renamed from: a  reason: collision with other field name */
        String f324a;

        a(String str, long j) {
            this.f324a = str;
            this.f12902a = j;
        }

        /* access modifiers changed from: package-private */
        public abstract void a(bd bdVar);

        public void run() {
            if (bd.a() != null) {
                Context context = bd.a().f320a;
                if (az.d(context)) {
                    long currentTimeMillis = System.currentTimeMillis();
                    SharedPreferences a2 = bd.a(bd.a());
                    if (currentTimeMillis - a2.getLong(":ts-" + this.f324a, 0) > this.f12902a || af.a(context)) {
                        SharedPreferences.Editor edit = bd.a(bd.a()).edit();
                        r.a(edit.putLong(":ts-" + this.f324a, System.currentTimeMillis()));
                        a(bd.a());
                    }
                }
            }
        }
    }

    private bd(Context context) {
        this.f320a = context.getApplicationContext();
        this.f321a = context.getSharedPreferences("sync", 0);
    }

    public static bd a(Context context) {
        if (f12901a == null) {
            synchronized (bd.class) {
                if (f12901a == null) {
                    f12901a = new bd(context);
                }
            }
        }
        return f12901a;
    }

    public String a(String str, String str2) {
        SharedPreferences sharedPreferences = this.f321a;
        return sharedPreferences.getString(str + ":" + str2, "");
    }

    public void a() {
        if (!this.f323a) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.f319a >= 3600000) {
                this.f319a = currentTimeMillis;
                this.f323a = true;
                ai.a(this.f320a).a((Runnable) new be(this), (int) (Math.random() * 10.0d));
            }
        }
    }

    public void a(a aVar) {
        if (this.f322a.putIfAbsent(aVar.f324a, aVar) == null) {
            ai.a(this.f320a).a((Runnable) aVar, ((int) (Math.random() * 30.0d)) + 10);
        }
    }

    public void a(String str, String str2, String str3) {
        SharedPreferences.Editor edit = f12901a.f321a.edit();
        r.a(edit.putString(str + ":" + str2, str3));
    }
}
