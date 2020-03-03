package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import java.util.Map;

public class au implements ar {

    /* renamed from: a  reason: collision with root package name */
    private static volatile au f12635a;
    private ar b;

    private au(Context context) {
        this.b = at.a(context);
        b.a("create id manager is: " + this.b);
    }

    public static au a(Context context) {
        if (f12635a == null) {
            synchronized (au.class) {
                if (f12635a == null) {
                    f12635a = new au(context.getApplicationContext());
                }
            }
        }
        return f12635a;
    }

    private String a(String str) {
        return str == null ? "" : str;
    }

    public void a(Map<String, String> map) {
        if (map != null) {
            String b2 = b();
            if (!TextUtils.isEmpty(b2)) {
                map.put("udid", b2);
            }
            String c = c();
            if (!TextUtils.isEmpty(c)) {
                map.put("oaid", c);
            }
            String d = d();
            if (!TextUtils.isEmpty(d)) {
                map.put("vaid", d);
            }
            String e = e();
            if (!TextUtils.isEmpty(e)) {
                map.put("aaid", e);
            }
        }
    }

    public boolean a() {
        return this.b.a();
    }

    public String b() {
        return a(this.b.b());
    }

    public String c() {
        return a(this.b.c());
    }

    public String d() {
        return a(this.b.d());
    }

    public String e() {
        return a(this.b.e());
    }
}
