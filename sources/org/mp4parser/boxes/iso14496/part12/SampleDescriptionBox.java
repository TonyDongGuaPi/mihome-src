package org.mp4parser.boxes.iso14496.part12;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Iterator;
import org.mp4parser.BoxParser;
import org.mp4parser.FullBox;
import org.mp4parser.boxes.sampleentry.AbstractSampleEntry;
import org.mp4parser.support.AbstractContainerBox;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class SampleDescriptionBox extends AbstractContainerBox implements FullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3880a = "stsd";
    private int b;
    private int c;

    public SampleDescriptionBox() {
        super("stsd");
    }

    public int ag_() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public int d() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public void a(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, long j, BoxParser boxParser) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        readableByteChannel.read(allocate);
        allocate.rewind();
        this.b = IsoTypeReader.f(allocate);
        this.c = IsoTypeReader.c(allocate);
        a(readableByteChannel, j - 8, boxParser);
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        writableByteChannel.write(s());
        ByteBuffer allocate = ByteBuffer.allocate(8);
        IsoTypeWriter.d(allocate, this.b);
        IsoTypeWriter.a(allocate, this.c);
        IsoTypeWriter.b(allocate, (long) a().size());
        writableByteChannel.write((ByteBuffer) allocate.rewind());
        a(writableByteChannel);
    }

    public AbstractSampleEntry e() {
        Iterator<AbstractSampleEntry> it = a(AbstractSampleEntry.class).iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    public long c() {
        long b2 = b() + 8;
        return b2 + ((long) ((this.t || 8 + b2 >= IjkMediaMeta.AV_CH_WIDE_RIGHT) ? 16 : 8));
    }
}
