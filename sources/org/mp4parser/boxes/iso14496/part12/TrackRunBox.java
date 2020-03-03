package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import com.tencent.smtt.sdk.TbsListener;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class TrackRunBox extends AbstractFullBox {
    private static final JoinPoint.StaticPart A = null;

    /* renamed from: a  reason: collision with root package name */
    public static final String f3910a = "trun";
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
    private int b;
    private SampleFlags c;
    private List<Entry> d = new ArrayList();

    static {
        p();
    }

    private static void p() {
        Factory factory = new Factory("TrackRunBox.java", TrackRunBox.class);
        e = factory.a("method-execution", (Signature) factory.a("1", "getEntries", "org.mp4parser.boxes.iso14496.part12.TrackRunBox", "", "", "", "java.util.List"), 60);
        f = factory.a("method-execution", (Signature) factory.a("1", "setEntries", "org.mp4parser.boxes.iso14496.part12.TrackRunBox", "java.util.List", "entries", "", "void"), 64);
        o = factory.a("method-execution", (Signature) factory.a("1", "setSampleDurationPresent", "org.mp4parser.boxes.iso14496.part12.TrackRunBox", "boolean", "v", "", "void"), 210);
        p = factory.a("method-execution", (Signature) factory.a("1", "isSampleFlagsPresent", "org.mp4parser.boxes.iso14496.part12.TrackRunBox", "", "", "", "boolean"), (int) TbsListener.ErrorCode.RENAME_EXCEPTION);
        q = factory.a("method-execution", (Signature) factory.a("1", "setSampleFlagsPresent", "org.mp4parser.boxes.iso14496.part12.TrackRunBox", "boolean", "v", "", "void"), (int) TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM);
        u = factory.a("method-execution", (Signature) factory.a("1", "isSampleCompositionTimeOffsetPresent", "org.mp4parser.boxes.iso14496.part12.TrackRunBox", "", "", "", "boolean"), (int) TbsListener.ErrorCode.RENAME_FAIL);
        v = factory.a("method-execution", (Signature) factory.a("1", "setSampleCompositionTimeOffsetPresent", "org.mp4parser.boxes.iso14496.part12.TrackRunBox", "boolean", "v", "", "void"), 235);
        w = factory.a("method-execution", (Signature) factory.a("1", "getDataOffset", "org.mp4parser.boxes.iso14496.part12.TrackRunBox", "", "", "", "int"), (int) IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE);
        x = factory.a("method-execution", (Signature) factory.a("1", "setDataOffset", "org.mp4parser.boxes.iso14496.part12.TrackRunBox", "int", "dataOffset", "", "void"), 248);
        y = factory.a("method-execution", (Signature) factory.a("1", "getFirstSampleFlags", "org.mp4parser.boxes.iso14496.part12.TrackRunBox", "", "", "", "org.mp4parser.boxes.iso14496.part12.SampleFlags"), 257);
        z = factory.a("method-execution", (Signature) factory.a("1", "setFirstSampleFlags", "org.mp4parser.boxes.iso14496.part12.TrackRunBox", "org.mp4parser.boxes.iso14496.part12.SampleFlags", "firstSampleFlags", "", "void"), 261);
        A = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.TrackRunBox", "", "", "", "java.lang.String"), 271);
        g = factory.a("method-execution", (Signature) factory.a("1", "getSampleCompositionTimeOffsets", "org.mp4parser.boxes.iso14496.part12.TrackRunBox", "", "", "", "[J"), 68);
        h = factory.a("method-execution", (Signature) factory.a("1", "getSampleCount", "org.mp4parser.boxes.iso14496.part12.TrackRunBox", "", "", "", "long"), 173);
        i = factory.a("method-execution", (Signature) factory.a("1", "isDataOffsetPresent", "org.mp4parser.boxes.iso14496.part12.TrackRunBox", "", "", "", "boolean"), 177);
        j = factory.a("method-execution", (Signature) factory.a("1", "setDataOffsetPresent", "org.mp4parser.boxes.iso14496.part12.TrackRunBox", "boolean", "v", "", "void"), 181);
        k = factory.a("method-execution", (Signature) factory.a("1", "isFirstSampleFlagsPresent", "org.mp4parser.boxes.iso14496.part12.TrackRunBox", "", "", "", "boolean"), 189);
        l = factory.a("method-execution", (Signature) factory.a("1", "isSampleSizePresent", "org.mp4parser.boxes.iso14496.part12.TrackRunBox", "", "", "", "boolean"), 194);
        m = factory.a("method-execution", (Signature) factory.a("1", "setSampleSizePresent", "org.mp4parser.boxes.iso14496.part12.TrackRunBox", "boolean", "v", "", "void"), (int) Opcodes.dh);
        n = factory.a("method-execution", (Signature) factory.a("1", "isSampleDurationPresent", "org.mp4parser.boxes.iso14496.part12.TrackRunBox", "", "", "", "boolean"), 206);
    }

    public TrackRunBox() {
        super(f3910a);
    }

    public List<Entry> e() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return this.d;
    }

    public void a(List<Entry> list) {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this, (Object) list));
        this.d = list;
    }

    public long[] f() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        if (!m()) {
            return null;
        }
        long[] jArr = new long[this.d.size()];
        for (int i2 = 0; i2 < jArr.length; i2++) {
            jArr[i2] = this.d.get(i2).d();
        }
        return jArr;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        int d2 = d();
        long j2 = (d2 & 1) == 1 ? 12 : 8;
        if ((d2 & 4) == 4) {
            j2 += 4;
        }
        long j3 = 0;
        if ((d2 & 256) == 256) {
            j3 = 4;
        }
        if ((d2 & 512) == 512) {
            j3 += 4;
        }
        if ((d2 & 1024) == 1024) {
            j3 += 4;
        }
        if ((d2 & 2048) == 2048) {
            j3 += 4;
        }
        return j2 + (j3 * ((long) this.d.size()));
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.b(byteBuffer, (long) this.d.size());
        int d2 = d();
        if ((d2 & 1) == 1) {
            IsoTypeWriter.b(byteBuffer, (long) this.b);
        }
        if ((d2 & 4) == 4) {
            this.c.a(byteBuffer);
        }
        for (Entry next : this.d) {
            if ((d2 & 256) == 256) {
                IsoTypeWriter.b(byteBuffer, next.f3911a);
            }
            if ((d2 & 512) == 512) {
                IsoTypeWriter.b(byteBuffer, next.b);
            }
            if ((d2 & 1024) == 1024) {
                next.c.a(byteBuffer);
            }
            if ((d2 & 2048) == 2048) {
                if (ag_() == 0) {
                    IsoTypeWriter.b(byteBuffer, next.d);
                } else {
                    byteBuffer.putInt((int) next.d);
                }
            }
        }
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        long b2 = IsoTypeReader.b(byteBuffer);
        if ((d() & 1) == 1) {
            this.b = CastUtils.a(IsoTypeReader.b(byteBuffer));
        } else {
            this.b = -1;
        }
        if ((d() & 4) == 4) {
            this.c = new SampleFlags(byteBuffer);
        }
        for (int i2 = 0; ((long) i2) < b2; i2++) {
            Entry entry = new Entry();
            if ((d() & 256) == 256) {
                entry.f3911a = IsoTypeReader.b(byteBuffer);
            }
            if ((d() & 512) == 512) {
                entry.b = IsoTypeReader.b(byteBuffer);
            }
            if ((d() & 1024) == 1024) {
                entry.c = new SampleFlags(byteBuffer);
            }
            if ((d() & 2048) == 2048) {
                entry.d = (long) byteBuffer.getInt();
            }
            this.d.add(entry);
        }
    }

    public long g() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        return (long) this.d.size();
    }

    public boolean h() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return (d() & 1) == 1;
    }

    public void a(boolean z2) {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this, Conversions.a(z2)));
        if (z2) {
            b(d() | 1);
        } else {
            b(d() & 16777214);
        }
    }

    public boolean i() {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this));
        return (d() & 4) == 4;
    }

    public boolean j() {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this));
        return (d() & 512) == 512;
    }

    public void b(boolean z2) {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this, Conversions.a(z2)));
        if (z2) {
            b(d() | 512);
        } else {
            b(d() & 16776703);
        }
    }

    public boolean k() {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this));
        return (d() & 256) == 256;
    }

    public void c(boolean z2) {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this, Conversions.a(z2)));
        if (z2) {
            b(d() | 256);
        } else {
            b(d() & 16776959);
        }
    }

    public boolean l() {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this));
        return (d() & 1024) == 1024;
    }

    public void d(boolean z2) {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this, Conversions.a(z2)));
        if (z2) {
            b(d() | 1024);
        } else {
            b(d() & 16776191);
        }
    }

    public boolean m() {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this));
        return (d() & 2048) == 2048;
    }

    public void e(boolean z2) {
        RequiresParseDetailAspect.a().a(Factory.a(v, (Object) this, (Object) this, Conversions.a(z2)));
        if (z2) {
            b(d() | 2048);
        } else {
            b(d() & 16775167);
        }
    }

    public int n() {
        RequiresParseDetailAspect.a().a(Factory.a(w, (Object) this, (Object) this));
        return this.b;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(x, (Object) this, (Object) this, Conversions.a(i2)));
        if (i2 == -1) {
            b(d() & 16777214);
        } else {
            b(d() | 1);
        }
        this.b = i2;
    }

    public SampleFlags o() {
        RequiresParseDetailAspect.a().a(Factory.a(y, (Object) this, (Object) this));
        return this.c;
    }

    public void a(SampleFlags sampleFlags) {
        RequiresParseDetailAspect.a().a(Factory.a(z, (Object) this, (Object) this, (Object) sampleFlags));
        if (sampleFlags == null) {
            b(d() & 16777211);
        } else {
            b(d() | 4);
        }
        this.c = sampleFlags;
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(A, (Object) this, (Object) this));
        return "TrackRunBox" + "{sampleCount=" + this.d.size() + ", dataOffset=" + this.b + ", dataOffsetPresent=" + h() + ", sampleSizePresent=" + j() + ", sampleDurationPresent=" + k() + ", sampleFlagsPresentPresent=" + l() + ", sampleCompositionTimeOffsetPresent=" + m() + ", firstSampleFlags=" + this.c + Operators.BLOCK_END;
    }

    public static class Entry {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public long f3911a;
        /* access modifiers changed from: private */
        public long b;
        /* access modifiers changed from: private */
        public SampleFlags c;
        /* access modifiers changed from: private */
        public long d;

        public Entry() {
        }

        public Entry(long j, long j2, SampleFlags sampleFlags, int i) {
            this.f3911a = j;
            this.b = j2;
            this.c = sampleFlags;
            this.d = (long) i;
        }

        public long a() {
            return this.f3911a;
        }

        public void a(long j) {
            this.f3911a = j;
        }

        public long b() {
            return this.b;
        }

        public void b(long j) {
            this.b = j;
        }

        public SampleFlags c() {
            return this.c;
        }

        public void a(SampleFlags sampleFlags) {
            this.c = sampleFlags;
        }

        public long d() {
            return this.d;
        }

        public void a(int i) {
            this.d = (long) i;
        }

        public String toString() {
            return "Entry{duration=" + this.f3911a + ", size=" + this.b + ", dlags=" + this.c + ", compTimeOffset=" + this.d + Operators.BLOCK_END;
        }
    }
}
