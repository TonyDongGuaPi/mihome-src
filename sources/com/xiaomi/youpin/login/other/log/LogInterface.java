package com.xiaomi.youpin.login.other.log;

public interface LogInterface {
    void a(String str, String str2);

    void a(String str, String str2, Throwable th);

    void a(String str, Throwable th);

    void a(Throwable th);

    void b(String str, String str2);

    void b(String str, String str2, Throwable th);

    void c(String str, String str2);

    void c(String str, String str2, Throwable th);

    void d(String str, String str2);

    void d(String str, String str2, Throwable th);

    void e(String str, String str2);

    void e(String str, String str2, Throwable th);
}
