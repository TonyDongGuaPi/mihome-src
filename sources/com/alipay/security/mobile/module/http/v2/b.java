package com.alipay.security.mobile.module.http.v2;

import android.content.Context;
import com.alipay.security.mobile.module.http.a;
import com.alipay.security.mobile.module.http.d;
import com.alipay.security.mobile.module.http.model.c;

public class b implements a {

    /* renamed from: a  reason: collision with root package name */
    private static a f1176a;
    private static a b;

    public static a a(Context context, String str) {
        if (context == null) {
            return null;
        }
        if (f1176a == null) {
            b = d.a(context, str);
            f1176a = new b();
        }
        return f1176a;
    }

    public c a(com.alipay.security.mobile.module.http.model.d dVar) {
        return com.alipay.security.mobile.module.http.model.b.a(b.a(com.alipay.security.mobile.module.http.model.b.a(dVar)));
    }

    public boolean a(String str) {
        return b.a(str);
    }
}
