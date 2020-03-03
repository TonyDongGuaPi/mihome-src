package com.xiaomi.jr.stats;

import com.xiaomi.jr.common.utils.MifiHostsUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class MifiStatUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11029a = (MifiHostsUtils.c("https://api.jr.mi.com/") + "images/stat.gif");
    private static HttpRequester b;

    MifiStatUtils() {
    }

    static void a(HttpRequester httpRequester) {
        b = httpRequester;
    }

    static void a(String str, String str2, Map<String, String> map) {
        HashMap hashMap = new HashMap();
        hashMap.put("productType", str);
        hashMap.put("pageTitle", str2);
        hashMap.put("t", String.valueOf(System.currentTimeMillis()));
        if (map != null) {
            hashMap.putAll(map);
        }
        JSONObject jSONObject = new JSONObject(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("data", jSONObject.toString());
        if (b != null) {
            b.get(f11029a, hashMap2);
        }
    }
}
