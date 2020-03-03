package com.alipay.zoloz.android.phone.mrpc.core;

import com.alipay.zoloz.android.phone.mrpc.core.gwprotocol.Deserializer;
import com.alipay.zoloz.android.phone.mrpc.core.gwprotocol.JsonDeserializer;
import com.alipay.zoloz.android.phone.mrpc.core.gwprotocol.JsonSerializer;
import com.alipay.zoloz.android.phone.mrpc.core.gwprotocol.Serializer;
import com.alipay.zoloz.mobile.a.a.b;
import com.alipay.zoloz.mobile.framework.service.annotation.OperationType;
import com.alipay.zoloz.mobile.framework.service.annotation.ResetCookie;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class RpcInvoker {

    /* renamed from: a  reason: collision with root package name */
    private static final ThreadLocal<Object> f1192a = new ThreadLocal<>();
    private static final ThreadLocal<Map<String, Object>> b = new ThreadLocal<>();
    private byte c = 0;
    private AtomicInteger d;
    private RpcFactory e;

    private void a(Object obj, Class<?> cls, Method method, Object[] objArr, Annotation[] annotationArr) {
    }

    private void a(Object obj, byte[] bArr, Class<?> cls, Method method, Object[] objArr, Annotation[] annotationArr) {
    }

    public RpcInvoker(RpcFactory rpcFactory) {
        this.e = rpcFactory;
        this.d = new AtomicInteger();
    }

    public Object invoke(Object obj, Class<?> cls, Method method, Object[] objArr) {
        byte[] bArr;
        b bVar;
        Method method2 = method;
        if (!ThreadUtil.checkMainThread()) {
            OperationType operationType = (OperationType) method2.getAnnotation(OperationType.class);
            boolean z = method2.getAnnotation(ResetCookie.class) != null;
            Type genericReturnType = method.getGenericReturnType();
            Annotation[] annotations = method.getAnnotations();
            byte[] bArr2 = null;
            f1192a.set((Object) null);
            b.set((Object) null);
            if (operationType != null) {
                String value = operationType.value();
                int incrementAndGet = this.d.incrementAndGet();
                a(obj, cls, method, objArr, annotations);
                try {
                    if (this.c == 0) {
                        byte[] a2 = a(method, objArr, value, incrementAndGet, z);
                        try {
                            Object parser = getDeserializer(genericReturnType, a2).parser();
                            if (genericReturnType != Void.TYPE) {
                                f1192a.set(parser);
                            }
                            bArr2 = a2;
                        } catch (b e2) {
                            bVar = e2;
                            bArr2 = a2;
                            bVar.setOperationType(value);
                            bArr = bArr2;
                            a(obj, bArr, cls, method, objArr, annotations, bVar);
                            a(obj, bArr, cls, method, objArr, annotations);
                            return f1192a.get();
                        }
                    }
                    bArr = bArr2;
                } catch (b e3) {
                    bVar = e3;
                    bVar.setOperationType(value);
                    bArr = bArr2;
                    a(obj, bArr, cls, method, objArr, annotations, bVar);
                    a(obj, bArr, cls, method, objArr, annotations);
                    return f1192a.get();
                }
                a(obj, bArr, cls, method, objArr, annotations);
                return f1192a.get();
            }
            throw new IllegalStateException("OperationType must be set.");
        }
        throw new IllegalThreadStateException("can't in main thread call rpc .");
    }

    private void a(Object obj, byte[] bArr, Class<?> cls, Method method, Object[] objArr, Annotation[] annotationArr, b bVar) {
        throw bVar;
    }

    private byte[] a(Method method, Object[] objArr, String str, int i, boolean z) {
        Serializer serializer = getSerializer(i, str, objArr);
        if (b.get() != null) {
            serializer.setExtParam(b.get());
        }
        byte[] bArr = (byte[]) getTransport(method, i, str, serializer.packet(), z).call();
        b.set((Object) null);
        return bArr;
    }

    public void batchBegin() {
        this.c = 1;
    }

    public FutureTask<?> batchCommit() {
        this.c = 0;
        return null;
    }

    public static void addProtocolArgs(String str, Object obj) {
        if (b.get() == null) {
            b.set(new HashMap());
        }
        b.get().put(str, obj);
    }

    public RpcCaller getTransport(Method method, int i, String str, byte[] bArr, boolean z) {
        return new HttpCaller(this.e.getConfig(), method, i, str, bArr, z);
    }

    public Serializer getSerializer(int i, String str, Object[] objArr) {
        return new JsonSerializer(i, str, objArr);
    }

    public Deserializer getDeserializer(Type type, byte[] bArr) {
        return new JsonDeserializer(type, bArr);
    }
}
