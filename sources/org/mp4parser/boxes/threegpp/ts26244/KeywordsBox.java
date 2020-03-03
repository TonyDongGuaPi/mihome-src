package org.mp4parser.boxes.threegpp.ts26244;

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

public class KeywordsBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3972a = "kywd";
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private String b;
    private String[] c;

    static {
        g();
    }

    private static void g() {
        Factory factory = new Factory("KeywordsBox.java", KeywordsBox.class);
        d = factory.a("method-execution", (Signature) factory.a("1", "getLanguage", "org.mp4parser.boxes.threegpp.ts26244.KeywordsBox", "", "", "", "java.lang.String"), 40);
        e = factory.a("method-execution", (Signature) factory.a("1", "setLanguage", "org.mp4parser.boxes.threegpp.ts26244.KeywordsBox", "java.lang.String", "language", "", "void"), 44);
        f = factory.a("method-execution", (Signature) factory.a("1", "getKeywords", "org.mp4parser.boxes.threegpp.ts26244.KeywordsBox", "", "", "", "[Ljava.lang.String;"), 48);
        g = factory.a("method-execution", (Signature) factory.a("1", "setKeywords", "org.mp4parser.boxes.threegpp.ts26244.KeywordsBox", "[Ljava.lang.String;", "keywords", "", "void"), 52);
        h = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.threegpp.ts26244.KeywordsBox", "", "", "", "java.lang.String"), 87);
    }

    public KeywordsBox() {
        super(f3972a);
    }

    public String e() {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this));
        return this.b;
    }

    public void a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this, (Object) str));
        this.b = str;
    }

    public String[] f() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.c;
    }

    public void a(String[] strArr) {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this, (Object) strArr));
        this.c = strArr;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        long j = 7;
        for (String b2 : this.c) {
            j += (long) (Utf8.b(b2) + 1 + 1);
        }
        return j;
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.l(byteBuffer);
        int f2 = IsoTypeReader.f(byteBuffer);
        this.c = new String[f2];
        for (int i = 0; i < f2; i++) {
            IsoTypeReader.f(byteBuffer);
            this.c[i] = IsoTypeReader.g(byteBuffer);
        }
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.a(byteBuffer, this.b);
        IsoTypeWriter.d(byteBuffer, this.c.length);
        for (String str : this.c) {
            IsoTypeWriter.d(byteBuffer, Utf8.b(str) + 1);
            byteBuffer.put(Utf8.a(str));
        }
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("KeywordsBox[language=");
        stringBuffer.append(e());
        for (int i = 0; i < this.c.length; i++) {
            stringBuffer.append(";keyword");
            stringBuffer.append(i);
            stringBuffer.append("=");
            stringBuffer.append(this.c[i]);
        }
        stringBuffer.append(Operators.ARRAY_END_STR);
        return stringBuffer.toString();
    }
}
