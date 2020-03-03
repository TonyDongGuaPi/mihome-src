package com.mics.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import com.mics.core.MiCS;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

public class FileUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final int f7776a = 3199;
    private static final int b = 1080;

    public static File a(String str) {
        return new File(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String r4, java.lang.String r5) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r5)
            long r2 = java.lang.System.currentTimeMillis()
            r1.append(r2)
            java.lang.String r5 = r1.toString()
            java.lang.String r5 = d(r5)
            r0.append(r5)
            java.lang.String r5 = ".jpg"
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = c()
            r0.append(r1)
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            r0 = 1080(0x438, float:1.513E-42)
            r1 = 3199(0xc7f, float:4.483E-42)
            android.graphics.Bitmap r0 = a((java.lang.String) r4, (int) r0, (int) r1)
            android.net.Uri r4 = android.net.Uri.parse(r4)     // Catch:{ Exception -> 0x004c, OutOfMemoryError -> 0x0050 }
            android.graphics.Bitmap r4 = a((android.graphics.Bitmap) r0, (android.net.Uri) r4)     // Catch:{ Exception -> 0x004c, OutOfMemoryError -> 0x0050 }
            goto L_0x0051
        L_0x004c:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0050:
            r4 = r0
        L_0x0051:
            java.io.File r0 = new java.io.File
            r0.<init>(r5)
            a((java.io.File) r0, (android.graphics.Bitmap) r4)
            if (r4 == 0) goto L_0x005e
            r4.recycle()
        L_0x005e:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mics.util.FileUtils.a(java.lang.String, java.lang.String):java.lang.String");
    }

    static void b(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    static {
        b(a());
        b(c());
    }

    static String a() {
        Context h = MiCS.a().h();
        File externalCacheDir = h.getExternalCacheDir();
        if (!"mounted".equals(Environment.getExternalStorageState()) || externalCacheDir == null) {
            return h.getCacheDir().toString();
        }
        return externalCacheDir.getAbsolutePath();
    }

    public static String b() {
        Context h = MiCS.a().h();
        if ("mounted".equals(Environment.getExternalStorageState())) {
            return Environment.getExternalStorageDirectory().getPath();
        }
        return h.getCacheDir().toString();
    }

    public static String c() {
        return a() + "/mics_image_cache/";
    }

    public static void c(String str) {
        File[] listFiles;
        File file = new File(str);
        if (file.exists() && (listFiles = file.listFiles()) != null) {
            for (File delete : listFiles) {
                delete.delete();
            }
        }
    }

    public static void d() {
        c(c());
    }

    public static String a(Context context) {
        StringBuilder sb = new StringBuilder(6);
        double a2 = (double) a(new File(c()));
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.applyPattern("0.00");
        if (a2 < 1048576.0d) {
            Double.isNaN(a2);
            sb.append(decimalFormat.format(a2 / 1024.0d));
            sb.append("KB");
        } else if (a2 < 1.073741824E9d) {
            Double.isNaN(a2);
            sb.append(decimalFormat.format(a2 / 1048576.0d));
            sb.append("MB");
        } else {
            Double.isNaN(a2);
            sb.append(decimalFormat.format(a2 / 1.073741824E9d));
            sb.append(ServerCompact.i);
        }
        return sb.toString();
    }

    public static long a(File file) {
        long j;
        File[] listFiles = file.listFiles();
        long j2 = 0;
        if (listFiles == null) {
            return 0;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                j = a(file2);
            } else {
                j = file2.length();
            }
            j2 += j;
        }
        return j2;
    }

    public static void a(Context context, Uri uri) {
        context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", uri));
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x002d A[SYNTHETIC, Splitter:B:21:0x002d] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0037 A[SYNTHETIC, Splitter:B:27:0x0037] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0042 A[SYNTHETIC, Splitter:B:32:0x0042] */
    /* JADX WARNING: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:24:0x0032=Splitter:B:24:0x0032, B:18:0x0028=Splitter:B:18:0x0028} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.io.File r4, android.graphics.Bitmap r5) {
        /*
            if (r5 != 0) goto L_0x0003
            return
        L_0x0003:
            r0 = 0
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ FileNotFoundException -> 0x0031, IOException -> 0x0027 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0031, IOException -> 0x0027 }
            r3.<init>(r4)     // Catch:{ FileNotFoundException -> 0x0031, IOException -> 0x0027 }
            r2.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0031, IOException -> 0x0027 }
            r4 = 90
            r5.compress(r1, r4, r2)     // Catch:{ FileNotFoundException -> 0x0022, IOException -> 0x001f, all -> 0x001c }
            r2.flush()     // Catch:{ FileNotFoundException -> 0x0022, IOException -> 0x001f, all -> 0x001c }
            r2.close()     // Catch:{ IOException -> 0x003b }
            goto L_0x003f
        L_0x001c:
            r4 = move-exception
            r0 = r2
            goto L_0x0040
        L_0x001f:
            r4 = move-exception
            r0 = r2
            goto L_0x0028
        L_0x0022:
            r4 = move-exception
            r0 = r2
            goto L_0x0032
        L_0x0025:
            r4 = move-exception
            goto L_0x0040
        L_0x0027:
            r4 = move-exception
        L_0x0028:
            r4.printStackTrace()     // Catch:{ all -> 0x0025 }
            if (r0 == 0) goto L_0x003f
            r0.close()     // Catch:{ IOException -> 0x003b }
            goto L_0x003f
        L_0x0031:
            r4 = move-exception
        L_0x0032:
            r4.printStackTrace()     // Catch:{ all -> 0x0025 }
            if (r0 == 0) goto L_0x003f
            r0.close()     // Catch:{ IOException -> 0x003b }
            goto L_0x003f
        L_0x003b:
            r4 = move-exception
            r4.printStackTrace()
        L_0x003f:
            return
        L_0x0040:
            if (r0 == 0) goto L_0x004a
            r0.close()     // Catch:{ IOException -> 0x0046 }
            goto L_0x004a
        L_0x0046:
            r5 = move-exception
            r5.printStackTrace()
        L_0x004a:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mics.util.FileUtils.a(java.io.File, android.graphics.Bitmap):void");
    }

    public static Bitmap a(String str, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        if (options.mCancel || options.outWidth == -1 || options.outHeight == -1) {
            return null;
        }
        options.inSampleSize = a(options, i, i2);
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError unused) {
            return null;
        }
    }

    private static int a(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        int i5 = i3 / i;
        if (i5 >= i4 / i2) {
            if (i5 > 0) {
                while (i3 / i5 > i) {
                    i5++;
                }
            }
        } else if (i5 > 0) {
            while (i4 / i5 > i2) {
                i5++;
            }
        }
        if (i5 > 0) {
            while (i3 / i5 > i) {
                i5++;
            }
        }
        return i5;
    }

    public static String d(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(e(str));
            return String.format("%1$032X", new Object[]{new BigInteger(1, instance.digest())});
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] e(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        }
    }

    public static Bitmap a(Bitmap bitmap, Uri uri) throws IOException {
        int attributeInt = new ExifInterface(uri.getPath()).getAttributeInt(android.support.media.ExifInterface.TAG_ORIENTATION, 1);
        if (attributeInt == 3) {
            return a(bitmap, 180);
        }
        if (attributeInt == 6) {
            return a(bitmap, 90);
        }
        if (attributeInt != 8) {
            return bitmap;
        }
        return a(bitmap, 270);
    }

    private static Bitmap a(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (bitmap != createBitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }
}
