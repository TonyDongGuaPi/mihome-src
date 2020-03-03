package com.tencent.bugly.crashreport.crash.jni;

import android.annotation.SuppressLint;
import android.content.Context;
import com.tencent.bugly.crashreport.a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.b;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import com.tencent.smtt.utils.TbsLog;
import com.xiaomi.smarthome.download.Constants;
import java.io.File;

public class NativeCrashHandler implements a {

    /* renamed from: a  reason: collision with root package name */
    private static NativeCrashHandler f9023a = null;
    private static boolean l = false;
    private static boolean m = false;
    /* access modifiers changed from: private */
    public final Context b;
    /* access modifiers changed from: private */
    public final com.tencent.bugly.crashreport.common.info.a c;
    private final w d;
    /* access modifiers changed from: private */
    public NativeExceptionHandler e;
    /* access modifiers changed from: private */
    public String f;
    private final boolean g;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    /* access modifiers changed from: private */
    public b n;

    /* access modifiers changed from: protected */
    public native boolean appendNativeLog(String str, String str2, String str3);

    /* access modifiers changed from: protected */
    public native boolean appendWholeNativeLog(String str);

    /* access modifiers changed from: protected */
    public native String getNativeKeyValueList();

    /* access modifiers changed from: protected */
    public native String getNativeLog();

    /* access modifiers changed from: protected */
    public native boolean putNativeKeyValue(String str, String str2);

    /* access modifiers changed from: protected */
    public native String regist(String str, boolean z, int i2);

    /* access modifiers changed from: protected */
    public native String removeNativeKeyValue(String str);

    /* access modifiers changed from: protected */
    public native void setNativeInfo(int i2, String str);

    /* access modifiers changed from: protected */
    public native void testCrash();

    /* access modifiers changed from: protected */
    public native String unregist();

    @SuppressLint({"SdCardPath"})
    private NativeCrashHandler(Context context, com.tencent.bugly.crashreport.common.info.a aVar, b bVar, w wVar, boolean z, String str) {
        this.b = z.a(context);
        try {
            if (z.a(str)) {
                str = context.getDir("bugly", 0).getAbsolutePath();
            }
        } catch (Throwable unused) {
            str = "/data/data/" + com.tencent.bugly.crashreport.common.info.a.a(context).c + "/app_bugly";
        }
        this.n = bVar;
        this.f = str;
        this.c = aVar;
        this.d = wVar;
        this.g = z;
        this.e = new a(context, aVar, bVar, com.tencent.bugly.crashreport.common.strategy.a.a());
    }

    public static synchronized NativeCrashHandler getInstance(Context context, com.tencent.bugly.crashreport.common.info.a aVar, b bVar, com.tencent.bugly.crashreport.common.strategy.a aVar2, w wVar, boolean z, String str) {
        NativeCrashHandler nativeCrashHandler;
        synchronized (NativeCrashHandler.class) {
            if (f9023a == null) {
                f9023a = new NativeCrashHandler(context, aVar, bVar, wVar, z, str);
            }
            nativeCrashHandler = f9023a;
        }
        return nativeCrashHandler;
    }

    public static synchronized NativeCrashHandler getInstance() {
        NativeCrashHandler nativeCrashHandler;
        synchronized (NativeCrashHandler.class) {
            nativeCrashHandler = f9023a;
        }
        return nativeCrashHandler;
    }

    public synchronized String getDumpFilePath() {
        return this.f;
    }

