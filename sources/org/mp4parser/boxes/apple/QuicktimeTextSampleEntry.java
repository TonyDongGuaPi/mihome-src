package org.mp4parser.boxes.apple;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.List;
import org.mp4parser.Box;
import org.mp4parser.BoxParser;
import org.mp4parser.boxes.sampleentry.AbstractSampleEntry;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class QuicktimeTextSampleEntry extends AbstractSampleEntry {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3799a = "text";
    int b;
    int c;
    int d;
    int e;
    int f;
    long g;
    long h;
    short i;
    short j;
    byte k;
    short l;
    int m = 65535;
    int n = 65535;
    int o = 65535;
    String p = "";
    int q;

    public QuicktimeTextSampleEntry() {
        super("text");
    }

    public void a(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, long j2, BoxParser boxParser) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(CastUtils.a(j2));
        readableByteChannel.read(allocate);
        allocate.position(6);
        this.q = IsoTypeReader.d(allocate);
        this.b = allocate.getInt();
        this.c = allocate.getInt();
        this.d = IsoTypeReader.d(allocate);
        this.e = IsoTypeReader.d(allocate);
        this.f = IsoTypeReader.d(allocate);
        this.g = IsoTypeReader.h(allocate);
        this.h = IsoTypeReader.h(allocate);
        this.i = allocate.getShort();
        this.j = allocate.getShort();
        this.k = allocate.get();
        this.l = allocate.getShort();
        this.m = IsoTypeReader.d(allocate);
        this.n = IsoTypeReader.d(allocate);
        this.o = IsoTypeReader.d(allocate);
        if (allocate.remaining() > 0) {
            byte[] bArr = new byte[IsoTypeReader.f(allocate)];
            allocate.get(bArr);
            this.p = new String(bArr);
            return;
        }
        this.p = null;
    }

    public void a(List<? extends Box> list) {
        throw new RuntimeException("QuicktimeTextSampleEntries may not have child boxes");
    }

    public void a(Box box) {
        throw new RuntimeException("QuicktimeTextSampleEntries may not have child boxes");
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        writableByteChannel.write(s());
        ByteBuffer allocate = ByteBuffer.allocate((this.p != null ? this.p.length() : 0) + 52);
        allocate.position(6);
        IsoTypeWriter.b(allocate, this.q);
        allocate.putInt(this.b);
        allocate.putInt(this.c);
        IsoTypeWriter.b(allocate, this.d);
        IsoTypeWriter.b(allocate, this.e);
        IsoTypeWriter.b(allocate, this.f);
        IsoTypeWriter.a(allocate, this.g);
        IsoTypeWriter.a(allocate, this.h);
        allocate.putShort(this.i);
        allocate.putShort(this.j);
        allocate.put(this.k);
        allocate.putShort(this.l);
        IsoTypeWriter.b(allocate, this.m);
        IsoTypeWriter.b(allocate, this.n);
        IsoTypeWriter.b(allocate, this.o);
        if (this.p != null) {
            IsoTypeWriter.d(allocate, this.p.length());
            allocate.put(this.p.getBytes());
        }
        writableByteChannel.write((ByteBuffer) allocate.rewind());
    }

    public long c() {
        long b2 = b() + 52 + ((long) (this.p != null ? this.p.length() : 0));
        return b2 + ((long) ((this.t || 8 + b2 >= IjkMediaMeta.AV_CH_WIDE_RIGHT) ? 16 : 8));
    }

    public int ah_() {
        return this.b;
    }

    public void f_(int i2) {
        this.b = i2;
    }

    public int e() {
        return this.c;
    }

    public void b(int i2) {
        this.c = i2;
    }

    public int f() {
        return this.d;
    }

    public void c(int i2) {
        this.d = i2;
    }

    public int g() {
        return this.e;
    }

    public void d(int i2) {
        this.e = i2;
    }

    public int h() {
        return this.f;
    }

    public void e(int i2) {
        this.f = i2;
    }

    public long i() {
        return this.g;
    }

    public void a(long j2) {
        this.g = j2;
    }

    public long j() {
        return this.h;
    }

    public void b(long j2) {
        this.h = j2;
    }

    public short k() {
        return this.i;
    }

    public void a(short s) {
        this.i = s;
    }

    public short l() {
        return this.j;
    }

    public void b(short s) {
        this.j = s;
    }

    public byte m() {
        return this.k;
    }

    public void a(byte b2) {
        this.k = b2;
    }

    public short n() {
        return this.l;
    }

    public void c(short s) {
        this.l = s;
    }

    public int o() {
        return this.m;
    }

    public void f(int i2) {
        this.m = i2;
    }

    public int p() {
        return this.n;
    }

    public void g(int i2) {
        this.n = i2;
    }

    public int q() {
        return this.o;
    }

    public void h(int i2) {
        this.o = i2;
    }

    public String r() {
        return this.p;
    }

    public void a(String str) {
        this.p = str;
    }
}
