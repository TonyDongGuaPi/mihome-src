package org.mp4parser.boxes.sampleentry;

import com.google.android.exoplayer2.metadata.id3.InternalFrame;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.Box;
import org.mp4parser.BoxParser;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public final class AudioSampleEntry extends AbstractSampleEntry {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3949a = "samr";
    public static final String b = "sawb";
    public static final String c = "mp4a";
    public static final String d = "drms";
    public static final String e = "alac";
    public static final String f = "owma";
    public static final String g = "ac-3";
    public static final String h = "ec-3";
    public static final String i = "mlpa";
    public static final String j = "dtsl";
    public static final String k = "dtsh";
    public static final String l = "dtse";
    public static final String m = "enca";
    private long A;
    private int B;
    private long C;
    private byte[] D;
    private int n;
    private int o;
    private long p;
    private int q;
    private int v;
    private int w;
    private long x;
    private long y;
    private long z;

    public AudioSampleEntry(String str) {
        super(str);
    }

    public void a(String str) {
        this.s = str;
    }

    public int e() {
        return this.n;
    }

    public void b(int i2) {
        this.n = i2;
    }

    public int f() {
        return this.o;
    }

    public void c(int i2) {
        this.o = i2;
    }

    public long g() {
        return this.p;
    }

    public void a(long j2) {
        this.p = j2;
    }

    public int h() {
        return this.q;
    }

    public void d(int i2) {
        this.q = i2;
    }

    public int i() {
        return this.v;
    }

    public void e(int i2) {
        this.v = i2;
    }

    public int j() {
        return this.w;
    }

    public void f(int i2) {
        this.w = i2;
    }

    public long k() {
        return this.x;
    }

    public void b(long j2) {
        this.x = j2;
    }

    public long l() {
        return this.y;
    }

    public void c(long j2) {
        this.y = j2;
    }

    public long m() {
        return this.z;
    }

    public void d(long j2) {
        this.z = j2;
    }

    public long n() {
        return this.A;
    }

    public void e(long j2) {
        this.A = j2;
    }

    public byte[] o() {
        return this.D;
    }

    public void a(byte[] bArr) {
        this.D = bArr;
    }

    public int p() {
        return this.B;
    }

    public void g(int i2) {
        this.B = i2;
    }

    public long q() {
        return this.C;
    }

    public void f(long j2) {
        this.C = j2;
    }

    public void a(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, long j2, BoxParser boxParser) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(28);
        readableByteChannel.read(allocate);
        allocate.position(6);
        this.r = IsoTypeReader.d(allocate);
        this.q = IsoTypeReader.d(allocate);
        this.B = IsoTypeReader.d(allocate);
        this.C = IsoTypeReader.b(allocate);
        this.n = IsoTypeReader.d(allocate);
        this.o = IsoTypeReader.d(allocate);
        this.v = IsoTypeReader.d(allocate);
        this.w = IsoTypeReader.d(allocate);
        this.p = IsoTypeReader.b(allocate);
        int i2 = 16;
        if (!this.s.equals(i)) {
            this.p >>>= 16;
        }
        if (this.q == 1) {
            ByteBuffer allocate2 = ByteBuffer.allocate(16);
            readableByteChannel.read(allocate2);
            allocate2.rewind();
            this.x = IsoTypeReader.b(allocate2);
            this.y = IsoTypeReader.b(allocate2);
            this.z = IsoTypeReader.b(allocate2);
            this.A = IsoTypeReader.b(allocate2);
        }
        int i3 = 36;
        if (this.q == 2) {
            ByteBuffer allocate3 = ByteBuffer.allocate(36);
            readableByteChannel.read(allocate3);
            allocate3.rewind();
            this.x = IsoTypeReader.b(allocate3);
            this.y = IsoTypeReader.b(allocate3);
            this.z = IsoTypeReader.b(allocate3);
            this.A = IsoTypeReader.b(allocate3);
            this.D = new byte[20];
            allocate3.get(this.D);
        }
        if (f.equals(this.s)) {
            System.err.println(f);
            long j3 = j2 - 28;
            if (this.q != 1) {
                i2 = 0;
            }
            long j4 = j3 - ((long) i2);
            if (this.q != 2) {
                i3 = 0;
            }
            final long j5 = j4 - ((long) i3);
            final ByteBuffer allocate4 = ByteBuffer.allocate(CastUtils.a(j5));
            readableByteChannel.read(allocate4);
            a((Box) new Box() {
                public String ae_() {
                    return InternalFrame.ID;
                }

                public long c() {
                    return j5;
                }

                public void b(WritableByteChannel writableByteChannel) throws IOException {
                    allocate4.rewind();
                    writableByteChannel.write(allocate4);
                }
            });
            return;
        }
        long j6 = j2 - 28;
        if (this.q != 1) {
            i2 = 0;
        }
        long j7 = j6 - ((long) i2);
        if (this.q != 2) {
            i3 = 0;
        }
        a(readableByteChannel, j7 - ((long) i3), boxParser);
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        writableByteChannel.write(s());
        int i2 = 0;
        int i3 = (this.q == 1 ? 16 : 0) + 28;
        if (this.q == 2) {
            i2 = 36;
        }
        ByteBuffer allocate = ByteBuffer.allocate(i3 + i2);
        allocate.position(6);
        IsoTypeWriter.b(allocate, this.r);
        IsoTypeWriter.b(allocate, this.q);
        IsoTypeWriter.b(allocate, this.B);
        IsoTypeWriter.b(allocate, this.C);
        IsoTypeWriter.b(allocate, this.n);
        IsoTypeWriter.b(allocate, this.o);
        IsoTypeWriter.b(allocate, this.v);
        IsoTypeWriter.b(allocate, this.w);
        if (this.s.equals(i)) {
            IsoTypeWriter.b(allocate, g());
        } else {
            IsoTypeWriter.b(allocate, g() << 16);
        }
        if (this.q == 1) {
            IsoTypeWriter.b(allocate, this.x);
            IsoTypeWriter.b(allocate, this.y);
            IsoTypeWriter.b(allocate, this.z);
            IsoTypeWriter.b(allocate, this.A);
        }
        if (this.q == 2) {
            IsoTypeWriter.b(allocate, this.x);
            IsoTypeWriter.b(allocate, this.y);
            IsoTypeWriter.b(allocate, this.z);
            IsoTypeWriter.b(allocate, this.A);
            allocate.put(this.D);
        }
        writableByteChannel.write((ByteBuffer) allocate.rewind());
        a(writableByteChannel);
    }

    public long c() {
        int i2 = 0;
        int i3 = 16;
        int i4 = (this.q == 1 ? 16 : 0) + 28;
        if (this.q == 2) {
            i2 = 36;
        }
        long b2 = ((long) (i4 + i2)) + b();
        if (!this.t && 8 + b2 < IjkMediaMeta.AV_CH_WIDE_RIGHT) {
            i3 = 8;
        }
        return b2 + ((long) i3);
    }

    public String toString() {
        return "AudioSampleEntry{bytesPerSample=" + this.A + ", bytesPerFrame=" + this.z + ", bytesPerPacket=" + this.y + ", samplesPerPacket=" + this.x + ", packetSize=" + this.w + ", compressionId=" + this.v + ", soundVersion=" + this.q + ", sampleRate=" + this.p + ", sampleSize=" + this.o + ", channelCount=" + this.n + ", boxes=" + a() + Operators.BLOCK_END;
    }
}
