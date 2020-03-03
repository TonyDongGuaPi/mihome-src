package com.reactnative.camera.utils;

public class ImageDimensions {

    /* renamed from: a  reason: collision with root package name */
    private int f8682a;
    private int b;
    private int c;
    private int d;

    public ImageDimensions(int i, int i2) {
        this(i, i2, 0);
    }

    public ImageDimensions(int i, int i2, int i3) {
        this(i, i2, i3, -1);
    }

    public ImageDimensions(int i, int i2, int i3, int i4) {
        this.f8682a = i;
        this.b = i2;
        this.c = i4;
        this.d = i3;
    }

    public boolean a() {
        return this.d % 180 == 90;
    }

    public int b() {
        if (a()) {
            return this.b;
        }
        return this.f8682a;
    }

    public int c() {
        if (a()) {
            return this.f8682a;
        }
        return this.b;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ImageDimensions)) {
            return super.equals(obj);
        }
        ImageDimensions imageDimensions = (ImageDimensions) obj;
        return imageDimensions.b() == b() && imageDimensions.c() == c() && imageDimensions.e() == e() && imageDimensions.d() == d();
    }
}
