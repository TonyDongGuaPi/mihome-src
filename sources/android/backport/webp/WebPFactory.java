package android.backport.webp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;

public final class WebPFactory {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f472a = true;

    private static native Bitmap nativeDecodeByteArray(byte[] bArr, BitmapFactory.Options options);

    private static native Bitmap nativeDecodeFile(String str, BitmapFactory.Options options);

    private static native byte[] nativeEncodeBitmap(Bitmap bitmap, int i);

    static {
        try {
            System.loadLibrary("webpbackport");
        } catch (Throwable unused) {
        }
    }

    private WebPFactory() {
    }

    public static boolean a() {
        return !f472a;
    }

    public static Bitmap a(byte[] bArr, BitmapFactory.Options options) {
        if (a()) {
            return nativeDecodeByteArray(bArr, options);
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
    }

    public static Bitmap a(String str, BitmapFactory.Options options) {
        if (a()) {
            return nativeDecodeFile(str, options);
        }
        return BitmapFactory.decodeFile(str, options);
    }

    public static byte[] a(Bitmap bitmap, int i) {
        if (a()) {
            return nativeEncodeBitmap(bitmap, i);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.WEBP, i, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
