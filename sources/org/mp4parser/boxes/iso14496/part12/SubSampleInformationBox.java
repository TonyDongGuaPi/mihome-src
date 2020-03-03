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
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class SubSampleInformationBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3893a = "subs";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    private List<SubSampleEntry> b = new ArrayList();

    static {
        f();
    }

    private static void f() {
        Factory factory = new Factory("SubSampleInformationBox.java", SubSampleInformationBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getEntries", "org.mp4parser.boxes.iso14496.part12.SubSampleInformationBox", "", "", "", "java.util.List"), 49);
        d = factory.a("method-execution", (Signature) factory.a("1", "setEntries", "org.mp4parser.boxes.iso14496.part12.SubSampleInformationBox", "java.util.List", "entries", "", "void"), 53);
        e = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.SubSampleInformationBox", "", "", "", "java.lang.String"), 123);
    }

    public SubSampleInformationBox() {
        super(f3893a);
    }

    public List<SubSampleEntry> e() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return this.b;
    }

    public void a(List<SubSampleEntry> list) {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this, (Object) list));
        this.b = list;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        long j = 8;
        for (SubSampleEntry next : this.b) {
            j = j + 4 + 2;
            for (int i = 0; i < next.c().size(); i++) {
                j = (ag_() == 1 ? j + 4 : j + 2) + 2 + 4;
            }
        }
        return j;
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        long b2 = IsoTypeReader.b(byteBuffer);
        for (int i = 0; ((long) i) < b2; i++) {
            SubSampleEntry subSampleEntry = new SubSampleEntry();
            subSampleEntry.a(IsoTypeReader.b(byteBuffer));
            int d2 = IsoTypeReader.d(byteBuffer);
            for (int i2 = 0; i2 < d2; i2++) {
                SubSampleEntry.SubsampleEntry subsampleEntry = new SubSampleEntry.SubsampleEntry();
                subsampleEntry.a(ag_() == 1 ? IsoTypeReader.b(byteBuffer) : (long) IsoTypeReader.d(byteBuffer));
                subsampleEntry.a(IsoTypeReader.f(byteBuffer));
                subsampleEntry.b(IsoTypeReader.f(byteBuffer));
                subsampleEntry.b(IsoTypeReader.b(byteBuffer));
                subSampleEntry.c().add(subsampleEntry);
            }
            this.b.add(subSampleEntry);
        }
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.b(byteBuffer, (long) this.b.size());
        for (SubSampleEntry next : this.b) {
            IsoTypeWriter.b(byteBuffer, next.a());
            IsoTypeWriter.b(byteBuffer, next.b());
            for (SubSampleEntry.SubsampleEntry next2 : next.c()) {
                if (ag_() == 1) {
                    IsoTypeWriter.b(byteBuffer, next2.a());
                } else {
                    IsoTypeWriter.b(byteBuffer, CastUtils.a(next2.a()));
                }
                IsoTypeWriter.d(byteBuffer, next2.b());
                IsoTypeWriter.d(byteBuffer, next2.c());
                IsoTypeWriter.b(byteBuffer, next2.d());
            }
        }
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return "SubSampleInformationBox{entryCount=" + this.b.size() + ", entries=" + this.b + Operators.BLOCK_END;
    }

    public static class SubSampleEntry {

        /* renamed from: a  reason: collision with root package name */
        private long f3894a;
        private List<SubsampleEntry> b = new ArrayList();

        public long a() {
            return this.f3894a;
        }

        public void a(long j) {
            this.f3894a = j;
        }

        public int b() {
            return this.b.size();
        }

        public List<SubsampleEntry> c() {
            return this.b;
        }

        public String toString() {
            return "SampleEntry{sampleDelta=" + this.f3894a + ", subsampleCount=" + this.b.size() + ", subsampleEntries=" + this.b + Operators.BLOCK_END;
        }

        public static class SubsampleEntry {

            /* renamed from: a  reason: collision with root package name */
            private long f3895a;
            private int b;
            private int c;
            private long d;

            public long a() {
                return this.f3895a;
            }

            public void a(long j) {
                this.f3895a = j;
            }

            public int b() {
                return this.b;
            }

            public void a(int i) {
                this.b = i;
            }

            public int c() {
                return this.c;
            }

            public void b(int i) {
                this.c = i;
            }

            public long d() {
                return this.d;
            }

            public void b(long j) {
                this.d = j;
            }

            public String toString() {
                return "SubsampleEntry{subsampleSize=" + this.f3895a + ", subsamplePriority=" + this.b + ", discardable=" + this.c + ", reserved=" + this.d + Operators.BLOCK_END;
            }
        }
    }
}
