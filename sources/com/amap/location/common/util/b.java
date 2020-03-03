package com.amap.location.common.util;

import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private static SimpleDateFormat f4595a;
    private static String b;

    public static int a(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    public static long a(long j, long j2, long j3) {
        return j < j2 ? j2 : j > j3 ? j3 : j;
    }

    public static synchronized String a(long j, String str) {
        synchronized (b.class) {
            if (TextUtils.isEmpty(str)) {
                str = "yyyy-MM-dd HH:mm:ss:SSS";
            }
            if (f4595a == null) {
                try {
                    f4595a = new SimpleDateFormat(str, Locale.CHINA);
                    b = str;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (!str.equals(b)) {
                f4595a.applyPattern(str);
                b = str;
            }
            if (j <= 0) {
                j = System.currentTimeMillis();
            }
            if (f4595a == null) {
                return "NULL";
            }
            String format = f4595a.format(Long.valueOf(j));
            return format;
        }
    }

    public static String a(byte[] bArr, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i] & 255);
            if (str != null && str.length() > 0) {
                sb.append(str);
            }
            if (hexString.length() < 2) {
                sb.append("0");
            }
            sb.append(hexString);
            if (str2 != null && str2.length() > 0 && i < bArr.length - 1) {
                sb.append(str2);
            }
        }
        return sb.toString();
    }

    public static byte[] a(long j, int i, boolean z) {
        if (i < 0 || i > 8) {
            throw new IllegalArgumentException("width 应该在 0-8 之间");
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        if (z) {
            while (i2 < i) {
                bArr[i2] = (byte) ((int) ((j >> (((i - i2) - 1) * 8)) & 255));
                i2++;
            }
        } else {
            while (i2 < i) {
                bArr[i2] = (byte) ((int) ((j >> (i2 * 8)) & 255));
                i2++;
            }
        }
        return bArr;
    }

    public static byte[] a(String str, String str2, String str3) {
        int i;
        int i2;
        int i3;
        if (str2 == null || str2.length() <= 0) {
            i = 0;
            i2 = 2;
        } else {
            i = str2.length();
            i2 = i + 2;
        }
        if (str3 == null || str3.length() <= 0) {
            i3 = 0;
        } else {
            i3 = str3.length();
            i2 += i3;
        }
        if ((str.length() + i3) % i2 == 0) {
            int length = (str.length() + i3) / i2;
            byte[] bArr = new byte[length];
            int i4 = i;
            int i5 = 0;
            while (i4 < str.length()) {
                bArr[i5] = (byte) Integer.parseInt(str.substring(i4, i4 + 2), 16);
                i4 += i3 + 2 + i;
                i5++;
            }
            if (i5 >= length) {
                return bArr;
            }
            throw new IllegalArgumentException("str 是不满足在规则的, 这是不可能的");
        }
        throw new IllegalArgumentException("str 是不满足在规则的");
    }
}
