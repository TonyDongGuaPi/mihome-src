package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.mi.global.shop.model.Tags;
import java.util.HashMap;
import java.util.Map;

public final class k {

    /* renamed from: a  reason: collision with root package name */
    bg f6614a = null;

    public k(Context context) {
        try {
            z.a().a(context);
        } catch (Throwable unused) {
        }
        this.f6614a = bg.a();
    }

    public static String a(Context context, String str, String str2) {
        Map<String, String> b = b(context, str2, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null);
        b.put("extensions", Tags.BaiduLbs.ADDRTYPE);
        return a(context, str, b);
    }

    public static String a(Context context, String str, String str2, String str3, String str4, String str5) {
        Map<String, String> b = b(context, str2, str3, str4, str5, (String) null, (String) null, (String) null);
        b.put("children", "1");
        b.put("page", "1");
        b.put("extensions", "base");
        return a(context, str, b);
    }

    public static String a(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        Map<String, String> b = b(context, str2, str3, (String) null, str4, str5, str6, str7);
        b.put("children", "1");
        b.put("page", "1");
        b.put("extensions", "base");
        return a(context, str, b);
    }

    private static String a(Context context, String str, Map<String, String> map) {
        byte[] bArr;
        try {
            HashMap hashMap = new HashMap(16);
            em emVar = new em();
            hashMap.clear();
            hashMap.put("Content-Type", "application/x-www-form-urlencoded");
            hashMap.put("Connection", "Keep-Alive");
            hashMap.put("User-Agent", "AMAP_Location_SDK_Android 4.7.1");
            String a2 = w.a();
            String a3 = w.a(context, a2, ad.b(map));
            map.put("ts", a2);
            map.put("scode", a3);
            emVar.b(map);
            emVar.a((Map<String, String>) hashMap);
            emVar.a(str);
            emVar.a(ab.a(context));
            emVar.a(es.f);
            emVar.b(es.f);
            if (fa.k(context)) {
                emVar.a(str.replace("http:", "https:"));
                bArr = bg.a(emVar);
            } else {
                bArr = bg.b(emVar);
            }
            return new String(bArr, "utf-8");
        } catch (Throwable unused) {
        }
        return null;
    }

    private static Map<String, String> b(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("key", u.f(context));
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("keywords", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("types", str2);
        }
        if (!TextUtils.isEmpty(str5) && !TextUtils.isEmpty(str6)) {
            hashMap.put("location", str6 + "," + str5);
        }
        if (!TextUtils.isEmpty(str3)) {
            hashMap.put("city", str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            hashMap.put("offset", str4);
        }
        if (!TextUtils.isEmpty(str7)) {
            hashMap.put("radius", str7);
        }
        return hashMap;
    }
}
