package com.huawei.hms.support.api;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import com.alipay.sdk.packet.e;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.client.PendingResult;
import com.huawei.hms.support.api.client.Result;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.client.SubAppInfo;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.huawei.hms.support.api.transport.DatagramTransport;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class a<R extends Result, T extends IMessageEntity> extends PendingResult<R> {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public CountDownLatch f5874a;
    /* access modifiers changed from: private */
    public R b = null;
    private WeakReference<ApiClient> c;
    private String d = null;
    private long e = 0;
    protected DatagramTransport transport = null;

    public abstract R onComplete(T t);

    public a(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
        this.d = str;
        a(apiClient, str, iMessageEntity, getResponseType());
    }

    public a(ApiClient apiClient, String str, IMessageEntity iMessageEntity, Class<T> cls) {
        a(apiClient, str, iMessageEntity, cls);
    }

    private void a(ApiClient apiClient, String str, IMessageEntity iMessageEntity, Class<T> cls) {
        if (apiClient != null) {
            this.c = new WeakReference<>(apiClient);
            this.f5874a = new CountDownLatch(1);
            try {
                this.transport = (DatagramTransport) Class.forName(apiClient.getTransportName()).getConstructor(new Class[]{String.class, IMessageEntity.class, Class.class}).newInstance(new Object[]{str, iMessageEntity, cls});
            } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | InvocationTargetException e2) {
                throw new IllegalStateException("Instancing transport exception, " + e2.getMessage(), e2);
            }
        } else {
            throw new IllegalArgumentException("apiClient cannot be null.");
        }
    }

    /* access modifiers changed from: protected */
    public Class<T> getResponseType() {
        Type type;
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass == null || (type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[1]) == null) {
            return null;
        }
        return (Class) type;
    }

    public final R await() {
        this.e = System.currentTimeMillis();
        if (Looper.myLooper() != Looper.getMainLooper()) {
            ApiClient apiClient = (ApiClient) this.c.get();
            if (!checkApiClient(apiClient)) {
                a(CommonCode.ErrorCode.CLIENT_API_INVALID, (IMessageEntity) null);
                return this.b;
            }
            this.transport.a(apiClient, new b(this));
            try {
                this.f5874a.await();
            } catch (InterruptedException unused) {
                a(CommonCode.ErrorCode.INTERNAL_ERROR, (IMessageEntity) null);
            }
            return this.b;
        }
        throw new IllegalStateException("await must not be called on the UI thread");
    }

    public R await(long j, TimeUnit timeUnit) {
        this.e = System.currentTimeMillis();
        if (Looper.myLooper() != Looper.getMainLooper()) {
            ApiClient apiClient = (ApiClient) this.c.get();
            if (!checkApiClient(apiClient)) {
                a(CommonCode.ErrorCode.CLIENT_API_INVALID, (IMessageEntity) null);
                return this.b;
            }
            AtomicBoolean atomicBoolean = new AtomicBoolean();
            this.transport.b(apiClient, new c(this, atomicBoolean));
            try {
                if (!this.f5874a.await(j, timeUnit)) {
                    atomicBoolean.set(true);
                    a(CommonCode.ErrorCode.EXECUTE_TIMEOUT, (IMessageEntity) null);
                }
            } catch (InterruptedException unused) {
                a(CommonCode.ErrorCode.INTERNAL_ERROR, (IMessageEntity) null);
            }
            return this.b;
        }
        throw new IllegalStateException("await must not be called on the UI thread");
    }

    public final void setResultCallback(ResultCallback<R> resultCallback) {
        setResultCallback(Looper.getMainLooper(), resultCallback);
    }

    public final void setResultCallback(Looper looper, ResultCallback<R> resultCallback) {
        this.e = System.currentTimeMillis();
        if (looper == null) {
            looper = Looper.myLooper();
        }
        C0055a aVar = new C0055a(looper);
        ApiClient apiClient = (ApiClient) this.c.get();
        if (!checkApiClient(apiClient)) {
            a(CommonCode.ErrorCode.CLIENT_API_INVALID, (IMessageEntity) null);
            aVar.a(resultCallback, this.b);
            return;
        }
        this.transport.b(apiClient, new d(this, aVar, resultCallback));
    }

    /* access modifiers changed from: private */
    public void a(int i, IMessageEntity iMessageEntity) {
        a(i);
        if (i <= 0) {
            this.b = onComplete(iMessageEntity);
        } else {
            this.b = onError(i);
        }
    }

    /* access modifiers changed from: protected */
    public R onError(int i) {
        Type genericSuperclass = getClass().getGenericSuperclass();
        Type type = genericSuperclass != null ? ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0] : null;
        Class a2 = type != null ? com.huawei.hms.support.a.a.a(type) : null;
        if (a2 != null) {
            try {
                this.b = (Result) a2.newInstance();
                this.b.setStatus(new Status(i));
            } catch (Exception unused) {
                return null;
            }
        }
        return this.b;
    }

    /* access modifiers changed from: protected */
    public boolean checkApiClient(ApiClient apiClient) {
        return apiClient != null && apiClient.isConnected();
    }

    /* renamed from: com.huawei.hms.support.api.a$a  reason: collision with other inner class name */
    protected static class C0055a<R extends Result> extends Handler {
        public C0055a() {
            this(Looper.getMainLooper());
        }

        public C0055a(Looper looper) {
            super(looper);
        }

        public void a(ResultCallback<? super R> resultCallback, R r) {
            sendMessage(obtainMessage(1, new Pair(resultCallback, r)));
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                Pair pair = (Pair) message.obj;
                b((ResultCallback) pair.first, (Result) pair.second);
            }
        }

        /* access modifiers changed from: protected */
        public void b(ResultCallback<? super R> resultCallback, R r) {
            resultCallback.onResult(r);
        }
    }

    private void a(int i) {
        ApiClient apiClient = (ApiClient) this.c.get();
        if (apiClient != null && this.d != null && this.e != 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("package", apiClient.getPackageName());
            hashMap.put("sdk_ver", String.valueOf(20502300));
            String str = null;
            SubAppInfo subAppInfo = apiClient.getSubAppInfo();
            if (subAppInfo != null) {
                str = subAppInfo.getSubAppID();
            }
            if (str == null) {
                str = apiClient.getAppID();
            }
            hashMap.put("app_id", str);
            String[] split = this.d.split("\\.");
            if (split.length == 2) {
                hashMap.put("service", split[0]);
                hashMap.put(e.i, split[1]);
            }
            hashMap.put("result", String.valueOf(i));
            hashMap.put("cost_time", String.valueOf(System.currentTimeMillis() - this.e));
            com.huawei.hms.support.b.a.a().a(apiClient.getContext(), "HMS_SDK_API_CALL", hashMap);
        }
    }
}
