package com.xiaomi.smarthome.library.bluetooth.proxy;

import java.lang.reflect.Proxy;

public class ProxyUtils {
    public static <T> T a(Object obj, ProxyInterceptor proxyInterceptor) {
        return a(obj, (Class<?>[]) obj.getClass().getInterfaces(), proxyInterceptor);
    }

    public static <T> T a(Object obj, Class<?>[] clsArr, ProxyInterceptor proxyInterceptor, boolean z, boolean z2) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), clsArr, new ProxyInvocationHandler(obj, proxyInterceptor, z, z2));
    }

    public static <T> T a(Object obj, Class<?> cls, ProxyInterceptor proxyInterceptor, boolean z, boolean z2) {
        return a(obj, (Class<?>[]) new Class[]{cls}, proxyInterceptor, z, z2);
    }

    public static <T> T a(Object obj, Class<?> cls, ProxyInterceptor proxyInterceptor) {
        return a(obj, cls, proxyInterceptor, false, false);
    }

    public static <T> T a(Object obj, Class<?> cls) {
        return a(obj, cls, (ProxyInterceptor) null, true, true);
    }

    public static <T> T a(Object obj) {
        return a(obj, (Class<?>[]) obj.getClass().getInterfaces(), (ProxyInterceptor) null);
    }

    public static <T> T b(Object obj, Class<?> cls, ProxyInterceptor proxyInterceptor) {
        return a(obj, cls, proxyInterceptor, false, true);
    }

    public static <T> T a(Object obj, Class<?>[] clsArr, ProxyInterceptor proxyInterceptor) {
        return a(obj, clsArr, proxyInterceptor, false, true);
    }
}
