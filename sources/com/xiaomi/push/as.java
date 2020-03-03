package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.stat.c.c;
import java.lang.reflect.Method;

class as implements ar {

    /* renamed from: a  reason: collision with root package name */
    private Context f12634a;
    private Class<?> b;
    private Object c;
    private Method d = null;
    private Method e = null;
    private Method f = null;
    private Method g = null;

    public as(Context context) {
        this.f12634a = context;
        b(context);
    }

    private String a(Context context, Method method) {
        if (this.c == null || method == null) {
            return null;
        }
        try {
            Object invoke = method.invoke(this.c, new Object[]{context});
            if (invoke != null) {
                return (String) invoke;
            }
            return null;
        } catch (Exception e2) {
            b.a("miui invoke error", (Throwable) e2);
            return null;
        }
    }

    public static boolean a(Context context) {
        return c.f23036a.equals(context.getPackageName());
    }

    private void b(Context context) {
        try {
            this.b = t.a(context, "com.android.id.impl.IdProviderImpl");
            this.c = this.b.newInstance();
            this.d = this.b.getMethod("getUDID", new Class[]{Context.class});
            this.e = this.b.getMethod("getOAID", new Class[]{Context.class});
            this.f = this.b.getMethod("getVAID", new Class[]{Context.class});
            this.g = this.b.getMethod("getAAID", new Class[]{Context.class});
        } catch (Exception e2) {
            b.a("miui load class error", (Throwable) e2);
        }
    }

    public boolean a() {
        return (this.b == null || this.c == null) ? false : true;
    }

    public String b() {
        return a(this.f12634a, this.d);
    }

    public String c() {
        return a(this.f12634a, this.e);
    }

    public String d() {
        return a(this.f12634a, this.f);
    }

    public String e() {
        return a(this.f12634a, this.g);
    }
}
