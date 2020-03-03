package com.xiaomi.youpin.hawkeye.utils;

import android.util.Log;
import com.xiaomi.youpin.hawkeye.Env;

public class HLogDefaultLoggingDelegate implements ILoggingDelegate {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23390a = "HawkEye   ***    ";
    private int b = 2;

    public void a(int i) {
        this.b = i;
    }

    public int a() {
        return this.b;
    }

    public boolean b(int i) {
        Log.d("HLog", "isDebug" + Env.f23349a);
        return this.b <= i && Env.f23349a;
    }

    public void a(String str, String str2) {
        Log.v(f23390a + str, str2);
    }

    public void b(String str, String str2) {
        Log.d(f23390a + str, str2);
    }

    public void c(String str, String str2) {
        Log.i(f23390a + str, str2);
    }

    public void d(String str, String str2) {
        Log.w(f23390a + str, str2);
    }

    public void e(String str, String str2) {
        Log.e(f23390a + str, str2);
    }
}
