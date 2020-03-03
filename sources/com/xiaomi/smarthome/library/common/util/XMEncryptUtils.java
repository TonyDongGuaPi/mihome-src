package com.xiaomi.smarthome.library.common.util;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.coloros.mcssdk.c.a;
import com.xiaomi.smarthome.library.crypto.Base64Coder;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public abstract class XMEncryptUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final byte[] f18752a = {Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.MAIN_ORDER_ID, 20, ScriptToolsConst.TagName.TagSerial, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.QUERY_RECORD_COUNT, Constants.TagName.BUSINESS_ORDER_TYPE, 97, 22, 97, 21, Constants.TagName.ELECTRONIC_STATE, 54, Constants.TagName.ORDER_RANGE_TYPE, Constants.TagName.USER_LOGIN_FAIL_COUNT, 3};
    private static final byte[] b = {Constants.TagName.PAY_ORDER_LIST, 23, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.ELECTRONIC_NUMBER, Constants.TagName.BUSINESS_ORDER_TYPE, 0, 4, 97, Constants.TagName.ORDER_BRIEF_INFO, 97, 2, 52, Constants.TagName.TERMINAL_BACK_INFO_LIST, 102, 18, 32};

    public static String a(String str, String str2) {
        int i;
        int i2;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2 != null ? XMStringUtils.b(str2) : f18752a, a.b);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, new IvParameterSpec(b));
            str.getBytes("UTF-8");
            byte[] a2 = Base64Coder.a(str);
            byte[] update = instance.update(a2, 0, a2.length);
            byte[] doFinal = instance.doFinal();
            if (update == null) {
                i = 0;
            } else {
                i = update.length;
            }
            int length = i + (doFinal == null ? 0 : doFinal.length);
            if (length <= 0) {
                return null;
            }
            byte[] bArr = new byte[length];
            if (update != null) {
                System.arraycopy(update, 0, bArr, 0, update.length);
                i2 = update.length;
            } else {
                i2 = 0;
            }
            if (doFinal != null) {
                System.arraycopy(doFinal, 0, bArr, i2, doFinal.length);
            }
            return new String(bArr, "UTF-8");
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public static String b(String str, String str2) throws Exception {
        SecretKeySpec secretKeySpec;
        if (str2 != null) {
            byte[] bArr = new byte[16];
            System.arraycopy(XMStringUtils.b(str2), 0, bArr, 0, 16);
            secretKeySpec = new SecretKeySpec(bArr, a.b);
        } else {
            secretKeySpec = new SecretKeySpec(f18752a, a.b);
        }
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, secretKeySpec, new IvParameterSpec(b));
        return String.valueOf(Base64Coder.a(instance.doFinal(str.getBytes("UTF-8"))));
    }
}
