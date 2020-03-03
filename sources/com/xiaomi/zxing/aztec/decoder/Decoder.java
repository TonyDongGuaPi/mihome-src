package com.xiaomi.zxing.aztec.decoder;

import android.support.media.ExifInterface;
import com.alipay.sdk.sys.a;
import com.alipay.sdk.util.i;
import com.mi.global.shop.model.Tags;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.aztec.AztecDetectorResult;
import com.xiaomi.zxing.common.BitMatrix;
import com.xiaomi.zxing.common.DecoderResult;
import com.xiaomi.zxing.common.reedsolomon.GenericGF;
import com.xiaomi.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.xiaomi.zxing.common.reedsolomon.ReedSolomonException;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.util.Arrays;
import java.util.List;
import miuipub.reflect.Field;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.cybergarage.http.HTTP;

public final class Decoder {

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f1635a = {"CTRL_PS", " ", "A", Field.b, Field.c, Field.h, ExifInterface.LONGITUDE_EAST, Field.g, "G", "H", Field.e, Field.f, "K", "L", "M", "N", "O", "P", "Q", "R", "S", ExifInterface.GPS_DIRECTION_TRUE, "U", "V", "W", "X", "Y", Field.f3009a, "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] b = {"CTRL_PS", " ", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", DeviceTagInterface.q, "p", DTransferConstants.F, "r", "s", "t", "u", "v", "w", "x", Constants.Name.Y, CompressorStreamFactory.h, "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] c = {"CTRL_PS", " ", Constants.Split.CTRL_A, Constants.Split.CTRL_B, Constants.Split.CTRL_C, Constants.Split.CTRL_D, Constants.Split.CTRL_E, "\u0006", "\u0007", "\b", HTTP.TAB, "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", Tags.MiHome.TEL_SEPARATOR4, "^", JSMethod.NOT_SET, "`", "|", com.xiaomi.mipush.sdk.Constants.J, "", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    private static final String[] d = {"", "\r", "\r\n", ". ", ", ", ": ", "!", "\"", "#", Operators.DOLLAR_STR, Operators.MOD, a.b, "'", Operators.BRACKET_START_STR, Operators.BRACKET_END_STR, "*", "+", ",", "-", ".", "/", ":", i.b, "<", "=", ">", "?", Operators.ARRAY_START_STR, Operators.ARRAY_END_STR, Operators.BLOCK_START_STR, "}", "CTRL_UL"};
    private static final String[] e = {"CTRL_PS", " ", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ",", ".", "CTRL_UL", "CTRL_US"};
    private AztecDetectorResult f;

    private enum Table {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    private static int a(int i, boolean z) {
        return ((z ? 88 : 112) + (i * 16)) * i;
    }

    public DecoderResult a(AztecDetectorResult aztecDetectorResult) throws FormatException {
        this.f = aztecDetectorResult;
        boolean[] d2 = d(a(aztecDetectorResult.d()));
        return new DecoderResult(b(d2), c(d2), (List<byte[]>) null, (String) null);
    }

    public static String a(boolean[] zArr) {
        return c(zArr);
    }

    private static String c(boolean[] zArr) {
        int length = zArr.length;
        Table table = Table.UPPER;
        Table table2 = Table.UPPER;
        StringBuilder sb = new StringBuilder(20);
        Table table3 = table;
        int i = 0;
        while (i < length) {
            if (table2 == Table.BINARY) {
                if (length - i >= 5) {
                    int a2 = a(zArr, i, 5);
                    int i2 = i + 5;
                    if (a2 == 0) {
                        if (length - i2 < 11) {
                            break;
                        }
                        a2 = a(zArr, i2, 11) + 31;
                        i2 += 11;
                    }
                    int i3 = i2;
                    int i4 = 0;
                    while (true) {
                        if (i4 >= a2) {
                            i = i3;
                            break;
                        } else if (length - i3 < 8) {
                            i = length;
                            break;
                        } else {
                            sb.append((char) a(zArr, i3, 8));
                            i3 += 8;
                            i4++;
                        }
                    }
                } else {
                    break;
                }
            } else {
                int i5 = table2 == Table.DIGIT ? 4 : 5;
                if (length - i < i5) {
                    break;
                }
                int a3 = a(zArr, i, i5);
                i += i5;
                String a4 = a(table2, a3);
                if (a4.startsWith("CTRL_")) {
                    Table a5 = a(a4.charAt(5));
                    if (a4.charAt(6) == 'L') {
                        table2 = a5;
                        table3 = table2;
                    } else {
                        table2 = a5;
                    }
                } else {
                    sb.append(a4);
                }
            }
            table2 = table3;
        }
        return sb.toString();
    }

    private static Table a(char c2) {
        if (c2 == 'B') {
            return Table.BINARY;
        }
        if (c2 == 'D') {
            return Table.DIGIT;
        }
        if (c2 == 'P') {
            return Table.PUNCT;
        }
        switch (c2) {
            case 'L':
                return Table.LOWER;
            case 'M':
                return Table.MIXED;
            default:
                return Table.UPPER;
        }
    }

    private static String a(Table table, int i) {
        switch (table) {
            case UPPER:
                return f1635a[i];
            case LOWER:
                return b[i];
            case MIXED:
                return c[i];
            case PUNCT:
                return d[i];
            case DIGIT:
                return e[i];
            default:
                throw new IllegalStateException("Bad table");
        }
    }

    private boolean[] d(boolean[] zArr) throws FormatException {
        GenericGF genericGF;
        int i = 8;
        if (this.f.a() <= 2) {
            i = 6;
            genericGF = GenericGF.c;
        } else if (this.f.a() <= 8) {
            genericGF = GenericGF.g;
        } else if (this.f.a() <= 22) {
            i = 10;
            genericGF = GenericGF.b;
        } else {
            i = 12;
            genericGF = GenericGF.f1657a;
        }
        int b2 = this.f.b();
        int length = zArr.length / i;
        if (length >= b2) {
            int i2 = length - b2;
            int[] iArr = new int[length];
            int length2 = zArr.length % i;
            int i3 = 0;
            while (i3 < length) {
                iArr[i3] = a(zArr, length2, i);
                i3++;
                length2 += i;
            }
            try {
                new ReedSolomonDecoder(genericGF).a(iArr, i2);
                int i4 = (1 << i) - 1;
                int i5 = 0;
                for (int i6 = 0; i6 < b2; i6++) {
                    int i7 = iArr[i6];
                    if (i7 == 0 || i7 == i4) {
                        throw FormatException.getFormatInstance();
                    }
                    if (i7 == 1 || i7 == i4 - 1) {
                        i5++;
                    }
                }
                boolean[] zArr2 = new boolean[((b2 * i) - i5)];
                int i8 = 0;
                for (int i9 = 0; i9 < b2; i9++) {
                    int i10 = iArr[i9];
                    if (i10 == 1 || i10 == i4 - 1) {
                        Arrays.fill(zArr2, i8, (i8 + i) - 1, i10 > 1);
                        i8 += i - 1;
                    } else {
                        int i11 = i - 1;
                        while (i11 >= 0) {
                            int i12 = i8 + 1;
                            zArr2[i8] = ((1 << i11) & i10) != 0;
                            i11--;
                            i8 = i12;
                        }
                    }
                }
                return zArr2;
            } catch (ReedSolomonException e2) {
                throw FormatException.getFormatInstance(e2);
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean[] a(BitMatrix bitMatrix) {
        BitMatrix bitMatrix2 = bitMatrix;
        boolean c2 = this.f.c();
        int a2 = this.f.a();
        int i = (c2 ? 11 : 14) + (a2 * 4);
        int[] iArr = new int[i];
        boolean[] zArr = new boolean[a(a2, c2)];
        int i2 = 2;
        if (c2) {
            for (int i3 = 0; i3 < iArr.length; i3++) {
                iArr[i3] = i3;
            }
        } else {
            int i4 = i / 2;
            int i5 = ((i + 1) + (((i4 - 1) / 15) * 2)) / 2;
            for (int i6 = 0; i6 < i4; i6++) {
                int i7 = (i6 / 15) + i6;
                iArr[(i4 - i6) - 1] = (i5 - i7) - 1;
                iArr[i4 + i6] = i7 + i5 + 1;
            }
        }
        int i8 = 0;
        int i9 = 0;
        while (i8 < a2) {
            int i10 = ((a2 - i8) * 4) + (c2 ? 9 : 12);
            int i11 = i8 * 2;
            int i12 = (i - 1) - i11;
            int i13 = 0;
            while (i13 < i10) {
                int i14 = i13 * 2;
                int i15 = 0;
                while (i15 < i2) {
                    int i16 = i11 + i15;
                    int i17 = i11 + i13;
                    zArr[i9 + i14 + i15] = bitMatrix2.a(iArr[i16], iArr[i17]);
                    int i18 = i12 - i15;
                    zArr[(i10 * 2) + i9 + i14 + i15] = bitMatrix2.a(iArr[i17], iArr[i18]);
                    int i19 = i12 - i13;
                    zArr[(i10 * 4) + i9 + i14 + i15] = bitMatrix2.a(iArr[i18], iArr[i19]);
                    zArr[(i10 * 6) + i9 + i14 + i15] = bitMatrix2.a(iArr[i19], iArr[i16]);
                    i15++;
                    c2 = c2;
                    a2 = a2;
                    i2 = 2;
                }
                boolean z = c2;
                int i20 = a2;
                i13++;
                i2 = 2;
            }
            boolean z2 = c2;
            int i21 = a2;
            i9 += i10 * 8;
            i8++;
            i2 = 2;
        }
        return zArr;
    }

    private static int a(boolean[] zArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 < i + i2; i4++) {
            i3 <<= 1;
            if (zArr[i4]) {
                i3 |= 1;
            }
        }
        return i3;
    }

    private static byte a(boolean[] zArr, int i) {
        int length = zArr.length - i;
        if (length >= 8) {
            return (byte) a(zArr, i, 8);
        }
        return (byte) (a(zArr, i, length) << (8 - length));
    }

    static byte[] b(boolean[] zArr) {
        byte[] bArr = new byte[((zArr.length + 7) / 8)];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = a(zArr, i * 8);
        }
        return bArr;
    }
}
