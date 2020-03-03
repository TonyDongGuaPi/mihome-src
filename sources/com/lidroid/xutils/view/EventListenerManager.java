package com.lidroid.xutils.view;

import android.view.View;
import com.lidroid.xutils.util.DoubleKeyValueMap;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.event.EventBase;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

public class EventListenerManager {

    /* renamed from: a  reason: collision with root package name */
    private static final DoubleKeyValueMap<ViewInjectInfo, Class<?>, Object> f6372a = new DoubleKeyValueMap<>();

    private EventListenerManager() {
    }

    public static void a(ViewFinder viewFinder, ViewInjectInfo viewInjectInfo, Annotation annotation, Object obj, Method method) {
        boolean z;
        try {
            View a2 = viewFinder.a(viewInjectInfo);
            if (a2 != null) {
                EventBase eventBase = (EventBase) annotation.annotationType().getAnnotation(EventBase.class);
                Class<?> listenerType = eventBase.listenerType();
                String listenerSetter = eventBase.listenerSetter();
                String methodName = eventBase.methodName();
                Object a3 = f6372a.a(viewInjectInfo, listenerType);
                if (a3 != null) {
                    DynamicHandler dynamicHandler = (DynamicHandler) Proxy.getInvocationHandler(a3);
                    z = obj.equals(dynamicHandler.a());
                    if (z) {
                        dynamicHandler.a(methodName, method);
                    }
                } else {
                    z = false;
                }
                if (!z) {
                    DynamicHandler dynamicHandler2 = new DynamicHandler(obj);
                    dynamicHandler2.a(methodName, method);
                    a3 = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, dynamicHandler2);
                    f6372a.a(viewInjectInfo, listenerType, a3);
                }
                a2.getClass().getMethod(listenerSetter, new Class[]{listenerType}).invoke(a2, new Object[]{a3});
            }
        } catch (Throwable th) {
            LogUtils.b(th.getMessage(), th);
        }
    }

    public static class DynamicHandler implements InvocationHandler {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<Object> f6373a;
        private final HashMap<String, Method> b = new HashMap<>(1);

        public DynamicHandler(Object obj) {
            this.f6373a = new WeakReference<>(obj);
        }

        public void a(String str, Method method) {
            this.b.put(str, method);
        }

        public Object a() {
            return this.f6373a.get();
        }

        public void a(Object obj) {
            this.f6373a = new WeakReference<>(obj);
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            Method method2;
            Object obj2 = this.f6373a.get();
            if (obj2 == null || (method2 = this.b.get(method.getName())) == null) {
                return null;
            }
            return method2.invoke(obj2, objArr);
        }
    }
}
