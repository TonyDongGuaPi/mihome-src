package com.seek.biscuit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {

    /* renamed from: a  reason: collision with root package name */
    static ArrayList<String> f8812a = new ArrayList<>();
    static String b = ".jpg";
    static String c = ".jpeg";
    static String d = ".png";
    static String e = ".webp";
    static String f = ".gif";
    static float g = 1080.0f;
    static float h = 1280.0f;
    static float i = 1000.0f;
    static float j = 640.0f;
    static int k = 66;
    static int l = 60;
    static int m = 82;
    static int n = 88;
    static int o = 94;
    static boolean p = true;
    private static final String q = "biscuit_cache";

    static {
        f8812a.add(b);
        f8812a.add(c);
        f8812a.add(d);
        f8812a.add(e);
        f8812a.add(f);
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int lastIndexOf = str.lastIndexOf(".");
        int length = str.length();
        if (lastIndexOf == -1) {
            return false;
        }
        return f8812a.contains(str.substring(lastIndexOf, length).toLowerCase());
    }

    public static String a(Context context) {
        File file = new File(context.getExternalCacheDir(), q);
        if (!file.exists()) {
            file.mkdir();
        }
        return file.getAbsolutePath();
    }

    public static void b(Context context) {
        b(a(context));
    }

    public static void b(String str) {
        File[] listFiles = new File(str).listFiles();
        if (listFiles.length > 0) {
            for (File delete : listFiles) {
                delete.delete();
            }
        }
    }

    public static int c(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt(android.support.media.ExifInterface.TAG_ORIENTATION, 1);
            if (attributeInt == 3) {
                return 180;
            }
            if (attributeInt == 6) {
                return 90;
            }
            if (attributeInt != 8) {
                return 0;
            }
            return 270;
        } catch (IOException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static Bitmap a(Bitmap bitmap, int i2) {
        if (bitmap == null) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i2);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    static void a(String str, String str2) {
        if (p) {
            Log.e(str, str2);
        }
    }

    static int c(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        float f2 = displayMetrics.density;
        if (f2 > 3.0f) {
            return l;
        }
        if (f2 > 2.5f && f2 <= 3.0f) {
            return k;
        }
        if (f2 > 2.0f && f2 <= 2.5f) {
            return m;
        }
        if (f2 <= 1.5f || f2 > 2.0f) {
            return o;
        }
        return n;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0051  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(@android.support.annotation.NonNull java.lang.String r10, @android.support.annotation.NonNull java.lang.String r11) throws java.io.IOException {
        /*
            boolean r0 = r10.equalsIgnoreCase(r11)
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0048 }
            java.io.File r2 = new java.io.File     // Catch:{ all -> 0x0048 }
            r2.<init>(r10)     // Catch:{ all -> 0x0048 }
            r1.<init>(r2)     // Catch:{ all -> 0x0048 }
            java.nio.channels.FileChannel r10 = r1.getChannel()     // Catch:{ all -> 0x0048 }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0042 }
            java.io.File r2 = new java.io.File     // Catch:{ all -> 0x0042 }
            r2.<init>(r11)     // Catch:{ all -> 0x0042 }
            r1.<init>(r2)     // Catch:{ all -> 0x0042 }
            java.nio.channels.FileChannel r11 = r1.getChannel()     // Catch:{ all -> 0x0042 }
            r4 = 0
            long r6 = r10.size()     // Catch:{ all -> 0x003d }
            r3 = r10
            r8 = r11
            r3.transferTo(r4, r6, r8)     // Catch:{ all -> 0x003d }
            r10.close()     // Catch:{ all -> 0x003d }
            if (r10 == 0) goto L_0x0037
            r10.close()
        L_0x0037:
            if (r11 == 0) goto L_0x003c
            r11.close()
        L_0x003c:
            return
        L_0x003d:
            r0 = move-exception
            r9 = r0
            r0 = r10
            r10 = r9
            goto L_0x004a
        L_0x0042:
            r11 = move-exception
            r9 = r0
            r0 = r10
            r10 = r11
            r11 = r9
            goto L_0x004a
        L_0x0048:
            r10 = move-exception
            r11 = r0
        L_0x004a:
            if (r0 == 0) goto L_0x004f
            r0.close()
        L_0x004f:
            if (r11 == 0) goto L_0x0054
            r11.close()
        L_0x0054:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.seek.biscuit.Utils.b(java.lang.String, java.lang.String):void");
    }
}
