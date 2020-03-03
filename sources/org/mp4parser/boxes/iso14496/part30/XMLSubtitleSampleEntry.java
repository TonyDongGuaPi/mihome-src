package org.mp4parser.boxes.iso14496.part30;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.BoxParser;
import org.mp4parser.boxes.sampleentry.AbstractSampleEntry;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import org.mp4parser.tools.Mp4Arrays;
import org.mp4parser.tools.Utf8;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class XMLSubtitleSampleEntry extends AbstractSampleEntry {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3931a = "stpp";
    private String b = "";
    private String c = "";
    private String d = "";

    public XMLSubtitleSampleEntry() {
        super(f3931a);
    }

    public long c() {
        int i = 8;
        long b2 = b() + ((long) (this.b.length() + 8 + this.c.length() + this.d.length() + 3));
        if (this.t || 8 + b2 >= IjkMediaMeta.AV_CH_WIDE_RIGHT) {
            i = 16;
        }
        return b2 + ((long) i);
    }

    public void a(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, long j, BoxParser boxParser) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        readableByteChannel.read((ByteBuffer) allocate.rewind());
        allocate.position(6);
        this.r = IsoTypeReader.d(allocate);
        byte[] bArr = new byte[0];
        while (true) {
            int read = Channels.newInputStream(readableByteChannel).read();
            if (read == 0) {
                break;
            }
            bArr = Mp4Arrays.a(bArr, (byte) read);
        }
        this.b = Utf8.a(bArr);
        byte[] bArr2 = new byte[0];
        while (true) {
            int read2 = Channels.newInputStream(readableByteChannel).read();
            if (read2 == 0) {
                break;
            }
            bArr2 = Mp4Arrays.a(bArr2, (byte) read2);
        }
        this.c = Utf8.a(bArr2);
        byte[] bArr3 = new byte[0];
        while (true) {
            int read3 = Channels.newInputStream(readableByteChannel).read();
            if (read3 == 0) {
                this.d = Utf8.a(bArr3);
                a(readableByteChannel, j - ((long) ((((byteBuffer.remaining() + this.b.length()) + this.c.length()) + this.d.length()) + 3)), boxParser);
                return;
            }
            bArr3 = Mp4Arrays.a(bArr3, (byte) read3);
        }
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        writableByteChannel.write(s());
        ByteBuffer allocate = ByteBuffer.allocate(this.b.length() + 8 + this.c.length() + this.d.length() + 3);
        allocate.position(6);
        IsoTypeWriter.b(allocate, this.r);
        IsoTypeWriter.c(allocate, this.b);
        IsoTypeWriter.c(allocate, this.c);
        IsoTypeWriter.c(allocate, this.d);
        writableByteChannel.write((ByteBuffer) allocate.rewind());
        a(writableByteChannel);
    }

    public String e() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public String f() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public String g() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }
}
