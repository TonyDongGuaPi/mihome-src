package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class SampleDependencyTypeBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3878a = "sdtp";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    private List<Entry> b = new ArrayList();

    static {
        f();
    }

    private static void f() {
        Factory factory = new Factory("SampleDependencyTypeBox.java", SampleDependencyTypeBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getEntries", "org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox", "", "", "", "java.util.List"), 70);
        d = factory.a("method-execution", (Signature) factory.a("1", "setEntries", "org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox", "java.util.List", "entries", "", "void"), 74);
        e = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox", "", "", "", "java.lang.String"), 79);
    }

    public SampleDependencyTypeBox() {
        super(f3878a);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) (this.b.size() + 4);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        for (Entry a2 : this.b) {
            IsoTypeWriter.d(byteBuffer, a2.f3879a);
        }
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        while (byteBuffer.remaining() > 0) {
            this.b.add(new Entry(IsoTypeReader.f(byteBuffer)));
        }
    }

    public List<Entry> e() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return this.b;
    }

    public void a(List<Entry> list) {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this, (Object) list));
        this.b = list;
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return "SampleDependencyTypeBox" + "{entries=" + this.b + Operators.BLOCK_END;
    }

    public static class Entry {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public int f3879a;

        public Entry(int i) {
            this.f3879a = i;
        }

        public byte a() {
            return (byte) ((this.f3879a >> 6) & 3);
        }

        public void a(int i) {
            this.f3879a = ((i & 3) << 6) | (this.f3879a & 63);
        }

        public byte b() {
            return (byte) ((this.f3879a >> 4) & 3);
        }

        public void b(int i) {
            this.f3879a = ((i & 3) << 4) | (this.f3879a & 207);
        }

        public byte c() {
            return (byte) ((this.f3879a >> 2) & 3);
        }

        public void c(int i) {
            this.f3879a = ((i & 3) << 2) | (this.f3879a & 243);
        }

        public byte d() {
            return (byte) (this.f3879a & 3);
        }

        public void d(int i) {
            this.f3879a = (i & 3) | (this.f3879a & 252);
        }

        public String toString() {
            return "Entry{isLeading=" + a() + ", sampleDependsOn=" + b() + ", sampleIsDependentOn=" + c() + ", sampleHasRedundancy=" + d() + Operators.BLOCK_END;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.f3879a == ((Entry) obj).f3879a;
        }

        public int hashCode() {
            return this.f3879a;
        }
    }
}
