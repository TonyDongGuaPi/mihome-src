package com.xiaomi.youpin.hawkeye;

import android.content.Context;
import android.util.Log;
import com.xiaomi.youpin.hawkeye.appstart.AppStartTask;
import com.xiaomi.youpin.hawkeye.network.HNetWorkRecordIntercept;
import okhttp3.OkHttpClient;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.NoAspectBoundException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class HawkEyeAspect {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1577a = "HawkEyeAspect";
    public static HawkEyeAspect b;
    private static Throwable c;

    @Pointcut("execution(* android.app.Application.attachBaseContext(android.content.Context)) && args(context)")
    public void a(Context context) {
    }

    static {
        try {
            b = new HawkEyeAspect();
        } catch (Throwable th) {
            c = th;
        }
    }

    public static HawkEyeAspect a() {
        if (b != null) {
            return b;
        }
        throw new NoAspectBoundException("com.xiaomi.youpin.hawkeye.HawkEyeAspect", c);
    }

    @Before("applicationAttachBaseContext(context)")
    public void b(Context context) {
        Log.d(f1577a, " Aspect appStart was success ");
        AppStartTask.b();
    }

    @Before("call(* okhttp3.OkHttpClient.Builder.build(..))")
    public void a(JoinPoint joinPoint) {
        Log.d(f1577a, " Aspect addInterceptor was success " + joinPoint.d());
        ((OkHttpClient.Builder) joinPoint.d()).addInterceptor(new HNetWorkRecordIntercept());
    }
}
