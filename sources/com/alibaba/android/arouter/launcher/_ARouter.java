package com.alibaba.android.arouter.launcher;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;
import com.alibaba.android.arouter.core.InstrumentationHook;
import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.exception.HandlerException;
import com.alibaba.android.arouter.exception.InitException;
import com.alibaba.android.arouter.exception.NoRouteFoundException;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.facade.service.AutowiredService;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.alibaba.android.arouter.facade.service.InterceptorService;
import com.alibaba.android.arouter.facade.service.PathReplaceService;
import com.alibaba.android.arouter.facade.template.ILogger;
import com.alibaba.android.arouter.thread.DefaultPoolExecutor;
import com.alibaba.android.arouter.utils.DefaultLogger;
import com.alibaba.android.arouter.utils.TextUtils;
import com.google.android.exoplayer2.C;
import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ThreadPoolExecutor;

final class _ARouter {

    /* renamed from: a  reason: collision with root package name */
    static ILogger f723a = new DefaultLogger("ARouter::");
    private static volatile boolean b = false;
    private static volatile boolean c = false;
    private static volatile boolean d = false;
    private static volatile _ARouter e = null;
    private static volatile boolean f = false;
    private static volatile ThreadPoolExecutor g = DefaultPoolExecutor.a();
    private static Handler h;
    /* access modifiers changed from: private */
    public static Context i;
    private static InterceptorService j;

    private _ARouter() {
    }

    protected static synchronized boolean a(Application application) {
        synchronized (_ARouter.class) {
            i = application;
            LogisticsCenter.a(i, g);
            f723a.info("ARouter::", "ARouter init success!");
            f = true;
            h = new Handler(Looper.getMainLooper());
        }
        return true;
    }

    static synchronized void a() {
        synchronized (_ARouter.class) {
            if (k()) {
                f = false;
                LogisticsCenter.a();
                f723a.info("ARouter::", "ARouter destroy success!");
            } else {
                f723a.error("ARouter::", "Destroy can be used in debug mode only!");
            }
        }
    }

    protected static _ARouter b() {
        if (f) {
            if (e == null) {
                synchronized (_ARouter.class) {
                    if (e == null) {
                        e = new _ARouter();
                    }
                }
            }
            return e;
        }
        throw new InitException("ARouterCore::Init::Invoke init(context) first!");
    }

    static synchronized void c() {
        synchronized (_ARouter.class) {
            c = true;
            f723a.info("ARouter::", "ARouter openDebug");
        }
    }

    static synchronized void d() {
        synchronized (_ARouter.class) {
            f723a.showLog(true);
            f723a.info("ARouter::", "ARouter openLog");
        }
    }

    @Deprecated
    static synchronized void e() {
        synchronized (_ARouter.class) {
            d = true;
        }
    }

    @Deprecated
    static boolean f() {
        return d;
    }

    @Deprecated
    static void g() {
        Log.i("ARouter::", "ARouter start attachBaseContext");
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke((Object) null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mInstrumentation");
            declaredField.setAccessible(true);
            declaredField.set(invoke, new InstrumentationHook());
            Log.i("ARouter::", "ARouter hook instrumentation success!");
        } catch (Exception e2) {
            Log.e("ARouter::", "ARouter hook instrumentation failed! [" + e2.getMessage() + Operators.ARRAY_END_STR);
        }
    }

    static synchronized void h() {
        synchronized (_ARouter.class) {
            f723a.showStackTrace(true);
            f723a.info("ARouter::", "ARouter printStackTrace");
        }
    }

    static synchronized void a(ThreadPoolExecutor threadPoolExecutor) {
        synchronized (_ARouter.class) {
            g = threadPoolExecutor;
        }
    }

    static synchronized void i() {
        synchronized (_ARouter.class) {
            b = true;
            f723a.info("ARouter::", "ARouter monitorMode on");
        }
    }

