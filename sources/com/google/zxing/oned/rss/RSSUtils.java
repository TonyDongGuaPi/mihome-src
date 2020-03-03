package com.google.zxing.oned.rss;

public final class RSSUtils {
    private RSSUtils() {
    }

    public static int getRSSvalue(int[] iArr, int i, boolean z) {
        int[] iArr2 = iArr;
        int i2 = i;
        int i3 = 0;
        for (int i4 : iArr2) {
            i3 += i4;
        }
        int length = iArr2.length;
        int i5 = i3;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (true) {
            int i9 = length - 1;
            if (i6 >= i9) {
                return i8;
            }
            int i10 = 1 << i6;
            int i11 = i7 | i10;
            int i12 = 1;
            while (i12 < iArr2[i6]) {
                int i13 = i5 - i12;
                int i14 = length - i6;
                int i15 = i14 - 2;
                int combins = combins(i13 - 1, i15);
                if (z && i11 == 0) {
                    int i16 = i14 - 1;
                    if (i13 - i16 >= i16) {
                        combins -= combins(i13 - i14, i15);
                    }
                }
                if (i14 - 1 > 1) {
                    int i17 = i13 - i15;
                    int i18 = 0;
                    while (i17 > i2) {
                        i18 += combins((i13 - i17) - 1, i14 - 3);
                        i17--;
                        int[] iArr3 = iArr;
                    }
                    combins -= i18 * (i9 - i6);
                } else if (i13 > i2) {
                    combins--;
                }
                i8 += combins;
                i12++;
                i11 &= i10 ^ -1;
                iArr2 = iArr;
            }
            i5 -= i12;
            i6++;
            i7 = i11;
            iArr2 = iArr;
        }
    }

    private static int combins(int i, int i2) {
        int i3 = i - i2;
        if (i3 > i2) {
            int i4 = i3;
            i3 = i2;
            i2 = i4;
        }
        int i5 = 1;
        int i6 = 1;
        while (i > i2) {
            i5 *= i;
            if (i6 <= i3) {
                i5 /= i6;
                i6++;
            }
            i--;
        }
        while (i6 <= i3) {
            i5 /= i6;
            i6++;
        }
        return i5;
    }
}
