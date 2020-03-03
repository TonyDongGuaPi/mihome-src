package org.mp4parser.support;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.List;
import java.util.logging.Logger;
import org.mp4parser.Box;
import org.mp4parser.BoxParser;
import org.mp4parser.FullBox;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public abstract class FullContainerBox extends AbstractContainerBox implements FullBox {

    /* renamed from: a  reason: collision with root package name */
    private static Logger f4103a = Logger.getLogger(FullContainerBox.class.getName());
    private int b;
    private int c;

    public FullContainerBox(String str) {
        super(str);
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

    public <T extends Box> List<T> a(Class<T> cls) {
        return a(cls, false);
    }

    public void a(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, long j, BoxParser boxParser) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        readableByteChannel.read(allocate);
        a((ByteBuffer) allocate.rewind());
        super.a(readableByteChannel, byteBuffer, j, boxParser);
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        super.b(writableByteChannel);
    }

    public String toString() {
        return String.valueOf(getClass().getSimpleName()) + "[childBoxes]";
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

    /* access modifiers changed from: protected */
    public ByteBuffer s() {
        ByteBuffer byteBuffer;
        if (this.t || c() >= IjkMediaMeta.AV_CH_WIDE_RIGHT) {
            byte[] bArr = new byte[20];
            bArr[3] = 1;
            bArr[4] = this.s.getBytes()[0];
            bArr[5] = this.s.getBytes()[1];
            bArr[6] = this.s.getBytes()[2];
            bArr[7] = this.s.getBytes()[3];
            byteBuffer = ByteBuffer.wrap(bArr);
            byteBuffer.position(8);
            IsoTypeWriter.a(byteBuffer, c());
            b(byteBuffer);
        } else {
            byte[] bArr2 = new byte[12];
            bArr2[4] = this.s.getBytes()[0];
            bArr2[5] = this.s.getBytes()[1];
            bArr2[6] = this.s.getBytes()[2];
            bArr2[7] = this.s.getBytes()[3];
            byteBuffer = ByteBuffer.wrap(bArr2);
            IsoTypeWriter.b(byteBuffer, c());
            byteBuffer.position(8);
            b(byteBuffer);
        }
        byteBuffer.rewind();
        return byteBuffer;
    }
}
