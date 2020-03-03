package com.alipay.zoloz.b.a;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.TreeMap;
import org.json.a.a.c;

public class g implements i, j {
    public boolean a(Class<?> cls) {
        return true;
    }

    public Object a(Object obj) {
        TreeMap treeMap = new TreeMap();
        Class cls = obj.getClass();
        Field[] declaredFields = cls.getDeclaredFields();
        while (!cls.equals(Object.class)) {
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    Object a2 = a(field, obj);
                    if (a2 != null) {
                        treeMap.put(field.getName(), a2);
                    }
                }
            }
            cls = cls.getSuperclass();
            declaredFields = cls.getDeclaredFields();
        }
        return treeMap;
    }

    public Object a(Object obj, Type type) {
        if (!obj.getClass().equals(c.class)) {
            return null;
        }
        c cVar = (c) obj;
        Class cls = (Class) type;
        Object newInstance = cls.newInstance();
        while (!cls.equals(Object.class)) {
            Field[] declaredFields = cls.getDeclaredFields();
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    String name = field.getName();
                    Type genericType = field.getGenericType();
                    if (cVar.b(name)) {
                        field.setAccessible(true);
                        field.set(newInstance, e.a(cVar.a(name), genericType));
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        return newInstance;
    }

    private static Object a(Field field, Object obj) {
        if (field == null || obj == null || "this$0".equals(field.getName())) {
            return null;
        }
        boolean isAccessible = field.isAccessible();
        field.setAccessible(true);
        Object obj2 = field.get(obj);
        if (obj2 == null) {
            return null;
        }
        field.setAccessible(isAccessible);
        return f.b(obj2);
    }
}
