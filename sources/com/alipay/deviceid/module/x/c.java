package com.alipay.deviceid.module.x;

import android.content.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class c {

    /* renamed from: a  reason: collision with root package name */
    private static String f902a;
    private static List<String> b;

    public static Map<String, String> a(Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put("AA1", context.getPackageName());
        j.a();
        hashMap.put("AA2", j.a(context));
        hashMap.put("AA3", "security-sdk-token");
        hashMap.put("AA4", "6.0.2.20181008");
        return hashMap;
    }

    public static Map<String, String> a(Map<String, String> map) {
        String a2 = e.a(map, "appchannel", "");
        HashMap hashMap = new HashMap();
        hashMap.put("AA6", a2);
        return hashMap;
    }
}
