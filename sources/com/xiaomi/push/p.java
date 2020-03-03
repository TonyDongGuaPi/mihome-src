package com.xiaomi.push;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class p {

    /* renamed from: a  reason: collision with root package name */
    private static volatile p f12839a;
    /* access modifiers changed from: private */
    public Context b;
    private Handler c = new Handler(Looper.getMainLooper());
    private Map<String, Map<String, String>> d = new HashMap();

    private p(Context context) {
        this.b = context;
    }

    public static p a(Context context) {
        if (f12839a == null) {
            synchronized (p.class) {
                if (f12839a == null) {
                    f12839a = new p(context);
                }
            }
        }
        return f12839a;
    }

    private synchronized String a(String str, String str2) {
        if (this.d == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        try {
            Map map = this.d.get(str);
            if (map == null) {
                return "";
            }
            return (String) map.get(str2);
        } catch (Throwable unused) {
            return "";
        }
    }

    private synchronized void c(String str, String str2, String str3) {
        if (this.d == null) {
            this.d = new HashMap();
        }
        Map map = this.d.get(str);
        if (map == null) {
            map = new HashMap();
        }
        map.put(str2, str3);
        this.d.put(str, map);
    }

    public synchronized void a(String str, String str2, String str3) {
        c(str, str2, str3);
        this.c.post(new q(this, str, str2, str3));
    }

    public synchronized String b(String str, String str2, String str3) {
        String a2 = a(str, str2);
        if (!TextUtils.isEmpty(a2)) {
            return a2;
        }
        return this.b.getSharedPreferences(str, 4).getString(str2, str3);
    }
}
