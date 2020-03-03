package com.megvii.idcardquality;

import android.graphics.Bitmap;
import android.util.Log;
import com.megvii.idcard.sdk.a;
import com.megvii.idcard.sdk.jni.IDCardApi;
import com.megvii.idcardquality.bean.IDCardAttr;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class IDCardQualityResult {

    /* renamed from: a  reason: collision with root package name */
    public IDCardAttr f6665a;
    public List<IDCardFailedType> b;
    private byte[] c;
    private int d;
    private int e;
    private a f;

    public enum IDCardFailedType {
        QUALITY_FAILED_TYPE_NONE,
        QUALITY_FAILED_TYPE_ERRORARGUMENT,
        QUALITY_FAILED_TYPE_NOIDCARD,
        QUALITY_FAILED_TYPE_WRONGSIDE,
        QUALITY_FAILED_TYPE_TILT,
        QUALITY_FAILED_TYPE_BLUR,
        QUALITY_FAILED_TYPE_SIZETOOSMALL,
        QUALITY_FAILED_TYPE_SIZETOOLARGE,
        QUALITY_FAILED_TYPE_SPECULARHIGHLIGHT,
        QUALITY_FAILED_TYPE_OUTSIDETHEROI,
        QUALITY_FAILED_TYPE_BRIGHTNESSTOOHIGH,
        QUALITY_FAILED_TYPE_BRIGHTNESSTOOLOW,
        QUALITY_FAILED_TYPE_SHADOW
    }

    public IDCardQualityResult(a aVar, byte[] bArr, int i, int i2) {
        this.f = aVar;
        this.c = bArr;
        this.e = i2;
        this.d = i;
    }

    public boolean a() {
        return (this.f6665a == null || this.b == null || this.b.size() != 0) ? false : true;
    }

    public Bitmap a(int i) {
        if (this.f6665a == null || this.f6665a.b == null) {
            return null;
        }
        return a.a(this.c, this.d, this.e, a.a(this.f6665a.b, 0.05f), i);
    }

    public Bitmap b(int i) {
        Log.w("ceshi", "attr===" + this.f6665a + ", " + this.f6665a.c + ", " + this.f6665a.o);
        if (this.f6665a == null || this.f6665a.c == null || this.f6665a.o != IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT) {
            return null;
        }
        return a.a(this.c, this.d, this.e, a.a(this.f6665a.c), i);
    }

    public Bitmap b() {
        return a(-1);
    }

    public Bitmap c() {
        return b(-1);
    }

    public byte[] a(boolean z, int i, int i2, boolean z2, boolean z3, int i3) {
        Bitmap bitmap;
        if (((z2 || z3) && i3 < 0) || i < 0 || i > 100) {
            return null;
        }
        if (z) {
            bitmap = b(i2);
        } else {
            bitmap = a(i2);
        }
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        try {
            byteArrayOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (byteArray == null || byteArray.length == 0) {
                return null;
            }
            if (!z2 && !z3) {
                return byteArray;
            }
            if (i3 < 0) {
                return null;
            }
            return IDCardApi.embedToJpgImgData(byteArray, z2 ? 1 : 0, z3 ? 1 : 0, i3);
        } catch (IOException unused) {
            return null;
        }
    }
}
