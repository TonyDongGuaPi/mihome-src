package com.xiaomi.smarthome.core.server.internal.util;

import android.text.TextUtils;
import com.coloros.mcssdk.c.a;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class LtmkEncryptUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14732a = "show_secure_pin";
    public static final String b = "1";
    public static final String c = "0";
    public static final int d = 0;
    public static final int e = 1;
    private static final String f = "7aa4c68c590d4031b980d98b41023800";
    private static final String g = "AES/CBC/NoPadding";

    private static boolean a(int i) {
        switch (i) {
            case 0:
            case 1:
                return true;
            default:
                return false;
        }
    }

    public static boolean a(String str, String str2) {
        boolean z = true;
        if (TextUtils.equals(str, "lumi.lock.mcn01") || TextUtils.equals(str, "loock.lock.v5")) {
            return true;
        }
        PluginRecord d2 = CoreApi.a().d(str);
        if (d2 == null || d2.c() == null || d2.c().I() != 1) {
            z = false;
        }
        if (z) {
            return a(str2);
        }
        return false;
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("[._]");
        if (split.length < 2) {
            return false;
        }
        try {
            int intValue = Integer.valueOf(split[0]).intValue();
            int intValue2 = Integer.valueOf(split[1]).intValue();
            if (intValue > 2 || (intValue == 2 && intValue2 >= 2)) {
                return true;
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static SecretKeySpec b(String str) throws Exception {
        MessageDigest instance = MessageDigest.getInstance("MD5");
        byte[] bytes = str.getBytes("UTF-8");
        instance.update(bytes, 0, bytes.length);
        return new SecretKeySpec(instance.digest(), a.b);
    }

    private static String b(String str, String str2) {
        try {
            SecretKeySpec b2 = b(str);
            Cipher instance = Cipher.getInstance(g);
            instance.init(1, b2, new IvParameterSpec(ByteUtils.a(f)));
            return ByteUtils.d(instance.doFinal(ByteUtils.a(str2)));
        } catch (Exception e2) {
            e2.printStackTrace();
            return str2;
        }
    }

    private static String c(String str, String str2) {
        try {
            SecretKeySpec b2 = b(str);
            Cipher instance = Cipher.getInstance(g);
            instance.init(2, b2, new IvParameterSpec(ByteUtils.a(f)));
            return ByteUtils.d(instance.doFinal(ByteUtils.a(str2)));
        } catch (Exception e2) {
            e2.printStackTrace();
            return str2;
        }
    }

    public static String a(String str, String str2, int i) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !a(i)) {
            return str2;
        }
        switch (i) {
            case 0:
                return str2;
            case 1:
                return b(str, str2);
            default:
                return str2;
        }
    }

    public static String b(String str, String str2, int i) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !a(i)) {
            return str2;
        }
        switch (i) {
            case 0:
                return str2;
            case 1:
                return c(str, str2);
            default:
                return str2;
        }
    }
}
