package com.mi.global.bbs.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import com.mi.global.bbs.service.killSelfService;

public class RestartAPPTool {
    public static void restartAPP(Context context, long j) {
        Intent intent = new Intent(context, killSelfService.class);
        intent.putExtra("PackageName", context.getPackageName());
        intent.putExtra("Delayed", j);
        context.startService(intent);
        Process.killProcess(Process.myPid());
    }

    public static void restartAPP(Context context) {
        restartAPP(context, 2000);
    }
}
