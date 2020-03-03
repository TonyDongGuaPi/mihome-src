package com.alipay.mobile.common.logging.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.mobile.common.logging.api.LogContext;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.common.logging.api.trace.TraceLogger;
import com.taobao.weex.el.parse.Operators;
import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class LoggingUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f970a = "LoggingUtil";
    private static Boolean b = null;
    private static int c = -1;

    public static synchronized boolean isDebug(Context context) {
        synchronized (LoggingUtil.class) {
            if (context == null) {
                return false;
            }
            if (b == null) {
                try {
                    ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 16384);
                    if (applicationInfo == null || (applicationInfo.flags & 2) == 0) {
                        b = false;
                    } else {
                        b = true;
                    }
                } catch (Throwable th) {
                    b = false;
                    Log.w(f970a, th);
                }
            }
            boolean booleanValue = b.booleanValue();
            return booleanValue;
        }
    }

    public static void reflectErrorLog(String str) {
        if (str != null) {
            reflectErrorLog(f970a, str, true);
        }
    }

    public static void reflectErrorLog(String str, String str2, boolean z) {
        try {
            StringBuilder sb = new StringBuilder();
            if (z) {
                sb.append(Operators.ARRAY_START);
                sb.append(Thread.currentThread().getName());
                sb.append(Operators.ARRAY_END);
                sb.append(str2);
            } else {
                sb.append(str2);
            }
            Class.forName("android.util.Log").getMethod("e", new Class[]{String.class, String.class}).invoke((Object) null, new Object[]{str, sb.toString()});
        } catch (Throwable unused) {
        }
    }

    public static void reflectErrorLog(String str, Throwable th) {
        if (str != null && th != null) {
            try {
                Class.forName("android.util.Log").getMethod("e", new Class[]{String.class, String.class, Throwable.class}).invoke((Object) null, new Object[]{f970a, Operators.ARRAY_START + Thread.currentThread().getName() + Operators.ARRAY_END + str, th});
            } catch (Throwable unused) {
            }
        }
    }

    public static String getNowTime() {
        return String.format(Locale.getDefault(), "%04d-%02d-%02d %02d:%02d:%02d:%03d", new Object[]{Integer.valueOf(Calendar.getInstance().get(1)), Integer.valueOf(Calendar.getInstance().get(2) + 1), Integer.valueOf(Calendar.getInstance().get(5)), Integer.valueOf(Calendar.getInstance().get(11)), Integer.valueOf(Calendar.getInstance().get(12)), Integer.valueOf(Calendar.getInstance().get(13)), Integer.valueOf(Calendar.getInstance().get(14))});
    }

    public static StringBuilder appendParam(StringBuilder sb, String str) {
        if (sb == null) {
            return null;
        }
        sb.append(',');
        if (str != null) {
            sb.append(str.replace(',', ' '));
        }
        return sb;
    }

    public static StringBuilder appendExtParam(StringBuilder sb, Map<String, String> map) {
        if (sb == null) {
            return null;
        }
        sb.append(',');
        if (map == null || map.size() == 0) {
            return sb;
        }
        boolean z = true;
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            String str2 = (String) next.getValue();
            if (!(str == null || str2 == null)) {
                if (z) {
                    z = false;
                } else {
                    sb.append('^');
                }
                sb.append(str.replace(',', ' ').replace('^', ' ').replace('=', ' '));
                sb.append('=');
                sb.append(str2.replace(',', ' ').replace('^', ' '));
            }
        }
        return sb;
    }

    public static StringBuilder appendJsonExtParam(StringBuilder sb, Map<String, String> map) {
        if (sb == null) {
            return null;
        }
        sb.append(',');
        if (map == null || map.size() == 0) {
            return sb;
        }
        boolean z = true;
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            String str2 = (String) next.getValue();
            if (!(str == null || str2 == null)) {
                if (z) {
                    z = false;
                } else {
                    sb.append('^');
                }
                sb.append(str.replace(',', ' ').replace('^', ' ').replace('=', ' '));
                sb.append('=');
                sb.append(str2.replace(',', '*').replace('^', ' '));
            }
        }
        return sb;
    }

    public static File getCommonExternalStorageDir() {
        File file;
        try {
            file = new File(Environment.getExternalStorageDirectory(), "alipay");
        } catch (Throwable unused) {
            file = new File("/sdcard/alipay");
        }
        try {
            if (!file.exists()) {
                file.mkdirs();
            } else if (file.isFile()) {
                FileUtil.deleteFileNotDir(file);
                file.mkdirs();
            }
        } catch (Throwable unused2) {
        }
        return file;
    }

    public static String getCommonExternalStoragePath() {
        File commonExternalStorageDir = getCommonExternalStorageDir();
        if (commonExternalStorageDir == null) {
            return null;
        }
        return commonExternalStorageDir.getAbsolutePath();
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028 A[SYNTHETIC, Splitter:B:8:0x0028] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.File getStorageFilesDir(android.content.Context r3, java.lang.String r4) {
        /*
            boolean r0 = isOfflineForExternalFile()
            java.lang.String r1 = "mounted"
            java.lang.String r2 = android.os.Environment.getExternalStorageState()
            boolean r1 = r1.equals(r2)
            if (r0 == 0) goto L_0x0025
            if (r1 == 0) goto L_0x0025
            java.io.File r0 = getCommonExternalStorageDir()     // Catch:{ Throwable -> 0x0025 }
            java.io.File r1 = new java.io.File     // Catch:{ Throwable -> 0x0025 }
            java.lang.String r2 = r3.getPackageName()     // Catch:{ Throwable -> 0x0025 }
            r1.<init>(r0, r2)     // Catch:{ Throwable -> 0x0025 }
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x0025 }
            r0.<init>(r1, r4)     // Catch:{ Throwable -> 0x0025 }
            goto L_0x0026
        L_0x0025:
            r0 = 0
        L_0x0026:
            if (r0 != 0) goto L_0x0032
            java.io.File r1 = new java.io.File     // Catch:{ Throwable -> 0x0032 }
            java.io.File r3 = r3.getFilesDir()     // Catch:{ Throwable -> 0x0032 }
            r1.<init>(r3, r4)     // Catch:{ Throwable -> 0x0032 }
            r0 = r1
        L_0x0032:
            if (r0 == 0) goto L_0x003d
            boolean r3 = r0.exists()
            if (r3 != 0) goto L_0x003d
            r0.mkdirs()
        L_0x003d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.util.LoggingUtil.getStorageFilesDir(android.content.Context, java.lang.String):java.io.File");
    }

    public static boolean isOfflineForExternalFile() {
        return isOfflineMode() || "rc".equals(LoggerFactory.getLogContext().getReleaseType());
    }

    public static boolean isOfflineMode() {
        if (c < 0) {
            String releaseType = LoggerFactory.getLogContext().getReleaseType();
            c = ("dev".equals(releaseType) || "test".equals(releaseType) || LogContext.RELEASETYPE_TESTPRE.equals(releaseType)) ? 1 : 0;
        }
        if (c == 1) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:5:0x0016 A[Catch:{ Throwable -> 0x0032 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isWalletProcessRuning(android.content.Context r3) {
        /*
            java.lang.String r0 = "activity"
            java.lang.Object r3 = r3.getSystemService(r0)     // Catch:{ Throwable -> 0x0032 }
            android.app.ActivityManager r3 = (android.app.ActivityManager) r3     // Catch:{ Throwable -> 0x0032 }
            java.util.List r3 = r3.getRunningAppProcesses()     // Catch:{ Throwable -> 0x0032 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Throwable -> 0x0032 }
        L_0x0010:
            boolean r0 = r3.hasNext()     // Catch:{ Throwable -> 0x0032 }
            if (r0 == 0) goto L_0x003c
            java.lang.Object r0 = r3.next()     // Catch:{ Throwable -> 0x0032 }
            android.app.ActivityManager$RunningAppProcessInfo r0 = (android.app.ActivityManager.RunningAppProcessInfo) r0     // Catch:{ Throwable -> 0x0032 }
            java.lang.String r1 = "com.eg.android.AlipayGphone"
            java.lang.String r2 = r0.processName     // Catch:{ Throwable -> 0x0032 }
            boolean r1 = r1.equals(r2)     // Catch:{ Throwable -> 0x0032 }
            if (r1 != 0) goto L_0x0030
            java.lang.String r1 = "com.eg.android.AlipayGphoneRC"
            java.lang.String r0 = r0.processName     // Catch:{ Throwable -> 0x0032 }
            boolean r0 = r1.equals(r0)     // Catch:{ Throwable -> 0x0032 }
            if (r0 == 0) goto L_0x0010
        L_0x0030:
            r3 = 1
            return r3
        L_0x0032:
            r3 = move-exception
            com.alipay.mobile.common.logging.api.trace.TraceLogger r0 = com.alipay.mobile.common.logging.api.LoggerFactory.getTraceLogger()
            java.lang.String r1 = f970a
            r0.error((java.lang.String) r1, (java.lang.Throwable) r3)
        L_0x003c:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.util.LoggingUtil.isWalletProcessRuning(android.content.Context):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x004a A[SYNTHETIC, Splitter:B:20:0x004a] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0051 A[SYNTHETIC, Splitter:B:28:0x0051] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String throwableToString(java.lang.Throwable r4) {
        /*
            if (r4 != 0) goto L_0x0005
            java.lang.String r4 = ""
            return r4
        L_0x0005:
            java.lang.String r0 = r4.getMessage()
            r1 = 0
            java.io.StringWriter r2 = new java.io.StringWriter     // Catch:{ Throwable -> 0x004e, all -> 0x0046 }
            r2.<init>()     // Catch:{ Throwable -> 0x004e, all -> 0x0046 }
            java.io.PrintWriter r3 = new java.io.PrintWriter     // Catch:{ Throwable -> 0x004e, all -> 0x0046 }
            r3.<init>(r2)     // Catch:{ Throwable -> 0x004e, all -> 0x0046 }
            java.lang.Throwable r1 = r4.getCause()     // Catch:{ Throwable -> 0x004f, all -> 0x0044 }
            if (r1 != 0) goto L_0x001e
            r4.printStackTrace(r3)     // Catch:{ Throwable -> 0x004f, all -> 0x0044 }
            goto L_0x0028
        L_0x001e:
            if (r1 == 0) goto L_0x0028
            r1.printStackTrace(r3)     // Catch:{ Throwable -> 0x004f, all -> 0x0044 }
            java.lang.Throwable r1 = r1.getCause()     // Catch:{ Throwable -> 0x004f, all -> 0x0044 }
            goto L_0x001e
        L_0x0028:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x004f, all -> 0x0044 }
            r4.<init>()     // Catch:{ Throwable -> 0x004f, all -> 0x0044 }
            r4.append(r0)     // Catch:{ Throwable -> 0x004f, all -> 0x0044 }
            java.lang.String r1 = "\r\n"
            r4.append(r1)     // Catch:{ Throwable -> 0x004f, all -> 0x0044 }
            java.lang.String r1 = r2.toString()     // Catch:{ Throwable -> 0x004f, all -> 0x0044 }
            r4.append(r1)     // Catch:{ Throwable -> 0x004f, all -> 0x0044 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x004f, all -> 0x0044 }
            r3.close()     // Catch:{ Throwable -> 0x0043 }
        L_0x0043:
            return r4
        L_0x0044:
            r4 = move-exception
            goto L_0x0048
        L_0x0046:
            r4 = move-exception
            r3 = r1
        L_0x0048:
            if (r3 == 0) goto L_0x004d
            r3.close()     // Catch:{ Throwable -> 0x004d }
        L_0x004d:
            throw r4
        L_0x004e:
            r3 = r1
        L_0x004f:
            if (r3 == 0) goto L_0x0054
            r3.close()     // Catch:{ Throwable -> 0x0054 }
        L_0x0054:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.util.LoggingUtil.throwableToString(java.lang.Throwable):java.lang.String");
    }

    public static String[] obtainThreadsStackTrace() {
        return a(true);
    }

    public static String acquireThreadsStackTrace() {
        return a(false)[0];
    }

    private static String[] a(boolean z) {
        StringBuilder sb = new StringBuilder(40000);
        StringBuilder sb2 = z ? new StringBuilder() : null;
        try {
            for (Map.Entry next : Thread.getAllStackTraces().entrySet()) {
                StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) next.getValue();
                String name = ((Thread) next.getKey()).getName();
                sb.append("\nThreadName=");
                sb.append(name);
                sb.append("\n");
                if (sb2 != null) {
                    if (sb2.length() > 0) {
                        sb2.append("|");
                    }
                    sb2.append(name);
                }
                for (StackTraceElement valueOf : stackTraceElementArr) {
                    sb.append(String.valueOf(valueOf));
                    sb.append("\n");
                }
                sb.append("\n");
            }
        } catch (Throwable th) {
            Log.e(f970a, "getThreadsStackTrace", th);
        }
        return new String[]{sb.toString(), String.valueOf(sb2)};
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0030 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0018 */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x002d A[SYNTHETIC, Splitter:B:21:0x002d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] gzipDataByBytes(byte[] r3) {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r1 = 0
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch:{ Throwable -> 0x0024 }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x0024 }
            r2.write(r3)     // Catch:{ Throwable -> 0x001f, all -> 0x001c }
            r2.finish()     // Catch:{ Throwable -> 0x001f, all -> 0x001c }
            byte[] r3 = r0.toByteArray()     // Catch:{ Throwable -> 0x001f, all -> 0x001c }
            r2.close()     // Catch:{ Throwable -> 0x0018 }
        L_0x0018:
            r0.close()     // Catch:{ Throwable -> 0x001b }
        L_0x001b:
            return r3
        L_0x001c:
            r3 = move-exception
            r1 = r2
            goto L_0x002b
        L_0x001f:
            r3 = move-exception
            r1 = r2
            goto L_0x0025
        L_0x0022:
            r3 = move-exception
            goto L_0x002b
        L_0x0024:
            r3 = move-exception
        L_0x0025:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0022 }
            r2.<init>(r3)     // Catch:{ all -> 0x0022 }
            throw r2     // Catch:{ all -> 0x0022 }
        L_0x002b:
            if (r1 == 0) goto L_0x0030
            r1.close()     // Catch:{ Throwable -> 0x0030 }
        L_0x0030:
            r0.close()     // Catch:{ Throwable -> 0x0033 }
        L_0x0033:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.util.LoggingUtil.gzipDataByBytes(byte[]):byte[]");
    }

    public static byte[] gzipDataByString(String str) {
        try {
            return gzipDataByBytes(str.getBytes("UTF-8"));
        } catch (Throwable th) {
            throw new IllegalStateException(th);
        }
    }

    public static int getProcessIdByName(Context context, String str) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        if (context == null || TextUtils.isEmpty(str)) {
            return -1;
        }
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (!(activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null)) {
                if (runningAppProcesses.size() != 0) {
                    for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                        if (next != null) {
                            if (str.equals(next.processName)) {
                                return next.pid;
                            }
                        }
                    }
                    return -1;
                }
            }
            return -1;
        } catch (Throwable th) {
            Log.w(f970a, th);
            return -1;
        }
    }

    public static String getZhizhiSetting(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str)) {
            return str2;
        }
        Cursor cursor = null;
        try {
            Cursor query = context.getContentResolver().query(Uri.parse(str), (String[]) null, (String) null, (String[]) null, (String) null);
            if (query != null) {
                try {
                    if (query.getCount() > 0) {
                        query.moveToFirst();
                        str2 = query.getString(0);
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = query;
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null && !query.isClosed()) {
                try {
                    query.close();
                } catch (Throwable unused) {
                }
            }
        } catch (Throwable th2) {
            th = th2;
            LoggerFactory.getTraceLogger().error(f970a, "getZhizhiSetting", th);
            cursor.close();
            TraceLogger traceLogger = LoggerFactory.getTraceLogger();
            String str3 = f970a;
            traceLogger.info(str3, "getZhizhiSetting, uri: " + str + ", value: " + str2);
            return str2;
        }
        TraceLogger traceLogger2 = LoggerFactory.getTraceLogger();
        String str32 = f970a;
        traceLogger2.info(str32, "getZhizhiSetting, uri: " + str + ", value: " + str2);
        return str2;
    }

    public static String concatArray(char c2, Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return "";
        }
        if (objArr.length == 1) {
            return String.valueOf(objArr[0]);
        }
        if (objArr.length == 2) {
            return String.valueOf(objArr[0]) + c2 + String.valueOf(objArr[1]);
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Object obj : objArr) {
            if (z) {
                z = false;
            } else {
                sb.append(c2);
            }
            sb.append(obj);
        }
        return sb.toString();
    }

    public static boolean isForegroundRunning(Context context) {
        if (context != null) {
            try {
                if (context.checkCallingOrSelfPermission("android.permission.GET_TASKS") == 0) {
                    List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
                    if (runningTasks.isEmpty() || !runningTasks.get(0).topActivity.getPackageName().equals(context.getPackageName())) {
                        return false;
                    }
                    return true;
                }
            } catch (Exception e) {
                LoggerFactory.getTraceLogger().error(f970a, (Throwable) e);
            }
        }
        return false;
    }
}
