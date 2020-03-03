package com.xiaomi.zxing.oned;

import com.drew.metadata.exif.makernotes.OlympusRawInfoMakernoteDirectory;
import com.taobao.weex.el.parse.Operators;
import com.tencent.smtt.sdk.TbsListener;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.ChecksumException;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.Result;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

public final class Code39Reader extends OneDReader {

    /* renamed from: a  reason: collision with root package name */
    static final String f1692a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
    static final int[] b = {52, 289, 97, 352, 49, 304, 112, 37, OlympusRawInfoMakernoteDirectory.l, 100, 265, 73, TbsListener.ErrorCode.THROWABLE_INITTESRUNTIMEENVIRONMENT, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 385, 193, 448, 145, 400, 208, 133, 388, Downloads.STATUS_QUEUED_FOR_WIFI, 148, 168, 162, 138, 42};
    static final int c = b[39];
    private static final String d = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%";
    private final boolean e;
    private final boolean f;
    private final StringBuilder g;
    private final int[] h;

    public Code39Reader() {
        this(false);
    }

    public Code39Reader(boolean z) {
        this(z, false);
    }

    public Code39Reader(boolean z, boolean z2) {
        this.e = z;
        this.f = z2;
        this.g = new StringBuilder(20);
        this.h = new int[9];
    }

    public Result a(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        String str;
        int[] iArr = this.h;
        Arrays.fill(iArr, 0);
        StringBuilder sb = this.g;
        sb.setLength(0);
        int[] a2 = a(bitArray, iArr);
        int d2 = bitArray.d(a2[1]);
        int a3 = bitArray.a();
        while (true) {
            a(bitArray, d2, iArr);
            int a4 = a(iArr);
            if (a4 >= 0) {
                char a5 = a(a4);
                sb.append(a5);
                int i2 = d2;
                for (int i3 : iArr) {
                    i2 += i3;
                }
                int d3 = bitArray.d(i2);
                if (a5 == '*') {
                    sb.setLength(sb.length() - 1);
                    int i4 = 0;
                    for (int i5 : iArr) {
                        i4 += i5;
                    }
                    int i6 = (d3 - d2) - i4;
                    if (d3 == a3 || i6 * 2 >= i4) {
                        if (this.e) {
                            int length = sb.length() - 1;
                            int i7 = 0;
                            for (int i8 = 0; i8 < length; i8++) {
                                i7 += d.indexOf(this.g.charAt(i8));
                            }
                            if (sb.charAt(length) == d.charAt(i7 % 43)) {
                                sb.setLength(length);
                            } else {
                                throw ChecksumException.getChecksumInstance();
                            }
                        }
                        if (sb.length() != 0) {
                            if (this.f) {
                                str = a((CharSequence) sb);
                            } else {
                                str = sb.toString();
                            }
                            float f2 = (float) i;
                            return new Result(str, (byte[]) null, new ResultPoint[]{new ResultPoint(((float) (a2[1] + a2[0])) / 2.0f, f2), new ResultPoint(((float) d2) + (((float) i4) / 2.0f), f2)}, BarcodeFormat.CODE_39);
                        }
                        throw NotFoundException.getNotFoundInstance();
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
                d2 = d3;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    private static int[] a(BitArray bitArray, int[] iArr) throws NotFoundException {
        int a2 = bitArray.a();
        int d2 = bitArray.d(0);
        int length = iArr.length;
        int i = d2;
        boolean z = false;
        int i2 = 0;
        while (d2 < a2) {
            if (bitArray.a(d2) ^ z) {
                iArr[i2] = iArr[i2] + 1;
            } else {
                int i3 = length - 1;
                if (i2 != i3) {
                    i2++;
                } else if (a(iArr) != c || !bitArray.a(Math.max(0, i - ((d2 - i) / 2)), i, false)) {
                    i += iArr[0] + iArr[1];
                    int i4 = length - 2;
                    System.arraycopy(iArr, 2, iArr, 0, i4);
                    iArr[i4] = 0;
                    iArr[i3] = 0;
                    i2--;
                } else {
                    return new int[]{i, d2};
                }
                iArr[i2] = 1;
                z = !z;
            }
            d2++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int a(int[] iArr) {
        int length = iArr.length;
        int i = 0;
        while (true) {
            int i2 = Integer.MAX_VALUE;
            for (int i3 : iArr) {
                if (i3 < i2 && i3 > i) {
                    i2 = i3;
                }
            }
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < length; i7++) {
                int i8 = iArr[i7];
                if (i8 > i2) {
                    i5 |= 1 << ((length - 1) - i7);
                    i4++;
                    i6 += i8;
                }
            }
            if (i4 == 3) {
                for (int i9 = 0; i9 < length && i4 > 0; i9++) {
                    int i10 = iArr[i9];
                    if (i10 > i2) {
                        i4--;
                        if (i10 * 2 >= i6) {
                            return -1;
                        }
                    }
                }
                return i5;
            } else if (i4 <= 3) {
                return -1;
            } else {
                i = i2;
            }
        }
    }

    private static char a(int i) throws NotFoundException {
        for (int i2 = 0; i2 < b.length; i2++) {
            if (b[i2] == i) {
                return f1692a.charAt(i2);
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static String a(CharSequence charSequence) throws FormatException {
        char c2;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt == '+' || charAt == '$' || charAt == '%' || charAt == '/') {
                i++;
                char charAt2 = charSequence.charAt(i);
                if (charAt != '+') {
                    if (charAt != '/') {
                        switch (charAt) {
                            case '$':
                                if (charAt2 >= 'A' && charAt2 <= 'Z') {
                                    c2 = (char) (charAt2 - '@');
                                    break;
                                } else {
                                    throw FormatException.getFormatInstance();
                                }
                                break;
                            case '%':
                                if (charAt2 < 'A' || charAt2 > 'E') {
                                    if (charAt2 >= 'F' && charAt2 <= 'W') {
                                        c2 = (char) (charAt2 - 11);
                                        break;
                                    } else {
                                        throw FormatException.getFormatInstance();
                                    }
                                } else {
                                    c2 = (char) (charAt2 - '&');
                                    break;
                                }
                                break;
                            default:
                                c2 = 0;
                                break;
                        }
                    } else if (charAt2 >= 'A' && charAt2 <= 'O') {
                        c2 = (char) (charAt2 - ' ');
                    } else if (charAt2 == 'Z') {
                        c2 = Operators.CONDITION_IF_MIDDLE;
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                } else if (charAt2 < 'A' || charAt2 > 'Z') {
                    throw FormatException.getFormatInstance();
                } else {
                    c2 = (char) (charAt2 + ' ');
                }
                sb.append(c2);
            } else {
                sb.append(charAt);
            }
            i++;
        }
        return sb.toString();
    }
}
