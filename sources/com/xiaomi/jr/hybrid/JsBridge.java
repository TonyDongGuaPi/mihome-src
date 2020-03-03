package com.xiaomi.jr.hybrid;

import android.webkit.JavascriptInterface;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.jr.common.utils.MifiLog;

public class JsBridge {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10848a = "MiFiJsBridge";
    private static final String b = "MiFiJsBridge";
    private HybridContext c;

    public JsBridge(HybridContext hybridContext) {
        this.c = hybridContext;
    }

    @JavascriptInterface
    public int lookup(String str, String str2) {
        return this.c.a(str, str2);
    }

    @JavascriptInterface
    public String invoke(String str, String str2, String str3, String str4) {
        long currentTimeMillis = System.currentTimeMillis();
        MifiLog.c("MiFiJsBridge", Operators.ARRAY_START_STR + currentTimeMillis + Operators.ARRAY_END_STR);
        String response = this.c.a(str, str2, str3, (Object) str4).toString();
        MifiLog.c("MiFiJsBridge", Operators.ARRAY_START_STR + currentTimeMillis + "] invoke(" + str + ", " + str2 + ", " + str3 + ", " + str4 + "), result=" + response);
        return response;
    }
}
