package org.mp4parser.boxes.iso14496.part12;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.BoxParser;
import org.mp4parser.FullBox;
import org.mp4parser.support.AbstractContainerBox;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class ItemProtectionBox extends AbstractContainerBox implements FullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3857a = "ipro";
    private int b;
    private int c;

    public ItemProtectionBox() {
        super(f3857a);
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

    public SchemeInformationBox e() {
        if (!a(SchemeInformationBox.class).isEmpty()) {
            return a(SchemeInformationBox.class).get(0);
        }
        return null;
    }

    public void a(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, long j, BoxParser boxParser) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(6);
        readableByteChannel.read(allocate);
        allocate.rewind();
        this.b = IsoTypeReader.f(allocate);
        this.c = IsoTypeReader.c(allocate);
        a(readableByteChannel, j - 6, boxParser);
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        writableByteChannel.write(s());
        ByteBuffer allocate = ByteBuffer.allocate(6);
        IsoTypeWriter.d(allocate, this.b);
        IsoTypeWriter.a(allocate, this.c);
        IsoTypeWriter.b(allocate, a().size());
        writableByteChannel.write((ByteBuffer) allocate.rewind());
        a(writableByteChannel);
    }

    public long c() {
        long b2 = b() + 6;
        return b2 + ((long) ((this.t || b2 >= IjkMediaMeta.AV_CH_WIDE_RIGHT) ? 16 : 8));
    }
}
