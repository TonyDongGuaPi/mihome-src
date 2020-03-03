package com.xiaomi.accountsdk.utils;

import android.text.TextUtils;
import android.util.Base64;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.accountsdk.account.exception.CryptoException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.PublicKey;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class IpFilterHelper {
    private static final String SIMPLE_PATTERN_IP_FILTER = "\\/\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
    private static final String TAG = "IpFilterHelper";
    private static volatile PublicKey sRSAPublicKey;

    IpFilterHelper() {
    }

    static String envIPAddressIfHas(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Matcher matcher = Pattern.compile(SIMPLE_PATTERN_IP_FILTER).matcher(str);
        while (matcher.find()) {
            String group = matcher.group();
            str = str.replace(group, Operators.BRACKET_START_STR + rsaEnv(group) + Operators.BRACKET_END_STR);
        }
        return str;
    }

    private static String rsaEnv(String str) {
        try {
            if (sRSAPublicKey == null) {
                sRSAPublicKey = RSACoder.getCertificatePublicKey(RSACoder.SPECIFIED_RSA_PUBLIC_KEY);
            }
            return Base64.encodeToString(RSACoder.encrypt(str.getBytes("UTF-8"), (Key) sRSAPublicKey), 0);
        } catch (CryptoException e) {
            AccountLog.w(TAG, (Throwable) e);
            return null;
        } catch (UnsupportedEncodingException e2) {
            AccountLog.w(TAG, (Throwable) e2);
            return null;
        }
    }
}
