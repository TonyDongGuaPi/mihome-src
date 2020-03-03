package com.xiaomi.qrcode2;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.MultiFormatReader;
import com.xiaomi.zxing.PlanarYUVLuminanceSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

final class DecodeHandler extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public static volatile boolean f13001a = false;
    private static final String b = "DecodeHandler";
    private final ScanBarcodeActivity c;
    private final MultiFormatReader d = new MultiFormatReader();
    private boolean e = true;

    DecodeHandler(ScanBarcodeActivity scanBarcodeActivity, Map<DecodeHintType, Object> map) {
        this.d.a((Map<DecodeHintType, ?>) map);
        this.c = scanBarcodeActivity;
    }

    public void handleMessage(Message message) {
        if (this.e) {
            int i = message.what;
            if (i == 5) {
                a((byte[]) message.obj, message.arg1, message.arg2);
            } else if (i == 9) {
                this.e = false;
                Looper.myLooper().quit();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0070  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(byte[] r8, int r9, int r10) {
        /*
            r7 = this;
            long r0 = java.lang.System.currentTimeMillis()
            r2 = 0
            com.xiaomi.qrcode2.ScanBarcodeActivity r3 = r7.c     // Catch:{ Exception -> 0x0010 }
            com.xiaomi.qrcode2.camera.CameraManager r3 = r3.getCameraManager()     // Catch:{ Exception -> 0x0010 }
            com.xiaomi.zxing.PlanarYUVLuminanceSource r8 = r3.a((byte[]) r8, (int) r9, (int) r10)     // Catch:{ Exception -> 0x0010 }
            goto L_0x0015
        L_0x0010:
            r8 = move-exception
            r8.printStackTrace()
            r8 = r2
        L_0x0015:
            if (r8 == 0) goto L_0x0039
            com.xiaomi.zxing.BinaryBitmap r9 = new com.xiaomi.zxing.BinaryBitmap
            com.xiaomi.zxing.common.HybridBinarizer r10 = new com.xiaomi.zxing.common.HybridBinarizer
            r10.<init>(r8)
            r9.<init>(r10)
            com.xiaomi.zxing.MultiFormatReader r10 = r7.d     // Catch:{ ReaderException -> 0x0034, all -> 0x002d }
            com.xiaomi.zxing.Result r9 = r10.b(r9)     // Catch:{ ReaderException -> 0x0034, all -> 0x002d }
            com.xiaomi.zxing.MultiFormatReader r10 = r7.d
            r10.a()
            goto L_0x003a
        L_0x002d:
            r8 = move-exception
            com.xiaomi.zxing.MultiFormatReader r9 = r7.d
            r9.a()
            throw r8
        L_0x0034:
            com.xiaomi.zxing.MultiFormatReader r9 = r7.d
            r9.a()
        L_0x0039:
            r9 = r2
        L_0x003a:
            com.xiaomi.qrcode2.ScanBarcodeActivity r10 = r7.c
            android.os.Handler r10 = r10.getHandler()
            if (r9 == 0) goto L_0x0070
            long r2 = java.lang.System.currentTimeMillis()
            java.lang.String r4 = b
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Found barcode in "
            r5.append(r6)
            long r2 = r2 - r0
            r5.append(r2)
            java.lang.String r0 = " ms"
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            android.util.Log.d(r4, r0)
            if (r10 == 0) goto L_0x0084
            r0 = 7
            android.os.Message r9 = android.os.Message.obtain(r10, r0, r9)
            a(r8)
            r9.sendToTarget()
            goto L_0x0084
        L_0x0070:
            boolean r9 = f13001a
            if (r9 == 0) goto L_0x007a
            r9 = 0
            f13001a = r9
            a(r8)
        L_0x007a:
            if (r10 == 0) goto L_0x0084
            r8 = 6
            android.os.Message r8 = android.os.Message.obtain(r10, r8)
            r8.sendToTarget()
        L_0x0084:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.qrcode2.DecodeHandler.a(byte[], int, int):void");
    }

    private static void a(PlanarYUVLuminanceSource planarYUVLuminanceSource) {
        if (planarYUVLuminanceSource != null) {
            int[] i = planarYUVLuminanceSource.i();
            int j = planarYUVLuminanceSource.j();
            Bitmap createBitmap = Bitmap.createBitmap(i, 0, j, j, planarYUVLuminanceSource.k(), Bitmap.Config.ARGB_8888);
            long currentTimeMillis = System.currentTimeMillis();
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsoluteFile() + "/barcoder");
            file.mkdirs();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile() + "/" + currentTimeMillis + ".jpg");
                createBitmap.compress(Bitmap.CompressFormat.JPEG, 50, fileOutputStream);
                createBitmap.recycle();
                fileOutputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static void a(PlanarYUVLuminanceSource planarYUVLuminanceSource, Bundle bundle) {
        int[] i = planarYUVLuminanceSource.i();
        int j = planarYUVLuminanceSource.j();
        Bitmap createBitmap = Bitmap.createBitmap(i, 0, j, j, planarYUVLuminanceSource.k(), Bitmap.Config.ARGB_8888);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        bundle.putByteArray("barcode_bitmap", byteArrayOutputStream.toByteArray());
        bundle.putFloat("barcode_scaled_factor", ((float) j) / ((float) planarYUVLuminanceSource.g()));
    }
}
