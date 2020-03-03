package org.mp4parser.boxes.threegpp.ts26244;

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

public class ClassificationBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3968a = "clsf";
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    private static final JoinPoint.StaticPart k = null;
    private static final JoinPoint.StaticPart l = null;
    private static final JoinPoint.StaticPart m = null;
    private static final JoinPoint.StaticPart n = null;
    private String b;
    private int c;
    private String d;
    private String e;

    static {
        i();
    }

    private static void i() {
        Factory factory = new Factory("ClassificationBox.java", ClassificationBox.class);
        f = factory.a("method-execution", (Signature) factory.a("1", "getLanguage", "org.mp4parser.boxes.threegpp.ts26244.ClassificationBox", "", "", "", "java.lang.String"), 44);
        g = factory.a("method-execution", (Signature) factory.a("1", "setLanguage", "org.mp4parser.boxes.threegpp.ts26244.ClassificationBox", "java.lang.String", "language", "", "void"), 48);
        h = factory.a("method-execution", (Signature) factory.a("1", "getClassificationEntity", "org.mp4parser.boxes.threegpp.ts26244.ClassificationBox", "", "", "", "java.lang.String"), 52);
        i = factory.a("method-execution", (Signature) factory.a("1", "setClassificationEntity", "org.mp4parser.boxes.threegpp.ts26244.ClassificationBox", "java.lang.String", "classificationEntity", "", "void"), 56);
        j = factory.a("method-execution", (Signature) factory.a("1", "getClassificationTableIndex", "org.mp4parser.boxes.threegpp.ts26244.ClassificationBox", "", "", "", "int"), 60);
        k = factory.a("method-execution", (Signature) factory.a("1", "setClassificationTableIndex", "org.mp4parser.boxes.threegpp.ts26244.ClassificationBox", "int", "classificationTableIndex", "", "void"), 64);
        l = factory.a("method-execution", (Signature) factory.a("1", "getClassificationInfo", "org.mp4parser.boxes.threegpp.ts26244.ClassificationBox", "", "", "", "java.lang.String"), 68);
        m = factory.a("method-execution", (Signature) factory.a("1", "setClassificationInfo", "org.mp4parser.boxes.threegpp.ts26244.ClassificationBox", "java.lang.String", "classificationInfo", "", "void"), 72);
        n = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.threegpp.ts26244.ClassificationBox", "", "", "", "java.lang.String"), 101);
    }

    public ClassificationBox() {
        super(f3968a);
    }

    public String e() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.d;
    }

    public void a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this, (Object) str));
        this.d = str;
    }

    public String f() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        return this.b;
    }

    public void b(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this, (Object) str));
        this.b = str;
    }

    public int g() {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this));
        return this.c;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this, Conversions.a(i2)));
        this.c = i2;
    }

    public String h() {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this));
        return this.e;
    }

    public void c(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this, (Object) str));
        this.e = str;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) (Utf8.b(this.e) + 8 + 1);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        byte[] bArr = new byte[4];
        byteBuffer.get(bArr);
        this.b = IsoFile.a(bArr);
        this.c = IsoTypeReader.d(byteBuffer);
        this.d = IsoTypeReader.l(byteBuffer);
        this.e = IsoTypeReader.g(byteBuffer);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        byteBuffer.put(IsoFile.a(this.b));
        IsoTypeWriter.b(byteBuffer, this.c);
        IsoTypeWriter.a(byteBuffer, this.d);
        byteBuffer.put(Utf8.a(this.e));
        byteBuffer.put((byte) 0);
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this));
        return "ClassificationBox[language=" + e() + "classificationEntity=" + f() + ";classificationTableIndex=" + g() + ";language=" + e() + ";classificationInfo=" + h() + Operators.ARRAY_END_STR;
    }
}
