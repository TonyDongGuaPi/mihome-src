package com.xiaomi.smarthome.framework.log;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;
import android.os.Process;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class MyLog {

    /* renamed from: a  reason: collision with root package name */
    public static final int f1539a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    private static int g = 3;
    private static final Object h = new Object();
    private static HashMap<Integer, Long> i = new HashMap<>();
    private static HashMap<Integer, String> j = new HashMap<>();
    private static final Integer k = -1;
    private static AtomicInteger l = new AtomicInteger(1);

    public static void a(String str) {
        a(3, str);
    }

    public static void b(String str) {
        a(2, str);
    }

    public static void c(String str) {
        a(2, str);
    }

    public static void d(String str) {
        a(1, str);
    }

    public static void a(String str, String str2) {
        a(1, str2);
    }

    public static void e(String str) {
        a(0, str);
    }

    public static void a(Object[] objArr) {
        a(0, XMStringUtils.a(objArr, ","));
    }

    public static void a(String str, Throwable th) {
        a(4, str, th);
    }

    public static void a(Throwable th) {
        a(4, th);
    }

    public static void f(String str) {
        a(4, str);
    }

    public static void a(Context context, String str) {
        Debug.MemoryInfo[] processMemoryInfo = ((ActivityManager) context.getSystemService("activity")).getProcessMemoryInfo(new int[]{Process.myPid()});
        b("+++Heap Info+++" + str + " total:" + processMemoryInfo[0].getTotalPss() + ", managed:" + processMemoryInfo[0].dalvikPss + ", native:" + processMemoryInfo[0].nativePss);
    }

    public static Integer g(String str) {
        Integer valueOf;
        if (g > 1) {
            return k;
        }
        synchronized (h) {
            valueOf = Integer.valueOf(l.incrementAndGet());
            i.put(valueOf, Long.valueOf(System.currentTimeMillis()));
            j.put(valueOf, str);
        }
        MiLiaoDebugLog a2 = MiLiaoDebugLog.a();
        a2.a(str + " starts");
        return valueOf;
    }

    public static void a(Integer num) {
        if (g <= 1) {
            synchronized (h) {
                if (i.containsKey(num)) {
                    long longValue = i.remove(num).longValue();
                    String remove = j.remove(num);
                    long currentTimeMillis = System.currentTimeMillis() - longValue;
                    MiLiaoDebugLog a2 = MiLiaoDebugLog.a();
                    a2.a(remove + " ends in " + currentTimeMillis + " ms");
                }
            }
        }
    }

    public static void a(int i2, String str) {
        if (i2 >= g) {
            MiLiaoDebugLog.a().a(str);
        }
    }

    public static void a(int i2, Throwable th) {
        if (i2 >= g) {
            MiLiaoDebugLog.a().a("", th);
        }
    }

    public static void a(int i2, String str, Throwable th) {
        if (i2 >= g) {
            MiLiaoDebugLog.a().a(str, th);
        }
    }

    public static void b(String str, String str2) {
        MiLiaoDebugLog.a().a(str, str2);
    }

    public static void a(int i2) {
        if (i2 < 2 || i2 > 5) {
            a(3, "set log level as " + i2);
        }
        g = i2;
    }

    public static int a() {
        return g;
    }

    public static void h(String str) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        printWriter.println(str);
        printWriter.println(String.format("Current thread id (%s); thread name (%s)", new Object[]{Long.valueOf(Thread.currentThread().getId()), Thread.currentThread().getName()}));
        new Throwable("Call stack").printStackTrace(printWriter);
        e(stringWriter.toString());
    }
}
