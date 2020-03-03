package com.xiaomi.zxing.oned;

import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.EncodeHintType;
import com.xiaomi.zxing.WriterException;
import com.xiaomi.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Map;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public final class Code128Writer extends OneDimensionalCodeWriter {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1691a = 104;
    private static final int b = 105;
    private static final int c = 100;
    private static final int d = 99;
    private static final int e = 106;
    private static final char f = 'ñ';
    private static final char g = 'ò';
    private static final char h = 'ó';
    private static final char i = 'ô';
    private static final int j = 102;
    private static final int k = 97;
    private static final int l = 96;
    private static final int m = 100;

    private enum CType {
        UNCODABLE,
        ONE_DIGIT,
        TWO_DIGITS,
        FNC_1
    }

    public BitMatrix a(String str, BarcodeFormat barcodeFormat, int i2, int i3, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.CODE_128) {
            return super.a(str, barcodeFormat, i2, i3, map);
        }
        throw new IllegalArgumentException("Can only encode CODE_128, but got " + barcodeFormat);
    }

    public boolean[] a(String str) {
        int i2;
        int length = str.length();
        if (length < 1 || length > 80) {
            throw new IllegalArgumentException("Contents length should be between 1 and 80 characters, but got " + length);
        }
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            char charAt = str.charAt(i4);
            if (charAt < ' ' || charAt > '~') {
                switch (charAt) {
                    case 241:
                    case 242:
                    case 243:
                    case IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE /*244*/:
                        break;
                    default:
                        throw new IllegalArgumentException("Bad character in input: " + charAt);
                }
            }
        }
        ArrayList<int[]> arrayList = new ArrayList<>();
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 1;
        while (i5 < length) {
            int a2 = a(str, i5, i6);
            int i9 = 100;
            if (a2 == i6) {
                switch (str.charAt(i5)) {
                    case 241:
                        i9 = 102;
                        break;
                    case 242:
                        i9 = 97;
                        break;
                    case 243:
                        i9 = 96;
                        break;
                    case IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE /*244*/:
                        break;
                    default:
                        if (i6 != 100) {
                            i9 = Integer.parseInt(str.substring(i5, i5 + 2));
                            i5++;
                            break;
                        } else {
                            i9 = str.charAt(i5) - ' ';
                            break;
                        }
                }
                i5++;
            } else {
                i2 = i6 == 0 ? a2 == 100 ? 104 : 105 : a2;
                i6 = a2;
            }
            arrayList.add(Code128Reader.f1690a[i2]);
            i7 += i2 * i8;
            if (i5 != 0) {
                i8++;
            }
        }
        arrayList.add(Code128Reader.f1690a[i7 % 103]);
        arrayList.add(Code128Reader.f1690a[106]);
        int i10 = 0;
        for (int[] iArr : arrayList) {
            int i11 = i10;
            for (int i12 : (int[]) r12.next()) {
                i11 += i12;
            }
            i10 = i11;
        }
        boolean[] zArr = new boolean[i10];
        for (int[] b2 : arrayList) {
            i3 += b(zArr, i3, b2, true);
        }
        return zArr;
    }

    private static CType a(CharSequence charSequence, int i2) {
        int length = charSequence.length();
        if (i2 >= length) {
            return CType.UNCODABLE;
        }
        char charAt = charSequence.charAt(i2);
        if (charAt == 241) {
            return CType.FNC_1;
        }
        if (charAt < '0' || charAt > '9') {
            return CType.UNCODABLE;
        }
        int i3 = i2 + 1;
        if (i3 >= length) {
            return CType.ONE_DIGIT;
        }
        char charAt2 = charSequence.charAt(i3);
        if (charAt2 < '0' || charAt2 > '9') {
            return CType.ONE_DIGIT;
        }
        return CType.TWO_DIGITS;
    }

    private static int a(CharSequence charSequence, int i2, int i3) {
        CType a2;
        CType a3;
        CType a4 = a(charSequence, i2);
        if (a4 == CType.UNCODABLE || a4 == CType.ONE_DIGIT) {
            return 100;
        }
        if (i3 == 99) {
            return i3;
        }
        if (i3 != 100) {
            if (a4 == CType.FNC_1) {
                a4 = a(charSequence, i2 + 1);
            }
            return a4 == CType.TWO_DIGITS ? 99 : 100;
        } else if (a4 == CType.FNC_1 || (a2 = a(charSequence, i2 + 2)) == CType.UNCODABLE || a2 == CType.ONE_DIGIT) {
            return i3;
        } else {
            if (a2 == CType.FNC_1) {
                return a(charSequence, i2 + 3) == CType.TWO_DIGITS ? 99 : 100;
            }
            int i4 = i2 + 4;
            while (true) {
                a3 = a(charSequence, i4);
                if (a3 != CType.TWO_DIGITS) {
                    break;
                }
                i4 += 2;
            }
            return a3 == CType.ONE_DIGIT ? 100 : 99;
        }
    }
}
