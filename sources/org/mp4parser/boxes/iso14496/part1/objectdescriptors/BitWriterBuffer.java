package org.mp4parser.boxes.iso14496.part1.objectdescriptors;

import java.nio.ByteBuffer;

public class BitWriterBuffer {
    static final /* synthetic */ boolean c = (!BitWriterBuffer.class.desiredAssertionStatus());

    /* renamed from: a  reason: collision with root package name */
    int f3822a;
    int b = 0;
    private ByteBuffer d;

    public BitWriterBuffer(ByteBuffer byteBuffer) {
        this.d = byteBuffer;
        this.f3822a = byteBuffer.position();
    }

    public void a(boolean z) {
        a(z ? 1 : 0, 1);
    }

    public void a(int i, int i2) {
        int i3;
        int i4 = 0;
        if (c || i <= (i3 = (1 << i2) - 1)) {
            int i5 = 8 - (this.b % 8);
            if (i2 <= i5) {
                int i6 = this.d.get(this.f3822a + (this.b / 8));
                if (i6 < 0) {
                    i6 += 256;
                }
                int i7 = i6 + (i << (i5 - i2));
                ByteBuffer byteBuffer = this.d;
                int i8 = this.f3822a + (this.b / 8);
                if (i7 > 127) {
                    i7 -= 256;
                }
                byteBuffer.put(i8, (byte) i7);
                this.b += i2;
            } else {
                int i9 = i2 - i5;
                a(i >> i9, i5);
                a(i & ((1 << i9) - 1), i9);
            }
            ByteBuffer byteBuffer2 = this.d;
            int i10 = this.f3822a + (this.b / 8);
            if (this.b % 8 > 0) {
                i4 = 1;
            }
            byteBuffer2.position(i10 + i4);
            return;
        }
        throw new AssertionError(String.format("Trying to write a value bigger (%s) than the number bits (%s) allows. Please mask the value before writing it and make your code is really working as intended.", new Object[]{Integer.valueOf(i), Integer.valueOf(i3)}));
    }
}
