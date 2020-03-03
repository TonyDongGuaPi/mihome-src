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

public class TierBitRateBox extends AbstractBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3926a = "tibr";
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
    long b;
    long c;
    long d;
    long e;
    long f;
    long g;

    static {
        k();
    }

    private static void k() {
        Factory factory = new Factory("TierBitRateBox.java", TierBitRateBox.class);
        h = factory.a("method-execution", (Signature) factory.a("1", "getBaseBitRate", "org.mp4parser.boxes.iso14496.part15.TierBitRateBox", "", "", "", "long"), 52);
        i = factory.a("method-execution", (Signature) factory.a("1", "setBaseBitRate", "org.mp4parser.boxes.iso14496.part15.TierBitRateBox", "long", "baseBitRate", "", "void"), 56);
        u = factory.a("method-execution", (Signature) factory.a("1", "getTierAvgBitRate", "org.mp4parser.boxes.iso14496.part15.TierBitRateBox", "", "", "", "long"), 92);
        v = factory.a("method-execution", (Signature) factory.a("1", "setTierAvgBitRate", "org.mp4parser.boxes.iso14496.part15.TierBitRateBox", "long", "tierAvgBitRate", "", "void"), 96);
        j = factory.a("method-execution", (Signature) factory.a("1", "getMaxBitRate", "org.mp4parser.boxes.iso14496.part15.TierBitRateBox", "", "", "", "long"), 60);
        k = factory.a("method-execution", (Signature) factory.a("1", "setMaxBitRate", "org.mp4parser.boxes.iso14496.part15.TierBitRateBox", "long", "maxBitRate", "", "void"), 64);
        l = factory.a("method-execution", (Signature) factory.a("1", "getAvgBitRate", "org.mp4parser.boxes.iso14496.part15.TierBitRateBox", "", "", "", "long"), 68);
        m = factory.a("method-execution", (Signature) factory.a("1", "setAvgBitRate", "org.mp4parser.boxes.iso14496.part15.TierBitRateBox", "long", "avgBitRate", "", "void"), 72);
        n = factory.a("method-execution", (Signature) factory.a("1", "getTierBaseBitRate", "org.mp4parser.boxes.iso14496.part15.TierBitRateBox", "", "", "", "long"), 76);
        o = factory.a("method-execution", (Signature) factory.a("1", "setTierBaseBitRate", "org.mp4parser.boxes.iso14496.part15.TierBitRateBox", "long", "tierBaseBitRate", "", "void"), 80);
        p = factory.a("method-execution", (Signature) factory.a("1", "getTierMaxBitRate", "org.mp4parser.boxes.iso14496.part15.TierBitRateBox", "", "", "", "long"), 84);
        q = factory.a("method-execution", (Signature) factory.a("1", "setTierMaxBitRate", "org.mp4parser.boxes.iso14496.part15.TierBitRateBox", "long", "tierMaxBitRate", "", "void"), 88);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 24;
    }

    public TierBitRateBox() {
        super(f3926a);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        IsoTypeWriter.b(byteBuffer, this.b);
        IsoTypeWriter.b(byteBuffer, this.c);
        IsoTypeWriter.b(byteBuffer, this.d);
        IsoTypeWriter.b(byteBuffer, this.e);
        IsoTypeWriter.b(byteBuffer, this.f);
        IsoTypeWriter.b(byteBuffer, this.g);
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        this.b = IsoTypeReader.b(byteBuffer);
        this.c = IsoTypeReader.b(byteBuffer);
        this.d = IsoTypeReader.b(byteBuffer);
        this.e = IsoTypeReader.b(byteBuffer);
        this.f = IsoTypeReader.b(byteBuffer);
        this.g = IsoTypeReader.b(byteBuffer);
    }

    public long d() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        return this.b;
    }

    public void a(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this, Conversions.a(j2)));
        this.b = j2;
    }

    public long e() {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this));
        return this.c;
    }

    public void b(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this, Conversions.a(j2)));
        this.c = j2;
    }

    public long f() {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this));
        return this.d;
    }

    public void c(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this, Conversions.a(j2)));
        this.d = j2;
    }

    public long g() {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this));
        return this.e;
    }

    public void d(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this, Conversions.a(j2)));
        this.e = j2;
    }

    public long h() {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this));
        return this.f;
    }

    public void e(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this, Conversions.a(j2)));
        this.f = j2;
    }

    public long i() {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this));
        return this.g;
    }

    public void f(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(v, (Object) this, (Object) this, Conversions.a(j2)));
        this.g = j2;
    }
}
