package org.xutils.common.util;

import android.os.Environment;
import android.os.StatFs;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.xutils.x;

public class FileUtil {
    private FileUtil() {
    }

    public static File a(String str) {
        File file;
        if (c().booleanValue()) {
            File externalCacheDir = x.b().getExternalCacheDir();
            if (externalCacheDir == null) {
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                file = new File(externalStorageDirectory, "Android/data/" + x.b().getPackageName() + "/cache/" + str);
            } else {
                file = new File(externalCacheDir, str);
            }
        } else {
            file = new File(x.b().getCacheDir(), str);
        }
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        return null;
    }

    public static boolean a() {
        return b() > 10485760;
    }

    public static long b() {
        if (!c().booleanValue()) {
            return 0;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    public static Boolean c() {
        return Boolean.valueOf(Environment.getExternalStorageState().equals("mounted"));
    }

    public static long a(File file) {
        long j = 0;
        if (!file.exists()) {
            return 0;
        }
        if (!file.isDirectory()) {
            return file.length();
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File a2 : listFiles) {
                j += a(a2);
            }
        }
        return j;
    }

    public static boolean a(String str, String str2) {
        FileOutputStream fileOutputStream;
        File file = new File(str);
        boolean z = false;
        if (!file.exists()) {
            return false;
        }
        File file2 = new File(str2);
        IOUtil.a(file2);
        File parentFile = file2.getParentFile();
        if (parentFile.exists() || parentFile.mkdirs()) {
            FileInputStream fileInputStream = null;
            try {
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    fileOutputStream = new FileOutputStream(file2);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = null;
                    fileInputStream = fileInputStream2;
                    IOUtil.a((Closeable) fileInputStream);
                    IOUtil.a((Closeable) fileOutputStream);
                    throw th;
                }
                try {
                    IOUtil.a((InputStream) fileInputStream2, (OutputStream) fileOutputStream);
                    z = true;
                    IOUtil.a((Closeable) fileInputStream2);
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = fileInputStream2;
                    IOUtil.a((Closeable) fileInputStream);
                    IOUtil.a((Closeable) fileOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                IOUtil.a((Closeable) fileInputStream);
                IOUtil.a((Closeable) fileOutputStream);
                throw th;
            }
            IOUtil.a((Closeable) fileOutputStream);
        }
        return z;
    }
}
