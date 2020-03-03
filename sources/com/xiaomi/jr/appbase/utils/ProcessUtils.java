package com.xiaomi.jr.appbase.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import java.util.List;

public class ProcessUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10325a = "ProcessUtils";

    public static boolean a(Context context) {
        return context.getPackageName().equals(b(context));
    }

    public static String b(Context context) {
        int myPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        String str = null;
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                if (next.pid == myPid) {
                    str = next.processName;
                }
            }
        }
        return str;
    }
}
