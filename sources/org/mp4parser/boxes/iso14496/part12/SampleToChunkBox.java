package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class SampleToChunkBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3884a = "stsc";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    List<Entry> b = Collections.emptyList();

    static {
        f();
    }

    private static void f() {
        Factory factory = new Factory("SampleToChunkBox.java", SampleToChunkBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getEntries", "org.mp4parser.boxes.iso14496.part12.SampleToChunkBox", "", "", "", "java.util.List"), 41);
        d = factory.a("method-execution", (Signature) factory.a("1", "setEntries", "org.mp4parser.boxes.iso14496.part12.SampleToChunkBox", "java.util.List", "entries", "", "void"), 45);
        e = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.SampleToChunkBox", "", "", "", "java.lang.String"), 78);
        f = factory.a("method-execution", (Signature) factory.a("1", "blowup", "org.mp4parser.boxes.iso14496.part12.SampleToChunkBox", "int", "chunkCount", "", "[J"), 89);
    }

    public SampleToChunkBox() {
        super(f3884a);
    }

    public List<Entry> e() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return this.b;
    }

    public void a(List<Entry> list) {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this, (Object) list));
        this.b = list;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) ((this.b.size() * 12) + 8);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        int a2 = CastUtils.a(IsoTypeReader.b(byteBuffer));
        this.b = new ArrayList(a2);
        for (int i = 0; i < a2; i++) {
            this.b.add(new Entry(IsoTypeReader.b(byteBuffer), IsoTypeReader.b(byteBuffer), IsoTypeReader.b(byteBuffer)));
        }
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.b(byteBuffer, (long) this.b.size());
        for (Entry next : this.b) {
            IsoTypeWriter.b(byteBuffer, next.a());
            IsoTypeWriter.b(byteBuffer, next.b());
            IsoTypeWriter.b(byteBuffer, next.c());
        }
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return "SampleToChunkBox[entryCount=" + this.b.size() + Operators.ARRAY_END_STR;
    }

    public long[] c(int i) {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this, Conversions.a(i)));
        long[] jArr = new long[i];
        LinkedList linkedList = new LinkedList(this.b);
        Collections.reverse(linkedList);
        Iterator it = linkedList.iterator();
        Entry entry = (Entry) it.next();
        for (int length = jArr.length; length > 1; length--) {
            jArr[length - 1] = entry.b();
            if (((long) length) == entry.a()) {
                entry = (Entry) it.next();
            }
        }
        jArr[0] = entry.b();
        return jArr;
    }

    public static class Entry {

        /* renamed from: a  reason: collision with root package name */
        long f3885a;
        long b;
        long c;

        public Entry(long j, long j2, long j3) {
            this.f3885a = j;
            this.b = j2;
            this.c = j3;
        }

        public long a() {
            return this.f3885a;
        }

        public void a(long j) {
            this.f3885a = j;
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

        public String toString() {
            return "Entry{firstChunk=" + this.f3885a + ", samplesPerChunk=" + this.b + ", sampleDescriptionIndex=" + this.c + Operators.BLOCK_END;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Entry entry = (Entry) obj;
            return this.f3885a == entry.f3885a && this.c == entry.c && this.b == entry.b;
        }

        public int hashCode() {
            return (((((int) (this.f3885a ^ (this.f3885a >>> 32))) * 31) + ((int) (this.b ^ (this.b >>> 32)))) * 31) + ((int) (this.c ^ (this.c >>> 32)));
        }
    }
}
