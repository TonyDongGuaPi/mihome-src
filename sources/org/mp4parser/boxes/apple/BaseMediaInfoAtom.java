package org.mp4parser.boxes.apple;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.payment.data.MibiConstants;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class BaseMediaInfoAtom extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3794a = "gmin";
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
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
    short b = 64;
    int c = 32768;
    int d = 32768;
    int e = 32768;
    short f;
    short g;

    static {
        k();
    }

    private static void k() {
        Factory factory = new Factory("BaseMediaInfoAtom.java", BaseMediaInfoAtom.class);
        h = factory.a("method-execution", (Signature) factory.a("1", "getGraphicsMode", "org.mp4parser.boxes.apple.BaseMediaInfoAtom", "", "", "", "short"), 54);
        i = factory.a("method-execution", (Signature) factory.a("1", "setGraphicsMode", "org.mp4parser.boxes.apple.BaseMediaInfoAtom", "short", "graphicsMode", "", "void"), 58);
        u = factory.a("method-execution", (Signature) factory.a("1", "getReserved", "org.mp4parser.boxes.apple.BaseMediaInfoAtom", "", "", "", "short"), 94);
        v = factory.a("method-execution", (Signature) factory.a("1", "setReserved", "org.mp4parser.boxes.apple.BaseMediaInfoAtom", "short", "reserved", "", "void"), 98);
        w = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.apple.BaseMediaInfoAtom", "", "", "", "java.lang.String"), 103);
        j = factory.a("method-execution", (Signature) factory.a("1", "getOpColorR", "org.mp4parser.boxes.apple.BaseMediaInfoAtom", "", "", "", "int"), 62);
        k = factory.a("method-execution", (Signature) factory.a("1", "setOpColorR", "org.mp4parser.boxes.apple.BaseMediaInfoAtom", "int", "opColorR", "", "void"), 66);
        l = factory.a("method-execution", (Signature) factory.a("1", "getOpColorG", "org.mp4parser.boxes.apple.BaseMediaInfoAtom", "", "", "", "int"), 70);
        m = factory.a("method-execution", (Signature) factory.a("1", "setOpColorG", "org.mp4parser.boxes.apple.BaseMediaInfoAtom", "int", "opColorG", "", "void"), 74);
        n = factory.a("method-execution", (Signature) factory.a("1", "getOpColorB", "org.mp4parser.boxes.apple.BaseMediaInfoAtom", "", "", "", "int"), 78);
        o = factory.a("method-execution", (Signature) factory.a("1", "setOpColorB", "org.mp4parser.boxes.apple.BaseMediaInfoAtom", "int", "opColorB", "", "void"), 82);
        p = factory.a("method-execution", (Signature) factory.a("1", MibiConstants.bz, "org.mp4parser.boxes.apple.BaseMediaInfoAtom", "", "", "", "short"), 86);
        q = factory.a("method-execution", (Signature) factory.a("1", "setBalance", "org.mp4parser.boxes.apple.BaseMediaInfoAtom", "short", "balance", "", "void"), 90);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 16;
    }

    public BaseMediaInfoAtom() {
        super(f3794a);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        byteBuffer.putShort(this.b);
        IsoTypeWriter.b(byteBuffer, this.c);
        IsoTypeWriter.b(byteBuffer, this.d);
        IsoTypeWriter.b(byteBuffer, this.e);
        byteBuffer.putShort(this.f);
        byteBuffer.putShort(this.g);
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = byteBuffer.getShort();
        this.c = IsoTypeReader.d(byteBuffer);
        this.d = IsoTypeReader.d(byteBuffer);
        this.e = IsoTypeReader.d(byteBuffer);
        this.f = byteBuffer.getShort();
        this.g = byteBuffer.getShort();
    }

    public short e() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        return this.b;
    }

    public void a(short s) {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this, Conversions.a(s)));
        this.b = s;
    }

    public int f() {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this));
        return this.c;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this, Conversions.a(i2)));
        this.c = i2;
    }

    public int g() {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this));
        return this.d;
    }

    public void d(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this, Conversions.a(i2)));
        this.d = i2;
    }

    public int h() {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this));
        return this.e;
    }

    public void e(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this, Conversions.a(i2)));
        this.e = i2;
    }

    public short i() {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this));
        return this.f;
    }

    public void b(short s) {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this, Conversions.a(s)));
        this.f = s;
    }

    public short j() {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this));
        return this.g;
    }

    public void c(short s) {
        RequiresParseDetailAspect.a().a(Factory.a(v, (Object) this, (Object) this, Conversions.a(s)));
        this.g = s;
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(w, (Object) this, (Object) this));
        return "BaseMediaInfoAtom{graphicsMode=" + this.b + ", opColorR=" + this.c + ", opColorG=" + this.d + ", opColorB=" + this.e + ", balance=" + this.f + ", reserved=" + this.g + Operators.BLOCK_END;
    }
}
