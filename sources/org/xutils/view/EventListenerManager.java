package org.xutils.view;

import android.text.TextUtils;
import android.view.View;
import com.taobao.weex.el.parse.Operators;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.xutils.common.util.DoubleKeyValueMap;
import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.Event;

final class EventListenerManager {

    /* renamed from: a  reason: collision with root package name */
    private static final long f11941a = 300;
    /* access modifiers changed from: private */
    public static final HashSet<String> b = new HashSet<>(2);
    private static final DoubleKeyValueMap<ViewInfo, Class<?>, Object> c = new DoubleKeyValueMap<>();

    static {
        b.add("onClick");
        b.add("onItemClick");
    }

    private EventListenerManager() {
    }

    public static void a(ViewFinder viewFinder, ViewInfo viewInfo, Event event, Object obj, Method method) {
        boolean z;
        try {
            View a2 = viewFinder.a(viewInfo);
            if (a2 != null) {
                Class<?> type = event.type();
                String str = event.setter();
                if (TextUtils.isEmpty(str)) {
                    str = "set" + type.getSimpleName();
                }
                String method2 = event.method();
                Object a3 = c.a(viewInfo, type);
                if (a3 != null) {
                    DynamicHandler dynamicHandler = (DynamicHandler) Proxy.getInvocationHandler(a3);
                    z = obj.equals(dynamicHandler.a());
                    if (z) {
                        dynamicHandler.a(method2, method);
                    }
                } else {
                    z = false;
                }
                if (!z) {
                    DynamicHandler dynamicHandler2 = new DynamicHandler(obj);
                    dynamicHandler2.a(method2, method);
                    a3 = Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, dynamicHandler2);
                    c.a(viewInfo, type, a3);
                }
                a2.getClass().getMethod(str, new Class[]{type}).invoke(a2, new Object[]{a3});
            }
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
        }
    }

    public static class DynamicHandler implements InvocationHandler {
        private static long c;

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<Object> f11942a;
        private final HashMap<String, Method> b = new HashMap<>(1);

        public DynamicHandler(Object obj) {
            this.f11942a = new WeakReference<>(obj);
        }

        public void a(String str, Method method) {
            this.b.put(str, method);
        }

        public Object a() {
            return this.f11942a.get();
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            Object obj2 = this.f11942a.get();
            if (obj2 != null) {
                String name = method.getName();
                if ("toString".equals(name)) {
                    return DynamicHandler.class.getSimpleName();
                }
                Method method2 = this.b.get(name);
                if (method2 == null && this.b.size() == 1) {
                    Iterator<Map.Entry<String, Method>> it = this.b.entrySet().iterator();
                    if (it.hasNext()) {
                        Map.Entry next = it.next();
                        if (TextUtils.isEmpty((CharSequence) next.getKey())) {
                            method2 = (Method) next.getValue();
                        }
                    }
                }
                if (method2 != null) {
                    if (EventListenerManager.b.contains(name)) {
                        long currentTimeMillis = System.currentTimeMillis() - c;
                        if (currentTimeMillis < EventListenerManager.f11941a) {
                            LogUtil.a("onClick cancelled: " + currentTimeMillis);
                            return null;
                        }
                        c = System.currentTimeMillis();
                    }
                    try {
                        return method2.invoke(obj2, objArr);
                    } catch (Throwable th) {
                        throw new RuntimeException("invoke method error:" + obj2.getClass().getName() + "#" + method2.getName(), th);
                    }
                } else {
                    LogUtil.e("method not impl: " + name + Operators.BRACKET_START_STR + obj2.getClass().getSimpleName() + Operators.BRACKET_END_STR);
                }
            }
            return null;
        }
    }
}
