package com.ximalaya.ting.android.opensdk.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.ximalaya.ting.android.opensdk.constants.ConstantsOpenSdk;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class BaseUtil {

    /* renamed from: a  reason: collision with root package name */
    static final Object[] f2251a = new Object[0];
    static final Class<?>[] b = new Class[0];
    public static String c;
    private static char d = 0;

    public static int a(String str) throws IllegalArgumentException {
        if (!TextUtils.isEmpty(str) && str.contains("-") && str.contains(":")) {
            String[] split = str.split("-");
            boolean z = split[0].split(":").length == 2;
            boolean z2 = split[0].split(":").length == 3;
            boolean z3 = split[0].split(":").length == 5;
            SimpleDateFormat simpleDateFormat = null;
            if (z2) {
                simpleDateFormat = new SimpleDateFormat("dd:HH:mm", Locale.getDefault());
            } else if (z3) {
                simpleDateFormat = new SimpleDateFormat("yy:MM:dd:HH:mm", Locale.getDefault());
            } else if (z) {
                simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            }
            if (simpleDateFormat == null) {
                return -2;
            }
            try {
                long time = simpleDateFormat.parse(simpleDateFormat.format(new Date(System.currentTimeMillis()))).getTime();
                long time2 = simpleDateFormat.parse(split[0]).getTime();
                if (split[1].contains("00:00") && z2) {
                    split[1] = split[1].split(":")[0] + ":23:59";
                } else if (split[1].contains("00:00") && z3) {
                    split[1] = split[1].split(":")[0] + ":" + split[1].split(":")[1] + ":" + split[1].split(":")[2] + ":23:59";
                } else if (split[1].contains("00:00") && z) {
                    split[1] = "23:59";
                }
                long time3 = simpleDateFormat.parse(split[1]).getTime();
                if (time >= time3) {
                    return -1;
                }
                if (time < time2 || time >= time3) {
                    return 1;
                }
                return 0;
            } catch (ParseException e) {
                e.printStackTrace();
                if (!ConstantsOpenSdk.b) {
                    return -2;
                }
                throw new IllegalArgumentException("Illegal Argument arg:" + str);
            }
        } else if (!ConstantsOpenSdk.b) {
            return -2;
        } else {
            throw new IllegalArgumentException("Illegal Argument arg:" + str);
        }
    }

    public static String[] a() {
        String[] strArr = new String[3];
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy:MM:dd");
        int i = -1;
        for (int i2 = 0; i2 < 3; i2++) {
            Calendar instance = Calendar.getInstance();
            instance.add(5, i);
            strArr[i2] = simpleDateFormat.format(instance.getTime());
            i++;
        }
        return strArr;
    }

    public static boolean b() {
        int intValue = Integer.valueOf(new SimpleDateFormat("HH", Locale.getDefault()).format(Long.valueOf(System.currentTimeMillis())).trim()).intValue();
        if (intValue == 22 || intValue == 23) {
            return true;
        }
        return intValue >= 0 && intValue < 6;
    }

    public static String a(Context context) {
        List<ActivityManager.RunningAppProcessInfo> list;
        if (context == null) {
            return "";
        }
        if (!TextUtils.isEmpty(c)) {
            return c;
        }
        int myPid = Process.myPid();
        try {
            list = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        if (list != null) {
            Iterator<ActivityManager.RunningAppProcessInfo> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ActivityManager.RunningAppProcessInfo next = it.next();
                if (next.pid == myPid) {
                    c = next.processName;
                    break;
                }
            }
        }
        if (TextUtils.isEmpty(c)) {
            c = e();
        }
        if (TextUtils.isEmpty(c)) {
            c = context.getPackageName();
        }
        return c;
    }

    private static String e() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/proc/" + Process.myPid() + "/cmdline")));
            String trim = bufferedReader.readLine().trim();
            bufferedReader.close();
            return trim;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    static Class<?>[] a(Class<?>[] clsArr) {
        if (clsArr == null || clsArr.length == 0) {
            return b;
        }
        return clsArr;
    }

    static Object[] a(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            return f2251a;
        }
        return objArr;
    }

    public static boolean b(Context context) {
        if (d()) {
            String a2 = a(context);
            return !TextUtils.isEmpty(a2) && a2.equals("com.ximalaya.ting.android");
        }
        String a3 = a(context);
        return !TextUtils.isEmpty(a3) && a3.equals(context.getPackageName());
    }

    public static boolean a(Context context, String str) {
        List<ActivityManager.RunningAppProcessInfo> list;
        try {
            list = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        if (list == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : list) {
            if (runningAppProcessInfo.processName.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean b(Context context, String str) {
        ActivityManager activityManager;
        if (context == null || str == null || (activityManager = (ActivityManager) context.getSystemService("activity")) == null) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            List<ActivityManager.AppTask> appTasks = activityManager.getAppTasks();
            if (appTasks == null || appTasks.isEmpty()) {
                return false;
            }
            for (ActivityManager.AppTask next : appTasks) {
                Logger.a((Object) "process RecentTaskInfo processName0:" + next.getTaskInfo().baseIntent.getComponent().getPackageName());
                ActivityManager.RecentTaskInfo recentTaskInfo = null;
                if (next != null) {
                    recentTaskInfo = next.getTaskInfo();
                }
                if (recentTaskInfo != null && recentTaskInfo.baseIntent != null && recentTaskInfo.baseIntent.getComponent() != null && str.equals(recentTaskInfo.baseIntent.getComponent().getPackageName())) {
                    return true;
                }
            }
            return false;
        }
        List<ActivityManager.RecentTaskInfo> recentTasks = activityManager.getRecentTasks(100, 2);
        if (recentTasks == null || recentTasks.isEmpty()) {
            return false;
        }
        for (int i = 0; i < recentTasks.size(); i++) {
            ActivityManager.RecentTaskInfo recentTaskInfo2 = recentTasks.get(i);
            Logger.a((Object) "process RecentTaskInfo processName1:" + recentTaskInfo2.baseIntent.getComponent().getPackageName());
            if (recentTaskInfo2.baseIntent != null && recentTaskInfo2.baseIntent.getComponent() != null && str.equals(recentTaskInfo2.baseIntent.getComponent().getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x001c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean c(android.content.Context r3, java.lang.String r4) {
        /*
            r0 = 0
            if (r3 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = "activity"
            java.lang.Object r3 = r3.getSystemService(r1)
            android.app.ActivityManager r3 = (android.app.ActivityManager) r3
            r1 = 100
            java.util.List r3 = r3.getRunningTasks(r1)
            java.util.Iterator r3 = r3.iterator()
        L_0x0016:
            boolean r1 = r3.hasNext()
            if (r1 == 0) goto L_0x003c
            java.lang.Object r1 = r3.next()
            android.app.ActivityManager$RunningTaskInfo r1 = (android.app.ActivityManager.RunningTaskInfo) r1
            android.content.ComponentName r2 = r1.topActivity
            java.lang.String r2 = r2.toString()
            boolean r2 = r2.equals(r4)
            if (r2 != 0) goto L_0x003a
            android.content.ComponentName r1 = r1.baseActivity
            java.lang.String r1 = r1.toString()
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x0016
        L_0x003a:
            r3 = 1
            return r3
        L_0x003c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.util.BaseUtil.c(android.content.Context, java.lang.String):boolean");
    }

    public static boolean c(Context context) {
        String a2 = a(context);
        return !TextUtils.isEmpty(a2) && a2.contains("player");
    }

    public static boolean d(Context context) {
        String a2 = a(context);
        return !TextUtils.isEmpty(a2) && a2.contains("pushservice");
    }

    public static String c() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null) {
            return "无堆栈...";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (stringBuffer.length() > 0) {
                stringBuffer.append(" <- ");
                stringBuffer.append(System.getProperty("line.separator"));
            }
            stringBuffer.append(MessageFormat.format("{0}.{1}() {2}", new Object[]{stackTraceElement.getClassName(), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber())}));
        }
        return stringBuffer.toString();
    }

    public static boolean d() {
        if (d == 0) {
            try {
                Class.forName(ConstantsOpenSdk.K);
                d = 1;
            } catch (ClassNotFoundException unused) {
                d = 2;
            } catch (Exception unused2) {
                d = 2;
            }
        }
        if (d != 1 && d == 2) {
            return false;
        }
        return true;
    }
}
