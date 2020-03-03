package com.xiaomi.jr.account;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class XiaomiServices {

    /* renamed from: a  reason: collision with root package name */
    private static final ConcurrentHashMap<String, XiaomiService> f1381a = new ConcurrentHashMap<>();

    public static void a(String str, String str2, String str3) {
        f1381a.putIfAbsent(str, new XiaomiService(str, str2, str + str3));
    }

    public static void a(XiaomiService xiaomiService) {
        f1381a.putIfAbsent(xiaomiService.f10282a, xiaomiService);
    }

    public static XiaomiService a(String str) {
        return f1381a.get(str);
    }

    static Collection<XiaomiService> a() {
        return f1381a.values();
    }
}
