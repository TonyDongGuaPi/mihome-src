package org.apache.commons.compress.archivers.zip;

class CircularBuffer {

    /* renamed from: a  reason: collision with root package name */
    private final int f3246a;
    private final byte[] b;
    private int c;
    private int d;

    CircularBuffer(int i) {
        this.f3246a = i;
        this.b = new byte[i];
    }

    public boolean a() {
        return this.c != this.d;
    }

    public void a(int i) {
        this.b[this.d] = (byte) i;
        this.d = (this.d + 1) % this.f3246a;
    }

    public int b() {
        if (!a()) {
            return -1;
        }
        byte b2 = this.b[this.c];
        this.c = (this.c + 1) % this.f3246a;
        return b2 & 255;
    }

    public void a(int i, int i2) {
        int i3 = this.d - i;
        int i4 = i2 + i3;
        while (i3 < i4) {
            this.b[this.d] = this.b[(this.f3246a + i3) % this.f3246a];
            this.d = (this.d + 1) % this.f3246a;
            i3++;
        }
    }
}
