package org.mp4parser.boxes.threegpp.ts26244;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.IsoFile;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import org.mp4parser.tools.Utf8;

public class RatingBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3975a = "rtng";
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
    private String c;
    private String d;
    private String e;

    static {
        i();
    }

    private static void i() {
        Factory factory = new Factory("RatingBox.java", RatingBox.class);
        f = factory.a("method-execution", (Signature) factory.a("1", "getLanguage", "org.mp4parser.boxes.threegpp.ts26244.RatingBox", "", "", "", "java.lang.String"), 45);
        g = factory.a("method-execution", (Signature) factory.a("1", "setLanguage", "org.mp4parser.boxes.threegpp.ts26244.RatingBox", "java.lang.String", "language", "", "void"), 49);
        h = factory.a("method-execution", (Signature) factory.a("1", "getRatingEntity", "org.mp4parser.boxes.threegpp.ts26244.RatingBox", "", "", "", "java.lang.String"), 60);
        i = factory.a("method-execution", (Signature) factory.a("1", "setRatingEntity", "org.mp4parser.boxes.threegpp.ts26244.RatingBox", "java.lang.String", "ratingEntity", "", "void"), 64);
        j = factory.a("method-execution", (Signature) factory.a("1", "getRatingCriteria", "org.mp4parser.boxes.threegpp.ts26244.RatingBox", "", "", "", "java.lang.String"), 74);
        k = factory.a("method-execution", (Signature) factory.a("1", "setRatingCriteria", "org.mp4parser.boxes.threegpp.ts26244.RatingBox", "java.lang.String", "ratingCriteria", "", "void"), 78);
        l = factory.a("method-execution", (Signature) factory.a("1", "getRatingInfo", "org.mp4parser.boxes.threegpp.ts26244.RatingBox", "", "", "", "java.lang.String"), 82);
        m = factory.a("method-execution", (Signature) factory.a("1", "setRatingInfo", "org.mp4parser.boxes.threegpp.ts26244.RatingBox", "java.lang.String", "ratingInfo", "", "void"), 86);
        n = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.threegpp.ts26244.RatingBox", "", "", "", "java.lang.String"), 114);
    }

    public RatingBox() {
        super(f3975a);
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

    public String g() {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this));
        return this.c;
    }

    public void c(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this, (Object) str));
        this.c = str;
    }

    public String h() {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this));
        return this.e;
    }

    public void d(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this, (Object) str));
        this.e = str;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) (Utf8.b(this.e) + 15);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.m(byteBuffer);
        this.c = IsoTypeReader.m(byteBuffer);
        this.d = IsoTypeReader.l(byteBuffer);
        this.e = IsoTypeReader.g(byteBuffer);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        byteBuffer.put(IsoFile.a(this.b));
        byteBuffer.put(IsoFile.a(this.c));
        IsoTypeWriter.a(byteBuffer, this.d);
        byteBuffer.put(Utf8.a(this.e));
        byteBuffer.put((byte) 0);
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this));
        return "RatingBox[language=" + e() + "ratingEntity=" + f() + ";ratingCriteria=" + g() + ";language=" + e() + ";ratingInfo=" + h() + Operators.ARRAY_END_STR;
    }
}
