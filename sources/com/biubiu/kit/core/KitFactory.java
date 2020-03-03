package com.biubiu.kit.core;

import java.util.ArrayList;
import java.util.List;

public class KitFactory {

    /* renamed from: a  reason: collision with root package name */
    private static IKitFactory f4799a;
    private static final List<Mapping> b = new ArrayList();

    public static void a(Class<?> cls, Class<?> cls2) {
        b.add(new Mapping(cls.getName(), cls2.getName()));
    }

    static String a(Class<?> cls) {
        for (Mapping next : b) {
            if (next.a(cls)) {
                return next.a();
            }
        }
        return null;
    }

    public static Object a(String str) {
        try {
            return Class.forName(str).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static IKitFactory b(String str) {
        IKitFactory iKitFactory = f4799a;
        if (iKitFactory != null) {
            return iKitFactory;
        }
        Class c = c(str);
        if (c == null) {
            return null;
        }
        try {
            IKitFactory iKitFactory2 = (IKitFactory) c.newInstance();
            try {
                iKitFactory2.map();
                f4799a = iKitFactory2;
                return iKitFactory2;
            } catch (Exception e) {
                Exception exc = e;
                iKitFactory = iKitFactory2;
                e = exc;
                e.printStackTrace();
                return iKitFactory;
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            return iKitFactory;
        }
    }

    private static Class c(String str) {
        try {
            return Class.forName(str + ".kitFactoryImpl");
        } catch (Exception unused) {
            try {
                return Class.forName("com.biubiu.kit.core.kitFactoryImpl");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
