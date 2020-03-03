package com.amap.api.location;

public class CoordUtil {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f1252a = false;

    public static native int convertToGcj(double[] dArr, double[] dArr2);

    public static boolean isLoadedSo() {
        return f1252a;
    }

    public static void setLoadedSo(boolean z) {
        f1252a = z;
    }
}
