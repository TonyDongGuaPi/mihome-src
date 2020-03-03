package com.xiaomi.smarthome.frame.process;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.core.server.CoreManager;
import java.util.List;

public class ProcessUtil {

    /* renamed from: a  reason: collision with root package name */
    public static String f1529a = "";

    public static void a(Context context) {
        if (context != null && TextUtils.isEmpty(f1529a)) {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses != null) {
                int myPid = Process.myPid();
                for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                    if (next.pid == myPid) {
                        f1529a = next.processName;
                    }
                }
            }
            if (TextUtils.isEmpty(f1529a)) {
                f1529a = a();
            }
            if (f1529a == null) {
                f1529a = "";
            }
            Log.i("ProcessUtil", f1529a);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x005b A[SYNTHETIC, Splitter:B:23:0x005b] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0068 A[SYNTHETIC, Splitter:B:28:0x0068] */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a() {
        /*
            r0 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0055 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0055 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0055 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0055 }
            r4.<init>()     // Catch:{ Exception -> 0x0055 }
            java.lang.String r5 = "/proc/"
            r4.append(r5)     // Catch:{ Exception -> 0x0055 }
            int r5 = android.os.Process.myPid()     // Catch:{ Exception -> 0x0055 }
            r4.append(r5)     // Catch:{ Exception -> 0x0055 }
            java.lang.String r5 = "/cmdline"
            r4.append(r5)     // Catch:{ Exception -> 0x0055 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0055 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0055 }
            java.lang.String r4 = "iso-8859-1"
            r2.<init>(r3, r4)     // Catch:{ Exception -> 0x0055 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0055 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004e, all -> 0x0049 }
            r0.<init>()     // Catch:{ Exception -> 0x004e, all -> 0x0049 }
        L_0x0031:
            int r2 = r1.read()     // Catch:{ Exception -> 0x004e, all -> 0x0049 }
            if (r2 <= 0) goto L_0x003c
            char r2 = (char) r2     // Catch:{ Exception -> 0x004e, all -> 0x0049 }
            r0.append(r2)     // Catch:{ Exception -> 0x004e, all -> 0x0049 }
            goto L_0x0031
        L_0x003c:
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x004e, all -> 0x0049 }
            r1.close()     // Catch:{ IOException -> 0x0044 }
            goto L_0x0048
        L_0x0044:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0048:
            return r0
        L_0x0049:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x0066
        L_0x004e:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x0056
        L_0x0053:
            r1 = move-exception
            goto L_0x0066
        L_0x0055:
            r1 = move-exception
        L_0x0056:
            r1.printStackTrace()     // Catch:{ all -> 0x0053 }
            if (r0 == 0) goto L_0x0063
            r0.close()     // Catch:{ IOException -> 0x005f }
            goto L_0x0063
        L_0x005f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0063:
            java.lang.String r0 = ""
            return r0
        L_0x0066:
            if (r0 == 0) goto L_0x0070
            r0.close()     // Catch:{ IOException -> 0x006c }
            goto L_0x0070
        L_0x006c:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0070:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.process.ProcessUtil.a():java.lang.String");
    }

    public static boolean b(Context context) {
        a(context);
        return "com.xiaomi.smarthome".equals(f1529a);
    }

    public static boolean c(Context context) {
        a(context);
        return CoreManager.d.equals(f1529a);
    }

    public static boolean d(Context context) {
        a(context);
        return "com.xiaomi.smarthome.notishortcut".equals(f1529a);
    }

    public static boolean e(Context context) {
        a(context);
        return f1529a.startsWith(CoreManager.g);
    }

    public static boolean f(Context context) {
        a(context);
        return f1529a.startsWith(CoreManager.h);
    }

    public static String g(Context context) {
        a(context);
        return f1529a;
    }

    public static boolean h(Context context) {
        a(context);
        return f1529a.equalsIgnoreCase(context.getPackageName());
    }

    public static boolean i(Context context) {
        a(context);
        return f1529a.endsWith(CoreManager.i);
    }

    public static boolean j(Context context) {
        a(context);
        return f1529a.endsWith(CoreManager.j);
    }

    public static boolean k(Context context) {
        a(context);
        return f1529a.endsWith(CoreManager.k);
    }

    public static boolean l(Context context) {
        a(context);
        return f1529a.endsWith(CoreManager.l);
    }

    public static boolean m(Context context) {
        a(context);
        return f1529a.endsWith(CoreManager.m);
    }

    public static boolean n(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (Build.VERSION.SDK_INT < 21) {
            String packageName = context.getPackageName();
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                if (next.processName.startsWith(packageName) && (next.importance == 100 || next.importance == 200)) {
                    Log.e("CommonUtils", "Process:" + next.processName);
                    return true;
                }
            }
            return false;
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses2 = activityManager.getRunningAppProcesses();
        if (activityManager.getRunningTasks(1).size() == 0 || activityManager.getRunningTasks(1).get(0) == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo next2 : runningAppProcesses2) {
            if (next2.processName.equals("com.xiaomi.smarthome")) {
                try {
                    if (((Integer) ActivityManager.RunningAppProcessInfo.class.getField("processState").get(next2)).intValue() == 6) {
                        return true;
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return false;
    }

    public static int a(Context context, String str) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return 0;
        }
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (next.processName.equalsIgnoreCase(str)) {
                return next.pid;
            }
        }
        return 0;
    }
}
