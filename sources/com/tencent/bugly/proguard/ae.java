package com.tencent.bugly.proguard;

import com.coloros.mcssdk.c.a;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class ae implements ag {

    /* renamed from: a  reason: collision with root package name */
    private String f9028a = null;

    public final byte[] a(byte[] bArr) throws Exception {
        if (this.f9028a == null || bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(b + " ");
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.f9028a.getBytes(), a.b);
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, secretKeySpec, new IvParameterSpec(this.f9028a.getBytes()));
        byte[] doFinal = instance.doFinal(bArr);
        StringBuffer stringBuffer2 = new StringBuffer();
        for (byte b2 : doFinal) {
            stringBuffer2.append(b2 + " ");
        }
        return doFinal;
    }

    public final byte[] b(byte[] bArr) throws Exception, NoSuchAlgorithmException {
        if (this.f9028a == null || bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(b + " ");
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.f9028a.getBytes(), a.b);
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, secretKeySpec, new IvParameterSpec(this.f9028a.getBytes()));
        byte[] doFinal = instance.doFinal(bArr);
        StringBuffer stringBuffer2 = new StringBuffer();
        for (byte b2 : doFinal) {
            stringBuffer2.append(b2 + " ");
        }
        return doFinal;
    }

    public final void a(String str) {
        if (str != null) {
            for (int length = str.length(); length < 16; length++) {
                str = str + "0";
            }
            this.f9028a = str.substring(0, 16);
        }
    }
}
