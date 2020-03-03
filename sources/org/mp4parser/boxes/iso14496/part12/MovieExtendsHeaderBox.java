package org.mp4parser.boxes.iso14496.part12;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class MovieExtendsHeaderBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3865a = "mehd";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    private long b;

    static {
        f();
    }

    private static void f() {
        Factory factory = new Factory("MovieExtendsHeaderBox.java", MovieExtendsHeaderBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getFragmentDuration", "org.mp4parser.boxes.iso14496.part12.MovieExtendsHeaderBox", "", "", "", "long"), 65);
        d = factory.a("method-execution", (Signature) factory.a("1", "setFragmentDuration", "org.mp4parser.boxes.iso14496.part12.MovieExtendsHeaderBox", "long", "fragmentDuration", "", "void"), 69);
    }

    public MovieExtendsHeaderBox() {
        super(f3865a);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) (ag_() == 1 ? 12 : 8);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = ag_() == 1 ? IsoTypeReader.h(byteBuffer) : IsoTypeReader.b(byteBuffer);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        if (ag_() == 1) {
            IsoTypeWriter.a(byteBuffer, this.b);
        } else {
            IsoTypeWriter.b(byteBuffer, this.b);
        }
    }

    public long e() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return this.b;
    }

    public void a(long j) {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this, Conversions.a(j)));
        this.b = j;
    }
}
