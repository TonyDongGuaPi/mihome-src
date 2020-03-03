package com.alipay.zoloz.b.b;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class a {
    public static boolean a(Class<?> cls) {
        if (!cls.isPrimitive() && !cls.equals(String.class) && !cls.equals(Integer.class) && !cls.equals(Long.class) && !cls.equals(Double.class) && !cls.equals(Float.class) && !cls.equals(Boolean.class) && !cls.equals(Short.class) && !cls.equals(Character.class) && !cls.equals(Byte.class) && !cls.equals(Void.class)) {
            return false;
        }
        return true;
    }

    public static Class<?> a(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return a(((ParameterizedType) type).getRawType());
        }
        throw new IllegalArgumentException("TODO");
    }
}
