package com.ximalaya.ting.android.player;

import android.text.TextUtils;
import com.mi.global.bbs.utils.ConnectionHelper;
import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5 {
    public static String a(byte[] bArr) {
        String str = "";
        if (bArr != null) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(bArr);
                String bigInteger = new BigInteger(1, instance.digest()).toString(16);
                while (true) {
                    str = bigInteger;
                    if (str.length() >= 32) {
                        break;
                    }
                    bigInteger = "0" + str;
                }
            } catch (Exception e) {
                Logger.a((Object) "md5加密出错" + e.getMessage());
            }
        }
        return str;
    }

    public static String a(String str) {
        if (str != null) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(str.getBytes());
                String bigInteger = new BigInteger(1, instance.digest()).toString(16);
                while (true) {
                    str = bigInteger;
                    if (str.length() >= 32) {
                        break;
                    }
                    bigInteger = "0" + str;
                }
            } catch (Exception e) {
                Logger.a((Object) "md5加密出错" + e.getMessage());
            }
        }
        return str;
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int indexOf = str.indexOf("?");
        int indexOf2 = str.indexOf("/", ConnectionHelper.HTTP_PREFIX.length());
        if (indexOf == -1) {
            indexOf = str.length();
        }
        return a(str.substring(indexOf2, indexOf));
    }
}
