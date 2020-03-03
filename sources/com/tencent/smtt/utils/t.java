package com.tencent.smtt.utils;

import android.content.Context;
import java.io.File;

public class t {
    private static t e;

    /* renamed from: a  reason: collision with root package name */
    public boolean f9216a = false;
    private Context b = null;
    private File c = null;
    private boolean d = false;
    private File f = null;

    private t(Context context) {
        this.b = context.getApplicationContext();
        b();
    }

    public static synchronized t a() {
        t tVar;
        synchronized (t.class) {
            tVar = e;
        }
        return tVar;
    }

    public static synchronized t a(Context context) {
        t tVar;
        synchronized (t.class) {
            if (e == null) {
                e = new t(context);
            }
            tVar = e;
        }
        return tVar;
    }

    private File d() {
        try {
            if (this.c == null) {
                this.c = new File(this.b.getDir("tbs", 0), "core_private");
                if (this.c == null || !this.c.isDirectory()) {
                    return null;
                }
            }
            File file = new File(this.c, "debug.conf");
            if (!file.exists()) {
                file.createNewFile();
            }
            return file;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void a(boolean z) {
        this.d = z;
        c();
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0053 A[SYNTHETIC, Splitter:B:30:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x005f A[SYNTHETIC, Splitter:B:37:0x005f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void b() {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            java.io.File r1 = r4.f     // Catch:{ Throwable -> 0x004d }
            if (r1 != 0) goto L_0x000c
            java.io.File r1 = r4.d()     // Catch:{ Throwable -> 0x004d }
            r4.f = r1     // Catch:{ Throwable -> 0x004d }
        L_0x000c:
            java.io.File r1 = r4.f     // Catch:{ Throwable -> 0x004d }
            if (r1 != 0) goto L_0x0012
            monitor-exit(r4)
            return
        L_0x0012:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x004d }
            java.io.File r2 = r4.f     // Catch:{ Throwable -> 0x004d }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x004d }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x004d }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x004d }
            java.util.Properties r0 = new java.util.Properties     // Catch:{ Throwable -> 0x0048, all -> 0x0045 }
            r0.<init>()     // Catch:{ Throwable -> 0x0048, all -> 0x0045 }
            r0.load(r2)     // Catch:{ Throwable -> 0x0048, all -> 0x0045 }
            java.lang.String r1 = "setting_forceUseSystemWebview"
            java.lang.String r3 = ""
            java.lang.String r0 = r0.getProperty(r1, r3)     // Catch:{ Throwable -> 0x0048, all -> 0x0045 }
            java.lang.String r1 = ""
            boolean r1 = r1.equals(r0)     // Catch:{ Throwable -> 0x0048, all -> 0x0045 }
            if (r1 != 0) goto L_0x003c
            boolean r0 = java.lang.Boolean.parseBoolean(r0)     // Catch:{ Throwable -> 0x0048, all -> 0x0045 }
            r4.f9216a = r0     // Catch:{ Throwable -> 0x0048, all -> 0x0045 }
        L_0x003c:
            r2.close()     // Catch:{ Exception -> 0x0040 }
            goto L_0x005b
        L_0x0040:
            r0 = move-exception
        L_0x0041:
            r0.printStackTrace()     // Catch:{ all -> 0x0057 }
            goto L_0x005b
        L_0x0045:
            r1 = move-exception
            r0 = r2
            goto L_0x005d
        L_0x0048:
            r1 = move-exception
            r0 = r2
            goto L_0x004e
        L_0x004b:
            r1 = move-exception
            goto L_0x005d
        L_0x004d:
            r1 = move-exception
        L_0x004e:
            r1.printStackTrace()     // Catch:{ all -> 0x004b }
            if (r0 == 0) goto L_0x005b
            r0.close()     // Catch:{ Exception -> 0x0059 }
            goto L_0x005b
        L_0x0057:
            r0 = move-exception
            goto L_0x0068
        L_0x0059:
            r0 = move-exception
            goto L_0x0041
        L_0x005b:
            monitor-exit(r4)
            return
        L_0x005d:
            if (r0 == 0) goto L_0x0067
            r0.close()     // Catch:{ Exception -> 0x0063 }
            goto L_0x0067
        L_0x0063:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0057 }
        L_0x0067:
            throw r1     // Catch:{ all -> 0x0057 }
        L_0x0068:
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.t.b():void");
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.io.BufferedInputStream, java.lang.String, java.io.BufferedOutputStream] */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.io.BufferedOutputStream] */
    /* JADX WARNING: type inference failed for: r0v4, types: [java.io.BufferedInputStream] */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: type inference failed for: r0v7 */
    /* JADX WARNING: type inference failed for: r0v10 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:22:0x0055=Splitter:B:22:0x0055, B:42:0x0078=Splitter:B:42:0x0078} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c() {
        /*
            r7 = this;
            r0 = 0
            java.io.File r1 = r7.d()     // Catch:{ Throwable -> 0x006b, all -> 0x0068 }
            if (r1 != 0) goto L_0x0018
            r0.close()     // Catch:{ Exception -> 0x000b }
            goto L_0x000f
        L_0x000b:
            r1 = move-exception
            r1.printStackTrace()
        L_0x000f:
            r0.close()     // Catch:{ Exception -> 0x0013 }
            goto L_0x0017
        L_0x0013:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0017:
            return
        L_0x0018:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x006b, all -> 0x0068 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x006b, all -> 0x0068 }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x006b, all -> 0x0068 }
            r3.<init>(r2)     // Catch:{ Throwable -> 0x006b, all -> 0x0068 }
            java.util.Properties r2 = new java.util.Properties     // Catch:{ Throwable -> 0x0064, all -> 0x0062 }
            r2.<init>()     // Catch:{ Throwable -> 0x0064, all -> 0x0062 }
            r2.load(r3)     // Catch:{ Throwable -> 0x0064, all -> 0x0062 }
            java.lang.String r4 = "setting_forceUseSystemWebview"
            boolean r5 = r7.f9216a     // Catch:{ Throwable -> 0x0064, all -> 0x0062 }
            java.lang.String r5 = java.lang.Boolean.toString(r5)     // Catch:{ Throwable -> 0x0064, all -> 0x0062 }
            r2.setProperty(r4, r5)     // Catch:{ Throwable -> 0x0064, all -> 0x0062 }
            java.lang.String r4 = "result_systemWebviewForceUsed"
            boolean r5 = r7.d     // Catch:{ Throwable -> 0x0064, all -> 0x0062 }
            java.lang.String r5 = java.lang.Boolean.toString(r5)     // Catch:{ Throwable -> 0x0064, all -> 0x0062 }
            r2.setProperty(r4, r5)     // Catch:{ Throwable -> 0x0064, all -> 0x0062 }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x0064, all -> 0x0062 }
            r4.<init>(r1)     // Catch:{ Throwable -> 0x0064, all -> 0x0062 }
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch:{ Throwable -> 0x0064, all -> 0x0062 }
            r1.<init>(r4)     // Catch:{ Throwable -> 0x0064, all -> 0x0062 }
            r2.store(r1, r0)     // Catch:{ Throwable -> 0x005e, all -> 0x0059 }
            r3.close()     // Catch:{ Exception -> 0x0051 }
            goto L_0x0055
        L_0x0051:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0055:
            r1.close()     // Catch:{ Exception -> 0x007c }
            goto L_0x0080
        L_0x0059:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x0084
        L_0x005e:
            r0 = move-exception
            r2 = r1
            r1 = r0
            goto L_0x0066
        L_0x0062:
            r1 = move-exception
            goto L_0x0084
        L_0x0064:
            r1 = move-exception
            r2 = r0
        L_0x0066:
            r0 = r3
            goto L_0x006d
        L_0x0068:
            r1 = move-exception
            r3 = r0
            goto L_0x0084
        L_0x006b:
            r1 = move-exception
            r2 = r0
        L_0x006d:
            r1.printStackTrace()     // Catch:{ all -> 0x0081 }
            r0.close()     // Catch:{ Exception -> 0x0074 }
            goto L_0x0078
        L_0x0074:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0078:
            r2.close()     // Catch:{ Exception -> 0x007c }
            goto L_0x0080
        L_0x007c:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0080:
            return
        L_0x0081:
            r1 = move-exception
            r3 = r0
            r0 = r2
        L_0x0084:
            r3.close()     // Catch:{ Exception -> 0x0088 }
            goto L_0x008c
        L_0x0088:
            r2 = move-exception
            r2.printStackTrace()
        L_0x008c:
            r0.close()     // Catch:{ Exception -> 0x0090 }
            goto L_0x0094
        L_0x0090:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0094:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.t.c():void");
    }
}
