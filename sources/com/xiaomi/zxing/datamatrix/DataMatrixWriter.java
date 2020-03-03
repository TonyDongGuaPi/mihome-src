package com.xiaomi.zxing.datamatrix;

import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.Dimension;
import com.xiaomi.zxing.EncodeHintType;
import com.xiaomi.zxing.Writer;
import com.xiaomi.zxing.common.BitMatrix;
import com.xiaomi.zxing.datamatrix.encoder.DefaultPlacement;
import com.xiaomi.zxing.datamatrix.encoder.ErrorCorrection;
import com.xiaomi.zxing.datamatrix.encoder.HighLevelEncoder;
import com.xiaomi.zxing.datamatrix.encoder.SymbolInfo;
import com.xiaomi.zxing.datamatrix.encoder.SymbolShapeHint;
import com.xiaomi.zxing.qrcode.encoder.ByteMatrix;
import java.util.Map;

public final class DataMatrixWriter implements Writer {
    public BitMatrix a(String str, BarcodeFormat barcodeFormat, int i, int i2) {
        return a(str, barcodeFormat, i, i2, (Map<EncodeHintType, ?>) null);
    }

    public BitMatrix a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) {
        Dimension dimension;
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (barcodeFormat != BarcodeFormat.DATA_MATRIX) {
            throw new IllegalArgumentException("Can only encode DATA_MATRIX, but got " + barcodeFormat);
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + i + 'x' + i2);
        } else {
            SymbolShapeHint symbolShapeHint = SymbolShapeHint.FORCE_NONE;
            Dimension dimension2 = null;
            if (map != null) {
                SymbolShapeHint symbolShapeHint2 = (SymbolShapeHint) map.get(EncodeHintType.DATA_MATRIX_SHAPE);
                if (symbolShapeHint2 != null) {
                    symbolShapeHint = symbolShapeHint2;
                }
                dimension = (Dimension) map.get(EncodeHintType.MIN_SIZE);
                if (dimension == null) {
                    dimension = null;
                }
                Dimension dimension3 = (Dimension) map.get(EncodeHintType.MAX_SIZE);
                if (dimension3 != null) {
                    dimension2 = dimension3;
                }
            } else {
                dimension = null;
            }
            String a2 = HighLevelEncoder.a(str, symbolShapeHint, dimension, dimension2);
            SymbolInfo a3 = SymbolInfo.a(a2.length(), symbolShapeHint, dimension, dimension2, true);
            DefaultPlacement defaultPlacement = new DefaultPlacement(ErrorCorrection.a(a2, a3), a3.d(), a3.e());
            defaultPlacement.d();
            return a(defaultPlacement, a3);
        }
    }

    private static BitMatrix a(DefaultPlacement defaultPlacement, SymbolInfo symbolInfo) {
        int d = symbolInfo.d();
        int e = symbolInfo.e();
        ByteMatrix byteMatrix = new ByteMatrix(symbolInfo.f(), symbolInfo.g());
        int i = 0;
        for (int i2 = 0; i2 < e; i2++) {
            if (i2 % symbolInfo.c == 0) {
                int i3 = 0;
                for (int i4 = 0; i4 < symbolInfo.f(); i4++) {
                    byteMatrix.a(i3, i, i4 % 2 == 0);
                    i3++;
                }
                i++;
            }
            int i5 = 0;
            for (int i6 = 0; i6 < d; i6++) {
                if (i6 % symbolInfo.b == 0) {
                    byteMatrix.a(i5, i, true);
                    i5++;
                }
                byteMatrix.a(i5, i, defaultPlacement.a(i6, i2));
                i5++;
                if (i6 % symbolInfo.b == symbolInfo.b - 1) {
                    byteMatrix.a(i5, i, i2 % 2 == 0);
                    i5++;
                }
            }
            i++;
            if (i2 % symbolInfo.c == symbolInfo.c - 1) {
                int i7 = 0;
                for (int i8 = 0; i8 < symbolInfo.f(); i8++) {
                    byteMatrix.a(i7, i, true);
                    i7++;
                }
                i++;
            }
        }
        return a(byteMatrix);
    }

    private static BitMatrix a(ByteMatrix byteMatrix) {
        int b = byteMatrix.b();
        int a2 = byteMatrix.a();
        BitMatrix bitMatrix = new BitMatrix(b, a2);
        bitMatrix.a();
        for (int i = 0; i < b; i++) {
            for (int i2 = 0; i2 < a2; i2++) {
                if (byteMatrix.a(i, i2) == 1) {
                    bitMatrix.b(i, i2);
                }
            }
        }
        return bitMatrix;
    }
}
