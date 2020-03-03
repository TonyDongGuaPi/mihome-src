package com.xiaomi.aiot.mibeacon.logging;

import android.util.Log;

final class VerboseAndroidLogger extends AbstractAndroidLogger {
    VerboseAndroidLogger() {
    }

    public void a(String str, String str2, Object... objArr) {
        Log.v(str, a(str2, objArr));
    }

    public void a(Throwable th, String str, String str2, Object... objArr) {
        Log.v(str, a(str2, objArr), th);
    }

    public void b(String str, String str2, Object... objArr) {
        Log.d(str, a(str2, objArr));
    }

    public void b(Throwable th, String str, String str2, Object... objArr) {
        Log.d(str, a(str2, objArr), th);
    }

    public void c(String str, String str2, Object... objArr) {
        Log.i(str, a(str2, objArr));
    }

    public void c(Throwable th, String str, String str2, Object... objArr) {
        Log.i(str, a(str2, objArr), th);
    }

    public void d(String str, String str2, Object... objArr) {
        Log.w(str, a(str2, objArr));
    }

    public void d(Throwable th, String str, String str2, Object... objArr) {
        Log.w(str, a(str2, objArr), th);
    }

    public void e(String str, String str2, Object... objArr) {
        Log.e(str, a(str2, objArr));
    }

    public void e(Throwable th, String str, String str2, Object... objArr) {
        Log.e(str, a(str2, objArr), th);
    }
}
