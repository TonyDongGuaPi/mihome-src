package org.mp4parser.boxes.iso14496.part30;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.Utf8;

public class WebVTTConfigurationBox extends AbstractBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3928a = "vttC";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    String b = "";

    static {
        e();
    }

    private static void e() {
        Factory factory = new Factory("WebVTTConfigurationBox.java", WebVTTConfigurationBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getConfig", "org.mp4parser.boxes.iso14496.part30.WebVTTConfigurationBox", "", "", "", "java.lang.String"), 36);
        d = factory.a("method-execution", (Signature) factory.a("1", "setConfig", "org.mp4parser.boxes.iso14496.part30.WebVTTConfigurationBox", "java.lang.String", "config", "", "void"), 40);
    }

    public WebVTTConfigurationBox() {
        super(f3928a);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) Utf8.b(this.b);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        byteBuffer.put(Utf8.a(this.b));
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        this.b = IsoTypeReader.a(byteBuffer, byteBuffer.remaining());
    }

    public String d() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return this.b;
    }

    public void a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this, (Object) str));
        this.b = str;
    }
}
