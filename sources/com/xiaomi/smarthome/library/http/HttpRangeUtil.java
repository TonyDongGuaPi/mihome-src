package com.xiaomi.smarthome.library.http;

import android.text.TextUtils;

public class HttpRangeUtil {
    public static String a(long j, long j2) {
        StringBuffer stringBuffer = new StringBuffer("bytes=");
        if (j == -1 || j2 == -1) {
            if (j == -1 && j2 == -1) {
                return null;
            }
            if (j == -1) {
                stringBuffer.append("-" + j2);
                return stringBuffer.toString();
            } else if (j2 != -1) {
                return null;
            } else {
                stringBuffer.append(j + "-");
                return stringBuffer.toString();
            }
        } else if (j >= j2) {
            return null;
        } else {
            stringBuffer.append(j + "-" + j2);
            return stringBuffer.toString();
        }
    }

    public static long[] a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split("-");
        if (split.length <= 1 || TextUtils.isEmpty(split[1])) {
            return null;
        }
        String[] split2 = split[1].split("/");
        if (split2.length <= 1 || TextUtils.isEmpty(split2[1])) {
            return null;
        }
        long[] jArr = new long[3];
        try {
            jArr[2] = Long.parseLong(split2[1].trim());
            String[] split3 = split[0].split(" ");
            if (split3.length > 1) {
                if (!TextUtils.isEmpty(split3[1])) {
                    jArr[0] = Long.parseLong(split3[1].trim());
                    jArr[1] = Long.parseLong(split2[0].trim());
                    return jArr;
                }
            }
            jArr[0] = 0;
            jArr[1] = Long.parseLong(split2[0].trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jArr;
    }
}
