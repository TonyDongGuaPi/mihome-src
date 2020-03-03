package com.imi.fastjson.util;

import com.google.code.microlog4android.format.PatternFormatter;
import com.imi.fastjson.JSONException;
import com.xiaomi.smarthome.camera.activity.timelapse.TCPClient;
import java.io.Closeable;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

public class IOUtils {

    /* renamed from: a  reason: collision with root package name */
    static final char[] f6190a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', PatternFormatter.CATEGORY_CONVERSION_CHAR, PatternFormatter.DATE_CONVERSION_CHAR, 'e', 'f', 'g', 'h', PatternFormatter.CLIENT_ID_CONVERSION_CHAR, 'j', 'k', 'l', PatternFormatter.MESSAGE_CONVERSION_CHAR, 'n', 'o', 'p', 'q', PatternFormatter.RELATIVE_TIME_CONVERSION_CHAR, 's', PatternFormatter.THREAD_CONVERSION_CHAR, 'u', 'v', 'w', 'x', 'y', 'z'};
    static final char[] b = {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9'};
    static final char[] c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static final int[] d = {9, 99, 999, 9999, TCPClient.SOCKET_CONNECTTIMEOUT, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};

    public static int a(long j) {
        long j2 = 10;
        for (int i = 1; i < 19; i++) {
            if (j < j2) {
                return i;
            }
            j2 *= 10;
        }
        return 19;
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void a(long j, int i, char[] cArr) {
        char c2;
        if (j < 0) {
            c2 = '-';
            j = -j;
        } else {
            c2 = 0;
        }
        while (j > 2147483647L) {
            long j2 = j / 100;
            int i2 = (int) (j - (((j2 << 6) + (j2 << 5)) + (j2 << 2)));
            int i3 = i - 1;
            cArr[i3] = c[i2];
            i = i3 - 1;
            cArr[i] = b[i2];
            j = j2;
        }
        int i4 = (int) j;
        while (i4 >= 65536) {
            int i5 = i4 / 100;
            int i6 = i4 - (((i5 << 6) + (i5 << 5)) + (i5 << 2));
            int i7 = i - 1;
            cArr[i7] = c[i6];
            i = i7 - 1;
            cArr[i] = b[i6];
            i4 = i5;
        }
        while (true) {
            int i8 = (52429 * i4) >>> 19;
            i--;
            cArr[i] = f6190a[i4 - ((i8 << 3) + (i8 << 1))];
            if (i8 == 0) {
                break;
            }
            i4 = i8;
        }
        if (c2 != 0) {
            cArr[i - 1] = c2;
        }
    }

    public static void a(int i, int i2, char[] cArr) {
        char c2;
        if (i < 0) {
            c2 = '-';
            i = -i;
        } else {
            c2 = 0;
        }
        while (i >= 65536) {
            int i3 = i / 100;
            int i4 = i - (((i3 << 6) + (i3 << 5)) + (i3 << 2));
            int i5 = i2 - 1;
            cArr[i5] = c[i4];
            i2 = i5 - 1;
            cArr[i2] = b[i4];
            i = i3;
        }
        while (true) {
            int i6 = (52429 * i) >>> 19;
            i2--;
            cArr[i2] = f6190a[i - ((i6 << 3) + (i6 << 1))];
            if (i6 == 0) {
                break;
            }
            i = i6;
        }
        if (c2 != 0) {
            cArr[i2 - 1] = c2;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(byte r4, int r5, char[] r6) {
        /*
            if (r4 >= 0) goto L_0x0006
            r0 = 45
            int r4 = -r4
            goto L_0x0007
        L_0x0006:
            r0 = 0
        L_0x0007:
            r1 = 52429(0xcccd, float:7.3469E-41)
            int r1 = r1 * r4
            int r1 = r1 >>> 19
            int r2 = r1 << 3
            int r3 = r1 << 1
            int r2 = r2 + r3
            int r4 = r4 - r2
            int r5 = r5 + -1
            char[] r2 = f6190a
            char r4 = r2[r4]
            r6[r5] = r4
            if (r1 != 0) goto L_0x0025
            if (r0 == 0) goto L_0x0024
            int r5 = r5 + -1
            r6[r5] = r0
        L_0x0024:
            return
        L_0x0025:
            r4 = r1
            goto L_0x0007
        */
        throw new UnsupportedOperationException("Method not decompiled: com.imi.fastjson.util.IOUtils.a(byte, int, char[]):void");
    }

    public static int a(int i) {
        int i2 = 0;
        while (i > d[i2]) {
            i2++;
        }
        return i2 + 1;
    }

    public static void a(CharsetDecoder charsetDecoder, ByteBuffer byteBuffer, CharBuffer charBuffer) {
        try {
            CoderResult decode = charsetDecoder.decode(byteBuffer, charBuffer, true);
            if (!decode.isUnderflow()) {
                decode.throwException();
            }
            CoderResult flush = charsetDecoder.flush(charBuffer);
            if (!flush.isUnderflow()) {
                flush.throwException();
            }
        } catch (CharacterCodingException e) {
            throw new JSONException(e.getMessage(), e);
        }
    }
}
