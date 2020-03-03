package com.ta.utdid2.a.a;

import cn.com.fmsh.tsm.business.constants.Constants;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class a {
    public static String a(String str) {
        byte[] bArr;
        try {
            bArr = a(a(), str.getBytes());
        } catch (Exception unused) {
            bArr = null;
        }
        if (bArr != null) {
            return a(bArr);
        }
        return null;
    }

    public static String b(String str) {
        try {
            return new String(b(a(), a(str)));
        } catch (Exception unused) {
            return null;
        }
    }

    private static byte[] a() throws Exception {
        return e.a(new byte[]{Framer.ENTER_FRAME_PREFIX, 83, -50, Constants.TagName.OPERATION_STEP, -84, Constants.TagName.URL_TYPE, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.PAY_ORDER, 10, Constants.TagName.CARD_APP_ACTIVATION_STATUS, 22, Constants.TagName.STATION_ID, -11, 30, Constants.TagName.ORDER_TYPE, Constants.TagName.PAY_CHANNEL_NAME});
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, com.coloros.mcssdk.c.a.b);
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
        return instance.doFinal(bArr2);
    }

    private static byte[] b(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, com.coloros.mcssdk.c.a.b);
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
        return instance.doFinal(bArr2);
    }

    /* renamed from: a  reason: collision with other method in class */
    private static byte[] m51a(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).byteValue();
        }
        return bArr;
    }

    private static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte a2 : bArr) {
            a(stringBuffer, a2);
        }
        return stringBuffer.toString();
    }

    private static void a(StringBuffer stringBuffer, byte b) {
        stringBuffer.append(com.coloros.mcssdk.c.a.f.charAt((b >> 4) & 15));
        stringBuffer.append(com.coloros.mcssdk.c.a.f.charAt(b & 15));
    }
}
