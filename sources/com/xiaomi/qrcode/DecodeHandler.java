package com.xiaomi.qrcode;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.xiaomi.smarthome.R;
import java.io.ByteArrayOutputStream;
import java.util.Map;

final class DecodeHandler extends Handler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12958a = "DecodeHandler";
    private final ScanBarcodeActivity b;
    private final MultiFormatReader c = new MultiFormatReader();
    private boolean d = true;

    DecodeHandler(ScanBarcodeActivity scanBarcodeActivity, Map<DecodeHintType, Object> map) {
        this.c.setHints(map);
        this.b = scanBarcodeActivity;
    }

    public void handleMessage(Message message) {
        if (this.d) {
            int i = message.what;
            if (i == R.id.decode) {
                a((byte[]) message.obj, message.arg1, message.arg2);
            } else if (i == R.id.quit) {
                this.d = false;
                Looper.myLooper().quit();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0068  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(byte[] r7, int r8, int r9) {
        /*
            r6 = this;
            long r0 = java.lang.System.currentTimeMillis()
            com.xiaomi.qrcode.ScanBarcodeActivity r2 = r6.b
            com.xiaomi.qrcode.camera.CameraManager r2 = r2.getCameraManager()
            com.google.zxing.PlanarYUVLuminanceSource r7 = r2.a((byte[]) r7, (int) r8, (int) r9)
            if (r7 == 0) goto L_0x0032
            com.google.zxing.BinaryBitmap r8 = new com.google.zxing.BinaryBitmap
            com.google.zxing.common.HybridBinarizer r9 = new com.google.zxing.common.HybridBinarizer
            r9.<init>(r7)
            r8.<init>(r9)
            com.google.zxing.MultiFormatReader r7 = r6.c     // Catch:{ ReaderException -> 0x002d, all -> 0x0026 }
            com.google.zxing.Result r7 = r7.decodeWithState(r8)     // Catch:{ ReaderException -> 0x002d, all -> 0x0026 }
            com.google.zxing.MultiFormatReader r8 = r6.c
            r8.reset()
            goto L_0x0033
        L_0x0026:
            r7 = move-exception
            com.google.zxing.MultiFormatReader r8 = r6.c
            r8.reset()
            throw r7
        L_0x002d:
            com.google.zxing.MultiFormatReader r7 = r6.c
            r7.reset()
        L_0x0032:
            r7 = 0
        L_0x0033:
            com.xiaomi.qrcode.ScanBarcodeActivity r8 = r6.b
            android.os.Handler r8 = r8.getHandler()
            if (r7 == 0) goto L_0x0068
            long r2 = java.lang.System.currentTimeMillis()
            java.lang.String r9 = f12958a
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Found barcode in "
            r4.append(r5)
            long r2 = r2 - r0
            r4.append(r2)
            java.lang.String r0 = " ms"
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            android.util.Log.d(r9, r0)
            if (r8 == 0) goto L_0x0074
            r9 = 2131428690(0x7f0b0552, float:1.8479032E38)
            android.os.Message r7 = android.os.Message.obtain(r8, r9, r7)
            r7.sendToTarget()
            goto L_0x0074
        L_0x0068:
            if (r8 == 0) goto L_0x0074
            r7 = 2131428689(0x7f0b0551, float:1.847903E38)
            android.os.Message r7 = android.os.Message.obtain(r8, r7)
            r7.sendToTarget()
        L_0x0074:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.qrcode.DecodeHandler.a(byte[], int, int):void");
    }

    private static void a(PlanarYUVLuminanceSource planarYUVLuminanceSource, Bundle bundle) {
        int[] renderThumbnail = planarYUVLuminanceSource.renderThumbnail();
        int thumbnailWidth = planarYUVLuminanceSource.getThumbnailWidth();
        Bitmap createBitmap = Bitmap.createBitmap(renderThumbnail, 0, thumbnailWidth, thumbnailWidth, planarYUVLuminanceSource.getThumbnailHeight(), Bitmap.Config.ARGB_8888);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        bundle.putByteArray("barcode_bitmap", byteArrayOutputStream.toByteArray());
        bundle.putFloat("barcode_scaled_factor", ((float) thumbnailWidth) / ((float) planarYUVLuminanceSource.getWidth()));
    }
}
