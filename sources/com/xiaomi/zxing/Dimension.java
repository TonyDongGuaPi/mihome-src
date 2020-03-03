package com.xiaomi.zxing;

public final class Dimension {

    /* renamed from: a  reason: collision with root package name */
    private final int f1624a;
    private final int b;

    public Dimension(int i, int i2) {
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException();
        }
        this.f1624a = i;
        this.b = i2;
    }

    public int a() {
        return this.f1624a;
    }

    public int b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Dimension)) {
            return false;
        }
        Dimension dimension = (Dimension) obj;
        if (this.f1624a == dimension.f1624a && this.b == dimension.b) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.f1624a * 32713) + this.b;
    }

    public String toString() {
        return this.f1624a + "x" + this.b;
    }
}
