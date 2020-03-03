package com.xiaomi.jr.hybrid;

public class SimpleType {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10849a = "SimpleType";

    public static Object a(String str, Class<?> cls) throws Exception {
        if (cls == Integer.class) {
            return Integer.valueOf(Integer.parseInt(str));
        }
        if (cls == Long.class) {
            return Long.valueOf(Long.parseLong(str));
        }
        if (cls == Float.class) {
            return Float.valueOf(Float.parseFloat(str));
        }
        if (cls == Double.class) {
            return Double.valueOf(Double.parseDouble(str));
        }
        if (cls == String.class) {
            return str;
        }
        return null;
    }
}
