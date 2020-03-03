package org.mp4parser.boxes.apple;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class TrackProductionApertureDimensionsAtom extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3804a = "prof";
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    double b;
    double c;

    static {
        g();
    }

    private static void g() {
        Factory factory = new Factory("TrackProductionApertureDimensionsAtom.java", TrackProductionApertureDimensionsAtom.class);
        d = factory.a("method-execution", (Signature) factory.a("1", "getWidth", "org.mp4parser.boxes.apple.TrackProductionApertureDimensionsAtom", "", "", "", "double"), 44);
        e = factory.a("method-execution", (Signature) factory.a("1", "setWidth", "org.mp4parser.boxes.apple.TrackProductionApertureDimensionsAtom", "double", "width", "", "void"), 48);
        f = factory.a("method-execution", (Signature) factory.a("1", "getHeight", "org.mp4parser.boxes.apple.TrackProductionApertureDimensionsAtom", "", "", "", "double"), 52);
        g = factory.a("method-execution", (Signature) factory.a("1", "setHeight", "org.mp4parser.boxes.apple.TrackProductionApertureDimensionsAtom", "double", "height", "", "void"), 56);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 12;
    }

    public TrackProductionApertureDimensionsAtom() {
        super(f3804a);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.a(byteBuffer, this.b);
        IsoTypeWriter.a(byteBuffer, this.c);
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.i(byteBuffer);
        this.c = IsoTypeReader.i(byteBuffer);
    }

    public double e() {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this));
        return this.b;
    }

    public void a(double d2) {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this, Conversions.a(d2)));
        this.b = d2;
    }

    public double f() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.c;
    }

    public void b(double d2) {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this, Conversions.a(d2)));
        this.c = d2;
    }
}
