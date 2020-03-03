package org.mp4parser.boxes.iso14496.part12;

import java.nio.ByteBuffer;
import org.mp4parser.IsoFile;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class SampleAuxiliaryInformationOffsetsBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3876a = "saio";
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    private long[] b = new long[0];
    private String c;
    private String d;

    static {
        h();
    }

    private static void h() {
        Factory factory = new Factory("SampleAuxiliaryInformationOffsetsBox.java", SampleAuxiliaryInformationOffsetsBox.class);
        e = factory.a("method-execution", (Signature) factory.a("1", "getAuxInfoType", "org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationOffsetsBox", "", "", "", "java.lang.String"), 106);
        f = factory.a("method-execution", (Signature) factory.a("1", "setAuxInfoType", "org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationOffsetsBox", "java.lang.String", "auxInfoType", "", "void"), 110);
        g = factory.a("method-execution", (Signature) factory.a("1", "getAuxInfoTypeParameter", "org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationOffsetsBox", "", "", "", "java.lang.String"), 114);
        h = factory.a("method-execution", (Signature) factory.a("1", "setAuxInfoTypeParameter", "org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationOffsetsBox", "java.lang.String", "auxInfoTypeParameter", "", "void"), 118);
        i = factory.a("method-execution", (Signature) factory.a("1", "getOffsets", "org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationOffsetsBox", "", "", "", "[J"), 122);
        j = factory.a("method-execution", (Signature) factory.a("1", "setOffsets", "org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationOffsetsBox", "[J", "offsets", "", "void"), 126);
    }

    public SampleAuxiliaryInformationOffsetsBox() {
        super(f3876a);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        int i2 = 8;
        int length = (ag_() == 0 ? this.b.length * 4 : this.b.length * 8) + 8;
        if ((d() & 1) != 1) {
            i2 = 0;
        }
        return (long) (length + i2);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        if ((d() & 1) == 1) {
            byteBuffer.put(IsoFile.a(this.c));
            byteBuffer.put(IsoFile.a(this.d));
        }
        IsoTypeWriter.b(byteBuffer, (long) this.b.length);
        for (long valueOf : this.b) {
            Long valueOf2 = Long.valueOf(valueOf);
            if (ag_() == 0) {
                IsoTypeWriter.b(byteBuffer, valueOf2.longValue());
            } else {
                IsoTypeWriter.a(byteBuffer, valueOf2.longValue());
            }
        }
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        if ((d() & 1) == 1) {
            this.c = IsoTypeReader.m(byteBuffer);
            this.d = IsoTypeReader.m(byteBuffer);
        }
        int a2 = CastUtils.a(IsoTypeReader.b(byteBuffer));
        this.b = new long[a2];
        for (int i2 = 0; i2 < a2; i2++) {
            if (ag_() == 0) {
                this.b[i2] = IsoTypeReader.b(byteBuffer);
            } else {
                this.b[i2] = IsoTypeReader.h(byteBuffer);
            }
        }
    }

    public String e() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return this.c;
    }

    public void a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this, (Object) str));
        this.c = str;
    }

    public String f() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return this.d;
    }

    public void b(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this, (Object) str));
        this.d = str;
    }

    public long[] g() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.b;
    }

    public void a(long[] jArr) {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this, (Object) jArr));
        this.b = jArr;
    }
}
