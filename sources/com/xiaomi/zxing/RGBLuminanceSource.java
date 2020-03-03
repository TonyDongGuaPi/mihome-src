package com.xiaomi.zxing;

import com.tencent.smtt.sdk.TbsListener;

public final class RGBLuminanceSource extends LuminanceSource {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f1630a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;

    public boolean b() {
        return true;
    }

    public RGBLuminanceSource(int i, int i2, int[] iArr) {
        super(i, i2);
        this.b = i;
        this.c = i2;
        this.d = 0;
        this.e = 0;
        int i3 = i * i2;
        this.f1630a = new byte[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = iArr[i4];
            this.f1630a[i4] = (byte) (((((i5 >> 16) & 255) + ((i5 >> 7) & TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_MINIQBSUCCESS)) + (i5 & 255)) / 4);
        }
    }

    private RGBLuminanceSource(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6) {
        super(i5, i6);
        if (i5 + i3 > i || i6 + i4 > i2) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        this.f1630a = bArr;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
    }

    public byte[] a(int i, byte[] bArr) {
        if (i < 0 || i >= h()) {
            throw new IllegalArgumentException("Requested row is outside the image: " + i);
        }
        int g = g();
        if (bArr == null || bArr.length < g) {
            bArr = new byte[g];
        }
        System.arraycopy(this.f1630a, ((i + this.e) * this.b) + this.d, bArr, 0, g);
        return bArr;
    }

    public byte[] a() {
        int g = g();
        int h = h();
        if (g == this.b && h == this.c) {
            return this.f1630a;
        }
        int i = g * h;
        byte[] bArr = new byte[i];
        int i2 = (this.e * this.b) + this.d;
        if (g == this.b) {
            System.arraycopy(this.f1630a, i2, bArr, 0, i);
            return bArr;
        }
        byte[] bArr2 = this.f1630a;
        for (int i3 = 0; i3 < h; i3++) {
            System.arraycopy(bArr2, i2, bArr, i3 * g, g);
            i2 += this.b;
        }
        return bArr;
    }

    public LuminanceSource a(int i, int i2, int i3, int i4) {
        return new RGBLuminanceSource(this.f1630a, this.b, this.c, this.d + i, this.e + i2, i3, i4);
    }
}
