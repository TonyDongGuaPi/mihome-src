package com.xiaomi.connection;

import android.util.Base64;
import android.util.Log;
import com.coloros.mcssdk.c.a;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

public class ByteOperator {

    /* renamed from: a  reason: collision with root package name */
    private static String f10087a = "ByteOperator ";

    public static int a(byte[] bArr) throws StringIndexOutOfBoundsException {
        return ((bArr[1] << 8) & 65280) | (bArr[0] & 255);
    }

    public static int a(byte[] bArr, int i) throws StringIndexOutOfBoundsException {
        return ((bArr[i + 1] << 8) & 65280) | (bArr[i] & 255);
    }

    public static int b(byte[] bArr) throws StringIndexOutOfBoundsException {
        return ((bArr[3] << 24) & -16777216) | (bArr[0] & 255) | ((bArr[1] << 8) & 65280) | ((bArr[2] << 16) & 16711680);
    }

    public static int b(byte[] bArr, int i) throws StringIndexOutOfBoundsException {
        return ((bArr[i + 3] << 24) & -16777216) | (bArr[i] & 255) | ((bArr[i + 1] << 8) & 65280) | ((bArr[i + 2] << 16) & 16711680);
    }

    public static int c(byte[] bArr) throws StringIndexOutOfBoundsException {
        byte b = bArr[3] & 255;
        Log.i(f10087a + ":byteArray4intH", b + ":" + Integer.toBinaryString(b));
        byte b2 = b | ((bArr[2] << 8) & 65280);
        Log.i(f10087a + ":byteArray4intH", b2 + ":" + Integer.toBinaryString(b2));
        byte b3 = b2 | ((bArr[1] << 16) & 16711680);
        Log.i(f10087a + ":byteArray4intH", b3 + ":" + Integer.toBinaryString(b3));
        byte b4 = ((bArr[0] << 24) & -16777216) | b3;
        Log.i(f10087a + ":byteArray4intH", b4 + ":" + Integer.toBinaryString(b4));
        return b4;
    }

    public static int c(byte[] bArr, int i) throws StringIndexOutOfBoundsException {
        byte b = bArr[i + 3] & 255;
        Log.i(f10087a + ":byteArray4intH", b + ":" + Integer.toBinaryString(b));
        byte b2 = b | ((bArr[i + 2] << 8) & 65280);
        Log.i(f10087a + ":byteArray4intH", b2 + ":" + Integer.toBinaryString(b2));
        byte b3 = b2 | ((bArr[i + 1] << 16) & 16711680);
        Log.i(f10087a + ":byteArray4intH", b3 + ":" + Integer.toBinaryString(b3));
        byte b4 = ((bArr[i] << 24) & -16777216) | b3;
        Log.i(f10087a + ":byteArray4intH", b4 + ":" + Integer.toBinaryString(b4));
        return b4;
    }

    public static int d(byte[] bArr, int i) throws StringIndexOutOfBoundsException {
        return ((bArr[i + 3] << 24) & -16777216) | (bArr[i] & 255) | ((bArr[i + 1] << 8) & 65280) | ((bArr[i + 2] << 16) & 16711680);
    }

