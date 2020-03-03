package org.mp4parser.boxes.sampleentry;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.BoxParser;
import org.mp4parser.Container;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import org.mp4parser.tools.Utf8;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public final class VisualSampleEntry extends AbstractSampleEntry implements Container {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3955a = "mp4v";
    public static final String b = "s263";
    public static final String c = "avc1";
    public static final String d = "avc3";
    public static final String e = "drmi";
    public static final String f = "hvc1";
    public static final String g = "hev1";
    public static final String h = "encv";
    static final /* synthetic */ boolean i = (!VisualSampleEntry.class.desiredAssertionStatus());
    private int j;
    private int k;
    private double l = 72.0d;
    private double m = 72.0d;
    private int n = 1;
    private String o = "";
    private int p = 24;
    private long[] q = new long[3];

    public VisualSampleEntry() {
        super(c);
    }

    public VisualSampleEntry(String str) {
        super(str);
    }

    public void a(String str) {
        this.s = str;
    }

    public int e() {
        return this.j;
    }

    public void b(int i2) {
        this.j = i2;
    }

    public int f() {
        return this.k;
    }

    public void c(int i2) {
        this.k = i2;
    }

    public double g() {
        return this.l;
    }

    public void a(double d2) {
        this.l = d2;
    }

    public double h() {
        return this.m;
    }

    public void b(double d2) {
        this.m = d2;
    }

    public int i() {
        return this.n;
    }

    public void d(int i2) {
        this.n = i2;
    }

    public String j() {
        return this.o;
    }

    public void b(String str) {
        this.o = str;
    }

    public int k() {
        return this.p;
    }

    public void e(int i2) {
        this.p = i2;
    }

    public void a(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, long j2, BoxParser boxParser) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(78);
        readableByteChannel.read(allocate);
        allocate.position(6);
        this.r = IsoTypeReader.d(allocate);
        long d2 = (long) IsoTypeReader.d(allocate);
        if (i || 0 == d2) {
            long d3 = (long) IsoTypeReader.d(allocate);
            if (i || 0 == d3) {
                this.q[0] = IsoTypeReader.b(allocate);
                this.q[1] = IsoTypeReader.b(allocate);
                this.q[2] = IsoTypeReader.b(allocate);
                this.j = IsoTypeReader.d(allocate);
                this.k = IsoTypeReader.d(allocate);
                this.l = IsoTypeReader.i(allocate);
                this.m = IsoTypeReader.i(allocate);
                long b2 = IsoTypeReader.b(allocate);
                if (i || 0 == b2) {
                    this.n = IsoTypeReader.d(allocate);
                    int f2 = IsoTypeReader.f(allocate);
                    if (f2 > 31) {
                        f2 = 31;
                    }
                    byte[] bArr = new byte[f2];
                    allocate.get(bArr);
                    this.o = Utf8.a(bArr);
                    if (f2 < 31) {
                        allocate.get(new byte[(31 - f2)]);
                    }
                    this.p = IsoTypeReader.d(allocate);
                    long d4 = (long) IsoTypeReader.d(allocate);
                    if (i || 65535 == d4) {
                        a(readableByteChannel, j2 - 78, boxParser);
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError("reserved byte not 0");
            }
            throw new AssertionError("reserved byte not 0");
        }
        throw new AssertionError("reserved byte not 0");
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        writableByteChannel.write(s());
        ByteBuffer allocate = ByteBuffer.allocate(78);
        allocate.position(6);
        IsoTypeWriter.b(allocate, this.r);
        IsoTypeWriter.b(allocate, 0);
        IsoTypeWriter.b(allocate, 0);
        IsoTypeWriter.b(allocate, this.q[0]);
        IsoTypeWriter.b(allocate, this.q[1]);
        IsoTypeWriter.b(allocate, this.q[2]);
        IsoTypeWriter.b(allocate, e());
        IsoTypeWriter.b(allocate, f());
        IsoTypeWriter.a(allocate, g());
        IsoTypeWriter.a(allocate, h());
        IsoTypeWriter.b(allocate, 0);
        IsoTypeWriter.b(allocate, i());
        IsoTypeWriter.d(allocate, Utf8.b(j()));
        allocate.put(Utf8.a(j()));
        int b2 = Utf8.b(j());
        while (b2 < 31) {
            b2++;
            allocate.put((byte) 0);
        }
        IsoTypeWriter.b(allocate, k());
        IsoTypeWriter.b(allocate, 65535);
        writableByteChannel.write((ByteBuffer) allocate.rewind());
        a(writableByteChannel);
    }

    public long c() {
        long b2 = b() + 78;
        return b2 + ((long) ((this.t || 8 + b2 >= IjkMediaMeta.AV_CH_WIDE_RIGHT) ? 16 : 8));
    }
}
