package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeReaderVariable;
import org.mp4parser.tools.IsoTypeWriter;
import org.mp4parser.tools.IsoTypeWriterVariable;

public class TrackFragmentRandomAccessBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3905a = "tfra";
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
    private long b;
    private int c;
    private int d = 2;
    private int e = 2;
    private int f = 2;
    private List<Entry> g = Collections.emptyList();

    static {
        l();
    }

    private static void l() {
        Factory factory = new Factory("TrackFragmentRandomAccessBox.java", TrackFragmentRandomAccessBox.class);
        h = factory.a("method-execution", (Signature) factory.a("1", "getTrackId", "org.mp4parser.boxes.iso14496.part12.TrackFragmentRandomAccessBox", "", "", "", "long"), 125);
        i = factory.a("method-execution", (Signature) factory.a("1", "setTrackId", "org.mp4parser.boxes.iso14496.part12.TrackFragmentRandomAccessBox", "long", "trackId", "", "void"), 129);
        u = factory.a("method-execution", (Signature) factory.a("1", "getEntries", "org.mp4parser.boxes.iso14496.part12.TrackFragmentRandomAccessBox", "", "", "", "java.util.List"), 165);
        v = factory.a("method-execution", (Signature) factory.a("1", "setEntries", "org.mp4parser.boxes.iso14496.part12.TrackFragmentRandomAccessBox", "java.util.List", "entries", "", "void"), 169);
        w = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.TrackFragmentRandomAccessBox", "", "", "", "java.lang.String"), 174);
        j = factory.a("method-execution", (Signature) factory.a("1", "getReserved", "org.mp4parser.boxes.iso14496.part12.TrackFragmentRandomAccessBox", "", "", "", "int"), 133);
        k = factory.a("method-execution", (Signature) factory.a("1", "getLengthSizeOfTrafNum", "org.mp4parser.boxes.iso14496.part12.TrackFragmentRandomAccessBox", "", "", "", "int"), 137);
        l = factory.a("method-execution", (Signature) factory.a("1", "setLengthSizeOfTrafNum", "org.mp4parser.boxes.iso14496.part12.TrackFragmentRandomAccessBox", "int", "lengthSizeOfTrafNum", "", "void"), 141);
        m = factory.a("method-execution", (Signature) factory.a("1", "getLengthSizeOfTrunNum", "org.mp4parser.boxes.iso14496.part12.TrackFragmentRandomAccessBox", "", "", "", "int"), 145);
        n = factory.a("method-execution", (Signature) factory.a("1", "setLengthSizeOfTrunNum", "org.mp4parser.boxes.iso14496.part12.TrackFragmentRandomAccessBox", "int", "lengthSizeOfTrunNum", "", "void"), 149);
        o = factory.a("method-execution", (Signature) factory.a("1", "getLengthSizeOfSampleNum", "org.mp4parser.boxes.iso14496.part12.TrackFragmentRandomAccessBox", "", "", "", "int"), 153);
        p = factory.a("method-execution", (Signature) factory.a("1", "setLengthSizeOfSampleNum", "org.mp4parser.boxes.iso14496.part12.TrackFragmentRandomAccessBox", "int", "lengthSizeOfSampleNum", "", "void"), 157);
        q = factory.a("method-execution", (Signature) factory.a("1", "getNumberOfEntries", "org.mp4parser.boxes.iso14496.part12.TrackFragmentRandomAccessBox", "", "", "", "long"), 161);
    }

    public TrackFragmentRandomAccessBox() {
        super(f3905a);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        long j2;
        if (ag_() == 1) {
            j2 = 16 + ((long) (this.g.size() * 16));
        } else {
            j2 = 16 + ((long) (this.g.size() * 8));
        }
        return j2 + ((long) (this.d * this.g.size())) + ((long) (this.e * this.g.size())) + ((long) (this.f * this.g.size()));
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.b(byteBuffer);
        long b2 = IsoTypeReader.b(byteBuffer);
        this.c = (int) (b2 >> 6);
        this.d = (((int) (63 & b2)) >> 4) + 1;
        this.e = (((int) (12 & b2)) >> 2) + 1;
        this.f = ((int) (b2 & 3)) + 1;
        long b3 = IsoTypeReader.b(byteBuffer);
        this.g = new ArrayList();
        for (int i2 = 0; ((long) i2) < b3; i2++) {
            Entry entry = new Entry();
            if (ag_() == 1) {
                entry.f3906a = IsoTypeReader.h(byteBuffer);
                entry.b = IsoTypeReader.h(byteBuffer);
            } else {
                entry.f3906a = IsoTypeReader.b(byteBuffer);
                entry.b = IsoTypeReader.b(byteBuffer);
            }
            entry.c = IsoTypeReaderVariable.a(byteBuffer, this.d);
            entry.d = IsoTypeReaderVariable.a(byteBuffer, this.e);
            entry.e = IsoTypeReaderVariable.a(byteBuffer, this.f);
            this.g.add(entry);
        }
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.b(byteBuffer, this.b);
        IsoTypeWriter.b(byteBuffer, ((long) (this.c << 6)) | ((long) (((this.d - 1) & 3) << 4)) | ((long) (((this.e - 1) & 3) << 2)) | ((long) ((this.f - 1) & 3)));
        IsoTypeWriter.b(byteBuffer, (long) this.g.size());
        for (Entry next : this.g) {
            if (ag_() == 1) {
                IsoTypeWriter.a(byteBuffer, next.f3906a);
                IsoTypeWriter.a(byteBuffer, next.b);
            } else {
                IsoTypeWriter.b(byteBuffer, next.f3906a);
                IsoTypeWriter.b(byteBuffer, next.b);
            }
            IsoTypeWriterVariable.a(next.c, byteBuffer, this.d);
            IsoTypeWriterVariable.a(next.d, byteBuffer, this.e);
            IsoTypeWriterVariable.a(next.e, byteBuffer, this.f);
        }
    }

    public long e() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        return this.b;
    }

    public void a(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this, Conversions.a(j2)));
        this.b = j2;
    }

    public int f() {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this));
        return this.c;
    }

    public int g() {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this));
        return this.d;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this, Conversions.a(i2)));
        this.d = i2;
    }

    public int h() {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this));
        return this.e;
    }

    public void d(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this, Conversions.a(i2)));
        this.e = i2;
    }

    public int i() {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this));
        return this.f;
    }

    public void e(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this, Conversions.a(i2)));
        this.f = i2;
    }

    public long j() {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this));
        return (long) this.g.size();
    }

    public List<Entry> k() {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this));
        return Collections.unmodifiableList(this.g);
    }

    public void a(List<Entry> list) {
        RequiresParseDetailAspect.a().a(Factory.a(v, (Object) this, (Object) this, (Object) list));
        this.g = list;
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(w, (Object) this, (Object) this));
        return "TrackFragmentRandomAccessBox{trackId=" + this.b + ", entries=" + this.g + Operators.BLOCK_END;
    }

    public static class Entry {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public long f3906a;
        /* access modifiers changed from: private */
        public long b;
        /* access modifiers changed from: private */
        public long c;
        /* access modifiers changed from: private */
        public long d;
        /* access modifiers changed from: private */
        public long e;

        public Entry() {
        }

        public Entry(long j, long j2, long j3, long j4, long j5) {
            this.b = j2;
            this.e = j5;
            this.f3906a = j;
            this.c = j3;
            this.d = j4;
        }

        public long a() {
            return this.f3906a;
        }

        public void a(long j) {
            this.f3906a = j;
        }

        public long b() {
            return this.b;
        }

        public void b(long j) {
            this.b = j;
        }

        public long c() {
            return this.c;
        }

        public void c(long j) {
            this.c = j;
        }

        public long d() {
            return this.d;
        }

        public void d(long j) {
            this.d = j;
        }

        public long e() {
            return this.e;
        }

        public void e(long j) {
            this.e = j;
        }

        public String toString() {
            return "Entry{time=" + this.f3906a + ", moofOffset=" + this.b + ", trafNumber=" + this.c + ", trunNumber=" + this.d + ", sampleNumber=" + this.e + Operators.BLOCK_END;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Entry entry = (Entry) obj;
            return this.b == entry.b && this.e == entry.e && this.f3906a == entry.f3906a && this.c == entry.c && this.d == entry.d;
        }

        public int hashCode() {
            return (((((((((int) (this.f3906a ^ (this.f3906a >>> 32))) * 31) + ((int) (this.b ^ (this.b >>> 32)))) * 31) + ((int) (this.c ^ (this.c >>> 32)))) * 31) + ((int) (this.d ^ (this.d >>> 32)))) * 31) + ((int) (this.e ^ (this.e >>> 32)));
        }
    }
}
