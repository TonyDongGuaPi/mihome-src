package org.xutils.http.request;

import android.text.TextUtils;
import java.lang.reflect.Type;
import java.util.HashMap;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.app.RequestTracker;

public final class UriRequestFactory {

    /* renamed from: a  reason: collision with root package name */
    private static Class<? extends RequestTracker> f10788a;
    private static final HashMap<String, Class<? extends UriRequest>> b = new HashMap<>();

    private UriRequestFactory() {
    }

    public static UriRequest a(RequestParams requestParams, Type type) throws Throwable {
        String str;
        String o = requestParams.o();
        int indexOf = o.indexOf(":");
        if (indexOf > 0) {
            str = o.substring(0, indexOf);
        } else {
            str = o.startsWith("/") ? "file" : null;
        }
        if (!TextUtils.isEmpty(str)) {
            Class cls = b.get(str);
            if (cls != null) {
                return (UriRequest) cls.getConstructor(new Class[]{RequestParams.class, Class.class}).newInstance(new Object[]{requestParams, type});
            } else if (str.startsWith("http")) {
                return new HttpRequest(requestParams, type);
            } else {
                if (str.equals("assets")) {
                    return new AssetsRequest(requestParams, type);
                }
                if (str.equals("file")) {
                    return new LocalFileRequest(requestParams, type);
                }
                throw new IllegalArgumentException("The url not be support: " + o);
            }
        } else {
            throw new IllegalArgumentException("The url not be support: " + o);
        }
    }

    public static void a(Class<? extends RequestTracker> cls) {
        f10788a = cls;
    }

    public static RequestTracker a() {
        try {
            if (f10788a == null) {
                return null;
            }
            return (RequestTracker) f10788a.newInstance();
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
            return null;
        }
    }

    public static void a(String str, Class<? extends UriRequest> cls) {
        b.put(str, cls);
    }
}
