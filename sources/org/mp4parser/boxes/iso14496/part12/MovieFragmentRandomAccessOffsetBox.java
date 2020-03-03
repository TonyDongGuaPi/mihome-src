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

public class MovieFragmentRandomAccessOffsetBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3869a = "mfro";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    private long b;

    static {
        f();
    }

    private static void f() {
        Factory factory = new Factory("MovieFragmentRandomAccessOffsetBox.java", MovieFragmentRandomAccessOffsetBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getMfraSize", "org.mp4parser.boxes.iso14496.part12.MovieFragmentRandomAccessOffsetBox", "", "", "", "long"), 56);
        d = factory.a("method-execution", (Signature) factory.a("1", "setMfraSize", "org.mp4parser.boxes.iso14496.part12.MovieFragmentRandomAccessOffsetBox", "long", "mfraSize", "", "void"), 60);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 8;
    }

    public MovieFragmentRandomAccessOffsetBox() {
        super(f3869a);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.b(byteBuffer);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.b(byteBuffer, this.b);
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
