package com.xiaomi.smarthome.scene.geofence.manager.model;

public class IZatGeoFenceItem extends GeoFenceItem {
    protected final int f;
    protected final int g;
    protected final int h;
    protected final int i;
    protected final int j;

    protected IZatGeoFenceItem(double d, double d2, int i2, int i3, int i4, int i5, int i6, int i7) {
        super(d, d2, i2);
        this.f = i3;
        this.g = i4;
        this.h = i5;
        this.i = i6;
        this.j = i7;
    }

    protected IZatGeoFenceItem(double d, double d2, double d3, int i2, int i3, int i4, int i5, int i6, int i7) {
        super(d, d2, d3, i2);
        this.f = i3;
        this.g = i4;
        this.h = i5;
        this.i = i6;
        this.j = i7;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        protected double f21575a;
        protected double b;
        protected double c;
        protected int d;
        protected int e;
        protected int f;
        protected int g;
        protected int h;
        private int i;

        public IZatGeoFenceItem a() {
            return new IZatGeoFenceItem(this.f21575a, this.b, this.c, this.i, this.d, this.e, this.f, this.g, this.h);
        }

        public Builder(double d2, double d3, int i2) {
            this.f21575a = d2;
            this.b = d3;
            this.c = 100.0d;
        }

        public Builder(double d2, double d3, int i2, int i3) {
            this.f21575a = d2;
            this.b = d3;
            this.c = (double) i2;
        }

        public Builder a(int i2) {
            this.i = i2;
            return this;
        }

        public Builder b(int i2) {
            this.d = i2;
            return this;
        }

        public Builder c(int i2) {
            this.e = i2;
            return this;
        }

        public Builder d(int i2) {
            this.f = i2;
            return this;
        }

        public Builder e(int i2) {
            this.g = i2;
            return this;
        }

        public Builder f(int i2) {
            this.h = i2;
            return this;
        }
    }
}
