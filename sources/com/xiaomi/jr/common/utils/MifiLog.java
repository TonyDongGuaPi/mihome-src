package com.xiaomi.jr.common.utils;

import android.content.Context;
import android.os.Process;
import android.support.media.ExifInterface;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import miuipub.reflect.Field;

public class MifiLog {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f1417a = false;
    public static final String b = "app_log";
    private static final int c = 40960;
    private static final int d = 50;
    /* access modifiers changed from: private */
    public static int e = Process.myPid();
    private static volatile boolean f;
    private static volatile boolean g;
    private static FileHandler h;
    private static Logger i;
    private static LinkedBlockingQueue<String> j = new LinkedBlockingQueue<>();
    private static Thread k;
    private static Runnable l = $$Lambda$MifiLog$fVQ9tOTyh66Cqv4HLdQpvBqhUw.INSTANCE;
    private static Formatter m = new Formatter() {

        /* renamed from: a  reason: collision with root package name */
        private Date f1418a = new Date();

        public String format(LogRecord logRecord) {
            this.f1418a.setTime(logRecord.getMillis());
            return String.format(Locale.getDefault(), "%02d-%d %02d:%02d:%02d.%03d %5d %s\n", new Object[]{Integer.valueOf(this.f1418a.getMonth() + 1), Integer.valueOf(this.f1418a.getDate()), Integer.valueOf(this.f1418a.getHours()), Integer.valueOf(this.f1418a.getMinutes()), Integer.valueOf(this.f1418a.getSeconds()), Long.valueOf(logRecord.getMillis() % 1000), Integer.valueOf(MifiLog.e), logRecord.getMessage()});
        }
    };

