package org.mp4parser.boxes.iso23009.part1;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import org.mp4parser.tools.Utf8;

public class EventMessageBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3937a = "emsg";
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
    private static final JoinPoint.StaticPart x = null;
    private static final JoinPoint.StaticPart y = null;
    String b;
    String c;
    long d;
    long e;
    long f;
    long g;
    byte[] h;

    static {
        l();
    }

    private static void l() {
        Factory factory = new Factory("EventMessageBox.java", EventMessageBox.class);
        i = factory.a("method-execution", (Signature) factory.a("1", "getSchemeIdUri", "org.mp4parser.boxes.iso23009.part1.EventMessageBox", "", "", "", "java.lang.String"), 59);
        j = factory.a("method-execution", (Signature) factory.a("1", "setSchemeIdUri", "org.mp4parser.boxes.iso23009.part1.EventMessageBox", "java.lang.String", "schemeIdUri", "", "void"), 63);
        v = factory.a("method-execution", (Signature) factory.a("1", "getId", "org.mp4parser.boxes.iso23009.part1.EventMessageBox", "", "", "", "long"), 99);
        w = factory.a("method-execution", (Signature) factory.a("1", "setId", "org.mp4parser.boxes.iso23009.part1.EventMessageBox", "long", "id", "", "void"), 103);
        x = factory.a("method-execution", (Signature) factory.a("1", "getMessageData", "org.mp4parser.boxes.iso23009.part1.EventMessageBox", "", "", "", "[B"), 107);
        y = factory.a("method-execution", (Signature) factory.a("1", "setMessageData", "org.mp4parser.boxes.iso23009.part1.EventMessageBox", "[B", "messageData", "", "void"), 111);
        k = factory.a("method-execution", (Signature) factory.a("1", "getValue", "org.mp4parser.boxes.iso23009.part1.EventMessageBox", "", "", "", "java.lang.String"), 67);
        l = factory.a("method-execution", (Signature) factory.a("1", "setValue", "org.mp4parser.boxes.iso23009.part1.EventMessageBox", "java.lang.String", "value", "", "void"), 71);
        m = factory.a("method-execution", (Signature) factory.a("1", "getTimescale", "org.mp4parser.boxes.iso23009.part1.EventMessageBox", "", "", "", "long"), 75);
        n = factory.a("method-execution", (Signature) factory.a("1", "setTimescale", "org.mp4parser.boxes.iso23009.part1.EventMessageBox", "long", "timescale", "", "void"), 79);
        o = factory.a("method-execution", (Signature) factory.a("1", "getPresentationTimeDelta", "org.mp4parser.boxes.iso23009.part1.EventMessageBox", "", "", "", "long"), 83);
        p = factory.a("method-execution", (Signature) factory.a("1", "setPresentationTimeDelta", "org.mp4parser.boxes.iso23009.part1.EventMessageBox", "long", "presentationTimeDelta", "", "void"), 87);
        q = factory.a("method-execution", (Signature) factory.a("1", "getEventDuration", "org.mp4parser.boxes.iso23009.part1.EventMessageBox", "", "", "", "long"), 91);
        u = factory.a("method-execution", (Signature) factory.a("1", "setEventDuration", "org.mp4parser.boxes.iso23009.part1.EventMessageBox", "long", "eventDuration", "", "void"), 95);
    }

    public EventMessageBox() {
        super(f3937a);
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.g(byteBuffer);
        this.c = IsoTypeReader.g(byteBuffer);
        this.d = IsoTypeReader.b(byteBuffer);
        this.e = IsoTypeReader.b(byteBuffer);
        this.f = IsoTypeReader.b(byteBuffer);
        this.g = IsoTypeReader.b(byteBuffer);
        this.h = new byte[byteBuffer.remaining()];
        byteBuffer.get(this.h);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.d(byteBuffer, this.b);
        IsoTypeWriter.d(byteBuffer, this.c);
        IsoTypeWriter.b(byteBuffer, this.d);
        IsoTypeWriter.b(byteBuffer, this.e);
        IsoTypeWriter.b(byteBuffer, this.f);
        IsoTypeWriter.b(byteBuffer, this.g);
        byteBuffer.put(this.h);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) (Utf8.b(this.b) + 22 + Utf8.b(this.c) + this.h.length);
    }

    public String e() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.b;
    }

    public void a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this, (Object) str));
        this.b = str;
    }

    public String f() {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this));
        return this.c;
    }

    public void b(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this, (Object) str));
        this.c = str;
    }

    public long g() {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this));
        return this.d;
    }

    public void a(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this, Conversions.a(j2)));
        this.d = j2;
    }

    public long h() {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this));
        return this.e;
    }

    public void b(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this, Conversions.a(j2)));
        this.e = j2;
    }

    public long i() {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this));
        return this.f;
    }

    public void c(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this, Conversions.a(j2)));
        this.f = j2;
    }

    public long j() {
        RequiresParseDetailAspect.a().a(Factory.a(v, (Object) this, (Object) this));
        return this.g;
    }

    public void d(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(w, (Object) this, (Object) this, Conversions.a(j2)));
        this.g = j2;
    }

    public byte[] k() {
        RequiresParseDetailAspect.a().a(Factory.a(x, (Object) this, (Object) this));
        return this.h;
    }

    public void a(byte[] bArr) {
        RequiresParseDetailAspect.a().a(Factory.a(y, (Object) this, (Object) this, (Object) bArr));
        this.h = bArr;
    }
}
