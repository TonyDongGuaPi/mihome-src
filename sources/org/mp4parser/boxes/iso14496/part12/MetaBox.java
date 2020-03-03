package org.mp4parser.boxes.iso14496.part12;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.BoxParser;
import org.mp4parser.support.AbstractContainerBox;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class MetaBox extends AbstractContainerBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3862a = "meta";
    private int b;
    private int c;

    public MetaBox() {
        super("meta");
    }

    public int d() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public int e() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    /* access modifiers changed from: protected */
    public final long a(ByteBuffer byteBuffer) {
        this.b = IsoTypeReader.f(byteBuffer);
        this.c = IsoTypeReader.c(byteBuffer);
        return 4;
    }

    /* access modifiers changed from: protected */
    public final void b(ByteBuffer byteBuffer) {
        IsoTypeWriter.d(byteBuffer, this.b);
        IsoTypeWriter.a(byteBuffer, this.c);
    }

    public void a(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, long j, BoxParser boxParser) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        readableByteChannel.read(allocate);
        a((ByteBuffer) allocate.rewind());
        a(readableByteChannel, j - 4, boxParser);
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        writableByteChannel.write(s());
        ByteBuffer allocate = ByteBuffer.allocate(4);
        b(allocate);
        writableByteChannel.write((ByteBuffer) allocate.rewind());
        a(writableByteChannel);
    }

    public long c() {
        long b2 = b() + 4;
        return b2 + ((long) ((this.t || b2 >= IjkMediaMeta.AV_CH_WIDE_RIGHT) ? 16 : 8));
    }
}
