package com.xiaomi.youpin.common.util;

import android.os.Environment;
import com.xiaomi.miot.support.monitor.core.tasks.TaskConfig;
import java.io.File;

public final class CleanUtils {
    private CleanUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean a() {
        return b(Utils.a().getCacheDir());
    }

    public static boolean b() {
        return b(Utils.a().getFilesDir());
    }

    public static boolean c() {
        return b(new File(Utils.a().getFilesDir().getParent(), TaskConfig.v));
    }

    public static boolean a(String str) {
        return Utils.a().deleteDatabase(str);
    }

    public static boolean d() {
        return b(new File(Utils.a().getFilesDir().getParent(), "shared_prefs"));
    }

    public static boolean e() {
        return "mounted".equals(Environment.getExternalStorageState()) && b(Utils.a().getExternalCacheDir());
    }

    public static boolean b(String str) {
        return c(str);
    }

    public static boolean a(File file) {
        return b(file);
    }

    public static boolean c(String str) {
        return b(d(str));
    }

    private static boolean b(File file) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                if (file2.isFile()) {
                    if (!file2.delete()) {
                        return false;
                    }
                } else if (file2.isDirectory() && !c(file2)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean c(File file) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                if (file2.isFile()) {
                    if (!file2.delete()) {
                        return false;
                    }
                } else if (file2.isDirectory() && !c(file2)) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    private static File d(String str) {
        if (e(str)) {
            return null;
        }
        return new File(str);
    }

    private static boolean e(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
