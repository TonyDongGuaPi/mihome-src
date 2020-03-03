package org.mp4parser.boxes.apple;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class AppleDataRateBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3786a = "rmdr";
    private static final JoinPoint.StaticPart c = null;
    private long b;

    static {
        f();
    }

    private static void f() {
        Factory factory = new Factory("AppleDataRateBox.java", AppleDataRateBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getDataRate", "org.mp4parser.boxes.apple.AppleDataRateBox", "", "", "", "long"), 53);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 8;
    }

    public AppleDataRateBox() {
        super(f3786a);
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
}
