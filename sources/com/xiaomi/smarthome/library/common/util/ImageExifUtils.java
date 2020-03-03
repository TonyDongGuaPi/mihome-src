package com.xiaomi.smarthome.library.common.util;

public final class ImageExifUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18681a = -1;
    public static final int b = Integer.MAX_VALUE;

    public static float b(int i) {
        if (i == 6) {
            return 90.0f;
        }
        if (i == 3) {
            return 180.0f;
        }
        return i == 8 ? 270.0f : 0.0f;
    }

    public static int a(float f) {
        if (ImageUtils.a(f, 0.0f)) {
            return 1;
        }
        if (ImageUtils.a(f, 90.0f)) {
            return 6;
        }
        if (ImageUtils.a(f, 180.0f)) {
            return 3;
        }
        if (ImageUtils.a(f, 270.0f)) {
            return 8;
        }
        return 1;
    }

    public static int a(int i) {
        int i2 = (i + 360) % 360;
        if (i2 == 0) {
            return 1;
        }
        if (i2 == 90) {
            return 6;
        }
        if (i2 == 180) {
            return 3;
        }
        return i2 == 270 ? 8 : 1;
    }
}
