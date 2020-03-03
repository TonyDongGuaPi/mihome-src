package com.miuipub.internal.hybrid;

import android.util.Log;
import android.webkit.JavascriptInterface;

public class JsInterface {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8261a = "MiuiJsBridge";
    private HybridManager b;

    public JsInterface(HybridManager hybridManager) {
        this.b = hybridManager;
    }

    @JavascriptInterface
    public String config(String str) {
        String a2 = this.b.a(str);
        if (Log.isLoggable("hybrid", 3)) {
            Log.d("hybrid", "config response is " + a2);
        }
        return a2;
    }

    @JavascriptInterface
    public String lookup(String str, String str2) {
        String a2 = this.b.a(str, str2);
        if (Log.isLoggable("hybrid", 3)) {
            Log.d("hybrid", "lookup response is " + a2);
        }
        return a2;
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
