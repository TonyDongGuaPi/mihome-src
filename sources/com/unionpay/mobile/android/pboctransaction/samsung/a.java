package com.unionpay.mobile.android.pboctransaction.samsung;

import android.text.TextUtils;
import com.miuipub.internal.hybrid.SignUtils;
import com.unionpay.mobile.android.pboctransaction.e;
import java.security.PrivateKey;
import javax.crypto.Cipher;

public final class a {
    public static String a(PrivateKey privateKey, String str) {
        if (TextUtils.isEmpty(str) || privateKey == null) {
            return "";
        }
        try {
            Cipher instance = Cipher.getInstance(SignUtils.b);
            byte[] bArr = new byte[245];
            System.arraycopy(str.getBytes(), 0, bArr, 0, str.getBytes().length);
            instance.init(1, privateKey);
            return e.a(instance.doFinal(bArr));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
