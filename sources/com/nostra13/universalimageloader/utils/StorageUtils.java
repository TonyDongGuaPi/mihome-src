package com.nostra13.universalimageloader.utils;

import android.content.Context;
import android.os.Environment;
import com.alipay.android.phone.a.a.a;
import java.io.File;
import java.io.IOException;

public final class StorageUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8498a = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final String b = "uil-images";

    private StorageUtils() {
    }

    public static File a(Context context) {
        return a(context, true);
    }

    public static File a(Context context, boolean z) {
        String str;
        try {
            str = Environment.getExternalStorageState();
        } catch (NullPointerException unused) {
            str = "";
        } catch (IncompatibleClassChangeError unused2) {
            str = "";
        }
        File c = (!z || !"mounted".equals(str) || !d(context)) ? null : c(context);
        if (c == null) {
            c = context.getCacheDir();
        }
        if (c != null) {
            return c;
        }
        String str2 = "/data/data/" + context.getPackageName() + "/cache/";
        L.c("Can't define system cache directory! '%s' will be used.", str2);
        return new File(str2);
    }

    public static File b(Context context) {
        return a(context, b);
    }

    public static File a(Context context, String str) {
        File a2 = a(context);
        File file = new File(a2, str);
        return (file.exists() || file.mkdir()) ? file : a2;
    }

    public static File b(Context context, String str) {
        File file = (!"mounted".equals(Environment.getExternalStorageState()) || !d(context)) ? null : new File(Environment.getExternalStorageDirectory(), str);
        return (file == null || (!file.exists() && !file.mkdirs())) ? context.getCacheDir() : file;
    }

    public static File a(Context context, String str, boolean z) {
        File file = (!z || !"mounted".equals(Environment.getExternalStorageState()) || !d(context)) ? null : new File(Environment.getExternalStorageDirectory(), str);
        return (file == null || (!file.exists() && !file.mkdirs())) ? context.getCacheDir() : file;
    }

    private static File c(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), a.f813a), "data"), context.getPackageName()), "cache");
        if (!file.exists()) {
            if (!file.mkdirs()) {
                L.c("Unable to create external cache directory", new Object[0]);
                return null;
            }
            try {
                new File(file, ".nomedia").createNewFile();
            } catch (IOException unused) {
                L.b("Can't create \".nomedia\" file in application external cache directory", new Object[0]);
            }
        }
        return file;
    }

    private static boolean d(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }
}
