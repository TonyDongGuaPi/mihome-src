package com.amap.location.common.log;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import com.amap.location.common.HeaderConfig;
import com.amap.location.common.log.LogConfig;
import com.amap.location.common.util.FileUtil;
import com.amap.location.common.util.a;
import java.io.File;
import java.io.FileFilter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class ALLog {
    /* access modifiers changed from: private */
    public static volatile HandlerThread A = null;
    /* access modifiers changed from: private */
    public static volatile File B = null;
    private static long C = 0;
    /* access modifiers changed from: private */
    public static final ArrayDeque<File> D = new ArrayDeque<>();
    private static volatile LinkedList<String> E = new LinkedList<>();
    private static LinkedList<LinkedList<String>> F = new LinkedList<>();
    private static final Object G = new Object();
    /* access modifiers changed from: private */
    public static volatile Context H = null;
    /* access modifiers changed from: private */
    public static String I = "";
    /* access modifiers changed from: private */
    public static volatile boolean J = false;
    private static String K = "";
    /* access modifiers changed from: private */
    public static final Runnable L = new Runnable() {
        public final void run() {
            try {
                if (!ALLog.r()) {
                    ALLog.u();
                    return;
                }
                File[] b = ALLog.f(ALLog.h);
                if (b != null && b.length > 0) {
                    synchronized (ALLog.D) {
                        for (File offer : b) {
                            ALLog.D.offer(offer);
                        }
                    }
                }
                String unused = ALLog.I = a.a(ALLog.H);
                File unused2 = ALLog.B = ALLog.s();
                if (ALLog.B == null) {
                    ALLog.u();
                    return;
                }
                boolean unused3 = ALLog.J = true;
                ALLog.z.sendMessageDelayed(ALLog.z.obtainMessage(2), ALLog.p);
            } catch (Exception e) {
                ALLog.a(ALLog.f4574a, "InitLogFileTask  error ", (Throwable) e);
            }
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private static final String f4574a = "ALLog";
    private static final String b = "trace_";
    private static volatile boolean c = false;
    private static volatile boolean d = false;
    private static volatile boolean e = false;
    private static boolean f = false;
    private static boolean g = true;
    /* access modifiers changed from: private */
    public static volatile String h = "";
    private static LogConfig.Product i = LogConfig.Product.SDK;
    private static String j = "sdk";
    private static final int k = 1;
    private static final int l = 2;
    private static final int m = 1;
    private static final int n = 2;
    private static final int o = 4;
    private static final long p = 20000;
    private static final long q = 5000;
    private static long r = 1048576;
    private static long s = 20;
    private static final long t = 5;
    private static long u = 204800;
    private static final SimpleDateFormat v = new SimpleDateFormat("MM-dd HH:mm:ss:SSS", Locale.US);
    private static final SimpleDateFormat w = new SimpleDateFormat("yyyyMMdd-HHmmss-SSS", Locale.US);
    private static final Date x = new Date();
    private static LogConfig.a y;
    /* access modifiers changed from: private */
    public static volatile Handler z;

    private ALLog() {
    }

    public static String a(String str) {
        return "@@_" + a.a(str) + "_@@";
    }

    private static String a(DateFormat dateFormat) {
        String format;
        synchronized (x) {
            x.setTime(System.currentTimeMillis());
            format = dateFormat.format(x);
        }
        return format;
    }

    private static void a(int i2, String str, String str2, boolean z2, boolean z3) {
        String str3;
        boolean z4 = z2 && d && J;
        boolean z5 = z3 && e && y != null && y.a();
        if (z4 || z5) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(System.currentTimeMillis()));
            sb.append("|");
            if (i2 != 4) {
                switch (i2) {
                    case 1:
                        str3 = "info|";
                        break;
                    case 2:
                        str3 = "warn|";
                        break;
                }
            } else {
                str3 = "error|";
            }
            sb.append(str3);
            long myTid = (long) Process.myTid();
            sb.append(K);
            sb.append("|");
            sb.append(String.valueOf(myTid));
            sb.append("|");
            sb.append(str);
            sb.append("|");
            sb.append(str2);
            sb.append("\n");
            if (z4) {
                d(sb.toString());
            }
            if (z5) {
                e(sb.substring(0, sb.length() - 1));
            }
        }
    }

    public static void a(Context context, LogConfig logConfig) {
        if (H == null) {
            H = context.getApplicationContext();
            c = logConfig.a();
            d = logConfig.b();
            e = logConfig.c();
            h = logConfig.i();
            y = logConfig.k();
            g = logConfig.d();
            f = logConfig.e();
            u = (long) logConfig.g();
            s = (long) logConfig.f();
            r = (long) logConfig.h();
            a(logConfig.j());
            K = String.valueOf(Process.myPid());
            if (d) {
                q();
            }
        }
    }

    private static void a(LogConfig.Product product) {
        i = product;
        switch (product) {
            case FLP:
                j = "flp";
                return;
            case NLP:
                j = "nlp";
                return;
            default:
                return;
        }
    }

    public static void a(Exception exc) {
        if (a()) {
            a(b, "", (Throwable) exc, f, g);
        }
    }

    public static void a(String str, String str2) {
        if (c) {
            Log.i(str, str2);
        }
    }

    public static void a(String str, String str2, Exception exc) {
        if (a()) {
            a(b + str, str2, (Throwable) exc, f, g);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (c) {
            Log.e(str, str2, th);
        }
    }

    public static void a(String str, String str2, Throwable th, boolean z2) {
        a(str, str2, th);
        a(4, str, str2 + Log.getStackTraceString(th), z2, false);
    }

    public static void a(String str, String str2, Throwable th, boolean z2, boolean z3) {
        a(str, str2, th);
        a(4, str, str2 + Log.getStackTraceString(th), z2, z3);
    }

    public static void a(String str, String str2, boolean z2) {
        a(str, str2);
        a(1, str, str2, z2, false);
    }

    public static void a(String str, String str2, boolean z2, boolean z3) {
        a(str, str2);
        a(1, str, str2, z2, z3);
    }

    private static void a(List<String> list) {
        if (list != null) {
            try {
                if (list.size() != 0) {
                    StringBuilder sb = new StringBuilder();
                    for (String append : list) {
                        sb.append(append);
                    }
                    if (!a(sb.toString(), B)) {
                        u();
                        return;
                    }
                    synchronized (D) {
                        while (((long) (D.size() + 1)) > s) {
                            File poll = D.poll();
                            if (poll != null && poll.exists()) {
                                try {
                                    poll.delete();
                                } catch (Exception e2) {
                                    a(f4574a, "MAX_FILE_NUM delete  error ", (Throwable) e2);
                                }
                            }
                        }
                    }
                    if (B.length() > r) {
                        synchronized (D) {
                            D.offer(B);
                        }
                        File s2 = s();
                        B = s2;
                        if (s2 == null) {
                            u();
                        }
                    }
                }
            } catch (Exception e3) {
                a(f4574a, "DumpTask  error ", (Throwable) e3);
            }
        }
    }

    public static void a(boolean z2) {
        d = z2;
    }

    public static boolean a() {
        try {
            return e && y != null && y.a();
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean a(String str, File file) {
        if (FileUtil.a(str + "\r\n-------------------\r\n", file, true)) {
            return true;
        }
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            File file2 = parentFile;
            int i2 = 0;
            while (true) {
                if (file2 != null) {
                    if (!file2.exists()) {
                        file2 = file2.getParentFile();
                        i2++;
                        if (i2 >= 2) {
                            break;
                        }
                    } else if (file2.isFile()) {
                        file2.delete();
                    }
                } else {
                    break;
                }
            }
            if (parentFile != null) {
                try {
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    file.createNewFile();
                    return FileUtil.a(str + "\r\n-------------------\r\n", file, true);
                } catch (Exception unused) {
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static void b(Message message) {
        switch (message.what) {
            case 1:
                LinkedList linkedList = null;
                synchronized (G) {
                    if (F.size() > 0) {
                        linkedList = F.removeFirst();
                    }
                }
                System.currentTimeMillis();
                a((List<String>) linkedList);
                System.currentTimeMillis();
                if (z != null) {
                    z.sendMessageDelayed(z.obtainMessage(2), p);
                    return;
                }
                return;
            case 2:
                synchronized (G) {
                    if (z != null) {
                        F.add(E);
                        while (((long) F.size()) > 5) {
                            F.removeFirst();
                        }
                        z.obtainMessage(1).sendToTarget();
                        E = new LinkedList<>();
                    } else {
                        E.clear();
                    }
                    C = 0;
                }
                return;
            default:
                return;
        }
    }

    public static void b(String str, String str2) {
        if (c) {
            Log.w(str, str2);
        }
    }

    public static void b(String str, String str2, boolean z2) {
        b(str, str2);
        a(2, str, str2, z2, false);
    }

    public static void b(String str, String str2, boolean z2, boolean z3) {
        b(str, str2);
        a(2, str, str2, z2, z3);
    }

    public static void b(boolean z2) {
        c = z2;
    }

    public static boolean b() {
        return c;
    }

    public static void c(String str, String str2) {
        if (c) {
            Log.e(str, str2);
        }
    }

    public static void c(String str, String str2, boolean z2) {
        c(str, str2);
        a(4, str, str2, z2, false);
    }

    public static void c(String str, String str2, boolean z2, boolean z3) {
        c(str, str2);
        a(4, str, str2, z2, z3);
    }

    public static void c(boolean z2) {
        e = z2;
    }

    public static boolean c() {
        return d;
    }

    private static void d(String str) {
        synchronized (G) {
            E.add(str);
            C += (long) str.length();
            if (((long) E.size()) >= 5000 || C > u) {
                if (z != null) {
                    F.add(E);
                    while (((long) F.size()) > 5) {
                        F.removeFirst();
                    }
                    z.obtainMessage(1).sendToTarget();
                    z.removeMessages(2);
                    E = new LinkedList<>();
                } else {
                    E.clear();
                }
                C = 0;
            }
        }
    }

    public static void d(String str, String str2) {
        if (a()) {
            b(b + str, str2, f, g);
        }
    }

    public static void d(boolean z2) {
        f = z2;
    }

    public static boolean d() {
        return e;
    }

    public static String e() {
        return h;
    }

    private static void e(String str) {
        if (y != null) {
            y.a(str);
        }
    }

    public static void e(boolean z2) {
        g = z2;
    }

    public static LogConfig.Product f() {
        return i;
    }

    /* access modifiers changed from: private */
    public static File[] f(String str) {
        File[] listFiles = new File(str).listFiles(new FileFilter() {
            public final boolean accept(File file) {
                return !file.isDirectory();
            }
        });
        if (listFiles == null || listFiles.length == 0) {
            return null;
        }
        Arrays.sort(listFiles, new Comparator<File>() {
            /* renamed from: a */
            public final int compare(File file, File file2) {
                long lastModified = file.lastModified() - file2.lastModified();
                if (lastModified > 0) {
                    return 1;
                }
                return lastModified < 0 ? -1 : 0;
            }
        });
        return listFiles;
    }

    private static void q() {
        AnonymousClass1 r0 = new HandlerThread("allog" + Process.myPid()) {
            /* access modifiers changed from: protected */
            public final void onLooperPrepared() {
                Looper looper = ALLog.A.getLooper();
                if (looper != null) {
                    Handler unused = ALLog.z = new Handler(looper) {
                        public void handleMessage(Message message) {
                            ALLog.b(message);
                        }
                    };
                    ALLog.z.post(ALLog.L);
                }
            }
        };
        A = r0;
        r0.start();
    }

    /* access modifiers changed from: private */
    public static boolean r() {
        File file = new File(h);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
        boolean exists = file.exists();
        if (!exists) {
            exists = file.mkdirs();
        }
        if (!exists || !file.canWrite()) {
            return false;
        }
        File file2 = new File(file, HeaderConfig.e());
        if (!file2.exists() && !file2.mkdir()) {
            return true;
        }
        h = file2.getAbsolutePath();
        return true;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        r1 = h;
        r0 = new java.io.File(r1, t() + "_log_" + a((java.text.DateFormat) w) + com.xiaomi.smarthome.download.Constants.n);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r0.createNewFile();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0064, code lost:
        if (android.text.TextUtils.isEmpty(I) != false) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0066, code lost:
        com.amap.location.common.util.FileUtil.a(I + "\r\n-------------------\r\n", r0, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x007d, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007e, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.File s() {
        /*
            java.util.ArrayDeque<java.io.File> r0 = D
            monitor-enter(r0)
            java.util.ArrayDeque<java.io.File> r1 = D     // Catch:{ all -> 0x007f }
            int r1 = r1.size()     // Catch:{ all -> 0x007f }
            r2 = 0
            if (r1 <= 0) goto L_0x0015
            java.util.ArrayDeque<java.io.File> r1 = D     // Catch:{ all -> 0x007f }
            java.lang.Object r1 = r1.getLast()     // Catch:{ all -> 0x007f }
            java.io.File r1 = (java.io.File) r1     // Catch:{ all -> 0x007f }
            goto L_0x0016
        L_0x0015:
            r1 = r2
        L_0x0016:
            if (r1 == 0) goto L_0x0030
            long r3 = r1.length()     // Catch:{ all -> 0x007f }
            long r5 = r     // Catch:{ all -> 0x007f }
            r7 = 2
            long r5 = r5 * r7
            r7 = 3
            long r5 = r5 / r7
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 >= 0) goto L_0x0030
            java.util.ArrayDeque<java.io.File> r2 = D     // Catch:{ all -> 0x007f }
            r2.removeLast()     // Catch:{ all -> 0x007f }
            monitor-exit(r0)     // Catch:{ all -> 0x007f }
            return r1
        L_0x0030:
            monitor-exit(r0)     // Catch:{ all -> 0x007f }
            java.io.File r0 = new java.io.File
            java.lang.String r1 = h
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = t()
            r3.append(r4)
            java.lang.String r4 = "_log_"
            r3.append(r4)
            java.text.SimpleDateFormat r4 = w
            java.lang.String r4 = a((java.text.DateFormat) r4)
            r3.append(r4)
            java.lang.String r4 = ".txt"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.<init>(r1, r3)
            r0.createNewFile()     // Catch:{ IOException -> 0x007e }
            java.lang.String r1 = I
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x007d
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = I
            r1.append(r2)
            java.lang.String r2 = "\r\n-------------------\r\n"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r2 = 1
            com.amap.location.common.util.FileUtil.a((java.lang.String) r1, (java.io.File) r0, (boolean) r2)
        L_0x007d:
            return r0
        L_0x007e:
            return r2
        L_0x007f:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x007f }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.location.common.log.ALLog.s():java.io.File");
    }

    private static String t() {
        return j;
    }

    /* access modifiers changed from: private */
    public static void u() {
        J = false;
        try {
            if (A != null) {
                if (Build.VERSION.SDK_INT > 18) {
                    A.quitSafely();
                } else {
                    A.quit();
                }
            }
            z = null;
            A = null;
            synchronized (D) {
                D.clear();
            }
            synchronized (G) {
                E.clear();
                F.clear();
            }
        } catch (Exception e2) {
            try {
                a(f4574a, "dispose error ", (Throwable) e2);
                z = null;
                A = null;
                synchronized (D) {
                    D.clear();
                    synchronized (G) {
                        E.clear();
                        F.clear();
                    }
                }
            } catch (Throwable th) {
                z = null;
                A = null;
                synchronized (D) {
                    D.clear();
                    synchronized (G) {
                        E.clear();
                        F.clear();
                        throw th;
                    }
                }
            }
        }
    }
}
