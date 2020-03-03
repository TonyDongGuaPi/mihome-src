package org.mp4parser.boxes.iso14496.part15;

import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import java.nio.ByteBuffer;
import java.util.List;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.boxes.iso14496.part15.HevcDecoderConfigurationRecord;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;

public class HevcConfigurationBox extends AbstractBox {
    private static final JoinPoint.StaticPart A = null;

    /* renamed from: a  reason: collision with root package name */
    public static final String f3918a = "hvcC";
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
    private HevcDecoderConfigurationRecord b = new HevcDecoderConfigurationRecord();

    static {
        y();
    }

    private static void y() {
        Factory factory = new Factory("HevcConfigurationBox.java", HevcConfigurationBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getHevcDecoderConfigurationRecord", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "", "", "", "org.mp4parser.boxes.iso14496.part15.HevcDecoderConfigurationRecord"), 37);
        d = factory.a("method-execution", (Signature) factory.a("1", "setHevcDecoderConfigurationRecord", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "org.mp4parser.boxes.iso14496.part15.HevcDecoderConfigurationRecord", "hevcDecoderConfigurationRecord", "", "void"), 41);
        m = factory.a("method-execution", (Signature) factory.a("1", "getGeneral_level_idc", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "", "", "", "int"), 89);
        n = factory.a("method-execution", (Signature) factory.a("1", "getMin_spatial_segmentation_idc", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "", "", "", "int"), 93);
        o = factory.a("method-execution", (Signature) factory.a("1", "getParallelismType", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "", "", "", "int"), 97);
        p = factory.a("method-execution", (Signature) factory.a("1", "getChromaFormat", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "", "", "", "int"), 101);
        q = factory.a("method-execution", (Signature) factory.a("1", "getBitDepthLumaMinus8", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "", "", "", "int"), 105);
        u = factory.a("method-execution", (Signature) factory.a("1", "getBitDepthChromaMinus8", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "", "", "", "int"), 109);
        v = factory.a("method-execution", (Signature) factory.a("1", "getAvgFrameRate", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "", "", "", "int"), 113);
        w = factory.a("method-execution", (Signature) factory.a("1", "getNumTemporalLayers", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "", "", "", "int"), 117);
        x = factory.a("method-execution", (Signature) factory.a("1", "getLengthSizeMinusOne", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "", "", "", "int"), 121);
        y = factory.a("method-execution", (Signature) factory.a("1", "isTemporalIdNested", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "", "", "", "boolean"), 125);
        e = factory.a("method-execution", (Signature) factory.a("1", "equals", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "java.lang.Object", DeviceTagInterface.q, "", "boolean"), 46);
        z = factory.a("method-execution", (Signature) factory.a("1", "getConstantFrameRate", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "", "", "", "int"), 129);
        A = factory.a("method-execution", (Signature) factory.a("1", "getArrays", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "", "", "", "java.util.List"), 133);
        f = factory.a("method-execution", (Signature) factory.a("1", "hashCode", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "", "", "", "int"), 59);
        g = factory.a("method-execution", (Signature) factory.a("1", "getConfigurationVersion", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "", "", "", "int"), 64);
        h = factory.a("method-execution", (Signature) factory.a("1", "getGeneral_profile_space", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "", "", "", "int"), 68);
        i = factory.a("method-execution", (Signature) factory.a("1", "isGeneral_tier_flag", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "", "", "", "boolean"), 72);
        j = factory.a("method-execution", (Signature) factory.a("1", "getGeneral_profile_idc", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "", "", "", "int"), 77);
        k = factory.a("method-execution", (Signature) factory.a("1", "getGeneral_profile_compatibility_flags", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "", "", "", "long"), 81);
        l = factory.a("method-execution", (Signature) factory.a("1", "getGeneral_constraint_indicator_flags", "org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox", "", "", "", "long"), 85);
    }

    public HevcConfigurationBox() {
        super(f3918a);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) this.b.a();
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        this.b.b(byteBuffer);
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        this.b.a(byteBuffer);
    }

    public HevcDecoderConfigurationRecord d() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return this.b;
    }

    public void a(HevcDecoderConfigurationRecord hevcDecoderConfigurationRecord) {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this, (Object) hevcDecoderConfigurationRecord));
        this.b = hevcDecoderConfigurationRecord;
    }

    public boolean equals(Object obj) {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this, obj));
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HevcConfigurationBox hevcConfigurationBox = (HevcConfigurationBox) obj;
        return this.b == null ? hevcConfigurationBox.b == null : this.b.equals(hevcConfigurationBox.b);
    }

    public int hashCode() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        if (this.b != null) {
            return this.b.hashCode();
        }
        return 0;
    }

    public int e() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return this.b.f3919a;
    }

    public int f() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        return this.b.b;
    }

    public boolean g() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.b.c;
    }

    public int h() {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this));
        return this.b.d;
    }

    public long i() {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this));
        return this.b.e;
    }

    public long j() {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this));
        return this.b.f;
    }

    public int k() {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this));
        return this.b.g;
    }

    public int l() {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this));
        return this.b.i;
    }

    public int m() {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this));
        return this.b.k;
    }

    public int n() {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this));
        return this.b.m;
    }

    public int o() {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this));
        return this.b.o;
    }

    public int p() {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this));
        return this.b.q;
    }

    public int q() {
        RequiresParseDetailAspect.a().a(Factory.a(v, (Object) this, (Object) this));
        return this.b.r;
    }

    public int r() {
        RequiresParseDetailAspect.a().a(Factory.a(w, (Object) this, (Object) this));
        return this.b.t;
    }

    public int s() {
        RequiresParseDetailAspect.a().a(Factory.a(x, (Object) this, (Object) this));
        return this.b.v;
    }

    public boolean t() {
        RequiresParseDetailAspect.a().a(Factory.a(y, (Object) this, (Object) this));
        return this.b.u;
    }

    public int u() {
        RequiresParseDetailAspect.a().a(Factory.a(z, (Object) this, (Object) this));
        return this.b.s;
    }

    public List<HevcDecoderConfigurationRecord.Array> v() {
        RequiresParseDetailAspect.a().a(Factory.a(A, (Object) this, (Object) this));
        return this.b.w;
    }
}
