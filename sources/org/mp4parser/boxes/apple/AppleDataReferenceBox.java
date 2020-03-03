package org.mp4parser.boxes.apple;

import java.nio.ByteBuffer;
import org.mp4parser.IsoFile;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import org.mp4parser.tools.Utf8;

public class AppleDataReferenceBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3787a = "rdrf";
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private int b;
    private String c;
    private String d;

    static {
        h();
    }

    private static void h() {
        Factory factory = new Factory("AppleDataReferenceBox.java", AppleDataReferenceBox.class);
        e = factory.a("method-execution", (Signature) factory.a("1", "getDataReferenceSize", "org.mp4parser.boxes.apple.AppleDataReferenceBox", "", "", "", "long"), 62);
        f = factory.a("method-execution", (Signature) factory.a("1", "getDataReferenceType", "org.mp4parser.boxes.apple.AppleDataReferenceBox", "", "", "", "java.lang.String"), 66);
        g = factory.a("method-execution", (Signature) factory.a("1", "getDataReference", "org.mp4parser.boxes.apple.AppleDataReferenceBox", "", "", "", "java.lang.String"), 70);
    }

    public AppleDataReferenceBox() {
        super(f3787a);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) (this.b + 12);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.c = IsoTypeReader.m(byteBuffer);
        this.b = CastUtils.a(IsoTypeReader.b(byteBuffer));
        this.d = IsoTypeReader.a(byteBuffer, this.b);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        byteBuffer.put(IsoFile.a(this.c));
        IsoTypeWriter.b(byteBuffer, (long) this.b);
        byteBuffer.put(Utf8.a(this.d));
    }

    public long e() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return (long) this.b;
    }

    public String f() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.c;
    }

    public String g() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return this.d;
    }
}
