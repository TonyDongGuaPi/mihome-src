package com.xiaomi.zxing.qrcode.decoder;

import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.common.BitSource;
import com.xiaomi.zxing.common.CharacterSetECI;
import com.xiaomi.zxing.common.StringUtils;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Map;

final class DecodedBitStreamParser {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f1759a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".toCharArray();
    private static final int b = 1;

    private DecodedBitStreamParser() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x00e8 A[LOOP:0: B:1:0x001e->B:60:0x00e8, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00c6 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.xiaomi.zxing.common.DecoderResult a(byte[] r17, com.xiaomi.zxing.qrcode.decoder.Version r18, com.xiaomi.zxing.qrcode.decoder.ErrorCorrectionLevel r19, java.util.Map<com.xiaomi.zxing.DecodeHintType, ?> r20) throws com.xiaomi.zxing.FormatException {
        /*
            r0 = r18
            com.xiaomi.zxing.common.BitSource r7 = new com.xiaomi.zxing.common.BitSource
            r8 = r17
            r7.<init>(r8)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r1 = 50
            r9.<init>(r1)
            java.util.ArrayList r10 = new java.util.ArrayList
            r11 = 1
            r10.<init>(r11)
            r1 = 0
            r2 = -1
            r12 = 0
            r14 = r12
            r13 = 0
            r15 = -1
            r16 = -1
        L_0x001e:
            int r1 = r7.c()     // Catch:{ IllegalArgumentException -> 0x00eb }
            r2 = 4
            if (r1 >= r2) goto L_0x0029
            com.xiaomi.zxing.qrcode.decoder.Mode r1 = com.xiaomi.zxing.qrcode.decoder.Mode.TERMINATOR     // Catch:{ IllegalArgumentException -> 0x00eb }
        L_0x0027:
            r6 = r1
            goto L_0x0032
        L_0x0029:
            int r1 = r7.a(r2)     // Catch:{ IllegalArgumentException -> 0x00eb }
            com.xiaomi.zxing.qrcode.decoder.Mode r1 = com.xiaomi.zxing.qrcode.decoder.Mode.forBits(r1)     // Catch:{ IllegalArgumentException -> 0x00eb }
            goto L_0x0027
        L_0x0032:
            com.xiaomi.zxing.qrcode.decoder.Mode r1 = com.xiaomi.zxing.qrcode.decoder.Mode.TERMINATOR     // Catch:{ IllegalArgumentException -> 0x00eb }
            if (r6 == r1) goto L_0x0059
            com.xiaomi.zxing.qrcode.decoder.Mode r1 = com.xiaomi.zxing.qrcode.decoder.Mode.FNC1_FIRST_POSITION     // Catch:{ IllegalArgumentException -> 0x00eb }
            if (r6 == r1) goto L_0x00c0
            com.xiaomi.zxing.qrcode.decoder.Mode r1 = com.xiaomi.zxing.qrcode.decoder.Mode.FNC1_SECOND_POSITION     // Catch:{ IllegalArgumentException -> 0x00eb }
            if (r6 != r1) goto L_0x0040
            goto L_0x00c0
        L_0x0040:
            com.xiaomi.zxing.qrcode.decoder.Mode r1 = com.xiaomi.zxing.qrcode.decoder.Mode.STRUCTURED_APPEND     // Catch:{ IllegalArgumentException -> 0x00eb }
            if (r6 != r1) goto L_0x0061
            int r1 = r7.c()     // Catch:{ IllegalArgumentException -> 0x00eb }
            r2 = 16
            if (r1 < r2) goto L_0x005c
            r1 = 8
            int r2 = r7.a(r1)     // Catch:{ IllegalArgumentException -> 0x00eb }
            int r1 = r7.a(r1)     // Catch:{ IllegalArgumentException -> 0x00eb }
            r16 = r1
            r15 = r2
        L_0x0059:
            r11 = r6
            goto L_0x00c2
        L_0x005c:
            com.xiaomi.zxing.FormatException r0 = com.xiaomi.zxing.FormatException.getFormatInstance()     // Catch:{ IllegalArgumentException -> 0x00eb }
            throw r0     // Catch:{ IllegalArgumentException -> 0x00eb }
        L_0x0061:
            com.xiaomi.zxing.qrcode.decoder.Mode r1 = com.xiaomi.zxing.qrcode.decoder.Mode.ECI     // Catch:{ IllegalArgumentException -> 0x00eb }
            if (r6 != r1) goto L_0x0075
            int r1 = a((com.xiaomi.zxing.common.BitSource) r7)     // Catch:{ IllegalArgumentException -> 0x00eb }
            com.xiaomi.zxing.common.CharacterSetECI r14 = com.xiaomi.zxing.common.CharacterSetECI.getCharacterSetECIByValue(r1)     // Catch:{ IllegalArgumentException -> 0x00eb }
            if (r14 == 0) goto L_0x0070
            goto L_0x0059
        L_0x0070:
            com.xiaomi.zxing.FormatException r0 = com.xiaomi.zxing.FormatException.getFormatInstance()     // Catch:{ IllegalArgumentException -> 0x00eb }
            throw r0     // Catch:{ IllegalArgumentException -> 0x00eb }
        L_0x0075:
            com.xiaomi.zxing.qrcode.decoder.Mode r1 = com.xiaomi.zxing.qrcode.decoder.Mode.HANZI     // Catch:{ IllegalArgumentException -> 0x00eb }
            if (r6 != r1) goto L_0x008b
            int r1 = r7.a(r2)     // Catch:{ IllegalArgumentException -> 0x00eb }
            int r2 = r6.getCharacterCountBits(r0)     // Catch:{ IllegalArgumentException -> 0x00eb }
            int r2 = r7.a(r2)     // Catch:{ IllegalArgumentException -> 0x00eb }
            if (r1 != r11) goto L_0x0059
            a(r7, r9, r2)     // Catch:{ IllegalArgumentException -> 0x00eb }
            goto L_0x0059
        L_0x008b:
            int r1 = r6.getCharacterCountBits(r0)     // Catch:{ IllegalArgumentException -> 0x00eb }
            int r3 = r7.a(r1)     // Catch:{ IllegalArgumentException -> 0x00eb }
            com.xiaomi.zxing.qrcode.decoder.Mode r1 = com.xiaomi.zxing.qrcode.decoder.Mode.NUMERIC     // Catch:{ IllegalArgumentException -> 0x00eb }
            if (r6 != r1) goto L_0x009b
            c(r7, r9, r3)     // Catch:{ IllegalArgumentException -> 0x00eb }
            goto L_0x0059
        L_0x009b:
            com.xiaomi.zxing.qrcode.decoder.Mode r1 = com.xiaomi.zxing.qrcode.decoder.Mode.ALPHANUMERIC     // Catch:{ IllegalArgumentException -> 0x00eb }
            if (r6 != r1) goto L_0x00a3
            a((com.xiaomi.zxing.common.BitSource) r7, (java.lang.StringBuilder) r9, (int) r3, (boolean) r13)     // Catch:{ IllegalArgumentException -> 0x00eb }
            goto L_0x0059
        L_0x00a3:
            com.xiaomi.zxing.qrcode.decoder.Mode r1 = com.xiaomi.zxing.qrcode.decoder.Mode.BYTE     // Catch:{ IllegalArgumentException -> 0x00eb }
            if (r6 != r1) goto L_0x00b2
            r1 = r7
            r2 = r9
            r4 = r14
            r5 = r10
            r11 = r6
            r6 = r20
            a(r1, r2, r3, r4, r5, r6)     // Catch:{ IllegalArgumentException -> 0x00eb }
            goto L_0x00c2
        L_0x00b2:
            r11 = r6
            com.xiaomi.zxing.qrcode.decoder.Mode r1 = com.xiaomi.zxing.qrcode.decoder.Mode.KANJI     // Catch:{ IllegalArgumentException -> 0x00eb }
            if (r11 != r1) goto L_0x00bb
            b(r7, r9, r3)     // Catch:{ IllegalArgumentException -> 0x00eb }
            goto L_0x00c2
        L_0x00bb:
            com.xiaomi.zxing.FormatException r0 = com.xiaomi.zxing.FormatException.getFormatInstance()     // Catch:{ IllegalArgumentException -> 0x00eb }
            throw r0     // Catch:{ IllegalArgumentException -> 0x00eb }
        L_0x00c0:
            r11 = r6
            r13 = 1
        L_0x00c2:
            com.xiaomi.zxing.qrcode.decoder.Mode r1 = com.xiaomi.zxing.qrcode.decoder.Mode.TERMINATOR     // Catch:{ IllegalArgumentException -> 0x00eb }
            if (r11 != r1) goto L_0x00e8
            com.xiaomi.zxing.common.DecoderResult r7 = new com.xiaomi.zxing.common.DecoderResult
            java.lang.String r2 = r9.toString()
            boolean r0 = r10.isEmpty()
            if (r0 == 0) goto L_0x00d4
            r3 = r12
            goto L_0x00d5
        L_0x00d4:
            r3 = r10
        L_0x00d5:
            if (r19 != 0) goto L_0x00d9
            r4 = r12
            goto L_0x00de
        L_0x00d9:
            java.lang.String r0 = r19.toString()
            r4 = r0
        L_0x00de:
            r0 = r7
            r1 = r17
            r5 = r15
            r6 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return r7
        L_0x00e8:
            r11 = 1
            goto L_0x001e
        L_0x00eb:
            com.xiaomi.zxing.FormatException r0 = com.xiaomi.zxing.FormatException.getFormatInstance()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.zxing.qrcode.decoder.DecodedBitStreamParser.a(byte[], com.xiaomi.zxing.qrcode.decoder.Version, com.xiaomi.zxing.qrcode.decoder.ErrorCorrectionLevel, java.util.Map):com.xiaomi.zxing.common.DecoderResult");
    }

    private static void a(BitSource bitSource, StringBuilder sb, int i) throws FormatException {
        if (i * 13 <= bitSource.c()) {
            byte[] bArr = new byte[(i * 2)];
            int i2 = 0;
            while (i > 0) {
                int a2 = bitSource.a(13);
                int i3 = (a2 % 96) | ((a2 / 96) << 8);
                int i4 = i3 + (i3 < 959 ? 41377 : 42657);
                bArr[i2] = (byte) ((i4 >> 8) & 255);
                bArr[i2 + 1] = (byte) (i4 & 255);
                i2 += 2;
                i--;
            }
            try {
                sb.append(new String(bArr, "GB2312"));
            } catch (UnsupportedEncodingException unused) {
                throw FormatException.getFormatInstance();
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static void b(BitSource bitSource, StringBuilder sb, int i) throws FormatException {
        if (i * 13 <= bitSource.c()) {
            byte[] bArr = new byte[(i * 2)];
            int i2 = 0;
            while (i > 0) {
                int a2 = bitSource.a(13);
                int i3 = (a2 % 192) | ((a2 / 192) << 8);
                int i4 = i3 + (i3 < 7936 ? 33088 : 49472);
                bArr[i2] = (byte) (i4 >> 8);
                bArr[i2 + 1] = (byte) i4;
                i2 += 2;
                i--;
            }
            try {
                sb.append(new String(bArr, "SJIS"));
            } catch (UnsupportedEncodingException unused) {
                throw FormatException.getFormatInstance();
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static void a(BitSource bitSource, StringBuilder sb, int i, CharacterSetECI characterSetECI, Collection<byte[]> collection, Map<DecodeHintType, ?> map) throws FormatException {
        String str;
        if (i * 8 <= bitSource.c()) {
            byte[] bArr = new byte[i];
            for (int i2 = 0; i2 < i; i2++) {
                bArr[i2] = (byte) bitSource.a(8);
            }
            if (characterSetECI == null) {
                str = StringUtils.a(bArr, map);
            } else {
                str = characterSetECI.name();
            }
            try {
                sb.append(new String(bArr, str));
                collection.add(bArr);
            } catch (UnsupportedEncodingException unused) {
                throw FormatException.getFormatInstance();
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static char a(int i) throws FormatException {
        if (i < f1759a.length) {
            return f1759a[i];
        }
        throw FormatException.getFormatInstance();
    }

    private static void a(BitSource bitSource, StringBuilder sb, int i, boolean z) throws FormatException {
        while (i > 1) {
            if (bitSource.c() >= 11) {
                int a2 = bitSource.a(11);
                sb.append(a(a2 / 45));
                sb.append(a(a2 % 45));
                i -= 2;
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (i == 1) {
            if (bitSource.c() >= 6) {
                sb.append(a(bitSource.a(6)));
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (z) {
            for (int length = sb.length(); length < sb.length(); length++) {
                if (sb.charAt(length) == '%') {
                    if (length < sb.length() - 1) {
                        int i2 = length + 1;
                        if (sb.charAt(i2) == '%') {
                            sb.deleteCharAt(i2);
                        }
                    }
                    sb.setCharAt(length, 29);
                }
            }
        }
    }

    private static void c(BitSource bitSource, StringBuilder sb, int i) throws FormatException {
        while (i >= 3) {
            if (bitSource.c() >= 10) {
                int a2 = bitSource.a(10);
                if (a2 < 1000) {
                    sb.append(a(a2 / 100));
                    sb.append(a((a2 / 10) % 10));
                    sb.append(a(a2 % 10));
                    i -= 3;
                } else {
                    throw FormatException.getFormatInstance();
                }
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (i == 2) {
            if (bitSource.c() >= 7) {
                int a3 = bitSource.a(7);
                if (a3 < 100) {
                    sb.append(a(a3 / 10));
                    sb.append(a(a3 % 10));
                    return;
                }
                throw FormatException.getFormatInstance();
            }
            throw FormatException.getFormatInstance();
        } else if (i != 1) {
        } else {
            if (bitSource.c() >= 4) {
                int a4 = bitSource.a(4);
                if (a4 < 10) {
                    sb.append(a(a4));
                    return;
                }
                throw FormatException.getFormatInstance();
            }
            throw FormatException.getFormatInstance();
        }
    }

    private static int a(BitSource bitSource) throws FormatException {
        int a2 = bitSource.a(8);
        if ((a2 & 128) == 0) {
            return a2 & 127;
        }
        if ((a2 & 192) == 128) {
            return bitSource.a(8) | ((a2 & 63) << 8);
        }
        if ((a2 & 224) == 192) {
            return bitSource.a(16) | ((a2 & 31) << 16);
        }
        throw FormatException.getFormatInstance();
    }
}
