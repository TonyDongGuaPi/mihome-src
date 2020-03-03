package com.orhanobut.logger;

public interface Printer {
    Printer a(String str, int i);

    Settings a();

    Settings a(String str);

    void a(int i, String str, String str2, Throwable th);

    void a(Object obj);

    void a(String str, Object... objArr);

    void a(Throwable th, String str, Object... objArr);

    void b();

    void b(String str);

    void b(String str, Object... objArr);

    void c(String str);

    void c(String str, Object... objArr);

    void d(String str, Object... objArr);

    void e(String str, Object... objArr);

    void f(String str, Object... objArr);
}
