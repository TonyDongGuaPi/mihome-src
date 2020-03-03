package com.xiaomi.smarthome.library.bluetooth.proxy;

import java.lang.reflect.Method;

public interface ProxyInterceptor {
    boolean a(Object obj, Method method, Object[] objArr);
}
