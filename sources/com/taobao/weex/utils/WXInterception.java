package com.taobao.weex.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXInterception {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private interface Intercepted {
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(8287978877330223741L, "com/taobao/weex/utils/WXInterception", 6);
        $jacocoData = a2;
        return a2;
    }

    private WXInterception() {
        $jacocoInit()[0] = true;
    }

    public static <T> T proxy(Object obj, Class<T> cls, InterceptionHandler<T> interceptionHandler) throws IllegalArgumentException {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj instanceof Intercepted) {
            $jacocoInit[1] = true;
            return obj;
        }
        interceptionHandler.setDelegate(obj);
        $jacocoInit[2] = true;
        T newProxyInstance = Proxy.newProxyInstance(WXInterception.class.getClassLoader(), new Class[]{cls, Intercepted.class}, interceptionHandler);
        $jacocoInit[3] = true;
        return newProxyInstance;
    }

    public static <T> T proxy(Object obj, InterceptionHandler<T> interceptionHandler, Class<?>... clsArr) throws IllegalArgumentException {
        boolean[] $jacocoInit = $jacocoInit();
        interceptionHandler.setDelegate(obj);
        $jacocoInit[4] = true;
        T newProxyInstance = Proxy.newProxyInstance(WXInterception.class.getClassLoader(), clsArr, interceptionHandler);
        $jacocoInit[5] = true;
        return newProxyInstance;
    }

    public static abstract class InterceptionHandler<T> implements InvocationHandler {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private T mDelegate;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-7605835440163016199L, "com/taobao/weex/utils/WXInterception$InterceptionHandler", 10);
            $jacocoData = a2;
            return a2;
        }

        public InterceptionHandler() {
            $jacocoInit()[0] = true;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            boolean[] $jacocoInit = $jacocoInit();
            try {
                Object invoke = method.invoke(delegate(), objArr);
                $jacocoInit[1] = true;
                return invoke;
            } catch (IllegalArgumentException e) {
                $jacocoInit[2] = true;
                WXLogUtils.e("", (Throwable) e);
                $jacocoInit[3] = true;
                return null;
            } catch (IllegalAccessException e2) {
                $jacocoInit[4] = true;
                WXLogUtils.e("", (Throwable) e2);
                $jacocoInit[5] = true;
                return null;
            } catch (InvocationTargetException e3) {
                $jacocoInit[6] = true;
                Throwable targetException = e3.getTargetException();
                $jacocoInit[7] = true;
                throw targetException;
            }
        }

        /* access modifiers changed from: protected */
        public T delegate() {
            boolean[] $jacocoInit = $jacocoInit();
            T t = this.mDelegate;
            $jacocoInit[8] = true;
            return t;
        }

        /* access modifiers changed from: package-private */
        public void setDelegate(T t) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mDelegate = t;
            $jacocoInit[9] = true;
        }
    }
}
