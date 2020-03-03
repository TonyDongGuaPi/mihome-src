package com.xiaomi.zxing.pdf417.decoder;

import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.ResultPoint;

final class DetectionResultRowIndicatorColumn extends DetectionResultColumn {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f1741a;

    DetectionResultRowIndicatorColumn(BoundingBox boundingBox, boolean z) {
        super(boundingBox);
        this.f1741a = z;
    }

    /* access modifiers changed from: package-private */
    public void c() {
        for (Codeword codeword : b()) {
            if (codeword != null) {
                codeword.b();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int a(BarcodeMetadata barcodeMetadata) {
        Codeword[] b = b();
        c();
        a(b, barcodeMetadata);
        BoundingBox a2 = a();
        ResultPoint e = this.f1741a ? a2.e() : a2.f();
        ResultPoint g = this.f1741a ? a2.g() : a2.h();
        int b2 = b((int) e.b());
        int b3 = b((int) g.b());
        float c = ((float) (b3 - b2)) / ((float) barcodeMetadata.c());
        int i = -1;
        int i2 = 0;
        int i3 = 1;
        while (b2 < b3) {
            if (b[b2] != null) {
                Codeword codeword = b[b2];
                int h = codeword.h() - i;
                if (h == 0) {
                    i2++;
                } else {
                    if (h == 1) {
                        i3 = Math.max(i3, i2);
                        i = codeword.h();
                    } else if (h < 0 || codeword.h() >= barcodeMetadata.c() || h > b2) {
                        b[b2] = null;
                    } else {
                        if (i3 > 2) {
                            h *= i3 - 2;
                        }
                        boolean z = h >= b2;
                        for (int i4 = 1; i4 <= h && !z; i4++) {
                            z = b[b2 - i4] != null;
                        }
                        if (z) {
                            b[b2] = null;
                        } else {
                            i = codeword.h();
                        }
                    }
                    i2 = 1;
                }
            }
            b2++;
        }
        double d = (double) c;
        Double.isNaN(d);
        return (int) (d + 0.5d);
    }

    /* access modifiers changed from: package-private */
    public int[] d() throws FormatException {
        int h;
        BarcodeMetadata e = e();
        if (e == null) {
            return null;
        }
        b(e);
        int[] iArr = new int[e.c()];
        for (Codeword codeword : b()) {
            if (codeword != null && (h = codeword.h()) < iArr.length) {
                iArr[h] = iArr[h] + 1;
            }
        }
        return iArr;
    }

    /* access modifiers changed from: package-private */
    public int b(BarcodeMetadata barcodeMetadata) {
        BoundingBox a2 = a();
        ResultPoint e = this.f1741a ? a2.e() : a2.f();
        ResultPoint g = this.f1741a ? a2.g() : a2.h();
        int b = b((int) e.b());
        int b2 = b((int) g.b());
        float c = ((float) (b2 - b)) / ((float) barcodeMetadata.c());
        Codeword[] b3 = b();
        int i = -1;
        int i2 = 0;
        int i3 = 1;
        while (b < b2) {
            if (b3[b] != null) {
                Codeword codeword = b3[b];
                codeword.b();
                int h = codeword.h() - i;
                if (h == 0) {
                    i2++;
                } else {
                    if (h == 1) {
                        i3 = Math.max(i3, i2);
                        i = codeword.h();
                    } else if (codeword.h() >= barcodeMetadata.c()) {
                        b3[b] = null;
                    } else {
                        i = codeword.h();
                    }
                    i2 = 1;
                }
            }
            b++;
        }
        double d = (double) c;
        Double.isNaN(d);
        return (int) (d + 0.5d);
    }

    /* access modifiers changed from: package-private */
    public BarcodeMetadata e() {
        Codeword[] b = b();
        BarcodeValue barcodeValue = new BarcodeValue();
        BarcodeValue barcodeValue2 = new BarcodeValue();
        BarcodeValue barcodeValue3 = new BarcodeValue();
        BarcodeValue barcodeValue4 = new BarcodeValue();
        for (Codeword codeword : b) {
            if (codeword != null) {
                codeword.b();
                int g = codeword.g() % 30;
                int h = codeword.h();
                if (!this.f1741a) {
                    h += 2;
                }
                switch (h % 3) {
                    case 0:
                        barcodeValue2.a((g * 3) + 1);
                        break;
                    case 1:
                        barcodeValue4.a(g / 3);
                        barcodeValue3.a(g % 3);
                        break;
                    case 2:
                        barcodeValue.a(g + 1);
                        break;
                }
            }
        }
        if (barcodeValue.a().length == 0 || barcodeValue2.a().length == 0 || barcodeValue3.a().length == 0 || barcodeValue4.a().length == 0 || barcodeValue.a()[0] < 1 || barcodeValue2.a()[0] + barcodeValue3.a()[0] < 3 || barcodeValue2.a()[0] + barcodeValue3.a()[0] > 90) {
            return null;
        }
        BarcodeMetadata barcodeMetadata = new BarcodeMetadata(barcodeValue.a()[0], barcodeValue2.a()[0], barcodeValue3.a()[0], barcodeValue4.a()[0]);
        a(b, barcodeMetadata);
        return barcodeMetadata;
    }

    private void a(Codeword[] codewordArr, BarcodeMetadata barcodeMetadata) {
        for (int i = 0; i < codewordArr.length; i++) {
            Codeword codeword = codewordArr[i];
            if (codewordArr[i] != null) {
                int g = codeword.g() % 30;
                int h = codeword.h();
                if (h <= barcodeMetadata.c()) {
                    if (!this.f1741a) {
                        h += 2;
                    }
                    switch (h % 3) {
                        case 0:
                            if ((g * 3) + 1 == barcodeMetadata.d()) {
                                break;
                            } else {
                                codewordArr[i] = null;
                                break;
                            }
                        case 1:
                            if (g / 3 != barcodeMetadata.b() || g % 3 != barcodeMetadata.e()) {
                                codewordArr[i] = null;
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (g + 1 == barcodeMetadata.a()) {
                                break;
                            } else {
                                codewordArr[i] = null;
                                break;
                            }
                    }
                } else {
                    codewordArr[i] = null;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean f() {
        return this.f1741a;
    }

    public String toString() {
        return "IsLeft: " + this.f1741a + 10 + super.toString();
    }
}
