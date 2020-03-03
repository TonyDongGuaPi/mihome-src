package com.xiaomi.smarthome.framework.navigate;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;

public class NavigateUtil {
    public static boolean a() {
        for (ActivityManager.RunningTaskInfo runningTaskInfo : ((ActivityManager) SHApplication.getAppContext().getSystemService("activity")).getRunningTasks(50)) {
            if (runningTaskInfo.baseActivity.equals(new ComponentName(SHApplication.getAppContext().getPackageName(), SmartHomeMainActivity.class.getName()))) {
                return true;
            }
        }
        return false;
    }

    public static void a(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(SHApplication.getAppContext(), AccountConflictActivity.class);
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        SHApplication.getAppContext().startActivity(intent);
    }
}
