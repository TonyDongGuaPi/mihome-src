package com.mi.blockcanary;

import android.util.Log;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class LogWriter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6747a = "LogWriter";
    /* access modifiers changed from: private */
    public static final Object b = new Object();
    private static final SimpleDateFormat c = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss.SSS", Locale.US);
    private static final SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
    private static final long e = 172800000;

    private LogWriter() {
        throw new InstantiationError("Must not instantiate this class");
    }

    public static String a(String str) {
        String a2;
        synchronized (b) {
            a2 = a("looper", str);
        }
        return a2;
    }

    public static void a() {
        HandlerThreadFactory.b().post(new Runnable() {
            public void run() {
                long currentTimeMillis = System.currentTimeMillis();
                File[] f = BlockCanaryInternals.f();
                if (f != null && f.length > 0) {
                    synchronized (LogWriter.b) {
                        for (File file : f) {
                            if (currentTimeMillis - file.lastModified() > LogWriter.e) {
                                file.delete();
                            }
                        }
                    }
                }
            }
        });
    }

    public static void b() {
        synchronized (b) {
            try {
                File[] f = BlockCanaryInternals.f();
                if (f != null && f.length > 0) {
                    for (File delete : f) {
                        delete.delete();
                    }
                }
            } catch (Throwable th) {
                Log.e(f6747a, "deleteAll: ", th);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x00a2 A[SYNTHETIC, Splitter:B:20:0x00a2] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00b1 A[SYNTHETIC, Splitter:B:26:0x00b1] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.lang.String r6, java.lang.String r7) {
        /*
            java.lang.String r0 = ""
            r1 = 0
            java.io.File r2 = com.mi.blockcanary.BlockCanaryInternals.e()     // Catch:{ Throwable -> 0x0097 }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0097 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0097 }
            r5.<init>()     // Catch:{ Throwable -> 0x0097 }
            java.lang.String r2 = r2.getAbsolutePath()     // Catch:{ Throwable -> 0x0097 }
            r5.append(r2)     // Catch:{ Throwable -> 0x0097 }
            java.lang.String r2 = "/"
            r5.append(r2)     // Catch:{ Throwable -> 0x0097 }
            r5.append(r6)     // Catch:{ Throwable -> 0x0097 }
            java.lang.String r6 = "-"
            r5.append(r6)     // Catch:{ Throwable -> 0x0097 }
            java.text.SimpleDateFormat r6 = c     // Catch:{ Throwable -> 0x0097 }
            java.lang.Long r2 = java.lang.Long.valueOf(r3)     // Catch:{ Throwable -> 0x0097 }
            java.lang.String r6 = r6.format(r2)     // Catch:{ Throwable -> 0x0097 }
            r5.append(r6)     // Catch:{ Throwable -> 0x0097 }
            java.lang.String r6 = ".log"
            r5.append(r6)     // Catch:{ Throwable -> 0x0097 }
            java.lang.String r6 = r5.toString()     // Catch:{ Throwable -> 0x0097 }
            java.io.OutputStreamWriter r0 = new java.io.OutputStreamWriter     // Catch:{ Throwable -> 0x0093 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x0093 }
            r5 = 1
            r2.<init>(r6, r5)     // Catch:{ Throwable -> 0x0093 }
            java.lang.String r5 = "UTF-8"
            r0.<init>(r2, r5)     // Catch:{ Throwable -> 0x0093 }
            java.io.BufferedWriter r2 = new java.io.BufferedWriter     // Catch:{ Throwable -> 0x0093 }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x0093 }
            java.lang.String r0 = "\r\n"
            r2.write(r0)     // Catch:{ Throwable -> 0x0090, all -> 0x008d }
            java.lang.String r0 = "**********************"
            r2.write(r0)     // Catch:{ Throwable -> 0x0090, all -> 0x008d }
            java.lang.String r0 = "\r\n"
            r2.write(r0)     // Catch:{ Throwable -> 0x0090, all -> 0x008d }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0090, all -> 0x008d }
            r0.<init>()     // Catch:{ Throwable -> 0x0090, all -> 0x008d }
            java.text.SimpleDateFormat r1 = d     // Catch:{ Throwable -> 0x0090, all -> 0x008d }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ Throwable -> 0x0090, all -> 0x008d }
            java.lang.String r1 = r1.format(r3)     // Catch:{ Throwable -> 0x0090, all -> 0x008d }
            r0.append(r1)     // Catch:{ Throwable -> 0x0090, all -> 0x008d }
            java.lang.String r1 = "(write log time)"
            r0.append(r1)     // Catch:{ Throwable -> 0x0090, all -> 0x008d }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x0090, all -> 0x008d }
            r2.write(r0)     // Catch:{ Throwable -> 0x0090, all -> 0x008d }
            java.lang.String r0 = "\r\n"
            r2.write(r0)     // Catch:{ Throwable -> 0x0090, all -> 0x008d }
            r2.write(r7)     // Catch:{ Throwable -> 0x0090, all -> 0x008d }
            java.lang.String r7 = "\r\n"
            r2.write(r7)     // Catch:{ Throwable -> 0x0090, all -> 0x008d }
            r2.flush()     // Catch:{ Throwable -> 0x0090, all -> 0x008d }
            r2.close()     // Catch:{ Throwable -> 0x0090, all -> 0x008d }
            goto L_0x00ae
        L_0x008d:
            r6 = move-exception
            r1 = r2
            goto L_0x00af
        L_0x0090:
            r7 = move-exception
            r1 = r2
            goto L_0x0099
        L_0x0093:
            r7 = move-exception
            goto L_0x0099
        L_0x0095:
            r6 = move-exception
            goto L_0x00af
        L_0x0097:
            r7 = move-exception
            r6 = r0
        L_0x0099:
            java.lang.String r0 = "LogWriter"
            java.lang.String r2 = "save: "
            android.util.Log.e(r0, r2, r7)     // Catch:{ all -> 0x0095 }
            if (r1 == 0) goto L_0x00ae
            r1.close()     // Catch:{ Exception -> 0x00a6 }
            goto L_0x00ae
        L_0x00a6:
            r7 = move-exception
            java.lang.String r0 = "LogWriter"
            java.lang.String r1 = "save: "
            android.util.Log.e(r0, r1, r7)
        L_0x00ae:
            return r6
        L_0x00af:
            if (r1 == 0) goto L_0x00bd
            r1.close()     // Catch:{ Exception -> 0x00b5 }
            goto L_0x00bd
        L_0x00b5:
            r7 = move-exception
            java.lang.String r0 = "LogWriter"
            java.lang.String r1 = "save: "
            android.util.Log.e(r0, r1, r7)
        L_0x00bd:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.blockcanary.LogWriter.a(java.lang.String, java.lang.String):java.lang.String");
    }

    public static File b(String str) {
        return new File(BlockCanaryInternals.d() + "/" + str + ".zip");
    }
}
