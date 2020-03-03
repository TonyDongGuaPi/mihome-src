package com.ximalaya.ting.android.player;

import android.os.Environment;
import android.os.Process;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater;
import java.io.File;
import java.io.PrintStream;

public class Logger {

    /* renamed from: a  reason: collision with root package name */
    public static final String f2275a = "解析json异常";
    public static boolean b = false;
    private static final int c = 0;

    public static void a(Object obj) {
        if (b) {
            System.out.println(obj);
        } else if (XMediaPlayerConstants.f2293a) {
            Log.i(XmNotificationCreater.C, obj + "");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0026  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.lang.String r5) {
        /*
            boolean r0 = com.ximalaya.ting.android.player.XMediaPlayerConstants.f2293a
            if (r0 == 0) goto L_0x002f
            java.io.File r0 = a()
            if (r0 != 0) goto L_0x000b
            return
        L_0x000b:
            r1 = 0
            java.io.PrintWriter r2 = new java.io.PrintWriter     // Catch:{ Throwable -> 0x002a, all -> 0x0023 }
            java.io.FileWriter r3 = new java.io.FileWriter     // Catch:{ Throwable -> 0x002a, all -> 0x0023 }
            r4 = 1
            r3.<init>(r0, r4)     // Catch:{ Throwable -> 0x002a, all -> 0x0023 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x002a, all -> 0x0023 }
            r2.println(r5)     // Catch:{ Throwable -> 0x0021, all -> 0x001e }
            r2.close()
            goto L_0x002f
        L_0x001e:
            r5 = move-exception
            r1 = r2
            goto L_0x0024
        L_0x0021:
            r1 = r2
            goto L_0x002a
        L_0x0023:
            r5 = move-exception
        L_0x0024:
            if (r1 == 0) goto L_0x0029
            r1.close()
        L_0x0029:
            throw r5
        L_0x002a:
            if (r1 == 0) goto L_0x002f
            r1.close()
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.Logger.a(java.lang.String):void");
    }

    public static File a() {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return null;
        }
        File file = new File("/sdcard/ting/errorLog/infor.log");
        if (!file.getParentFile().getParentFile().exists()) {
            file.getParentFile().getParentFile().mkdir();
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        return file;
    }

    public static void b(Object obj) {
        if (XMediaPlayerConstants.f2293a) {
            throw new RuntimeException("出现异常：" + obj);
        }
    }

    public static String b() {
        if (!XMediaPlayerConstants.f2293a) {
            return "";
        }
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        return "@" + stackTraceElement.getFileName() + ": Line " + stackTraceElement.getLineNumber();
    }

    public static void a(String str, Object obj) {
        if (XMediaPlayerConstants.f2293a && obj != null && !str.equals("dl_mp3")) {
            Log.i(str, "JTid(" + Long.toString(Thread.currentThread().getId()) + ")STid(" + Process.myTid() + ")SPid(" + Process.myPid() + Operators.BRACKET_END_STR + obj);
        }
    }

    public static void a(String str, String str2) {
        if (str2 != null && a(str, 2)) {
            Log.v(str, str2);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (str2 != null && a(str, 2)) {
            Log.v(str, str2, th);
        }
    }

    public static void b(String str, String str2) {
        if (b) {
            f(str, str2);
        } else if (str2 != null && a(str, 3)) {
            Log.d(str, str2);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        if (str2 != null && a(str, 3)) {
            Log.d(str, str2, th);
        }
    }

    public static void c(String str, String str2) {
        if (str2 != null && a(str, 4)) {
            Log.i(str, str2);
        }
    }

    public static void c(String str, String str2, Throwable th) {
        if (str2 != null && a(str, 4)) {
            Log.i(str, str2, th);
        }
    }

    public static void d(String str, String str2) {
        if (str2 != null && a(str, 5)) {
            Log.w(str, str2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (str2 != null && a(str, 5)) {
            Log.w(str, str2, th);
        }
    }

    public static void a(String str, Throwable th) {
        if (th != null && a(str, 5)) {
            Log.w(str, th);
        }
    }

    public static void a(Exception exc) {
        e("解析json异常", "解析json异常" + exc.getMessage() + b());
    }

    public static void e(String str, String str2) {
        if (str2 != null && a(str, 6)) {
            Log.e(str, str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (str2 != null && a(str, 6)) {
            Log.e(str, str2, th);
        }
    }

    public static void f(String str, String str2) {
        PrintStream printStream = System.out;
        printStream.println(str + "  " + str2);
    }

    public static boolean a(String str, int i) {
        return XMediaPlayerConstants.f2293a && i >= 0;
    }
}
