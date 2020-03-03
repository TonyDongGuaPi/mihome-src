package com.mi.global.bbs.utils;

import android.text.TextUtils;
import com.mi.util.AesEncryptionUtil;

public class CheckUtil {
    public static String getCheckAes(String str) {
        String a2 = AesEncryptionUtil.a(str);
        return TextUtils.isEmpty(a2) ? str : a2;
    }
}
