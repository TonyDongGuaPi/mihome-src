package org.mp4parser.boxes.iso14496.part12;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public final class BitRateBox extends AbstractBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3833a = "btrt";
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    private long b;
    private long c;
    private long d;

    static {
        g();
    }

    private static void g() {
        Factory factory = new Factory("BitRateBox.java", BitRateBox.class);
        e = factory.a("method-execution", (Signature) factory.a("1", "getBufferSizeDb", "org.mp4parser.boxes.iso14496.part12.BitRateBox", "", "", "", "long"), 75);
        f = factory.a("method-execution", (Signature) factory.a("1", "setBufferSizeDb", "org.mp4parser.boxes.iso14496.part12.BitRateBox", "long", "bufferSizeDb", "", "void"), 84);
        g = factory.a("method-execution", (Signature) factory.a("1", "getMaxBitrate", "org.mp4parser.boxes.iso14496.part12.BitRateBox", "", "", "", "long"), 93);
        h = factory.a("method-execution", (Signature) factory.a("1", "setMaxBitrate", "org.mp4parser.boxes.iso14496.part12.BitRateBox", "long", "maxBitrate", "", "void"), 102);
        i = factory.a("method-execution", (Signature) factory.a("1", "getAvgBitrate", "org.mp4parser.boxes.iso14496.part12.BitRateBox", "", "", "", "long"), 111);
        j = factory.a("method-execution", (Signature) factory.a("1", "setAvgBitrate", "org.mp4parser.boxes.iso14496.part12.BitRateBox", "long", "avgBitrate", "", "void"), 120);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 12;
    }

    public BitRateBox() {
        super(f3833a);
    }

    public void a(ByteBuffer byteBuffer) {
        this.b = IsoTypeReader.b(byteBuffer);
        this.c = IsoTypeReader.b(byteBuffer);
        this.d = IsoTypeReader.b(byteBuffer);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        IsoTypeWriter.b(byteBuffer, this.b);
        IsoTypeWriter.b(byteBuffer, this.c);
        IsoTypeWriter.b(byteBuffer, this.d);
    }

    public long d() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return this.b;
    }

    public void a(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this, Conversions.a(j2)));
        this.b = j2;
    }

    public long e() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return this.c;
    }

    public void b(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this, Conversions.a(j2)));
        this.c = j2;
    }

    public long f() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.d;
    }

    public void c(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this, Conversions.a(j2)));
        this.d = j2;
    }
}
