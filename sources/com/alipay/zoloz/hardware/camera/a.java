package com.alipay.zoloz.hardware.camera;

import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public class a {

    /* renamed from: a  reason: collision with root package name */
    ByteBuffer f1197a;
    int b;
    int c;
    int d;
    ShortBuffer e;
    int f;
    int g;
    int h;
    int i;
    boolean j;

    public a() {
    }

    public a(ByteBuffer byteBuffer, int i2, int i3, int i4, ShortBuffer shortBuffer, int i5, int i6, int i7, int i8) {
        this(byteBuffer, i2, i3, i4, shortBuffer, i5, i6, i7, i8, false);
    }

    public a(ByteBuffer byteBuffer, int i2, int i3, int i4, ShortBuffer shortBuffer, int i5, int i6, int i7, int i8, boolean z) {
        this.f1197a = byteBuffer;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = shortBuffer;
        this.f = i5;
        this.g = i6;
        this.h = i7;
        this.i = i8;
        this.j = z;
    }

    public ByteBuffer a() {
        return this.f1197a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public ShortBuffer e() {
        return this.e;
    }

    public int f() {
        return this.f;
    }

    public int g() {
        return this.g;
    }

    public int h() {
        return this.h;
    }

    public int i() {
        return this.i;
    }
}
