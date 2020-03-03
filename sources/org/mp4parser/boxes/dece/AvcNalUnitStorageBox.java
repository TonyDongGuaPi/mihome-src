package org.mp4parser.boxes.dece;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.List;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox;
import org.mp4parser.boxes.iso14496.part15.AvcDecoderConfigurationRecord;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;

public class AvcNalUnitStorageBox extends AbstractBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3807a = "avcn";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    AvcDecoderConfigurationRecord b;

    static {
        i();
    }

    private static void i() {
        Factory factory = new Factory("AvcNalUnitStorageBox.java", AvcNalUnitStorageBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getAvcDecoderConfigurationRecord", "org.mp4parser.boxes.dece.AvcNalUnitStorageBox", "", "", "", "org.mp4parser.boxes.iso14496.part15.AvcDecoderConfigurationRecord"), 44);
        d = factory.a("method-execution", (Signature) factory.a("1", "getLengthSizeMinusOne", "org.mp4parser.boxes.dece.AvcNalUnitStorageBox", "", "", "", "int"), 49);
        e = factory.a("method-execution", (Signature) factory.a("1", "getSequenceParameterSetsAsStrings", "org.mp4parser.boxes.dece.AvcNalUnitStorageBox", "", "", "", "java.util.List"), 53);
        f = factory.a("method-execution", (Signature) factory.a("1", "getSequenceParameterSetExtsAsStrings", "org.mp4parser.boxes.dece.AvcNalUnitStorageBox", "", "", "", "java.util.List"), 57);
        g = factory.a("method-execution", (Signature) factory.a("1", "getPictureParameterSetsAsStrings", "org.mp4parser.boxes.dece.AvcNalUnitStorageBox", "", "", "", "java.util.List"), 61);
        h = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.dece.AvcNalUnitStorageBox", "", "", "", "java.lang.String"), 81);
    }

    public AvcNalUnitStorageBox() {
        super(f3807a);
    }

    public AvcNalUnitStorageBox(AvcConfigurationBox avcConfigurationBox) {
        super(f3807a);
        this.b = avcConfigurationBox.p();
    }

    public AvcDecoderConfigurationRecord d() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return this.b;
    }

    public int e() {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this));
        return this.b.e;
    }

    public List<String> f() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return this.b.b();
    }

    public List<String> g() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.b.c();
    }

    public List<String> h() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return this.b.d();
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return this.b.a();
    }

    public void a(ByteBuffer byteBuffer) {
        this.b = new AvcDecoderConfigurationRecord(byteBuffer);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        this.b.a(byteBuffer);
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        return "AvcNalUnitStorageBox{SPS=" + this.b.b() + ",PPS=" + this.b.d() + ",lengthSize=" + (this.b.e + 1) + Operators.BLOCK_END;
    }
}
