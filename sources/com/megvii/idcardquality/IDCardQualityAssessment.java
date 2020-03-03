package com.megvii.idcardquality;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import com.megvii.idcard.sdk.a;
import com.megvii.idcard.sdk.jni.IDCardApi;
import com.megvii.idcardquality.IDCardQualityResult;
import com.megvii.idcardquality.bean.IDCardAttr;
import java.util.ArrayList;

public class IDCardQualityAssessment {

    /* renamed from: a  reason: collision with root package name */
    public int f6662a;
    public float b;
    public float c;
    public float d;
    public boolean e;
    public boolean f;
    public String g;
    private a h;
    private int i;

    /* synthetic */ IDCardQualityAssessment(Builder builder, byte b2) {
        this(builder);
    }

    public IDCardQualityAssessment() {
        IDCardAttr.IDCardSide iDCardSide = IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT;
        this.f6662a = 2;
        this.b = 0.5f;
        this.c = 0.5f;
        this.d = 0.5f;
        this.e = false;
        this.f = false;
        this.h = new a();
    }

    private IDCardQualityAssessment(Builder builder) {
        IDCardAttr.IDCardSide iDCardSide = IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT;
        this.f6662a = 2;
        this.b = 0.5f;
        this.c = 0.5f;
        this.d = 0.5f;
        this.e = false;
        this.f = false;
        this.h = new a();
        this.b = builder.f6663a;
        this.c = builder.b;
        this.d = builder.c;
        this.f = builder.e;
        this.e = builder.d;
    }

    public boolean a(Context context, byte[] bArr) {
        this.g = this.h.a(context, bArr);
        return this.g == null;
    }

    public void b() {
        this.h.c();
    }

    public IDCardQualityResult a(byte[] bArr, int i2, int i3, IDCardAttr.IDCardSide iDCardSide, Rect rect) {
        return a(bArr, i2, i3, iDCardSide, rect, this.f6662a);
    }

