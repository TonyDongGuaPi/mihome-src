package org.mp4parser.muxer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.tools.CastUtils;

public class MemoryDataSourceImpl implements DataSource {

    /* renamed from: a  reason: collision with root package name */
    ByteBuffer f3986a;

    public void close() throws IOException {
    }

    public MemoryDataSourceImpl(byte[] bArr) {
        this.f3986a = ByteBuffer.wrap(bArr);
    }

    public MemoryDataSourceImpl(ByteBuffer byteBuffer) {
        this.f3986a = byteBuffer;
    }

    public int a(ByteBuffer byteBuffer) throws IOException {
        if (this.f3986a.remaining() == 0 && byteBuffer.remaining() != 0) {
            return -1;
        }
        int min = Math.min(byteBuffer.remaining(), this.f3986a.remaining());
        if (byteBuffer.hasArray()) {
            byteBuffer.put(this.f3986a.array(), this.f3986a.position(), min);
            this.f3986a.position(this.f3986a.position() + min);
        } else {
            byte[] bArr = new byte[min];
            this.f3986a.get(bArr);
            byteBuffer.put(bArr);
        }
        return min;
    }

    public long a() throws IOException {
        return (long) this.f3986a.capacity();
    }

    public long b() throws IOException {
        return (long) this.f3986a.position();
    }

    public void a(long j) throws IOException {
        this.f3986a.position(CastUtils.a(j));
    }

    public long a(long j, long j2, WritableByteChannel writableByteChannel) throws IOException {
        return (long) writableByteChannel.write((ByteBuffer) ((ByteBuffer) this.f3986a.position(CastUtils.a(j))).slice().limit(CastUtils.a(j2)));
    }

    public ByteBuffer a(long j, long j2) throws IOException {
        int position = this.f3986a.position();
        this.f3986a.position(CastUtils.a(j));
        ByteBuffer slice = this.f3986a.slice();
        slice.limit(CastUtils.a(j2));
        this.f3986a.position(position);
        return slice;
    }
}
