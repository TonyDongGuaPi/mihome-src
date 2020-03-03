package com.xiaomi.zxing.qrcode.decoder;

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
    private final ReedSolomonDecoder f1760a = new ReedSolomonDecoder(GenericGF.e);

    public DecoderResult a(boolean[][] zArr) throws ChecksumException, FormatException {
        return a(zArr, (Map<DecodeHintType, ?>) null);
    }

    public DecoderResult a(boolean[][] zArr, Map<DecodeHintType, ?> map) throws ChecksumException, FormatException {
        int length = zArr.length;
        BitMatrix bitMatrix = new BitMatrix(length);
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < length; i2++) {
                if (zArr[i][i2]) {
                    bitMatrix.b(i2, i);
                }
            }
        }
        return a(bitMatrix, map);
    }

    public DecoderResult a(BitMatrix bitMatrix) throws ChecksumException, FormatException {
        return a(bitMatrix, (Map<DecodeHintType, ?>) null);
    }

    public DecoderResult a(BitMatrix bitMatrix, Map<DecodeHintType, ?> map) throws FormatException, ChecksumException {
        ChecksumException e;
        BitMatrixParser bitMatrixParser = new BitMatrixParser(bitMatrix);
        FormatException formatException = null;
        try {
            return a(bitMatrixParser, map);
        } catch (FormatException e2) {
            FormatException formatException2 = e2;
            e = null;
            formatException = formatException2;
            try {
                bitMatrixParser.d();
                bitMatrixParser.a(true);
                bitMatrixParser.b();
                bitMatrixParser.a();
                bitMatrixParser.e();
                DecoderResult a2 = a(bitMatrixParser, map);
                a2.a((Object) new QRCodeDecoderMetaData(true));
                return a2;
            } catch (ChecksumException | FormatException e3) {
                if (formatException != null) {
                    throw formatException;
                } else if (e != null) {
                    throw e;
                } else {
                    throw e3;
                }
            }
        } catch (ChecksumException e4) {
            e = e4;
            bitMatrixParser.d();
            bitMatrixParser.a(true);
            bitMatrixParser.b();
            bitMatrixParser.a();
            bitMatrixParser.e();
            DecoderResult a22 = a(bitMatrixParser, map);
            a22.a((Object) new QRCodeDecoderMetaData(true));
            return a22;
        }
    }

    private DecoderResult a(BitMatrixParser bitMatrixParser, Map<DecodeHintType, ?> map) throws FormatException, ChecksumException {
        Version b = bitMatrixParser.b();
        ErrorCorrectionLevel a2 = bitMatrixParser.a().a();
        DataBlock[] a3 = DataBlock.a(bitMatrixParser.c(), b, a2);
        int i = 0;
        for (DataBlock a4 : a3) {
            i += a4.a();
        }
        byte[] bArr = new byte[i];
        int length = a3.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            DataBlock dataBlock = a3[i2];
            byte[] b2 = dataBlock.b();
            int a5 = dataBlock.a();
            a(b2, a5);
            int i4 = i3;
            int i5 = 0;
            while (i5 < a5) {
                bArr[i4] = b2[i5];
                i5++;
                i4++;
            }
            i2++;
            i3 = i4;
        }
        return DecodedBitStreamParser.a(bArr, b, a2, map);
    }

    private void a(byte[] bArr, int i) throws ChecksumException {
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        try {
            this.f1760a.a(iArr, bArr.length - i);
            for (int i3 = 0; i3 < i; i3++) {
                bArr[i3] = (byte) iArr[i3];
            }
        } catch (ReedSolomonException unused) {
            throw ChecksumException.getChecksumInstance();
        }
    }
}
