package com.alipay.mobile.common.logging.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import android.util.Pair;
import java.util.UUID;

public class HybridEncryption {

    /* renamed from: a  reason: collision with root package name */
    private static HybridEncryption f968a;
    private static String b = (AESUtil.class.getName() + RSAUtil.class.getName() + Base64.class.getName() + LoggingUtil.class.getName() + MD5Util.class.getName());
    private String c;
    private byte[] d = null;
    private String e = null;
    private boolean f;
    private boolean g;
    private boolean h;

    private HybridEncryption(Context context) {
        this.c = a(context);
    }

    private String a(Context context) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (Throwable th) {
            Log.e("Hybrid", "obtainPublicKey", th);
            applicationInfo = null;
        }
        if (applicationInfo == null || applicationInfo.metaData == null) {
            return null;
        }
        return applicationInfo.metaData.getString("setting.logging.encryption.pubkey");
    }

    public static synchronized void createInstance(Context context) {
        synchronized (HybridEncryption.class) {
            if (f968a == null) {
                f968a = new HybridEncryption(context);
            }
        }
    }

    public static HybridEncryption getInstance() {
        return f968a;
    }

    public String getSecureSeed() {
        return this.e;
    }

    public Pair<String, String> encrypt(byte[] bArr) {
        byte[] bArr2;
        if (bArr == null) {
            return null;
        }
        if (this.d == null) {
            try {
                bArr2 = UUID.randomUUID().toString().getBytes();
            } catch (Throwable th) {
                if (!this.f) {
                    this.f = true;
                    Log.e("Hybrid", "encrypt", th);
                }
                bArr2 = null;
            }
            if (bArr2 == null) {
                try {
                    bArr2 = String.valueOf(System.currentTimeMillis()).getBytes();
                } catch (Throwable th2) {
                    if (!this.g) {
                        this.g = true;
                        Log.e("Hybrid", "encrypt", th2);
                    }
                }
            }
            this.d = AESUtil.getRawKey(bArr2);
            this.e = Base64.encode(RSAUtil.encrypt(this.d, this.c));
        }
        if (this.d == null || this.e == null) {
            return null;
        }
        try {
            return new Pair<>(this.e, Base64.encode(AESUtil.encrypt(this.d, bArr)));
        } catch (Throwable th3) {
            if (!this.h) {
                this.h = true;
                Log.e("Hybrid", "encrypt", th3);
            }
            return null;
        }
    }
}
