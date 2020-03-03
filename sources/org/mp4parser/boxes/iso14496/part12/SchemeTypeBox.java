package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.IsoFile;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import org.mp4parser.tools.Utf8;

public class SchemeTypeBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3887a = "schm";
    static final /* synthetic */ boolean e = (!SchemeTypeBox.class.desiredAssertionStatus());
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    private static final JoinPoint.StaticPart k = null;
    private static final JoinPoint.StaticPart l = null;
    String b = "    ";
    long c;
    String d = null;

    private static void h() {
        Factory factory = new Factory("SchemeTypeBox.java", SchemeTypeBox.class);
        f = factory.a("method-execution", (Signature) factory.a("1", "getSchemeType", "org.mp4parser.boxes.iso14496.part12.SchemeTypeBox", "", "", "", "java.lang.String"), 44);
        g = factory.a("method-execution", (Signature) factory.a("1", "setSchemeType", "org.mp4parser.boxes.iso14496.part12.SchemeTypeBox", "java.lang.String", "schemeType", "", "void"), 48);
        h = factory.a("method-execution", (Signature) factory.a("1", "getSchemeVersion", "org.mp4parser.boxes.iso14496.part12.SchemeTypeBox", "", "", "", "long"), 53);
        i = factory.a("method-execution", (Signature) factory.a("1", "setSchemeVersion", "org.mp4parser.boxes.iso14496.part12.SchemeTypeBox", "int", "schemeVersion", "", "void"), 57);
        j = factory.a("method-execution", (Signature) factory.a("1", "getSchemeUri", "org.mp4parser.boxes.iso14496.part12.SchemeTypeBox", "", "", "", "java.lang.String"), 61);
        k = factory.a("method-execution", (Signature) factory.a("1", "setSchemeUri", "org.mp4parser.boxes.iso14496.part12.SchemeTypeBox", "java.lang.String", "schemeUri", "", "void"), 65);
        l = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.SchemeTypeBox", "", "", "", "java.lang.String"), 93);
    }

    static {
        h();
    }

    public SchemeTypeBox() {
        super(f3887a);
    }

    public String e() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.b;
    }

    public void a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this, (Object) str));
        if (e || (str != null && str.length() == 4)) {
            this.b = str;
            return;
        }
        throw new AssertionError("SchemeType may not be null or not 4 bytes long");
    }

    public long f() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        return this.c;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this, Conversions.a(i2)));
        this.c = (long) i2;
    }

    public String g() {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this));
        return this.d;
    }

    public void b(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this, (Object) str));
        this.d = str;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) (((d() & 1) == 1 ? Utf8.b(this.d) + 1 : 0) + 12);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.m(byteBuffer);
        this.c = IsoTypeReader.b(byteBuffer);
        if ((d() & 1) == 1) {
            this.d = IsoTypeReader.g(byteBuffer);
        }
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        byteBuffer.put(IsoFile.a(this.b));
        IsoTypeWriter.b(byteBuffer, this.c);
        if ((d() & 1) == 1) {
            byteBuffer.put(Utf8.a(this.d));
        }
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this));
        return "Schema Type Box[" + "schemeUri=" + this.d + "; " + "schemeType=" + this.b + "; " + "schemeVersion=" + this.c + "; " + Operators.ARRAY_END_STR;
    }
}
