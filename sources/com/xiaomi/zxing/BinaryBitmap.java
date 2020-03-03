package com.xiaomi.zxing;

import com.xiaomi.zxing.common.BitArray;
import com.xiaomi.zxing.common.BitMatrix;

public final class BinaryBitmap {

    /* renamed from: a  reason: collision with root package name */
    private final Binarizer f1622a;
    private BitMatrix b;

    public BinaryBitmap(Binarizer binarizer) {
        if (binarizer != null) {
            this.f1622a = binarizer;
            return;
        }
        throw new IllegalArgumentException("Binarizer must be non-null.");
    }

    public int a() {
        return this.f1622a.c();
    }

    public int b() {
        return this.f1622a.d();
    }

    public BitArray a(int i, BitArray bitArray) throws NotFoundException {
        return this.f1622a.a(i, bitArray);
    }

    public BitMatrix c() throws NotFoundException {
        if (this.b == null) {
            this.b = this.f1622a.b();
        }
        return this.b;
    }

    public boolean d() {
        return this.f1622a.a().b();
    }

    public BinaryBitmap a(int i, int i2, int i3, int i4) {
        return new BinaryBitmap(this.f1622a.a(this.f1622a.a().a(i, i2, i3, i4)));
    }

    public boolean e() {
        return this.f1622a.a().c();
    }

    public BinaryBitmap f() {
        return new BinaryBitmap(this.f1622a.a(this.f1622a.a().e()));
    }

    public BinaryBitmap g() {
        return new BinaryBitmap(this.f1622a.a(this.f1622a.a().f()));
    }

    public String toString() {
        try {
            return c().toString();
        } catch (NotFoundException unused) {
            return "";
        }
    }
}
