package com.megvii.licencemanage.sdk.jni;

import android.content.Context;

public class LicenceApi {
    public static native String nativeGetLicense(Context context, String str, int i, long[] jArr);

    public static native int nativeSetLicense(Context context, String str);

    static {
        System.loadLibrary("MegviiIDCardQuality_1.2.2");
    }
}
