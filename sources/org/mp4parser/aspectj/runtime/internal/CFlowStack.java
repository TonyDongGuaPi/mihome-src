package org.mp4parser.aspectj.runtime.internal;

import com.mobikwik.sdk.lib.Constants;
import com.xiaomi.stat.b;
import java.util.Stack;
import org.mp4parser.aspectj.lang.NoAspectBoundException;
import org.mp4parser.aspectj.runtime.CFlow;
import org.mp4parser.aspectj.runtime.internal.cflowstack.ThreadStack;
import org.mp4parser.aspectj.runtime.internal.cflowstack.ThreadStackFactory;
import org.mp4parser.aspectj.runtime.internal.cflowstack.ThreadStackFactoryImpl;
import org.mp4parser.aspectj.runtime.internal.cflowstack.ThreadStackFactoryImpl11;

public class CFlowStack {

    /* renamed from: a  reason: collision with root package name */
    private static ThreadStackFactory f3763a;
    private ThreadStack b = f3763a.a();

    static {
        k();
    }

    private Stack h() {
        return this.b.a();
    }

    public void a(Object obj) {
        h().push(obj);
    }

    public void b(Object obj) {
        h().push(new CFlow(obj));
    }

    public void a(Object[] objArr) {
        h().push(new CFlowPlusState(objArr));
    }

    public void a() {
        Stack h = h();
        h.pop();
        if (h.isEmpty()) {
            this.b.b();
        }
    }

    public Object b() {
        Stack h = h();
        if (!h.isEmpty()) {
            return h.peek();
        }
        throw new NoAspectBoundException();
    }

    public Object a(int i) {
        CFlow d = d();
        if (d == null) {
            return null;
        }
        return d.a(i);
    }

    public Object c() {
        CFlow d = d();
        if (d != null) {
            return d.a();
        }
        throw new NoAspectBoundException();
    }

    public CFlow d() {
        Stack h = h();
        if (h.isEmpty()) {
            return null;
        }
        return (CFlow) h.peek();
    }

    public CFlow e() {
        Stack h = h();
        if (h.isEmpty()) {
            return null;
        }
        return (CFlow) h.elementAt(0);
    }

    public boolean f() {
        return !h().isEmpty();
    }

    private static ThreadStackFactory i() {
        return new ThreadStackFactoryImpl();
    }

    private static ThreadStackFactory j() {
        return new ThreadStackFactoryImpl11();
    }

    private static void k() {
        String a2 = a("aspectj.runtime.cflowstack.usethreadlocal", "unspecified");
        boolean z = false;
        if (!a2.equals("unspecified") ? a2.equals(Constants.YES) || a2.equals("true") : System.getProperty("java.class.version", b.m).compareTo("46.0") >= 0) {
            z = true;
        }
        if (z) {
            f3763a = i();
        } else {
            f3763a = j();
        }
    }

    private static String a(String str, String str2) {
        try {
            return System.getProperty(str, str2);
        } catch (SecurityException unused) {
            return str2;
        }
    }

    public static String g() {
        return f3763a.getClass().getName();
    }
}