    /* access modifiers changed from: private */
    public static /* synthetic */ void c() {
        while (g) {
            try {
                i.info(j.take());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void a(Context context) {
        Utils.a();
        if (!g) {
            i = Logger.getLogger(b);
            i.setUseParentHandlers(false);
            try {
                File file = new File(a(context, ""));
                if (!file.exists()) {
                    file.mkdirs();
                }
                FileHandler fileHandler = new FileHandler(a(context, b), 40960, 50);
                fileHandler.setFormatter(m);
                h = fileHandler;
                i.addHandler(h);
                k = new Thread(l);
                k.setDaemon(true);
                k.start();
                g = true;
                f = f1417a;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void a() {
        Utils.a();
        if (g) {
            g = false;
            i.removeHandler(h);
            try {
                h.close();
            } catch (Exception unused) {
            }
            h = null;
            j.offer("stop mifi logging");
            k = null;
            i = null;
        }
    }

    public static void a(String str, String str2) {
        a(str, str2, (Throwable) null);
    }

    public static void a(String str, String str2, Throwable th) {
        a(2, "V", str, str2, th);
    }

    public static void b(String str, String str2) {
        b(str, str2, (Throwable) null);
    }

    public static void b(String str, String str2, Throwable th) {
        a(3, Field.h, str, str2, th);
    }

    public static void c(String str, String str2) {
        c(str, str2, (Throwable) null);
    }

    public static void c(String str, String str2, Throwable th) {
        a(4, Field.e, str, str2, th);
    }

    public static void d(String str, String str2) {
        d(str, str2, (Throwable) null);
    }

    public static void d(String str, String str2, Throwable th) {
        a(5, "W", str, str2, th);
    }

    public static void e(String str, String str2) {
        e(str, str2, (Throwable) null);
    }

    public static void e(String str, String str2, Throwable th) {
        a(6, ExifInterface.LONGITUDE_EAST, str, str2, th);
    }

    private static void a(int i2, String str, String str2, String str3, Throwable th) {
        if (g) {
            if (th != null) {
                str3 = str3 + "\n" + Log.getStackTraceString(th);
            }
            if (f) {
                Log.println(i2, str2, str3);
            }
            j.offer(a(str, str2, str3));
        }
    }

    private static String a(String str, String str2, String str3) {
        return String.format(Locale.getDefault(), "%5d %s %s: %s", new Object[]{Integer.valueOf(Process.myTid()), str, str2, str3});
    }

    public static boolean a(Context context, int i2) {
        int i3;
        ArrayList arrayList = new ArrayList();
        for (int i4 = 0; i4 < 50; i4++) {
            File file = new File(a(context, "app_log." + i4));
            if (file.exists()) {
                arrayList.add(file);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        int i5 = 0;
        while (true) {
            if (!it.hasNext()) {
                i3 = 0;
                break;
            }
            File file2 = (File) it.next();
            arrayList2.add(file2);
            i5 = (int) (((long) i5) + file2.length());
            if (i5 >= i2) {
                i3 = i5 - i2;
                break;
            }
        }
        if (arrayList2.size() > 0) {
            return a((List<File>) arrayList2, new File(a(context, b)), i3);
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r0.write("EXCEPTION OCCURS while merge " + r8.getName());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0092, code lost:
        r6 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0094, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r8.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x0079 */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0053  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(java.util.List<java.io.File> r6, java.io.File r7, int r8) {
        /*
            java.io.BufferedWriter r0 = new java.io.BufferedWriter     // Catch:{ IOException -> 0x00a6 }
            java.io.FileWriter r1 = new java.io.FileWriter     // Catch:{ IOException -> 0x00a6 }
            r1.<init>(r7)     // Catch:{ IOException -> 0x00a6 }
            r0.<init>(r1)     // Catch:{ IOException -> 0x00a6 }
            r7 = 0
            r1 = 1
            if (r8 <= 0) goto L_0x0047
            java.io.RandomAccessFile r2 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            int r3 = r6.size()     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            int r3 = r3 - r1
            java.lang.Object r3 = r6.get(r3)     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            java.io.File r3 = (java.io.File) r3     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            java.lang.String r4 = "rwd"
            r2.<init>(r3, r4)     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            long r3 = (long) r8
            r2.seek(r3)     // Catch:{ Exception -> 0x0034 }
            r2.readLine()     // Catch:{ Exception -> 0x0034 }
        L_0x0027:
            java.lang.String r3 = r2.readLine()     // Catch:{ Exception -> 0x0034 }
            if (r3 == 0) goto L_0x003d
            r0.write(r3)     // Catch:{ Exception -> 0x0034 }
            r0.newLine()     // Catch:{ Exception -> 0x0034 }
            goto L_0x0027
        L_0x0034:
            r3 = move-exception
            goto L_0x003a
        L_0x0036:
            r6 = move-exception
            goto L_0x0043
        L_0x0038:
            r3 = move-exception
            r2 = r7
        L_0x003a:
            r3.printStackTrace()     // Catch:{ all -> 0x0041 }
        L_0x003d:
            com.xiaomi.jr.common.utils.Utils.a((java.io.Closeable) r2)
            goto L_0x0047
        L_0x0041:
            r6 = move-exception
            r7 = r2
        L_0x0043:
            com.xiaomi.jr.common.utils.Utils.a((java.io.Closeable) r7)
            throw r6
        L_0x0047:
            int r2 = r6.size()
            if (r8 <= 0) goto L_0x004f
            r8 = 2
            goto L_0x0050
        L_0x004f:
            r8 = 1
        L_0x0050:
            int r2 = r2 - r8
        L_0x0051:
            if (r2 < 0) goto L_0x00a2
            java.lang.Object r8 = r6.get(r2)
            java.io.File r8 = (java.io.File) r8
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0078, all -> 0x0075 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0078, all -> 0x0075 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0078, all -> 0x0075 }
            r5.<init>(r8)     // Catch:{ IOException -> 0x0078, all -> 0x0075 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0078, all -> 0x0075 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x0078, all -> 0x0075 }
        L_0x0068:
            java.lang.String r4 = r3.readLine()     // Catch:{ IOException -> 0x0079 }
            if (r4 == 0) goto L_0x0098
            r0.write(r4)     // Catch:{ IOException -> 0x0079 }
            r0.newLine()     // Catch:{ IOException -> 0x0079 }
            goto L_0x0068
        L_0x0075:
            r6 = move-exception
            r3 = r7
            goto L_0x009e
        L_0x0078:
            r3 = r7
        L_0x0079:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0094 }
            r4.<init>()     // Catch:{ IOException -> 0x0094 }
            java.lang.String r5 = "EXCEPTION OCCURS while merge "
            r4.append(r5)     // Catch:{ IOException -> 0x0094 }
            java.lang.String r8 = r8.getName()     // Catch:{ IOException -> 0x0094 }
            r4.append(r8)     // Catch:{ IOException -> 0x0094 }
            java.lang.String r8 = r4.toString()     // Catch:{ IOException -> 0x0094 }
            r0.write(r8)     // Catch:{ IOException -> 0x0094 }
            goto L_0x0098
        L_0x0092:
            r6 = move-exception
            goto L_0x009e
        L_0x0094:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x0092 }
        L_0x0098:
            com.xiaomi.jr.common.utils.Utils.a((java.io.Closeable) r3)
            int r2 = r2 + -1
            goto L_0x0051
        L_0x009e:
            com.xiaomi.jr.common.utils.Utils.a((java.io.Closeable) r3)
            throw r6
        L_0x00a2:
            com.xiaomi.jr.common.utils.Utils.a((java.io.Closeable) r0)
            return r1
        L_0x00a6:
            r6 = move-exception
            r6.printStackTrace()
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.common.utils.MifiLog.a(java.util.List, java.io.File, int):boolean");
    }

    private static String a(Context context, String str) {
        return FileUtils.a(context, "app_log/" + str);
    }
}
