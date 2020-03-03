package com.xiaomi.zxing.oned;

import com.drew.metadata.exif.ExifDirectoryBase;
import com.drew.metadata.exif.makernotes.OlympusRawInfoMakernoteDirectory;
import com.drew.metadata.iptc.IptcDirectory;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.google.common.base.Ascii;
import com.mi.global.shop.constants.UIConstant;
import com.taobao.weex.el.parse.Operators;
import com.tencent.smtt.sdk.TbsListener;
import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.ChecksumException;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.Result;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.common.BitArray;
import com.ximalaya.ting.android.xmpayordersdk.IXmPayOrderListener;
import java.util.Arrays;
import java.util.Map;
import org.apache.http.HttpStatus;

public final class Code93Reader extends OneDReader {

    /* renamed from: a  reason: collision with root package name */
    static final String f1693a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*";
    static final int[] b = {276, TbsListener.ErrorCode.THROWABLE_INITTESRUNTIMEENVIRONMENT, ExifDirectoryBase.Q, 322, 296, OlympusRawInfoMakernoteDirectory.l, OlympusRawInfoMakernoteDirectory.j, IptcDirectory.n, 274, 266, 424, 420, TbsListener.ErrorCode.INFO_CORE_EXIST_NOT_LOAD, 404, 402, 394, 360, IptcDirectory.p, 354, 308, 282, 344, 332, 326, 300, 278, 436, 434, 428, HttpStatus.SC_UNPROCESSABLE_ENTITY, 406, 410, 364, 358, UIConstant.h, TbsListener.ErrorCode.ERROR_CANLOADVIDEO_RETURN_NULL, 302, 468, 466, FacebookRequestErrorClassification.ESC_APP_NOT_INSTALLED, 366, 374, IXmPayOrderListener.y, 294, 474, 470, 306, 350};
    private static final char[] c = f1693a.toCharArray();
    private static final int d = b[47];
    private final StringBuilder e = new StringBuilder(20);
    private final int[] f = new int[6];

    public Result a(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        int[] a2 = a(bitArray);
        int d2 = bitArray.d(a2[1]);
        int a3 = bitArray.a();
        int[] iArr = this.f;
        Arrays.fill(iArr, 0);
        StringBuilder sb = this.e;
        sb.setLength(0);
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
                    sb.deleteCharAt(sb.length() - 1);
                    int i4 = 0;
                    for (int i5 : iArr) {
                        i4 += i5;
                    }
                    if (d3 == a3 || !bitArray.a(d3)) {
                        throw NotFoundException.getNotFoundInstance();
                    } else if (sb.length() >= 2) {
                        b(sb);
                        sb.setLength(sb.length() - 2);
                        float f2 = (float) i;
                        return new Result(a((CharSequence) sb), (byte[]) null, new ResultPoint[]{new ResultPoint(((float) (a2[1] + a2[0])) / 2.0f, f2), new ResultPoint(((float) d2) + (((float) i4) / 2.0f), f2)}, BarcodeFormat.CODE_93);
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                } else {
                    d2 = d3;
                }
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    private int[] a(BitArray bitArray) throws NotFoundException {
        int a2 = bitArray.a();
        int d2 = bitArray.d(0);
        Arrays.fill(this.f, 0);
        int[] iArr = this.f;
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
                } else if (a(iArr) == d) {
                    return new int[]{i, d2};
                } else {
                    i += iArr[0] + iArr[1];
                    int i4 = length - 2;
                    System.arraycopy(iArr, 2, iArr, 0, i4);
                    iArr[i4] = 0;
                    iArr[i3] = 0;
                    i2--;
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
        for (int i2 : iArr) {
            i += i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            int round = Math.round((((float) iArr[i4]) * 9.0f) / ((float) i));
            if (round < 1 || round > 4) {
                return -1;
            }
            if ((i4 & 1) == 0) {
                int i5 = i3;
                for (int i6 = 0; i6 < round; i6++) {
                    i5 = (i5 << 1) | 1;
                }
                i3 = i5;
            } else {
                i3 <<= round;
            }
        }
        return i3;
    }

    private static char a(int i) throws NotFoundException {
        for (int i2 = 0; i2 < b.length; i2++) {
            if (b[i2] == i) {
                return c[i2];
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
            if (charAt < 'a' || charAt > 'd') {
                sb.append(charAt);
            } else if (i < length - 1) {
                i++;
                char charAt2 = charSequence.charAt(i);
                switch (charAt) {
                    case 'a':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            c2 = (char) (charAt2 - '@');
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                        break;
                    case 'b':
                        if (charAt2 < 'A' || charAt2 > 'E') {
                            if (charAt2 < 'F' || charAt2 > 'J') {
                                if (charAt2 < 'K' || charAt2 > 'O') {
                                    if (charAt2 < 'P' || charAt2 > 'S') {
                                        if (charAt2 >= 'T' && charAt2 <= 'Z') {
                                            c2 = Ascii.MAX;
                                            break;
                                        } else {
                                            throw FormatException.getFormatInstance();
                                        }
                                    } else {
                                        c2 = (char) (charAt2 + '+');
                                        break;
                                    }
                                } else {
                                    c2 = (char) (charAt2 + 16);
                                    break;
                                }
                            } else {
                                c2 = (char) (charAt2 - 11);
                                break;
                            }
                        } else {
                            c2 = (char) (charAt2 - '&');
                            break;
                        }
                        break;
                    case 'c':
                        if (charAt2 >= 'A' && charAt2 <= 'O') {
                            c2 = (char) (charAt2 - ' ');
                            break;
                        } else if (charAt2 == 'Z') {
                            c2 = Operators.CONDITION_IF_MIDDLE;
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    case 'd':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            c2 = (char) (charAt2 + ' ');
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    default:
                        c2 = 0;
                        break;
                }
                sb.append(c2);
            } else {
                throw FormatException.getFormatInstance();
            }
            i++;
        }
        return sb.toString();
    }

    private static void b(CharSequence charSequence) throws ChecksumException {
        int length = charSequence.length();
        a(charSequence, length - 2, 20);
        a(charSequence, length - 1, 15);
    }

    private static void a(CharSequence charSequence, int i, int i2) throws ChecksumException {
        int i3 = 0;
        int i4 = 1;
        for (int i5 = i - 1; i5 >= 0; i5--) {
            i3 += f1693a.indexOf(charSequence.charAt(i5)) * i4;
            i4++;
            if (i4 > i2) {
                i4 = 1;
            }
        }
        if (charSequence.charAt(i) != c[i3 % 47]) {
            throw ChecksumException.getChecksumInstance();
        }
    }
}
