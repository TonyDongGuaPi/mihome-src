package com.tencent.tinker.loader.hotplug.interceptor;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.tencent.tinker.loader.hotplug.interceptor.Interceptor;
import java.lang.reflect.Field;

public class HandlerMessageInterceptor extends Interceptor<Handler.Callback> {
    private static Field c;

    /* renamed from: a  reason: collision with root package name */
    private final Handler f9242a;
    private final MessageHandler b;

    public interface MessageHandler {
        boolean a(Message message);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:2|3|(3:5|6|7)|8|9) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0011 */
    static {
        /*
            java.lang.Class<com.tencent.tinker.loader.hotplug.interceptor.HandlerMessageInterceptor> r0 = com.tencent.tinker.loader.hotplug.interceptor.HandlerMessageInterceptor.class
            monitor-enter(r0)
            java.lang.reflect.Field r1 = c     // Catch:{ all -> 0x0013 }
            if (r1 != 0) goto L_0x0011
            java.lang.Class<android.os.Handler> r1 = android.os.Handler.class
            java.lang.String r2 = "mCallback"
            java.lang.reflect.Field r1 = com.tencent.tinker.loader.shareutil.ShareReflectUtil.a((java.lang.Class<?>) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0011 }
            c = r1     // Catch:{ Throwable -> 0x0011 }
        L_0x0011:
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            return
        L_0x0013:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.tinker.loader.hotplug.interceptor.HandlerMessageInterceptor.<clinit>():void");
    }

    public HandlerMessageInterceptor(Handler handler, MessageHandler messageHandler) {
        this.f9242a = handler;
        this.b = messageHandler;
    }

    /* access modifiers changed from: protected */
    @Nullable
    /* renamed from: a */
    public Handler.Callback b() throws Throwable {
        return (Handler.Callback) c.get(this.f9242a);
    }

    /* access modifiers changed from: protected */
    @NonNull
    /* renamed from: a */
    public Handler.Callback b(@Nullable Handler.Callback callback) throws Throwable {
        if (callback == null || !Interceptor.ITinkerHotplugProxy.class.isAssignableFrom(callback.getClass())) {
            return new CallbackWrapper(this.b, callback);
        }
        return callback;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void a(@Nullable Handler.Callback callback) throws Throwable {
        c.set(this.f9242a, callback);
    }

    private static class CallbackWrapper implements Handler.Callback, Interceptor.ITinkerHotplugProxy {

        /* renamed from: a  reason: collision with root package name */
        private final MessageHandler f9243a;
        private final Handler.Callback b;
        private volatile boolean c = false;

        CallbackWrapper(MessageHandler messageHandler, Handler.Callback callback) {
            this.f9243a = messageHandler;
            this.b = callback;
        }

        public boolean handleMessage(Message message) {
            boolean z;
            if (this.c) {
                return false;
            }
            this.c = true;
            if (this.f9243a.a(message)) {
                z = true;
            } else {
                z = this.b != null ? this.b.handleMessage(message) : false;
            }
            this.c = false;
            return z;
        }
    }
}
