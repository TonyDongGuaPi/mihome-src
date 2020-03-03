package com.xiaomi.smarthome.library.common.imagecache;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import java.io.Closeable;
import java.io.File;

public class ImageCacheUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18626a = 8192;

    private ImageCacheUtils() {
    }

    public static void a() {
        if (c()) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    @SuppressLint({"NewApi"})
    public static int a(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 12) {
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    @SuppressLint({"NewApi"})
    public static boolean b() {
        return Build.VERSION.SDK_INT < 9 || Environment.isExternalStorageRemovable();
    }

    @SuppressLint({"NewApi"})
    public static File a(Context context) {
        File externalCacheDir;
        if (d() && (externalCacheDir = context.getExternalCacheDir()) != null) {
            return externalCacheDir;
        }
        return new File(Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + context.getPackageName() + "/cache/"));
    }

    @SuppressLint({"NewApi"})
    public static long a(File file) {
        if (Build.VERSION.SDK_INT >= 9) {
            return file.getUsableSpace();
        }
        StatFs statFs = new StatFs(file.getPath());
        return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
    }

    public static int b(Context context) {
        return ((ActivityManager) context.getSystemService("activity")).getMemoryClass();
    }

    public static boolean c() {
        return Build.VERSION.SDK_INT < 8;
    }

    public static boolean d() {
        return Build.VERSION.SDK_INT >= 8;
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }
}
