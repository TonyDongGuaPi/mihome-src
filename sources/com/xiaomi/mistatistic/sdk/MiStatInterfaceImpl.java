package com.xiaomi.mistatistic.sdk;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class MiStatInterfaceImpl extends MiStatInterface {
    private static MiStatInterfaceImpl i;

    public static synchronized MiStatInterfaceImpl g() {
        MiStatInterfaceImpl miStatInterfaceImpl;
        synchronized (MiStatInterfaceImpl.class) {
            if (i == null) {
                i = new MiStatInterfaceImpl();
            }
            miStatInterfaceImpl = i;
        }
        return miStatInterfaceImpl;
    }

    public void a(WebView webView, String str) {
        webView.addJavascriptInterface(this, str);
    }

    @JavascriptInterface
    public void trackCountEvent(String str, String str2) {
        a(str, str2, (Map<String, String>) null);
    }

    @JavascriptInterface
    public void trackCountEvent(String str, String str2, String str3) {
        HashMap hashMap = null;
        if (!TextUtils.isEmpty(str3)) {
            try {
                JSONObject jSONObject = new JSONObject(str3);
                HashMap hashMap2 = new HashMap();
                try {
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        hashMap2.put(next, jSONObject.optString(next));
                    }
                    hashMap = hashMap2;
                } catch (JSONException e) {
                    e = e;
                    hashMap = hashMap2;
                    e.printStackTrace();
                    a(str, str2, (Map<String, String>) hashMap);
                }
            } catch (JSONException e2) {
                e = e2;
                e.printStackTrace();
                a(str, str2, (Map<String, String>) hashMap);
            }
        }
        a(str, str2, (Map<String, String>) hashMap);
    }

    @JavascriptInterface
    public void trackCalculateEvent(String str, String str2, long j) {
        a(str, str2, j, (Map<String, String>) null);
    }

    @JavascriptInterface
    public void trackNumericPropertyEvent(String str, String str2, long j) {
        b(str, str2, j);
    }

    @JavascriptInterface
    public void trackStringPropertyEvent(String str, String str2, String str3) {
        a(str, str2, str3);
    }
}
