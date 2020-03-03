package org.apache.commons.compress.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SeekableByteChannel;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class SeekableInMemoryByteChannel implements SeekableByteChannel {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3352a = 1073741823;
    private byte[] b;
    private final AtomicBoolean c;
    private int d;
    private int e;

    public SeekableInMemoryByteChannel(byte[] bArr) {
        this.c = new AtomicBoolean();
        this.b = bArr;
        this.e = bArr.length;
    }

    public SeekableInMemoryByteChannel() {
        this(new byte[0]);
    }

    public SeekableInMemoryByteChannel(int i) {
        this(new byte[i]);
    }

    public long position() {
        return (long) this.d;
    }

    public SeekableByteChannel position(long j) throws IOException {
        b();
        if (j < 0 || j > 2147483647L) {
            throw new IllegalArgumentException("Position has to be in range 0.. 2147483647");
        }
        this.d = (int) j;
        return this;
    }

    public long size() {
        return (long) this.e;
    }

    public SeekableByteChannel truncate(long j) {
        if (((long) this.e) > j) {
            this.e = (int) j;
        }
        c();
        return this;
    }

    public int read(ByteBuffer byteBuffer) throws IOException {
        b();
        c();
        int remaining = byteBuffer.remaining();
        int i = this.e - this.d;
        if (i <= 0) {
            return -1;
        }
        if (remaining > i) {
            remaining = i;
        }
        byteBuffer.put(this.b, this.d, remaining);
        this.d += remaining;
        return remaining;
    }

    public void close() {
        this.c.set(true);
    }

    public boolean isOpen() {
        return !this.c.get();
    }

    public int write(ByteBuffer byteBuffer) throws IOException {
        b();
        int remaining = byteBuffer.remaining();
        if (remaining > this.e - this.d) {
            int i = this.d + remaining;
            if (i < 0) {
                a(Integer.MAX_VALUE);
                remaining = Integer.MAX_VALUE - this.d;
            } else {
                a(i);
            }
        }
        byteBuffer.get(this.b, this.d, remaining);
        this.d += remaining;
        if (this.e < this.d) {
            this.e = this.d;
        }
        return remaining;
    }

    public byte[] a() {
        return this.b;
    }

    private void a(int i) {
        int length = this.b.length;
        if (length <= 0) {
            length = 1;
        }
        if (i < f3352a) {
            while (length < i) {
                length <<= 1;
            }
            i = length;
        }
        this.b = Arrays.copyOf(this.b, i);
    }

    private void b() throws ClosedChannelException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
    }

    private void c() {
        if (this.d > this.e) {
            this.d = this.e;
        }
    }
}
