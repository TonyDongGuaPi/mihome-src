package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.Utf8;

public class XmlBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3914a = "xml ";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    String b = "";

    static {
        f();
    }

    private static void f() {
        Factory factory = new Factory("XmlBox.java", XmlBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getXml", "org.mp4parser.boxes.iso14496.part12.XmlBox", "", "", "", "java.lang.String"), 20);
        d = factory.a("method-execution", (Signature) factory.a("1", "setXml", "org.mp4parser.boxes.iso14496.part12.XmlBox", "java.lang.String", "xml", "", "void"), 24);
        e = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.XmlBox", "", "", "", "java.lang.String"), 46);
    }

    public XmlBox() {
        super(f3914a);
    }

    public String e() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return this.b;
    }

    public void a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this, (Object) str));
        this.b = str;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) (Utf8.b(this.b) + 4);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.a(byteBuffer, byteBuffer.remaining());
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        byteBuffer.put(Utf8.a(this.b));
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return "XmlBox{xml='" + this.b + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
