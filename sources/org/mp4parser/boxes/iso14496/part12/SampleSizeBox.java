package org.mp4parser.boxes.iso14496.part12;

import com.facebook.imagepipeline.producers.DecodeProducer;
import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class SampleSizeBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3882a = "stsz";
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    private static final JoinPoint.StaticPart k = null;
    int b;
    private long c;
    private long[] d = new long[0];

    static {
        h();
    }

    private static void h() {
        Factory factory = new Factory("SampleSizeBox.java", SampleSizeBox.class);
        e = factory.a("method-execution", (Signature) factory.a("1", "getSampleSize", "org.mp4parser.boxes.iso14496.part12.SampleSizeBox", "", "", "", "long"), 49);
        f = factory.a("method-execution", (Signature) factory.a("1", "setSampleSize", "org.mp4parser.boxes.iso14496.part12.SampleSizeBox", "long", DecodeProducer.SAMPLE_SIZE, "", "void"), 53);
        g = factory.a("method-execution", (Signature) factory.a("1", "getSampleSizeAtIndex", "org.mp4parser.boxes.iso14496.part12.SampleSizeBox", "int", "index", "", "long"), 58);
        h = factory.a("method-execution", (Signature) factory.a("1", "getSampleCount", "org.mp4parser.boxes.iso14496.part12.SampleSizeBox", "", "", "", "long"), 66);
        i = factory.a("method-execution", (Signature) factory.a("1", "getSampleSizes", "org.mp4parser.boxes.iso14496.part12.SampleSizeBox", "", "", "", "[J"), 75);
        j = factory.a("method-execution", (Signature) factory.a("1", "setSampleSizes", "org.mp4parser.boxes.iso14496.part12.SampleSizeBox", "[J", "sampleSizes", "", "void"), 79);
        k = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.SampleSizeBox", "", "", "", "java.lang.String"), 118);
    }

    public SampleSizeBox() {
        super(f3882a);
    }

    public long e() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return this.c;
    }

    public void a(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this, Conversions.a(j2)));
        this.c = j2;
    }

    public long c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this, Conversions.a(i2)));
        if (this.c > 0) {
            return this.c;
        }
        return this.d[i2];
    }

    public long f() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        if (this.c > 0) {
            return (long) this.b;
        }
        return (long) this.d.length;
    }

    public long[] g() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.d;
    }

    public void a(long[] jArr) {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this, (Object) jArr));
        this.d = jArr;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) ((this.c == 0 ? this.d.length * 4 : 0) + 12);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.c = IsoTypeReader.b(byteBuffer);
        this.b = CastUtils.a(IsoTypeReader.b(byteBuffer));
        if (this.c == 0) {
            this.d = new long[this.b];
            for (int i2 = 0; i2 < this.b; i2++) {
                this.d[i2] = IsoTypeReader.b(byteBuffer);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.b(byteBuffer, this.c);
        if (this.c == 0) {
            IsoTypeWriter.b(byteBuffer, (long) this.d.length);
            for (long b2 : this.d) {
                IsoTypeWriter.b(byteBuffer, b2);
            }
            return;
        }
        IsoTypeWriter.b(byteBuffer, (long) this.b);
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this));
        return "SampleSizeBox[sampleSize=" + e() + ";sampleCount=" + f() + Operators.ARRAY_END_STR;
    }
}
