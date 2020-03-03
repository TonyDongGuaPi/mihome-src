package com.xiaomi.ai.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import com.alipay.sdk.util.i;
import com.taobao.weex.el.parse.Operators;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import miuipub.security.DigestUtils;

public class g {

    /* renamed from: a  reason: collision with root package name */
    private PackageManager f9952a;
    private String b;
    private String c;
    private String d;
    private String e;

    public g(Context context) {
        Signature[] signatureArr;
        this.f9952a = context.getPackageManager();
        this.b = context.getPackageName();
        Signature[] signatureArr2 = new Signature[0];
        try {
            signatureArr = this.f9952a.getPackageInfo(context.getPackageName(), 64).signatures;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            signatureArr = signatureArr2;
        }
        if (signatureArr.length != 0) {
            Signature signature = signatureArr[0];
            this.c = a("MD5", signature.toByteArray());
            this.d = a(DigestUtils.b, signature.toByteArray());
            this.e = a("SHA-256", signature.toByteArray());
        }
    }

    private static String a(String str, byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.reset();
            instance.update(bArr);
            return a(instance.digest());
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString);
            if (i < bArr.length - 1) {
                sb.append(Operators.CONDITION_IF_MIDDLE);
            }
        }
        return sb.toString();
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.e;
    }

    public String d() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.b);
        stringBuffer.append(i.b);
        stringBuffer.append(this.c);
        stringBuffer.append(i.b);
        stringBuffer.append(this.e);
        return stringBuffer.toString();
    }
}
