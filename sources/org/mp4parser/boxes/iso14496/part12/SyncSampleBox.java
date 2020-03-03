package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class SyncSampleBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3897a = "stss";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    private long[] b;

    static {
        f();
    }

    private static void f() {
        Factory factory = new Factory("SyncSampleBox.java", SyncSampleBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getSampleNumber", "org.mp4parser.boxes.iso14496.part12.SyncSampleBox", "", "", "", "[J"), 45);
        d = factory.a("method-execution", (Signature) factory.a("1", "setSampleNumber", "org.mp4parser.boxes.iso14496.part12.SyncSampleBox", "[J", "sampleNumber", "", "void"), 49);
        e = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.SyncSampleBox", "", "", "", "java.lang.String"), 80);
    }

    public SyncSampleBox() {
        super(f3897a);
    }

    public long[] e() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return this.b;
    }

    public void a(long[] jArr) {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this, (Object) jArr));
        this.b = jArr;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) ((this.b.length * 4) + 8);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        int a2 = CastUtils.a(IsoTypeReader.b(byteBuffer));
        this.b = new long[a2];
        for (int i = 0; i < a2; i++) {
            this.b[i] = IsoTypeReader.b(byteBuffer);
        }
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.b(byteBuffer, (long) this.b.length);
        for (long b2 : this.b) {
            IsoTypeWriter.b(byteBuffer, b2);
        }
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return "SyncSampleBox[entryCount=" + this.b.length + Operators.ARRAY_END_STR;
    }
}
