package org.mp4parser.boxes.sampleentry;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.BoxParser;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class Ovc1VisualSampleEntryImpl extends AbstractSampleEntry {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3951a = "ovc1";
    private byte[] b = new byte[0];

    public Ovc1VisualSampleEntryImpl() {
        super(f3951a);
    }

    public byte[] e() {
        return this.b;
    }

    public void a(byte[] bArr) {
        this.b = bArr;
    }

    public void a(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, long j, BoxParser boxParser) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(CastUtils.a(j));
        readableByteChannel.read(allocate);
        allocate.position(6);
        this.r = IsoTypeReader.d(allocate);
        this.b = new byte[allocate.remaining()];
        allocate.get(this.b);
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        writableByteChannel.write(s());
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.position(6);
        IsoTypeWriter.b(allocate, this.r);
        writableByteChannel.write((ByteBuffer) allocate.rewind());
        writableByteChannel.write(ByteBuffer.wrap(this.b));
    }

    public long c() {
        int i = 16;
        if (!this.t && ((long) (this.b.length + 16)) < IjkMediaMeta.AV_CH_WIDE_RIGHT) {
            i = 8;
        }
        return ((long) i) + ((long) this.b.length) + 8;
    }
}
