package com.tencent.smtt.sdk;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

class ai {

    /* renamed from: a  reason: collision with root package name */
    private static ai f9139a;
    private static Context b;

    private ai() {
    }

    static ai a(Context context) {
        if (f9139a == null) {
            synchronized (ai.class) {
                if (f9139a == null) {
                    f9139a = new ai();
                }
            }
        }
        b = context.getApplicationContext();
        return f9139a;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x003b A[SYNTHETIC, Splitter:B:26:0x003b] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0046 A[SYNTHETIC, Splitter:B:32:0x0046] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Properties e() {
        /*
            r5 = this;
            r0 = 0
            java.io.File r1 = r5.a()     // Catch:{ Exception -> 0x0034 }
            java.util.Properties r2 = new java.util.Properties     // Catch:{ Exception -> 0x0034 }
            r2.<init>()     // Catch:{ Exception -> 0x0034 }
            if (r1 == 0) goto L_0x0027
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0025 }
            r3.<init>(r1)     // Catch:{ Exception -> 0x0025 }
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0025 }
            r1.<init>(r3)     // Catch:{ Exception -> 0x0025 }
            r2.load(r1)     // Catch:{ Exception -> 0x0020, all -> 0x001b }
            r0 = r1
            goto L_0x0027
        L_0x001b:
            r0 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x0044
        L_0x0020:
            r0 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x0036
        L_0x0025:
            r1 = move-exception
            goto L_0x0036
        L_0x0027:
            if (r0 == 0) goto L_0x0031
            r0.close()     // Catch:{ IOException -> 0x002d }
            goto L_0x0031
        L_0x002d:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0031:
            return r2
        L_0x0032:
            r1 = move-exception
            goto L_0x0044
        L_0x0034:
            r1 = move-exception
            r2 = r0
        L_0x0036:
            r1.printStackTrace()     // Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x0043
            r0.close()     // Catch:{ IOException -> 0x003f }
            goto L_0x0043
        L_0x003f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0043:
            return r2
        L_0x0044:
            if (r0 == 0) goto L_0x004e
            r0.close()     // Catch:{ IOException -> 0x004a }
            goto L_0x004e
        L_0x004a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x004e:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.ai.e():java.util.Properties");
    }

    /* access modifiers changed from: package-private */
    public File a() {
        am.a();
        File file = new File(am.s(b), "tbscoreinstall.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return file;
    }

    /* access modifiers changed from: package-private */
    public void a(int i) {
        a("dexopt_retry_num", i);
    }

    /* access modifiers changed from: package-private */
    public void a(int i, int i2) {
        a("copy_core_ver", i);
        a("copy_status", i2);
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        a("install_apk_path", str);
    }

    /* access modifiers changed from: package-private */
    public void a(String str, int i) {
        a(str, String.valueOf(i));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0049 A[SYNTHETIC, Splitter:B:24:0x0049] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004f A[SYNTHETIC, Splitter:B:27:0x004f] */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            r0 = 0
            java.util.Properties r1 = r3.e()     // Catch:{ Exception -> 0x0043 }
            if (r1 == 0) goto L_0x0035
            r1.setProperty(r4, r5)     // Catch:{ Exception -> 0x0043 }
            java.io.File r5 = r3.a()     // Catch:{ Exception -> 0x0043 }
            if (r5 == 0) goto L_0x0035
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0043 }
            r2.<init>(r5)     // Catch:{ Exception -> 0x0043 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0032, all -> 0x0030 }
            r5.<init>()     // Catch:{ Exception -> 0x0032, all -> 0x0030 }
            java.lang.String r0 = "update "
            r5.append(r0)     // Catch:{ Exception -> 0x0032, all -> 0x0030 }
            r5.append(r4)     // Catch:{ Exception -> 0x0032, all -> 0x0030 }
            java.lang.String r4 = " and status!"
            r5.append(r4)     // Catch:{ Exception -> 0x0032, all -> 0x0030 }
            java.lang.String r4 = r5.toString()     // Catch:{ Exception -> 0x0032, all -> 0x0030 }
            r1.store(r2, r4)     // Catch:{ Exception -> 0x0032, all -> 0x0030 }
            r0 = r2
            goto L_0x0035
        L_0x0030:
            r4 = move-exception
            goto L_0x004d
        L_0x0032:
            r4 = move-exception
            r0 = r2
            goto L_0x0044
        L_0x0035:
            if (r0 == 0) goto L_0x004c
            r0.close()     // Catch:{ IOException -> 0x003b }
            goto L_0x004c
        L_0x003b:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x004c
        L_0x0040:
            r4 = move-exception
            r2 = r0
            goto L_0x004d
        L_0x0043:
            r4 = move-exception
        L_0x0044:
            r4.printStackTrace()     // Catch:{ all -> 0x0040 }
            if (r0 == 0) goto L_0x004c
            r0.close()     // Catch:{ IOException -> 0x003b }
        L_0x004c:
            return
        L_0x004d:
            if (r2 == 0) goto L_0x0057
            r2.close()     // Catch:{ IOException -> 0x0053 }
            goto L_0x0057
        L_0x0053:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0057:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.ai.a(java.lang.String, java.lang.String):void");
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return c("install_core_ver");
    }

    /* access modifiers changed from: package-private */
    public int b(String str) {
        Properties e = e();
        if (e == null || e.getProperty(str) == null) {
            return -1;
        }
        return Integer.parseInt(e.getProperty(str));
    }

    /* access modifiers changed from: package-private */
    public void b(int i) {
        a("unzip_retry_num", i);
    }

    /* access modifiers changed from: package-private */
    public void b(int i, int i2) {
        a("install_core_ver", i);
        a("install_status", i2);
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return b("install_status");
    }

    /* access modifiers changed from: package-private */
    public int c(String str) {
        Properties e = e();
        if (e == null || e.getProperty(str) == null) {
            return 0;
        }
        return Integer.parseInt(e.getProperty(str));
    }

    /* access modifiers changed from: package-private */
    public void c(int i) {
        a("incrupdate_status", i);
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return b("incrupdate_status");
    }

    /* access modifiers changed from: package-private */
    public String d(String str) {
        Properties e = e();
        if (e == null || e.getProperty(str) == null) {
            return null;
        }
        return e.getProperty(str);
    }

    /* access modifiers changed from: package-private */
    public void d(int i) {
        a("unlzma_status", i);
    }
}
