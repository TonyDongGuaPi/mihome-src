package org.mp4parser.aspectj.runtime.internal;

import com.mobikwik.sdk.lib.Constants;
import com.xiaomi.stat.b;
import org.mp4parser.aspectj.runtime.internal.cflowstack.ThreadCounter;
import org.mp4parser.aspectj.runtime.internal.cflowstack.ThreadStackFactory;
import org.mp4parser.aspectj.runtime.internal.cflowstack.ThreadStackFactoryImpl;
import org.mp4parser.aspectj.runtime.internal.cflowstack.ThreadStackFactoryImpl11;

public class CFlowCounter {

    /* renamed from: a  reason: collision with root package name */
    private static ThreadStackFactory f3761a;
    private ThreadCounter b = f3761a.b();

    static {
        g();
    }

    public void a() {
        this.b.a();
    }

    public void b() {
        this.b.b();
        if (!this.b.c()) {
            this.b.d();
        }
    }

    public boolean c() {
        return this.b.c();
    }

    private static ThreadStackFactory e() {
        return new ThreadStackFactoryImpl();
    }

    private static ThreadStackFactory f() {
        return new ThreadStackFactoryImpl11();
    }

    private static void g() {
        String a2 = a("aspectj.runtime.cflowstack.usethreadlocal", "unspecified");
        boolean z = false;
        if (!a2.equals("unspecified") ? a2.equals(Constants.YES) || a2.equals("true") : System.getProperty("java.class.version", b.m).compareTo("46.0") >= 0) {
            z = true;
        }
        if (z) {
            f3761a = e();
        } else {
            f3761a = f();
        }
    }

    private static String a(String str, String str2) {
        try {
            return System.getProperty(str, str2);
        } catch (SecurityException unused) {
            return str2;
        }
    }

    public static String d() {
        return f3761a.getClass().getName();
    }
}
