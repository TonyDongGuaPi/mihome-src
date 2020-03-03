package com.tencent.tinker.loader.hotplug;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.hotplug.handler.AMSInterceptHandler;
import com.tencent.tinker.loader.hotplug.handler.MHMessageHandler;
import com.tencent.tinker.loader.hotplug.handler.PMSInterceptHandler;
import com.tencent.tinker.loader.hotplug.interceptor.HandlerMessageInterceptor;
import com.tencent.tinker.loader.hotplug.interceptor.ServiceBinderInterceptor;
import com.tencent.tinker.loader.hotplug.interceptor.TinkerHackInstrumentation;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;

public final class ComponentHotplug {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9237a = "Tinker.ComponentHotplug";
    private static volatile boolean b = false;
    private static ServiceBinderInterceptor c;
    private static ServiceBinderInterceptor d;
    private static HandlerMessageInterceptor e;
    private static TinkerHackInstrumentation f;

    public static synchronized void a(TinkerApplication tinkerApplication, ShareSecurityCheck shareSecurityCheck) throws UnsupportedEnvironmentException {
        synchronized (ComponentHotplug.class) {
            if (!b) {
                try {
                    if (IncrementComponentManager.a((Context) tinkerApplication, shareSecurityCheck)) {
                        c = new ServiceBinderInterceptor(tinkerApplication, "activity", new AMSInterceptHandler(tinkerApplication));
                        d = new ServiceBinderInterceptor(tinkerApplication, "package", new PMSInterceptHandler());
                        c.c();
                        d.c();
                        if (Build.VERSION.SDK_INT < 27) {
                            e = new HandlerMessageInterceptor(a((Context) tinkerApplication), new MHMessageHandler(tinkerApplication));
                            e.c();
                        } else {
                            f = TinkerHackInstrumentation.create(tinkerApplication);
                            f.install();
                        }
                        b = true;
                        Log.i(f9237a, "installed successfully.");
                    }
                } catch (Throwable th) {
                    a();
                    throw new UnsupportedEnvironmentException(th);
                }
            }
        }
    }

    public static synchronized void a(TinkerApplication tinkerApplication) throws UnsupportedEnvironmentException {
        synchronized (ComponentHotplug.class) {
            if (b) {
                try {
                    c.c();
                    d.c();
                    if (Build.VERSION.SDK_INT < 27) {
                        e.c();
                    } else {
                        f.install();
                    }
                } catch (Throwable th) {
                    a();
                    throw new UnsupportedEnvironmentException(th);
                }
            } else {
                Log.i(f9237a, "method install() is not invoked, ignore ensuring operations.");
            }
        }
    }

    private static Handler a(Context context) {
        Object a2 = ShareReflectUtil.a(context, (Class<?>) null);
        if (a2 != null) {
            try {
                return (Handler) ShareReflectUtil.a(a2, "mH").get(a2);
            } catch (Throwable th) {
                throw new IllegalStateException(th);
            }
        } else {
            throw new IllegalStateException("failed to fetch instance of ActivityThread.");
        }
    }

    public static synchronized void a() {
        synchronized (ComponentHotplug.class) {
            if (b) {
                try {
                    c.d();
                    d.d();
                    if (Build.VERSION.SDK_INT < 27) {
                        e.d();
                    } else {
                        f.uninstall();
                    }
                } catch (Throwable th) {
                    Log.e(f9237a, "exception when uninstall.", th);
                }
                b = false;
            }
        }
    }

    private ComponentHotplug() {
        throw new UnsupportedOperationException();
    }
}
