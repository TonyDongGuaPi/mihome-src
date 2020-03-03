package com.xiaomi.aiot.mibeacon.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.support.annotation.NonNull;
import java.util.List;

public class ProcessUtils {

    /* renamed from: a  reason: collision with root package name */
    Context f9989a;

    public ProcessUtils(@NonNull Context context) {
        this.f9989a = context;
    }

    public String a() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.f9989a.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (next.pid == c()) {
                return next.processName;
            }
        }
        return null;
    }

    public String b() {
        return this.f9989a.getApplicationContext().getPackageName();
    }

    public int c() {
        return Process.myPid();
    }

    public boolean d() {
        return b().equals(a());
    }
}
