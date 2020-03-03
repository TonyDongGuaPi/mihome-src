package com.xiaomi.aiot.mibeacon.logging;

public interface Logger {
    void a(String str, String str2, Object... objArr);

    void a(Throwable th, String str, String str2, Object... objArr);

    void b(String str, String str2, Object... objArr);

    void b(Throwable th, String str, String str2, Object... objArr);

    void c(String str, String str2, Object... objArr);

    void c(Throwable th, String str, String str2, Object... objArr);

    void d(String str, String str2, Object... objArr);

    void d(Throwable th, String str, String str2, Object... objArr);

    void e(String str, String str2, Object... objArr);

    void e(Throwable th, String str, String str2, Object... objArr);
}
