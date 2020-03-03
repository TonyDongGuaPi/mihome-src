package org.mp4parser.boxes.samplegrouping;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;
import org.mp4parser.IsoFile;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.boxes.iso14496.part15.StepwiseTemporalLayerEntry;
import org.mp4parser.boxes.iso14496.part15.SyncSampleEntry;
import org.mp4parser.boxes.iso14496.part15.TemporalLayerSampleGroup;
import org.mp4parser.boxes.iso14496.part15.TemporalSubLayerSampleGroup;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class SampleGroupDescriptionBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3960a = "sgpd";
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    private static final JoinPoint.StaticPart k = null;
    private static final JoinPoint.StaticPart l = null;
    private static final JoinPoint.StaticPart m = null;
    private String b;
    private int c;
    private List<GroupEntry> d = new LinkedList();

    static {
        h();
    }

    private static void h() {
        Factory factory = new Factory("SampleGroupDescriptionBox.java", SampleGroupDescriptionBox.class);
        e = factory.a("method-execution", (Signature) factory.a("1", "getGroupingType", "org.mp4parser.boxes.samplegrouping.SampleGroupDescriptionBox", "", "", "", "java.lang.String"), 56);
        f = factory.a("method-execution", (Signature) factory.a("1", "setGroupingType", "org.mp4parser.boxes.samplegrouping.SampleGroupDescriptionBox", "java.lang.String", "groupingType", "", "void"), 60);
        g = factory.a("method-execution", (Signature) factory.a("1", "getDefaultLength", "org.mp4parser.boxes.samplegrouping.SampleGroupDescriptionBox", "", "", "", "int"), 152);
        h = factory.a("method-execution", (Signature) factory.a("1", "setDefaultLength", "org.mp4parser.boxes.samplegrouping.SampleGroupDescriptionBox", "int", "defaultLength", "", "void"), 156);
        i = factory.a("method-execution", (Signature) factory.a("1", "getGroupEntries", "org.mp4parser.boxes.samplegrouping.SampleGroupDescriptionBox", "", "", "", "java.util.List"), 160);
        j = factory.a("method-execution", (Signature) factory.a("1", "setGroupEntries", "org.mp4parser.boxes.samplegrouping.SampleGroupDescriptionBox", "java.util.List", "groupEntries", "", "void"), 164);
        k = factory.a("method-execution", (Signature) factory.a("1", "equals", "org.mp4parser.boxes.samplegrouping.SampleGroupDescriptionBox", "java.lang.Object", DeviceTagInterface.q, "", "boolean"), 169);
        l = factory.a("method-execution", (Signature) factory.a("1", "hashCode", "org.mp4parser.boxes.samplegrouping.SampleGroupDescriptionBox", "", "", "", "int"), 190);
        m = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.samplegrouping.SampleGroupDescriptionBox", "", "", "", "java.lang.String"), (int) Opcodes.dh);
    }

    public SampleGroupDescriptionBox() {
        super(f3960a);
        a(1);
    }

    public String e() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return this.b;
    }

    public void a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this, (Object) str));
        this.b = str;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        long j2 = (ag_() == 1 ? 12 : 8) + 4;
        for (GroupEntry next : this.d) {
            if (ag_() == 1 && this.c == 0) {
                j2 += 4;
            }
            j2 += (long) next.n();
        }
        return j2;
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        byteBuffer.put(IsoFile.a(this.b));
        if (ag_() == 1) {
            IsoTypeWriter.b(byteBuffer, (long) this.c);
        }
        IsoTypeWriter.b(byteBuffer, (long) this.d.size());
        for (GroupEntry next : this.d) {
            if (ag_() == 1 && this.c == 0) {
                IsoTypeWriter.b(byteBuffer, (long) next.b().limit());
            }
            byteBuffer.put(next.b());
        }
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        if (ag_() == 1) {
            this.b = IsoTypeReader.m(byteBuffer);
            if (ag_() == 1) {
                this.c = CastUtils.a(IsoTypeReader.b(byteBuffer));
            }
            long b2 = IsoTypeReader.b(byteBuffer);
            while (true) {
                long j2 = b2 - 1;
                if (b2 > 0) {
                    int i2 = this.c;
                    if (ag_() == 1) {
                        if (this.c == 0) {
                            i2 = CastUtils.a(IsoTypeReader.b(byteBuffer));
                        }
                        int position = byteBuffer.position() + i2;
                        ByteBuffer slice = byteBuffer.slice();
                        slice.limit(i2);
                        this.d.add(a(slice, this.b));
                        byteBuffer.position(position);
                        b2 = j2;
                    } else {
                        throw new RuntimeException("This should be implemented");
                    }
                } else {
                    return;
                }
            }
        } else {
            throw new RuntimeException("SampleGroupDescriptionBox are only supported in version 1");
        }
    }

    private GroupEntry a(ByteBuffer byteBuffer, String str) {
        GroupEntry groupEntry;
        if (RollRecoveryEntry.f3959a.equals(str)) {
            groupEntry = new RollRecoveryEntry();
        } else if (RateShareEntry.f3957a.equals(str)) {
            groupEntry = new RateShareEntry();
        } else if (CencSampleEncryptionInformationGroupEntry.f3956a.equals(str)) {
            groupEntry = new CencSampleEncryptionInformationGroupEntry();
        } else if (VisualRandomAccessEntry.f3965a.equals(str)) {
            groupEntry = new VisualRandomAccessEntry();
        } else if (TemporalLevelEntry.f3963a.equals(str)) {
            groupEntry = new TemporalLevelEntry();
        } else if ("sync".equals(str)) {
            groupEntry = new SyncSampleEntry();
        } else if (TemporalLayerSampleGroup.f3924a.equals(str)) {
            groupEntry = new TemporalLayerSampleGroup();
        } else if (TemporalSubLayerSampleGroup.f3925a.equals(str)) {
            groupEntry = new TemporalSubLayerSampleGroup();
        } else if (StepwiseTemporalLayerEntry.f3922a.equals(str)) {
            groupEntry = new StepwiseTemporalLayerEntry();
        } else {
            groupEntry = new UnknownEntry(str);
        }
        groupEntry.a(byteBuffer);
        return groupEntry;
    }

    public int f() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return this.c;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this, Conversions.a(i2)));
        this.c = i2;
    }

    public List<GroupEntry> g() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.d;
    }

    public void a(List<GroupEntry> list) {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this, (Object) list));
        this.d = list;
    }

    public boolean equals(Object obj) {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this, obj));
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SampleGroupDescriptionBox sampleGroupDescriptionBox = (SampleGroupDescriptionBox) obj;
        if (this.c != sampleGroupDescriptionBox.c) {
            return false;
        }
        return this.d == null ? sampleGroupDescriptionBox.d == null : this.d.equals(sampleGroupDescriptionBox.d);
    }

    public int hashCode() {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this));
        int i2 = 0;
        int i3 = (this.c + 0) * 31;
        if (this.d != null) {
            i2 = this.d.hashCode();
        }
        return i3 + i2;
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this));
        StringBuilder sb = new StringBuilder("SampleGroupDescriptionBox{groupingType='");
        sb.append(this.d.size() > 0 ? this.d.get(0).a() : "????");
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(", defaultLength=");
        sb.append(this.c);
        sb.append(", groupEntries=");
        sb.append(this.d);
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }
}
