package com.tsmclient.smartcard;

import android.text.TextUtils;
import android.util.Base64;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.xiaomi.smarthome.camera.activity.timelapse.TCPClient;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Coder {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final int[] sizeTable = {9, 99, 999, 9999, TCPClient.SOCKET_CONNECTTIMEOUT, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};

    public static byte toBytesLow(int i) {
        return (byte) (i & 255);
    }

    public static int sizeOfInt(int i) {
        if (i < 0) {
            i = -i;
        }
        int i2 = 0;
        while (i > sizeTable[i2]) {
            i2++;
        }
        return i2 + 1;
    }

    public static String to2Bytes(int i) {
        return String.format("%02d", new Object[]{Integer.valueOf(i)});
    }

    public static String to4Bytes(int i) {
        return String.format("%04d", new Object[]{Integer.valueOf(i)});
    }

    public static String to10Bytes(int i) {
        return String.format("%010d", new Object[]{Integer.valueOf(i)});
    }

    public static String to4HexBytes(int i) {
        return String.format("%04X", new Object[]{Integer.valueOf(i & 65535)});
    }

    public static String bytesToHexString(byte b) {
        return bytesToHexString(new byte[]{b}, 0, 1);
    }

    public static String bytesToHexString(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return bytesToHexString(bArr, 0, bArr.length);
    }

    private static String bytesToHexString(byte[] bArr, int i, int i2) {
        char[] cArr = new char[(i2 * 2)];
        int i3 = 0;
        for (int i4 = i; i4 < i + i2; i4++) {
            byte b = bArr[i4];
            int i5 = i3 + 1;
            cArr[i3] = HEX_DIGITS[(b >>> 4) & 15];
            i3 = i5 + 1;
            cArr[i5] = HEX_DIGITS[b & 15];
        }
        return new String(cArr);
    }

    public static byte[] hexStringToBytes(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((toByte(str.charAt(i)) << 4) | toByte(str.charAt(i + 1)));
        }
        return bArr;
    }

    public static String decodeBase64Coder(String str) {
        return !TextUtils.isEmpty(str) ? new String(Base64.decode(str, 8)) : str;
    }

    public static byte[] decodeBase64ToByteArray(String str) {
        if (!TextUtils.isEmpty(str)) {
            return Base64.decode(str, 8);
        }
        return null;
    }

    public static String encodeBase64Coder(byte[] bArr) {
        if (bArr != null) {
            return Base64.encodeToString(bArr, 8);
        }
        return null;
    }

    public static String hashDeviceInfo(String str) {
        try {
            return Base64.encodeToString(MessageDigest.getInstance("SHA1").digest(str.getBytes()), 11);
        } catch (NoSuchAlgorithmException unused) {
            throw new IllegalStateException("failed to init SHA1 digest");
        }
    }

    public static String encodeMD5(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            return bytesToHexString(instance.digest());
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    public static String encodeMD5(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            return bytesToHexString(instance.digest());
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    private static int toByte(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return (c - 'A') + 10;
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 'a') + 10;
        }
        throw new RuntimeException("Invalid hex char '" + c + "'");
    }

    public static int bytesToInt(byte[] bArr, int i, int i2) {
        if (bArr == null || i < 0 || i2 > bArr.length) {
            throw new IllegalArgumentException("argument is null");
        }
        byte b = 0;
        int i3 = i2 + i;
        while (i < i3) {
            b = (b << 8) | (bArr[i] & 255);
            i++;
        }
        return b;
    }

    public static int hexStringToInt(String str) {
        return bytesToInt(hexStringToBytes(str));
    }

    public static int bytesToInt(byte... bArr) {
        if (bArr != null) {
            byte b = 0;
            for (byte b2 : bArr) {
                b = (b << 8) | (b2 & 255);
            }
            return b;
        }
        throw new IllegalArgumentException("argument is null");
    }

    public static byte[] str2Bcd(String str) {
        int i;
        int i2;
        if (str != null) {
            int length = str.length();
            if (length % 2 != 0) {
                str = "0" + str;
                length = str.length();
            }
            if (length >= 2) {
                length /= 2;
            }
            byte[] bArr = new byte[length];
            byte[] bytes = str.getBytes();
            for (int i3 = 0; i3 < str.length() / 2; i3++) {
                int i4 = i3 * 2;
                if (bytes[i4] >= 48 && bytes[i4] <= 57) {
                    i = bytes[i4] - 48;
                } else if (bytes[i4] < 97 || bytes[i4] > 122) {
                    i = bytes[i4] + Constants.TagName.STATION_ID + 10;
                } else {
                    i = (bytes[i4] - 97) + 10;
                }
                int i5 = i4 + 1;
                if (bytes[i5] >= 48 && bytes[i5] <= 57) {
                    i2 = bytes[i5] - 48;
                } else if (bytes[i5] < 97 || bytes[i5] > 122) {
                    i2 = bytes[i5] + Constants.TagName.STATION_ID + 10;
                } else {
                    i2 = (bytes[i5] - 97) + 10;
                }
                bArr[i3] = (byte) ((i << 4) + i2);
            }
            return bArr;
        }
        throw new IllegalArgumentException("argument is null");
    }

    public static byte[] shortToByte(short s) {
        return new byte[]{(byte) ((s >>> 8) & 255), (byte) (s & 255)};
    }
}
