package com.xiaomi.msg.logger;

import android.support.media.ExifInterface;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import miuipub.reflect.Field;

public class MIMCLog {

    /* renamed from: a  reason: collision with root package name */
    public static final int f12112a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    private static Logger e = null;
    private static boolean f = true;
    private static int g = 2;
    private static int h = 2;
    private static final SimpleDateFormat i = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    /* access modifiers changed from: private */
    public static BlockingDeque<Log> j = new LinkedBlockingDeque();
    /* access modifiers changed from: private */
    public static volatile Log2FileThread k = null;
    private static String l = "./files/logs/";
    private static final long m = 15728640;
    /* access modifiers changed from: private */
    public static String n = "mimc.log";
    private static final String o = "mimc0.log";

    public static String c(int i2) {
        switch (i2) {
            case 1:
                return Field.h;
            case 2:
                return Field.e;
            case 3:
                return "W";
            case 4:
                return ExifInterface.LONGITUDE_EAST;
            default:
                return "";
        }
    }

    public static void a(int i2) {
        g = i2;
    }

    public static void b(int i2) {
        h = i2;
    }

    public static void a(Logger logger) {
        e = logger;
    }

    public static void a(String str) {
        l = str;
    }

    public static void a(boolean z) {
        f = z;
    }

    public static void b(String str) {
        n = str;
    }

    public static void a(String str, String str2) {
        a(1, str, str2);
    }

    public static void a(String str, String str2, Throwable th) {
        a(1, str, str2, th);
    }

    public static void b(String str, String str2) {
        a(2, str, str2);
    }

    public static void b(String str, String str2, Throwable th) {
        a(2, str, str2, th);
    }

    public static void c(String str, String str2) {
        a(3, str, str2);
    }

    public static void c(String str, String str2, Throwable th) {
        a(3, str, str2, th);
    }

    public static void d(String str, String str2) {
        a(4, str, str2);
    }

    public static void d(String str, String str2, Throwable th) {
        a(4, str, str2, th);
    }

    private static void a(int i2, String str, String str2) {
        a(i2, str, str2, (Throwable) null);
    }

    private static void a(int i2, String str, String str2, Throwable th) {
        if (e != null && i2 >= g) {
            if (th != null) {
                switch (i2) {
                    case 1:
                        e.a(str, str2, th);
                        break;
                    case 2:
                        e.b(str, str2, th);
                        break;
                    case 3:
                        e.c(str, str2, th);
                        break;
                    case 4:
                        e.d(str, str2, th);
                        break;
                }
            } else {
                switch (i2) {
                    case 1:
                        e.a(str, str2);
                        break;
                    case 2:
                        e.b(str, str2);
                        break;
                    case 3:
                        e.c(str, str2);
                        break;
                    case 4:
                        e.d(str, str2);
                        break;
                }
            }
        }
        if (f && i2 >= h) {
            BlockingDeque<Log> blockingDeque = j;
            blockingDeque.offer(new Log(a(i2, str) + str2, th));
            if (k == null || !k.isAlive()) {
                d();
            }
        }
    }

    private static void d() {
        synchronized (MIMCLog.class) {
            if (k == null || !k.isAlive()) {
                k = new Log2FileThread();
                k.start();
                Runtime.getRuntime().addShutdownHook(new Thread() {
                    public void run() {
                        MIMCLog.k.a();
                        MIMCLog.k.interrupt();
                    }
                });
            }
        }
    }

