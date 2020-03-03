package com.alipay.deviceid.module.x;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class ai {

    /* renamed from: a  reason: collision with root package name */
    private static List<am> f871a;

    static {
        ArrayList arrayList = new ArrayList();
        f871a = arrayList;
        arrayList.add(new ao());
        f871a.add(new ag());
        f871a.add(new af());
        f871a.add(new ak());
        f871a.add(new ae());
        f871a.add(new ad());
        f871a.add(new aj());
    }

    public static String a(Object obj) {
        if (obj == null) {
            return null;
        }
        Object b = b(obj);
        if (ap.a(b.getClass())) {
            return ab.b(b.toString());
        }
        if (Collection.class.isAssignableFrom(b.getClass())) {
            return new aa((Collection) (List) b).toString();
        }
        if (Map.class.isAssignableFrom(b.getClass())) {
            return new ab((Map) b).toString();
        }
        throw new IllegalArgumentException("Unsupported Class : " + b.getClass());
    }

    public static Object b(Object obj) {
        Object a2;
        if (obj == null) {
            return null;
        }
        for (am next : f871a) {
            if (next.a(obj.getClass()) && (a2 = next.a(obj)) != null) {
                return a2;
            }
        }
        throw new IllegalArgumentException("Unsupported Class : " + obj.getClass());
    }
}
