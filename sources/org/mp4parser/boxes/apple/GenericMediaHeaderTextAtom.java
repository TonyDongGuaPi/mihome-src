package org.mp4parser.boxes.apple;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;

public class GenericMediaHeaderTextAtom extends AbstractBox {
    private static final JoinPoint.StaticPart A = null;
    private static final JoinPoint.StaticPart B = null;
    private static final JoinPoint.StaticPart C = null;
    private static final JoinPoint.StaticPart D = null;
    private static final JoinPoint.StaticPart E = null;

    /* renamed from: a  reason: collision with root package name */
    public static final String f3797a = "text";
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
    int b = 65536;
    int c;
    int d;
    int e;
    int f = 65536;
    int g;
    int h;
    int i;
    int j = 1073741824;

    static {
        m();
    }

    private static void m() {
        Factory factory = new Factory("GenericMediaHeaderTextAtom.java", GenericMediaHeaderTextAtom.class);
        k = factory.a("method-execution", (Signature) factory.a("1", "getUnknown_1", "org.mp4parser.boxes.apple.GenericMediaHeaderTextAtom", "", "", "", "int"), 60);
        l = factory.a("method-execution", (Signature) factory.a("1", "setUnknown_1", "org.mp4parser.boxes.apple.GenericMediaHeaderTextAtom", "int", "unknown_1", "", "void"), 64);
        x = factory.a("method-execution", (Signature) factory.a("1", "getUnknown_6", "org.mp4parser.boxes.apple.GenericMediaHeaderTextAtom", "", "", "", "int"), 100);
        y = factory.a("method-execution", (Signature) factory.a("1", "setUnknown_6", "org.mp4parser.boxes.apple.GenericMediaHeaderTextAtom", "int", "unknown_6", "", "void"), 104);
        z = factory.a("method-execution", (Signature) factory.a("1", "getUnknown_7", "org.mp4parser.boxes.apple.GenericMediaHeaderTextAtom", "", "", "", "int"), 108);
        A = factory.a("method-execution", (Signature) factory.a("1", "setUnknown_7", "org.mp4parser.boxes.apple.GenericMediaHeaderTextAtom", "int", "unknown_7", "", "void"), 112);
        B = factory.a("method-execution", (Signature) factory.a("1", "getUnknown_8", "org.mp4parser.boxes.apple.GenericMediaHeaderTextAtom", "", "", "", "int"), 116);
        C = factory.a("method-execution", (Signature) factory.a("1", "setUnknown_8", "org.mp4parser.boxes.apple.GenericMediaHeaderTextAtom", "int", "unknown_8", "", "void"), 120);
        D = factory.a("method-execution", (Signature) factory.a("1", "getUnknown_9", "org.mp4parser.boxes.apple.GenericMediaHeaderTextAtom", "", "", "", "int"), 124);
        E = factory.a("method-execution", (Signature) factory.a("1", "setUnknown_9", "org.mp4parser.boxes.apple.GenericMediaHeaderTextAtom", "int", "unknown_9", "", "void"), 128);
        m = factory.a("method-execution", (Signature) factory.a("1", "getUnknown_2", "org.mp4parser.boxes.apple.GenericMediaHeaderTextAtom", "", "", "", "int"), 68);
        n = factory.a("method-execution", (Signature) factory.a("1", "setUnknown_2", "org.mp4parser.boxes.apple.GenericMediaHeaderTextAtom", "int", "unknown_2", "", "void"), 72);
        o = factory.a("method-execution", (Signature) factory.a("1", "getUnknown_3", "org.mp4parser.boxes.apple.GenericMediaHeaderTextAtom", "", "", "", "int"), 76);
        p = factory.a("method-execution", (Signature) factory.a("1", "setUnknown_3", "org.mp4parser.boxes.apple.GenericMediaHeaderTextAtom", "int", "unknown_3", "", "void"), 80);
        q = factory.a("method-execution", (Signature) factory.a("1", "getUnknown_4", "org.mp4parser.boxes.apple.GenericMediaHeaderTextAtom", "", "", "", "int"), 84);
        u = factory.a("method-execution", (Signature) factory.a("1", "setUnknown_4", "org.mp4parser.boxes.apple.GenericMediaHeaderTextAtom", "int", "unknown_4", "", "void"), 88);
        v = factory.a("method-execution", (Signature) factory.a("1", "getUnknown_5", "org.mp4parser.boxes.apple.GenericMediaHeaderTextAtom", "", "", "", "int"), 92);
        w = factory.a("method-execution", (Signature) factory.a("1", "setUnknown_5", "org.mp4parser.boxes.apple.GenericMediaHeaderTextAtom", "int", "unknown_5", "", "void"), 96);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 36;
    }

    public GenericMediaHeaderTextAtom() {
        super("text");
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.b);
        byteBuffer.putInt(this.c);
        byteBuffer.putInt(this.d);
        byteBuffer.putInt(this.e);
        byteBuffer.putInt(this.f);
        byteBuffer.putInt(this.g);
        byteBuffer.putInt(this.h);
        byteBuffer.putInt(this.i);
        byteBuffer.putInt(this.j);
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        this.b = byteBuffer.getInt();
        this.c = byteBuffer.getInt();
        this.d = byteBuffer.getInt();
        this.e = byteBuffer.getInt();
        this.f = byteBuffer.getInt();
        this.g = byteBuffer.getInt();
        this.h = byteBuffer.getInt();
        this.i = byteBuffer.getInt();
        this.j = byteBuffer.getInt();
    }

    public int d() {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this));
        return this.b;
    }

    public void a(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this, Conversions.a(i2)));
        this.b = i2;
    }

    public int e() {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this));
        return this.c;
    }

    public void b(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this, Conversions.a(i2)));
        this.c = i2;
    }

    public int f() {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this));
        return this.d;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this, Conversions.a(i2)));
        this.d = i2;
    }

    public int g() {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this));
        return this.e;
    }

    public void d(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this, Conversions.a(i2)));
        this.e = i2;
    }

    public int h() {
        RequiresParseDetailAspect.a().a(Factory.a(v, (Object) this, (Object) this));
        return this.f;
    }

    public void e(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(w, (Object) this, (Object) this, Conversions.a(i2)));
        this.f = i2;
    }

    public int i() {
        RequiresParseDetailAspect.a().a(Factory.a(x, (Object) this, (Object) this));
        return this.g;
    }

    public void f(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(y, (Object) this, (Object) this, Conversions.a(i2)));
        this.g = i2;
    }

    public int j() {
        RequiresParseDetailAspect.a().a(Factory.a(z, (Object) this, (Object) this));
        return this.h;
    }

    public void g(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(A, (Object) this, (Object) this, Conversions.a(i2)));
        this.h = i2;
    }

    public int k() {
        RequiresParseDetailAspect.a().a(Factory.a(B, (Object) this, (Object) this));
        return this.i;
    }

    public void h(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(C, (Object) this, (Object) this, Conversions.a(i2)));
        this.i = i2;
    }

    public int l() {
        RequiresParseDetailAspect.a().a(Factory.a(D, (Object) this, (Object) this));
        return this.j;
    }

    public void i(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(E, (Object) this, (Object) this, Conversions.a(i2)));
        this.j = i2;
    }
}
