package com.xiaomi.zxing.aztec;

import com.xiaomi.zxing.BinaryBitmap;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.Reader;
import com.xiaomi.zxing.Result;
import java.util.Map;

public final class AztecReader implements Reader {
    public void a() {
    }

    public Result a(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return a(binaryBitmap, (Map<DecodeHintType, ?>) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x005b A[LOOP:0: B:28:0x0059->B:29:0x005b, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0083  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.zxing.Result a(com.xiaomi.zxing.BinaryBitmap r6, java.util.Map<com.xiaomi.zxing.DecodeHintType, ?> r7) throws com.xiaomi.zxing.NotFoundException, com.xiaomi.zxing.FormatException {
        /*
            r5 = this;
            com.xiaomi.zxing.aztec.detector.Detector r0 = new com.xiaomi.zxing.aztec.detector.Detector
            com.xiaomi.zxing.common.BitMatrix r6 = r6.c()
            r0.<init>(r6)
            r6 = 0
            r1 = 0
            com.xiaomi.zxing.aztec.AztecDetectorResult r2 = r0.a((boolean) r6)     // Catch:{ NotFoundException -> 0x002b, FormatException -> 0x0025 }
            com.xiaomi.zxing.ResultPoint[] r3 = r2.e()     // Catch:{ NotFoundException -> 0x002b, FormatException -> 0x0025 }
            com.xiaomi.zxing.aztec.decoder.Decoder r4 = new com.xiaomi.zxing.aztec.decoder.Decoder     // Catch:{ NotFoundException -> 0x0023, FormatException -> 0x0021 }
            r4.<init>()     // Catch:{ NotFoundException -> 0x0023, FormatException -> 0x0021 }
            com.xiaomi.zxing.common.DecoderResult r2 = r4.a((com.xiaomi.zxing.aztec.AztecDetectorResult) r2)     // Catch:{ NotFoundException -> 0x0023, FormatException -> 0x0021 }
            r4 = r3
            r3 = r1
            r1 = r2
            r2 = r3
            goto L_0x002f
        L_0x0021:
            r2 = move-exception
            goto L_0x0027
        L_0x0023:
            r2 = move-exception
            goto L_0x002d
        L_0x0025:
            r2 = move-exception
            r3 = r1
        L_0x0027:
            r4 = r3
            r3 = r2
            r2 = r1
            goto L_0x002f
        L_0x002b:
            r2 = move-exception
            r3 = r1
        L_0x002d:
            r4 = r3
            r3 = r1
        L_0x002f:
            if (r1 != 0) goto L_0x004c
            r1 = 1
            com.xiaomi.zxing.aztec.AztecDetectorResult r0 = r0.a((boolean) r1)     // Catch:{ FormatException | NotFoundException -> 0x0044 }
            com.xiaomi.zxing.ResultPoint[] r4 = r0.e()     // Catch:{ FormatException | NotFoundException -> 0x0044 }
            com.xiaomi.zxing.aztec.decoder.Decoder r1 = new com.xiaomi.zxing.aztec.decoder.Decoder     // Catch:{ FormatException | NotFoundException -> 0x0044 }
            r1.<init>()     // Catch:{ FormatException | NotFoundException -> 0x0044 }
            com.xiaomi.zxing.common.DecoderResult r1 = r1.a((com.xiaomi.zxing.aztec.AztecDetectorResult) r0)     // Catch:{ FormatException | NotFoundException -> 0x0044 }
            goto L_0x004c
        L_0x0044:
            r6 = move-exception
            if (r2 != 0) goto L_0x004b
            if (r3 == 0) goto L_0x004a
            throw r3
        L_0x004a:
            throw r6
        L_0x004b:
            throw r2
        L_0x004c:
            if (r7 == 0) goto L_0x0063
            com.xiaomi.zxing.DecodeHintType r0 = com.xiaomi.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK
            java.lang.Object r7 = r7.get(r0)
            com.xiaomi.zxing.ResultPointCallback r7 = (com.xiaomi.zxing.ResultPointCallback) r7
            if (r7 == 0) goto L_0x0063
            int r0 = r4.length
        L_0x0059:
            if (r6 >= r0) goto L_0x0063
            r2 = r4[r6]
            r7.a(r2)
            int r6 = r6 + 1
            goto L_0x0059
        L_0x0063:
            com.xiaomi.zxing.Result r6 = new com.xiaomi.zxing.Result
            java.lang.String r7 = r1.b()
            byte[] r0 = r1.a()
            com.xiaomi.zxing.BarcodeFormat r2 = com.xiaomi.zxing.BarcodeFormat.AZTEC
            r6.<init>(r7, r0, r4, r2)
            java.util.List r7 = r1.c()
            if (r7 == 0) goto L_0x007d
            com.xiaomi.zxing.ResultMetadataType r0 = com.xiaomi.zxing.ResultMetadataType.BYTE_SEGMENTS
            r6.a(r0, r7)
        L_0x007d:
            java.lang.String r7 = r1.d()
            if (r7 == 0) goto L_0x0088
            com.xiaomi.zxing.ResultMetadataType r0 = com.xiaomi.zxing.ResultMetadataType.ERROR_CORRECTION_LEVEL
            r6.a(r0, r7)
        L_0x0088:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.zxing.aztec.AztecReader.a(com.xiaomi.zxing.BinaryBitmap, java.util.Map):com.xiaomi.zxing.Result");
    }
}
