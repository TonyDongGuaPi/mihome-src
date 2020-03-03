package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import com.tencent.smtt.sdk.TbsListener;
import java.nio.ByteBuffer;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class TrackFragmentHeaderBox extends AbstractFullBox {
    private static final JoinPoint.StaticPart A = null;
    private static final JoinPoint.StaticPart B = null;
    private static final JoinPoint.StaticPart C = null;
    private static final JoinPoint.StaticPart D = null;
    private static final JoinPoint.StaticPart E = null;
    private static final JoinPoint.StaticPart F = null;
    private static final JoinPoint.StaticPart G = null;
    private static final JoinPoint.StaticPart H = null;

    /* renamed from: a  reason: collision with root package name */
    public static final String f3904a = "tfhd";
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
    private long b;
    private long c = -1;
    private long d;
    private long e = -1;
    private long f = -1;
    private SampleFlags g;
    private boolean h;
    private boolean i;

    static {
        r();
    }

    private static void r() {
        Factory factory = new Factory("TrackFragmentHeaderBox.java", TrackFragmentHeaderBox.class);
        j = factory.a("method-execution", (Signature) factory.a("1", "hasBaseDataOffset", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "", "", "", "boolean"), 126);
        k = factory.a("method-execution", (Signature) factory.a("1", "hasSampleDescriptionIndex", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "", "", "", "boolean"), 130);
        w = factory.a("method-execution", (Signature) factory.a("1", "setSampleDescriptionIndex", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "long", "sampleDescriptionIndex", "", "void"), 171);
        x = factory.a("method-execution", (Signature) factory.a("1", "getDefaultSampleDuration", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "", "", "", "long"), 180);
        y = factory.a("method-execution", (Signature) factory.a("1", "setDefaultSampleDuration", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "long", "defaultSampleDuration", "", "void"), 184);
        z = factory.a("method-execution", (Signature) factory.a("1", "getDefaultSampleSize", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "", "", "", "long"), 189);
        A = factory.a("method-execution", (Signature) factory.a("1", "setDefaultSampleSize", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "long", "defaultSampleSize", "", "void"), 193);
        B = factory.a("method-execution", (Signature) factory.a("1", "getDefaultSampleFlags", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "", "", "", "org.mp4parser.boxes.iso14496.part12.SampleFlags"), (int) Opcodes.dh);
        C = factory.a("method-execution", (Signature) factory.a("1", "setDefaultSampleFlags", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "org.mp4parser.boxes.iso14496.part12.SampleFlags", "defaultSampleFlags", "", "void"), 202);
        D = factory.a("method-execution", (Signature) factory.a("1", "isDurationIsEmpty", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "", "", "", "boolean"), 207);
        E = factory.a("method-execution", (Signature) factory.a("1", "setDurationIsEmpty", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "boolean", "durationIsEmpty", "", "void"), 211);
        F = factory.a("method-execution", (Signature) factory.a("1", "isDefaultBaseIsMoof", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "", "", "", "boolean"), 216);
        l = factory.a("method-execution", (Signature) factory.a("1", "hasDefaultSampleDuration", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "", "", "", "boolean"), 134);
        G = factory.a("method-execution", (Signature) factory.a("1", "setDefaultBaseIsMoof", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "boolean", "defaultBaseIsMoof", "", "void"), (int) TbsListener.ErrorCode.COPY_INSTALL_SUCCESS);
        H = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "", "", "", "java.lang.String"), (int) TbsListener.ErrorCode.DEXOAT_EXCEPTION);
        m = factory.a("method-execution", (Signature) factory.a("1", "hasDefaultSampleSize", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "", "", "", "boolean"), 138);
        n = factory.a("method-execution", (Signature) factory.a("1", "hasDefaultSampleFlags", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "", "", "", "boolean"), 142);
        o = factory.a("method-execution", (Signature) factory.a("1", "getTrackId", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "", "", "", "long"), 146);
        p = factory.a("method-execution", (Signature) factory.a("1", "setTrackId", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "long", "trackId", "", "void"), 150);
        q = factory.a("method-execution", (Signature) factory.a("1", "getBaseDataOffset", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "", "", "", "long"), 154);
        u = factory.a("method-execution", (Signature) factory.a("1", "setBaseDataOffset", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "long", "baseDataOffset", "", "void"), 158);
        v = factory.a("method-execution", (Signature) factory.a("1", "getSampleDescriptionIndex", "org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox", "", "", "", "long"), 167);
    }

    public TrackFragmentHeaderBox() {
        super(f3904a);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        int d2 = d();
        long j2 = (d2 & 1) == 1 ? 16 : 8;
        if ((d2 & 2) == 2) {
            j2 += 4;
        }
        if ((d2 & 8) == 8) {
            j2 += 4;
        }
        if ((d2 & 16) == 16) {
            j2 += 4;
        }
        return (d2 & 32) == 32 ? j2 + 4 : j2;
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.b(byteBuffer, this.b);
        if ((d() & 1) == 1) {
            IsoTypeWriter.a(byteBuffer, k());
        }
        if ((d() & 2) == 2) {
            IsoTypeWriter.b(byteBuffer, l());
        }
        if ((d() & 8) == 8) {
            IsoTypeWriter.b(byteBuffer, m());
        }
        if ((d() & 16) == 16) {
            IsoTypeWriter.b(byteBuffer, n());
        }
        if ((d() & 32) == 32) {
            this.g.a(byteBuffer);
        }
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.b(byteBuffer);
        if ((d() & 1) == 1) {
            this.c = IsoTypeReader.h(byteBuffer);
        }
        if ((d() & 2) == 2) {
            this.d = IsoTypeReader.b(byteBuffer);
        }
        if ((d() & 8) == 8) {
            this.e = IsoTypeReader.b(byteBuffer);
        }
        if ((d() & 16) == 16) {
            this.f = IsoTypeReader.b(byteBuffer);
        }
        if ((d() & 32) == 32) {
            this.g = new SampleFlags(byteBuffer);
        }
        if ((d() & 65536) == 65536) {
            this.h = true;
        }
        if ((d() & 131072) == 131072) {
            this.i = true;
        }
    }

    public boolean e() {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this));
        return (d() & 1) != 0;
    }

    public boolean f() {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this));
        return (d() & 2) != 0;
    }

    public boolean g() {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this));
        return (d() & 8) != 0;
    }

    public boolean h() {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this));
        return (d() & 16) != 0;
    }

    public boolean i() {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this));
        return (d() & 32) != 0;
    }

    public long j() {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this));
        return this.b;
    }

    public void a(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this, Conversions.a(j2)));
        this.b = j2;
    }

    public long k() {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this));
        return this.c;
    }

    public void b(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this, Conversions.a(j2)));
        if (j2 == -1) {
            b(d() & 2147483646);
        } else {
            b(d() | 1);
        }
        this.c = j2;
    }

    public long l() {
        RequiresParseDetailAspect.a().a(Factory.a(v, (Object) this, (Object) this));
        return this.d;
    }

    public void c(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(w, (Object) this, (Object) this, Conversions.a(j2)));
        if (j2 == -1) {
            b(d() & 2147483645);
        } else {
            b(d() | 2);
        }
        this.d = j2;
    }

    public long m() {
        RequiresParseDetailAspect.a().a(Factory.a(x, (Object) this, (Object) this));
        return this.e;
    }

    public void d(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(y, (Object) this, (Object) this, Conversions.a(j2)));
        b(d() | 8);
        this.e = j2;
    }

    public long n() {
        RequiresParseDetailAspect.a().a(Factory.a(z, (Object) this, (Object) this));
        return this.f;
    }

    public void e(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(A, (Object) this, (Object) this, Conversions.a(j2)));
        b(d() | 16);
        this.f = j2;
    }

    public SampleFlags o() {
        RequiresParseDetailAspect.a().a(Factory.a(B, (Object) this, (Object) this));
        return this.g;
    }

    public void a(SampleFlags sampleFlags) {
        RequiresParseDetailAspect.a().a(Factory.a(C, (Object) this, (Object) this, (Object) sampleFlags));
        b(d() | 32);
        this.g = sampleFlags;
    }

    public boolean p() {
        RequiresParseDetailAspect.a().a(Factory.a(D, (Object) this, (Object) this));
        return this.h;
    }

    public void a(boolean z2) {
        RequiresParseDetailAspect.a().a(Factory.a(E, (Object) this, (Object) this, Conversions.a(z2)));
        b(d() | 65536);
        this.h = z2;
    }

    public boolean q() {
        RequiresParseDetailAspect.a().a(Factory.a(F, (Object) this, (Object) this));
        return this.i;
    }

    public void b(boolean z2) {
        RequiresParseDetailAspect.a().a(Factory.a(G, (Object) this, (Object) this, Conversions.a(z2)));
        b(d() | 131072);
        this.i = z2;
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(H, (Object) this, (Object) this));
        return "TrackFragmentHeaderBox" + "{trackId=" + this.b + ", baseDataOffset=" + this.c + ", sampleDescriptionIndex=" + this.d + ", defaultSampleDuration=" + this.e + ", defaultSampleSize=" + this.f + ", defaultSampleFlags=" + this.g + ", durationIsEmpty=" + this.h + ", defaultBaseIsMoof=" + this.i + Operators.BLOCK_END;
    }
}
