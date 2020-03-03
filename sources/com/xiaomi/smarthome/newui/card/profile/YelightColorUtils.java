package com.xiaomi.smarthome.newui.card.profile;

public class YelightColorUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final float f20591a = 35.0f;
    public static final float b = 360.0f;
    public static final float c = 0.0f;
    public static final float d = 0.8f;
    public static final float e = 0.4f;
    public static final int f = 6500;
    public static final int g = 1700;
    public static final float h = 0.95f;
    public static final float i = 0.5f;
    public static final int j = 100;

    public static float a(float f2, int i2, int i3) {
        if (f2 < 0.4f) {
            f2 = 0.4f;
        }
        if (f2 > 0.8f) {
            f2 = 0.8f;
        }
        return ((float) i2) + ((0.8f - f2) * (((float) (i3 - i2)) / 0.4f));
    }

    public static float a(int i2, int i3, int i4) {
        if (i2 < i3) {
            i2 = i3;
        }
        if (i2 > i4) {
            i2 = i4;
        }
        return (((float) (i4 - i2)) * (0.4f / ((float) (i4 - i3)))) + 0.4f;
    }

    public static int a(float f2) {
        int i2 = (int) (((f2 - 0.5f) / 0.45f) * 100.0f);
        if (i2 <= 0) {
            i2 = 1;
        }
        if (i2 > 100) {
            return 100;
        }
        return i2;
    }

    public static float b(int i2) {
        if (i2 < 1) {
            i2 = 1;
        }
        if (i2 > 100) {
            i2 = 100;
        }
        return (((float) i2) * 0.0045f) + 0.5f;
    }

    public static float a(int i2) {
        return a(i2, (int) g, (int) f);
    }
}