    public synchronized void setDumpFilePath(String str) {
        this.f = str;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(16:14|(1:16)(15:18|(1:20)|21|22|(1:24)|25|(1:27)|28|29|(1:31)(1:32)|33|(1:35)(1:36)|37|38|39)|17|21|22|(0)|25|(0)|28|29|(0)(0)|33|(0)(0)|37|38|39) */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:7|8|9|(3:11|12|(16:14|(1:16)(15:18|(1:20)|21|22|(1:24)|25|(1:27)|28|29|(1:31)(1:32)|33|(1:35)(1:36)|37|38|39)|17|21|22|(0)|25|(0)|28|29|(0)(0)|33|(0)(0)|37|38|39))(2:44|(7:46|47|48|(1:50)(1:51)|52|(1:54)|(7:56|(1:58)|59|(1:61)|62|63|64)))|65|66|67|68) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x008d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x018d */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007f A[Catch:{ Throwable -> 0x008d }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008b A[Catch:{ Throwable -> 0x008d }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0091 A[Catch:{ Throwable -> 0x00bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0099 A[Catch:{ Throwable -> 0x00bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a4 A[Catch:{ Throwable -> 0x00bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ac A[Catch:{ Throwable -> 0x00bb }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(boolean r11) {
        /*
            r10 = this;
            monitor-enter(r10)
            boolean r0 = r10.j     // Catch:{ all -> 0x0193 }
            r1 = 0
            if (r0 == 0) goto L_0x000f
            java.lang.String r11 = "[Native] Native crash report has already registered."
            java.lang.Object[] r0 = new java.lang.Object[r1]     // Catch:{ all -> 0x0193 }
            com.tencent.bugly.proguard.x.d(r11, r0)     // Catch:{ all -> 0x0193 }
            monitor-exit(r10)
            return
        L_0x000f:
            boolean r0 = r10.i     // Catch:{ all -> 0x0193 }
            r2 = 2
            r3 = 1
            if (r0 == 0) goto L_0x00c4
            java.lang.String r0 = r10.f     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r11 = r10.regist(r0, r11, r3)     // Catch:{ Throwable -> 0x00bb }
            if (r11 == 0) goto L_0x018d
            java.lang.String r0 = "[Native] Native Crash Report enable."
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x00bb }
            com.tencent.bugly.proguard.x.a(r0, r4)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r0 = "[Native] Check extra jni for Bugly NDK v%s"
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x00bb }
            r4[r1] = r11     // Catch:{ Throwable -> 0x00bb }
            com.tencent.bugly.proguard.x.c(r0, r4)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r0 = "2.1.1"
            java.lang.String r4 = "."
            java.lang.String r5 = ""
            java.lang.String r0 = r0.replace(r4, r5)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r4 = "2.3.0"
            java.lang.String r5 = "."
            java.lang.String r6 = ""
            java.lang.String r4 = r4.replace(r5, r6)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r5 = "."
            java.lang.String r6 = ""
            java.lang.String r5 = r11.replace(r5, r6)     // Catch:{ Throwable -> 0x00bb }
            int r6 = r5.length()     // Catch:{ Throwable -> 0x00bb }
            if (r6 != r2) goto L_0x0061
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00bb }
            r2.<init>()     // Catch:{ Throwable -> 0x00bb }
            r2.append(r5)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r5 = "0"
            r2.append(r5)     // Catch:{ Throwable -> 0x00bb }
        L_0x005c:
            java.lang.String r5 = r2.toString()     // Catch:{ Throwable -> 0x00bb }
            goto L_0x0075
        L_0x0061:
            int r2 = r5.length()     // Catch:{ Throwable -> 0x00bb }
            if (r2 != r3) goto L_0x0075
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00bb }
            r2.<init>()     // Catch:{ Throwable -> 0x00bb }
            r2.append(r5)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r5 = "00"
            r2.append(r5)     // Catch:{ Throwable -> 0x00bb }
            goto L_0x005c
        L_0x0075:
            int r2 = java.lang.Integer.parseInt(r5)     // Catch:{ Throwable -> 0x008d }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Throwable -> 0x008d }
            if (r2 < r0) goto L_0x0081
            l = r3     // Catch:{ Throwable -> 0x008d }
        L_0x0081:
            int r0 = java.lang.Integer.parseInt(r5)     // Catch:{ Throwable -> 0x008d }
            int r2 = java.lang.Integer.parseInt(r4)     // Catch:{ Throwable -> 0x008d }
            if (r0 < r2) goto L_0x008d
            m = r3     // Catch:{ Throwable -> 0x008d }
        L_0x008d:
            boolean r0 = m     // Catch:{ Throwable -> 0x00bb }
            if (r0 == 0) goto L_0x0099
            java.lang.String r0 = "[Native] Info setting jni can be accessed."
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x00bb }
            com.tencent.bugly.proguard.x.a(r0, r2)     // Catch:{ Throwable -> 0x00bb }
            goto L_0x00a0
        L_0x0099:
            java.lang.String r0 = "[Native] Info setting jni can not be accessed."
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x00bb }
            com.tencent.bugly.proguard.x.d(r0, r2)     // Catch:{ Throwable -> 0x00bb }
        L_0x00a0:
            boolean r0 = l     // Catch:{ Throwable -> 0x00bb }
            if (r0 == 0) goto L_0x00ac
            java.lang.String r0 = "[Native] Extra jni can be accessed."
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x00bb }
            com.tencent.bugly.proguard.x.a(r0, r2)     // Catch:{ Throwable -> 0x00bb }
            goto L_0x00b3
        L_0x00ac:
            java.lang.String r0 = "[Native] Extra jni can not be accessed."
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x00bb }
            com.tencent.bugly.proguard.x.d(r0, r2)     // Catch:{ Throwable -> 0x00bb }
        L_0x00b3:
            com.tencent.bugly.crashreport.common.info.a r0 = r10.c     // Catch:{ Throwable -> 0x00bb }
            r0.n = r11     // Catch:{ Throwable -> 0x00bb }
            r10.j = r3     // Catch:{ Throwable -> 0x00bb }
            monitor-exit(r10)
            return
        L_0x00bb:
            java.lang.String r11 = "[Native] Failed to load Bugly SO file."
            java.lang.Object[] r0 = new java.lang.Object[r1]     // Catch:{ all -> 0x0193 }
            com.tencent.bugly.proguard.x.c(r11, r0)     // Catch:{ all -> 0x0193 }
            goto L_0x018d
        L_0x00c4:
            boolean r0 = r10.h     // Catch:{ all -> 0x0193 }
            if (r0 == 0) goto L_0x018d
            java.lang.String r0 = "com.tencent.feedback.eup.jni.NativeExceptionUpload"
            java.lang.String r4 = "registNativeExceptionHandler2"
            r5 = 4
            java.lang.Class[] r6 = new java.lang.Class[r5]     // Catch:{ Throwable -> 0x018d }
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r1] = r7     // Catch:{ Throwable -> 0x018d }
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r3] = r7     // Catch:{ Throwable -> 0x018d }
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch:{ Throwable -> 0x018d }
            r6[r2] = r7     // Catch:{ Throwable -> 0x018d }
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch:{ Throwable -> 0x018d }
            r8 = 3
            r6[r8] = r7     // Catch:{ Throwable -> 0x018d }
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x018d }
            java.lang.String r7 = r10.f     // Catch:{ Throwable -> 0x018d }
            r5[r1] = r7     // Catch:{ Throwable -> 0x018d }
            java.lang.String r7 = com.tencent.bugly.crashreport.common.info.b.a((boolean) r1)     // Catch:{ Throwable -> 0x018d }
            r5[r3] = r7     // Catch:{ Throwable -> 0x018d }
            r7 = 5
            if (r11 == 0) goto L_0x00f1
            r9 = 1
            goto L_0x00f2
        L_0x00f1:
            r9 = 5
        L_0x00f2:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x018d }
            r5[r2] = r9     // Catch:{ Throwable -> 0x018d }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r3)     // Catch:{ Throwable -> 0x018d }
            r5[r8] = r9     // Catch:{ Throwable -> 0x018d }
            r9 = 0
            java.lang.Object r0 = com.tencent.bugly.proguard.z.a(r0, r4, r9, r6, r5)     // Catch:{ Throwable -> 0x018d }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x018d }
            if (r0 != 0) goto L_0x0138
            java.lang.String r0 = "com.tencent.feedback.eup.jni.NativeExceptionUpload"
            java.lang.String r4 = "registNativeExceptionHandler"
            java.lang.Class[] r5 = new java.lang.Class[r8]     // Catch:{ Throwable -> 0x018d }
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r5[r1] = r6     // Catch:{ Throwable -> 0x018d }
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r5[r3] = r6     // Catch:{ Throwable -> 0x018d }
            java.lang.Class r6 = java.lang.Integer.TYPE     // Catch:{ Throwable -> 0x018d }
            r5[r2] = r6     // Catch:{ Throwable -> 0x018d }
            java.lang.Object[] r6 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x018d }
            java.lang.String r8 = r10.f     // Catch:{ Throwable -> 0x018d }
            r6[r1] = r8     // Catch:{ Throwable -> 0x018d }
            java.lang.String r8 = com.tencent.bugly.crashreport.common.info.b.a((boolean) r1)     // Catch:{ Throwable -> 0x018d }
            r6[r3] = r8     // Catch:{ Throwable -> 0x018d }
            com.tencent.bugly.crashreport.common.info.a.b()     // Catch:{ Throwable -> 0x018d }
            int r8 = com.tencent.bugly.crashreport.common.info.a.J()     // Catch:{ Throwable -> 0x018d }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Throwable -> 0x018d }
            r6[r2] = r8     // Catch:{ Throwable -> 0x018d }
            java.lang.Object r0 = com.tencent.bugly.proguard.z.a(r0, r4, r9, r5, r6)     // Catch:{ Throwable -> 0x018d }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x018d }
        L_0x0138:
            if (r0 == 0) goto L_0x018d
            r10.j = r3     // Catch:{ Throwable -> 0x018d }
            com.tencent.bugly.crashreport.common.info.a r2 = com.tencent.bugly.crashreport.common.info.a.b()     // Catch:{ Throwable -> 0x018d }
            r2.n = r0     // Catch:{ Throwable -> 0x018d }
            java.lang.String r2 = "com.tencent.feedback.eup.jni.NativeExceptionUpload"
            java.lang.String r4 = "checkExtraJni"
            java.lang.Class[] r5 = new java.lang.Class[r3]     // Catch:{ Throwable -> 0x018d }
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r5[r1] = r6     // Catch:{ Throwable -> 0x018d }
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x018d }
            r6[r1] = r0     // Catch:{ Throwable -> 0x018d }
            java.lang.Object r0 = com.tencent.bugly.proguard.z.a(r2, r4, r9, r5, r6)     // Catch:{ Throwable -> 0x018d }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ Throwable -> 0x018d }
            if (r0 == 0) goto L_0x015e
            boolean r0 = r0.booleanValue()     // Catch:{ Throwable -> 0x018d }
            l = r0     // Catch:{ Throwable -> 0x018d }
        L_0x015e:
            java.lang.String r0 = "com.tencent.feedback.eup.jni.NativeExceptionUpload"
            java.lang.String r2 = "enableHandler"
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch:{ Throwable -> 0x018d }
            java.lang.Class r5 = java.lang.Boolean.TYPE     // Catch:{ Throwable -> 0x018d }
            r4[r1] = r5     // Catch:{ Throwable -> 0x018d }
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x018d }
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r3)     // Catch:{ Throwable -> 0x018d }
            r5[r1] = r6     // Catch:{ Throwable -> 0x018d }
            com.tencent.bugly.proguard.z.a(r0, r2, r9, r4, r5)     // Catch:{ Throwable -> 0x018d }
            if (r11 == 0) goto L_0x0176
            r7 = 1
        L_0x0176:
            java.lang.String r11 = "com.tencent.feedback.eup.jni.NativeExceptionUpload"
            java.lang.String r0 = "setLogMode"
            java.lang.Class[] r2 = new java.lang.Class[r3]     // Catch:{ Throwable -> 0x018d }
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ Throwable -> 0x018d }
            r2[r1] = r4     // Catch:{ Throwable -> 0x018d }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x018d }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r7)     // Catch:{ Throwable -> 0x018d }
            r3[r1] = r4     // Catch:{ Throwable -> 0x018d }
            com.tencent.bugly.proguard.z.a(r11, r0, r9, r2, r3)     // Catch:{ Throwable -> 0x018d }
            monitor-exit(r10)
            return
        L_0x018d:
            r10.i = r1     // Catch:{ all -> 0x0193 }
            r10.h = r1     // Catch:{ all -> 0x0193 }
            monitor-exit(r10)
            return
        L_0x0193:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.a(boolean):void");
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:17:0x0033=Splitter:B:17:0x0033, B:21:0x0044=Splitter:B:21:0x0044} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void startNativeMonitor() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.i     // Catch:{ all -> 0x004b }
            if (r0 != 0) goto L_0x0044
            boolean r0 = r3.h     // Catch:{ all -> 0x004b }
            if (r0 == 0) goto L_0x000a
            goto L_0x0044
        L_0x000a:
            java.lang.String r0 = "Bugly"
            com.tencent.bugly.crashreport.common.info.a r1 = r3.c     // Catch:{ all -> 0x004b }
            java.lang.String r1 = r1.m     // Catch:{ all -> 0x004b }
            boolean r1 = com.tencent.bugly.proguard.z.a((java.lang.String) r1)     // Catch:{ all -> 0x004b }
            r1 = r1 ^ 1
            com.tencent.bugly.crashreport.common.info.a r2 = r3.c     // Catch:{ all -> 0x004b }
            java.lang.String r2 = r2.m     // Catch:{ all -> 0x004b }
            if (r1 != 0) goto L_0x0022
            com.tencent.bugly.crashreport.common.info.a r2 = r3.c     // Catch:{ all -> 0x004b }
            r2.getClass()     // Catch:{ all -> 0x004b }
            goto L_0x0023
        L_0x0022:
            r0 = r2
        L_0x0023:
            boolean r0 = a((java.lang.String) r0, (boolean) r1)     // Catch:{ all -> 0x004b }
            r3.i = r0     // Catch:{ all -> 0x004b }
            boolean r0 = r3.i     // Catch:{ all -> 0x004b }
            if (r0 != 0) goto L_0x0033
            boolean r0 = r3.h     // Catch:{ all -> 0x004b }
            if (r0 != 0) goto L_0x0033
            monitor-exit(r3)
            return
        L_0x0033:
            boolean r0 = r3.g     // Catch:{ all -> 0x004b }
            r3.a((boolean) r0)     // Catch:{ all -> 0x004b }
            com.tencent.bugly.proguard.w r0 = r3.d     // Catch:{ all -> 0x004b }
            com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler$1 r1 = new com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler$1     // Catch:{ all -> 0x004b }
            r1.<init>()     // Catch:{ all -> 0x004b }
            r0.a(r1)     // Catch:{ all -> 0x004b }
            monitor-exit(r3)
            return
        L_0x0044:
            boolean r0 = r3.g     // Catch:{ all -> 0x004b }
            r3.a((boolean) r0)     // Catch:{ all -> 0x004b }
            monitor-exit(r3)
            return
        L_0x004b:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.startNativeMonitor():void");
    }

    private static boolean a(String str, boolean z) {
        boolean z2;
        try {
            x.a("[Native] Trying to load so: %s", str);
            if (z) {
                System.load(str);
            } else {
                System.loadLibrary(str);
            }
            try {
                x.a("[Native] Successfully loaded SO: %s", str);
                return true;
            } catch (Throwable th) {
                th = th;
                z2 = true;
            }
        } catch (Throwable th2) {
            th = th2;
            z2 = false;
            x.d(th.getMessage(), new Object[0]);
            x.d("[Native] Failed to load so: %s", str);
            return z2;
        }
    }

    private synchronized void b() {
        if (!this.j) {
            x.d("[Native] Native crash report has already unregistered.", new Object[0]);
            return;
        }
        try {
            if (unregist() != null) {
                x.a("[Native] Successfully closed native crash report.", new Object[0]);
                this.j = false;
                return;
            }
        } catch (Throwable unused) {
            x.c("[Native] Failed to close native crash report.", new Object[0]);
        }
        try {
            z.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", (Object) null, new Class[]{Boolean.TYPE}, new Object[]{false});
            this.j = false;
            x.a("[Native] Successfully closed native crash report.", new Object[0]);
            return;
        } catch (Throwable unused2) {
            x.c("[Native] Failed to close native crash report.", new Object[0]);
            this.i = false;
            this.h = false;
            return;
        }
    }

    public void testNativeCrash() {
        if (!this.i) {
            x.d("[Native] Bugly SO file has not been load.", new Object[0]);
        } else {
            testCrash();
        }
    }

    public NativeExceptionHandler getNativeExceptionHandler() {
        return this.e;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        File[] listFiles;
        long b2 = z.b() - c.g;
        File file = new File(this.f);
        if (file.exists() && file.isDirectory() && (listFiles = file.listFiles()) != null && listFiles.length != 0) {
            int length = "tomb_".length();
            int i2 = 0;
            for (File file2 : listFiles) {
                String name = file2.getName();
                if (name.startsWith("tomb_")) {
                    try {
                        int indexOf = name.indexOf(Constants.n);
                        if (indexOf > 0 && Long.parseLong(name.substring(length, indexOf)) >= b2) {
                        }
                    } catch (Throwable unused) {
                        x.e("[Native] Tomb file format error, delete %s", name);
                    }
                    if (file2.delete()) {
                        i2++;
                    }
                }
            }
            x.c("[Native] Clean tombs %d", Integer.valueOf(i2));
        }
    }

    private synchronized void b(boolean z) {
        if (z) {
            startNativeMonitor();
        } else {
            b();
        }
    }

    public synchronized boolean isUserOpened() {
        return this.k;
    }

    private synchronized void c(boolean z) {
        if (this.k != z) {
            x.a("user change native %b", Boolean.valueOf(z));
            this.k = z;
        }
    }

    public synchronized void setUserOpened(boolean z) {
        c(z);
        boolean isUserOpened = isUserOpened();
        com.tencent.bugly.crashreport.common.strategy.a a2 = com.tencent.bugly.crashreport.common.strategy.a.a();
        if (a2 != null) {
            isUserOpened = isUserOpened && a2.c().g;
        }
        if (isUserOpened != this.j) {
            x.a("native changed to %b", Boolean.valueOf(isUserOpened));
            b(isUserOpened);
        }
    }

    public synchronized void onStrategyChanged(StrategyBean strategyBean) {
        if (strategyBean != null) {
            try {
                if (strategyBean.g != this.j) {
                    x.d("server native changed to %b", Boolean.valueOf(strategyBean.g));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        boolean z = com.tencent.bugly.crashreport.common.strategy.a.a().c().g && this.k;
        if (z != this.j) {
            x.a("native changed to %b", Boolean.valueOf(z));
            b(z);
        }
    }

    public boolean appendLogToNative(String str, String str2, String str3) {
        if ((!this.h && !this.i) || !l || str == null || str2 == null || str3 == null) {
            return false;
        }
        try {
            if (this.i) {
                return appendNativeLog(str, str2, str3);
            }
            Boolean bool = (Boolean) z.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "appendNativeLog", (Object) null, new Class[]{String.class, String.class, String.class}, new Object[]{str, str2, str3});
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        } catch (UnsatisfiedLinkError unused) {
            l = false;
            return false;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return false;
        }
    }

    public boolean putKeyValueToNative(String str, String str2) {
        if ((!this.h && !this.i) || !l || str == null || str2 == null) {
            return false;
        }
        try {
            if (this.i) {
                return putNativeKeyValue(str, str2);
            }
            Boolean bool = (Boolean) z.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "putNativeKeyValue", (Object) null, new Class[]{String.class, String.class}, new Object[]{str, str2});
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        } catch (UnsatisfiedLinkError unused) {
            l = false;
            return false;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return false;
        }
    }

    private boolean a(int i2, String str) {
        if (!this.i || !m) {
            return false;
        }
        try {
            setNativeInfo(i2, str);
            return true;
        } catch (UnsatisfiedLinkError unused) {
            m = false;
            return false;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return false;
        }
    }

    public boolean filterSigabrtSysLog() {
        return a((int) TbsLog.TBSLOG_CODE_SDK_LOAD_ERROR, "true");
    }

    public boolean setNativeAppVersion(String str) {
        return a(10, str);
    }

    public boolean setNativeAppChannel(String str) {
        return a(12, str);
    }

    public boolean setNativeAppPackage(String str) {
        return a(13, str);
    }

    public boolean setNativeUserId(String str) {
        return a(11, str);
    }

    public boolean setNativeIsAppForeground(boolean z) {
        return a(14, z ? "true" : "false");
    }

    public boolean setNativeLaunchTime(long j2) {
        try {
            return a(15, String.valueOf(j2));
        } catch (NumberFormatException e2) {
            if (x.a(e2)) {
                return false;
            }
            e2.printStackTrace();
            return false;
        }
    }
}
