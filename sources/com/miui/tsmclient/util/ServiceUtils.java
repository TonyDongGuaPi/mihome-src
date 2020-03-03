package com.miui.tsmclient.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.text.TextUtils;
import com.mijia.model.property.CameraPropertyBase;
import java.util.List;

public class ServiceUtils {
    private static final String KEY_START_FOREGROUND_SERVICE_FLAG = "key_start_foreground_service";

    private ServiceUtils() {
    }

    public static void startService(Context context, Intent intent) {
        if (intent != null) {
            try {
                if (isInWhiteList(context)) {
                    context.startService(intent);
                } else if (needStartInForeground(context, intent)) {
                    intent.putExtra(KEY_START_FOREGROUND_SERVICE_FLAG, true);
                    context.startForegroundService(intent);
                } else {
                    context.startService(intent);
                }
            } catch (Exception e) {
                LogUtils.e("startService failed", e);
            }
        }
    }

    public static boolean isStartByForeground(Intent intent) {
        return intent != null && intent.getBooleanExtra(KEY_START_FOREGROUND_SERVICE_FLAG, false);
    }

    private static boolean isAppRunInBackground(Context context, Intent intent) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return true;
        }
        String packageName = TextUtils.isEmpty(intent.getPackage()) ? context.getPackageName() : intent.getPackage();
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (TextUtils.equals(next.processName, packageName) && next.importance == 100) {
                return false;
            }
        }
        return true;
    }

    private static boolean isInWhiteList(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        PowerManager powerManager = (PowerManager) context.getSystemService(CameraPropertyBase.l);
        if (powerManager == null || !powerManager.isIgnoringBatteryOptimizations(context.getPackageName())) {
            return false;
        }
        return true;
    }

    private static boolean needStartInForeground(Context context, Intent intent) {
        return Build.VERSION.SDK_INT >= 26 && isAppRunInBackground(context, intent);
    }
}
