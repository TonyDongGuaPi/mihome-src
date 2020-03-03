package cn.com.xm.bt.a;

import android.util.Log;
import com.google.code.microlog4android.format.PatternFormatter;
import com.taobao.weex.el.parse.Operators;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class a {

    /* renamed from: a  reason: collision with root package name */
    public static int f551a = 2;
    public static int b = 2;
    private static int c = 0;
    private static boolean d = true;
    private static boolean e = true;
    private static boolean f = true;
    private static boolean g = true;
    private static File h;

    private static String c() {
        return "";
    }

    public static void a(boolean z) {
        c = z ? 0 : -1;
        d = z;
        e = z;
        f = z;
        g = z;
    }

    public static synchronized void a(File file) {
        synchronized (a.class) {
            h = file;
        }
    }

    public static void a(Object obj) {
        if (f && obj == null) {
            a("DEBUG", ">>> `NOT NULL` ASSERTION FAILED <<<", 0, 'e');
        }
    }

    public static void a(Thread thread) {
        if (f && thread != null && Thread.currentThread().getId() != thread.getId()) {
            a("DEBUG", ">>> `RUN ON THREAD` ASSERTION FAILED <<<", 0, 'e');
        }
    }

    public static void b(boolean z) {
        if (f && !z) {
            a("DEBUG", ">>> `TRUE` ASSERTION FAILED <<<", 0, 'e');
        }
    }

    public static void a(String str) {
        a("DEBUG", str, 0, PatternFormatter.DATE_CONVERSION_CHAR);
    }

    public static void b(String str) {
        if (g) {
            a("DEBUG", "LOCK#" + str, 0, 'v');
        }
    }

    public static void c(String str) {
        a("DEBUG", str, 0, 'e');
    }

    public static void a(String str, String str2) {
        if (c > -1 && c < 1) {
            Log.i(str, c() + str2);
        }
        c(str, str2);
    }

    public static void b(String str, String str2) {
        if (c > -1 && c < 1) {
            Log.i(str, c() + str2);
        }
    }

    public static void d(String str) {
        a("DEBUG", str, 0, PatternFormatter.CLIENT_ID_CONVERSION_CHAR);
    }

    public static void a() {
        if (e) {
            a("DEBUG", "<<<<====", 0, 'v');
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized void c(java.lang.String r4, java.lang.String r5) {
        /*
            java.lang.Class<cn.com.xm.bt.a.a> r0 = cn.com.xm.bt.a.a.class
            monitor-enter(r0)
            java.io.File r1 = h     // Catch:{ all -> 0x0047 }
            if (r1 == 0) goto L_0x0045
            java.io.File r1 = h     // Catch:{ all -> 0x0047 }
            boolean r1 = r1.canWrite()     // Catch:{ all -> 0x0047 }
            if (r1 != 0) goto L_0x0010
            goto L_0x0045
        L_0x0010:
            java.io.FileWriter r1 = new java.io.FileWriter     // Catch:{ IOException -> 0x0043 }
            java.io.File r2 = h     // Catch:{ IOException -> 0x0043 }
            r3 = 1
            r1.<init>(r2, r3)     // Catch:{ IOException -> 0x0043 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0043 }
            r2.<init>()     // Catch:{ IOException -> 0x0043 }
            java.lang.String r3 = b()     // Catch:{ IOException -> 0x0043 }
            r2.append(r3)     // Catch:{ IOException -> 0x0043 }
            java.lang.String r3 = "  "
            r2.append(r3)     // Catch:{ IOException -> 0x0043 }
            r2.append(r4)     // Catch:{ IOException -> 0x0043 }
            java.lang.String r4 = "  "
            r2.append(r4)     // Catch:{ IOException -> 0x0043 }
            r2.append(r5)     // Catch:{ IOException -> 0x0043 }
            java.lang.String r4 = "\n"
            r2.append(r4)     // Catch:{ IOException -> 0x0043 }
            java.lang.String r4 = r2.toString()     // Catch:{ IOException -> 0x0043 }
            r1.write(r4)     // Catch:{ IOException -> 0x0043 }
            r1.close()     // Catch:{ IOException -> 0x0043 }
        L_0x0043:
            monitor-exit(r0)
            return
        L_0x0045:
            monitor-exit(r0)
            return
        L_0x0047:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.xm.bt.a.a.c(java.lang.String, java.lang.String):void");
    }

    private static String b() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }

    public static void e(String str) {
        a("DEBUG", str, 0, 'w');
    }

    private static void a(String str, String str2, int i, char c2) {
        if (d) {
            String name = Thread.currentThread().getName();
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[i + 4];
            String className = stackTraceElement.getClassName();
            String str3 = Operators.ARRAY_START_STR + name + "]<" + className.substring(className.lastIndexOf(46) + 1) + ":" + stackTraceElement.getMethodName() + ":" + stackTraceElement.getLineNumber() + "> ";
            switch (c2) {
                case 'd':
                    Log.d(str, str3 + str2 + "");
                    return;
                case 'e':
                    Log.e(str, str3 + str2 + "");
                    return;
                case 'i':
                    Log.i(str, str3 + str2 + "");
                    return;
                case 'v':
                    Log.v(str, str3 + str2 + "");
                    return;
                case 'w':
                    Log.w(str, str3 + str2 + "");
                    return;
                default:
                    return;
            }
        }
    }
}
