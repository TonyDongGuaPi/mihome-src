package org.mp4parser.boxes.sampleentry;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;
import org.mp4parser.BoxParser;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class TextSampleEntry extends AbstractSampleEntry {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3952a = "tx3g";
    public static final String b = "enct";
    private long c;
    private int d;
    private int e;
    private int[] f = new int[4];
    private BoxRecord g = new BoxRecord();
    private StyleRecord h = new StyleRecord();

    public String toString() {
        return "TextSampleEntry";
    }

    public TextSampleEntry() {
        super(f3952a);
    }

    public TextSampleEntry(String str) {
        super(str);
    }

    public void a(String str) {
        this.s = str;
    }

    public void a(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, long j, BoxParser boxParser) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(38);
        readableByteChannel.read(allocate);
        allocate.position(6);
        this.r = IsoTypeReader.d(allocate);
        this.c = IsoTypeReader.b(allocate);
        this.d = IsoTypeReader.f(allocate);
        this.e = IsoTypeReader.f(allocate);
        this.f = new int[4];
        this.f[0] = IsoTypeReader.f(allocate);
        this.f[1] = IsoTypeReader.f(allocate);
        this.f[2] = IsoTypeReader.f(allocate);
        this.f[3] = IsoTypeReader.f(allocate);
        this.g = new BoxRecord();
        this.g.a(allocate);
        this.h = new StyleRecord();
        this.h.a(allocate);
        a(readableByteChannel, j - 38, boxParser);
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        writableByteChannel.write(s());
        ByteBuffer allocate = ByteBuffer.allocate(38);
        allocate.position(6);
        IsoTypeWriter.b(allocate, this.r);
        IsoTypeWriter.b(allocate, this.c);
        IsoTypeWriter.d(allocate, this.d);
        IsoTypeWriter.d(allocate, this.e);
        IsoTypeWriter.d(allocate, this.f[0]);
        IsoTypeWriter.d(allocate, this.f[1]);
        IsoTypeWriter.d(allocate, this.f[2]);
        IsoTypeWriter.d(allocate, this.f[3]);
        this.g.b(allocate);
        this.h.b(allocate);
        writableByteChannel.write((ByteBuffer) allocate.rewind());
        a(writableByteChannel);
    }

    public BoxRecord e() {
        return this.g;
    }

    public void a(BoxRecord boxRecord) {
        this.g = boxRecord;
    }

    public StyleRecord f() {
        return this.h;
    }

    public void a(StyleRecord styleRecord) {
        this.h = styleRecord;
    }

    public boolean g() {
        return (this.c & 32) == 32;
    }

    public void a(boolean z) {
        if (z) {
            this.c |= 32;
        } else {
            this.c &= -33;
        }
    }

    public boolean h() {
        return (this.c & 64) == 64;
    }

    public void b(boolean z) {
        if (z) {
            this.c |= 64;
        } else {
            this.c &= -65;
        }
    }

    public boolean i() {
        return (this.c & 384) == 384;
    }

    public void c(boolean z) {
        if (z) {
            this.c |= 384;
        } else {
            this.c &= -385;
        }
    }

    public boolean j() {
        return (this.c & 2048) == 2048;
    }

    public void d(boolean z) {
        if (z) {
            this.c |= 2048;
        } else {
            this.c &= -2049;
        }
    }

    public boolean k() {
        return (this.c & 131072) == 131072;
    }

    public void e(boolean z) {
        if (z) {
            this.c |= 131072;
        } else {
            this.c &= -131073;
        }
    }

    public boolean l() {
        return (this.c & 262144) == 262144;
    }

    public void f(boolean z) {
        if (z) {
            this.c |= 262144;
        } else {
            this.c &= -262145;
        }
    }

    public int m() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }

    public int n() {
        return this.e;
    }

    public void c(int i) {
        this.e = i;
    }

    public int[] o() {
        return this.f;
    }

    public void a(int[] iArr) {
        this.f = iArr;
    }

    public long c() {
        long b2 = b() + 38;
        return b2 + ((long) ((this.t || b2 >= IjkMediaMeta.AV_CH_WIDE_RIGHT) ? 16 : 8));
    }

    public static class BoxRecord {

        /* renamed from: a  reason: collision with root package name */
        int f3953a;
        int b;
        int c;
        int d;

        public int a() {
            return 8;
        }

        public BoxRecord() {
        }

        public BoxRecord(int i, int i2, int i3, int i4) {
            this.f3953a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
        }

        public void a(ByteBuffer byteBuffer) {
            this.f3953a = IsoTypeReader.d(byteBuffer);
            this.b = IsoTypeReader.d(byteBuffer);
            this.c = IsoTypeReader.d(byteBuffer);
            this.d = IsoTypeReader.d(byteBuffer);
        }

        public void b(ByteBuffer byteBuffer) {
            IsoTypeWriter.b(byteBuffer, this.f3953a);
            IsoTypeWriter.b(byteBuffer, this.b);
            IsoTypeWriter.b(byteBuffer, this.c);
            IsoTypeWriter.b(byteBuffer, this.d);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            BoxRecord boxRecord = (BoxRecord) obj;
            return this.c == boxRecord.c && this.b == boxRecord.b && this.d == boxRecord.d && this.f3953a == boxRecord.f3953a;
        }

        public int hashCode() {
            return (((((this.f3953a * 31) + this.b) * 31) + this.c) * 31) + this.d;
        }
    }

    public static class StyleRecord {

        /* renamed from: a  reason: collision with root package name */
        int f3954a;
        int b;
        int c;
        int d;
        int e;
        int[] f = {255, 255, 255, 255};

        public int a() {
            return 12;
        }

        public StyleRecord() {
        }

        public StyleRecord(int i, int i2, int i3, int i4, int i5, int[] iArr) {
            this.f3954a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = i5;
            this.f = iArr;
        }

        public void a(ByteBuffer byteBuffer) {
            this.f3954a = IsoTypeReader.d(byteBuffer);
            this.b = IsoTypeReader.d(byteBuffer);
            this.c = IsoTypeReader.d(byteBuffer);
            this.d = IsoTypeReader.f(byteBuffer);
            this.e = IsoTypeReader.f(byteBuffer);
            this.f = new int[4];
            this.f[0] = IsoTypeReader.f(byteBuffer);
            this.f[1] = IsoTypeReader.f(byteBuffer);
            this.f[2] = IsoTypeReader.f(byteBuffer);
            this.f[3] = IsoTypeReader.f(byteBuffer);
        }

        public void b(ByteBuffer byteBuffer) {
            IsoTypeWriter.b(byteBuffer, this.f3954a);
            IsoTypeWriter.b(byteBuffer, this.b);
            IsoTypeWriter.b(byteBuffer, this.c);
            IsoTypeWriter.d(byteBuffer, this.d);
            IsoTypeWriter.d(byteBuffer, this.e);
            IsoTypeWriter.d(byteBuffer, this.f[0]);
            IsoTypeWriter.d(byteBuffer, this.f[1]);
            IsoTypeWriter.d(byteBuffer, this.f[2]);
            IsoTypeWriter.d(byteBuffer, this.f[3]);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            StyleRecord styleRecord = (StyleRecord) obj;
            return this.b == styleRecord.b && this.d == styleRecord.d && this.c == styleRecord.c && this.e == styleRecord.e && this.f3954a == styleRecord.f3954a && Arrays.equals(this.f, styleRecord.f);
        }

        public int hashCode() {
            return (((((((((this.f3954a * 31) + this.b) * 31) + this.c) * 31) + this.d) * 31) + this.e) * 31) + (this.f != null ? Arrays.hashCode(this.f) : 0);
        }
    }
}
