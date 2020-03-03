package cn.fraudmetrix.octopus.aspirit.e;

import android.support.annotation.NonNull;
import cn.fraudmetrix.octopus.aspirit.utils.d;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class a implements InvocationHandler {

    /* renamed from: a  reason: collision with root package name */
    protected Object f640a;

    /* renamed from: cn.fraudmetrix.octopus.aspirit.e.a$a  reason: collision with other inner class name */
    public static class C0021a {
        public static <T> T a(@NonNull Class<?> cls, Object obj) {
            d.a((Object) cls, "interfaces is null");
            return a((Class<?>[]) new Class[]{cls}, obj);
        }

        public static <T> T a(@NonNull Class<?>[] clsArr, @NonNull Object obj) {
            d.a((Object) clsArr, "interfaces is null");
            return Proxy.newProxyInstance(C0021a.class.getClassLoader(), clsArr, new a(obj));
        }
    }

    public a(Object obj) {
        this.f640a = obj;
    }

    public Object invoke(Object obj, Method method, Object[] objArr) {
        if (this.f640a == null) {
            return null;
        }
        return method.invoke(this.f640a, objArr);
    }
}
