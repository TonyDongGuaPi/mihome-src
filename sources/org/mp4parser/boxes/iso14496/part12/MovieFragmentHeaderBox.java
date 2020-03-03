package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class MovieFragmentHeaderBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3867a = "mfhd";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    private long b;

    static {
        f();
    }

    private static void f() {
        Factory factory = new Factory("MovieFragmentHeaderBox.java", MovieFragmentHeaderBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getSequenceNumber", "org.mp4parser.boxes.iso14496.part12.MovieFragmentHeaderBox", "", "", "", "long"), 59);
        d = factory.a("method-execution", (Signature) factory.a("1", "setSequenceNumber", "org.mp4parser.boxes.iso14496.part12.MovieFragmentHeaderBox", "long", "sequenceNumber", "", "void"), 63);
        e = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.MovieFragmentHeaderBox", "", "", "", "java.lang.String"), 68);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 8;
    }

    public MovieFragmentHeaderBox() {
        super(f3867a);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.b(byteBuffer, this.b);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.b(byteBuffer);
    }

    public long e() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return this.b;
    }

    public void a(long j) {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this, Conversions.a(j)));
        this.b = j;
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return "MovieFragmentHeaderBox{sequenceNumber=" + this.b + Operators.BLOCK_END;
    }
}
