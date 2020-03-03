package com.xiaomi.zxing.qrcode.encoder;

import com.xiaomi.zxing.EncodeHintType;
import com.xiaomi.zxing.WriterException;
import com.xiaomi.zxing.common.BitArray;
import com.xiaomi.zxing.common.CharacterSetECI;
import com.xiaomi.zxing.common.reedsolomon.GenericGF;
import com.xiaomi.zxing.common.reedsolomon.ReedSolomonEncoder;
import com.xiaomi.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.xiaomi.zxing.qrcode.decoder.Mode;
import com.xiaomi.zxing.qrcode.decoder.Version;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

public final class Encoder {

    /* renamed from: a  reason: collision with root package name */
    static final String f1774a = "ISO-8859-1";
    private static final int[] b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};

    private Encoder() {
    }

    private static int a(ByteMatrix byteMatrix) {
        return MaskUtil.a(byteMatrix) + MaskUtil.b(byteMatrix) + MaskUtil.c(byteMatrix) + MaskUtil.d(byteMatrix);
    }

    public static QRCode a(String str, ErrorCorrectionLevel errorCorrectionLevel) throws WriterException {
        return a(str, errorCorrectionLevel, (Map<EncodeHintType, ?>) null);
    }

    public static QRCode a(String str, ErrorCorrectionLevel errorCorrectionLevel, Map<EncodeHintType, ?> map) throws WriterException {
        CharacterSetECI characterSetECIByName;
        String str2 = "ISO-8859-1";
        if (map != null && map.containsKey(EncodeHintType.CHARACTER_SET)) {
            str2 = map.get(EncodeHintType.CHARACTER_SET).toString();
        }
        Mode a2 = a(str, str2);
        BitArray bitArray = new BitArray();
        if (a2 == Mode.BYTE && !"ISO-8859-1".equals(str2) && (characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(str2)) != null) {
            a(characterSetECIByName, bitArray);
        }
        a(a2, bitArray);
        BitArray bitArray2 = new BitArray();
        a(str, a2, bitArray2, str2);
        Version a3 = a(bitArray.a() + a2.getCharacterCountBits(a(bitArray.a() + a2.getCharacterCountBits(Version.b(1)) + bitArray2.a(), errorCorrectionLevel)) + bitArray2.a(), errorCorrectionLevel);
        BitArray bitArray3 = new BitArray();
        bitArray3.a(bitArray);
        a(a2 == Mode.BYTE ? bitArray2.b() : str.length(), a3, a2, bitArray3);
        bitArray3.a(bitArray2);
        Version.ECBlocks a4 = a3.a(errorCorrectionLevel);
        int c = a3.c() - a4.c();
        a(c, bitArray3);
        BitArray a5 = a(bitArray3, a3.c(), c, a4.b());
        QRCode qRCode = new QRCode();
        qRCode.a(errorCorrectionLevel);
        qRCode.a(a2);
        qRCode.a(a3);
        int d = a3.d();
        ByteMatrix byteMatrix = new ByteMatrix(d, d);
        int a6 = a(a5, errorCorrectionLevel, a3, byteMatrix);
        qRCode.a(a6);
        MatrixUtil.a(a5, errorCorrectionLevel, a3, a6, byteMatrix);
        qRCode.a(byteMatrix);
        return qRCode;
    }

    static int a(int i) {
        if (i < b.length) {
            return b[i];
        }
        return -1;
    }

    public static Mode a(String str) {
        return a(str, (String) null);
    }

    private static Mode a(String str, String str2) {
        if ("Shift_JIS".equals(str2) && b(str)) {
            return Mode.KANJI;
        }
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt >= '0' && charAt <= '9') {
                z2 = true;
            } else if (a((int) charAt) == -1) {
                return Mode.BYTE;
            } else {
                z = true;
            }
        }
        if (z) {
            return Mode.ALPHANUMERIC;
        }
        if (z2) {
            return Mode.NUMERIC;
        }
        return Mode.BYTE;
    }

    private static boolean b(String str) {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i = 0; i < length; i += 2) {
                byte b2 = bytes[i] & 255;
                if ((b2 < 129 || b2 > 159) && (b2 < 224 || b2 > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }

    private static int a(BitArray bitArray, ErrorCorrectionLevel errorCorrectionLevel, Version version, ByteMatrix byteMatrix) throws WriterException {
        int i = Integer.MAX_VALUE;
        int i2 = -1;
        for (int i3 = 0; i3 < 8; i3++) {
            MatrixUtil.a(bitArray, errorCorrectionLevel, version, i3, byteMatrix);
            int a2 = a(byteMatrix);
            if (a2 < i) {
                i2 = i3;
                i = a2;
            }
        }
        return i2;
    }

    private static Version a(int i, ErrorCorrectionLevel errorCorrectionLevel) throws WriterException {
        for (int i2 = 1; i2 <= 40; i2++) {
            Version b2 = Version.b(i2);
            if (b2.c() - b2.a(errorCorrectionLevel).c() >= (i + 7) / 8) {
                return b2;
            }
        }
        throw new WriterException("Data too big");
    }

    static void a(int i, BitArray bitArray) throws WriterException {
        int i2 = i * 8;
        if (bitArray.a() <= i2) {
            for (int i3 = 0; i3 < 4 && bitArray.a() < i2; i3++) {
                bitArray.a(false);
            }
            int a2 = bitArray.a() & 7;
            if (a2 > 0) {
                while (a2 < 8) {
                    bitArray.a(false);
                    a2++;
                }
            }
            int b2 = i - bitArray.b();
            for (int i4 = 0; i4 < b2; i4++) {
                bitArray.c((i4 & 1) == 0 ? 236 : 17, 8);
            }
            if (bitArray.a() != i2) {
                throw new WriterException("Bits size does not equal capacity");
            }
            return;
        }
        throw new WriterException("data bits cannot fit in the QR Code" + bitArray.a() + " > " + i2);
    }

    static void a(int i, int i2, int i3, int i4, int[] iArr, int[] iArr2) throws WriterException {
        if (i4 < i3) {
            int i5 = i % i3;
            int i6 = i3 - i5;
            int i7 = i / i3;
            int i8 = i7 + 1;
            int i9 = i2 / i3;
            int i10 = i9 + 1;
            int i11 = i7 - i9;
            int i12 = i8 - i10;
            if (i11 != i12) {
                throw new WriterException("EC bytes mismatch");
            } else if (i3 != i6 + i5) {
                throw new WriterException("RS blocks mismatch");
            } else if (i != ((i9 + i11) * i6) + ((i10 + i12) * i5)) {
                throw new WriterException("Total bytes mismatch");
            } else if (i4 < i6) {
                iArr[0] = i9;
                iArr2[0] = i11;
            } else {
                iArr[0] = i10;
                iArr2[0] = i12;
            }
        } else {
            throw new WriterException("Block ID too large");
        }
    }

    static BitArray a(BitArray bitArray, int i, int i2, int i3) throws WriterException {
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        if (bitArray.b() == i5) {
            ArrayList<BlockPair> arrayList = new ArrayList<>(i6);
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            for (int i10 = 0; i10 < i6; i10++) {
                int[] iArr = new int[1];
                int[] iArr2 = new int[1];
                a(i, i2, i3, i10, iArr, iArr2);
                int i11 = iArr[0];
                byte[] bArr = new byte[i11];
                bitArray.a(i7 * 8, bArr, 0, i11);
                byte[] a2 = a(bArr, iArr2[0]);
                arrayList.add(new BlockPair(bArr, a2));
                i8 = Math.max(i8, i11);
                i9 = Math.max(i9, a2.length);
                i7 += iArr[0];
            }
            if (i5 == i7) {
                BitArray bitArray2 = new BitArray();
                for (int i12 = 0; i12 < i8; i12++) {
                    for (BlockPair a3 : arrayList) {
                        byte[] a4 = a3.a();
                        if (i12 < a4.length) {
                            bitArray2.c(a4[i12], 8);
                        }
                    }
                }
                for (int i13 = 0; i13 < i9; i13++) {
                    for (BlockPair b2 : arrayList) {
                        byte[] b3 = b2.b();
                        if (i13 < b3.length) {
                            bitArray2.c(b3[i13], 8);
                        }
                    }
                }
                if (i4 == bitArray2.b()) {
                    return bitArray2;
                }
                throw new WriterException("Interleaving error: " + i4 + " and " + bitArray2.b() + " differ.");
            }
            throw new WriterException("Data bytes does not match offset");
        }
        throw new WriterException("Number of bits and data bytes does not match");
    }

    static byte[] a(byte[] bArr, int i) {
        int length = bArr.length;
        int[] iArr = new int[(length + i)];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        new ReedSolomonEncoder(GenericGF.e).a(iArr, i);
        byte[] bArr2 = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr2[i3] = (byte) iArr[length + i3];
        }
        return bArr2;
    }

    static void a(Mode mode, BitArray bitArray) {
        bitArray.c(mode.getBits(), 4);
    }

    static void a(int i, Version version, Mode mode, BitArray bitArray) throws WriterException {
        int characterCountBits = mode.getCharacterCountBits(version);
        int i2 = 1 << characterCountBits;
        if (i < i2) {
            bitArray.c(i, characterCountBits);
            return;
        }
        throw new WriterException(i + " is bigger than " + (i2 - 1));
    }

    static void a(String str, Mode mode, BitArray bitArray, String str2) throws WriterException {
        switch (mode) {
            case NUMERIC:
                a((CharSequence) str, bitArray);
                return;
            case ALPHANUMERIC:
                b(str, bitArray);
                return;
            case BYTE:
                a(str, bitArray, str2);
                return;
            case KANJI:
                a(str, bitArray);
                return;
            default:
                throw new WriterException("Invalid mode: " + mode);
        }
    }

    static void a(CharSequence charSequence, BitArray bitArray) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int charAt = charSequence.charAt(i) - '0';
            int i2 = i + 2;
            if (i2 < length) {
                bitArray.c((charAt * 100) + ((charSequence.charAt(i + 1) - '0') * 10) + (charSequence.charAt(i2) - '0'), 10);
                i += 3;
            } else {
                i++;
                if (i < length) {
                    bitArray.c((charAt * 10) + (charSequence.charAt(i) - '0'), 7);
                    i = i2;
                } else {
                    bitArray.c(charAt, 4);
                }
            }
        }
    }

    static void b(CharSequence charSequence, BitArray bitArray) throws WriterException {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int a2 = a((int) charSequence.charAt(i));
            if (a2 != -1) {
                int i2 = i + 1;
                if (i2 < length) {
                    int a3 = a((int) charSequence.charAt(i2));
                    if (a3 != -1) {
                        bitArray.c((a2 * 45) + a3, 11);
                        i += 2;
                    } else {
                        throw new WriterException();
                    }
                } else {
                    bitArray.c(a2, 6);
                    i = i2;
                }
            } else {
                throw new WriterException();
            }
        }
    }

    static void a(String str, BitArray bitArray, String str2) throws WriterException {
        try {
            for (byte c : str.getBytes(str2)) {
                bitArray.c(c, 8);
            }
        } catch (UnsupportedEncodingException e) {
            throw new WriterException((Throwable) e);
        }
    }

    static void a(String str, BitArray bitArray) throws WriterException {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            int i = 0;
            while (i < length) {
                byte b2 = ((bytes[i] & 255) << 8) | (bytes[i + 1] & 255);
                int i2 = (b2 < 33088 || b2 > 40956) ? (b2 < 57408 || b2 > 60351) ? -1 : b2 - 49472 : b2 - 33088;
                if (i2 != -1) {
                    bitArray.c(((i2 >> 8) * 192) + (i2 & 255), 13);
                    i += 2;
                } else {
                    throw new WriterException("Invalid byte sequence");
                }
            }
        } catch (UnsupportedEncodingException e) {
            throw new WriterException((Throwable) e);
        }
    }

    private static void a(CharacterSetECI characterSetECI, BitArray bitArray) {
        bitArray.c(Mode.ECI.getBits(), 4);
        bitArray.c(characterSetECI.getValue(), 8);
    }
}
