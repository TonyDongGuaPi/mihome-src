package com.xiaomi.yp_pic_pick.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import java.io.IOException;

public class Utils {

    /* renamed from: a  reason: collision with root package name */
    public static final int f19525a = 3199;
    public static final int b = 1080;

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0020 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.io.File r3, java.io.File r4) {
        /*
            java.lang.String r0 = r3.getPath()
            r1 = 1080(0x438, float:1.513E-42)
            r2 = 3199(0xc7f, float:4.483E-42)
            android.graphics.Bitmap r0 = com.xiaomi.yp_pic_pick.utils.FileUtils.a((java.lang.String) r0, (int) r1, (int) r2)
            java.lang.String r3 = r3.getPath()     // Catch:{ Exception -> 0x0019, OutOfMemoryError -> 0x001d }
            android.net.Uri r3 = android.net.Uri.parse(r3)     // Catch:{ Exception -> 0x0019, OutOfMemoryError -> 0x001d }
            android.graphics.Bitmap r3 = a((android.graphics.Bitmap) r0, (android.net.Uri) r3)     // Catch:{ Exception -> 0x0019, OutOfMemoryError -> 0x001d }
            goto L_0x001e
        L_0x0019:
            r3 = move-exception
            r3.printStackTrace()
        L_0x001d:
            r3 = r0
        L_0x001e:
            if (r3 != 0) goto L_0x0022
            r3 = 0
            return r3
        L_0x0022:
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.JPEG
            r1 = 100
            r2 = 1
            boolean r3 = com.xiaomi.youpin.common.util.ImageUtils.a((android.graphics.Bitmap) r3, (java.io.File) r4, (android.graphics.Bitmap.CompressFormat) r0, (int) r1, (boolean) r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.yp_pic_pick.utils.Utils.a(java.io.File, java.io.File):boolean");
    }

    private static Bitmap a(Bitmap bitmap, Uri uri) throws IOException {
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
