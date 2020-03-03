package org.mp4parser.boxes.dolby;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitReaderBuffer;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitWriterBuffer;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;

public class AC3SpecificBox extends AbstractBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3813a = "dac3";
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
    int b;
    int c;
    int d;
    int e;
    int f;
    int g;
    int h;

    static {
        k();
    }

    private static void k() {
        Factory factory = new Factory("AC3SpecificBox.java", AC3SpecificBox.class);
        i = factory.a("method-execution", (Signature) factory.a("1", "getFscod", "org.mp4parser.boxes.dolby.AC3SpecificBox", "", "", "", "int"), 55);
        j = factory.a("method-execution", (Signature) factory.a("1", "setFscod", "org.mp4parser.boxes.dolby.AC3SpecificBox", "int", "fscod", "", "void"), 59);
        v = factory.a("method-execution", (Signature) factory.a("1", "getBitRateCode", "org.mp4parser.boxes.dolby.AC3SpecificBox", "", "", "", "int"), 95);
        w = factory.a("method-execution", (Signature) factory.a("1", "setBitRateCode", "org.mp4parser.boxes.dolby.AC3SpecificBox", "int", "bitRateCode", "", "void"), 99);
        x = factory.a("method-execution", (Signature) factory.a("1", "getReserved", "org.mp4parser.boxes.dolby.AC3SpecificBox", "", "", "", "int"), 103);
        y = factory.a("method-execution", (Signature) factory.a("1", "setReserved", "org.mp4parser.boxes.dolby.AC3SpecificBox", "int", "reserved", "", "void"), 107);
        z = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.dolby.AC3SpecificBox", "", "", "", "java.lang.String"), 112);
        k = factory.a("method-execution", (Signature) factory.a("1", "getBsid", "org.mp4parser.boxes.dolby.AC3SpecificBox", "", "", "", "int"), 63);
        l = factory.a("method-execution", (Signature) factory.a("1", "setBsid", "org.mp4parser.boxes.dolby.AC3SpecificBox", "int", "bsid", "", "void"), 67);
        m = factory.a("method-execution", (Signature) factory.a("1", "getBsmod", "org.mp4parser.boxes.dolby.AC3SpecificBox", "", "", "", "int"), 71);
        n = factory.a("method-execution", (Signature) factory.a("1", "setBsmod", "org.mp4parser.boxes.dolby.AC3SpecificBox", "int", "bsmod", "", "void"), 75);
        o = factory.a("method-execution", (Signature) factory.a("1", "getAcmod", "org.mp4parser.boxes.dolby.AC3SpecificBox", "", "", "", "int"), 79);
        p = factory.a("method-execution", (Signature) factory.a("1", "setAcmod", "org.mp4parser.boxes.dolby.AC3SpecificBox", "int", "acmod", "", "void"), 83);
        q = factory.a("method-execution", (Signature) factory.a("1", "getLfeon", "org.mp4parser.boxes.dolby.AC3SpecificBox", "", "", "", "int"), 87);
        u = factory.a("method-execution", (Signature) factory.a("1", "setLfeon", "org.mp4parser.boxes.dolby.AC3SpecificBox", "int", "lfeon", "", "void"), 91);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 3;
    }

    public AC3SpecificBox() {
        super(f3813a);
    }

    public void a(ByteBuffer byteBuffer) {
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(byteBuffer);
        this.b = bitReaderBuffer.a(2);
        this.c = bitReaderBuffer.a(5);
        this.d = bitReaderBuffer.a(3);
        this.e = bitReaderBuffer.a(3);
        this.f = bitReaderBuffer.a(1);
        this.g = bitReaderBuffer.a(5);
        this.h = bitReaderBuffer.a(5);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        BitWriterBuffer bitWriterBuffer = new BitWriterBuffer(byteBuffer);
        bitWriterBuffer.a(this.b, 2);
        bitWriterBuffer.a(this.c, 5);
        bitWriterBuffer.a(this.d, 3);
        bitWriterBuffer.a(this.e, 3);
        bitWriterBuffer.a(this.f, 1);
        bitWriterBuffer.a(this.g, 5);
        bitWriterBuffer.a(this.h, 5);
    }

    public int d() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.b;
    }

    public void a(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this, Conversions.a(i2)));
        this.b = i2;
    }

    public int e() {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this));
        return this.c;
    }

    public void b(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this, Conversions.a(i2)));
        this.c = i2;
    }

    public int f() {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this));
        return this.d;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this, Conversions.a(i2)));
        this.d = i2;
    }

    public int g() {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this));
        return this.e;
    }

    public void d(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this, Conversions.a(i2)));
        this.e = i2;
    }

    public int h() {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this));
        return this.f;
    }

    public void e(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this, Conversions.a(i2)));
        this.f = i2;
    }

    public int i() {
        RequiresParseDetailAspect.a().a(Factory.a(v, (Object) this, (Object) this));
        return this.g;
    }

    public void f(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(w, (Object) this, (Object) this, Conversions.a(i2)));
        this.g = i2;
    }

    public int j() {
        RequiresParseDetailAspect.a().a(Factory.a(x, (Object) this, (Object) this));
        return this.h;
    }

    public void g(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(y, (Object) this, (Object) this, Conversions.a(i2)));
        this.h = i2;
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(z, (Object) this, (Object) this));
        return "AC3SpecificBox{fscod=" + this.b + ", bsid=" + this.c + ", bsmod=" + this.d + ", acmod=" + this.e + ", lfeon=" + this.f + ", bitRateCode=" + this.g + ", reserved=" + this.h + Operators.BLOCK_END;
    }
}