    public static String a(int i2, String str) {
        return String.format("%1$s %2$s/%3$s: ", new Object[]{i.format(new Date()), c(i2), str});
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0032 A[SYNTHETIC, Splitter:B:21:0x0032] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x003c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.Throwable r3) {
        /*
            if (r3 != 0) goto L_0x0005
            java.lang.String r3 = ""
            return r3
        L_0x0005:
            r0 = 0
            java.io.StringWriter r1 = new java.io.StringWriter     // Catch:{ all -> 0x002e }
            r1.<init>()     // Catch:{ all -> 0x002e }
            java.io.PrintWriter r2 = new java.io.PrintWriter     // Catch:{ all -> 0x002c }
            r2.<init>(r1)     // Catch:{ all -> 0x002c }
            r3.printStackTrace(r2)     // Catch:{ all -> 0x0029 }
            r2.flush()     // Catch:{ all -> 0x0029 }
            r1.flush()     // Catch:{ all -> 0x0029 }
            r1.close()     // Catch:{ IOException -> 0x001d }
            goto L_0x0021
        L_0x001d:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0021:
            r2.close()
            java.lang.String r3 = r1.toString()
            return r3
        L_0x0029:
            r3 = move-exception
            r0 = r2
            goto L_0x0030
        L_0x002c:
            r3 = move-exception
            goto L_0x0030
        L_0x002e:
            r3 = move-exception
            r1 = r0
        L_0x0030:
            if (r1 == 0) goto L_0x003a
            r1.close()     // Catch:{ IOException -> 0x0036 }
            goto L_0x003a
        L_0x0036:
            r1 = move-exception
            r1.printStackTrace()
        L_0x003a:
            if (r0 == 0) goto L_0x003f
            r0.close()
        L_0x003f:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.msg.logger.MIMCLog.a(java.lang.Throwable):java.lang.String");
    }

    public static class Log2FileThread extends Thread {
        private static final int b = 4096;

        /* renamed from: a  reason: collision with root package name */
        private volatile boolean f12113a = false;
        private StringBuilder c = new StringBuilder(4096);

        public void run() {
            while (!this.f12113a) {
                try {
                    Log log = (Log) MIMCLog.j.poll(200, TimeUnit.MILLISECONDS);
                    if (log != null) {
                        if (log.b() != null) {
                            this.c.append(String.format("%s\n%s\n", new Object[]{log.a(), MIMCLog.a(log.b())}));
                        } else {
                            this.c.append(String.format("%s\n", new Object[]{log.a()}));
                        }
                        if (this.c.length() >= 4096) {
                            MIMCLog.f(this.c.toString(), MIMCLog.n);
                            this.c.delete(0, this.c.length());
                        }
                    } else if (this.c.length() > 0) {
                        MIMCLog.f(this.c.toString(), MIMCLog.n);
                        this.c.delete(0, this.c.length());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void a() {
            this.f12113a = true;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x007c A[SYNTHETIC, Splitter:B:36:0x007c] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0087 A[SYNTHETIC, Splitter:B:41:0x0087] */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void f(java.lang.String r8, java.lang.String r9) {
        /*
            r0 = 0
            java.lang.String r1 = l     // Catch:{ Exception -> 0x0076 }
            if (r1 == 0) goto L_0x0073
            java.lang.String r1 = l     // Catch:{ Exception -> 0x0076 }
            boolean r1 = r1.isEmpty()     // Catch:{ Exception -> 0x0076 }
            if (r1 == 0) goto L_0x000e
            goto L_0x0073
        L_0x000e:
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x0076 }
            java.lang.String r2 = l     // Catch:{ Exception -> 0x0076 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0076 }
            boolean r2 = r1.exists()     // Catch:{ Exception -> 0x0076 }
            if (r2 == 0) goto L_0x0021
            boolean r2 = r1.isDirectory()     // Catch:{ Exception -> 0x0076 }
            if (r2 != 0) goto L_0x0028
        L_0x0021:
            boolean r2 = r1.mkdirs()     // Catch:{ Exception -> 0x0076 }
            if (r2 != 0) goto L_0x0028
            return
        L_0x0028:
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0076 }
            r2.<init>(r1, r9)     // Catch:{ Exception -> 0x0076 }
            long r3 = r2.length()     // Catch:{ Exception -> 0x0076 }
            r5 = 15728640(0xf00000, double:7.7709807E-317)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0051
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x0076 }
            java.lang.String r4 = "mimc0.log"
            r3.<init>(r1, r4)     // Catch:{ Exception -> 0x0076 }
            boolean r4 = r3.exists()     // Catch:{ Exception -> 0x0076 }
            if (r4 == 0) goto L_0x004e
            boolean r4 = r3.isFile()     // Catch:{ Exception -> 0x0076 }
            if (r4 == 0) goto L_0x004e
            r3.delete()     // Catch:{ Exception -> 0x0076 }
        L_0x004e:
            r2.renameTo(r3)     // Catch:{ Exception -> 0x0076 }
        L_0x0051:
            java.io.BufferedWriter r2 = new java.io.BufferedWriter     // Catch:{ Exception -> 0x0076 }
            java.io.OutputStreamWriter r3 = new java.io.OutputStreamWriter     // Catch:{ Exception -> 0x0076 }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0076 }
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x0076 }
            r5.<init>(r1, r9)     // Catch:{ Exception -> 0x0076 }
            r9 = 1
            r4.<init>(r5, r9)     // Catch:{ Exception -> 0x0076 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0076 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0076 }
            r2.write(r8)     // Catch:{ Exception -> 0x0070, all -> 0x006d }
            r2.close()     // Catch:{ IOException -> 0x0080 }
            goto L_0x0084
        L_0x006d:
            r8 = move-exception
            r0 = r2
            goto L_0x0085
        L_0x0070:
            r8 = move-exception
            r0 = r2
            goto L_0x0077
        L_0x0073:
            return
        L_0x0074:
            r8 = move-exception
            goto L_0x0085
        L_0x0076:
            r8 = move-exception
        L_0x0077:
            r8.printStackTrace()     // Catch:{ all -> 0x0074 }
            if (r0 == 0) goto L_0x0084
            r0.close()     // Catch:{ IOException -> 0x0080 }
            goto L_0x0084
        L_0x0080:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0084:
            return
        L_0x0085:
            if (r0 == 0) goto L_0x008f
            r0.close()     // Catch:{ IOException -> 0x008b }
            goto L_0x008f
        L_0x008b:
            r9 = move-exception
            r9.printStackTrace()
        L_0x008f:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.msg.logger.MIMCLog.f(java.lang.String, java.lang.String):void");
    }
}
