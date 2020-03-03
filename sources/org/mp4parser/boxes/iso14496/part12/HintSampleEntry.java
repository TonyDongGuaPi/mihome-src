package org.mp4parser.boxes.iso14496.part12;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.BoxParser;
import org.mp4parser.boxes.sampleentry.AbstractSampleEntry;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class HintSampleEntry extends AbstractSampleEntry {

    /* renamed from: a  reason: collision with root package name */
    protected byte[] f3852a;

    public HintSampleEntry(String str) {
        super(str);
    }

    public void a(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, long j, BoxParser boxParser) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        readableByteChannel.read(allocate);
        allocate.position(6);
        this.r = IsoTypeReader.d(allocate);
        this.f3852a = new byte[CastUtils.a(j - 8)];
        readableByteChannel.read(ByteBuffer.wrap(this.f3852a));
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        writableByteChannel.write(s());
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.position(6);
        IsoTypeWriter.b(allocate, this.r);
        allocate.rewind();
        writableByteChannel.write(allocate);
        writableByteChannel.write(ByteBuffer.wrap(this.f3852a));
    }

    public byte[] e() {
        return this.f3852a;
    }

    public void a(byte[] bArr) {
        this.f3852a = bArr;
    }

    public long c() {
        int i = 8;
        long length = (long) (this.f3852a.length + 8);
        if (this.t || 8 + length >= IjkMediaMeta.AV_CH_WIDE_RIGHT) {
            i = 16;
        }
        return length + ((long) i);
    }
}
