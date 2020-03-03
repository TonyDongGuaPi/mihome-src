package cn.fraudmetrix.octopus.aspirit.base.ui.mvp;

import cn.fraudmetrix.octopus.aspirit.base.ui.mvp.b;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

public abstract class a<V extends b> {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public V f624a;
    /* access modifiers changed from: protected */
    public V b;

    /* renamed from: cn.fraudmetrix.octopus.aspirit.base.ui.mvp.a$a  reason: collision with other inner class name */
    class C0019a implements InvocationHandler {
        C0019a() {
        }

        public Object invoke(Object obj, Method method, Object[] objArr) {
            if (!a.this.f()) {
                return null;
            }
            return method.invoke(a.this.f624a, objArr);
        }
    }

    public void a(V v) {
        this.f624a = v;
        this.b = (b) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{e()}, new C0019a());
    }

    public void d() {
        this.f624a = null;
    }

    public Class<?> e() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        }
        return null;
    }

    public boolean f() {
        return this.f624a != null;
    }

    public void g() {
    }

    public void h() {
    }

    public void i() {
    }

    public void j() {
    }
}
