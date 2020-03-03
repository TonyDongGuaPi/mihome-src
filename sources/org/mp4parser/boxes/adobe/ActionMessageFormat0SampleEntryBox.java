package org.mp4parser.boxes.adobe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.BoxParser;
import org.mp4parser.boxes.sampleentry.AbstractSampleEntry;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class ActionMessageFormat0SampleEntryBox extends AbstractSampleEntry {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3784a = "amf0";

    public ActionMessageFormat0SampleEntryBox() {
        super(f3784a);
    }

    public void a(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, long j, BoxParser boxParser) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        readableByteChannel.read(allocate);
        allocate.position(6);
        this.r = IsoTypeReader.d(allocate);
        a(readableByteChannel, j - 8, boxParser);
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        writableByteChannel.write(s());
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.position(6);
        IsoTypeWriter.b(allocate, this.r);
        writableByteChannel.write((ByteBuffer) allocate.rewind());
        a(writableByteChannel);
    }

    public long c() {
        long b = b() + 8;
        return b + ((long) ((this.t || b >= IjkMediaMeta.AV_CH_WIDE_RIGHT) ? 16 : 8));
    }
}
