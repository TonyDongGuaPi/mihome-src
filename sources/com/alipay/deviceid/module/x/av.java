package com.alipay.deviceid.module.x;

import android.content.Context;
import java.lang.reflect.Proxy;

public final class av extends bh {

    /* renamed from: a  reason: collision with root package name */
    Context f879a;

    public av(Context context) {
        this.f879a = context;
    }

    public final <T> T a(Class<T> cls, final bl blVar) {
        bi biVar = new bi(new au() {
            public final String a() {
                return blVar.f894a;
            }

            public final bl c() {
                return blVar;
            }

            public final boolean d() {
                return blVar.c;
            }

            public final bm b() {
                return ay.a(av.this.f879a.getApplicationContext());
            }
        });
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new bj(biVar.f891a, cls, biVar.b));
    }
}
