package com.xiaomi.youpin.common.util.blur;

import android.graphics.Bitmap;
import java.util.ArrayList;
import java.util.concurrent.Callable;

class JavaBlurProcess implements BlurProcess {

    /* renamed from: a  reason: collision with root package name */
    private static final short[] f23289a = {512, 512, 456, 512, 328, 456, 335, 512, 405, 328, 271, 456, 388, 335, 292, 512, 454, 405, 364, 328, 298, 271, 496, 456, 420, 388, 360, 335, 312, 292, 273, 512, 482, 454, 428, 405, 383, 364, 345, 328, 312, 298, 284, 271, 259, 496, 475, 456, 437, 420, 404, 388, 374, 360, 347, 335, 323, 312, 302, 292, 282, 273, 265, 512, 497, 482, 468, 454, 441, 428, 417, 405, 394, 383, 373, 364, 354, 345, 337, 328, 320, 312, 305, 298, 291, 284, 278, 271, 265, 259, 507, 496, 485, 475, 465, 456, 446, 437, 428, 420, 412, 404, 396, 388, 381, 374, 367, 360, 354, 347, 341, 335, 329, 323, 318, 312, 307, 302, 297, 292, 287, 282, 278, 273, 269, 265, 261, 512, 505, 497, 489, 482, 475, 468, 461, 454, 447, 441, 435, 428, 422, 417, 411, 405, 399, 394, 389, 383, 378, 373, 368, 364, 359, 354, 350, 345, 341, 337, 332, 328, 324, 320, 316, 312, 309, 305, 301, 298, 294, 291, 287, 284, 281, 278, 274, 271, 268, 265, 262, 259, 257, 507, 501, 496, 491, 485, 480, 475, 470, 465, 460, 456, 451, 446, 442, 437, 433, 428, 424, 420, 416, 412, 408, 404, 400, 396, 392, 388, 385, 381, 377, 374, 370, 367, 363, 360, 357, 354, 350, 347, 344, 341, 338, 335, 332, 329, 326, 323, 320, 318, 315, 312, 310, 307, 304, 302, 299, 297, 294, 292, 289, 287, 285, 282, 280, 278, 275, 273, 271, 269, 267, 265, 263, 261, 259};
    private static final byte[] b = {9, 11, 12, 13, 13, 14, 14, 15, 15, 15, 15, 16, 16, 16, 16, 17, 17, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 18, 18, 18, 18, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24};

    JavaBlurProcess() {
    }

