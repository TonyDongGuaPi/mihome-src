package org.mp4parser.boxes.threegpp.ts26244;

import com.alipay.sdk.widget.j;
import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import org.mp4parser.tools.Utf8;

public class TitleBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3977a = "titl";
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private String b;
    private String c;

    static {
        g();
    }

    private static void g() {
        Factory factory = new Factory("TitleBox.java", TitleBox.class);
        d = factory.a("method-execution", (Signature) factory.a("1", "getLanguage", "org.mp4parser.boxes.threegpp.ts26244.TitleBox", "", "", "", "java.lang.String"), 47);
        e = factory.a("method-execution", (Signature) factory.a("1", "setLanguage", "org.mp4parser.boxes.threegpp.ts26244.TitleBox", "java.lang.String", "language", "", "void"), 56);
        f = factory.a("method-execution", (Signature) factory.a("1", "getTitle", "org.mp4parser.boxes.threegpp.ts26244.TitleBox", "", "", "", "java.lang.String"), 60);
        g = factory.a("method-execution", (Signature) factory.a("1", j.d, "org.mp4parser.boxes.threegpp.ts26244.TitleBox", "java.lang.String", "title", "", "void"), 64);
        h = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.threegpp.ts26244.TitleBox", "", "", "", "java.lang.String"), 87);
    }

    public TitleBox() {
        super(f3977a);
    }

    public String e() {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this));
        return this.b;
    }

    public void a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this, (Object) str));
        this.b = str;
    }

    public String f() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.c;
    }

    public void b(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this, (Object) str));
        this.c = str;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) (Utf8.b(this.c) + 7);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.a(byteBuffer, this.b);
        byteBuffer.put(Utf8.a(this.c));
        byteBuffer.put((byte) 0);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.l(byteBuffer);
        this.c = IsoTypeReader.g(byteBuffer);
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        return "TitleBox[language=" + e() + ";title=" + f() + Operators.ARRAY_END_STR;
    }
}