    static boolean j() {
        return b;
    }

    static boolean k() {
        return c;
    }

    static void a(ILogger iLogger) {
        if (iLogger != null) {
            f723a = iLogger;
        }
    }

    static void a(Object obj) {
        AutowiredService autowiredService = (AutowiredService) ARouter.a().a("/arouter/service/autowired").navigation();
        if (autowiredService != null) {
            autowiredService.autowire(obj);
        }
    }

    /* access modifiers changed from: protected */
    public Postcard a(String str) {
        if (!TextUtils.a((CharSequence) str)) {
            PathReplaceService pathReplaceService = (PathReplaceService) ARouter.a().a(PathReplaceService.class);
            if (pathReplaceService != null) {
                str = pathReplaceService.forString(str);
            }
            return a(str, b(str));
        }
        throw new HandlerException("ARouter::Parameter is invalid!");
    }

    /* access modifiers changed from: protected */
    public Postcard a(Uri uri) {
        if (uri == null || TextUtils.a((CharSequence) uri.toString())) {
            throw new HandlerException("ARouter::Parameter invalid!");
        }
        PathReplaceService pathReplaceService = (PathReplaceService) ARouter.a().a(PathReplaceService.class);
        if (pathReplaceService != null) {
            uri = pathReplaceService.forUri(uri);
        }
        return new Postcard(uri.getPath(), b(uri.getPath()), uri, (Bundle) null);
    }

    /* access modifiers changed from: protected */
    public Postcard a(String str, String str2) {
        if (TextUtils.a((CharSequence) str) || TextUtils.a((CharSequence) str2)) {
            throw new HandlerException("ARouter::Parameter is invalid!");
        }
        PathReplaceService pathReplaceService = (PathReplaceService) ARouter.a().a(PathReplaceService.class);
        if (pathReplaceService != null) {
            str = pathReplaceService.forString(str);
        }
        return new Postcard(str, str2);
    }

    private String b(String str) {
        if (TextUtils.a((CharSequence) str) || !str.startsWith("/")) {
            throw new HandlerException("ARouter::Extract the default group failed, the path must be start with '/' and contain more than 2 '/'!");
        }
        try {
            String substring = str.substring(1, str.indexOf("/", 1));
            if (!TextUtils.a((CharSequence) substring)) {
                return substring;
            }
            throw new HandlerException("ARouter::Extract the default group failed! There's nothing between 2 '/'!");
        } catch (Exception e2) {
            ILogger iLogger = f723a;
            iLogger.warning("ARouter::", "Failed to extract default group! " + e2.getMessage());
            return null;
        }
    }

    static void l() {
        j = (InterceptorService) ARouter.a().a("/arouter/service/interceptor").navigation();
    }

