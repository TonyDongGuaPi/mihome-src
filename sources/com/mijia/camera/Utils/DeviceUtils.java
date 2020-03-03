package com.mijia.camera.Utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DeviceUtils {
    public static String a(Context context) {
        String str = Settings.Secure.getString(context.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID) + Build.SERIAL + XmPluginHostApi.instance().getAccountId();
        try {
            return a(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return str;
        }
    }

    private static String a(String str) throws NoSuchAlgorithmException {
        byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }
}