    /* access modifiers changed from: private */
    public static void b(int[] iArr, int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = i;
        int i8 = i2;
        int i9 = i3;
        int i10 = i6;
        int i11 = i7 - 1;
        int i12 = i8 - 1;
        int i13 = (i9 * 2) + 1;
        short s = f23289a[i9];
        byte b2 = b[i9];
        int[] iArr2 = new int[i13];
        if (i10 == 1) {
            int i14 = (i5 * i8) / i4;
            int i15 = ((i5 + 1) * i8) / i4;
            while (i14 < i15) {
                int i16 = i7 * i14;
                int i17 = 0;
                long j = 0;
                long j2 = 0;
                long j3 = 0;
                long j4 = 0;
                long j5 = 0;
                long j6 = 0;
                while (i17 <= i9) {
                    iArr2[i17] = iArr[i16];
                    i17++;
                    j += (long) (((iArr[i16] >>> 16) & 255) * i17);
                    j2 += (long) (((iArr[i16] >>> 8) & 255) * i17);
                    j3 += (long) ((iArr[i16] & 255) * i17);
                    j4 += (long) ((iArr[i16] >>> 16) & 255);
                    j5 += (long) ((iArr[i16] >>> 8) & 255);
                    j6 += (long) (iArr[i16] & 255);
                    s = s;
                }
                short s2 = s;
                int i18 = i16;
                long j7 = 0;
                long j8 = 0;
                long j9 = 0;
                for (int i19 = 1; i19 <= i9; i19++) {
                    if (i19 <= i11) {
                        i18++;
                    }
                    iArr2[i19 + i9] = iArr[i18];
                    int i20 = (i9 + 1) - i19;
                    j += (long) (((iArr[i18] >>> 16) & 255) * i20);
                    j2 += (long) (((iArr[i18] >>> 8) & 255) * i20);
                    j3 += (long) ((iArr[i18] & 255) * i20);
                    j7 += (long) ((iArr[i18] >>> 16) & 255);
                    j8 += (long) ((iArr[i18] >>> 8) & 255);
                    j9 += (long) (iArr[i18] & 255);
                }
                int i21 = i9 > i11 ? i11 : i9;
                long j10 = j7;
                int i22 = i21;
                int i23 = i21 + i16;
                int i24 = i16;
                int i25 = i9;
                int i26 = 0;
                while (i26 < i7) {
                    int i27 = i15;
                    int i28 = i14;
                    long j11 = j10;
                    short s3 = s2;
                    long j12 = (long) s3;
                    iArr[i24] = (int) (((long) (iArr[i24] & -16777216)) | ((((j * j12) >>> b2) & 255) << 16) | ((((j2 * j12) >>> b2) & 255) << 8) | (((j12 * j3) >>> b2) & 255));
                    i24++;
                    long j13 = j - j4;
                    long j14 = j2 - j5;
                    long j15 = j3 - j6;
                    int i29 = (i25 + i13) - i9;
                    if (i29 >= i13) {
                        i29 -= i13;
                    }
                    long j16 = j4 - ((long) ((iArr2[i29] >>> 16) & 255));
                    long j17 = j5 - ((long) ((iArr2[i29] >>> 8) & 255));
                    long j18 = j6 - ((long) (iArr2[i29] & 255));
                    if (i22 < i11) {
                        i23++;
                        i22++;
                    }
                    iArr2[i29] = iArr[i23];
                    long j19 = j11 + ((long) ((iArr[i23] >>> 16) & 255));
                    long j20 = j8 + ((long) ((iArr[i23] >>> 8) & 255));
                    long j21 = j9 + ((long) (iArr[i23] & 255));
                    j = j13 + j19;
                    j2 = j14 + j20;
                    j3 = j15 + j21;
                    i25++;
                    if (i25 >= i13) {
                        i25 = 0;
                    }
                    j4 = j16 + ((long) ((iArr2[i25] >>> 16) & 255));
                    j5 = j17 + ((long) ((iArr2[i25] >>> 8) & 255));
                    j6 = j18 + ((long) (iArr2[i25] & 255));
                    j10 = j19 - ((long) ((iArr2[i25] >>> 16) & 255));
                    j8 = j20 - ((long) ((iArr2[i25] >>> 8) & 255));
                    j9 = j21 - ((long) (iArr2[i25] & 255));
                    i26++;
                    s2 = s3;
                    i15 = i27;
                    i14 = i28;
                    i7 = i;
                }
                int i30 = i15;
                i14++;
                s = s2;
                i7 = i;
            }
            return;
        }
        short s4 = s;
        if (i10 == 2) {
            short s5 = s4;
            int i31 = i;
            int i32 = (i5 * i31) / i4;
            int i33 = ((i5 + 1) * i31) / i4;
            while (i32 < i33) {
                int i34 = 0;
                long j22 = 0;
                long j23 = 0;
                long j24 = 0;
                long j25 = 0;
                long j26 = 0;
                long j27 = 0;
                while (i34 <= i9) {
                    iArr2[i34] = iArr[i32];
                    i34++;
                    j22 += (long) (((iArr[i32] >>> 16) & 255) * i34);
                    j23 += (long) (((iArr[i32] >>> 8) & 255) * i34);
                    j24 += (long) ((iArr[i32] & 255) * i34);
                    j25 += (long) ((iArr[i32] >>> 16) & 255);
                    j26 += (long) ((iArr[i32] >>> 8) & 255);
                    j27 += (long) (iArr[i32] & 255);
                }
                int i35 = i32;
                int i36 = 1;
                long j28 = 0;
                long j29 = 0;
                long j30 = 0;
                while (i36 <= i9) {
                    if (i36 <= i12) {
                        i35 += i31;
                    }
                    iArr2[i36 + i9] = iArr[i35];
                    int i37 = (i9 + 1) - i36;
                    j22 += (long) (((iArr[i35] >>> 16) & 255) * i37);
                    j23 += (long) (((iArr[i35] >>> 8) & 255) * i37);
                    j24 += (long) ((iArr[i35] & 255) * i37);
                    j28 += (long) ((iArr[i35] >>> 16) & 255);
                    j29 += (long) ((iArr[i35] >>> 8) & 255);
                    j30 += (long) (iArr[i35] & 255);
                    i36++;
                    b2 = b2;
                }
                byte b3 = b2;
                int i38 = i9 > i12 ? i12 : i9;
                int i39 = i9;
                int i40 = i38;
                int i41 = (i38 * i31) + i32;
                int i42 = 0;
                int i43 = i32;
                while (i42 < i8) {
                    int i44 = i42;
                    int i45 = i33;
                    int i46 = i32;
                    int i47 = i12;
                    long j31 = (long) s5;
                    iArr[i43] = (int) (((long) (iArr[i43] & -16777216)) | ((((j22 * j31) >>> b3) & 255) << 16) | ((((j23 * j31) >>> b3) & 255) << 8) | (((j31 * j24) >>> b3) & 255));
                    i43 += i31;
                    long j32 = j22 - j25;
                    long j33 = j23 - j26;
                    long j34 = j24 - j27;
                    int i48 = (i39 + i13) - i9;
                    if (i48 >= i13) {
                        i48 -= i13;
                    }
                    long j35 = j25 - ((long) ((iArr2[i48] >>> 16) & 255));
                    long j36 = j26 - ((long) ((iArr2[i48] >>> 8) & 255));
                    long j37 = j27 - ((long) (iArr2[i48] & 255));
                    int i49 = i47;
                    if (i40 < i49) {
                        i41 += i31;
                        i40++;
                    }
                    iArr2[i48] = iArr[i41];
                    long j38 = j28 + ((long) ((iArr[i41] >>> 16) & 255));
                    long j39 = j29 + ((long) ((iArr[i41] >>> 8) & 255));
                    long j40 = j30 + ((long) (iArr[i41] & 255));
                    j22 = j32 + j38;
                    j23 = j33 + j39;
                    j24 = j34 + j40;
                    i39++;
                    if (i39 >= i13) {
                        i39 = 0;
                    }
                    j25 = j35 + ((long) ((iArr2[i39] >>> 16) & 255));
                    j26 = j36 + ((long) ((iArr2[i39] >>> 8) & 255));
                    j27 = j37 + ((long) (iArr2[i39] & 255));
                    j28 = j38 - ((long) ((iArr2[i39] >>> 16) & 255));
                    j29 = j39 - ((long) ((iArr2[i39] >>> 8) & 255));
                    j30 = j40 - ((long) (iArr2[i39] & 255));
                    i12 = i49;
                    i33 = i45;
                    i32 = i46;
                    i42 = i44 + 1;
                    i8 = i2;
                }
                int i50 = i33;
                int i51 = i12;
                i32++;
                b2 = b3;
                i8 = i2;
            }
        }
    }

