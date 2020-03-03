package com.xiaomi.zxing.datamatrix.decoder;

import com.xiaomi.zxing.ChecksumException;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.common.BitMatrix;
import com.xiaomi.zxing.common.DecoderResult;
import com.xiaomi.zxing.common.reedsolomon.GenericGF;
import com.xiaomi.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.xiaomi.zxing.common.reedsolomon.ReedSolomonException;

public final class Decoder {

    /* renamed from: a  reason: collision with root package name */
    private final ReedSolomonDecoder f1666a = new ReedSolomonDecoder(GenericGF.f);

    public DecoderResult a(boolean[][] zArr) throws FormatException, ChecksumException {
        int length = zArr.length;
        BitMatrix bitMatrix = new BitMatrix(length);
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < length; i2++) {
                if (zArr[i][i2]) {
                    bitMatrix.b(i2, i);
                }
            }
        }
        return a(bitMatrix);
    }

    public DecoderResult a(BitMatrix bitMatrix) throws FormatException, ChecksumException {
        BitMatrixParser bitMatrixParser = new BitMatrixParser(bitMatrix);
        DataBlock[] a2 = DataBlock.a(bitMatrixParser.b(), bitMatrixParser.a());
        int length = a2.length;
        int i = 0;
        for (DataBlock a3 : a2) {
            i += a3.a();
        }
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < length; i2++) {
            DataBlock dataBlock = a2[i2];
            byte[] b = dataBlock.b();
            int a4 = dataBlock.a();
            a(b, a4);
            for (int i3 = 0; i3 < a4; i3++) {
                bArr[(i3 * length) + i2] = b[i3];
            }
        }
        return DecodedBitStreamParser.a(bArr);
    }

    private void a(byte[] bArr, int i) throws ChecksumException {
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        try {
            this.f1666a.a(iArr, bArr.length - i);
            for (int i3 = 0; i3 < i; i3++) {
                bArr[i3] = (byte) iArr[i3];
            }
        } catch (ReedSolomonException unused) {
            throw ChecksumException.getChecksumInstance();
        }
    }
}
