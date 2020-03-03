package com.a.a.a;

import android.util.Log;
import android.webkit.JavascriptInterface;

public class c {

    /* renamed from: a  reason: collision with root package name */
    public static final String f677a = "MiuiJsBridge";
    private b b;

    public c(b bVar) {
        this.b = bVar;
    }

    @JavascriptInterface
    public String invoke(String str, String str2, String str3, String str4) {
        String a2 = this.b.a(str, str2, str3, str4);
        if (Log.isLoggable("hybrid", 3)) {
            Log.d("hybrid", "blocking response is " + a2);
        }
        return a2;
    }
}
