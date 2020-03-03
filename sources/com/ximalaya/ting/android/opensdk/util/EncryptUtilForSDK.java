package com.ximalaya.ting.android.opensdk.util;

import android.content.Context;

public class EncryptUtilForSDK {

    /* renamed from: a  reason: collision with root package name */
    private static EncryptUtilForSDK f2254a;
    private Context b;

    public native byte[] decryptByPublicKey2(Context context, byte[] bArr, String str, String str2, String str3) throws Exception;

    public native String decryptRc4ByPublicKey(Context context, String str, String str2, String str3) throws Exception;

    static {
        try {
            System.loadLibrary("xmopendatacrypto");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a() {
        f2254a = null;
    }

    public static synchronized EncryptUtilForSDK b() {
        EncryptUtilForSDK encryptUtilForSDK;
        synchronized (EncryptUtilForSDK.class) {
            if (f2254a == null) {
                synchronized (EncryptUtilForSDK.class) {
                    if (f2254a == null) {
                        f2254a = new EncryptUtilForSDK();
                    }
                }
            }
            encryptUtilForSDK = f2254a;
        }
        return encryptUtilForSDK;
    }

    public void a(Context context) {
        if (context != null) {
            this.b = context.getApplicationContext();
        }
    }

    public byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[0];
        try {
            return decryptByPublicKey2(this.b, bArr, PayUtil.b(), PayUtil.c(), this.b.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return bArr2;
        }
    }

    public String a(String str) {
        try {
            return decryptRc4ByPublicKey(this.b, str, PayUtil.a(), this.b.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
