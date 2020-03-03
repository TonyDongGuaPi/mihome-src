package com.alipay.sdk.packet.impl;

import android.content.Context;
import com.alipay.sdk.packet.b;
import com.alipay.sdk.packet.e;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class d extends e {
    public static final String t = "log_v";

    /* access modifiers changed from: protected */
    public String a(String str, JSONObject jSONObject) {
        return str;
    }

    /* access modifiers changed from: protected */
    public JSONObject a() throws JSONException {
        return null;
    }

    /* access modifiers changed from: protected */
    public Map<String, String> a(boolean z, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(e.f1118a, String.valueOf(z));
        hashMap.put("content-type", "application/octet-stream");
        hashMap.put(e.g, "CBC");
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public String c() throws JSONException {
        HashMap hashMap = new HashMap();
        hashMap.put(e.i, "/sdk/log");
        hashMap.put("api_version", "1.0.0");
        HashMap hashMap2 = new HashMap();
        hashMap2.put(t, "1.0");
        return a((HashMap<String, String>) hashMap, (HashMap<String, String>) hashMap2);
    }

    public b a(Context context, String str) throws Throwable {
        return a(context, str, "https://mcgw.alipay.com/sdklog.do", true);
    }
}
