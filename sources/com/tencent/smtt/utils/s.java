package com.tencent.smtt.utils;

import android.annotation.TargetApi;
import android.content.Context;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

public class s {
    private static s c;

    /* renamed from: a  reason: collision with root package name */
    private Context f9215a = null;
    private File b = null;
    private String d = "http://log.tbs.qq.com/ajax?c=pu&v=2&k=";
    private String e = "http://log.tbs.qq.com/ajax?c=pu&tk=";
    private String f = "http://wup.imtt.qq.com:8080";
    private String g = "http://log.tbs.qq.com/ajax?c=dl&k=";
    private String h = "http://cfg.imtt.qq.com/tbs?v=2&mk=";
    private String i = "http://log.tbs.qq.com/ajax?c=ul&v=2&k=";
    private String j = "http://mqqad.html5.qq.com/adjs";
    private String k = "http://log.tbs.qq.com/ajax?c=ucfu&k=";

    @TargetApi(11)
    private s(Context context) {
        TbsLog.w("TbsCommonConfig", "TbsCommonConfig constructing...");
        this.f9215a = context.getApplicationContext();
        g();
    }

    public static synchronized s a() {
        s sVar;
        synchronized (s.class) {
            sVar = c;
        }
        return sVar;
    }

    public static synchronized s a(Context context) {
        s sVar;
        synchronized (s.class) {
            if (c == null) {
                c = new s(context);
            }
            sVar = c;
        }
        return sVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f0 A[SYNTHETIC, Splitter:B:48:0x00f0] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00fb A[SYNTHETIC, Splitter:B:55:0x00fb] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void g() {
        /*
            r6 = this;
            monitor-enter(r6)
            r0 = 0
            java.io.File r1 = r6.h()     // Catch:{ Throwable -> 0x00c3, all -> 0x00be }
            if (r1 != 0) goto L_0x0011
            java.lang.String r1 = "TbsCommonConfig"
            java.lang.String r2 = "Config file is null, default values will be applied"
            com.tencent.smtt.utils.TbsLog.e(r1, r2)     // Catch:{ Throwable -> 0x00c3, all -> 0x00be }
            monitor-exit(r6)
            return
        L_0x0011:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x00c3, all -> 0x00be }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x00c3, all -> 0x00be }
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x00c3, all -> 0x00be }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x00c3, all -> 0x00be }
            java.util.Properties r0 = new java.util.Properties     // Catch:{ Throwable -> 0x00bc }
            r0.<init>()     // Catch:{ Throwable -> 0x00bc }
            r0.load(r1)     // Catch:{ Throwable -> 0x00bc }
            java.lang.String r2 = "pv_post_url"
            java.lang.String r3 = ""
            java.lang.String r2 = r0.getProperty(r2, r3)     // Catch:{ Throwable -> 0x00bc }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ Throwable -> 0x00bc }
            if (r3 != 0) goto L_0x0035
            r6.d = r2     // Catch:{ Throwable -> 0x00bc }
        L_0x0035:
            java.lang.String r2 = "wup_proxy_domain"
            java.lang.String r3 = ""
            java.lang.String r2 = r0.getProperty(r2, r3)     // Catch:{ Throwable -> 0x00bc }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ Throwable -> 0x00bc }
            if (r3 != 0) goto L_0x0047
            r6.f = r2     // Catch:{ Throwable -> 0x00bc }
        L_0x0047:
            java.lang.String r2 = "tbs_download_stat_post_url"
            java.lang.String r3 = ""
            java.lang.String r2 = r0.getProperty(r2, r3)     // Catch:{ Throwable -> 0x00bc }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ Throwable -> 0x00bc }
            if (r3 != 0) goto L_0x0059
            r6.g = r2     // Catch:{ Throwable -> 0x00bc }
        L_0x0059:
            java.lang.String r2 = "tbs_downloader_post_url"
            java.lang.String r3 = ""
            java.lang.String r2 = r0.getProperty(r2, r3)     // Catch:{ Throwable -> 0x00bc }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ Throwable -> 0x00bc }
            if (r3 != 0) goto L_0x006b
            r6.h = r2     // Catch:{ Throwable -> 0x00bc }
        L_0x006b:
            java.lang.String r2 = "tbs_log_post_url"
            java.lang.String r3 = ""
            java.lang.String r2 = r0.getProperty(r2, r3)     // Catch:{ Throwable -> 0x00bc }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ Throwable -> 0x00bc }
            if (r3 != 0) goto L_0x007d
            r6.i = r2     // Catch:{ Throwable -> 0x00bc }
        L_0x007d:
            java.lang.String r2 = "tips_url"
            java.lang.String r3 = ""
            java.lang.String r2 = r0.getProperty(r2, r3)     // Catch:{ Throwable -> 0x00bc }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ Throwable -> 0x00bc }
            if (r3 != 0) goto L_0x008f
            r6.j = r2     // Catch:{ Throwable -> 0x00bc }
        L_0x008f:
            java.lang.String r2 = "tbs_cmd_post_url"
            java.lang.String r3 = ""
            java.lang.String r2 = r0.getProperty(r2, r3)     // Catch:{ Throwable -> 0x00bc }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ Throwable -> 0x00bc }
            if (r3 != 0) goto L_0x00a1
            r6.k = r2     // Catch:{ Throwable -> 0x00bc }
        L_0x00a1:
            java.lang.String r2 = "pv_post_url_tk"
            java.lang.String r3 = ""
            java.lang.String r0 = r0.getProperty(r2, r3)     // Catch:{ Throwable -> 0x00bc }
            java.lang.String r2 = ""
            boolean r2 = r2.equals(r0)     // Catch:{ Throwable -> 0x00bc }
            if (r2 != 0) goto L_0x00b3
            r6.e = r0     // Catch:{ Throwable -> 0x00bc }
        L_0x00b3:
            r1.close()     // Catch:{ IOException -> 0x00b7 }
            goto L_0x00f6
        L_0x00b7:
            r0 = move-exception
        L_0x00b8:
            r0.printStackTrace()     // Catch:{ all -> 0x00ff }
            goto L_0x00f6
        L_0x00bc:
            r0 = move-exception
            goto L_0x00c7
        L_0x00be:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x00f9
        L_0x00c3:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L_0x00c7:
            java.io.StringWriter r2 = new java.io.StringWriter     // Catch:{ all -> 0x00f8 }
            r2.<init>()     // Catch:{ all -> 0x00f8 }
            java.io.PrintWriter r3 = new java.io.PrintWriter     // Catch:{ all -> 0x00f8 }
            r3.<init>(r2)     // Catch:{ all -> 0x00f8 }
            r0.printStackTrace(r3)     // Catch:{ all -> 0x00f8 }
            java.lang.String r0 = "TbsCommonConfig"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f8 }
            r3.<init>()     // Catch:{ all -> 0x00f8 }
            java.lang.String r4 = "exceptions occurred1:"
            r3.append(r4)     // Catch:{ all -> 0x00f8 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00f8 }
            r3.append(r2)     // Catch:{ all -> 0x00f8 }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x00f8 }
            com.tencent.smtt.utils.TbsLog.e(r0, r2)     // Catch:{ all -> 0x00f8 }
            if (r1 == 0) goto L_0x00f6
            r1.close()     // Catch:{ IOException -> 0x00f4 }
            goto L_0x00f6
        L_0x00f4:
            r0 = move-exception
            goto L_0x00b8
        L_0x00f6:
            monitor-exit(r6)
            return
        L_0x00f8:
            r0 = move-exception
        L_0x00f9:
            if (r1 == 0) goto L_0x0105
            r1.close()     // Catch:{ IOException -> 0x0101 }
            goto L_0x0105
        L_0x00ff:
            r0 = move-exception
            goto L_0x0106
        L_0x0101:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x00ff }
        L_0x0105:
            throw r0     // Catch:{ all -> 0x00ff }
        L_0x0106:
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.s.g():void");
    }

    private File h() {
        File file = null;
        try {
            if (this.b == null) {
                this.b = new File(j.a(this.f9215a, 5));
                if (this.b == null || !this.b.isDirectory()) {
                    return null;
                }
            }
            File file2 = new File(this.b, "tbsnet.conf");
            if (!file2.exists()) {
                TbsLog.e("TbsCommonConfig", "Get file(" + file2.getCanonicalPath() + ") failed!");
                return null;
            }
            try {
                TbsLog.w("TbsCommonConfig", "pathc:" + file2.getCanonicalPath());
                return file2;
            } catch (Throwable th) {
                File file3 = file2;
                th = th;
                file = file3;
                StringWriter stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                TbsLog.e("TbsCommonConfig", "exceptions occurred2:" + stringWriter.toString());
                return file;
            }
        } catch (Throwable th2) {
            th = th2;
            StringWriter stringWriter2 = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter2));
            TbsLog.e("TbsCommonConfig", "exceptions occurred2:" + stringWriter2.toString());
            return file;
        }
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.g;
    }

    public String d() {
        return this.h;
    }

    public String e() {
        return this.i;
    }

    public String f() {
        return this.e;
    }
}
