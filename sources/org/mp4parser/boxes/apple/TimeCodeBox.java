package org.mp4parser.boxes.apple;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.Collections;
import java.util.List;
import org.mp4parser.Box;
import org.mp4parser.Container;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.boxes.sampleentry.SampleEntry;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class TimeCodeBox extends AbstractBox implements Container, SampleEntry {
    private static final JoinPoint.StaticPart A = null;
    private static final JoinPoint.StaticPart B = null;
    private static final JoinPoint.StaticPart C = null;
    private static final JoinPoint.StaticPart D = null;
    private static final JoinPoint.StaticPart E = null;
    private static final JoinPoint.StaticPart F = null;
    private static final JoinPoint.StaticPart G = null;
    private static final JoinPoint.StaticPart H = null;

    /* renamed from: a  reason: collision with root package name */
    public static final String f3800a = "tmcd";
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
    private static final JoinPoint.StaticPart z = null;
    int b;
    int c;
    int d;
    int e;
    int f;
    long g;
    int h;
    byte[] i = new byte[0];

    static {
        l();
    }

    private static void l() {
        Factory factory = new Factory("TimeCodeBox.java", TimeCodeBox.class);
        j = factory.a("method-execution", (Signature) factory.a("1", "getDataReferenceIndex", "org.mp4parser.boxes.apple.TimeCodeBox", "", "", "", "int"), 87);
        k = factory.a("method-execution", (Signature) factory.a("1", "setDataReferenceIndex", "org.mp4parser.boxes.apple.TimeCodeBox", "int", "dataReferenceIndex", "", "void"), 91);
        w = factory.a("method-execution", (Signature) factory.a("1", "setReserved1", "org.mp4parser.boxes.apple.TimeCodeBox", "int", "reserved1", "", "void"), 136);
        x = factory.a("method-execution", (Signature) factory.a("1", "getReserved2", "org.mp4parser.boxes.apple.TimeCodeBox", "", "", "", "int"), 140);
        y = factory.a("method-execution", (Signature) factory.a("1", "setReserved2", "org.mp4parser.boxes.apple.TimeCodeBox", "int", "reserved2", "", "void"), 144);
        z = factory.a("method-execution", (Signature) factory.a("1", "getFlags", "org.mp4parser.boxes.apple.TimeCodeBox", "", "", "", "long"), 148);
        A = factory.a("method-execution", (Signature) factory.a("1", "setFlags", "org.mp4parser.boxes.apple.TimeCodeBox", "long", ApiConst.K, "", "void"), 152);
        B = factory.a("method-execution", (Signature) factory.a("1", "getRest", "org.mp4parser.boxes.apple.TimeCodeBox", "", "", "", "[B"), 156);
        C = factory.a("method-execution", (Signature) factory.a("1", "setRest", "org.mp4parser.boxes.apple.TimeCodeBox", "[B", "rest", "", "void"), 160);
        D = factory.a("method-execution", (Signature) factory.a("1", "getBoxes", "org.mp4parser.boxes.apple.TimeCodeBox", "", "", "", "java.util.List"), 165);
        E = factory.a("method-execution", (Signature) factory.a("1", "setBoxes", "org.mp4parser.boxes.apple.TimeCodeBox", "java.util.List", "boxes", "", "void"), 169);
        F = factory.a("method-execution", (Signature) factory.a("1", "getBoxes", "org.mp4parser.boxes.apple.TimeCodeBox", "java.lang.Class", "clazz", "", "java.util.List"), 173);
        l = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.apple.TimeCodeBox", "", "", "", "java.lang.String"), 97);
        G = factory.a("method-execution", (Signature) factory.a("1", "getBoxes", "org.mp4parser.boxes.apple.TimeCodeBox", "java.lang.Class:boolean", "clazz:recursive", "", "java.util.List"), 177);
        H = factory.a("method-execution", (Signature) factory.a("1", "writeContainer", "org.mp4parser.boxes.apple.TimeCodeBox", "java.nio.channels.WritableByteChannel", "bb", "java.io.IOException", "void"), 181);
        m = factory.a("method-execution", (Signature) factory.a("1", "getTimeScale", "org.mp4parser.boxes.apple.TimeCodeBox", "", "", "", "int"), 108);
        n = factory.a("method-execution", (Signature) factory.a("1", "setTimeScale", "org.mp4parser.boxes.apple.TimeCodeBox", "int", "timeScale", "", "void"), 112);
        o = factory.a("method-execution", (Signature) factory.a("1", "getFrameDuration", "org.mp4parser.boxes.apple.TimeCodeBox", "", "", "", "int"), 116);
        p = factory.a("method-execution", (Signature) factory.a("1", "setFrameDuration", "org.mp4parser.boxes.apple.TimeCodeBox", "int", "frameDuration", "", "void"), 120);
        q = factory.a("method-execution", (Signature) factory.a("1", "getNumberOfFrames", "org.mp4parser.boxes.apple.TimeCodeBox", "", "", "", "int"), 124);
        u = factory.a("method-execution", (Signature) factory.a("1", "setNumberOfFrames", "org.mp4parser.boxes.apple.TimeCodeBox", "int", "numberOfFrames", "", "void"), 128);
        v = factory.a("method-execution", (Signature) factory.a("1", "getReserved1", "org.mp4parser.boxes.apple.TimeCodeBox", "", "", "", "int"), 132);
    }

    public TimeCodeBox() {
        super(f3800a);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) (this.i.length + 28);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        byteBuffer.put(new byte[6]);
        IsoTypeWriter.b(byteBuffer, this.h);
        byteBuffer.putInt(this.e);
        IsoTypeWriter.b(byteBuffer, this.g);
        byteBuffer.putInt(this.b);
        byteBuffer.putInt(this.c);
        IsoTypeWriter.d(byteBuffer, this.d);
        IsoTypeWriter.a(byteBuffer, this.f);
        byteBuffer.put(this.i);
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        byteBuffer.position(6);
        this.h = IsoTypeReader.d(byteBuffer);
        this.e = byteBuffer.getInt();
        this.g = IsoTypeReader.b(byteBuffer);
        this.b = byteBuffer.getInt();
        this.c = byteBuffer.getInt();
        this.d = IsoTypeReader.f(byteBuffer);
        this.f = IsoTypeReader.c(byteBuffer);
        this.i = new byte[byteBuffer.remaining()];
        byteBuffer.get(this.i);
    }

    public int d() {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this));
        return this.h;
    }

    public void a(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this, Conversions.a(i2)));
        this.h = i2;
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this));
        return "TimeCodeBox{timeScale=" + this.b + ", frameDuration=" + this.c + ", numberOfFrames=" + this.d + ", reserved1=" + this.e + ", reserved2=" + this.f + ", flags=" + this.g + Operators.BLOCK_END;
    }

    public int e() {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this));
        return this.b;
    }

    public void b(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this, Conversions.a(i2)));
        this.b = i2;
    }

    public int f() {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this));
        return this.c;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this, Conversions.a(i2)));
        this.c = i2;
    }

    public int g() {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this));
        return this.d;
    }

    public void d(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this, Conversions.a(i2)));
        this.d = i2;
    }

    public int h() {
        RequiresParseDetailAspect.a().a(Factory.a(v, (Object) this, (Object) this));
        return this.e;
    }

    public void e(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(w, (Object) this, (Object) this, Conversions.a(i2)));
        this.e = i2;
    }

    public int i() {
        RequiresParseDetailAspect.a().a(Factory.a(x, (Object) this, (Object) this));
        return this.f;
    }

    public void f(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(y, (Object) this, (Object) this, Conversions.a(i2)));
        this.f = i2;
    }

    public long j() {
        RequiresParseDetailAspect.a().a(Factory.a(z, (Object) this, (Object) this));
        return this.g;
    }

    public void a(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(A, (Object) this, (Object) this, Conversions.a(j2)));
        this.g = j2;
    }

    public byte[] k() {
        RequiresParseDetailAspect.a().a(Factory.a(B, (Object) this, (Object) this));
        return this.i;
    }

    public void a(byte[] bArr) {
        RequiresParseDetailAspect.a().a(Factory.a(C, (Object) this, (Object) this, (Object) bArr));
        this.i = bArr;
    }

    public List<Box> a() {
        RequiresParseDetailAspect.a().a(Factory.a(D, (Object) this, (Object) this));
        return Collections.emptyList();
    }

    public void a(List<? extends Box> list) {
        RequiresParseDetailAspect.a().a(Factory.a(E, (Object) this, (Object) this, (Object) list));
        throw new RuntimeException("Time Code Box doesn't accept any children");
    }

    public <T extends Box> List<T> a(Class<T> cls) {
        RequiresParseDetailAspect.a().a(Factory.a(F, (Object) this, (Object) this, (Object) cls));
        return Collections.emptyList();
    }

    public <T extends Box> List<T> a(Class<T> cls, boolean z2) {
        RequiresParseDetailAspect.a().a(Factory.a(G, (Object) this, (Object) this, (Object) cls, Conversions.a(z2)));
        return Collections.emptyList();
    }

    public void a(WritableByteChannel writableByteChannel) throws IOException {
        RequiresParseDetailAspect.a().a(Factory.a(H, (Object) this, (Object) this, (Object) writableByteChannel));
    }
}
