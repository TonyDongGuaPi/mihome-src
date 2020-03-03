package org.mp4parser.boxes.apple;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.DoNotParseDetail;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.Utf8;

public abstract class Utf8AppleDataBox extends AppleDataBox {
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart f = null;
    String e;

    static {
        k();
    }

    private static void k() {
        Factory factory = new Factory("Utf8AppleDataBox.java", Utf8AppleDataBox.class);
        d = factory.a("method-execution", (Signature) factory.a("1", "getValue", "org.mp4parser.boxes.apple.Utf8AppleDataBox", "", "", "", "java.lang.String"), 20);
        f = factory.a("method-execution", (Signature) factory.a("1", "setValue", "org.mp4parser.boxes.apple.Utf8AppleDataBox", "java.lang.String", "value", "", "void"), 28);
    }

    protected Utf8AppleDataBox(String str) {
        super(str, 1);
    }

    public String d() {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this));
        if (!x()) {
            w();
        }
        return this.e;
    }

    public void a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this, (Object) str));
        this.e = str;
    }

    @DoNotParseDetail
    public byte[] e() {
        return Utf8.a(this.e);
    }

    /* access modifiers changed from: protected */
    public int f() {
        return this.e.getBytes(Charset.forName("UTF-8")).length;
    }

    /* access modifiers changed from: protected */
    public void c(ByteBuffer byteBuffer) {
        this.e = IsoTypeReader.a(byteBuffer, byteBuffer.remaining());
    }
}
