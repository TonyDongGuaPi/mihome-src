package org.mp4parser.muxer;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.mp4parser.tools.CastUtils;

public class InMemRandomAccessSourceImpl implements RandomAccessSource {

    /* renamed from: a  reason: collision with root package name */
    ByteBuffer f3985a;

    public void close() throws IOException {
    }

    public InMemRandomAccessSourceImpl(ByteBuffer byteBuffer) {
        this.f3985a = byteBuffer.duplicate();
    }

    public InMemRandomAccessSourceImpl(byte[] bArr) {
        this.f3985a = ByteBuffer.wrap(bArr);
    }

    public synchronized ByteBuffer a(long j, long j2) throws IOException {
        this.f3985a.position(CastUtils.a(j));
        return (ByteBuffer) this.f3985a.slice().limit(CastUtils.a(j2));
    }
}
