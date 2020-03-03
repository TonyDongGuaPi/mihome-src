package com.xiaomi.smarthome.library.common.util;

import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import java.io.File;

public class SDCardUtils {
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
        File externalStorageDirectory;
        if (b() || (externalStorageDirectory = Environment.getExternalStorageDirectory()) == null || TextUtils.isEmpty(externalStorageDirectory.getPath())) {
            return 0;
        }
        StatFs statFs = new StatFs(externalStorageDirectory.getPath());
        return ((long) statFs.getBlockSize()) * (((long) statFs.getAvailableBlocks()) - 4);
    }
}