    public IDCardQualityResult a(byte[] bArr, int i2, int i3, IDCardAttr.IDCardSide iDCardSide, Rect rect, int i4) {
        byte[] bArr2 = bArr;
        int i5 = i2;
        int i6 = i3;
        IDCardAttr.IDCardSide iDCardSide2 = iDCardSide;
        IDCardQualityResult iDCardQualityResult = new IDCardQualityResult(this.h, bArr2, i5, i6);
        iDCardQualityResult.b = new ArrayList();
        if (bArr2 == null || i5 == 0 || i6 == 0 || iDCardSide2 == null) {
            iDCardQualityResult.b.add(IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_ERRORARGUMENT);
            return iDCardQualityResult;
        }
        Rect rect2 = rect == null ? new Rect(0, 0, i5, i6) : rect;
        a(i2, i3, rect2, iDCardSide, this.i, i4);
        System.currentTimeMillis();
        a.d a2 = this.h.a(bArr2, i5, i6, i4);
        if (a2 == null) {
            iDCardQualityResult.b.add(IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_ERRORARGUMENT);
            return iDCardQualityResult;
        }
        float f2 = a2.f6658a;
        float f3 = a2.b;
        float f4 = a2.c;
        IDCardAttr iDCardAttr = new IDCardAttr();
        iDCardAttr.k = f2;
        iDCardAttr.l = f3;
        iDCardAttr.f6666a = f4;
        iDCardAttr.d = new float[]{0.0f, 0.0f, 0.0f};
        iDCardAttr.j = IDCardAttr.IDCardType.NORMAL;
        iDCardAttr.o = iDCardSide2;
        iDCardAttr.m = 0;
        iDCardAttr.n = 0;
        if (f2 < this.d) {
            iDCardQualityResult.b.add(IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_OUTSIDETHEROI);
        }
        if (f3 < this.c) {
            iDCardQualityResult.b.add(IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_OUTSIDETHEROI);
        }
        if (f4 < this.b) {
            iDCardQualityResult.b.add(IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_BLUR);
        }
        if (f2 >= this.d && f3 >= this.c && f4 >= this.b) {
            a.e b2 = this.h.b();
            iDCardAttr.m = (b2 == null || b2.c == null) ? 0 : b2.c.length;
            iDCardAttr.n = (b2 == null || b2.d == null) ? 0 : b2.d.length;
            iDCardAttr.e = iDCardAttr.n > 0;
            iDCardAttr.f = iDCardAttr.m > 0;
            iDCardAttr.g = b2.c;
            iDCardAttr.h = b2.d;
            iDCardAttr.i = b2.e;
            if (!this.f && !b2.f6659a) {
                iDCardQualityResult.b.add(0, IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_SHADOW);
            }
            if (!this.e && !b2.b) {
                iDCardQualityResult.b.add(0, IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_SPECULARHIGHLIGHT);
            }
            if ((this.e || b2.b) && (this.f || b2.f6659a)) {
                Point[] a3 = a.a(b2.e[0].c, rect2);
                Bitmap a4 = a.a(a.a(a3), bArr2, i5, i6);
                Bitmap a5 = a.a(new Rect((int) (((float) a4.getWidth()) * 0.06f), (int) (((float) a4.getHeight()) * 0.08594f), (int) (((float) a4.getWidth()) * 0.255f), (int) (((float) a4.getHeight()) * 0.41406f)), a4);
                IDCardAttr.s = a5;
                int a6 = a.a(a5);
                Log.w("ceshi", "NE_mean===" + a6);
                Bitmap a7 = a.a(new Rect((int) (((float) a4.getWidth()) * 0.35f), (int) (((float) a4.getHeight()) * 0.35f), (int) (((float) a4.getWidth()) * 0.65f), (int) (((float) a4.getHeight()) * 0.65f)), a4);
                IDCardAttr.t = a7;
                int a8 = (int) (((float) (a.a(a7) + 5)) * 1.5f);
                Log.w("ceshi", "c_mean===" + a8);
                if (a6 < a8) {
                    if (iDCardSide2 != IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT) {
                        iDCardQualityResult.b.add(0, IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_WRONGSIDE);
                        return iDCardQualityResult;
                    }
                    Rect rect3 = new Rect();
                    int width = (int) ((((float) a4.getWidth()) * 0.6225f) + ((float) a3[0].x));
                    int height = (int) ((((float) a4.getHeight()) * 0.16633664f) + ((float) a3[0].y));
                    int width2 = (int) ((((float) a4.getWidth()) * 0.9375f) + ((float) a3[0].x));
                    int height2 = (int) ((((float) a4.getHeight()) * 0.740594f) + ((float) a3[0].y));
                    rect3.left = width;
                    rect3.top = height;
                    rect3.right = width2;
                    rect3.bottom = height2;
                    iDCardAttr.c = new Point[]{new Point(width, height), new Point(width2, height), new Point(width2, height2), new Point(width, height2)};
                    iDCardAttr.q = a.a(rect3, bArr2, i5, i6);
                } else if (iDCardSide2 == IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT) {
                    iDCardQualityResult.b.add(0, IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_WRONGSIDE);
                    return iDCardQualityResult;
                }
                float b3 = a.b(a4);
                iDCardAttr.r = a4;
                iDCardAttr.p = b3;
                iDCardAttr.b = a3;
            }
        }
        iDCardQualityResult.f6665a = iDCardAttr;
        return iDCardQualityResult;
    }

    public void a(int i2, int i3, Rect rect, IDCardAttr.IDCardSide iDCardSide, int i4, int i5) {
        this.f6662a = i5;
        this.i = i4;
        a.c a2 = this.h.a();
        if (a2 != null) {
            int i6 = rect.left;
            int i7 = rect.top;
            int i8 = rect.right;
            int i9 = rect.bottom;
            a2.f6657a = i4;
            a2.b = 20.0f;
            a2.c = 20.0f;
            a2.d = 20.0f;
            a2.h = i6;
            a2.i = i7;
            a2.j = i8;
            a2.k = i9;
            a2.l = 0;
            this.h.a(a2);
        }
    }

    public static final class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public float f6663a = 0.5f;
        /* access modifiers changed from: private */
        public float b = 0.5f;
        /* access modifiers changed from: private */
        public float c = 0.5f;
        /* access modifiers changed from: private */
        public boolean d = false;
        /* access modifiers changed from: private */
        public boolean e = false;

        public final Builder a(float f) {
            this.f6663a = f;
            return this;
        }

        public final Builder b(float f) {
            this.b = f;
            return this;
        }

        public final Builder c(float f) {
            this.c = f;
            return this;
        }

        public final Builder a(boolean z) {
            this.d = z;
            return this;
        }

        public final Builder b(boolean z) {
            this.e = z;
            return this;
        }

        public final IDCardQualityAssessment a() {
            return new IDCardQualityAssessment(this, (byte) 0);
        }
    }

    public static String a() {
        return IDCardApi.nativeGetVersion();
    }
}
