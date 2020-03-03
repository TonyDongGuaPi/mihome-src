package com.mi.global.shop.util.fresco;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class GifUtils {
    public static String a(int i, int i2) {
        String upperCase = Integer.toHexString(i).toUpperCase();
        if (upperCase.length() >= i2) {
            return upperCase.length() > i2 ? upperCase.substring(upperCase.length() - i2) : upperCase;
        }
        while (upperCase.length() < i2) {
            upperCase = "0" + upperCase;
        }
        return upperCase;
    }

    public static byte[] a(InputStream inputStream) throws IOException, OutOfMemoryError {
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                try {
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        inputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x002a A[SYNTHETIC, Splitter:B:18:0x002a] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0036 A[SYNTHETIC, Splitter:B:25:0x0036] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap a(java.io.File r1, int r2, int r3) {
        /*
            com.mi.global.shop.util.fresco.GifImageDecoder r0 = new com.mi.global.shop.util.fresco.GifImageDecoder
            r0.<init>()
            r0.a(r2, r3)
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0023, all -> 0x0020 }
            r3.<init>(r1)     // Catch:{ IOException -> 0x0023, all -> 0x0020 }
            r0.a((java.io.InputStream) r3)     // Catch:{ IOException -> 0x001e }
            android.graphics.Bitmap r1 = r0.b()     // Catch:{ IOException -> 0x001e }
            r3.close()     // Catch:{ IOException -> 0x0019 }
            goto L_0x001d
        L_0x0019:
            r2 = move-exception
            r2.printStackTrace()
        L_0x001d:
            return r1
        L_0x001e:
            r1 = move-exception
            goto L_0x0025
        L_0x0020:
            r1 = move-exception
            r3 = r2
            goto L_0x0034
        L_0x0023:
            r1 = move-exception
            r3 = r2
        L_0x0025:
            r1.printStackTrace()     // Catch:{ all -> 0x0033 }
            if (r3 == 0) goto L_0x0032
            r3.close()     // Catch:{ IOException -> 0x002e }
            goto L_0x0032
        L_0x002e:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0032:
            return r2
        L_0x0033:
            r1 = move-exception
        L_0x0034:
            if (r3 == 0) goto L_0x003e
            r3.close()     // Catch:{ IOException -> 0x003a }
            goto L_0x003e
        L_0x003a:
            r2 = move-exception
            r2.printStackTrace()
        L_0x003e:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.util.fresco.GifUtils.a(java.io.File, int, int):android.graphics.Bitmap");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0027 A[SYNTHETIC, Splitter:B:18:0x0027] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0033 A[SYNTHETIC, Splitter:B:25:0x0033] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap a(java.io.File r3) {
        /*
            com.mi.global.shop.util.fresco.GifImageDecoder r0 = new com.mi.global.shop.util.fresco.GifImageDecoder
            r0.<init>()
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            r0.a((java.io.InputStream) r2)     // Catch:{ IOException -> 0x001b }
            android.graphics.Bitmap r3 = r0.b()     // Catch:{ IOException -> 0x001b }
            r2.close()     // Catch:{ IOException -> 0x0016 }
            goto L_0x001a
        L_0x0016:
            r0 = move-exception
            r0.printStackTrace()
        L_0x001a:
            return r3
        L_0x001b:
            r3 = move-exception
            goto L_0x0022
        L_0x001d:
            r3 = move-exception
            r2 = r1
            goto L_0x0031
        L_0x0020:
            r3 = move-exception
            r2 = r1
        L_0x0022:
            r3.printStackTrace()     // Catch:{ all -> 0x0030 }
            if (r2 == 0) goto L_0x002f
            r2.close()     // Catch:{ IOException -> 0x002b }
            goto L_0x002f
        L_0x002b:
            r3 = move-exception
            r3.printStackTrace()
        L_0x002f:
            return r1
        L_0x0030:
            r3 = move-exception
        L_0x0031:
            if (r2 == 0) goto L_0x003b
            r2.close()     // Catch:{ IOException -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r0 = move-exception
            r0.printStackTrace()
        L_0x003b:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.util.fresco.GifUtils.a(java.io.File):android.graphics.Bitmap");
    }
}