    /* access modifiers changed from: protected */
    public <T> T a(Class<? extends T> cls) {
        try {
            Postcard a2 = LogisticsCenter.a(cls.getName());
            if (a2 == null) {
                a2 = LogisticsCenter.a(cls.getSimpleName());
            }
            if (a2 == null) {
                return null;
            }
            LogisticsCenter.a(a2);
            return a2.getProvider();
        } catch (NoRouteFoundException e2) {
            f723a.warning("ARouter::", e2.getMessage());
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public Object a(Context context, final Postcard postcard, int i2, NavigationCallback navigationCallback) {
        try {
            LogisticsCenter.a(postcard);
            if (navigationCallback != null) {
                navigationCallback.onFound(postcard);
            }
            if (postcard.isGreenChannel()) {
                return b(context, postcard, i2, navigationCallback);
            }
            final Context context2 = context;
            final int i3 = i2;
            final NavigationCallback navigationCallback2 = navigationCallback;
            final Postcard postcard2 = postcard;
            j.doInterceptions(postcard, new InterceptorCallback() {
                public void onContinue(Postcard postcard) {
                    Object unused = _ARouter.this.b(context2, postcard, i3, navigationCallback2);
                }

                public void onInterrupt(Throwable th) {
                    if (navigationCallback2 != null) {
                        navigationCallback2.onInterrupt(postcard2);
                    }
                    ILogger iLogger = _ARouter.f723a;
                    iLogger.info("ARouter::", "Navigation failed, termination by interceptor : " + th.getMessage());
                }
            });
            return null;
        } catch (NoRouteFoundException e2) {
            f723a.warning("ARouter::", e2.getMessage());
            if (k()) {
                a((Runnable) new Runnable() {
                    public void run() {
                        Context m = _ARouter.i;
                        Toast.makeText(m, "There's no route matched!\n Path = [" + postcard.getPath() + "]\n Group = [" + postcard.getGroup() + Operators.ARRAY_END_STR, 1).show();
                    }
                });
            }
            if (navigationCallback != null) {
                navigationCallback.onLost(postcard);
            } else {
                DegradeService degradeService = (DegradeService) ARouter.a().a(DegradeService.class);
                if (degradeService != null) {
                    degradeService.onLost(context, postcard);
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: private */
    public Object b(Context context, Postcard postcard, int i2, NavigationCallback navigationCallback) {
        if (context == null) {
            context = i;
        }
        final Context context2 = context;
        switch (postcard.getType()) {
            case ACTIVITY:
                final Intent intent = new Intent(context2, postcard.getDestination());
                intent.putExtras(postcard.getExtras());
                int flags = postcard.getFlags();
                if (-1 != flags) {
                    intent.setFlags(flags);
                } else if (!(context2 instanceof Activity)) {
                    intent.setFlags(C.ENCODING_PCM_MU_LAW);
                }
                String action = postcard.getAction();
                if (!TextUtils.a((CharSequence) action)) {
                    intent.setAction(action);
                }
                final int i3 = i2;
                final Postcard postcard2 = postcard;
                final NavigationCallback navigationCallback2 = navigationCallback;
                a((Runnable) new Runnable() {
                    public void run() {
                        _ARouter.this.a(i3, context2, intent, postcard2, navigationCallback2);
                    }
                });
                return null;
            case PROVIDER:
                return postcard.getProvider();
            case BOARDCAST:
            case CONTENT_PROVIDER:
            case FRAGMENT:
                try {
                    Object newInstance = postcard.getDestination().getConstructor(new Class[0]).newInstance(new Object[0]);
                    if (newInstance instanceof Fragment) {
                        ((Fragment) newInstance).setArguments(postcard.getExtras());
                    } else if (newInstance instanceof android.support.v4.app.Fragment) {
                        ((android.support.v4.app.Fragment) newInstance).setArguments(postcard.getExtras());
                    }
                    return newInstance;
                } catch (Exception e2) {
                    ILogger iLogger = f723a;
                    iLogger.error("ARouter::", "Fetch fragment instance error, " + TextUtils.a(e2.getStackTrace()));
                    break;
                }
        }
        return null;
    }

    private void a(Runnable runnable) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            h.post(runnable);
        } else {
            runnable.run();
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2, Context context, Intent intent, Postcard postcard, NavigationCallback navigationCallback) {
        if (i2 < 0) {
            ActivityCompat.startActivity(context, intent, postcard.getOptionsBundle());
        } else if (context instanceof Activity) {
            ActivityCompat.startActivityForResult((Activity) context, intent, i2, postcard.getOptionsBundle());
        } else {
            f723a.warning("ARouter::", "Must use [navigation(activity, ...)] to support [startActivityForResult]");
        }
        if (!(-1 == postcard.getEnterAnim() || -1 == postcard.getExitAnim() || !(context instanceof Activity))) {
            ((Activity) context).overridePendingTransition(postcard.getEnterAnim(), postcard.getExitAnim());
        }
        if (navigationCallback != null) {
            navigationCallback.onArrival(postcard);
        }
    }
}
