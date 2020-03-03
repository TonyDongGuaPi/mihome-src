package com.tencent.wxop.stat.common;

import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import com.tencent.wxop.stat.StatConfig;
import com.tencent.wxop.stat.g;

public final class StatLogger {

    /* renamed from: a  reason: collision with root package name */
    private String f9314a = "default";
    private boolean b = true;
    private int c = 2;

    public StatLogger() {
    }

    public StatLogger(String str) {
        this.f9314a = str;
    }

    private String c() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        int length = stackTrace.length;
        int i = 0;
        while (i < length) {
            StackTraceElement stackTraceElement = stackTrace[i];
            if (stackTraceElement.isNativeMethod() || stackTraceElement.getClassName().equals(Thread.class.getName()) || stackTraceElement.getClassName().equals(getClass().getName())) {
                i++;
            } else {
                return Operators.ARRAY_START_STR + Thread.currentThread().getName() + Operators.BRACKET_START_STR + Thread.currentThread().getId() + "): " + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + Operators.ARRAY_END_STR;
            }
        }
        return null;
    }

    public final void a(int i) {
        this.c = i;
    }

    public final void a(Object obj) {
        String str;
        if (this.c <= 4) {
            String c2 = c();
            if (c2 == null) {
                str = obj.toString();
            } else {
                str = c2 + " - " + obj;
            }
            Log.i(this.f9314a, str);
            g A = StatConfig.A();
            if (A != null) {
                A.a(str);
            }
        }
    }

    public final void a(String str) {
        this.f9314a = str;
    }

    public final void a(Throwable th) {
        if (this.c <= 6) {
            Log.e(this.f9314a, "", th);
            g A = StatConfig.A();
            if (A != null) {
                A.d(th);
            }
        }
    }

    public final void a(boolean z) {
        this.b = z;
    }

    public final boolean a() {
        return this.b;
    }

    public final int b() {
        return this.c;
    }

    public final void b(Object obj) {
        if (a()) {
            a(obj);
        }
    }

    public final void b(Throwable th) {
        if (a()) {
            a(th);
        }
    }

    public final void c(Object obj) {
        String str;
        if (this.c <= 2) {
            String c2 = c();
            if (c2 == null) {
                str = obj.toString();
            } else {
                str = c2 + " - " + obj;
            }
            Log.v(this.f9314a, str);
            g A = StatConfig.A();
            if (A != null) {
                A.b(str);
            }
        }
    }

    public final void d(Object obj) {
        if (a()) {
            c(obj);
        }
    }

    public final void e(Object obj) {
        String str;
        if (this.c <= 5) {
            String c2 = c();
            if (c2 == null) {
                str = obj.toString();
            } else {
                str = c2 + " - " + obj;
            }
            Log.w(this.f9314a, str);
            g A = StatConfig.A();
            if (A != null) {
                A.c(str);
            }
        }
    }

    public final void f(Object obj) {
        if (a()) {
            e(obj);
        }
    }

    public final void g(Object obj) {
        String str;
        if (this.c <= 6) {
            String c2 = c();
            if (c2 == null) {
                str = obj.toString();
            } else {
                str = c2 + " - " + obj;
            }
            Log.e(this.f9314a, str);
            g A = StatConfig.A();
            if (A != null) {
                A.d(str);
            }
        }
    }

    public final void h(Object obj) {
        if (a()) {
            g(obj);
        }
    }

    public final void i(Object obj) {
        String str;
        if (this.c <= 3) {
            String c2 = c();
            if (c2 == null) {
                str = obj.toString();
            } else {
                str = c2 + " - " + obj;
            }
            Log.d(this.f9314a, str);
            g A = StatConfig.A();
            if (A != null) {
                A.e(str);
            }
        }
    }

    public final void j(Object obj) {
        if (a()) {
            i(obj);
        }
    }
}
