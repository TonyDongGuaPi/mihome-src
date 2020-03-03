package com.ximalaya.ting.android.sdkdownloader.util;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.NonNull;
import com.ximalaya.ting.android.sdkdownloader.XmDownloadManager;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {

    /* renamed from: a  reason: collision with root package name */
    static final int f2376a = -1;

    private FileUtil() {
    }

    public static File a(String str) {
        File file;
        if (c().booleanValue()) {
            File externalCacheDir = XmDownloadManager.b().c().getExternalCacheDir();
            if (externalCacheDir == null) {
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                file = new File(externalStorageDirectory, "Android/data/" + XmDownloadManager.b().c().getPackageName() + "/cache/" + str);
            } else {
                file = new File(externalCacheDir, str);
            }
        } else {
            file = new File(XmDownloadManager.b().c().getCacheDir(), str);
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

    public static boolean a(String str, String str2) throws Throwable {
        FileOutputStream fileOutputStream;
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        File file2 = new File(str2);
        IOUtil.a(file2);
        File parentFile = file2.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            return false;
        }
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
                IOUtil.a((Closeable) fileInputStream2);
                IOUtil.a((Closeable) fileOutputStream);
                return true;
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
    }

    public static boolean a(long j, String str) {
        long b = b(new File(str));
        return b > 0 && j < b;
    }

    public static long b(File file) {
        long j;
        long j2;
        if (file != null) {
            try {
                if (file.exists()) {
                    StatFs statFs = new StatFs(file.getPath());
                    if (Build.VERSION.SDK_INT >= 18) {
                        j = statFs.getBlockSizeLong();
                    } else {
                        j = (long) statFs.getBlockSize();
                    }
                    if (Build.VERSION.SDK_INT >= 18) {
                        j2 = statFs.getAvailableBlocksLong();
                    } else {
                        j2 = (long) statFs.getAvailableBlocks();
                    }
                    return j2 * j;
                }
            } catch (Exception unused) {
                return -1;
            }
        }
        return -1;
    }

    public static File b(String str) {
        return c(new File(str));
    }

    @NonNull
    private static File c(File file) {
        if (!file.exists()) {
            try {
                File parentFile = file.getParentFile();
                if (parentFile != null && !parentFile.exists()) {
                    parentFile.mkdirs();
                }
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
