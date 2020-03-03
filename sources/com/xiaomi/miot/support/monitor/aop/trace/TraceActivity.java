package com.xiaomi.miot.support.monitor.aop.trace;

import android.app.Activity;
import android.content.Context;
import com.xiaomi.miot.support.monitor.MiotMonitorClient;
import com.xiaomi.miot.support.monitor.MiotMonitorManager;
import com.xiaomi.miot.support.monitor.core.MiotDataStorage;
import com.xiaomi.miot.support.monitor.core.activity.ActivityCore;
import com.xiaomi.miot.support.monitor.core.appstart.AppStartInfo;
import com.xiaomi.miot.support.monitor.utils.LogX;
import com.xiaomi.miot.support.monitor.utils.TimeRecodUtils;
import org.aspectj.lang.NoAspectBoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TraceActivity {

    /* renamed from: a  reason: collision with root package name */
    public static final TraceActivity f1473a = null;
    private static final String b = "TraceActivity";
    private static Throwable d;
    /* access modifiers changed from: private */
    public boolean c = false;

    static {
        try {
            d();
        } catch (Throwable th) {
            d = th;
        }
    }

    public static TraceActivity b() {
        if (f1473a != null) {
            return f1473a;
        }
        throw new NoAspectBoundException("com.xiaomi.miot.support.monitor.aop.trace.TraceActivity", d);
    }

    public static boolean c() {
        return f1473a != null;
    }

    private static void d() {
        f1473a = new TraceActivity();
    }

    @Pointcut("execution(* com.xiaomi.smarthome.SmartHomeMainActivity.onCreate(..))")
    public void a() {
    }

    @Pointcut("execution(* android.app.Application.attachBaseContext(android.content.Context)) && args(context)")
    public void a(Context context) {
    }

    @Around("startMainActivity()")
    public Object a(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LogX.d(b, "aroundStartMainActivity: ");
        if (ActivityCore.f1480a == 0) {
            ActivityCore.f1480a = System.currentTimeMillis();
            TimeRecodUtils.a(TimeRecodUtils.f1492a, Long.valueOf(ActivityCore.f1480a));
        }
        TimeRecodUtils.a(TimeRecodUtils.b, Long.valueOf(System.currentTimeMillis()));
        if (MiotMonitorManager.a().c().j) {
            MiotMonitorManager.a().c().j = false;
            MiotMonitorClient.g();
        }
        Object d2 = proceedingJoinPoint.d();
        if (d2 instanceof Activity) {
            ((Activity) d2).getWindow().getDecorView().post(new Runnable() {
                public void run() {
                    int i;
                    int i2;
                    if (ActivityCore.f1480a > 0) {
                        if (TraceActivity.this.c) {
                            i2 = 1;
                            i = (int) (System.currentTimeMillis() - ActivityCore.f1480a);
                        } else {
                            i2 = 2;
                            i = (int) (System.currentTimeMillis() - TimeRecodUtils.b(TimeRecodUtils.b).longValue());
                        }
                        boolean unused = TraceActivity.this.c = false;
                        if (i <= 10000) {
                            MiotDataStorage.a().a(new AppStartInfo(i, i2));
                        }
                    }
                }
            });
        }
        return proceedingJoinPoint.j();
    }

    @Before("applicationAttachBaseContext(context)")
    public void b(Context context) {
        LogX.d(b, "applicationAttachBaseContextAdvice: ");
        this.c = true;
        ActivityCore.f1480a = System.currentTimeMillis();
        TimeRecodUtils.a(TimeRecodUtils.f1492a, Long.valueOf(ActivityCore.f1480a));
    }
}
