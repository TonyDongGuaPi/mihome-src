package org.mp4parser.boxes.oma;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public final class OmaDrmAccessUnitFormatBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3947a = "odaf";
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    private static final JoinPoint.StaticPart k = null;
    private boolean b;
    private byte c;
    private int d;
    private int e;

    static {
        h();
    }

    private static void h() {
        Factory factory = new Factory("OmaDrmAccessUnitFormatBox.java", OmaDrmAccessUnitFormatBox.class);
        f = factory.a("method-execution", (Signature) factory.a("1", "isSelectiveEncryption", "org.mp4parser.boxes.oma.OmaDrmAccessUnitFormatBox", "", "", "", "boolean"), 46);
        g = factory.a("method-execution", (Signature) factory.a("1", "getKeyIndicatorLength", "org.mp4parser.boxes.oma.OmaDrmAccessUnitFormatBox", "", "", "", "int"), 50);
        h = factory.a("method-execution", (Signature) factory.a("1", "setKeyIndicatorLength", "org.mp4parser.boxes.oma.OmaDrmAccessUnitFormatBox", "int", "keyIndicatorLength", "", "void"), 54);
        i = factory.a("method-execution", (Signature) factory.a("1", "getInitVectorLength", "org.mp4parser.boxes.oma.OmaDrmAccessUnitFormatBox", "", "", "", "int"), 58);
        j = factory.a("method-execution", (Signature) factory.a("1", "setInitVectorLength", "org.mp4parser.boxes.oma.OmaDrmAccessUnitFormatBox", "int", "initVectorLength", "", "void"), 62);
        k = factory.a("method-execution", (Signature) factory.a("1", "setAllBits", "org.mp4parser.boxes.oma.OmaDrmAccessUnitFormatBox", "byte", "allBits", "", "void"), 66);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 7;
    }

    public OmaDrmAccessUnitFormatBox() {
        super(f3947a);
    }

    public boolean e() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.b;
    }

    public int f() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return this.d;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this, Conversions.a(i2)));
        this.d = i2;
    }

    public int g() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.e;
    }

    public void d(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this, Conversions.a(i2)));
        this.e = i2;
    }

    public void a(byte b2) {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this, Conversions.a(b2)));
        this.c = b2;
        this.b = (b2 & 128) == 128;
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.c = (byte) IsoTypeReader.f(byteBuffer);
        this.b = (this.c & 128) == 128;
        this.d = IsoTypeReader.f(byteBuffer);
        this.e = IsoTypeReader.f(byteBuffer);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.d(byteBuffer, (int) this.c);
        IsoTypeWriter.d(byteBuffer, this.d);
        IsoTypeWriter.d(byteBuffer, this.e);
    }
}