    public Bitmap a(Bitmap bitmap, float f) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[(width * height)];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        int i = StackBlurManager.f23291a;
        ArrayList arrayList = new ArrayList(i);
        ArrayList arrayList2 = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = (int) f;
            int[] iArr2 = iArr;
            int i4 = width;
            int i5 = height;
            int i6 = i3;
            int i7 = i;
            int i8 = i3;
            int i9 = i2;
            arrayList.add(new BlurTask(iArr2, i4, i5, i6, i7, i9, 1));
            arrayList2.add(new BlurTask(iArr2, i4, i5, i8, i7, i9, 2));
        }
        try {
            StackBlurManager.b.invokeAll(arrayList);
            try {
                StackBlurManager.b.invokeAll(arrayList2);
                return Bitmap.createBitmap(iArr, width, height, Bitmap.Config.ARGB_8888);
            } catch (InterruptedException unused) {
                return null;
            }
        } catch (InterruptedException unused2) {
            return null;
        }
    }

    private static class BlurTask implements Callable<Void> {

        /* renamed from: a  reason: collision with root package name */
        private final int[] f23290a;
        private final int b;
        private final int c;
        private final int d;
        private final int e;
        private final int f;
        private final int g;

        public BlurTask(int[] iArr, int i, int i2, int i3, int i4, int i5, int i6) {
            this.f23290a = iArr;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
            this.f = i5;
            this.g = i6;
        }

        /* renamed from: a */
        public Void call() throws Exception {
            JavaBlurProcess.b(this.f23290a, this.b, this.c, this.d, this.e, this.f, this.g);
            return null;
        }
    }
}
