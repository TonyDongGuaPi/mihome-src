package com.tencent.smtt.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;

public class v {

    /* renamed from: a  reason: collision with root package name */
    private static File f9220a;

    public static long a() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
    }

    @TargetApi(9)
    public static boolean a(Context context) {
        File dir;
        if (context == null) {
            return false;
        }
        if (f9220a != null) {
            return true;
        }
        try {
            if (context.getApplicationInfo().processName.contains("com.tencent.mm") && (dir = context.getDir("tbs", 0)) != null) {
                if (dir.isDirectory()) {
                    File file = new File(dir, "share");
                    if (!file.isDirectory() && !file.mkdir()) {
                        return false;
                    }
                    f9220a = file;
                    file.setExecutable(true, false);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
