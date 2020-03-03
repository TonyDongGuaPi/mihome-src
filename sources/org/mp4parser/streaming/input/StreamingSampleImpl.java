package org.mp4parser.streaming.input;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import org.mp4parser.streaming.SampleExtension;
import org.mp4parser.streaming.StreamingSample;

public class StreamingSampleImpl implements StreamingSample {

    /* renamed from: a  reason: collision with root package name */
    private ByteBuffer f4069a;
    private long b;
    private HashMap<Class<? extends SampleExtension>, SampleExtension> c = new HashMap<>();

    public StreamingSampleImpl(ByteBuffer byteBuffer, long j) {
        this.f4069a = byteBuffer.duplicate();
        this.b = j;
    }

    public StreamingSampleImpl(byte[] bArr, long j) {
        this.b = j;
        this.f4069a = ByteBuffer.wrap(bArr);
    }

    public StreamingSampleImpl(List<ByteBuffer> list, long j) {
        this.b = j;
        int i = 0;
        for (ByteBuffer limit : list) {
            i = i + 4 + limit.limit();
        }
        this.f4069a = ByteBuffer.allocate(i);
        for (ByteBuffer next : list) {
            this.f4069a.put((byte) ((next.limit() & -16777216) >> 24));
            this.f4069a.put((byte) ((next.limit() & 16711680) >> 16));
            this.f4069a.put((byte) ((next.limit() & 65280) >> 8));
            this.f4069a.put((byte) (next.limit() & 255));
            this.f4069a.put((ByteBuffer) next.rewind());
        }
    }

    public ByteBuffer a() {
        return this.f4069a;
    }

    public long b() {
        return this.b;
    }

    public <T extends SampleExtension> T a(Class<T> cls) {
        return (SampleExtension) this.c.get(cls);
    }

    public void a(SampleExtension sampleExtension) {
        this.c.put(sampleExtension.getClass(), sampleExtension);
    }

    public <T extends SampleExtension> T b(Class<T> cls) {
        return (SampleExtension) this.c.remove(cls);
    }
}
