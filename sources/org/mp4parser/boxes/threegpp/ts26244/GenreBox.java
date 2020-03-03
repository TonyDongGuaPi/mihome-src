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

public class GenreBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3971a = "gnre";
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
        Factory factory = new Factory("GenreBox.java", GenreBox.class);
        d = factory.a("method-execution", (Signature) factory.a("1", "getLanguage", "org.mp4parser.boxes.threegpp.ts26244.GenreBox", "", "", "", "java.lang.String"), 43);
        e = factory.a("method-execution", (Signature) factory.a("1", "setLanguage", "org.mp4parser.boxes.threegpp.ts26244.GenreBox", "java.lang.String", "language", "", "void"), 47);
        f = factory.a("method-execution", (Signature) factory.a("1", "getGenre", "org.mp4parser.boxes.threegpp.ts26244.GenreBox", "", "", "", "java.lang.String"), 51);
        g = factory.a("method-execution", (Signature) factory.a("1", "setGenre", "org.mp4parser.boxes.threegpp.ts26244.GenreBox", "java.lang.String", "genre", "", "void"), 55);
        h = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.threegpp.ts26244.GenreBox", "", "", "", "java.lang.String"), 78);
    }

    public GenreBox() {
        super(f3971a);
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

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.l(byteBuffer);
        this.c = IsoTypeReader.g(byteBuffer);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.a(byteBuffer, this.b);
        byteBuffer.put(Utf8.a(this.c));
        byteBuffer.put((byte) 0);
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        return "GenreBox[language=" + e() + ";genre=" + f() + Operators.ARRAY_END_STR;
    }
}
