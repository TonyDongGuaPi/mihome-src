package com.xiaomi.jr.hybrid;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.xiaomi.jr.hybrid.FeatureUtil;

public abstract class HybridContext {
    private static final String f = "HybridContext";

    /* renamed from: a  reason: collision with root package name */
    protected Context f1441a;
    protected Fragment b;
    protected NativeInterface c;
    protected FeatureManager d = new FeatureManager();
    protected Handler e = new Handler(Looper.getMainLooper());

    public abstract void a(Response response, Object obj);

    public abstract void a(String str);

    public String f() {
        return null;
    }

    public HybridContext(Context context, Fragment fragment, NativeInterface nativeInterface) {
        this.f1441a = context.getApplicationContext();
        this.b = fragment;
        this.c = nativeInterface;
    }

    public FeatureManager a() {
        return this.d;
    }

    public NativeInterface b() {
        return this.c;
    }

    public Fragment c() {
        return this.b;
    }

    public Context d() {
        return this.f1441a;
    }

    public Handler e() {
        return this.e;
    }

    public int a(String str, String str2) {
        if (!FeatureConfigManager.a(f(), str, str2)) {
            return 205;
        }
        try {
            Object b2 = b(str);
            if (b2 == null || FeatureUtil.d(b2.getClass(), str2) == null) {
                return 205;
            }
            return 0;
        } catch (HybridException e2) {
            return e2.getResponse().a();
        }
    }

    public Response a(String str, String str2, String str3, Object obj) {
        if (!FeatureConfigManager.a(f(), str, str2)) {
            return new Response(205, "feature or action is not available");
        }
        try {
            Object b2 = b(str);
            Request a2 = a((Class) b2.getClass(), str2, str3, obj);
            if (FeatureUtil.b(b2.getClass(), a2.b()) != FeatureUtil.Mode.ASYNC) {
                return FeatureUtil.a(b2, a2);
            }
            HybridUtils.a((Runnable) new AsyncInvocation(b2, a2));
            return Response.j;
        } catch (HybridException e2) {
            return e2.getResponse();
        }
    }

    private Object b(String str) throws HybridException {
        HybridFeature a2 = this.d.a(str);
        a2.setHybridContext(this);
        return a2;
    }

    private Request a(Class cls, String str, String str2, Object obj) throws HybridException {
        Request request = new Request(this);
        request.a(str);
        Class<Object> c2 = FeatureUtil.c(cls, str);
        if (c2 != null) {
            if (c2 != Object.class) {
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        Object a2 = SimpleType.a(str2, c2);
                        if (a2 == null) {
                            try {
                                a2 = HybridUtils.a().fromJson(str2, c2);
                            } catch (Exception unused) {
                                throw new HybridException(206, "illegal json format for the param " + str2 + " in action " + str);
                            }
                        }
                        request.a(a2, str2);
                    } catch (Exception unused2) {
                        throw new HybridException(206, "illegal value for the param " + str2 + " in action " + str);
                    }
                } else {
                    throw new HybridException(206, "param can't be empty for action " + str);
                }
            }
            request.a(obj);
            return request;
        }
        throw new HybridException(205, "no such action");
    }

    private class AsyncInvocation implements Runnable {
        private Object b;
        private Request c;

        public AsyncInvocation(Object obj, Request request) {
            this.b = obj;
            this.c = request;
        }

        public void run() {
            HybridContext.this.a(FeatureUtil.a(this.b, this.c), this.c.e());
        }
    }

    public void g() {
        this.e.removeCallbacksAndMessages((Object) null);
        for (HybridFeature cleanup : this.d.a().values()) {
            cleanup.cleanup();
        }
    }
}
