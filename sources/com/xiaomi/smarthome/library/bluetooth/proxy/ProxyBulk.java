package com.xiaomi.smarthome.library.bluetooth.proxy;

import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import java.lang.reflect.Method;

public class ProxyBulk {

    /* renamed from: a  reason: collision with root package name */
    public Object f18521a;
    public Method b;
    public Object[] c;

    public ProxyBulk(Object obj, Method method, Object[] objArr) {
        this.f18521a = obj;
        this.b = method;
        this.c = objArr;
    }

    public Object a() {
        try {
            return this.b.invoke(this.f18521a, this.c);
        } catch (Throwable th) {
            BluetoothLog.a(th);
            return null;
        }
    }

    public static Object a(Object obj) {
        return ((ProxyBulk) obj).a();
    }
}
