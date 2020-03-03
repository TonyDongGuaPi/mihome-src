package org.mp4parser.support;

import android.util.Log;

public class AndroidLogger extends Logger {
    private static final String b = "isoparser";

    /* renamed from: a  reason: collision with root package name */
    String f4102a;

    public AndroidLogger(String str) {
        this.f4102a = str;
    }

    public void a(String str) {
        Log.d(b, String.valueOf(this.f4102a) + ":" + str);
    }

    public void b(String str) {
        Log.w(b, String.valueOf(this.f4102a) + ":" + str);
    }

    public void c(String str) {
        Log.e(b, String.valueOf(this.f4102a) + ":" + str);
    }
}
