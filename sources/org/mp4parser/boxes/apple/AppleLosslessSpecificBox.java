package org.mp4parser.boxes.apple;

import com.facebook.imagepipeline.producers.DecodeProducer;
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

public final class AppleLosslessSpecificBox extends AbstractFullBox {
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
    public static final String f3790a = "alac";
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
    private long b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private long j;
    private long k;
    private long l;

    static {
        p();
    }

    private static void p() {
        Factory factory = new Factory("AppleLosslessSpecificBox.java", AppleLosslessSpecificBox.class);
        m = factory.a("method-execution", (Signature) factory.a("1", "getMaxSamplePerFrame", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "", "", "", "long"), 38);
        n = factory.a("method-execution", (Signature) factory.a("1", "setMaxSamplePerFrame", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "int", "maxSamplePerFrame", "", "void"), 42);
        z = factory.a("method-execution", (Signature) factory.a("1", "getKModifier", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "", "", "", "int"), 78);
        A = factory.a("method-execution", (Signature) factory.a("1", "setKModifier", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "int", "kModifier", "", "void"), 82);
        B = factory.a("method-execution", (Signature) factory.a("1", "getChannels", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "", "", "", "int"), 86);
        C = factory.a("method-execution", (Signature) factory.a("1", "setChannels", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "int", MibiConstants.gp, "", "void"), 90);
        D = factory.a("method-execution", (Signature) factory.a("1", "getUnknown2", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "", "", "", "int"), 94);
        E = factory.a("method-execution", (Signature) factory.a("1", "setUnknown2", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "int", "unknown2", "", "void"), 98);
        F = factory.a("method-execution", (Signature) factory.a("1", "getMaxCodedFrameSize", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "", "", "", "long"), 102);
        G = factory.a("method-execution", (Signature) factory.a("1", "setMaxCodedFrameSize", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "int", "maxCodedFrameSize", "", "void"), 106);
        H = factory.a("method-execution", (Signature) factory.a("1", "getBitRate", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "", "", "", "long"), 110);
        I = factory.a("method-execution", (Signature) factory.a("1", "setBitRate", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "int", "bitRate", "", "void"), 114);
        o = factory.a("method-execution", (Signature) factory.a("1", "getUnknown1", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "", "", "", "int"), 46);
        J = factory.a("method-execution", (Signature) factory.a("1", "getSampleRate", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "", "", "", "long"), 118);
        K = factory.a("method-execution", (Signature) factory.a("1", "setSampleRate", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "int", "sampleRate", "", "void"), 122);
        p = factory.a("method-execution", (Signature) factory.a("1", "setUnknown1", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "int", "unknown1", "", "void"), 50);
        q = factory.a("method-execution", (Signature) factory.a("1", "getSampleSize", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "", "", "", "int"), 54);
        u = factory.a("method-execution", (Signature) factory.a("1", "setSampleSize", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "int", DecodeProducer.SAMPLE_SIZE, "", "void"), 58);
        v = factory.a("method-execution", (Signature) factory.a("1", "getHistoryMult", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "", "", "", "int"), 62);
        w = factory.a("method-execution", (Signature) factory.a("1", "setHistoryMult", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "int", "historyMult", "", "void"), 66);
        x = factory.a("method-execution", (Signature) factory.a("1", "getInitialHistory", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "", "", "", "int"), 70);
        y = factory.a("method-execution", (Signature) factory.a("1", "setInitialHistory", "org.mp4parser.boxes.apple.AppleLosslessSpecificBox", "int", "initialHistory", "", "void"), 74);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 28;
    }

    public AppleLosslessSpecificBox() {
        super("alac");
    }

    public long e() {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this));
        return this.b;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this, Conversions.a(i2)));
        this.b = (long) i2;
    }

    public int f() {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this));
        return this.c;
    }

    public void d(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this, Conversions.a(i2)));
        this.c = i2;
    }

    public int g() {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this));
        return this.d;
    }

    public void e(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this, Conversions.a(i2)));
        this.d = i2;
    }

    public int h() {
        RequiresParseDetailAspect.a().a(Factory.a(v, (Object) this, (Object) this));
        return this.e;
    }

    public void f(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(w, (Object) this, (Object) this, Conversions.a(i2)));
        this.e = i2;
    }

    public int i() {
        RequiresParseDetailAspect.a().a(Factory.a(x, (Object) this, (Object) this));
        return this.f;
    }

    public void g(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(y, (Object) this, (Object) this, Conversions.a(i2)));
        this.f = i2;
    }

    public int j() {
        RequiresParseDetailAspect.a().a(Factory.a(z, (Object) this, (Object) this));
        return this.g;
    }

    public void h(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(A, (Object) this, (Object) this, Conversions.a(i2)));
        this.g = i2;
    }

    public int k() {
        RequiresParseDetailAspect.a().a(Factory.a(B, (Object) this, (Object) this));
        return this.h;
    }

    public void i(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(C, (Object) this, (Object) this, Conversions.a(i2)));
        this.h = i2;
    }

    public int l() {
        RequiresParseDetailAspect.a().a(Factory.a(D, (Object) this, (Object) this));
        return this.i;
    }

    public void j(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(E, (Object) this, (Object) this, Conversions.a(i2)));
        this.i = i2;
    }

    public long m() {
        RequiresParseDetailAspect.a().a(Factory.a(F, (Object) this, (Object) this));
        return this.j;
    }

    public void k(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(G, (Object) this, (Object) this, Conversions.a(i2)));
        this.j = (long) i2;
    }

    public long n() {
        RequiresParseDetailAspect.a().a(Factory.a(H, (Object) this, (Object) this));
        return this.k;
    }

    public void l(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(I, (Object) this, (Object) this, Conversions.a(i2)));
        this.k = (long) i2;
    }

    public long o() {
        RequiresParseDetailAspect.a().a(Factory.a(J, (Object) this, (Object) this));
        return this.l;
    }

    public void m(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(K, (Object) this, (Object) this, Conversions.a(i2)));
        this.l = (long) i2;
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.b(byteBuffer);
        this.c = IsoTypeReader.f(byteBuffer);
        this.d = IsoTypeReader.f(byteBuffer);
        this.e = IsoTypeReader.f(byteBuffer);
        this.f = IsoTypeReader.f(byteBuffer);
        this.g = IsoTypeReader.f(byteBuffer);
        this.h = IsoTypeReader.f(byteBuffer);
        this.i = IsoTypeReader.d(byteBuffer);
        this.j = IsoTypeReader.b(byteBuffer);
        this.k = IsoTypeReader.b(byteBuffer);
        this.l = IsoTypeReader.b(byteBuffer);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.b(byteBuffer, this.b);
        IsoTypeWriter.d(byteBuffer, this.c);
        IsoTypeWriter.d(byteBuffer, this.d);
        IsoTypeWriter.d(byteBuffer, this.e);
        IsoTypeWriter.d(byteBuffer, this.f);
        IsoTypeWriter.d(byteBuffer, this.g);
        IsoTypeWriter.d(byteBuffer, this.h);
        IsoTypeWriter.b(byteBuffer, this.i);
        IsoTypeWriter.b(byteBuffer, this.j);
        IsoTypeWriter.b(byteBuffer, this.k);
        IsoTypeWriter.b(byteBuffer, this.l);
    }
}
