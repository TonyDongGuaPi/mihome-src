package com.ximalaya.ting.android.opensdk.httputil.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMACSHA1 {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1998a = "HmacSHA1";
    private static final String b = "UTF-8";

    public static byte[] a(String str, String str2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("UTF-8"), f1998a);
        Mac instance = Mac.getInstance(f1998a);
        instance.init(secretKeySpec);
        return instance.doFinal(str.getBytes("UTF-8"));
    }
}
