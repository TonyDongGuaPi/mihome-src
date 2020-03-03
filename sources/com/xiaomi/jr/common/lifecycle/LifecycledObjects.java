package com.xiaomi.jr.common.lifecycle;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class LifecycledObjects {

    /* renamed from: a  reason: collision with root package name */
    private static Map<Integer, Object> f10359a = new ConcurrentHashMap();
    private static Map<Object, List<Object>> b = new ConcurrentHashMap();

    public static Integer a(Object obj, Object obj2) {
        Integer valueOf = Integer.valueOf(obj.hashCode());
        if (!f10359a.values().contains(obj)) {
            f10359a.put(valueOf, obj);
        }
        if (!b.containsKey(obj2)) {
            b.put(obj2, new CopyOnWriteArrayList());
        }
        if (!b.get(obj2).contains(obj)) {
            b.get(obj2).add(obj);
        }
        return valueOf;
    }

    public static void a(Integer num) {
        Object remove = f10359a.remove(num);
        for (Object next : b.keySet()) {
            List list = b.get(next);
            if (list.contains(remove)) {
                list.remove(remove);
            }
            if (list.isEmpty()) {
                b.remove(next);
            }
        }
    }

    public static void a(Object obj) {
        if (b.containsKey(obj)) {
            for (Object hashCode : b.get(obj)) {
                f10359a.remove(Integer.valueOf(hashCode.hashCode()));
            }
            b.remove(obj);
        }
    }

    public static <T> T b(Integer num) {
        return f10359a.get(num);
    }
}
