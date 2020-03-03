package org.mp4parser.boxes.iso14496.part12;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class TrackExtendsBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3901a = "trex";
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
    private static final JoinPoint.StaticPart q = null;
    private long b;
    private long c;
    private long d;
    private long e;
    private SampleFlags f;

    static {
        k();
    }

    private static void k() {
        Factory factory = new Factory("TrackExtendsBox.java", TrackExtendsBox.class);
        g = factory.a("method-execution", (Signature) factory.a("1", "getTrackId", "org.mp4parser.boxes.iso14496.part12.TrackExtendsBox", "", "", "", "long"), 72);
        h = factory.a("method-execution", (Signature) factory.a("1", "setTrackId", "org.mp4parser.boxes.iso14496.part12.TrackExtendsBox", "long", "trackId", "", "void"), 76);
        q = factory.a("method-execution", (Signature) factory.a("1", "getDefaultSampleFlagsStr", "org.mp4parser.boxes.iso14496.part12.TrackExtendsBox", "", "", "", "java.lang.String"), 113);
        i = factory.a("method-execution", (Signature) factory.a("1", "getDefaultSampleDescriptionIndex", "org.mp4parser.boxes.iso14496.part12.TrackExtendsBox", "", "", "", "long"), 80);
        j = factory.a("method-execution", (Signature) factory.a("1", "setDefaultSampleDescriptionIndex", "org.mp4parser.boxes.iso14496.part12.TrackExtendsBox", "long", "defaultSampleDescriptionIndex", "", "void"), 84);
        k = factory.a("method-execution", (Signature) factory.a("1", "getDefaultSampleDuration", "org.mp4parser.boxes.iso14496.part12.TrackExtendsBox", "", "", "", "long"), 88);
        l = factory.a("method-execution", (Signature) factory.a("1", "setDefaultSampleDuration", "org.mp4parser.boxes.iso14496.part12.TrackExtendsBox", "long", "defaultSampleDuration", "", "void"), 92);
        m = factory.a("method-execution", (Signature) factory.a("1", "getDefaultSampleSize", "org.mp4parser.boxes.iso14496.part12.TrackExtendsBox", "", "", "", "long"), 96);
        n = factory.a("method-execution", (Signature) factory.a("1", "setDefaultSampleSize", "org.mp4parser.boxes.iso14496.part12.TrackExtendsBox", "long", "defaultSampleSize", "", "void"), 100);
        o = factory.a("method-execution", (Signature) factory.a("1", "getDefaultSampleFlags", "org.mp4parser.boxes.iso14496.part12.TrackExtendsBox", "", "", "", "org.mp4parser.boxes.iso14496.part12.SampleFlags"), 104);
        p = factory.a("method-execution", (Signature) factory.a("1", "setDefaultSampleFlags", "org.mp4parser.boxes.iso14496.part12.TrackExtendsBox", "org.mp4parser.boxes.iso14496.part12.SampleFlags", "defaultSampleFlags", "", "void"), 108);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 24;
    }

    public TrackExtendsBox() {
        super(f3901a);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.b(byteBuffer, this.b);
        IsoTypeWriter.b(byteBuffer, this.c);
        IsoTypeWriter.b(byteBuffer, this.d);
        IsoTypeWriter.b(byteBuffer, this.e);
        this.f.a(byteBuffer);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.b(byteBuffer);
        this.c = IsoTypeReader.b(byteBuffer);
        this.d = IsoTypeReader.b(byteBuffer);
        this.e = IsoTypeReader.b(byteBuffer);
        this.f = new SampleFlags(byteBuffer);
    }

    public long e() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return this.b;
    }

    public void a(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this, Conversions.a(j2)));
        this.b = j2;
    }

    public long f() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.c;
    }

    public void b(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this, Conversions.a(j2)));
        this.c = j2;
    }

    public long g() {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this));
        return this.d;
    }

    public void c(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this, Conversions.a(j2)));
        this.d = j2;
    }

    public long h() {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this));
        return this.e;
    }

    public void d(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this, Conversions.a(j2)));
        this.e = j2;
    }

    public SampleFlags i() {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this));
        return this.f;
    }

    public void a(SampleFlags sampleFlags) {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this, (Object) sampleFlags));
        this.f = sampleFlags;
    }

    public String j() {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this));
        return this.f.toString();
    }
}
