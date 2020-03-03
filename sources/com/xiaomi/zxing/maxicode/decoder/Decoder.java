package com.xiaomi.zxing.maxicode.decoder;

import com.xiaomi.zxing.ChecksumException;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.common.BitMatrix;
import com.xiaomi.zxing.common.DecoderResult;
import com.xiaomi.zxing.common.reedsolomon.GenericGF;
import com.xiaomi.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.xiaomi.zxing.common.reedsolomon.ReedSolomonException;
import java.util.Map;

public final class Decoder {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1683a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private final ReedSolomonDecoder d = new ReedSolomonDecoder(GenericGF.h);

    public DecoderResult a(BitMatrix bitMatrix) throws ChecksumException, FormatException {
        return a(bitMatrix, (Map<DecodeHintType, ?>) null);
    }

    public DecoderResult a(BitMatrix bitMatrix, Map<DecodeHintType, ?> map) throws FormatException, ChecksumException {
        byte[] bArr;
        byte[] a2 = new BitMatrixParser(bitMatrix).a();
        a(a2, 0, 10, 10, 0);
        byte b2 = a2[0] & 15;
        switch (b2) {
            case 2:
            case 3:
            case 4:
                byte[] bArr2 = a2;
                a(bArr2, 20, 84, 40, 1);
                a(bArr2, 20, 84, 40, 2);
                bArr = new byte[94];
                break;
            case 5:
                byte[] bArr3 = a2;
                a(bArr3, 20, 68, 56, 1);
                a(bArr3, 20, 68, 56, 2);
                bArr = new byte[78];
                break;
            default:
                throw FormatException.getFormatInstance();
        }
        System.arraycopy(a2, 0, bArr, 0, 10);
        System.arraycopy(a2, 20, bArr, 10, bArr.length - 10);
        return DecodedBitStreamParser.a(bArr, (int) b2);
    }

    private void a(byte[] bArr, int i, int i2, int i3, int i4) throws ChecksumException {
        int i5 = i2 + i3;
        int i6 = i4 == 0 ? 1 : 2;
        int[] iArr = new int[(i5 / i6)];
        for (int i7 = 0; i7 < i5; i7++) {
            if (i4 == 0 || i7 % 2 == i4 - 1) {
                iArr[i7 / i6] = bArr[i7 + i] & 255;
            }
        }
        try {
            this.d.a(iArr, i3 / i6);
            for (int i8 = 0; i8 < i2; i8++) {
                if (i4 == 0 || i8 % 2 == i4 - 1) {
                    bArr[i8 + i] = (byte) iArr[i8 / i6];
                }
            }
        } catch (ReedSolomonException unused) {
            throw ChecksumException.getChecksumInstance();
        }
    }
}
