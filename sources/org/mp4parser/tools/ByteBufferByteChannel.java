package org.mp4parser.tools;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

public class ByteBufferByteChannel implements ByteChannel {

    /* renamed from: a  reason: collision with root package name */
    ByteBuffer f4107a;

    public void close() throws IOException {
    }

    public boolean isOpen() {
        return true;
    }

    public ByteBufferByteChannel(byte[] bArr) {
        this(ByteBuffer.wrap(bArr));
    }

    public ByteBufferByteChannel(ByteBuffer byteBuffer) {
        this.f4107a = byteBuffer;
    }

    public int read(ByteBuffer byteBuffer) throws IOException {
        int remaining = byteBuffer.remaining();
        if (this.f4107a.remaining() <= 0) {
            return -1;
        }
        byteBuffer.put((ByteBuffer) this.f4107a.duplicate().limit(this.f4107a.position() + byteBuffer.remaining()));
        this.f4107a.position(this.f4107a.position() + remaining);
        return remaining;
    }

    public int write(ByteBuffer byteBuffer) throws IOException {
        int remaining = byteBuffer.remaining();
        this.f4107a.put(byteBuffer);
        return remaining;
    }
}
