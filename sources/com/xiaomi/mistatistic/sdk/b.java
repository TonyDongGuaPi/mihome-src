package com.xiaomi.mistatistic.sdk;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.StrictMode;
import com.xiaomi.mistatistic.sdk.controller.c;
import com.xiaomi.mistatistic.sdk.controller.e;
import com.xiaomi.mistatistic.sdk.controller.h;
import com.xiaomi.mistatistic.sdk.controller.j;
import com.xiaomi.mistatistic.sdk.controller.k;
import com.xiaomi.mistatistic.sdk.controller.n;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.TreeMap;

public class b implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private static int f11999a = 1;
    private static boolean b = false;
    private final Thread.UncaughtExceptionHandler c;

    public static void a(boolean z) {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (!(defaultUncaughtExceptionHandler instanceof b)) {
            if (z) {
                defaultUncaughtExceptionHandler = null;
            }
            Thread.setDefaultUncaughtExceptionHandler(new b(defaultUncaughtExceptionHandler));
            b = true;
        }
    }

    public static void a(a aVar, boolean z) {
        h.a("uploadException, isManually:" + z);
        if (b) {
            if (aVar == null || aVar.f12002a == null) {
                throw new IllegalArgumentException("the throwable is null.");
            } else if (aVar.f12002a.getStackTrace() != null && aVar.f12002a.getStackTrace().length != 0) {
                if (!BuildSetting.c(c.a())) {
                    h.d("not allowed to upload debug or exception log");
                    return;
                }
                StringWriter stringWriter = new StringWriter();
                aVar.f12002a.printStackTrace(new PrintWriter(stringWriter));
                String obj = stringWriter.toString();
                h.b("exception callstack:" + obj);
                final TreeMap treeMap = new TreeMap();
                treeMap.put("app_id", c.b());
                treeMap.put("app_key", c.c());
                treeMap.put("device_uuid", new e().a());
                treeMap.put("device_os", "Android " + Build.VERSION.SDK_INT);
                treeMap.put("device_model", Build.MODEL);
                treeMap.put("app_version", aVar.b);
                treeMap.put("app_channel", aVar.c);
                treeMap.put("app_start_time", aVar.d);
                treeMap.put("app_crash_time", aVar.e);
                treeMap.put("crash_exception_type", aVar.f12002a.getClass().getName() + ":" + aVar.f12002a.getMessage());
                treeMap.put("crash_exception_desc", aVar.f12002a instanceof OutOfMemoryError ? "OutOfMemoryError" : obj);
                treeMap.put("crash_callstack", obj);
                if (z) {
                    treeMap.put("manual", "true");
                }
                n.b.execute(new Runnable() {
                    public void run() {
                        try {
                            j.a(BuildSetting.a() ? "http://10.99.168.145:8097/micrash" : "https://data.mistat.xiaomi.com/micrash", treeMap, new j.b() {
                                public void a(String str) {
                                    h.a("upload exception result: " + str);
                                }
                            });
                        } catch (IOException e) {
                            h.a("Error to upload the exception ", (Throwable) e);
                        }
                    }
                });
            }
        }
    }

    public static class a implements Serializable {

        /* renamed from: a  reason: collision with root package name */
        public Throwable f12002a;
        public String b;
        public String c;
        public String d;
        public String e;

        public a(Throwable th) {
            this.f12002a = th;
            this.b = c.e();
            this.c = c.d();
            this.d = c.f();
            this.e = String.valueOf(System.currentTimeMillis());
        }

        public a() {
            this.f12002a = null;
            this.b = c.e();
            this.c = c.d();
            this.d = c.f();
            this.e = null;
        }
    }

    public b() {
        this.c = null;
    }

    public b(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.c = uncaughtExceptionHandler;
    }

    @SuppressLint({"NewApi"})
    public void uncaughtException(Thread thread, Throwable th) {
        h.a("uncaughtException...");
        if (Build.VERSION.SDK_INT >= 9) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().build());
        }
        com.xiaomi.mistatistic.sdk.controller.asyncjobs.a.b();
        if (!MiStatInterface.f()) {
            a(th);
        } else if (!a()) {
            a(new a(th), false);
        } else {
            h.a("crazy crash...");
        }
        if (this.c != null) {
            this.c.uncaughtException(thread, th);
        }
    }

    public void a(Thread thread, Throwable th) {
        h.a("uncaughtExceptionManually...");
        if (Build.VERSION.SDK_INT >= 9) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().build());
        }
        if (!MiStatInterface.f()) {
            a(th);
        } else if (!a()) {
            a(new a(th), true);
        } else {
            h.a("crazy crash, skip the crash");
        }
        if (this.c != null) {
            this.c.uncaughtException(thread, th);
        }
    }

    public boolean a() {
        if (System.currentTimeMillis() - k.a(c.a(), "crash_time", 0) > 300000) {
            k.b(c.a(), "crash_count", 1);
            k.b(c.a(), "crash_time", System.currentTimeMillis());
        } else {
            int a2 = k.a(c.a(), "crash_count", 0);
            if (a2 == 0) {
                k.b(c.a(), "crash_time", System.currentTimeMillis());
            }
            int i = a2 + 1;
            k.b(c.a(), "crash_count", i);
            if (i > 10) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0040 A[SYNTHETIC, Splitter:B:20:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0046 A[SYNTHETIC, Splitter:B:24:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.lang.Throwable r4) {
        /*
            java.util.ArrayList r0 = b()
            com.xiaomi.mistatistic.sdk.b$a r1 = new com.xiaomi.mistatistic.sdk.b$a
            r1.<init>(r4)
            r0.add(r1)
            int r4 = r0.size()
            r1 = 0
            r2 = 5
            if (r4 <= r2) goto L_0x0017
            r0.remove(r1)
        L_0x0017:
            r4 = 0
            android.content.Context r2 = com.xiaomi.mistatistic.sdk.controller.c.a()     // Catch:{ IOException -> 0x0038 }
            java.lang.String r3 = ".exceptiondetail"
            java.io.FileOutputStream r1 = r2.openFileOutput(r3, r1)     // Catch:{ IOException -> 0x0038 }
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x0038 }
            r2.<init>(r1)     // Catch:{ IOException -> 0x0038 }
            r2.writeObject(r0)     // Catch:{ IOException -> 0x0032, all -> 0x002e }
            r2.close()     // Catch:{ IOException -> 0x0043 }
            goto L_0x0043
        L_0x002e:
            r4 = move-exception
            r0 = r4
            r4 = r2
            goto L_0x0044
        L_0x0032:
            r4 = move-exception
            r0 = r4
            r4 = r2
            goto L_0x0039
        L_0x0036:
            r0 = move-exception
            goto L_0x0044
        L_0x0038:
            r0 = move-exception
        L_0x0039:
            java.lang.String r1 = ""
            com.xiaomi.mistatistic.sdk.controller.h.a((java.lang.String) r1, (java.lang.Throwable) r0)     // Catch:{ all -> 0x0036 }
            if (r4 == 0) goto L_0x0043
            r4.close()     // Catch:{ IOException -> 0x0043 }
        L_0x0043:
            return
        L_0x0044:
            if (r4 == 0) goto L_0x0049
            r4.close()     // Catch:{ IOException -> 0x0049 }
        L_0x0049:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mistatistic.sdk.b.a(java.lang.Throwable):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x004a A[SYNTHETIC, Splitter:B:26:0x004a] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0056 A[SYNTHETIC, Splitter:B:34:0x0056] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<com.xiaomi.mistatistic.sdk.b.a> b() {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.content.Context r2 = com.xiaomi.mistatistic.sdk.controller.c.a()     // Catch:{ Exception -> 0x0041 }
            java.io.File r2 = r2.getFilesDir()     // Catch:{ Exception -> 0x0041 }
            if (r2 == 0) goto L_0x0038
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x0041 }
            java.lang.String r4 = ".exceptiondetail"
            r3.<init>(r2, r4)     // Catch:{ Exception -> 0x0041 }
            boolean r2 = r3.isFile()     // Catch:{ Exception -> 0x0041 }
            if (r2 == 0) goto L_0x0038
            java.io.ObjectInputStream r2 = new java.io.ObjectInputStream     // Catch:{ Exception -> 0x0041 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0041 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0041 }
            r2.<init>(r4)     // Catch:{ Exception -> 0x0041 }
            java.lang.Object r1 = r2.readObject()     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            r0 = r1
            r1 = r2
            goto L_0x0038
        L_0x0030:
            r0 = move-exception
            r1 = r2
            goto L_0x0054
        L_0x0033:
            r1 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
            goto L_0x0042
        L_0x0038:
            if (r1 == 0) goto L_0x003d
            r1.close()     // Catch:{ IOException -> 0x003d }
        L_0x003d:
            r1 = 0
            goto L_0x004e
        L_0x003f:
            r0 = move-exception
            goto L_0x0054
        L_0x0041:
            r2 = move-exception
        L_0x0042:
            java.lang.String r3 = ""
            com.xiaomi.mistatistic.sdk.controller.h.a((java.lang.String) r3, (java.lang.Throwable) r2)     // Catch:{ all -> 0x003f }
            r2 = 1
            if (r1 == 0) goto L_0x004d
            r1.close()     // Catch:{ IOException -> 0x004d }
        L_0x004d:
            r1 = 1
        L_0x004e:
            if (r1 == 0) goto L_0x0053
            c()
        L_0x0053:
            return r0
        L_0x0054:
            if (r1 == 0) goto L_0x0059
            r1.close()     // Catch:{ IOException -> 0x0059 }
        L_0x0059:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mistatistic.sdk.b.b():java.util.ArrayList");
    }

    public static void c() {
        new File(c.a().getFilesDir(), ".exceptiondetail").delete();
    }

    public static void a(int i) {
        f11999a = i;
    }

    public static int d() {
        return f11999a;
    }
}
