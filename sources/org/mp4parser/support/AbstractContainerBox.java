package org.mp4parser.support;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.BasicContainer;
import org.mp4parser.BoxParser;
import org.mp4parser.Container;
import org.mp4parser.ParsableBox;
import org.mp4parser.tools.IsoTypeWriter;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class AbstractContainerBox extends BasicContainer implements ParsableBox {
    protected String s;
    protected boolean t;
    Container u;

    public AbstractContainerBox(String str) {
        this.s = str;
    }

    public void a(Container container) {
        this.u = container;
    }

    public long c() {
        long b = b();
        return b + ((long) ((this.t || 8 + b >= IjkMediaMeta.AV_CH_WIDE_RIGHT) ? 16 : 8));
    }

    public String ae_() {
        return this.s;
    }

    /* access modifiers changed from: protected */
    public ByteBuffer s() {
        ByteBuffer byteBuffer;
        if (this.t || c() >= IjkMediaMeta.AV_CH_WIDE_RIGHT) {
            byte[] bArr = new byte[16];
            bArr[3] = 1;
            bArr[4] = this.s.getBytes()[0];
            bArr[5] = this.s.getBytes()[1];
            bArr[6] = this.s.getBytes()[2];
            bArr[7] = this.s.getBytes()[3];
            byteBuffer = ByteBuffer.wrap(bArr);
            byteBuffer.position(8);
            IsoTypeWriter.a(byteBuffer, c());
        } else {
            byte[] bArr2 = new byte[8];
            bArr2[4] = this.s.getBytes()[0];
            bArr2[5] = this.s.getBytes()[1];
            bArr2[6] = this.s.getBytes()[2];
            bArr2[7] = this.s.getBytes()[3];
            byteBuffer = ByteBuffer.wrap(bArr2);
            IsoTypeWriter.b(byteBuffer, c());
        }
        byteBuffer.rewind();
        return byteBuffer;
    }

    public void a(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, long j, BoxParser boxParser) throws IOException {
        this.t = byteBuffer.remaining() == 16;
        a(readableByteChannel, j, boxParser);
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        writableByteChannel.write(s());
        a(writableByteChannel);
    }
}
