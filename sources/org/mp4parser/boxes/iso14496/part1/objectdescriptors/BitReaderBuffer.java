package org.mp4parser.boxes.iso14496.part1.objectdescriptors;

import java.nio.ByteBuffer;

public class BitReaderBuffer {

    /* renamed from: a  reason: collision with root package name */
    int f3821a;
    int b;
    private ByteBuffer c;

    public BitReaderBuffer(ByteBuffer byteBuffer) {
        this.c = byteBuffer;
        this.f3821a = byteBuffer.position();
    }

    public boolean a() {
        return a(1) == 1;
    }

    public int a(int i) {
        int i2;
        int i3 = this.c.get(this.f3821a + (this.b / 8));
        if (i3 < 0) {
            i3 += 256;
        }
        int i4 = 8 - (this.b % 8);
        if (i <= i4) {
            i2 = ((i3 << (this.b % 8)) & 255) >> ((this.b % 8) + (i4 - i));
            this.b += i;
        } else {
            int i5 = i - i4;
            i2 = (a(i4) << i5) + a(i5);
        }
        ByteBuffer byteBuffer = this.c;
        int i6 = this.f3821a;
        double d = (double) this.b;
        Double.isNaN(d);
        byteBuffer.position(i6 + ((int) Math.ceil(d / 8.0d)));
        return i2;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        int i = 8 - (this.b % 8);
        if (i == 8) {
            i = 0;
        }
        a(i);
        return i;
    }

    public int d() {
        return (this.c.limit() * 8) - this.b;
    }
}
