package com.megvii.idcard.sdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.Log;
import com.megvii.idcard.sdk.jni.IDCardApi;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private long f6654a;
    private int b = 0;

    /* renamed from: com.megvii.idcard.sdk.a$a  reason: collision with other inner class name */
    public static class C0061a implements Serializable {
        private static final long serialVersionUID = 3786070988580648667L;

        /* renamed from: a  reason: collision with root package name */
        public float[] f6655a;
        public float[] b;
        public f[] c;
    }

    public static class b implements Serializable {
        private static final long serialVersionUID = 4644547927906498343L;

        /* renamed from: a  reason: collision with root package name */
        public float[] f6656a;
        public float[] b;
        public f[] c;
    }

    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public int f6657a;
        public float b;
        public float c;
        public float d;
        public int e;
        public int f;
        public int g;
        public int h;
        public int i;
        public int j;
        public int k;
        public int l;
    }

    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public float f6658a;
        public float b;
        public float c;
    }

    public static class e implements Serializable {
        private static final long serialVersionUID = 5507432037314593640L;

        /* renamed from: a  reason: collision with root package name */
        public boolean f6659a = false;
        public boolean b = false;
        public g[] c;
        public b[] d;
        public C0061a[] e;
    }

    public static class f implements Serializable {
        private static final long serialVersionUID = 7096991384851649494L;

        /* renamed from: a  reason: collision with root package name */
        public float f6660a;
        public float b;
    }

    public static class g implements Serializable {
        private static final long serialVersionUID = -5095788114139817829L;

        /* renamed from: a  reason: collision with root package name */
        public float[] f6661a;
        public float[] b;
        public f[] c;
    }

    private static String a(int i) {
        switch (i) {
            case -1:
                return "MG_RETCODE_FAILED";
            case 0:
                return "MG_RETCODE_OK";
            case 1:
                return "MG_RETCODE_INVALID_ARGUMENT";
            case 2:
                return "MG_RETCODE_INVALID_HANDLE";
            case 3:
                return "MG_RETCODE_INDEX_OUT_OF_RANGE";
            default:
                switch (i) {
                    case 101:
                        return "MG_RETCODE_EXPIRE";
                    case 102:
                        return "MG_RETCODE_INVALID_BUNDLEID";
                    case 103:
                        return "MG_RETCODE_INVALID_LICENSE";
                    case 104:
                        return "MG_RETCODE_INVALID_MODEL";
                    default:
                        return null;
                }
        }
    }

    public final String a(Context context, byte[] bArr) {
        if (context == null || bArr == null) {
            return a(1);
        }
        long nativeInit = IDCardApi.nativeInit(context, bArr);
        String a2 = a((int) nativeInit);
        if (a2 != null) {
            return a2;
        }
        this.f6654a = nativeInit;
        return null;
    }

    public final c a() {
        if (this.f6654a == 0) {
            return null;
        }
        float[] nativeGetIDCardConfig = IDCardApi.nativeGetIDCardConfig(this.f6654a);
        c cVar = new c();
        cVar.f6657a = (int) nativeGetIDCardConfig[0];
        cVar.b = nativeGetIDCardConfig[1];
        cVar.c = nativeGetIDCardConfig[2];
        cVar.d = nativeGetIDCardConfig[3];
        cVar.e = (int) nativeGetIDCardConfig[4];
        cVar.f = (int) nativeGetIDCardConfig[5];
        cVar.g = (int) nativeGetIDCardConfig[6];
        cVar.h = (int) nativeGetIDCardConfig[7];
        cVar.i = (int) nativeGetIDCardConfig[8];
        cVar.j = (int) nativeGetIDCardConfig[9];
        cVar.k = (int) nativeGetIDCardConfig[10];
        return cVar;
    }

    public final void a(c cVar) {
        c cVar2 = cVar;
        if (this.f6654a != 0) {
            IDCardApi.nativeSetIDCardConfig(this.f6654a, cVar2.f6657a, cVar2.b, cVar2.c, cVar2.d, cVar2.e, cVar2.f, cVar2.g, cVar2.h, cVar2.i, cVar2.j, cVar2.k, cVar2.l);
        }
    }

    public final d a(byte[] bArr, int i, int i2, int i3) {
        if (this.f6654a == 0) {
            return null;
        }
        d dVar = new d();
        float[] nativeDetect = IDCardApi.nativeDetect(this.f6654a, bArr, i, i2, i3);
        dVar.f6658a = nativeDetect[0];
        dVar.b = nativeDetect[1];
        dVar.c = nativeDetect[2];
        return dVar;
    }

    public final e b() {
        if (this.f6654a == 0) {
            return null;
        }
        this.b = 0;
        e eVar = new e();
        float[] nativeCalculateQuality = IDCardApi.nativeCalculateQuality(this.f6654a);
        int i = this.b;
        this.b = i + 1;
        int i2 = (int) nativeCalculateQuality[i];
        g[] gVarArr = new g[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            g gVar = new g();
            gVar.f6661a = new float[3];
            for (int i4 = 0; i4 < gVar.f6661a.length; i4++) {
                float[] fArr = gVar.f6661a;
                int i5 = this.b;
                this.b = i5 + 1;
                fArr[i4] = nativeCalculateQuality[i5];
            }
            gVar.b = new float[3];
            for (int i6 = 0; i6 < gVar.b.length; i6++) {
                float[] fArr2 = gVar.b;
                int i7 = this.b;
                this.b = i7 + 1;
                fArr2[i6] = nativeCalculateQuality[i7];
            }
            int i8 = this.b;
            this.b = i8 + 1;
            int i9 = (int) nativeCalculateQuality[i8];
            gVar.c = new f[i9];
            for (int i10 = 0; i10 < i9; i10++) {
                gVar.c[i10] = new f();
                f fVar = gVar.c[i10];
                int i11 = this.b;
                this.b = i11 + 1;
                fVar.f6660a = nativeCalculateQuality[i11];
                f fVar2 = gVar.c[i10];
                int i12 = this.b;
                this.b = i12 + 1;
                fVar2.b = nativeCalculateQuality[i12];
            }
            gVarArr[i3] = gVar;
        }
        eVar.c = gVarArr;
        int i13 = this.b;
        this.b = i13 + 1;
        int i14 = (int) nativeCalculateQuality[i13];
        b[] bVarArr = new b[i14];
        Log.w("ceshi", "size===" + i14);
        for (int i15 = 0; i15 < i14; i15++) {
            b bVar = new b();
            bVar.f6656a = new float[3];
            for (int i16 = 0; i16 < bVar.f6656a.length; i16++) {
                float[] fArr3 = bVar.f6656a;
                int i17 = this.b;
                this.b = i17 + 1;
                fArr3[i16] = nativeCalculateQuality[i17];
            }
            bVar.b = new float[3];
            for (int i18 = 0; i18 < bVar.b.length; i18++) {
                float[] fArr4 = bVar.b;
                int i19 = this.b;
                this.b = i19 + 1;
                fArr4[i18] = nativeCalculateQuality[i19];
            }
            int i20 = this.b;
            this.b = i20 + 1;
            int i21 = (int) nativeCalculateQuality[i20];
            bVar.c = new f[i21];
            for (int i22 = 0; i22 < i21; i22++) {
                bVar.c[i22] = new f();
                f fVar3 = bVar.c[i22];
                int i23 = this.b;
                this.b = i23 + 1;
                fVar3.f6660a = nativeCalculateQuality[i23];
                f fVar4 = bVar.c[i22];
                int i24 = this.b;
                this.b = i24 + 1;
                fVar4.b = nativeCalculateQuality[i24];
            }
            bVarArr[i15] = bVar;
        }
        eVar.d = bVarArr;
        int i25 = this.b;
        this.b = i25 + 1;
        eVar.e = a(nativeCalculateQuality, (int) nativeCalculateQuality[i25]);
        if (eVar.c.length == 0) {
            eVar.f6659a = true;
        }
        if (eVar.d.length == 0) {
            eVar.b = true;
        }
        return eVar;
    }

    private C0061a[] a(float[] fArr, int i) {
        C0061a[] aVarArr = new C0061a[i];
        for (int i2 = 0; i2 < i; i2++) {
            C0061a aVar = new C0061a();
            aVar.f6655a = new float[3];
            for (int i3 = 0; i3 < aVar.f6655a.length; i3++) {
                float[] fArr2 = aVar.f6655a;
                int i4 = this.b;
                this.b = i4 + 1;
                fArr2[i3] = fArr[i4];
            }
            aVar.b = new float[3];
            for (int i5 = 0; i5 < aVar.b.length; i5++) {
                float[] fArr3 = aVar.b;
                int i6 = this.b;
                this.b = i6 + 1;
                fArr3[i5] = fArr[i6];
            }
            int i7 = this.b;
            this.b = i7 + 1;
            int i8 = (int) fArr[i7];
            aVar.c = new f[i8];
            for (int i9 = 0; i9 < i8; i9++) {
                aVar.c[i9] = new f();
                f fVar = aVar.c[i9];
                int i10 = this.b;
                this.b = i10 + 1;
                fVar.f6660a = fArr[i10];
                f fVar2 = aVar.c[i9];
                int i11 = this.b;
                this.b = i11 + 1;
                fVar2.b = fArr[i11];
            }
            aVarArr[i2] = aVar;
        }
        return aVarArr;
    }

    public final void c() {
        if (this.f6654a != 0) {
            IDCardApi.nativeRelease(this.f6654a);
            this.f6654a = 0;
        }
    }

    public static Rect a(Point[] pointArr, float f2) {
        if (pointArr == null || pointArr.length < 2) {
            return null;
        }
        Rect rect = new Rect();
        rect.left = pointArr[0].x;
        rect.top = pointArr[0].y;
        rect.right = pointArr[pointArr.length - 1].x;
        rect.bottom = pointArr[pointArr.length - 1].y;
        for (Point point : pointArr) {
            rect.union(point.x, point.y);
        }
        if (f2 <= 0.0f) {
            return rect;
        }
        int width = rect.width();
        int height = rect.height();
        float f3 = ((float) width) * f2;
        rect.left = (int) (((float) rect.left) - f3);
        float f4 = ((float) height) * f2;
        rect.top = (int) (((float) rect.top) - f4);
        rect.right = (int) (((float) rect.right) + f3);
        rect.bottom = (int) (((float) rect.bottom) + f4);
        return rect;
    }

    public static Rect a(Point[] pointArr) {
        return a(pointArr, 0.0f);
    }

    public static Bitmap a(byte[] bArr, int i, int i2, Rect rect, int i3) {
        YuvImage yuvImage = new YuvImage(bArr, 17, i, i2, (int[]) null);
        rect.left = Math.max(0, rect.left);
        rect.top = Math.max(0, rect.top);
        rect.right = Math.min(rect.right, i - 1);
        rect.bottom = Math.min(rect.bottom, i2 - 1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(rect, 100, byteArrayOutputStream);
        try {
            byteArrayOutputStream.close();
        } catch (IOException unused) {
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        if (i3 <= 0 || i3 >= Math.max(decodeByteArray.getWidth(), decodeByteArray.getHeight())) {
            return decodeByteArray;
        }
        float max = ((float) Math.max(decodeByteArray.getHeight(), decodeByteArray.getWidth())) / ((float) i3);
        return Bitmap.createScaledBitmap(decodeByteArray, (int) (((float) decodeByteArray.getWidth()) / max), (int) (((float) decodeByteArray.getHeight()) / max), true);
    }

    public static Point[] a(f[] fVarArr, Rect rect) {
        float width = (fVarArr[0].f6660a * ((float) rect.width())) + ((float) rect.left);
        float width2 = (fVarArr[0].f6660a * ((float) rect.width())) + ((float) rect.left);
        float height = (fVarArr[0].b * ((float) rect.height())) + ((float) rect.top);
        float height2 = (fVarArr[0].b * ((float) rect.height())) + ((float) rect.top);
        float f2 = width2;
        float f3 = width;
        for (int i = 0; i < fVarArr.length; i++) {
            float width3 = (fVarArr[i].f6660a * ((float) rect.width())) + ((float) rect.left);
            float height3 = (fVarArr[i].b * ((float) rect.height())) + ((float) rect.top);
            if (f3 > width3) {
                f3 = width3;
            }
            if (f2 < width3) {
                f2 = width3;
            }
            if (height > height3) {
                height = height3;
            }
            if (height2 < height3) {
                height2 = height3;
            }
        }
        int i2 = (int) f3;
        int i3 = (int) height;
        int i4 = (int) f2;
        int i5 = (int) height2;
        return new Point[]{new Point(i2, i3), new Point(i4, i3), new Point(i4, i5), new Point(i2, i5)};
    }

    public static int a(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[(width * height)];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        long j = 0;
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            j += (long) (((iArr[i2] >> 16) & 255) - ((((iArr[i2] >> 8) & 255) + (iArr[i2] & 255)) / 2));
            i++;
        }
        return (int) (j / ((long) i));
    }

    public static Bitmap a(Rect rect, byte[] bArr, int i, int i2) {
        YuvImage yuvImage = new YuvImage(bArr, 17, i, i2, (int[]) null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, i, i2), 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return a(rect, BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));
    }

    public static float b(Bitmap bitmap) {
        if (bitmap == null) {
            return 0.0f;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[(width * height)];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        for (int i = 0; i < iArr.length; i++) {
            j += (long) ((iArr[i] >> 16) & 255);
            j2 += (long) ((iArr[i] >> 8) & 255);
            j3 += (long) (iArr[i] & 255);
        }
        double d2 = (double) j;
        Double.isNaN(d2);
        double d3 = (double) j2;
        Double.isNaN(d3);
        double d4 = (d2 * 0.299d) + (d3 * 0.587d);
        double d5 = (double) j3;
        Double.isNaN(d5);
        double d6 = d4 + (d5 * 0.114d);
        double length = (double) iArr.length;
        Double.isNaN(length);
        return (float) (d6 / length);
    }

    public static Bitmap a(Rect rect, Bitmap bitmap) {
        float width = (float) rect.width();
        if (width > ((float) bitmap.getWidth())) {
            width = (float) bitmap.getWidth();
        }
        float height = (float) rect.height();
        if (height > ((float) bitmap.getHeight())) {
            height = (float) bitmap.getHeight();
        }
        float centerX = ((float) rect.centerX()) - (width / 2.0f);
        if (centerX < 0.0f) {
            centerX = 0.0f;
        }
        float centerY = ((float) rect.centerY()) - (height / 2.0f);
        if (centerY < 0.0f) {
            centerY = 0.0f;
        }
        if (centerX + width > ((float) bitmap.getWidth())) {
            width = ((float) bitmap.getWidth()) - centerX;
        }
        if (centerY + height > ((float) bitmap.getHeight())) {
            height = ((float) bitmap.getHeight()) - centerY;
        }
        return Bitmap.createBitmap(bitmap, (int) centerX, (int) centerY, (int) width, (int) height);
    }
}
