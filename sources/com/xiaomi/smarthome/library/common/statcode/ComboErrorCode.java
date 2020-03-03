package com.xiaomi.smarthome.library.common.statcode;

import com.amap.api.services.core.AMapException;

public class ComboErrorCode {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18647a = 2101;
    public static final int b = 2401;
    public static final int c = 2201;
    public static final int d = 2501;
    public static final int e = 2701;
    public static final int f = 6;
    public static final int g = 50;
    public static final int h = 35;
    public static final int i = 2210;
    public static final int j = 0;
    private static final int k = 0;
    private static final int l = 1;
    private static final int m = 2;

    public static int a(int i2) {
        if (i2 == 0) {
            return AMapException.CODE_AMAP_NEARBY_INVALID_USERID;
        }
        if (i2 == 1) {
            return 2400;
        }
        if (i2 == 2) {
            return AMapException.CODE_AMAP_CLIENT_UPLOADAUTO_STARTED_ERROR;
        }
        return 2900;
    }

    public static int a(boolean z, boolean z2, int i2) {
        if (!z) {
            return 2201;
        }
        return (i2 != 0 || z2) ? i2 + i : i;
    }

    public static boolean a(int i2, int i3) {
        return (i3 == 0 && i2 > 6) || (i3 == 1 && i2 > 50) || (i3 == 2 && i2 > 35);
    }

    public static int b(int i2) {
        return 29000 - i2;
    }
}
