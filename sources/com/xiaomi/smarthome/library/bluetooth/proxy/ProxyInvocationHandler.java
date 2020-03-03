package com.xiaomi.smarthome.library.bluetooth.proxy;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyInvocationHandler implements Handler.Callback, ProxyInterceptor, InvocationHandler {

    /* renamed from: a  reason: collision with root package name */
    private Object f18522a;
    private ProxyInterceptor b;
    private boolean c;
    private boolean d;
    private Handler e;

    public ProxyInvocationHandler(Object obj) {
        this(obj, (ProxyInterceptor) null);
    }

    public ProxyInvocationHandler(Object obj, ProxyInterceptor proxyInterceptor) {
        this(obj, proxyInterceptor, false);
    }

    public ProxyInvocationHandler(Object obj, ProxyInterceptor proxyInterceptor, boolean z) {
        this(obj, proxyInterceptor, z, false);
    }

    public ProxyInvocationHandler(Object obj, ProxyInterceptor proxyInterceptor, boolean z, boolean z2) {
        this.c = z;
        this.b = proxyInterceptor;
        this.d = z2;
        this.f18522a = a(obj);
        this.e = new Handler(Looper.getMainLooper(), this);
    }

    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        Object a2 = a();
        if (a(a2, method, objArr)) {
            return null;
        }
        ProxyBulk proxyBulk = new ProxyBulk(a2, method, objArr);
        return this.d ? a(proxyBulk) : b(proxyBulk);
    }

    public boolean a(Object obj, Method method, Object[] objArr) {
        if (this.b == null) {
            return false;
        }
        try {
            return this.b.a(obj, method, objArr);
        } catch (Exception e2) {
            BluetoothLog.a((Throwable) e2);
            return false;
        }
    }

    private Object a(Object obj) {
        return this.c ? new WeakReference(obj) : obj;
    }

    private Object a() {
        if (this.c) {
            return ((WeakReference) this.f18522a).get();
        }
        return this.f18522a;
    }

    private Object a(ProxyBulk proxyBulk) {
        this.e.obtainMessage(0, proxyBulk).sendToTarget();
        return null;
    }

    private Object b(ProxyBulk proxyBulk) {
        try {
            return proxyBulk.a();
        } catch (Throwable unused) {
            return null;
        }
    }

    public boolean handleMessage(Message message) {
        ProxyBulk.a(message.obj);
        return true;
    }
}
