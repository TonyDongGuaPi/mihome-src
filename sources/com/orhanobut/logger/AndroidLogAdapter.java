package com.orhanobut.logger;

import android.util.Log;

class AndroidLogAdapter implements LogAdapter {
    AndroidLogAdapter() {
    }

    public void a(String str, String str2) {
        Log.d(str, str2);
    }

    public void b(String str, String str2) {
        Log.e(str, str2);
    }

    public void c(String str, String str2) {
        Log.w(str, str2);
    }

    public void d(String str, String str2) {
        Log.i(str, str2);
    }

    public void e(String str, String str2) {
        Log.v(str, str2);
    }

    public void f(String str, String str2) {
        Log.wtf(str, str2);
    }
}
