package com.alipay.zoloz.b.a;

import com.alipay.zoloz.b.b.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.json.a.a.c;

public class f {

    /* renamed from: a  reason: collision with root package name */
    private static List<j> f1196a = new ArrayList();

    static {
        f1196a.add(new l());
        f1196a.add(new d());
        f1196a.add(new c());
        f1196a.add(new h());
        f1196a.add(new b());
        f1196a.add(new a());
        f1196a.add(new g());
    }

    public static String a(Object obj) {
        if (obj == null) {
            return null;
        }
        Object b = b(obj);
        if (a.a(b.getClass())) {
            return c.d(b.toString());
        }
        if (Collection.class.isAssignableFrom(b.getClass())) {
            return new org.json.a.a.a((Collection) (List) b).toString();
        }
        if (Map.class.isAssignableFrom(b.getClass())) {
            return new c((Map) b).toString();
        }
        throw new IllegalArgumentException("Unsupported Class : " + b.getClass());
    }

    public static Object b(Object obj) {
        Object a2;
        if (obj == null) {
            return null;
        }
        for (j next : f1196a) {
            if (next.a(obj.getClass()) && (a2 = next.a(obj)) != null) {
                return a2;
            }
        }
        throw new IllegalArgumentException("Unsupported Class : " + obj.getClass());
    }
}
