package com.nostra13.universalimageloader.utils;

import android.opengl.GLES10;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

public final class ImageSizeUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final int f8493a = 2048;
    private static ImageSize b;

    static {
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        int max = Math.max(iArr[0], 2048);
        b = new ImageSize(max, max);
    }

    private ImageSizeUtils() {
    }

    public static ImageSize a(ImageAware imageAware, ImageSize imageSize) {
        int a2 = imageAware.a();
        if (a2 <= 0) {
            a2 = imageSize.a();
        }
        int b2 = imageAware.b();
        if (b2 <= 0) {
            b2 = imageSize.b();
        }
        return new ImageSize(a2, b2);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0030, code lost:
        r7 = r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(com.nostra13.universalimageloader.core.assist.ImageSize r6, com.nostra13.universalimageloader.core.assist.ImageSize r7, com.nostra13.universalimageloader.core.assist.ViewScaleType r8, boolean r9) {
        /*
            int r0 = r6.a()
            int r6 = r6.b()
            int r1 = r7.a()
            int r7 = r7.b()
            int[] r2 = com.nostra13.universalimageloader.utils.ImageSizeUtils.AnonymousClass1.f8494a
            int r8 = r8.ordinal()
            r8 = r2[r8]
            r2 = 1
            switch(r8) {
                case 1: goto L_0x003b;
                case 2: goto L_0x001e;
                default: goto L_0x001c;
            }
        L_0x001c:
            r7 = 1
            goto L_0x0055
        L_0x001e:
            if (r9 == 0) goto L_0x0032
            int r8 = r0 / 2
            int r3 = r6 / 2
            r4 = 1
        L_0x0025:
            int r5 = r8 / r4
            if (r5 <= r1) goto L_0x0030
            int r5 = r3 / r4
            if (r5 <= r7) goto L_0x0030
            int r4 = r4 * 2
            goto L_0x0025
        L_0x0030:
            r7 = r4
            goto L_0x0055
        L_0x0032:
            int r8 = r0 / r1
            int r7 = r6 / r7
            int r7 = java.lang.Math.min(r8, r7)
            goto L_0x0055
        L_0x003b:
            if (r9 == 0) goto L_0x004d
            int r8 = r0 / 2
            int r3 = r6 / 2
            r4 = 1
        L_0x0042:
            int r5 = r8 / r4
            if (r5 > r1) goto L_0x004a
            int r5 = r3 / r4
            if (r5 <= r7) goto L_0x0030
        L_0x004a:
            int r4 = r4 * 2
            goto L_0x0042
        L_0x004d:
            int r8 = r0 / r1
            int r7 = r6 / r7
            int r7 = java.lang.Math.max(r8, r7)
        L_0x0055:
            if (r7 >= r2) goto L_0x0058
            r7 = 1
        L_0x0058:
            int r6 = a((int) r0, (int) r6, (int) r7, (boolean) r9)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.utils.ImageSizeUtils.a(com.nostra13.universalimageloader.core.assist.ImageSize, com.nostra13.universalimageloader.core.assist.ImageSize, com.nostra13.universalimageloader.core.assist.ViewScaleType, boolean):int");
    }

    private static int a(int i, int i2, int i3, boolean z) {
        int a2 = b.a();
        int b2 = b.b();
        while (true) {
            if (i / i3 <= a2 && i2 / i3 <= b2) {
                return i3;
            }
            i3 = z ? i3 * 2 : i3 + 1;
        }
    }

    public static int a(ImageSize imageSize) {
        int a2 = imageSize.a();
        int b2 = imageSize.b();
        return Math.max((int) Math.ceil((double) (((float) a2) / ((float) b.a()))), (int) Math.ceil((double) (((float) b2) / ((float) b.b()))));
    }

    public static float b(ImageSize imageSize, ImageSize imageSize2, ViewScaleType viewScaleType, boolean z) {
        int a2 = imageSize.a();
        int b2 = imageSize.b();
        int a3 = imageSize2.a();
        int b3 = imageSize2.b();
        float f = (float) a2;
        float f2 = f / ((float) a3);
        float f3 = (float) b2;
        float f4 = f3 / ((float) b3);
        if ((viewScaleType != ViewScaleType.FIT_INSIDE || f2 < f4) && (viewScaleType != ViewScaleType.CROP || f2 >= f4)) {
            a3 = (int) (f / f4);
        } else {
            b3 = (int) (f3 / f2);
        }
        if ((z || a3 >= a2 || b3 >= b2) && (!z || a3 == a2 || b3 == b2)) {
            return 1.0f;
        }
        return ((float) a3) / f;
    }
}
