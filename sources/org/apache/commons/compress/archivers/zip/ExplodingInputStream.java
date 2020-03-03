package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.io.InputStream;

class ExplodingInputStream extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    private final InputStream f3247a;
    private BitStream b;
    private final int c;
    private final int d;
    private final int e;
    private BinaryTree f;
    private BinaryTree g;
    private BinaryTree h;
    private final CircularBuffer i = new CircularBuffer(32768);

    public ExplodingInputStream(int i2, int i3, InputStream inputStream) {
        if (i2 != 4096 && i2 != 8192) {
            throw new IllegalArgumentException("The dictionary size must be 4096 or 8192");
        } else if (i3 == 2 || i3 == 3) {
            this.c = i2;
            this.d = i3;
            this.e = i3;
            this.f3247a = inputStream;
        } else {
            throw new IllegalArgumentException("The number of trees must be 2 or 3");
        }
    }

    private void a() throws IOException {
        if (this.b == null) {
            if (this.d == 3) {
                this.f = BinaryTree.a(this.f3247a, 256);
            }
            this.g = BinaryTree.a(this.f3247a, 64);
            this.h = BinaryTree.a(this.f3247a, 64);
            this.b = new BitStream(this.f3247a);
        }
    }

    public int read() throws IOException {
        if (!this.i.a()) {
            b();
        }
        return this.i.b();
    }

    private void b() throws IOException {
        int i2;
        a();
        int a2 = this.b.a();
        if (a2 == 1) {
            if (this.f != null) {
                i2 = this.f.a(this.b);
            } else {
                i2 = this.b.b();
            }
            if (i2 != -1) {
                this.i.a(i2);
            }
        } else if (a2 == 0) {
            int i3 = this.c == 4096 ? 6 : 7;
            int a3 = (int) this.b.a(i3);
            int a4 = this.h.a(this.b);
            if (a4 != -1 || a3 > 0) {
                int i4 = (a4 << i3) | a3;
                int a5 = this.g.a(this.b);
                if (a5 == 63) {
                    a5 = (int) (((long) a5) + this.b.a(8));
                }
                this.i.a(i4 + 1, a5 + this.e);
            }
        }
    }
}
