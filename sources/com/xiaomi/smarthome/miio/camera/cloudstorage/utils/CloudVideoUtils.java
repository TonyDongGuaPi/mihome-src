package com.xiaomi.smarthome.miio.camera.cloudstorage.utils;

import android.text.TextUtils;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import java.util.Calendar;
import java.util.List;

public class CloudVideoUtils {
    public static final String TAG = "CloudVideoUtils";

    private static int hexToBin(char c) {
        if ('0' <= c && c <= '9') {
            return c - '0';
        }
        if ('A' <= c && c <= 'F') {
            return (c - 'A') + 10;
        }
        if ('a' > c || c > 'f') {
            return -1;
        }
        return (c - 'a') + 10;
    }

    public static long getDayTS0(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        instance.set(11, 0);
        instance.set(13, 0);
        instance.set(12, 0);
        instance.set(14, 0);
        return instance.getTimeInMillis();
    }

    public static long getCurrentDayTS0() {
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(13, 0);
        instance.set(12, 0);
        instance.set(14, 0);
        return instance.getTimeInMillis();
    }

    public static String getDeviceModel(String str) {
        Device b;
        if (TextUtils.isEmpty(str) || (b = SmartHomeDeviceManager.a().b(str)) == null) {
            return null;
        }
        return b.model;
    }

    public static byte[] parseHexBinary(String str) {
        int length = str.length();
        if (length % 2 == 0) {
            byte[] bArr = new byte[(length / 2)];
            for (int i = 0; i < length; i += 2) {
                int hexToBin = hexToBin(str.charAt(i));
                int hexToBin2 = hexToBin(str.charAt(i + 1));
                if (hexToBin == -1 || hexToBin2 == -1) {
                    throw new IllegalArgumentException("contains illegal character for hexBinary: " + str);
                }
                bArr[i / 2] = (byte) ((hexToBin * 16) + hexToBin2);
            }
            return bArr;
        }
        throw new IllegalArgumentException("hexBinary needs to be even-length: " + str);
    }

    public static String byteList2String(List<Byte> list) {
        if (list == null || list.size() <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Byte byteValue : list) {
            sb.append(String.format("%X", new Object[]{Byte.valueOf(byteValue.byteValue())}));
        }
        return sb.toString();
    }

    public static int getDayIntervalByTimestamp(long j) {
        if (j > 0) {
            return (int) (j / 86400000);
        }
        return 0;
    }
}
