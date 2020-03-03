package me.everything.webp;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;

public class WebPDecoder {

    /* renamed from: a  reason: collision with root package name */
    private static WebPDecoder f2920a;

    public static native byte[] decodeRGBAnative(byte[] bArr, long j, int[] iArr, int[] iArr2);

    private WebPDecoder() {
        System.loadLibrary("webp_evme");
    }

    public static WebPDecoder a() {
        if (f2920a == null) {
            synchronized (WebPDecoder.class) {
                if (f2920a == null) {
                    f2920a = new WebPDecoder();
                }
            }
        }
        return f2920a;
    }

    public Bitmap a(byte[] bArr) {
        return a(bArr, 0, 0);
    }

    public Bitmap a(byte[] bArr, int i, int i2) {
        int[] iArr = {i};
        int[] iArr2 = {i2};
        byte[] decodeRGBAnative = decodeRGBAnative(bArr, (long) bArr.length, iArr, iArr2);
        if (decodeRGBAnative.length == 0) {
            return null;
        }
        int[] iArr3 = new int[(decodeRGBAnative.length / 4)];
        ByteBuffer.wrap(decodeRGBAnative).asIntBuffer().get(iArr3);
        return Bitmap.createBitmap(iArr3, iArr[0], iArr2[0], Bitmap.Config.ARGB_8888);
    }
}
