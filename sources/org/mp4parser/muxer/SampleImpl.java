package org.mp4parser.muxer;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.tools.CastUtils;

public class SampleImpl implements Sample {

    /* renamed from: a  reason: collision with root package name */
    private final long f3989a;
    private final long b;
    private ByteBuffer[] c;

    public SampleImpl(ByteBuffer byteBuffer) {
        this.f3989a = -1;
        this.b = (long) byteBuffer.limit();
        this.c = new ByteBuffer[]{byteBuffer};
    }

    public SampleImpl(ByteBuffer[] byteBufferArr) {
        this.f3989a = -1;
        int i = 0;
        for (ByteBuffer remaining : byteBufferArr) {
            i += remaining.remaining();
        }
        this.b = (long) i;
        this.c = byteBufferArr;
    }

    public SampleImpl(long j, long j2, ByteBuffer byteBuffer) {
        this.f3989a = j;
        this.b = j2;
        this.c = new ByteBuffer[]{byteBuffer};
    }

    public void a(WritableByteChannel writableByteChannel) throws IOException {
        for (ByteBuffer duplicate : this.c) {
            writableByteChannel.write(duplicate.duplicate());
        }
    }

    public long a() {
        return this.b;
    }

    public ByteBuffer b() {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[CastUtils.a(this.b)]);
        for (ByteBuffer duplicate : this.c) {
            wrap.put(duplicate.duplicate());
        }
        wrap.rewind();
        return wrap;
    }

    public String toString() {
        return "SampleImpl" + "{offset=" + this.f3989a + "{size=" + this.b + Operators.BLOCK_END;
    }
}
