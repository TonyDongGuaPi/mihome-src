package org.mp4parser.support;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.BoxParser;
import org.mp4parser.IsoFile;
import org.mp4parser.ParsableBox;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.Hex;
import org.mp4parser.tools.IsoTypeWriter;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public abstract class AbstractBox implements ParsableBox {

    /* renamed from: a  reason: collision with root package name */
    private static Logger f4100a = Logger.a(AbstractBox.class);
    static final /* synthetic */ boolean t = (!AbstractBox.class.desiredAssertionStatus());
    private byte[] b;
    private ByteBuffer c;
    private ByteBuffer d = null;
    protected String r;
    boolean s;

    /* access modifiers changed from: protected */
    public abstract void a(ByteBuffer byteBuffer);

    /* access modifiers changed from: protected */
    public abstract long ai_();

    /* access modifiers changed from: protected */
    public abstract void b(ByteBuffer byteBuffer);

    protected AbstractBox(String str) {
        this.r = str;
        this.s = true;
    }

    protected AbstractBox(String str, byte[] bArr) {
        this.r = str;
        this.b = bArr;
        this.s = true;
    }

    @DoNotParseDetail
    public void a(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, long j, BoxParser boxParser) throws IOException {
        this.c = ByteBuffer.allocateDirect(CastUtils.a(j));
        int i = 0;
        int i2 = 0;
        while (((long) i) < j && (i2 = readableByteChannel.read(this.c)) != -1) {
            i += i2;
        }
        if (i2 == -1) {
            Logger logger = f4100a;
            logger.c(this + " might have been truncated by file end. bytesRead=" + i + " contentSize=" + j);
        }
        this.c.position(0);
        this.s = false;
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        if (this.s) {
            ByteBuffer allocate = ByteBuffer.allocate(CastUtils.a(c()));
            d(allocate);
            b(allocate);
            if (this.d != null) {
                this.d.rewind();
                while (this.d.remaining() > 0) {
                    allocate.put(this.d);
                }
            }
            writableByteChannel.write((ByteBuffer) allocate.rewind());
            return;
        }
        int i = 16;
        int i2 = e() ? 8 : 16;
        if (!"uuid".equals(ae_())) {
            i = 0;
        }
        ByteBuffer allocate2 = ByteBuffer.allocate(i2 + i);
        d(allocate2);
        writableByteChannel.write((ByteBuffer) allocate2.rewind());
        writableByteChannel.write((ByteBuffer) this.c.position(0));
    }

    public final synchronized void w() {
        Logger logger = f4100a;
        logger.a("parsing details of " + ae_());
        if (this.c != null) {
            ByteBuffer byteBuffer = this.c;
            this.s = true;
            byteBuffer.rewind();
            a(byteBuffer);
            if (byteBuffer.remaining() > 0) {
                this.d = byteBuffer.slice();
            }
            this.c = null;
            if (!t) {
                if (!c(byteBuffer)) {
                    throw new AssertionError();
                }
            }
        }
    }

    public long c() {
        long ai_ = this.s ? ai_() : (long) this.c.limit();
        int i = 0;
        long j = ai_ + ((long) ((ai_ >= 4294967288L ? 8 : 0) + 8 + ("uuid".equals(ae_()) ? 16 : 0)));
        if (this.d != null) {
            i = this.d.limit();
        }
        return j + ((long) i);
    }

    @DoNotParseDetail
    public String ae_() {
        return this.r;
    }

    @DoNotParseDetail
    public byte[] af_() {
        return this.b;
    }

    public boolean x() {
        return this.s;
    }

    private boolean c(ByteBuffer byteBuffer) {
        ByteBuffer allocate = ByteBuffer.allocate(CastUtils.a(ai_() + ((long) (this.d != null ? this.d.limit() : 0))));
        b(allocate);
        if (this.d != null) {
            this.d.rewind();
            while (this.d.remaining() > 0) {
                allocate.put(this.d);
            }
        }
        byteBuffer.rewind();
        allocate.rewind();
        if (byteBuffer.remaining() != allocate.remaining()) {
            System.err.print(String.valueOf(ae_()) + ": remaining differs " + byteBuffer.remaining() + " vs. " + allocate.remaining());
            f4100a.c(String.valueOf(ae_()) + ": remaining differs " + byteBuffer.remaining() + " vs. " + allocate.remaining());
            return false;
        }
        int position = byteBuffer.position();
        int limit = byteBuffer.limit() - 1;
        int limit2 = allocate.limit() - 1;
        while (limit >= position) {
            byte b2 = byteBuffer.get(limit);
            byte b3 = allocate.get(limit2);
            if (b2 != b3) {
                f4100a.c(String.format("%s: buffers differ at %d: %2X/%2X", new Object[]{ae_(), Integer.valueOf(limit), Byte.valueOf(b2), Byte.valueOf(b3)}));
                byte[] bArr = new byte[byteBuffer.remaining()];
                byte[] bArr2 = new byte[allocate.remaining()];
                byteBuffer.get(bArr);
                allocate.get(bArr2);
                System.err.println("original      : " + Hex.a(bArr, 4));
                System.err.println("reconstructed : " + Hex.a(bArr2, 4));
                return false;
            }
            limit--;
            limit2--;
        }
        return true;
    }

    private boolean e() {
        int i = "uuid".equals(ae_()) ? 24 : 8;
        if (!this.s) {
            return ((long) (this.c.limit() + i)) < IjkMediaMeta.AV_CH_WIDE_RIGHT;
        }
        return (ai_() + ((long) (this.d != null ? this.d.limit() : 0))) + ((long) i) < IjkMediaMeta.AV_CH_WIDE_RIGHT;
    }

    private void d(ByteBuffer byteBuffer) {
        if (e()) {
            IsoTypeWriter.b(byteBuffer, c());
            byteBuffer.put(IsoFile.a(ae_()));
        } else {
            IsoTypeWriter.b(byteBuffer, 1);
            byteBuffer.put(IsoFile.a(ae_()));
            IsoTypeWriter.a(byteBuffer, c());
        }
        if ("uuid".equals(ae_())) {
            byteBuffer.put(af_());
        }
    }
}
