package com.mi.blockcanary.internal;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import com.mi.blockcanary.BlockCanaryInternals;
import java.util.List;

public class ProcessUtils {

    /* renamed from: a  reason: collision with root package name */
    private static volatile String f6755a;
    private static final Object b = new Object();

    private ProcessUtils() {
        throw new InstantiationError("Must not instantiate this class");
    }

    public static String a() {
        if (f6755a != null) {
            return f6755a;
        }
        synchronized (b) {
            if (f6755a != null) {
                String str = f6755a;
                return str;
            }
            f6755a = a(BlockCanaryInternals.c().j());
            String str2 = f6755a;
            return str2;
        }
    }

    private static String a(Context context) {
        int myPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null || runningAppProcesses.isEmpty()) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (next != null && next.pid == myPid) {
                return next.processName;
            }
        }
        return null;
    }
}
