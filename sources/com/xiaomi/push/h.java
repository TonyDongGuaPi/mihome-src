package com.xiaomi.push;

import cn.com.fmsh.tsm.business.constants.Constants;
import com.coloros.mcssdk.c.a;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class h {

    /* renamed from: a  reason: collision with root package name */
    private static final byte[] f12765a = {Constants.TagName.PAY_ORDER_LIST, 23, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.ELECTRONIC_NUMBER, Constants.TagName.BUSINESS_ORDER_TYPE, 0, 4, 97, Constants.TagName.ORDER_BRIEF_INFO, 97, 2, 52, Constants.TagName.TERMINAL_BACK_INFO_LIST, 102, 18, 32};

    private static Cipher a(byte[] bArr, int i) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, a.b);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(f12765a);
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(i, secretKeySpec, ivParameterSpec);
        return instance;
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        return a(bArr, 2).doFinal(bArr2);
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        return a(bArr, 1).doFinal(bArr2);
    }
}
