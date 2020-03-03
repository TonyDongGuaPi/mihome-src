package org.apache.commons.compress.compressors.deflate;

public class DeflateParameters {

    /* renamed from: a  reason: collision with root package name */
    private boolean f3321a = true;
    private int b = -1;

    public boolean a() {
        return this.f3321a;
    }

    public void a(boolean z) {
        this.f3321a = z;
    }

    public int b() {
        return this.b;
    }

    public void a(int i) {
        if (i < -1 || i > 9) {
            throw new IllegalArgumentException("Invalid Deflate compression level: " + i);
        }
        this.b = i;
    }
}
