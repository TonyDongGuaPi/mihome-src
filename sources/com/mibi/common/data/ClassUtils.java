package com.mibi.common.data;

public class ClassUtils {
    private ClassUtils() {
    }

    public static Object a(String str) {
        try {
            try {
                return Class.forName(str).newInstance();
            } catch (IllegalAccessException | InstantiationException unused) {
                return null;
            }
        } catch (ClassNotFoundException unused2) {
            return null;
        }
    }
}
