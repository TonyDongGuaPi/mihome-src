package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.IsoFile;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class SampleAuxiliaryInformationSizesBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3877a = "saiz";
    static final /* synthetic */ boolean b = (!SampleAuxiliaryInformationSizesBox.class.desiredAssertionStatus());
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
    private short c;
    private short[] d = new short[0];
    private int e;
    private String f;
    private String g;

    private static void k() {
        Factory factory = new Factory("SampleAuxiliaryInformationSizesBox.java", SampleAuxiliaryInformationSizesBox.class);
        h = factory.a("method-execution", (Signature) factory.a("1", "getSize", "org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationSizesBox", "int", "index", "", "short"), 55);
        i = factory.a("method-execution", (Signature) factory.a("1", "getAuxInfoType", "org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationSizesBox", "", "", "", "java.lang.String"), 104);
        u = factory.a("method-execution", (Signature) factory.a("1", "setSampleCount", "org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationSizesBox", "int", "sampleCount", "", "void"), 144);
        v = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationSizesBox", "", "", "", "java.lang.String"), 149);
        j = factory.a("method-execution", (Signature) factory.a("1", "setAuxInfoType", "org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationSizesBox", "java.lang.String", "auxInfoType", "", "void"), 108);
        k = factory.a("method-execution", (Signature) factory.a("1", "getAuxInfoTypeParameter", "org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationSizesBox", "", "", "", "java.lang.String"), 112);
        l = factory.a("method-execution", (Signature) factory.a("1", "setAuxInfoTypeParameter", "org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationSizesBox", "java.lang.String", "auxInfoTypeParameter", "", "void"), 116);
        m = factory.a("method-execution", (Signature) factory.a("1", "getDefaultSampleInfoSize", "org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationSizesBox", "", "", "", "int"), 120);
        n = factory.a("method-execution", (Signature) factory.a("1", "setDefaultSampleInfoSize", "org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationSizesBox", "int", "defaultSampleInfoSize", "", "void"), 124);
        o = factory.a("method-execution", (Signature) factory.a("1", "getSampleInfoSizes", "org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationSizesBox", "", "", "", "[S"), 129);
        p = factory.a("method-execution", (Signature) factory.a("1", "setSampleInfoSizes", "org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationSizesBox", "[S", "sampleInfoSizes", "", "void"), 135);
        q = factory.a("method-execution", (Signature) factory.a("1", "getSampleCount", "org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationSizesBox", "", "", "", "int"), 140);
    }

    static {
        k();
    }

    public SampleAuxiliaryInformationSizesBox() {
        super(f3877a);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) (((d() & 1) == 1 ? 12 : 4) + 5 + (this.c == 0 ? this.d.length : 0));
    }

    public short c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this, Conversions.a(i2)));
        if (g() == 0) {
            return this.d[i2];
        }
        return this.c;
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        if ((d() & 1) == 1) {
            byteBuffer.put(IsoFile.a(this.f));
            byteBuffer.put(IsoFile.a(this.g));
        }
        IsoTypeWriter.d(byteBuffer, (int) this.c);
        if (this.c == 0) {
            IsoTypeWriter.b(byteBuffer, (long) this.d.length);
            for (short d2 : this.d) {
                IsoTypeWriter.d(byteBuffer, (int) d2);
            }
            return;
        }
        IsoTypeWriter.b(byteBuffer, (long) this.e);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        if ((d() & 1) == 1) {
            this.f = IsoTypeReader.m(byteBuffer);
            this.g = IsoTypeReader.m(byteBuffer);
        }
        this.c = (short) IsoTypeReader.f(byteBuffer);
        this.e = CastUtils.a(IsoTypeReader.b(byteBuffer));
        if (this.c == 0) {
            this.d = new short[this.e];
            for (int i2 = 0; i2 < this.e; i2++) {
                this.d[i2] = (short) IsoTypeReader.f(byteBuffer);
            }
        }
    }

    public String e() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.f;
    }

    public void a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this, (Object) str));
        this.f = str;
    }

    public String f() {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this));
        return this.g;
    }

    public void b(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this, (Object) str));
        this.g = str;
    }

    public int g() {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this));
        return this.c;
    }

    public void d(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this, Conversions.a(i2)));
        if (b || i2 <= 255) {
            this.c = (short) i2;
            return;
        }
        throw new AssertionError();
    }

    public short[] h() {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this));
        short[] sArr = new short[this.d.length];
        System.arraycopy(this.d, 0, sArr, 0, this.d.length);
        return sArr;
    }

    public void a(short[] sArr) {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this, (Object) sArr));
        this.d = new short[sArr.length];
        System.arraycopy(sArr, 0, this.d, 0, sArr.length);
    }

    public int i() {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this));
        return this.e;
    }

    public void e(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this, Conversions.a(i2)));
        this.e = i2;
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(v, (Object) this, (Object) this));
        return "SampleAuxiliaryInformationSizesBox{defaultSampleInfoSize=" + this.c + ", sampleCount=" + this.e + ", auxInfoType='" + this.f + Operators.SINGLE_QUOTE + ", auxInfoTypeParameter='" + this.g + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