    public static byte[] a(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((65280 & i) >> 8), (byte) ((16711680 & i) >> 16), (byte) ((i & -16777216) >> 24)};
    }

    public static byte[] b(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i & 65280) >> 8)};
    }

    public static byte[] c(int i) {
        byte[] bArr = new byte[4];
        bArr[3] = (byte) (i & 255);
        bArr[2] = (byte) ((65280 & i) >> 8);
        bArr[1] = (byte) ((16711680 & i) >> 16);
        bArr[0] = (byte) ((i & -16777216) >> 24);
        return bArr;
    }

    public static final boolean a(byte[] bArr, byte[] bArr2) throws StringIndexOutOfBoundsException {
        int length = bArr.length;
        int length2 = bArr2.length;
        if (length >= length2) {
            length = length2;
        }
        System.arraycopy(bArr2, 0, bArr, 0, length);
        return true;
    }

    public static final boolean a(byte[] bArr, int i, byte[] bArr2, int i2, int i3) throws StringIndexOutOfBoundsException {
        int length = bArr.length - i;
        int i4 = (i3 - i2) + 1;
        if (length < i4) {
            i4 = length;
        }
        System.arraycopy(bArr2, i2, bArr, i, i4);
        return true;
    }

    public static final String e(byte[] bArr, int i) throws StringIndexOutOfBoundsException {
        String str = "";
        if (i > bArr.length) {
            i = bArr.length;
        }
        for (int i2 = 0; i2 < i; i2++) {
            String hexString = Integer.toHexString(bArr[i2] & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            str = str + hexString.toUpperCase(Locale.ENGLISH);
        }
        return str;
    }

    public static final String d(byte[] bArr) {
        String str = "";
        int length = bArr == null ? 0 : bArr.length;
        for (int i = 0; i < length; i++) {
            String hexString = Integer.toHexString(bArr[i] & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            str = str + hexString.toUpperCase(Locale.ENGLISH);
        }
        return str;
    }

    public static final String a(byte[] bArr, int i, int i2) throws StringIndexOutOfBoundsException {
        String str = "";
        while (i <= i2) {
            String hexString = Integer.toHexString(bArr[i] & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            str = str + hexString.toUpperCase(Locale.ENGLISH);
            i++;
        }
        return str;
    }

    public static final String e(byte[] bArr) {
        String str = "";
        for (int i = 0; i < (bArr == null ? 0 : bArr.length); i++) {
            str = str + ((char) (bArr[i] & 255));
        }
        return str;
    }

    public static byte[] b(byte[] bArr, int i, int i2) throws StringIndexOutOfBoundsException {
        if (i2 < i) {
            return new byte[48];
        }
        if (i2 == i) {
            return new byte[]{bArr[i]};
        }
        byte[] bArr2 = new byte[((i2 - i) + 1)];
        System.arraycopy(bArr, i, bArr2, 0, bArr2.length);
        return bArr2;
    }

    public static final boolean b(byte[] bArr, int i, byte[] bArr2, int i2, int i3) throws StringIndexOutOfBoundsException {
        int i4 = (i3 - i2) + 1;
        if (bArr.length - i < i4) {
            return false;
        }
        byte[] b = b(bArr2, i2, i3);
        byte[] b2 = b(bArr, i, i4 - 1);
        return e(b, b.length).equals(e(b2, b2.length));
    }

    public static final String f(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr2[i] = Byte.valueOf(bArr[i]).byteValue();
        }
        return new String(bArr2);
    }

    public static final int[] g(byte[] bArr) {
        int[] iArr = new int[15];
        for (int i = 0; i < iArr.length - 1; i++) {
            iArr[i] = bArr[i + 4];
        }
        iArr[13] = a(new byte[]{bArr[17], bArr[18]});
        iArr[14] = c(new byte[]{bArr[19], bArr[20], bArr[21], bArr[22]});
        return iArr;
    }

    public static final String[] h(byte[] bArr) {
        byte[] bArr2 = bArr;
        byte b = bArr2[4];
        int i = b + 4;
        int i2 = i + 1;
        int i3 = bArr2[i2] + i2;
        int i4 = i3 + 1;
        int i5 = bArr2[i4] + i4;
        int i6 = i5 + 1;
        int i7 = bArr2[i6] + i6;
        int i8 = i7 + 1;
        int i9 = bArr2[i8] + i8;
        int i10 = i9 + 1;
        int i11 = bArr2[i10] + i10;
        int i12 = i11 + 1;
        int i13 = i12 + bArr2[i12];
        int i14 = i13 + 1;
        String[] strArr = {c(bArr2, 5, i), c(bArr2, i2 + 1, i3), c(bArr2, i4 + 1, i5), c(bArr2, i6 + 1, i7), c(bArr2, i8 + 1, i9), c(bArr2, i10 + 1, i11), c(bArr2, i12 + 1, i13), c(bArr2, i14 + 1, i14 + bArr2[i14])};
        String str = f10087a;
        Log.i(str, "ipLengthPosition" + 4 + "  ipLength" + b + "        byteArrayLenth" + bArr2.length + "  sambaInfo[0] " + strArr[0] + "  sambaInfo[1] " + strArr[1] + "  sambaInfo[2] " + strArr[2] + "  sambaInfo[3] " + strArr[3] + "  sambaInfo[4] " + strArr[4] + "  sambaInfo[5] " + strArr[5] + "  sambaInfo[6] " + strArr[6] + "  sambaInfo[7] " + strArr[7]);
        return strArr;
    }

    public static final String c(byte[] bArr, int i, int i2) throws StringIndexOutOfBoundsException {
        byte[] bArr2 = new byte[((i2 - i) + 1)];
        for (int i3 = i; i3 <= i2; i3++) {
            bArr2[i3 - i] = Byte.valueOf(bArr[i3]).byteValue();
        }
        return new String(bArr2);
    }

    public static boolean b(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr2.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static String a(byte[] bArr, boolean z) throws UnsupportedEncodingException {
        String str = "";
        if (bArr == null) {
            return "";
        }
        String encodeToString = Base64.encodeToString(bArr, 2);
        try {
            str = URLEncoder.encode(encodeToString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.i(f10087a + ":byteArrayToUrlcode", "Exception = " + e.getMessage());
            e.printStackTrace();
        }
        Log.i(f10087a + ":byteArrayToUrlcode", "byte operator Y :base64 string = " + encodeToString);
        if (!z || !str.endsWith("%0A")) {
            Log.i(f10087a + ":byteArrayToUrlcode", "byte operator  :url string = " + str);
            return str;
        }
        Log.i(f10087a + ":byteArrayToUrlcode", "byte operator Y :url string = " + str.replace("%0A", ""));
        return str.replace("%0A", "");
    }

    public static String i(byte[] bArr) {
        return bArr == null ? "" : Base64.encodeToString(bArr, 2);
    }

    public static byte[] j(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        for (int i = 0; i < 4; i++) {
            bArr2[i] = bArr[3 - i];
            bArr2[i + 4] = bArr[7 - i];
            bArr2[i + 8] = bArr[11 - i];
            bArr2[i + 12] = bArr[15 - i];
        }
        return bArr2;
    }

    public static byte[] a(String str) {
        int i = 16;
        byte[] bArr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        if (length <= 16) {
            i = length;
        }
        System.arraycopy(bytes, 0, bArr, 0, i);
        return bArr;
    }

    public static byte[] b(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        int length = upperCase.length() / 2;
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (a(charArray[i2 + 1]) | (a(charArray[i2]) << 4));
        }
        return bArr;
    }

    private static byte a(char c) {
        return (byte) a.f.indexOf(c);
    }

    public static byte[] d(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i & 65280) >> 8)};
    }

    public static byte[] e(int i) {
        byte[] bArr = new byte[2];
        bArr[1] = (byte) (i & 255);
        bArr[0] = (byte) ((i & 65280) >> 8);
        return bArr;
    }

    public static short f(byte[] bArr, int i) throws StringIndexOutOfBoundsException {
        return (short) ((bArr[i] & 255) | (bArr[i + 1] << 8));
    }

    public static char g(byte[] bArr, int i) throws StringIndexOutOfBoundsException {
        return (char) (bArr[i] & 255);
    }

    public static byte[] a(short s) {
        return new byte[]{(byte) ((s >>> 8) & 255), (byte) (s & 255)};
    }

    public static final boolean c(byte[] bArr, int i, byte[] bArr2, int i2, int i3) throws StringIndexOutOfBoundsException {
        int i4 = (i3 - i2) + 1;
        if (bArr.length - i < i4) {
            return false;
        }
        for (int i5 = 0; i5 < i4; i5++) {
            bArr[i5 + i] = bArr2[i3 - i5];
        }
        return true;
    }

    public static byte[] c(String str) {
        String[] split = (str.equals("") || !str.contains(".")) ? null : str.split("\\.");
        byte[] bArr = new byte[4];
        if (split.length == 4) {
            bArr[3] = (byte) Integer.valueOf(split[0]).intValue();
            bArr[2] = (byte) Integer.valueOf(split[1]).intValue();
            bArr[1] = (byte) Integer.valueOf(split[2]).intValue();
            bArr[0] = (byte) Integer.valueOf(split[3]).intValue();
        } else {
            String str2 = f10087a;
            Log.e(str2, "stringIPToLittleEndianByteArray format error : " + str);
        }
        return bArr;
    }

    public static int k(byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            i |= bArr[i2] << i2;
        }
        return i;
    }

    public static byte[] a(int i, int i2) {
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = (byte) ((i >> i3) & 1);
        }
        return bArr;
    }

    public static byte[] f(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i & 65280) >> 8)};
    }
}
