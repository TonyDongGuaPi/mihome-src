package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public final class bv extends bz {

    /* renamed from: a  reason: collision with root package name */
    private Context f6527a;
    private String b;
    private ah e;
    private Object[] f;

    public bv(Context context, bz bzVar, ah ahVar, String str, Object... objArr) {
        super(bzVar);
        this.f6527a = context;
        this.b = str;
        this.e = ahVar;
        this.f = objArr;
    }

    private String b() {
        try {
            return String.format(ad.c(this.b), this.f);
        } catch (Throwable th) {
            th.printStackTrace();
            aq.b(th, "ofm", "gpj");
            return "";
        }
    }

    /* access modifiers changed from: protected */
    public final byte[] a(byte[] bArr) throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        String a2 = ad.a(bArr);
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        String a3 = ad.a(this.e.b(ad.a(b())));
        return ad.a("{\"pinfo\":\"" + a3 + "\",\"els\":[" + a2 + "]}");
    }
}
