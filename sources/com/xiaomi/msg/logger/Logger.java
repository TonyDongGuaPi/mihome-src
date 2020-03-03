package com.xiaomi.msg.logger;

public interface Logger {
    void a(String str, String str2);

    void a(String str, String str2, Throwable th);

    void b(String str, String str2);

    void b(String str, String str2, Throwable th);

    void c(String str, String str2);

    void c(String str, String str2, Throwable th);

    void d(String str, String str2);

    void d(String str, String str2, Throwable th);
}
