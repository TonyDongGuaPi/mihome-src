package org.mp4parser.boxes.iso14496.part12;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class DegradationPriorityBox extends AbstractFullBox {
    private static final JoinPoint.StaticPart b = null;
    private static final JoinPoint.StaticPart c = null;

    /* renamed from: a  reason: collision with root package name */
    int[] f3843a = new int[0];

    static {
        f();
    }

    private static void f() {
        Factory factory = new Factory("DegradationPriorityBox.java", DegradationPriorityBox.class);
        b = factory.a("method-execution", (Signature) factory.a("1", "getPriorities", "org.mp4parser.boxes.iso14496.part12.DegradationPriorityBox", "", "", "", "[I"), 38);
        c = factory.a("method-execution", (Signature) factory.a("1", "setPriorities", "org.mp4parser.boxes.iso14496.part12.DegradationPriorityBox", "[I", "priorities", "", "void"), 42);
    }

    public DegradationPriorityBox() {
        super("stdp");
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) ((this.f3843a.length * 2) + 4);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        for (int b2 : this.f3843a) {
            IsoTypeWriter.b(byteBuffer, b2);
        }
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.f3843a = new int[(byteBuffer.remaining() / 2)];
        for (int i = 0; i < this.f3843a.length; i++) {
            this.f3843a[i] = IsoTypeReader.d(byteBuffer);
        }
    }

    public int[] e() {
        RequiresParseDetailAspect.a().a(Factory.a(b, (Object) this, (Object) this));
        return this.f3843a;
    }

    public void a(int[] iArr) {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this, (Object) iArr));
        this.f3843a = iArr;
    }
}
