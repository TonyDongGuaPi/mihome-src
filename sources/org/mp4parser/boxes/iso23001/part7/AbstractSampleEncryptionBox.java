package org.mp4parser.boxes.iso23001.part7;

import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.boxes.iso23001.part7.CencSampleAuxiliaryDataFormat;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.DoNotParseDetail;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public abstract class AbstractSampleEncryptionBox extends AbstractFullBox {
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;

    /* renamed from: a  reason: collision with root package name */
    protected int f3932a = -1;
    protected int b = -1;
    protected byte[] c = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    List<CencSampleAuxiliaryDataFormat> d = Collections.emptyList();

    static {
        l();
    }

    private static void l() {
        Factory factory = new Factory("AbstractSampleEncryptionBox.java", AbstractSampleEncryptionBox.class);
        e = factory.a("method-execution", (Signature) factory.a("1", "getOffsetToFirstIV", "org.mp4parser.boxes.iso23001.part7.AbstractSampleEncryptionBox", "", "", "", "int"), 28);
        f = factory.a("method-execution", (Signature) factory.a("1", "getEntries", "org.mp4parser.boxes.iso23001.part7.AbstractSampleEncryptionBox", "", "", "", "java.util.List"), 89);
        g = factory.a("method-execution", (Signature) factory.a("1", "setEntries", "org.mp4parser.boxes.iso23001.part7.AbstractSampleEncryptionBox", "java.util.List", "entries", "", "void"), 93);
        h = factory.a("method-execution", (Signature) factory.a("1", "equals", "org.mp4parser.boxes.iso23001.part7.AbstractSampleEncryptionBox", "java.lang.Object", DeviceTagInterface.q, "", "boolean"), 173);
        i = factory.a("method-execution", (Signature) factory.a("1", "hashCode", "org.mp4parser.boxes.iso23001.part7.AbstractSampleEncryptionBox", "", "", "", "int"), 200);
        j = factory.a("method-execution", (Signature) factory.a("1", "getEntrySizes", "org.mp4parser.boxes.iso23001.part7.AbstractSampleEncryptionBox", "", "", "", "java.util.List"), 208);
    }

    protected AbstractSampleEncryptionBox(String str) {
        super(str);
    }

    public int e() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return (c() > IjkMediaMeta.AV_CH_WIDE_RIGHT ? 16 : 8) + (h() ? this.c.length + 4 : 0) + 4;
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        if ((d() & 1) > 0) {
            this.f3932a = IsoTypeReader.c(byteBuffer);
            this.b = IsoTypeReader.f(byteBuffer);
            this.c = new byte[16];
            byteBuffer.get(this.c);
        }
        long b2 = IsoTypeReader.b(byteBuffer);
        ByteBuffer duplicate = byteBuffer.duplicate();
        ByteBuffer duplicate2 = byteBuffer.duplicate();
        this.d = a(duplicate, b2, 8);
        if (this.d == null) {
            this.d = a(duplicate2, b2, 16);
            byteBuffer.position((byteBuffer.position() + byteBuffer.remaining()) - duplicate2.remaining());
        } else {
            byteBuffer.position((byteBuffer.position() + byteBuffer.remaining()) - duplicate.remaining());
        }
        if (this.d == null) {
            throw new RuntimeException("Cannot parse SampleEncryptionBox");
        }
    }

    private List<CencSampleAuxiliaryDataFormat> a(ByteBuffer byteBuffer, long j2, int i2) {
        ArrayList arrayList = new ArrayList();
        while (true) {
            long j3 = j2 - 1;
            if (j2 <= 0) {
                return arrayList;
            }
            try {
                CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat = new CencSampleAuxiliaryDataFormat();
                cencSampleAuxiliaryDataFormat.f3934a = new byte[i2];
                byteBuffer.get(cencSampleAuxiliaryDataFormat.f3934a);
                if ((d() & 2) > 0) {
                    cencSampleAuxiliaryDataFormat.b = new CencSampleAuxiliaryDataFormat.Pair[IsoTypeReader.d(byteBuffer)];
                    for (int i3 = 0; i3 < cencSampleAuxiliaryDataFormat.b.length; i3++) {
                        cencSampleAuxiliaryDataFormat.b[i3] = cencSampleAuxiliaryDataFormat.a(IsoTypeReader.d(byteBuffer), IsoTypeReader.b(byteBuffer));
                    }
                }
                arrayList.add(cencSampleAuxiliaryDataFormat);
                j2 = j3;
            } catch (BufferUnderflowException unused) {
                return null;
            }
        }
    }

    public List<CencSampleAuxiliaryDataFormat> f() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.d;
    }

    public void a(List<CencSampleAuxiliaryDataFormat> list) {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this, (Object) list));
        this.d = list;
    }

    @DoNotParseDetail
    public boolean g() {
        return (d() & 2) > 0;
    }

    @DoNotParseDetail
    public void a(boolean z) {
        if (z) {
            b(d() | 2);
        } else {
            b(d() & 16777213);
        }
    }

    /* access modifiers changed from: protected */
    @DoNotParseDetail
    public boolean h() {
        return (d() & 1) > 0;
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        if (h()) {
            IsoTypeWriter.a(byteBuffer, this.f3932a);
            IsoTypeWriter.d(byteBuffer, this.b);
            byteBuffer.put(this.c);
        }
        IsoTypeWriter.b(byteBuffer, (long) k());
        for (CencSampleAuxiliaryDataFormat next : this.d) {
            if (next.a() > 0) {
                if (next.f3934a.length == 8 || next.f3934a.length == 16) {
                    byteBuffer.put(next.f3934a);
                    if (g()) {
                        IsoTypeWriter.b(byteBuffer, next.b.length);
                        for (CencSampleAuxiliaryDataFormat.Pair pair : next.b) {
                            IsoTypeWriter.b(byteBuffer, pair.a());
                            IsoTypeWriter.b(byteBuffer, pair.b());
                        }
                    }
                } else {
                    throw new RuntimeException("IV must be either 8 or 16 bytes");
                }
            }
        }
    }

    private int k() {
        int i2 = 0;
        for (CencSampleAuxiliaryDataFormat a2 : this.d) {
            if (a2.a() > 0) {
                i2++;
            }
        }
        return i2;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        long length = (h() ? 8 + ((long) this.c.length) : 4) + 4;
        for (CencSampleAuxiliaryDataFormat a2 : this.d) {
            length += (long) a2.a();
        }
        return length;
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        super.b(writableByteChannel);
    }

    public boolean equals(Object obj) {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this, obj));
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AbstractSampleEncryptionBox abstractSampleEncryptionBox = (AbstractSampleEncryptionBox) obj;
        if (this.f3932a != abstractSampleEncryptionBox.f3932a || this.b != abstractSampleEncryptionBox.b) {
            return false;
        }
        if (this.d == null ? abstractSampleEncryptionBox.d == null : this.d.equals(abstractSampleEncryptionBox.d)) {
            return Arrays.equals(this.c, abstractSampleEncryptionBox.c);
        }
        return false;
    }

    public int hashCode() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        int i2 = 0;
        int hashCode = ((((this.f3932a * 31) + this.b) * 31) + (this.c != null ? Arrays.hashCode(this.c) : 0)) * 31;
        if (this.d != null) {
            i2 = this.d.hashCode();
        }
        return hashCode + i2;
    }

    public List<Short> i() {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this));
        ArrayList arrayList = new ArrayList(this.d.size());
        for (CencSampleAuxiliaryDataFormat next : this.d) {
            short length = (short) next.f3934a.length;
            if (g()) {
                length = (short) (((short) (length + 2)) + (next.b.length * 6));
            }
            arrayList.add(Short.valueOf(length));
        }
        return arrayList;
    }
}
