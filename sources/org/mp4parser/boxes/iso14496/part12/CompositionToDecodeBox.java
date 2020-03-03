package org.mp4parser.boxes.iso14496.part12;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;

public class CompositionToDecodeBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3838a = "cslg";
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    private static final JoinPoint.StaticPart k = null;
    private static final JoinPoint.StaticPart l = null;
    private static final JoinPoint.StaticPart m = null;
    private static final JoinPoint.StaticPart n = null;
    private static final JoinPoint.StaticPart o = null;
    private static final JoinPoint.StaticPart p = null;
    int b;
    int c;
    int d;
    int e;
    int f;

    static {
        k();
    }

    private static void k() {
        Factory factory = new Factory("CompositionToDecodeBox.java", CompositionToDecodeBox.class);
        g = factory.a("method-execution", (Signature) factory.a("1", "getCompositionOffsetToDisplayOffsetShift", "org.mp4parser.boxes.iso14496.part12.CompositionToDecodeBox", "", "", "", "int"), 60);
        h = factory.a("method-execution", (Signature) factory.a("1", "setCompositionOffsetToDisplayOffsetShift", "org.mp4parser.boxes.iso14496.part12.CompositionToDecodeBox", "int", "compositionOffsetToDisplayOffsetShift", "", "void"), 64);
        i = factory.a("method-execution", (Signature) factory.a("1", "getLeastDisplayOffset", "org.mp4parser.boxes.iso14496.part12.CompositionToDecodeBox", "", "", "", "int"), 68);
        j = factory.a("method-execution", (Signature) factory.a("1", "setLeastDisplayOffset", "org.mp4parser.boxes.iso14496.part12.CompositionToDecodeBox", "int", "leastDisplayOffset", "", "void"), 72);
        k = factory.a("method-execution", (Signature) factory.a("1", "getGreatestDisplayOffset", "org.mp4parser.boxes.iso14496.part12.CompositionToDecodeBox", "", "", "", "int"), 76);
        l = factory.a("method-execution", (Signature) factory.a("1", "setGreatestDisplayOffset", "org.mp4parser.boxes.iso14496.part12.CompositionToDecodeBox", "int", "greatestDisplayOffset", "", "void"), 80);
        m = factory.a("method-execution", (Signature) factory.a("1", "getDisplayStartTime", "org.mp4parser.boxes.iso14496.part12.CompositionToDecodeBox", "", "", "", "int"), 84);
        n = factory.a("method-execution", (Signature) factory.a("1", "setDisplayStartTime", "org.mp4parser.boxes.iso14496.part12.CompositionToDecodeBox", "int", "displayStartTime", "", "void"), 88);
        o = factory.a("method-execution", (Signature) factory.a("1", "getDisplayEndTime", "org.mp4parser.boxes.iso14496.part12.CompositionToDecodeBox", "", "", "", "int"), 92);
        p = factory.a("method-execution", (Signature) factory.a("1", "setDisplayEndTime", "org.mp4parser.boxes.iso14496.part12.CompositionToDecodeBox", "int", "displayEndTime", "", "void"), 96);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 24;
    }

    public CompositionToDecodeBox() {
        super(f3838a);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = byteBuffer.getInt();
        this.c = byteBuffer.getInt();
        this.d = byteBuffer.getInt();
        this.e = byteBuffer.getInt();
        this.f = byteBuffer.getInt();
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        byteBuffer.putInt(this.b);
        byteBuffer.putInt(this.c);
        byteBuffer.putInt(this.d);
        byteBuffer.putInt(this.e);
        byteBuffer.putInt(this.f);
    }

    public int e() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return this.b;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this, Conversions.a(i2)));
        this.b = i2;
    }

    public int f() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.c;
    }

    public void d(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this, Conversions.a(i2)));
        this.c = i2;
    }

    public int g() {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this));
        return this.d;
    }

    public void e(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this, Conversions.a(i2)));
        this.d = i2;
    }

    public int h() {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this));
        return this.e;
    }

    public void f(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this, Conversions.a(i2)));
        this.e = i2;
    }

    public int i() {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this));
        return this.f;
    }

    public void g(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this, Conversions.a(i2)));
        this.f = i2;
    }
}
