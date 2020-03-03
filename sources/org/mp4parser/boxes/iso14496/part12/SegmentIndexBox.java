package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitReaderBuffer;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitWriterBuffer;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class SegmentIndexBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3888a = "sidx";
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    private static final JoinPoint.StaticPart k = null;
    private static final JoinPoint.StaticPart l = null;
    private static final JoinPoint.StaticPart m = null;
    private static final JoinPoint.StaticPart n = null;
    private static final JoinPoint.StaticPart o = null;
    private static final JoinPoint.StaticPart p = null;
    private static final JoinPoint.StaticPart q = null;
    private static final JoinPoint.StaticPart u = null;
    private static final JoinPoint.StaticPart v = null;
    private static final JoinPoint.StaticPart w = null;
    List<Entry> b = new ArrayList();
    long c;
    long d;
    long e;
    long f;
    int g;

    static {
        k();
    }

    private static void k() {
        Factory factory = new Factory("SegmentIndexBox.java", SegmentIndexBox.class);
        h = factory.a("method-execution", (Signature) factory.a("1", "getEntries", "org.mp4parser.boxes.iso14496.part12.SegmentIndexBox", "", "", "", "java.util.List"), 128);
        i = factory.a("method-execution", (Signature) factory.a("1", "setEntries", "org.mp4parser.boxes.iso14496.part12.SegmentIndexBox", "java.util.List", "entries", "", "void"), 132);
        u = factory.a("method-execution", (Signature) factory.a("1", "getReserved", "org.mp4parser.boxes.iso14496.part12.SegmentIndexBox", "", "", "", "int"), 168);
        v = factory.a("method-execution", (Signature) factory.a("1", "setReserved", "org.mp4parser.boxes.iso14496.part12.SegmentIndexBox", "int", "reserved", "", "void"), 172);
        w = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.SegmentIndexBox", "", "", "", "java.lang.String"), 177);
        j = factory.a("method-execution", (Signature) factory.a("1", "getReferenceId", "org.mp4parser.boxes.iso14496.part12.SegmentIndexBox", "", "", "", "long"), 136);
        k = factory.a("method-execution", (Signature) factory.a("1", "setReferenceId", "org.mp4parser.boxes.iso14496.part12.SegmentIndexBox", "long", "referenceId", "", "void"), 140);
        l = factory.a("method-execution", (Signature) factory.a("1", "getTimeScale", "org.mp4parser.boxes.iso14496.part12.SegmentIndexBox", "", "", "", "long"), 144);
        m = factory.a("method-execution", (Signature) factory.a("1", "setTimeScale", "org.mp4parser.boxes.iso14496.part12.SegmentIndexBox", "long", "timeScale", "", "void"), 148);
        n = factory.a("method-execution", (Signature) factory.a("1", "getEarliestPresentationTime", "org.mp4parser.boxes.iso14496.part12.SegmentIndexBox", "", "", "", "long"), 152);
        o = factory.a("method-execution", (Signature) factory.a("1", "setEarliestPresentationTime", "org.mp4parser.boxes.iso14496.part12.SegmentIndexBox", "long", "earliestPresentationTime", "", "void"), 156);
        p = factory.a("method-execution", (Signature) factory.a("1", "getFirstOffset", "org.mp4parser.boxes.iso14496.part12.SegmentIndexBox", "", "", "", "long"), 160);
        q = factory.a("method-execution", (Signature) factory.a("1", "setFirstOffset", "org.mp4parser.boxes.iso14496.part12.SegmentIndexBox", "long", "firstOffset", "", "void"), 164);
    }

    public SegmentIndexBox() {
        super(f3888a);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 12 + ((long) (ag_() == 0 ? 8 : 16)) + 2 + 2 + ((long) (this.b.size() * 12));
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.b(byteBuffer, this.c);
        IsoTypeWriter.b(byteBuffer, this.d);
        if (ag_() == 0) {
            IsoTypeWriter.b(byteBuffer, this.e);
            IsoTypeWriter.b(byteBuffer, this.f);
        } else {
            IsoTypeWriter.a(byteBuffer, this.e);
            IsoTypeWriter.a(byteBuffer, this.f);
        }
        IsoTypeWriter.b(byteBuffer, this.g);
        IsoTypeWriter.b(byteBuffer, this.b.size());
        for (Entry next : this.b) {
            BitWriterBuffer bitWriterBuffer = new BitWriterBuffer(byteBuffer);
            bitWriterBuffer.a(next.a(), 1);
            bitWriterBuffer.a(next.b(), 31);
            IsoTypeWriter.b(byteBuffer, next.c());
            BitWriterBuffer bitWriterBuffer2 = new BitWriterBuffer(byteBuffer);
            bitWriterBuffer2.a(next.d(), 1);
            bitWriterBuffer2.a(next.e(), 3);
            bitWriterBuffer2.a(next.f(), 28);
        }
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.c = IsoTypeReader.b(byteBuffer);
        this.d = IsoTypeReader.b(byteBuffer);
        if (ag_() == 0) {
            this.e = IsoTypeReader.b(byteBuffer);
            this.f = IsoTypeReader.b(byteBuffer);
        } else {
            this.e = IsoTypeReader.h(byteBuffer);
            this.f = IsoTypeReader.h(byteBuffer);
        }
        this.g = IsoTypeReader.d(byteBuffer);
        int d2 = IsoTypeReader.d(byteBuffer);
        for (int i2 = 0; i2 < d2; i2++) {
            BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(byteBuffer);
            Entry entry = new Entry();
            entry.a((byte) bitReaderBuffer.a(1));
            entry.a(bitReaderBuffer.a(31));
            entry.a(IsoTypeReader.b(byteBuffer));
            BitReaderBuffer bitReaderBuffer2 = new BitReaderBuffer(byteBuffer);
            entry.b((byte) bitReaderBuffer2.a(1));
            entry.c((byte) bitReaderBuffer2.a(3));
            entry.b(bitReaderBuffer2.a(28));
            this.b.add(entry);
        }
    }

    public List<Entry> e() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        return this.b;
    }

    public void a(List<Entry> list) {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this, (Object) list));
        this.b = list;
    }

    public long f() {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this));
        return this.c;
    }

    public void a(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this, Conversions.a(j2)));
        this.c = j2;
    }

    public long g() {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this));
        return this.d;
    }

    public void b(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this, Conversions.a(j2)));
        this.d = j2;
    }

    public long h() {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this));
        return this.e;
    }

    public void c(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this, Conversions.a(j2)));
        this.e = j2;
    }

    public long i() {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this));
        return this.f;
    }

    public void d(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this, Conversions.a(j2)));
        this.f = j2;
    }

    public int j() {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this));
        return this.g;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(v, (Object) this, (Object) this, Conversions.a(i2)));
        this.g = i2;
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(w, (Object) this, (Object) this));
        return "SegmentIndexBox{entries=" + this.b + ", referenceId=" + this.c + ", timeScale=" + this.d + ", earliestPresentationTime=" + this.e + ", firstOffset=" + this.f + ", reserved=" + this.g + Operators.BLOCK_END;
    }

    public static class Entry {

        /* renamed from: a  reason: collision with root package name */
        byte f3889a;
        int b;
        long c;
        byte d;
        byte e;
        int f;

        public Entry() {
        }

        public Entry(int i, int i2, long j, boolean z, int i3, int i4) {
            this.f3889a = (byte) i;
            this.b = i2;
            this.c = j;
            this.d = z ? (byte) 1 : 0;
            this.e = (byte) i3;
            this.f = i4;
        }

        public byte a() {
            return this.f3889a;
        }

        public void a(byte b2) {
            this.f3889a = b2;
        }

        public int b() {
            return this.b;
        }

        public void a(int i) {
            this.b = i;
        }

        public long c() {
            return this.c;
        }

        public void a(long j) {
            this.c = j;
        }

        public byte d() {
            return this.d;
        }

        public void b(byte b2) {
            this.d = b2;
        }

        public byte e() {
            return this.e;
        }

        public void c(byte b2) {
            this.e = b2;
        }

        public int f() {
            return this.f;
        }

        public void b(int i) {
            this.f = i;
        }

        public String toString() {
            return "Entry{referenceType=" + this.f3889a + ", referencedSize=" + this.b + ", subsegmentDuration=" + this.c + ", startsWithSap=" + this.d + ", sapType=" + this.e + ", sapDeltaTime=" + this.f + Operators.BLOCK_END;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Entry entry = (Entry) obj;
            return this.f3889a == entry.f3889a && this.b == entry.b && this.f == entry.f && this.e == entry.e && this.d == entry.d && this.c == entry.c;
        }

        public int hashCode() {
            return (((((((((this.f3889a * 31) + this.b) * 31) + ((int) (this.c ^ (this.c >>> 32)))) * 31) + this.d) * 31) + this.e) * 31) + this.f;
        }
    }
}
