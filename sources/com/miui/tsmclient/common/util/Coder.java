package com.miui.tsmclient.common.util;

import android.support.media.ExifInterface;
import android.util.Base64;
import miuipub.reflect.Field;

public class Coder {
    private static final String[] HEX_DIGITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    private static final String[] HEX_DIGITS_UPPERCASE = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", Field.b, Field.c, Field.h, ExifInterface.LONGITUDE_EAST, Field.g};

    private Coder() {
    }

    public static String byteArrayToString(byte[] bArr) {
        return byteArrayToString(bArr, false);
    }

    public static String byteArrayToString(byte[] bArr, boolean z) {
        StringBuilder sb = new StringBuilder();
        for (byte byteToHexString : bArr) {
            sb.append(byteToHexString(byteToHexString, z));
        }
        return sb.toString();
    }

    public static String byteToHexString(byte b) {
        return byteToHexString(b, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: byte} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r2v0, types: [int, byte] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String byteToHexString(int r2, boolean r3) {
        /*
            if (r2 >= 0) goto L_0x0004
            int r2 = r2 + 256
        L_0x0004:
            int r0 = r2 / 16
            int r2 = r2 % 16
            if (r3 == 0) goto L_0x0022
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String[] r1 = HEX_DIGITS_UPPERCASE
            r0 = r1[r0]
            r3.append(r0)
            java.lang.String[] r0 = HEX_DIGITS_UPPERCASE
            r2 = r0[r2]
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            return r2
        L_0x0022:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String[] r1 = HEX_DIGITS
            r0 = r1[r0]
            r3.append(r0)
            java.lang.String[] r0 = HEX_DIGITS
            r2 = r0[r2]
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.common.util.Coder.byteToHexString(byte, boolean):java.lang.String");
    }

    public static final String encodeBase64NoWrap(String str) {
        return Base64.encodeToString(str.getBytes(), 2);
    }

    public static final String encodeBase64NoWrap(byte[] bArr) {
        return Base64.encodeToString(bArr, 2);
    }

    public static final byte[] encodeBase64BytesNoWrap(String str) {
        return Base64.encode(str.getBytes(), 2);
    }

    public static final String decodeBase64(String str) {
        return new String(Base64.decode(str, 0));
    }

    public static final byte[] decodeBase64Bytes(String str) {
        return Base64.decode(str, 0);
    }
}
