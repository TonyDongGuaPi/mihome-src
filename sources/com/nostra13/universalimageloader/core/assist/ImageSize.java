package com.nostra13.universalimageloader.core.assist;

public class ImageSize {

    /* renamed from: a  reason: collision with root package name */
    private static final int f8475a = 9;
    private static final String b = "x";
    private final int c;
    private final int d;

    public ImageSize(int i, int i2) {
        this.c = i;
        this.d = i2;
    }

    public ImageSize(int i, int i2, int i3) {
        if (i3 % 180 == 0) {
            this.c = i;
            this.d = i2;
            return;
        }
        this.c = i2;
        this.d = i;
    }

    public int a() {
        return this.c;
    }

    public int b() {
        return this.d;
    }

    public ImageSize a(int i) {
        return new ImageSize(this.c / i, this.d / i);
    }

    public ImageSize a(float f) {
        return new ImageSize((int) (((float) this.c) * f), (int) (((float) this.d) * f));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(9);
        sb.append(this.c);
        sb.append("x");
        sb.append(this.d);
        return sb.toString();
    }
}
