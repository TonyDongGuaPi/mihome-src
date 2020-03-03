package org.xutils.http;

import java.lang.reflect.Type;
import org.xutils.HttpManager;
import org.xutils.common.Callback;
import org.xutils.x;

public final class HttpManagerImpl implements HttpManager {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f10763a = new Object();
    private static volatile HttpManagerImpl b;

    private HttpManagerImpl() {
    }

    public static void a() {
        if (b == null) {
            synchronized (f10763a) {
                if (b == null) {
                    b = new HttpManagerImpl();
                }
            }
        }
        x.Ext.a((HttpManager) b);
    }

    public <T> Callback.Cancelable a(RequestParams requestParams, Callback.CommonCallback<T> commonCallback) {
        return a(HttpMethod.GET, requestParams, commonCallback);
    }

    public <T> Callback.Cancelable b(RequestParams requestParams, Callback.CommonCallback<T> commonCallback) {
        return a(HttpMethod.POST, requestParams, commonCallback);
    }

    public <T> Callback.Cancelable a(HttpMethod httpMethod, RequestParams requestParams, Callback.CommonCallback<T> commonCallback) {
        requestParams.a(httpMethod);
        return x.c().a(new HttpTask(requestParams, commonCallback instanceof Callback.Cancelable ? (Callback.Cancelable) commonCallback : null, commonCallback));
    }

    public <T> T a(RequestParams requestParams, Class<T> cls) throws Throwable {
        return a(HttpMethod.GET, requestParams, cls);
    }

    public <T> T b(RequestParams requestParams, Class<T> cls) throws Throwable {
        return a(HttpMethod.POST, requestParams, cls);
    }

    public <T> T a(HttpMethod httpMethod, RequestParams requestParams, Class<T> cls) throws Throwable {
        return a(httpMethod, requestParams, new DefaultSyncCallback(cls));
    }

    public <T> T a(HttpMethod httpMethod, RequestParams requestParams, Callback.TypedCallback<T> typedCallback) throws Throwable {
        requestParams.a(httpMethod);
        return x.c().b(new HttpTask(requestParams, (Callback.Cancelable) null, typedCallback));
    }

    private class DefaultSyncCallback<T> implements Callback.TypedCallback<T> {
        private final Class<T> b;

        public void a(Throwable th, boolean z) {
        }

        public void a(Callback.CancelledException cancelledException) {
        }

        public void b(T t) {
        }

        public void c() {
        }

        public DefaultSyncCallback(Class<T> cls) {
            this.b = cls;
        }

        public Type f() {
            return this.b;
        }
    }
}
