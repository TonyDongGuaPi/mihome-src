package com.alipay.zoloz.toyger.blob;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class BitmapHelper {
    public static final int FRAME_MODE_BGR = 2;
    public static final int FRAME_MODE_BGRA = 1;
    public static final int FRAME_MODE_NV21 = 0;
    public static final int FRAME_MODE_RGB = 4;
    public static final int FRAME_MODE_RGBA = 3;

    public static int convertByteToInt(byte b) {
        return (((b >> 4) & 15) * 16) + (b & 15);
    }

    public static Bitmap bytes2Bitmap(byte[] bArr, int i, int i2, int i3) {
        switch (i3) {
            case 0:
                return getYUVBitmap(bArr, i, i2);
            case 3:
                return RGBABytes2Bitmap(bArr, i, i2);
            case 4:
                return RGBBytes2Bitmap(bArr, i, i2);
            default:
                return null;
        }
    }

    public static Bitmap bytes2Bitmap(byte[] bArr) {
        if (bArr.length != 0) {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }
        return null;
    }

    public static Bitmap RGBABytes2Bitmap(byte[] bArr, int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        createBitmap.copyPixelsFromBuffer(ByteBuffer.wrap(bArr));
        return createBitmap;
    }

    public static Bitmap RGBBytes2Bitmap(byte[] bArr, int i, int i2) {
        int[] convertBytesToInts = convertBytesToInts(bArr);
        if (convertBytesToInts == null) {
            return null;
        }
        return Bitmap.createBitmap(convertBytesToInts, 0, i, i, i2, Bitmap.Config.ARGB_8888);
    }

    public static int[] convertBytesToInts(byte[] bArr) {
        int i;
        int length = bArr.length;
        if (length == 0) {
            return null;
        }
        int i2 = 0;
        int i3 = length % 3 != 0 ? 1 : 0;
        int[] iArr = new int[((length / 3) + i3)];
        int length2 = iArr.length;
        if (i3 == 0) {
            while (i2 < length2) {
                int i4 = i2 * 3;
                iArr[i2] = convertByteToInt(bArr[i4 + 2]) | (convertByteToInt(bArr[i4]) << 16) | (convertByteToInt(bArr[i4 + 1]) << 8) | -16777216;
                i2++;
            }
        } else {
            while (true) {
                i = length2 - 1;
                if (i2 >= i) {
                    break;
                }
                int i5 = i2 * 3;
                iArr[i2] = convertByteToInt(bArr[i5 + 2]) | (convertByteToInt(bArr[i5]) << 16) | (convertByteToInt(bArr[i5 + 1]) << 8) | -16777216;
                i2++;
            }
            iArr[i] = -16777216;
        }
        return iArr;
    }

    public static Bitmap readBitMap(Context context, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        return BitmapFactory.decodeStream(context.getResources().openRawResource(i), (Rect) null, options);
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) i) / ((float) width), ((float) i2) / ((float) height));
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] Bitmap2BytesEx(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[(width * height)];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        return addBMP_RGB_888(iArr, width, height);
    }

    private static byte[] addBMP_RGB_888(int[] iArr, int i, int i2) {
        byte[] bArr = new byte[(i2 * i * 4)];
        int length = iArr.length - 1;
        int i3 = 0;
        while (length >= i) {
            int i4 = length - i;
            for (int i5 = i4 + 1; i5 <= length; i5++) {
                bArr[i3] = (byte) (iArr[i5] >> 0);
                bArr[i3 + 1] = (byte) (iArr[i5] >> 8);
                bArr[i3 + 2] = (byte) (iArr[i5] >> 16);
                bArr[i3 + 3] = (byte) (iArr[i5] >> 24);
                i3 += 4;
            }
            length = i4;
        }
        return bArr;
    }

    public static Bitmap resize(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) i) / ((float) width), ((float) i2) / ((float) height));
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        bitmap.recycle();
        return createBitmap;
    }

    public static Bitmap resize(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = ((float) i) / ((float) width);
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        bitmap.recycle();
        return createBitmap;
    }

    public static void recycle(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: byte} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r5v1, types: [int, byte] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap getBitmap(byte[] r6, int r7, int r8) {
        /*
            if (r6 != 0) goto L_0x0004
            r6 = 0
            return r6
        L_0x0004:
            int r0 = r6.length
            int r0 = r0 / 3
            int[] r1 = new int[r0]
            r2 = 0
        L_0x000a:
            if (r2 >= r0) goto L_0x002d
            int r3 = r2 * 3
            byte r4 = r6[r3]
            int r5 = r3 + 1
            byte r5 = r6[r5]
            int r3 = r3 + 2
            byte r3 = r6[r3]
            if (r4 >= 0) goto L_0x001c
            int r4 = r4 + 256
        L_0x001c:
            if (r5 >= 0) goto L_0x0020
            int r5 = r5 + 256
        L_0x0020:
            if (r3 >= 0) goto L_0x0024
            int r3 = r3 + 256
        L_0x0024:
            int r3 = android.graphics.Color.rgb(r4, r5, r3)
            r1[r2] = r3
            int r2 = r2 + 1
            goto L_0x000a
        L_0x002d:
            android.graphics.Bitmap$Config r6 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r6 = android.graphics.Bitmap.createBitmap(r1, r7, r8, r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.zoloz.toyger.blob.BitmapHelper.getBitmap(byte[], int, int):android.graphics.Bitmap");
    }

    public static Bitmap getYUVBitmap(byte[] bArr, int i, int i2) {
        YuvImage yuvImage = new YuvImage(bArr, 17, i, i2, (int[]) null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, i, i2), 100, byteArrayOutputStream);
        try {
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } catch (Error | OutOfMemoryError unused) {
            return null;
        }
    }

    public static Bitmap getYUVBitmap(byte[] bArr, int i, int i2, int i3) {
        YuvImage yuvImage = new YuvImage(bArr, 17, i, i2, (int[]) null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, i, i2), i3, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } catch (OutOfMemoryError unused) {
            return null;
        }
    }

    public static byte[] processImage(byte[] bArr, int i, int i2, int i3, int i4, int i5) {
        switch (i) {
            case 0:
                return rotateYUVImage(bArr, i2, i3, i4, i5);
            case 3:
                return compressRGBAImage(bArr, i2, i3, i4, i5);
            case 4:
                return compressRGBImage(bArr, i2, i3, i4, i5);
            default:
                return null;
        }
    }

    public static byte[] bitmapToByteArray(Bitmap bitmap, int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        try {
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] compressRGBImage(byte[] bArr, int i, int i2, int i3, int i4) {
        byte[] bArr2;
        Bitmap RGBBytes2Bitmap = RGBBytes2Bitmap(bArr, i, i2);
        if (i3 == 90 || i3 == 270) {
            Matrix matrix = new Matrix();
            matrix.setRotate((float) i3);
            Bitmap createBitmap = Bitmap.createBitmap(RGBBytes2Bitmap, 0, 0, RGBBytes2Bitmap.getWidth(), RGBBytes2Bitmap.getHeight(), matrix, true);
            byte[] bitmapToByteArray = bitmapToByteArray(createBitmap, i4);
            createBitmap.recycle();
            bArr2 = bitmapToByteArray;
        } else {
            bArr2 = bitmapToByteArray(RGBBytes2Bitmap, i4);
        }
        RGBBytes2Bitmap.recycle();
        return bArr2;
    }

    public static byte[] compressRGBAImage(byte[] bArr, int i, int i2, int i3, int i4) {
        byte[] bArr2;
        Bitmap RGBABytes2Bitmap = RGBABytes2Bitmap(bArr, i, i2);
        if (i3 == 90 || i3 == 270) {
            Matrix matrix = new Matrix();
            matrix.setRotate((float) i3);
            Bitmap createBitmap = Bitmap.createBitmap(RGBABytes2Bitmap, 0, 0, RGBABytes2Bitmap.getWidth(), RGBABytes2Bitmap.getHeight(), matrix, true);
            byte[] bitmapToByteArray = bitmapToByteArray(createBitmap, i4);
            createBitmap.recycle();
            bArr2 = bitmapToByteArray;
        } else {
            bArr2 = bitmapToByteArray(RGBABytes2Bitmap, i4);
        }
        RGBABytes2Bitmap.recycle();
        return bArr2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] rotateYUVImage(byte[] r7, int r8, int r9, int r10, int r11) {
        /*
            r0 = 90
            if (r10 != r0) goto L_0x000a
            byte[] r7 = rotateYUV420Degree270(r7, r8, r9)
        L_0x0008:
            r1 = r7
            goto L_0x0017
        L_0x000a:
            r0 = 270(0x10e, float:3.78E-43)
            if (r10 != r0) goto L_0x0013
            byte[] r7 = rotateYUV420Degree90(r7, r8, r9)
            goto L_0x0008
        L_0x0013:
            r1 = r7
            r6 = r9
            r9 = r8
            r8 = r6
        L_0x0017:
            java.io.ByteArrayOutputStream r7 = new java.io.ByteArrayOutputStream
            r7.<init>()
            if (r1 == 0) goto L_0x0032
            android.graphics.YuvImage r10 = new android.graphics.YuvImage
            r2 = 17
            r5 = 0
            r0 = r10
            r3 = r9
            r4 = r8
            r0.<init>(r1, r2, r3, r4, r5)
            android.graphics.Rect r0 = new android.graphics.Rect
            r1 = 0
            r0.<init>(r1, r1, r9, r8)
            r10.compressToJpeg(r0, r11, r7)
        L_0x0032:
            r7.close()     // Catch:{ IOException -> 0x0035 }
        L_0x0035:
            byte[] r7 = r7.toByteArray()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.zoloz.toyger.blob.BitmapHelper.rotateYUVImage(byte[], int, int, int, int):byte[]");
    }

    private static byte[] rotateYUV420Degree90(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        int i4 = (i3 * 3) / 2;
        byte[] bArr2 = new byte[i4];
        int i5 = 0;
        for (int i6 = 0; i6 < i; i6++) {
            for (int i7 = i2 - 1; i7 >= 0; i7--) {
                bArr2[i5] = bArr[(i7 * i) + i6];
                i5++;
            }
        }
        int i8 = i4 - 1;
        int i9 = i - 1;
        while (i9 > 0) {
            int i10 = i8;
            for (int i11 = 0; i11 < i2 / 2; i11++) {
                int i12 = (i11 * i) + i3;
                bArr2[i10] = bArr[i12 + i9];
                int i13 = i10 - 1;
                bArr2[i13] = bArr[i12 + (i9 - 1)];
                i10 = i13 - 1;
            }
            i9 -= 2;
            i8 = i10;
        }
        return bArr2;
    }

    private static byte[] rotateYUV420Degree180(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        int i4 = (i3 * 3) / 2;
        byte[] bArr2 = new byte[i4];
        int i5 = 0;
        for (int i6 = i3 - 1; i6 >= 0; i6--) {
            bArr2[i5] = bArr[i6];
            i5++;
        }
        for (int i7 = i4 - 1; i7 >= i3; i7 -= 2) {
            int i8 = i5 + 1;
            bArr2[i5] = bArr[i7 - 1];
            i5 = i8 + 1;
            bArr2[i8] = bArr[i7];
        }
        return bArr2;
    }

    public static byte[] rotateYUV420Degree270(byte[] bArr, int i, int i2) {
        return rotateYUV420Degree180(rotateYUV420Degree90(bArr, i, i2), i, i2);
    }

    public static Bitmap reverseBitmap(Bitmap bitmap, int i) {
        float[] fArr;
        if (bitmap == null) {
            return null;
        }
        if (i != 0) {
            fArr = new float[]{1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
        } else {
            fArr = new float[]{-1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
        }
        Matrix matrix = new Matrix();
        matrix.setValues(fArr);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate((float) i);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        if (createBitmap.equals(bitmap)) {
            return createBitmap;
        }
        bitmap.recycle();
        return createBitmap;
    }

    public static Bitmap cropBitmap(Bitmap bitmap, Rect rect) {
        bitmap.getWidth();
        bitmap.getHeight();
        return Bitmap.createBitmap(bitmap, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
    }
}