package cn.tongdun.android.core.q9q99gq99gggqg9qqqgg;

import cn.com.fmsh.tsm.business.constants.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class qgggqg999gg9qqggq {
    private static final Pattern gqg9qq9gqq9q9q = Pattern.compile(gqg9qq9gqq9q9q("362b442c2a47375f5f7e11177a0a6363625b7a7c11610d0d6f6e5735340d790c0d7e0b640c0a67177f7f5e31375a2a4343427b5a5c31412d2d4f4e7715142d592d0b65453c", 8));

    public static String gqg9qq9gqq9q9q(int i) {
        return (i & 255) + gqg9qq9gqq9q9q("46", 2) + ((i >> 8) & 255) + gqg9qq9gqq9q9q("46", 15) + ((i >> 16) & 255) + gqg9qq9gqq9q9q("46", 11) + ((i >> 24) & 255);
    }

    public static String qgg9qgg9999g9g(int i) {
        int i2 = -1 << (32 - i);
        return ((i2 >> 24) & 255) + gqg9qq9gqq9q9q("46", 116) + ((i2 >> 16) & 255) + gqg9qq9gqq9q9q("46", 85) + ((i2 >> 8) & 255) + gqg9qq9gqq9q9q("46", 106) + (i2 & 255);
    }

    public static String gqg9qq9gqq9q9q(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length);
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                stringBuffer.append(gqg9qq9gqq9q9q("58", 59));
                stringBuffer.append(hexString);
            } else {
                stringBuffer.append(hexString);
            }
            stringBuffer.append(gqg9qq9gqq9q9q("52", 18));
        }
        return String.valueOf(stringBuffer.substring(0, stringBuffer.length() - 1));
    }

    public static boolean gqg9qq9gqq9q9q(String str) {
        return str == null || "".equals(str.trim()) || str.length() == 0;
    }

    public static boolean gqg9qq9gqq9q9q(String... strArr) {
        if (strArr == null) {
            return true;
        }
        for (String str : strArr) {
            if (str != null && !"".equals(str) && str.length() > 0) {
                return false;
            }
        }
        return true;
    }

    public static List gqg9qq9gqq9q9q(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        if (str == null || str.length() == 0) {
            return arrayList;
        }
        if (str2 == null || str2.length() == 0) {
            arrayList.add(str);
            return arrayList;
        } else if (str.equals(str2)) {
            return arrayList;
        } else {
            while (true) {
                int indexOf = str.indexOf(str2);
                if (indexOf == -1) {
                    break;
                } else if (indexOf == 0) {
                    str = str.substring(indexOf + str2.length());
                } else if (indexOf >= 1) {
                    arrayList.add(str.substring(0, indexOf));
                    str = str.substring(indexOf + str2.length());
                }
            }
            if (!gqg9qq9gqq9q9q(str)) {
                arrayList.add(str);
            }
            return arrayList;
        }
    }

    public static boolean qgg9qgg9999g9g(String str) {
        return gqg9qq9gqq9q9q.matcher(str).matches();
    }

    public static String gqg9qq9gqq9q9q(String str, int i) {
        try {
            int length = str.length() / 2;
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 * 2;
                bArr[i2] = (byte) ("0123456789abcdef".indexOf(charArray[i3 + 1]) | ("0123456789abcdef".indexOf(charArray[i3]) << 4));
            }
            byte b = (byte) (i ^ 85);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ Constants.TagName.DEVICE_MODEL);
            byte b2 = bArr[0];
            int i4 = 1;
            while (i4 < length2) {
                byte b3 = bArr[i4];
                bArr[i4] = (byte) ((b2 ^ bArr[i4]) ^ b);
                i4++;
                b2 = b3;
            }
            return new String(bArr, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
