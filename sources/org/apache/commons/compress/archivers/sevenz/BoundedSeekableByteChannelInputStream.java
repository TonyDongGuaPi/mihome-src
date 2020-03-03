package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

class BoundedSeekableByteChannelInputStream extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3222a = 8192;
    private final ByteBuffer b;
    private final SeekableByteChannel c;
    private long d;

    public void close() {
    }

    public BoundedSeekableByteChannelInputStream(SeekableByteChannel seekableByteChannel, long j) {
        this.c = seekableByteChannel;
        this.d = j;
        if (j >= 8192 || j <= 0) {
            this.b = ByteBuffer.allocate(8192);
        } else {
            this.b = ByteBuffer.allocate((int) j);
        }
    }

    public int read() throws IOException {
        if (this.d <= 0) {
            return -1;
        }
        this.d--;
        int a2 = a(1);
        if (a2 < 0) {
            return a2;
        }
        return this.b.get() & 255;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        ByteBuffer byteBuffer;
        if (this.d == 0) {
            return -1;
        }
        if (((long) i2) > this.d) {
            i2 = (int) this.d;
        }
        if (i2 <= this.b.capacity()) {
            byteBuffer = this.b;
            i3 = a(i2);
        } else {
            byteBuffer = ByteBuffer.allocate(i2);
            i3 = this.c.read(byteBuffer);
            byteBuffer.flip();
        }
        if (i3 >= 0) {
            byteBuffer.get(bArr, i, i3);
            this.d -= (long) i3;
        }
        return i3;
    }

    private int a(int i) throws IOException {
        this.b.rewind().limit(i);
        int read = this.c.read(this.b);
        this.b.flip();
        return read;
    }
}
