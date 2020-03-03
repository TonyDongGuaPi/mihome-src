package com.xiaomi.msg.fec;

import com.xiaomi.msg.common.Helper;
import com.xiaomi.msg.logger.MIMCLog;
import java.lang.reflect.Array;

public class FEC {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12109a = "FEC";
    private int b;
    private int c;
    private int[][] d;
    private int[][] e;

    public FEC(int i, int i2) {
        this.b = i;
        this.c = i2;
        this.d = (int[][]) Array.newInstance(int.class, new int[]{i2, i});
        this.e = (int[][]) Array.newInstance(int.class, new int[]{i, i});
        b();
    }

    private void b() {
        for (int i = 0; i < this.c; i++) {
            for (int i2 = 1; i2 <= this.b; i2++) {
                this.d[i][i2 - 1] = Galois.a().e(i2, i);
            }
        }
    }

    public int[][] a() {
        return this.d;
    }

    public int[][] a(int[][] iArr) {
        b(iArr);
        return this.e;
    }

    public boolean a(byte[][] bArr, int i, byte[][] bArr2) {
        long currentTimeMillis = System.currentTimeMillis();
        for (int i2 = 0; i2 < this.c; i2++) {
            for (int i3 = 0; i3 < this.b; i3++) {
                for (int i4 = 0; i4 < i; i4++) {
                    bArr2[i2][i4] = (byte) Galois.a().a(Helper.a(bArr2[i2][i4]), Galois.a().c(this.d[i2][i3], Helper.a(bArr[i3][i4])));
                }
            }
        }
        MIMCLog.a(f12109a, String.format("encode use time2 %d", new Object[]{Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}));
        return true;
    }

    public boolean b(byte[][] bArr, int i, byte[][] bArr2) {
        long currentTimeMillis = System.currentTimeMillis();
        for (int i2 = 0; i2 < this.b; i2++) {
            for (int i3 = 0; i3 < this.b; i3++) {
                for (int i4 = 0; i4 < i; i4++) {
                    bArr2[i2][i4] = (byte) Galois.a().a(Helper.a(bArr2[i2][i4]), Galois.a().c(this.e[i2][i3], Helper.a(bArr[i3][i4])));
                }
            }
        }
        MIMCLog.a(f12109a, String.format("decode use time3 %d", new Object[]{Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}));
        return true;
    }

    public boolean b(int[][] iArr) {
        int[][] iArr2 = iArr;
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = false;
        for (int[] length : iArr2) {
            if (iArr2.length != length.length) {
                MIMCLog.c(f12109a, "reverseMatrix input matrix must has the same cols and lines");
                return false;
            }
        }
        int i = this.b;
        int[][] iArr3 = this.e;
        for (int i2 = 0; i2 < i; i2++) {
            iArr3[i2][i2] = 1;
        }
        int i3 = 0;
        while (i3 < i) {
            int i4 = i3;
            while (true) {
                if (i4 >= i) {
                    break;
                } else if (iArr2[i4][i3] != 0) {
                    a(iArr, iArr3, i, i3, i4);
                    break;
                } else {
                    i4++;
                }
            }
            if (iArr2[i3][i3] == 0) {
                MIMCLog.c(f12109a, "reverseMatrix the input matrix has no reverse matrix");
                return z;
            }
            int i5 = i - 1;
            for (int i6 = i5; i6 >= 0; i6--) {
                iArr3[i3][i6] = Galois.a().d(iArr3[i3][i6], iArr2[i3][i3]);
            }
            for (int i7 = i5; i7 >= i3; i7--) {
                iArr2[i3][i7] = Galois.a().d(iArr2[i3][i7], iArr2[i3][i3]);
            }
            for (int i8 = 0; i8 < i; i8++) {
                if (i8 != i3) {
                    for (int i9 = i5; i9 >= 0; i9--) {
                        iArr3[i8][i9] = Galois.a().b(iArr3[i8][i9], Galois.a().c(iArr2[i8][i3], iArr3[i3][i9]));
                    }
                    for (int i10 = i5; i10 >= i3; i10--) {
                        iArr2[i8][i10] = Galois.a().b(iArr2[i8][i10], Galois.a().c(iArr2[i8][i3], iArr2[i3][i10]));
                    }
                }
            }
            i3++;
            z = false;
        }
        for (int i11 = i - 1; i11 > 0; i11--) {
            for (int i12 = i11 - 1; i12 >= 0; i12--) {
                for (int i13 = 0; i13 < i; i13++) {
                    iArr3[i12][i13] = Galois.a().b(iArr3[i12][i13], Galois.a().c(iArr2[i12][i11], iArr3[i11][i13]));
                }
                iArr2[i12][i11] = 0;
            }
        }
        MIMCLog.a(f12109a, String.format("reverseMatrix use time1:%d", new Object[]{Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}));
        return true;
    }

    private void a(int[][] iArr, int[][] iArr2, int i, int i2, int i3) {
        int[] iArr3 = new int[i];
        int[] iArr4 = new int[i];
        for (int i4 = 0; i4 < i; i4++) {
            iArr3[i4] = iArr[i2][i4];
            iArr4[i4] = iArr2[i2][i4];
        }
        for (int i5 = 0; i5 < i; i5++) {
            iArr[i2][i5] = iArr[i3][i5];
            iArr2[i2][i5] = iArr2[i3][i5];
            iArr[i3][i5] = iArr3[i5];
            iArr2[i3][i5] = iArr4[i5];
        }
    }
}
