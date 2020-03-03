package com.xiaomi.smarthome.library.common.wheel;

public class NumericWheelAdapter implements WheelAdapter {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18755a = 9;
    private static final int b = 0;
    private int c;
    private int d;
    private String e;

    public NumericWheelAdapter() {
        this(0, 9);
    }

    public NumericWheelAdapter(int i, int i2) {
        this(i, i2, (String) null);
    }

    public NumericWheelAdapter(int i, int i2, String str) {
        this.c = i;
        this.d = i2;
        this.e = str;
    }

    public String a(int i) {
        if (i < 0 || i >= a()) {
            return null;
        }
        int i2 = this.c + i;
        if (this.e == null) {
            return Integer.toString(i2);
        }
        return String.format(this.e, new Object[]{Integer.valueOf(i2)});
    }

    public int a() {
        return (this.d - this.c) + 1;
    }

    public int b() {
        int length = Integer.toString(Math.max(Math.abs(this.d), Math.abs(this.c))).length();
        return this.c < 0 ? length + 1 : length;
    }
}
