package com.xiaomi.youpin.common.cache;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.util.Set;

public class StringCache implements ICache<String> {

    /* renamed from: a  reason: collision with root package name */
    MemoryCache<String> f23233a;
    ICache<String> b;

    public static StringCache a(int i) {
        return new StringCache(new MemoryCache(i), false, -1);
    }

    public static StringCache a(Context context, String str, int i) {
        return a(context, str, i, true);
    }

    public static StringCache a(Context context, String str, int i, boolean z) {
        if (!str.startsWith("/")) {
            str = new File(context.getCacheDir(), str).getAbsolutePath();
        }
        return new StringCache(new FileCache(str, i), z, i >= 0 ? i / 2 : 10);
    }

    public static FileCache b(Context context, String str, int i) {
        String str2;
        if (!str.startsWith("/")) {
            File externalCacheDir = context.getExternalCacheDir();
            if (!"mounted".equals(Environment.getExternalStorageState()) || externalCacheDir == null) {
                str2 = context.getCacheDir().toString();
            } else {
                str2 = externalCacheDir.getAbsolutePath();
            }
            str = new File(str2, str).getAbsolutePath();
        }
        return new FileCache(str, i);
    }

    public static StringCache a(Context context, String str) {
        return new StringCache(new DBCache(context, str), true, 50);
    }

    public static StringCache b(Context context, String str) {
        return new StringCache(new SharedPreferencesCache(context, str), false, -1);
    }

    public StringCache(ICache<String> iCache, boolean z, int i) {
        this.b = iCache;
        if (iCache instanceof MemoryCache ? false : z) {
            this.f23233a = new MemoryCache<>(i);
        }
    }

    public Set<String> a() {
        return this.b.a();
    }

    /* renamed from: a */
    public String d(String str) {
        String d = this.f23233a != null ? this.f23233a.d(str) : null;
        if (d == null) {
            d = this.b.d(str);
            if (this.f23233a != null) {
                this.f23233a.a(str, d);
            }
        }
        return d;
    }

    public boolean b(String str) {
        String d;
        boolean b2 = this.f23233a != null ? this.f23233a.b(str) : false;
        if (!b2 && (d = this.b.d(str)) != null) {
            b2 = true;
            if (this.f23233a != null) {
                this.f23233a.a(str, d);
            }
        }
        return b2;
    }

    public boolean a(String str, String str2) {
        boolean a2 = this.b.a(str, str2);
        if (a2) {
            if (this.f23233a != null) {
                this.f23233a.a(str, str2);
            }
        } else if (this.f23233a != null) {
            this.f23233a.c(str);
        }
        return a2;
    }

    public boolean c(String str) {
        boolean c = this.b.c(str);
        if (this.f23233a != null) {
            this.f23233a.c(str);
        }
        return c;
    }

    public void b() {
        this.b.b();
        if (this.f23233a != null) {
            this.f23233a.b();
        }
    }

    public void c() {
        this.b.c();
        if (this.f23233a != null) {
            this.f23233a.b();
        }
    }

    public void d() {
        this.b.d();
        if (this.f23233a != null) {
            this.f23233a.d();
        }
    }
}
