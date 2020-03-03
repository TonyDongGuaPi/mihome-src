package org.mp4parser.boxes.threegpp.ts26244;

import com.taobao.weex.common.Constants;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import org.mp4parser.tools.Utf8;

public class LocationInformationBox extends AbstractFullBox {
    private static final JoinPoint.StaticPart A = null;
    private static final JoinPoint.StaticPart B = null;

    /* renamed from: a  reason: collision with root package name */
    public static final String f3973a = "loci";
    private static final JoinPoint.StaticPart j = null;
    private static final JoinPoint.StaticPart k = null;
    private static final JoinPoint.StaticPart l = null;
    private static final JoinPoint.StaticPart m = null;
    private static final JoinPoint.StaticPart n = null;
    private static final JoinPoint.StaticPart o = null;
    private static final JoinPoint.StaticPart p = null;
    private static final JoinPoint.StaticPart q = null;
    private static final JoinPoint.StaticPart u = null;
    private static final JoinPoint.StaticPart v = null;
    private static final JoinPoint.StaticPart w = null;
    private static final JoinPoint.StaticPart x = null;
    private static final JoinPoint.StaticPart y = null;
    private static final JoinPoint.StaticPart z = null;
    private String b;
    private String c = "";
    private int d;
    private double e;
    private double f;
    private double g;
    private String h = "";
    private String i = "";

    static {
        n();
    }

    private static void n() {
        Factory factory = new Factory("LocationInformationBox.java", LocationInformationBox.class);
        j = factory.a("method-execution", (Signature) factory.a("1", "getLanguage", "org.mp4parser.boxes.threegpp.ts26244.LocationInformationBox", "", "", "", "java.lang.String"), 30);
        k = factory.a("method-execution", (Signature) factory.a("1", "setLanguage", "org.mp4parser.boxes.threegpp.ts26244.LocationInformationBox", "java.lang.String", "language", "", "void"), 34);
        w = factory.a("method-execution", (Signature) factory.a("1", "getAltitude", "org.mp4parser.boxes.threegpp.ts26244.LocationInformationBox", "", "", "", "double"), 70);
        x = factory.a("method-execution", (Signature) factory.a("1", "setAltitude", "org.mp4parser.boxes.threegpp.ts26244.LocationInformationBox", "double", "altitude", "", "void"), 74);
        y = factory.a("method-execution", (Signature) factory.a("1", "getAstronomicalBody", "org.mp4parser.boxes.threegpp.ts26244.LocationInformationBox", "", "", "", "java.lang.String"), 78);
        z = factory.a("method-execution", (Signature) factory.a("1", "setAstronomicalBody", "org.mp4parser.boxes.threegpp.ts26244.LocationInformationBox", "java.lang.String", "astronomicalBody", "", "void"), 82);
        A = factory.a("method-execution", (Signature) factory.a("1", "getAdditionalNotes", "org.mp4parser.boxes.threegpp.ts26244.LocationInformationBox", "", "", "", "java.lang.String"), 86);
        B = factory.a("method-execution", (Signature) factory.a("1", "setAdditionalNotes", "org.mp4parser.boxes.threegpp.ts26244.LocationInformationBox", "java.lang.String", "additionalNotes", "", "void"), 90);
        l = factory.a("method-execution", (Signature) factory.a("1", "getName", "org.mp4parser.boxes.threegpp.ts26244.LocationInformationBox", "", "", "", "java.lang.String"), 38);
        m = factory.a("method-execution", (Signature) factory.a("1", "setName", "org.mp4parser.boxes.threegpp.ts26244.LocationInformationBox", "java.lang.String", "name", "", "void"), 42);
        n = factory.a("method-execution", (Signature) factory.a("1", "getRole", "org.mp4parser.boxes.threegpp.ts26244.LocationInformationBox", "", "", "", "int"), 46);
        o = factory.a("method-execution", (Signature) factory.a("1", "setRole", "org.mp4parser.boxes.threegpp.ts26244.LocationInformationBox", "int", Constants.Name.ROLE, "", "void"), 50);
        p = factory.a("method-execution", (Signature) factory.a("1", "getLongitude", "org.mp4parser.boxes.threegpp.ts26244.LocationInformationBox", "", "", "", "double"), 54);
        q = factory.a("method-execution", (Signature) factory.a("1", "setLongitude", "org.mp4parser.boxes.threegpp.ts26244.LocationInformationBox", "double", "longitude", "", "void"), 58);
        u = factory.a("method-execution", (Signature) factory.a("1", "getLatitude", "org.mp4parser.boxes.threegpp.ts26244.LocationInformationBox", "", "", "", "double"), 62);
        v = factory.a("method-execution", (Signature) factory.a("1", "setLatitude", "org.mp4parser.boxes.threegpp.ts26244.LocationInformationBox", "double", "latitude", "", "void"), 66);
    }

    public LocationInformationBox() {
        super(f3973a);
    }

    public String e() {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this));
        return this.b;
    }

    public void a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this, (Object) str));
        this.b = str;
    }

    public String f() {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this));
        return this.c;
    }

    public void b(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this, (Object) str));
        this.c = str;
    }

    public int g() {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this));
        return this.d;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this, Conversions.a(i2)));
        this.d = i2;
    }

    public double h() {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this));
        return this.e;
    }

    public void a(double d2) {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this, Conversions.a(d2)));
        this.e = d2;
    }

    public double i() {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this));
        return this.f;
    }

    public void b(double d2) {
        RequiresParseDetailAspect.a().a(Factory.a(v, (Object) this, (Object) this, Conversions.a(d2)));
        this.f = d2;
    }

    public double k() {
        RequiresParseDetailAspect.a().a(Factory.a(w, (Object) this, (Object) this));
        return this.g;
    }

    public void c(double d2) {
        RequiresParseDetailAspect.a().a(Factory.a(x, (Object) this, (Object) this, Conversions.a(d2)));
        this.g = d2;
    }

    public String l() {
        RequiresParseDetailAspect.a().a(Factory.a(y, (Object) this, (Object) this));
        return this.h;
    }

    public void c(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(z, (Object) this, (Object) this, (Object) str));
        this.h = str;
    }

    public String m() {
        RequiresParseDetailAspect.a().a(Factory.a(A, (Object) this, (Object) this));
        return this.i;
    }

    public void d(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(B, (Object) this, (Object) this, (Object) str));
        this.i = str;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) (Utf8.a(this.c).length + 22 + Utf8.a(this.h).length + Utf8.a(this.i).length);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.l(byteBuffer);
        this.c = IsoTypeReader.g(byteBuffer);
        this.d = IsoTypeReader.f(byteBuffer);
        this.e = IsoTypeReader.i(byteBuffer);
        this.f = IsoTypeReader.i(byteBuffer);
        this.g = IsoTypeReader.i(byteBuffer);
        this.h = IsoTypeReader.g(byteBuffer);
        this.i = IsoTypeReader.g(byteBuffer);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.a(byteBuffer, this.b);
        byteBuffer.put(Utf8.a(this.c));
        byteBuffer.put((byte) 0);
        IsoTypeWriter.d(byteBuffer, this.d);
        IsoTypeWriter.a(byteBuffer, this.e);
        IsoTypeWriter.a(byteBuffer, this.f);
        IsoTypeWriter.a(byteBuffer, this.g);
        byteBuffer.put(Utf8.a(this.h));
        byteBuffer.put((byte) 0);
        byteBuffer.put(Utf8.a(this.i));
        byteBuffer.put((byte) 0);
    }
}
