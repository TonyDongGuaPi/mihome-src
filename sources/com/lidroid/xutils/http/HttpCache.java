package com.lidroid.xutils.http;

import android.text.TextUtils;
import com.lidroid.xutils.cache.LruMemoryCache;
import com.lidroid.xutils.http.client.HttpRequest;
import java.util.concurrent.ConcurrentHashMap;

public class HttpCache {
    private static final int b = 102400;
    private static final long c = 60000;
    private static long e = 60000;
    private static final ConcurrentHashMap<String, Boolean> f = new ConcurrentHashMap<>(10);

    /* renamed from: a  reason: collision with root package name */
    private final LruMemoryCache<String, String> f6330a;
    private int d;

    public HttpCache() {
        this(102400, 60000);
    }

    public HttpCache(int i, long j) {
        this.d = 102400;
        this.d = i;
        e = j;
        this.f6330a = new LruMemoryCache<String, String>(this.d) {
            /* access modifiers changed from: protected */
            public int a(String str, String str2) {
                if (str2 == null) {
                    return 0;
                }
                return str2.length();
            }
        };
    }

    public void a(int i) {
        this.f6330a.a(i);
    }

    public static void a(long j) {
        e = j;
    }

    public static long a() {
        return e;
    }

    public void a(String str, String str2) {
        a(str, str2, e);
    }

    public void a(String str, String str2, long j) {
        if (str != null && str2 != null && j >= 1) {
            this.f6330a.a(str, str2, System.currentTimeMillis() + j);
        }
    }

    public String a(String str) {
        if (str != null) {
            return this.f6330a.a(str);
        }
        return null;
    }

    public void b() {
        this.f6330a.a();
    }

    public boolean a(HttpRequest.HttpMethod httpMethod) {
        Boolean bool;
        if (httpMethod == null || (bool = f.get(httpMethod.toString())) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean b(String str) {
        Boolean bool;
        if (!TextUtils.isEmpty(str) && (bool = f.get(str.toUpperCase())) != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public void a(HttpRequest.HttpMethod httpMethod, boolean z) {
        f.put(httpMethod.toString(), Boolean.valueOf(z));
    }

    static {
        f.put(HttpRequest.HttpMethod.GET.toString(), true);
    }
}
