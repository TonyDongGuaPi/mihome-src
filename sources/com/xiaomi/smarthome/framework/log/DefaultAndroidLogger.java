package com.xiaomi.smarthome.framework.log;

import android.util.Log;

public class DefaultAndroidLogger implements LoggerInterface {

    /* renamed from: a  reason: collision with root package name */
    private String f16520a;

    public void a(String str) {
        this.f16520a = str;
    }

    public void b(String str) {
        Log.v(this.f16520a, str);
    }

    public void a(String str, Throwable th) {
        Log.v(this.f16520a, str, th);
    }
}
