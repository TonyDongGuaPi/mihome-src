package com.xiaomi.smarthome.core.server.internal.util;

import android.app.ActivityManager;
import android.content.Context;
import java.util.List;

public class ProcUtil {
    public static boolean a(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (next.processName.equalsIgnoreCase("com.xiaomi.smarthome") && (next.importance == 100 || next.importance == 200)) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(Context context, int i) {
        for (ActivityManager.RunningAppProcessInfo next : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (next.pid == i && "com.xiaomi.smarthome".equals(next.processName)) {
                return true;
            }
        }
        return false;
    }
}
