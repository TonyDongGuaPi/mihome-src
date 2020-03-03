package com.xiaomi.miot.support.monitor.aop.okhttp3;

import com.xiaomi.miot.support.monitor.core.MiotDataStorage;
import com.xiaomi.miot.support.monitor.core.net.NetInfo;
import com.xiaomi.miot.support.monitor.core.tasks.TaskManager;
import com.xiaomi.miot.support.monitor.utils.LogX;
import java.lang.reflect.Field;
import okhttp3.OkHttpClient;
import okhttp3.Route;
import okhttp3.internal.connection.RealConnection;
import org.aspectj.lang.NoAspectBoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class OkHttp3Aspect {

    /* renamed from: a  reason: collision with root package name */
    public static final OkHttp3Aspect f11448a = null;
    private static final String b = "OkHttp3Aspect";
    private static Throwable c;

    static {
        try {
            f();
        } catch (Throwable th) {
            c = th;
        }
    }

    public static OkHttp3Aspect d() {
        if (f11448a != null) {
            return f11448a;
        }
        throw new NoAspectBoundException("com.xiaomi.miot.support.monitor.aop.okhttp3.OkHttp3Aspect", c);
    }

    public static boolean e() {
        return f11448a != null;
    }

    private static void f() {
        f11448a = new OkHttp3Aspect();
    }

    @Pointcut("call(public okhttp3.OkHttpClient build())")
    public void a() {
    }

    @Pointcut("execution(* okhttp3.internal.connection.RealConnection.connectSocket(..))")
    public void b() {
    }

    @Pointcut("execution(* okhttp3.internal.connection.RealConnection.establishProtocol(..))")
    public void c() {
    }

    @Around("build()")
    public Object a(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LogX.d(b, "aroundBuild");
        Object d = proceedingJoinPoint.d();
        if ((d instanceof OkHttpClient.Builder) && TaskManager.a().b("net")) {
            ((OkHttpClient.Builder) d).addInterceptor(new NetWorkInterceptor());
        }
        return proceedingJoinPoint.j();
    }

    @Around("connectPointCut()")
    public Object b(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long currentTimeMillis = System.currentTimeMillis();
        Object j = proceedingJoinPoint.j();
        long currentTimeMillis2 = System.currentTimeMillis();
        String str = "";
        try {
            if (proceedingJoinPoint.d() instanceof RealConnection) {
                Field declaredField = proceedingJoinPoint.d().getClass().getDeclaredField("route");
                declaredField.setAccessible(true);
                Route route = (Route) declaredField.get(proceedingJoinPoint.d());
                str = route.address().url().host() + route.address().url().encodedPath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        NetInfo netInfo = new NetInfo();
        netInfo.costTime = currentTimeMillis2 - currentTimeMillis;
        MiotDataStorage.a().a(MiotDataStorage.b, str, netInfo);
        return j;
    }

    @Around("connectSSLPointCut()")
    public Object c(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long currentTimeMillis = System.currentTimeMillis();
        Object j = proceedingJoinPoint.j();
        long currentTimeMillis2 = System.currentTimeMillis();
        String str = "";
        try {
            if (proceedingJoinPoint.d() instanceof RealConnection) {
                Field declaredField = proceedingJoinPoint.d().getClass().getDeclaredField("route");
                declaredField.setAccessible(true);
                Route route = (Route) declaredField.get(proceedingJoinPoint.d());
                str = route.address().url().host() + route.address().url().encodedPath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        NetInfo netInfo = new NetInfo();
        netInfo.costTime = currentTimeMillis2 - currentTimeMillis;
        MiotDataStorage.a().a(MiotDataStorage.c, str, netInfo);
        return j;
    }
}
