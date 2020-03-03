package com.xiaomi.youpin.login.api.stat;

import java.util.HashMap;
import java.util.Map;

public class LoginStatUtil {
    public static Map<String, String> a(int i, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("errCode", String.valueOf(i));
        hashMap.put("errMsg", str);
        return hashMap;
    }

    public static Map<String, String> a(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("isLocal", String.valueOf(z));
        return hashMap;
    }

    public static Map<String, String> b(int i, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("errCode", String.valueOf(i));
        hashMap.put("errMsg", str);
        hashMap.put("isLocal", "true");
        return hashMap;
    }

    public static Map<String, String> a(int i, String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("errCode", String.valueOf(i));
        hashMap.put("errMsg", str);
        hashMap.put("isLocal", "false");
        hashMap.put("phone", str2);
        return hashMap;
    }
}
