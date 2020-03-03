package com.alipay.deviceid.module.x;

import android.os.Looper;
import com.alipay.deviceid.module.rpc.mrpc.core.RpcException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public final class bk {

    /* renamed from: a  reason: collision with root package name */
    private static final ThreadLocal<Object> f893a = new ThreadLocal<>();
    private static final ThreadLocal<Map<String, Object>> b = new ThreadLocal<>();
    private byte c = 0;
    private AtomicInteger d;
    private bi e;

    public bk(bi biVar) {
        this.e = biVar;
        this.d = new AtomicInteger();
    }

    public final Object a(Method method, Object[] objArr) {
        if (!(Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper())) {
            aq aqVar = (aq) method.getAnnotation(aq.class);
            boolean z = method.getAnnotation(ar.class) != null;
            Type genericReturnType = method.getGenericReturnType();
            method.getAnnotations();
            f893a.set((Object) null);
            b.set((Object) null);
            if (aqVar != null) {
                String a2 = aqVar.a();
                int incrementAndGet = this.d.incrementAndGet();
                try {
                    if (this.c == 0) {
                        bt btVar = new bt(incrementAndGet, a2, objArr);
                        if (b.get() != null) {
                            btVar.a(b.get());
                        }
                        byte[] a3 = btVar.a();
                        b.set((Object) null);
                        Object a4 = new bs(genericReturnType, (byte[]) new aw(this.e.f891a, method, incrementAndGet, a2, a3, z).a()).a();
                        if (genericReturnType != Void.TYPE) {
                            f893a.set(a4);
                        }
                    }
                    return f893a.get();
                } catch (RpcException e2) {
                    e2.setOperationType(a2);
                    throw e2;
                }
            } else {
                throw new IllegalStateException("OperationType must be set.");
            }
        } else {
            throw new IllegalThreadStateException("can't in main thread call rpc .");
        }
    }
}
