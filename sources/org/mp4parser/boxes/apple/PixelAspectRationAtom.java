package org.mp4parser.boxes.apple;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;

public class PixelAspectRationAtom extends AbstractBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3798a = "pasp";
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private int b;
    private int c;

    static {
        f();
    }

    private static void f() {
        Factory factory = new Factory("PixelAspectRationAtom.java", PixelAspectRationAtom.class);
        d = factory.a("method-execution", (Signature) factory.a("1", "gethSpacing", "org.mp4parser.boxes.apple.PixelAspectRationAtom", "", "", "", "int"), 31);
        e = factory.a("method-execution", (Signature) factory.a("1", "sethSpacing", "org.mp4parser.boxes.apple.PixelAspectRationAtom", "int", "hSpacing", "", "void"), 35);
        f = factory.a("method-execution", (Signature) factory.a("1", "getvSpacing", "org.mp4parser.boxes.apple.PixelAspectRationAtom", "", "", "", "int"), 39);
        g = factory.a("method-execution", (Signature) factory.a("1", "setvSpacing", "org.mp4parser.boxes.apple.PixelAspectRationAtom", "int", "vSpacing", "", "void"), 43);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 8;
    }

    public PixelAspectRationAtom() {
        super(f3798a);
    }

    public int d() {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this));
        return this.b;
    }

    public void a(int i) {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this, Conversions.a(i)));
        this.b = i;
    }

    public int e() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.c;
    }

    public void b(int i) {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this, Conversions.a(i)));
        this.c = i;
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.b);
        byteBuffer.putInt(this.c);
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        this.b = byteBuffer.getInt();
        this.c = byteBuffer.getInt();
    }
}
