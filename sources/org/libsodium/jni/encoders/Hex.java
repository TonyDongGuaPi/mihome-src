package org.libsodium.jni.encoders;

import com.google.code.microlog4android.format.PatternFormatter;
import com.taobao.weex.el.parse.Operators;

public class Hex implements Encoder {
    private static final char[] d = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', PatternFormatter.CATEGORY_CONVERSION_CHAR, PatternFormatter.DATE_CONVERSION_CHAR, 'e', 'f'};
    private static final char[] e = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static byte[] a(char[] cArr) {
        int i = 0;
        int length = cArr == null ? 0 : cArr.length;
        if ((length & 1) == 0) {
            byte[] bArr = new byte[(length >> 1)];
            int i2 = 0;
            while (i < length) {
                int i3 = i + 1;
                i = i3 + 1;
                bArr[i2] = (byte) (((a(cArr[i], i) << 4) | a(cArr[i3], i3)) & 255);
                i2++;
            }
            return bArr;
        }
        throw new RuntimeException("Odd number of characters.");
    }

    public byte[] a(String str) {
        return a(str != null ? str.toCharArray() : new char[0]);
    }

    private static char[] b(byte[] bArr) {
        return a(bArr, true);
    }

    private static char[] a(byte[] bArr, boolean z) {
        return a(bArr, z ? d : e);
    }

    private static char[] a(byte[] bArr, char[] cArr) {
        int length = bArr.length;
        char[] cArr2 = new char[(length << 1)];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr2[i] = cArr[(bArr[i2] & 240) >>> 4];
            i = i3 + 1;
            cArr2[i3] = cArr[bArr[i2] & 15];
        }
        return cArr2;
    }

    public String a(byte[] bArr) {
        return new String(b(bArr));
    }

    private static int a(char c, int i) {
        int digit = Character.digit(c, 16);
        if (digit != -1) {
            return digit;
        }
        throw new RuntimeException("Illegal hexadecimal character " + c + " at index " + i);
    }

    public String toString() {
        return super.toString() + "[charsetName=" + f3724a + Operators.ARRAY_END_STR;
    }
}
