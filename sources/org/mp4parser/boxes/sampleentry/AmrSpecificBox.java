package org.mp4parser.boxes.sampleentry;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.IsoFile;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class AmrSpecificBox extends AbstractBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3948a = "damr";
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    private static final JoinPoint.StaticPart k = null;
    private static final JoinPoint.StaticPart l = null;
    private static final JoinPoint.StaticPart m = null;
    private String b;
    private int c;
    private int d;
    private int e;
    private int f;

    static {
        i();
    }

    private static void i() {
        Factory factory = new Factory("AmrSpecificBox.java", AmrSpecificBox.class);
        g = factory.a("method-execution", (Signature) factory.a("1", "getVendor", "org.mp4parser.boxes.sampleentry.AmrSpecificBox", "", "", "", "java.lang.String"), 46);
        h = factory.a("method-execution", (Signature) factory.a("1", "getDecoderVersion", "org.mp4parser.boxes.sampleentry.AmrSpecificBox", "", "", "", "int"), 50);
        i = factory.a("method-execution", (Signature) factory.a("1", "getModeSet", "org.mp4parser.boxes.sampleentry.AmrSpecificBox", "", "", "", "int"), 54);
        j = factory.a("method-execution", (Signature) factory.a("1", "getModeChangePeriod", "org.mp4parser.boxes.sampleentry.AmrSpecificBox", "", "", "", "int"), 58);
        k = factory.a("method-execution", (Signature) factory.a("1", "getFramesPerSample", "org.mp4parser.boxes.sampleentry.AmrSpecificBox", "", "", "", "int"), 62);
        l = factory.a("method-execution", (Signature) factory.a("1", "getContent", "org.mp4parser.boxes.sampleentry.AmrSpecificBox", "java.nio.ByteBuffer", "byteBuffer", "", "void"), 84);
        m = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.sampleentry.AmrSpecificBox", "", "", "", "java.lang.String"), 92);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 9;
    }

    public AmrSpecificBox() {
        super(f3948a);
    }

    public String d() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return this.b;
    }

    public int e() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        return this.c;
    }

    public int f() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.d;
    }

    public int g() {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this));
        return this.e;
    }

    public int h() {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this));
        return this.f;
    }

    public void a(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[4];
        byteBuffer.get(bArr);
        this.b = IsoFile.a(bArr);
        this.c = IsoTypeReader.f(byteBuffer);
        this.d = IsoTypeReader.d(byteBuffer);
        this.e = IsoTypeReader.f(byteBuffer);
        this.f = IsoTypeReader.f(byteBuffer);
    }

    public void b(ByteBuffer byteBuffer) {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this, (Object) byteBuffer));
        byteBuffer.put(IsoFile.a(this.b));
        IsoTypeWriter.d(byteBuffer, this.c);
        IsoTypeWriter.b(byteBuffer, this.d);
        IsoTypeWriter.d(byteBuffer, this.e);
        IsoTypeWriter.d(byteBuffer, this.f);
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this));
        return "AmrSpecificBox[vendor=" + d() + ";decoderVersion=" + e() + ";modeSet=" + f() + ";modeChangePeriod=" + g() + ";framesPerSample=" + h() + Operators.ARRAY_END_STR;
    }
}
