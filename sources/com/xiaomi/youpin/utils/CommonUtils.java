package com.xiaomi.youpin.utils;

import android.os.Environment;
import android.os.StatFs;

public abstract class CommonUtils {
    public static boolean a() {
        return Environment.getExternalStorageState().equals("removed");
    }

    public static boolean b() {
        return !Environment.getExternalStorageState().equals("mounted");
    }

    public static boolean c() {
        return e() <= 102400;
    }

    public static boolean d() {
        return !b() && !c() && !a();
    }

    public static long e() {
        if (b()) {
            return 0;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((long) statFs.getBlockSize()) * (((long) statFs.getAvailableBlocks()) - 4);
    }
}
