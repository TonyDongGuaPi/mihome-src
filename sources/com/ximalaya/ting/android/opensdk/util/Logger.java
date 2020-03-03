package com.ximalaya.ting.android.opensdk.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.ximalaya.ting.android.opensdk.constants.ConstantsOpenSdk;
import com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logger {

    /* renamed from: a  reason: collision with root package name */
    public static final String f2259a = "解析json异常";
    public static String b = "";
    public static String c = "upload_log_file";
    public static boolean d = false;
    public static String e = (Environment.getExternalStorageDirectory() + "/ting/errorLog/infor.log");
    static Map<String, String> f = new HashMap();
    private static long g = System.currentTimeMillis();
    private static int h = 0;

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b0 A[SYNTHETIC, Splitter:B:42:0x00b0] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ba A[SYNTHETIC, Splitter:B:47:0x00ba] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00c7 A[SYNTHETIC, Splitter:B:54:0x00c7] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00d1 A[SYNTHETIC, Splitter:B:59:0x00d1] */
    /* JADX WARNING: Removed duplicated region for block: B:72:? A[RETURN, SYNTHETIC] */
    static {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.io.File r1 = android.os.Environment.getExternalStorageDirectory()
            r0.append(r1)
            java.lang.String r1 = "/ting/errorLog/infor.log"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            e = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            f = r0
            long r0 = java.lang.System.currentTimeMillis()
            g = r0
            r0 = 0
            h = r0
            java.util.Map<java.lang.String, java.lang.String> r1 = f
            r1.clear()
            r1 = 0
            java.lang.String r2 = android.os.Environment.getExternalStorageState()     // Catch:{ Exception -> 0x0032 }
            goto L_0x0037
        L_0x0032:
            r2 = move-exception
            r2.printStackTrace()
            r2 = r1
        L_0x0037:
            java.lang.String r3 = "mounted"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x00da
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.io.File r3 = android.os.Environment.getExternalStorageDirectory()
            r2.append(r3)
            java.lang.String r3 = "/ting/config.ini"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L_0x00da
            java.io.File r3 = new java.io.File
            r3.<init>(r2)
            boolean r3 = r3.exists()
            if (r3 == 0) goto L_0x00da
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Exception -> 0x00a9, all -> 0x00a5 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x00a9, all -> 0x00a5 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00a3 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x00a3 }
        L_0x006f:
            java.lang.String r1 = r2.readLine()     // Catch:{ Exception -> 0x00a0, all -> 0x009e }
            if (r1 == 0) goto L_0x0092
            boolean r4 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x00a0, all -> 0x009e }
            if (r4 != 0) goto L_0x006f
            java.lang.String r4 = "="
            java.lang.String[] r1 = r1.split(r4)     // Catch:{ Exception -> 0x00a0, all -> 0x009e }
            if (r1 == 0) goto L_0x006f
            int r4 = r1.length     // Catch:{ Exception -> 0x00a0, all -> 0x009e }
            r5 = 2
            if (r4 != r5) goto L_0x006f
            java.util.Map<java.lang.String, java.lang.String> r4 = f     // Catch:{ Exception -> 0x00a0, all -> 0x009e }
            r5 = r1[r0]     // Catch:{ Exception -> 0x00a0, all -> 0x009e }
            r6 = 1
            r1 = r1[r6]     // Catch:{ Exception -> 0x00a0, all -> 0x009e }
            r4.put(r5, r1)     // Catch:{ Exception -> 0x00a0, all -> 0x009e }
            goto L_0x006f
        L_0x0092:
            r2.close()     // Catch:{ IOException -> 0x0096 }
            goto L_0x009a
        L_0x0096:
            r0 = move-exception
            r0.printStackTrace()
        L_0x009a:
            r3.close()     // Catch:{ IOException -> 0x00be }
            goto L_0x00da
        L_0x009e:
            r0 = move-exception
            goto L_0x00c5
        L_0x00a0:
            r0 = move-exception
            r1 = r2
            goto L_0x00ab
        L_0x00a3:
            r0 = move-exception
            goto L_0x00ab
        L_0x00a5:
            r0 = move-exception
            r2 = r1
            r3 = r2
            goto L_0x00c5
        L_0x00a9:
            r0 = move-exception
            r3 = r1
        L_0x00ab:
            r0.printStackTrace()     // Catch:{ all -> 0x00c3 }
            if (r1 == 0) goto L_0x00b8
            r1.close()     // Catch:{ IOException -> 0x00b4 }
            goto L_0x00b8
        L_0x00b4:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00b8:
            if (r3 == 0) goto L_0x00da
            r3.close()     // Catch:{ IOException -> 0x00be }
            goto L_0x00da
        L_0x00be:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00da
        L_0x00c3:
            r0 = move-exception
            r2 = r1
        L_0x00c5:
            if (r2 == 0) goto L_0x00cf
            r2.close()     // Catch:{ IOException -> 0x00cb }
            goto L_0x00cf
        L_0x00cb:
            r1 = move-exception
            r1.printStackTrace()
        L_0x00cf:
            if (r3 == 0) goto L_0x00d9
            r3.close()     // Catch:{ IOException -> 0x00d5 }
            goto L_0x00d9
        L_0x00d5:
            r1 = move-exception
            r1.printStackTrace()
        L_0x00d9:
            throw r0
        L_0x00da:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.util.Logger.<clinit>():void");
    }

    public static void a(Object obj) {
        if (ConstantsOpenSdk.b) {
            Log.i(XmNotificationCreater.C, obj + "");
        }
    }

    public static void a(List list, String str) {
        if (list != null && list.size() != 0) {
            a("start-log-list:" + str + ":size:" + list.size());
            for (Object next : list) {
                if (next != null) {
                    a(next.toString());
                }
            }
            a("end-log-list" + str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x001e  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0024  */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.lang.String r4, java.io.File r5) {
        /*
            if (r5 != 0) goto L_0x0003
            return
        L_0x0003:
            r0 = 0
            java.io.PrintWriter r1 = new java.io.PrintWriter     // Catch:{ Throwable -> 0x0022, all -> 0x001b }
            java.io.FileWriter r2 = new java.io.FileWriter     // Catch:{ Throwable -> 0x0022, all -> 0x001b }
            r3 = 1
            r2.<init>(r5, r3)     // Catch:{ Throwable -> 0x0022, all -> 0x001b }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0022, all -> 0x001b }
            r1.println(r4)     // Catch:{ Throwable -> 0x0019, all -> 0x0016 }
            r1.close()
            goto L_0x0027
        L_0x0016:
            r4 = move-exception
            r0 = r1
            goto L_0x001c
        L_0x0019:
            r0 = r1
            goto L_0x0022
        L_0x001b:
            r4 = move-exception
        L_0x001c:
            if (r0 == 0) goto L_0x0021
            r0.close()
        L_0x0021:
            throw r4
        L_0x0022:
            if (r0 == 0) goto L_0x0027
            r0.close()
        L_0x0027:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.util.Logger.a(java.lang.String, java.io.File):void");
    }

    public static void a(String str) {
        if (c(str) || ConstantsOpenSdk.b) {
            a((Object) str);
            File a2 = a();
            if (a2 != null) {
                a(str, a2);
            }
        }
    }

    public static String a(Context context) {
        b = context.getCacheDir().getPath();
        return b;
    }

    public static void b(String str) {
        if (ConstantsOpenSdk.M && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(b) && !d) {
            a(str, new File(b, c));
        }
    }

    public static void b(Context context) {
        if (context != null && context.getExternalFilesDir("") != null) {
            e = context.getExternalFilesDir("") + "/errorLog/infor.log";
        }
    }

    public static File a() {
        try {
            if (!"mounted".equals(Environment.getExternalStorageState())) {
                return null;
            }
            File file = new File(Environment.getExternalStorageDirectory() + "/ting/errorLog/infor.log");
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return file;
        } catch (ArrayIndexOutOfBoundsException unused) {
            return null;
        }
    }

    public static boolean c(String str) {
        for (Map.Entry next : f.entrySet()) {
            if (str.contains((CharSequence) next.getKey()) && ((String) next.getValue()).equals("true")) {
                return true;
            }
        }
        return false;
    }

    public static void b(Object obj) {
        if (ConstantsOpenSdk.b) {
            throw new RuntimeException("出现异常：" + obj);
        }
    }

    public static void d(String str) {
        a((Object) str);
        g = System.currentTimeMillis();
    }

    public static void e(String str) {
        a((Object) "time " + str + ":" + (System.currentTimeMillis() - g));
        g = System.currentTimeMillis();
    }

    public static String b() {
        if (!ConstantsOpenSdk.b) {
            return "";
        }
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        return "@" + stackTraceElement.getFileName() + ": Line " + stackTraceElement.getLineNumber();
    }

    public static void a(int i) {
        h = i;
    }

    public static void a(String str, String str2, boolean z) {
        if (str2 != null && a(str, 3)) {
            Log.d(str, str2);
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
        if (str2 != null && a(str, 3)) {
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

    public static boolean a(String str, int i) {
        if (ConstantsOpenSdk.b && i >= h) {
            return true;
        }
        return false;
    }
}
