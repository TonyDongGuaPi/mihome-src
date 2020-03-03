package org.mp4parser.boxes.iso14496.part15;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;

public final class AvcConfigurationBox extends AbstractBox {
    private static final JoinPoint.StaticPart A = null;
    private static final JoinPoint.StaticPart B = null;
    private static final JoinPoint.StaticPart C = null;
    private static final JoinPoint.StaticPart D = null;
    private static final JoinPoint.StaticPart E = null;
    private static final JoinPoint.StaticPart F = null;
    private static final JoinPoint.StaticPart G = null;

    /* renamed from: a  reason: collision with root package name */
    public static final String f3916a = "avcC";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
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
    private static final JoinPoint.StaticPart u = null;
    private static final JoinPoint.StaticPart v = null;
    private static final JoinPoint.StaticPart w = null;
    private static final JoinPoint.StaticPart x = null;
    private static final JoinPoint.StaticPart y = null;
    private static final JoinPoint.StaticPart z = null;
    public AvcDecoderConfigurationRecord b = new AvcDecoderConfigurationRecord();

    static {
        q();
    }

    private static void q() {
        Factory factory = new Factory("AvcConfigurationBox.java", AvcConfigurationBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getConfigurationVersion", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "", "", "", "int"), 44);
        d = factory.a("method-execution", (Signature) factory.a("1", "setConfigurationVersion", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "int", "configurationVersion", "", "void"), 48);
        m = factory.a("method-execution", (Signature) factory.a("1", "getSequenceParameterSets", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "", "", "", "java.util.List"), 84);
        n = factory.a("method-execution", (Signature) factory.a("1", "setSequenceParameterSets", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "java.util.List", "sequenceParameterSets", "", "void"), 88);
        o = factory.a("method-execution", (Signature) factory.a("1", "getPictureParameterSets", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "", "", "", "java.util.List"), 92);
        p = factory.a("method-execution", (Signature) factory.a("1", "setPictureParameterSets", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "java.util.List", "pictureParameterSets", "", "void"), 96);
        q = factory.a("method-execution", (Signature) factory.a("1", "getChromaFormat", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "", "", "", "int"), 100);
        u = factory.a("method-execution", (Signature) factory.a("1", "setChromaFormat", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "int", "chromaFormat", "", "void"), 104);
        v = factory.a("method-execution", (Signature) factory.a("1", "getBitDepthLumaMinus8", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "", "", "", "int"), 108);
        w = factory.a("method-execution", (Signature) factory.a("1", "setBitDepthLumaMinus8", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "int", "bitDepthLumaMinus8", "", "void"), 112);
        x = factory.a("method-execution", (Signature) factory.a("1", "getBitDepthChromaMinus8", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "", "", "", "int"), 116);
        y = factory.a("method-execution", (Signature) factory.a("1", "setBitDepthChromaMinus8", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "int", "bitDepthChromaMinus8", "", "void"), 120);
        e = factory.a("method-execution", (Signature) factory.a("1", "getAvcProfileIndication", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "", "", "", "int"), 52);
        z = factory.a("method-execution", (Signature) factory.a("1", "getSequenceParameterSetExts", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "", "", "", "java.util.List"), 124);
        A = factory.a("method-execution", (Signature) factory.a("1", "setSequenceParameterSetExts", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "java.util.List", "sequenceParameterSetExts", "", "void"), 128);
        B = factory.a("method-execution", (Signature) factory.a("1", "hasExts", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "", "", "", "boolean"), 132);
        C = factory.a("method-execution", (Signature) factory.a("1", "setHasExts", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "boolean", "hasExts", "", "void"), 136);
        D = factory.a("method-execution", (Signature) factory.a("1", "getContentSize", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "", "", "", "long"), 147);
        E = factory.a("method-execution", (Signature) factory.a("1", "getContent", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "java.nio.ByteBuffer", "byteBuffer", "", "void"), 153);
        F = factory.a("method-execution", (Signature) factory.a("1", "getavcDecoderConfigurationRecord", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "", "", "", "org.mp4parser.boxes.iso14496.part15.AvcDecoderConfigurationRecord"), 158);
        G = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "", "", "", "java.lang.String"), 163);
        f = factory.a("method-execution", (Signature) factory.a("1", "setAvcProfileIndication", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "int", "avcProfileIndication", "", "void"), 56);
        g = factory.a("method-execution", (Signature) factory.a("1", "getProfileCompatibility", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "", "", "", "int"), 60);
        h = factory.a("method-execution", (Signature) factory.a("1", "setProfileCompatibility", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "int", "profileCompatibility", "", "void"), 64);
        i = factory.a("method-execution", (Signature) factory.a("1", "getAvcLevelIndication", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "", "", "", "int"), 68);
        j = factory.a("method-execution", (Signature) factory.a("1", "setAvcLevelIndication", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "int", "avcLevelIndication", "", "void"), 72);
        k = factory.a("method-execution", (Signature) factory.a("1", "getLengthSizeMinusOne", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "", "", "", "int"), 76);
        l = factory.a("method-execution", (Signature) factory.a("1", "setLengthSizeMinusOne", "org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox", "int", "lengthSizeMinusOne", "", "void"), 80);
    }

    public AvcConfigurationBox() {
        super(f3916a);
    }

    public int d() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return this.b.f3917a;
    }

    public void a(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this, Conversions.a(i2)));
        this.b.f3917a = i2;
    }

    public int e() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return this.b.b;
    }

    public void b(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this, Conversions.a(i2)));
        this.b.b = i2;
    }

    public int f() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return this.b.c;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this, Conversions.a(i2)));
        this.b.c = i2;
    }

    public int g() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.b.d;
    }

    public void d(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this, Conversions.a(i2)));
        this.b.d = i2;
    }

    public int h() {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this));
        return this.b.e;
    }

    public void e(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this, Conversions.a(i2)));
        this.b.e = i2;
    }

    public List<ByteBuffer> i() {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this));
        return Collections.unmodifiableList(this.b.f);
    }

    public void a(List<ByteBuffer> list) {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this, (Object) list));
        this.b.f = list;
    }

    public List<ByteBuffer> j() {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this));
        return Collections.unmodifiableList(this.b.g);
    }

    public void b(List<ByteBuffer> list) {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this, (Object) list));
        this.b.g = list;
    }

    public int k() {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this));
        return this.b.i;
    }

    public void f(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this, Conversions.a(i2)));
        this.b.i = i2;
    }

    public int l() {
        RequiresParseDetailAspect.a().a(Factory.a(v, (Object) this, (Object) this));
        return this.b.j;
    }

    public void g(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(w, (Object) this, (Object) this, Conversions.a(i2)));
        this.b.j = i2;
    }

    public int m() {
        RequiresParseDetailAspect.a().a(Factory.a(x, (Object) this, (Object) this));
        return this.b.k;
    }

    public void h(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(y, (Object) this, (Object) this, Conversions.a(i2)));
        this.b.k = i2;
    }

    public List<ByteBuffer> n() {
        RequiresParseDetailAspect.a().a(Factory.a(z, (Object) this, (Object) this));
        return this.b.l;
    }

    public void c(List<ByteBuffer> list) {
        RequiresParseDetailAspect.a().a(Factory.a(A, (Object) this, (Object) this, (Object) list));
        this.b.l = list;
    }

    public boolean o() {
        RequiresParseDetailAspect.a().a(Factory.a(B, (Object) this, (Object) this));
        return this.b.h;
    }

    public void a(boolean z2) {
        RequiresParseDetailAspect.a().a(Factory.a(C, (Object) this, (Object) this, Conversions.a(z2)));
        this.b.h = z2;
    }

    public void a(ByteBuffer byteBuffer) {
        this.b = new AvcDecoderConfigurationRecord(byteBuffer);
    }

    public long ai_() {
        RequiresParseDetailAspect.a().a(Factory.a(D, (Object) this, (Object) this));
        return this.b.a();
    }

    public void b(ByteBuffer byteBuffer) {
        RequiresParseDetailAspect.a().a(Factory.a(E, (Object) this, (Object) this, (Object) byteBuffer));
        this.b.a(byteBuffer);
    }

    public AvcDecoderConfigurationRecord p() {
        RequiresParseDetailAspect.a().a(Factory.a(F, (Object) this, (Object) this));
        return this.b;
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(G, (Object) this, (Object) this));
        return "AvcConfigurationBox{avcDecoderConfigurationRecord=" + this.b + Operators.BLOCK_END;
    }
}
