package com.sina.weibo.sdk.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaMetadataRetriever;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtils {
    private static void a(String str, int i, int i2) throws IOException {
        Bitmap createBitmap;
        if (i <= 0) {
            throw new IllegalArgumentException("size must be greater than 0!");
        } else if (!c(str)) {
            if (str == null) {
                str = "null";
            }
            throw new FileNotFoundException(str);
        } else if (BitmapHelper.a(str)) {
            int i3 = i * 2;
            FileInputStream fileInputStream = new FileInputStream(str);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(fileInputStream, (Rect) null, options);
            try {
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            int i4 = 0;
            while (true) {
                if ((options.outWidth >> i4) <= i3 && (options.outHeight >> i4) <= i3) {
                    break;
                }
                i4++;
            }
            options.inSampleSize = (int) Math.pow(2.0d, (double) i4);
            options.inJustDecodeBounds = false;
            Bitmap a2 = a(str, options);
            if (a2 != null) {
                b(str);
                d(str);
                float width = ((float) i) / ((float) (a2.getWidth() > a2.getHeight() ? a2.getWidth() : a2.getHeight()));
                if (width < 1.0f) {
                    while (true) {
                        try {
                            createBitmap = Bitmap.createBitmap((int) (((float) a2.getWidth()) * width), (int) (((float) a2.getHeight()) * width), Bitmap.Config.ARGB_8888);
                            break;
                        } catch (OutOfMemoryError unused) {
                            System.gc();
                            double d = (double) width;
                            Double.isNaN(d);
                            width = (float) (d * 0.8d);
                        }
                    }
                    if (createBitmap == null) {
                        a2.recycle();
                    }
                    Canvas canvas = new Canvas(createBitmap);
                    Matrix matrix = new Matrix();
                    matrix.setScale(width, width);
                    canvas.drawBitmap(a2, matrix, new Paint());
                    a2.recycle();
                    a2 = createBitmap;
                }
                FileOutputStream fileOutputStream = new FileOutputStream(str);
                if (options.outMimeType == null || !options.outMimeType.contains("png")) {
                    a2.compress(Bitmap.CompressFormat.JPEG, i2, fileOutputStream);
                } else {
                    a2.compress(Bitmap.CompressFormat.PNG, i2, fileOutputStream);
                }
                try {
                    fileOutputStream.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                a2.recycle();
                return;
            }
            throw new IOException("Bitmap decode error!");
        } else {
            throw new IOException("");
        }
    }

    private static void b(String str, int i, int i2) throws IOException {
        if (i <= 0) {
            throw new IllegalArgumentException("size must be greater than 0!");
        } else if (!c(str)) {
            if (str == null) {
                str = "null";
            }
            throw new FileNotFoundException(str);
        } else if (BitmapHelper.a(str)) {
            FileInputStream fileInputStream = new FileInputStream(str);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(fileInputStream, (Rect) null, options);
            try {
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            int i3 = 0;
            while (true) {
                if ((options.outWidth >> i3) <= i && (options.outHeight >> i3) <= i) {
                    break;
                }
                i3++;
            }
            options.inSampleSize = (int) Math.pow(2.0d, (double) i3);
            options.inJustDecodeBounds = false;
            Bitmap a2 = a(str, options);
            if (a2 != null) {
                b(str);
                d(str);
                FileOutputStream fileOutputStream = new FileOutputStream(str);
                if (options.outMimeType == null || !options.outMimeType.contains("png")) {
                    a2.compress(Bitmap.CompressFormat.JPEG, i2, fileOutputStream);
                } else {
                    a2.compress(Bitmap.CompressFormat.PNG, i2, fileOutputStream);
                }
                try {
                    fileOutputStream.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                a2.recycle();
                return;
            }
            throw new IOException("Bitmap decode error!");
        } else {
            throw new IOException("");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0030, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0031, code lost:
        r6 = r5;
        r5 = r3;
        r3 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[ExcHandler: FileNotFoundException (unused java.io.FileNotFoundException), SYNTHETIC, Splitter:B:6:0x0013] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Bitmap a(java.lang.String r7, android.graphics.BitmapFactory.Options r8) {
        /*
            if (r8 != 0) goto L_0x000b
            android.graphics.BitmapFactory$Options r0 = new android.graphics.BitmapFactory$Options
            r0.<init>()
            r1 = 1
            r0.inSampleSize = r1
            goto L_0x000c
        L_0x000b:
            r0 = r8
        L_0x000c:
            r1 = 0
            r2 = 0
            r3 = r2
            r4 = r3
        L_0x0010:
            r5 = 5
            if (r1 >= r5) goto L_0x0049
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ OutOfMemoryError -> 0x0030, FileNotFoundException -> 0x0049 }
            r5.<init>(r7)     // Catch:{ OutOfMemoryError -> 0x0030, FileNotFoundException -> 0x0049 }
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeStream(r5, r2, r8)     // Catch:{ OutOfMemoryError -> 0x002a, FileNotFoundException -> 0x0049 }
            r5.close()     // Catch:{ IOException -> 0x0024 }
            goto L_0x0028
        L_0x0020:
            r3 = move-exception
            r6 = r5
            r5 = r4
            goto L_0x002e
        L_0x0024:
            r3 = move-exception
            r3.printStackTrace()     // Catch:{ OutOfMemoryError -> 0x0020, FileNotFoundException -> 0x0028 }
        L_0x0028:
            r3 = r4
            goto L_0x0049
        L_0x002a:
            r4 = move-exception
            r6 = r5
            r5 = r3
            r3 = r4
        L_0x002e:
            r4 = r6
            goto L_0x0034
        L_0x0030:
            r5 = move-exception
            r6 = r5
            r5 = r3
            r3 = r6
        L_0x0034:
            r3.printStackTrace()
            int r3 = r0.inSampleSize
            int r3 = r3 * 2
            r0.inSampleSize = r3
            r4.close()     // Catch:{ IOException -> 0x0041 }
            goto L_0x0045
        L_0x0041:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0045:
            int r1 = r1 + 1
            r3 = r5
            goto L_0x0010
        L_0x0049:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.utils.ImageUtils.a(java.lang.String, android.graphics.BitmapFactory$Options):android.graphics.Bitmap");
    }

    private static void a(File file) {
        if (file != null && file.exists() && !file.delete()) {
            throw new RuntimeException(file.getAbsolutePath() + " doesn't be deleted!");
        }
    }

    private static boolean b(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        int i = 1;
        while (!z && i <= 5 && file.isFile() && file.exists()) {
            z = file.delete();
            if (!z) {
                i++;
            }
        }
        return z;
    }

    private static boolean c(String str) {
        if (!TextUtils.isEmpty(str) && new File(str).exists()) {
            return true;
        }
        return false;
    }

    private static boolean b(File file) {
        File parentFile;
        if (file == null || (parentFile = file.getParentFile()) == null || parentFile.exists()) {
            return false;
        }
        if (file.exists() || file.mkdirs()) {
            return true;
        }
        return false;
    }

    @TargetApi(10)
    public static long a(String str) {
        if (!new File(str).exists()) {
            return 0;
        }
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(str);
            return Long.parseLong(mediaMetadataRetriever.extractMetadata(9));
        } catch (Exception unused) {
            return 0;
        }
    }

    private static void d(String str) {
        if (str != null) {
            File file = new File(str);
            if (!file.exists() && b(file)) {
                if (file.exists()) {
                    a(file);
                }
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
    }
}
