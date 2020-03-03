package com.mi.global.shop.util;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CookieUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7084a = "Set-cookie";
    public static final String b = "Set-cookie2";

    public static List<HttpCookie> a(Map<String, List<String>> map) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            if (str != null && (str.equalsIgnoreCase(f7084a) || str.equalsIgnoreCase(b))) {
                for (String parse : (List) next.getValue()) {
                    try {
                        for (HttpCookie add : HttpCookie.parse(parse)) {
                            arrayList.add(add);
                        }
                    } catch (IllegalArgumentException unused) {
                    }
                }
            }
        }
        return arrayList;
    }
}
