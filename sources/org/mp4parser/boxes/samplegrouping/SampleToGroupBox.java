package org.mp4parser.boxes.samplegrouping;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class SampleToGroupBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3961a = "sbgp";
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    List<Entry> b = new LinkedList();
    private String c;
    private String d;

    static {
        h();
    }

    private static void h() {
        Factory factory = new Factory("SampleToGroupBox.java", SampleToGroupBox.class);
        e = factory.a("method-execution", (Signature) factory.a("1", "getGroupingType", "org.mp4parser.boxes.samplegrouping.SampleToGroupBox", "", "", "", "java.lang.String"), 84);
        f = factory.a("method-execution", (Signature) factory.a("1", "setGroupingType", "org.mp4parser.boxes.samplegrouping.SampleToGroupBox", "java.lang.String", "groupingType", "", "void"), 88);
        g = factory.a("method-execution", (Signature) factory.a("1", "getGroupingTypeParameter", "org.mp4parser.boxes.samplegrouping.SampleToGroupBox", "", "", "", "java.lang.String"), 92);
        h = factory.a("method-execution", (Signature) factory.a("1", "setGroupingTypeParameter", "org.mp4parser.boxes.samplegrouping.SampleToGroupBox", "java.lang.String", "groupingTypeParameter", "", "void"), 96);
        i = factory.a("method-execution", (Signature) factory.a("1", "getEntries", "org.mp4parser.boxes.samplegrouping.SampleToGroupBox", "", "", "", "java.util.List"), 100);
        j = factory.a("method-execution", (Signature) factory.a("1", "setEntries", "org.mp4parser.boxes.samplegrouping.SampleToGroupBox", "java.util.List", "entries", "", "void"), 104);
    }

    public SampleToGroupBox() {
        super(f3961a);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) (ag_() == 1 ? (this.b.size() * 8) + 16 : (this.b.size() * 8) + 12);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        byteBuffer.put(this.c.getBytes());
        if (ag_() == 1) {
            byteBuffer.put(this.d.getBytes());
        }
        IsoTypeWriter.b(byteBuffer, (long) this.b.size());
        for (Entry next : this.b) {
            IsoTypeWriter.b(byteBuffer, next.a());
            IsoTypeWriter.b(byteBuffer, (long) next.b());
        }
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.c = IsoTypeReader.m(byteBuffer);
        if (ag_() == 1) {
            this.d = IsoTypeReader.m(byteBuffer);
        }
        long b2 = IsoTypeReader.b(byteBuffer);
        while (true) {
            long j2 = b2 - 1;
            if (b2 > 0) {
                this.b.add(new Entry((long) CastUtils.a(IsoTypeReader.b(byteBuffer)), CastUtils.a(IsoTypeReader.b(byteBuffer))));
                b2 = j2;
            } else {
                return;
            }
        }
    }

    public String e() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return this.c;
    }

    public void a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this, (Object) str));
        this.c = str;
    }

    public String f() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return this.d;
    }

    public void b(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this, (Object) str));
        this.d = str;
    }

    public List<Entry> g() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.b;
    }

    public void a(List<Entry> list) {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this, (Object) list));
        this.b = list;
    }

    public static class Entry {

        /* renamed from: a  reason: collision with root package name */
        private long f3962a;
        private int b;

        public Entry(long j, int i) {
            this.f3962a = j;
            this.b = i;
        }

        public long a() {
            return this.f3962a;
        }

        public void a(long j) {
            this.f3962a = j;
        }

        public int b() {
            return this.b;
        }

        public void a(int i) {
            this.b = i;
        }

        public String toString() {
            return "Entry{sampleCount=" + this.f3962a + ", groupDescriptionIndex=" + this.b + Operators.BLOCK_END;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Entry entry = (Entry) obj;
            return this.b == entry.b && this.f3962a == entry.f3962a;
        }

        public int hashCode() {
            return (((int) (this.f3962a ^ (this.f3962a >>> 32))) * 31) + this.b;
        }
    }
}
