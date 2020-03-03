package cn.fraudmetrix.octopus.aspirit.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class l {
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0041 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x003c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.graphics.Bitmap r10) {
        /*
            android.graphics.Bitmap r0 = b(r10)
            if (r0 == 0) goto L_0x0039
            int r10 = r0.getWidth()
            int r8 = r0.getHeight()
            int r1 = r10 * r8
            int[] r9 = new int[r1]
            r2 = 0
            r4 = 0
            r5 = 0
            r1 = r9
            r3 = r10
            r6 = r10
            r7 = r8
            r0.getPixels(r1, r2, r3, r4, r5, r6, r7)
            com.google.zxing.RGBLuminanceSource r0 = new com.google.zxing.RGBLuminanceSource
            r0.<init>(r10, r8, r9)
            com.google.zxing.BinaryBitmap r10 = new com.google.zxing.BinaryBitmap
            com.google.zxing.common.HybridBinarizer r1 = new com.google.zxing.common.HybridBinarizer
            r1.<init>(r0)
            r10.<init>(r1)
            com.google.zxing.qrcode.QRCodeReader r0 = new com.google.zxing.qrcode.QRCodeReader
            r0.<init>()
            com.google.zxing.Result r10 = r0.decode(r10)     // Catch:{ ChecksumException | FormatException | NotFoundException -> 0x0035 }
            goto L_0x003a
        L_0x0035:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0039:
            r10 = 0
        L_0x003a:
            if (r10 == 0) goto L_0x0041
            java.lang.String r10 = r10.getText()
            goto L_0x0043
        L_0x0041:
            java.lang.String r10 = ""
        L_0x0043:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.fraudmetrix.octopus.aspirit.utils.l.a(android.graphics.Bitmap):java.lang.String");
    }

    private static Bitmap b(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        try {
            int width = (bitmap.getWidth() * bitmap.getHeight()) / 160000;
            if (width <= 1) {
                return bitmap;
            }
            Matrix matrix = new Matrix();
            double d = (double) width;
            matrix.postScale((float) (1.0d / Math.sqrt(d)), (float) (1.0d / Math.sqrt(d)));
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
