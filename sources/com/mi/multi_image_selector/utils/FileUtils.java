package com.mi.multi_image_selector.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.alipay.android.phone.a.a.a;
import java.io.File;
import java.io.IOException;

public class FileUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7397a = "IMG_";
    private static final String b = ".jpg";
    private static final String c = "android.permission.WRITE_EXTERNAL_STORAGE";

    public static File a(Context context) throws IOException {
        File file;
        if (TextUtils.equals(Environment.getExternalStorageState(), "mounted")) {
            file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            if (!file.exists()) {
                file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM + "/Camera");
                if (!file.exists()) {
                    file = a(context, true);
                }
            }
        } else {
            file = a(context, true);
        }
        return File.createTempFile(f7397a, b, file);
    }

    public static File b(Context context) {
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
        File c2 = (!z || !"mounted".equals(str) || !d(context)) ? null : c(context);
        if (c2 == null) {
            c2 = context.getCacheDir();
        }
        if (c2 != null) {
            return c2;
        }
        return new File("/data/data/" + context.getPackageName() + "/cache/");
    }

    public static File a(Context context, String str) {
        File b2 = b(context);
        File file = new File(b2, str);
        return (file.exists() || file.mkdir()) ? file : b2;
    }

    private static File c(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), a.f813a), "data"), context.getPackageName()), "cache");
        if (!file.exists()) {
            if (!file.mkdirs()) {
                return null;
            }
            try {
                new File(file, ".nomedia").createNewFile();
            } catch (IOException unused) {
            }
        }
        return file;
    }

    private static boolean d(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }
}
