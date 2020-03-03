package com.alibaba.android.arouter.launcher;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import com.alibaba.android.arouter.exception.InitException;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.facade.template.ILogger;
import java.util.concurrent.ThreadPoolExecutor;

public final class ARouter {

    /* renamed from: a  reason: collision with root package name */
    public static final String f722a = "NTeRQWvye18AkPd6G";
    public static final String b = "wmHzgD4lOj5o4241";
    public static ILogger c = null;
    private static volatile ARouter d = null;
    private static volatile boolean e = false;

    private ARouter() {
    }

    public static void a(Application application) {
        if (!e) {
            c = _ARouter.f723a;
            _ARouter.f723a.info("ARouter::", "ARouter init start.");
            e = _ARouter.a(application);
            if (e) {
                _ARouter.l();
            }
            _ARouter.f723a.info("ARouter::", "ARouter init over.");
        }
    }

    public static ARouter a() {
        if (e) {
            if (d == null) {
                synchronized (ARouter.class) {
                    if (d == null) {
                        d = new ARouter();
                    }
                }
            }
            return d;
        }
        throw new InitException("ARouter::Init::Invoke init(context) first!");
    }

    public static synchronized void b() {
        synchronized (ARouter.class) {
            _ARouter.c();
        }
    }

    public static boolean c() {
        return _ARouter.k();
    }

    public static synchronized void d() {
        synchronized (ARouter.class) {
            _ARouter.d();
        }
    }

    public static synchronized void e() {
        synchronized (ARouter.class) {
            _ARouter.h();
        }
    }

    public static synchronized void a(ThreadPoolExecutor threadPoolExecutor) {
        synchronized (ARouter.class) {
            _ARouter.a(threadPoolExecutor);
        }
    }

    public synchronized void f() {
        _ARouter.a();
        e = false;
    }

    @Deprecated
    public static synchronized void g() {
        synchronized (ARouter.class) {
            _ARouter.e();
        }
    }

    @Deprecated
    public static boolean h() {
        return _ARouter.f();
    }

    @Deprecated
    public static void i() {
        _ARouter.g();
    }

    public static synchronized void j() {
        synchronized (ARouter.class) {
            _ARouter.i();
        }
    }

    public static boolean k() {
        return _ARouter.j();
    }

    public static void a(ILogger iLogger) {
        _ARouter.a(iLogger);
    }

    public void a(Object obj) {
        _ARouter.a(obj);
    }

    public Postcard a(String str) {
        return _ARouter.b().a(str);
    }

    @Deprecated
    public Postcard a(String str, String str2) {
        return _ARouter.b().a(str, str2);
    }

    public Postcard a(Uri uri) {
        return _ARouter.b().a(uri);
    }

    public <T> T a(Class<? extends T> cls) {
        return _ARouter.b().a(cls);
    }

    public Object a(Context context, Postcard postcard, int i, NavigationCallback navigationCallback) {
        return _ARouter.b().a(context, postcard, i, navigationCallback);
    }
}
