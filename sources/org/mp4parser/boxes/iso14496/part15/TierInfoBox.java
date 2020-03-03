package org.mp4parser.boxes.iso14496.part15;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class TierInfoBox extends AbstractBox {
    private static final JoinPoint.StaticPart A = null;
    private static final JoinPoint.StaticPart B = null;
    private static final JoinPoint.StaticPart C = null;
    private static final JoinPoint.StaticPart D = null;
    private static final JoinPoint.StaticPart E = null;
    private static final JoinPoint.StaticPart F = null;
    private static final JoinPoint.StaticPart G = null;
    private static final JoinPoint.StaticPart H = null;
    private static final JoinPoint.StaticPart I = null;
    private static final JoinPoint.StaticPart J = null;
    private static final JoinPoint.StaticPart K = null;

    /* renamed from: a  reason: collision with root package name */
    public static final String f3927a = "tiri";
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
    int b;
    int c;
    int d;
    int e;
    int f = 0;
    int g;
    int h;
    int i;
    int j;
    int k = 0;
    int l;

    static {
        o();
    }

    private static void o() {
        Factory factory = new Factory("TierInfoBox.java", TierInfoBox.class);
        m = factory.a("method-execution", (Signature) factory.a("1", "getTierID", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "", "", "", "int"), 69);
        n = factory.a("method-execution", (Signature) factory.a("1", "setTierID", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "int", "tierID", "", "void"), 73);
        z = factory.a("method-execution", (Signature) factory.a("1", "getVisualWidth", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "", "", "", "int"), 109);
        A = factory.a("method-execution", (Signature) factory.a("1", "setVisualWidth", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "int", "visualWidth", "", "void"), 113);
        B = factory.a("method-execution", (Signature) factory.a("1", "getVisualHeight", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "", "", "", "int"), 117);
        C = factory.a("method-execution", (Signature) factory.a("1", "setVisualHeight", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "int", "visualHeight", "", "void"), 121);
        D = factory.a("method-execution", (Signature) factory.a("1", "getDiscardable", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "", "", "", "int"), 125);
        E = factory.a("method-execution", (Signature) factory.a("1", "setDiscardable", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "int", "discardable", "", "void"), 129);
        F = factory.a("method-execution", (Signature) factory.a("1", "getConstantFrameRate", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "", "", "", "int"), 133);
        G = factory.a("method-execution", (Signature) factory.a("1", "setConstantFrameRate", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "int", "constantFrameRate", "", "void"), 137);
        H = factory.a("method-execution", (Signature) factory.a("1", "getReserved2", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "", "", "", "int"), 141);
        I = factory.a("method-execution", (Signature) factory.a("1", "setReserved2", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "int", "reserved2", "", "void"), 145);
        o = factory.a("method-execution", (Signature) factory.a("1", "getProfileIndication", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "", "", "", "int"), 77);
        J = factory.a("method-execution", (Signature) factory.a("1", "getFrameRate", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "", "", "", "int"), 149);
        K = factory.a("method-execution", (Signature) factory.a("1", "setFrameRate", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "int", "frameRate", "", "void"), 153);
        p = factory.a("method-execution", (Signature) factory.a("1", "setProfileIndication", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "int", "profileIndication", "", "void"), 81);
        q = factory.a("method-execution", (Signature) factory.a("1", "getProfile_compatibility", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "", "", "", "int"), 85);
        u = factory.a("method-execution", (Signature) factory.a("1", "setProfile_compatibility", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "int", "profile_compatibility", "", "void"), 89);
        v = factory.a("method-execution", (Signature) factory.a("1", "getLevelIndication", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "", "", "", "int"), 93);
        w = factory.a("method-execution", (Signature) factory.a("1", "setLevelIndication", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "int", "levelIndication", "", "void"), 97);
        x = factory.a("method-execution", (Signature) factory.a("1", "getReserved1", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "", "", "", "int"), 101);
        y = factory.a("method-execution", (Signature) factory.a("1", "setReserved1", "org.mp4parser.boxes.iso14496.part15.TierInfoBox", "int", "reserved1", "", "void"), 105);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 13;
    }

    public TierInfoBox() {
        super(f3927a);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        IsoTypeWriter.b(byteBuffer, this.b);
        IsoTypeWriter.d(byteBuffer, this.c);
        IsoTypeWriter.d(byteBuffer, this.d);
        IsoTypeWriter.d(byteBuffer, this.e);
        IsoTypeWriter.d(byteBuffer, this.f);
        IsoTypeWriter.b(byteBuffer, this.g);
        IsoTypeWriter.b(byteBuffer, this.h);
        IsoTypeWriter.d(byteBuffer, (this.i << 6) + (this.j << 4) + this.k);
        IsoTypeWriter.b(byteBuffer, this.l);
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        this.b = IsoTypeReader.d(byteBuffer);
        this.c = IsoTypeReader.f(byteBuffer);
        this.d = IsoTypeReader.f(byteBuffer);
        this.e = IsoTypeReader.f(byteBuffer);
        this.f = IsoTypeReader.f(byteBuffer);
        this.g = IsoTypeReader.d(byteBuffer);
        this.h = IsoTypeReader.d(byteBuffer);
        int f2 = IsoTypeReader.f(byteBuffer);
        this.i = (f2 & 192) >> 6;
        this.j = (f2 & 48) >> 4;
        this.k = f2 & 15;
        this.l = IsoTypeReader.d(byteBuffer);
    }

    public int d() {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this));
        return this.b;
    }

    public void a(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this, Conversions.a(i2)));
        this.b = i2;
    }

    public int e() {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this));
        return this.c;
    }

    public void b(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this, Conversions.a(i2)));
        this.c = i2;
    }

    public int f() {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this));
        return this.d;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this, Conversions.a(i2)));
        this.d = i2;
    }

    public int g() {
        RequiresParseDetailAspect.a().a(Factory.a(v, (Object) this, (Object) this));
        return this.e;
    }

    public void d(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(w, (Object) this, (Object) this, Conversions.a(i2)));
        this.e = i2;
    }

    public int h() {
        RequiresParseDetailAspect.a().a(Factory.a(x, (Object) this, (Object) this));
        return this.f;
    }

    public void e(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(y, (Object) this, (Object) this, Conversions.a(i2)));
        this.f = i2;
    }

    public int i() {
        RequiresParseDetailAspect.a().a(Factory.a(z, (Object) this, (Object) this));
        return this.g;
    }

    public void f(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(A, (Object) this, (Object) this, Conversions.a(i2)));
        this.g = i2;
    }

    public int j() {
        RequiresParseDetailAspect.a().a(Factory.a(B, (Object) this, (Object) this));
        return this.h;
    }

    public void g(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(C, (Object) this, (Object) this, Conversions.a(i2)));
        this.h = i2;
    }

    public int k() {
        RequiresParseDetailAspect.a().a(Factory.a(D, (Object) this, (Object) this));
        return this.i;
    }

    public void h(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(E, (Object) this, (Object) this, Conversions.a(i2)));
        this.i = i2;
    }

    public int l() {
        RequiresParseDetailAspect.a().a(Factory.a(F, (Object) this, (Object) this));
        return this.j;
    }

    public void i(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(G, (Object) this, (Object) this, Conversions.a(i2)));
        this.j = i2;
    }

    public int m() {
        RequiresParseDetailAspect.a().a(Factory.a(H, (Object) this, (Object) this));
        return this.k;
    }

    public void j(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(I, (Object) this, (Object) this, Conversions.a(i2)));
        this.k = i2;
    }

    public int n() {
        RequiresParseDetailAspect.a().a(Factory.a(J, (Object) this, (Object) this));
        return this.l;
    }

    public void k(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(K, (Object) this, (Object) this, Conversions.a(i2)));
        this.l = i2;
    }
}
