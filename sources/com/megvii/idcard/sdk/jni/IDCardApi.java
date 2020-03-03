package com.megvii.idcard.sdk.jni;

import android.content.Context;

public class IDCardApi {
    public static native byte[] embedToJpgImgData(byte[] bArr, int i, int i2, int i3);

    public static native float[] nativeCalculateQuality(long j);

    public static native float[] nativeDetect(long j, byte[] bArr, int i, int i2, int i3);

    public static native long nativeGetApiExpication(Context context);

    public static native long nativeGetApiName();

    public static native float[] nativeGetIDCardConfig(long j);

    public static native String nativeGetVersion();

    public static native long nativeInit(Context context, byte[] bArr);

    public static native void nativeRelease(long j);

    public static native int nativeSetIDCardConfig(long j, int i, float f, float f2, float f3, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9);

    static {
        System.loadLibrary("MegviiIDCardQuality_1.2.2");
    }
}
