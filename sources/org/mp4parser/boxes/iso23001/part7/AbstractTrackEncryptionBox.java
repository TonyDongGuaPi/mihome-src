package org.mp4parser.boxes.iso23001.part7;

import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.UUID;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public abstract class AbstractTrackEncryptionBox extends AbstractFullBox {
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    private static final JoinPoint.StaticPart k = null;

    /* renamed from: a  reason: collision with root package name */
    int f3933a;
    int b;
    byte[] c;

    static {
        h();
    }

    private static void h() {
        Factory factory = new Factory("AbstractTrackEncryptionBox.java", AbstractTrackEncryptionBox.class);
        d = factory.a("method-execution", (Signature) factory.a("1", "getDefaultAlgorithmId", "org.mp4parser.boxes.iso23001.part7.AbstractTrackEncryptionBox", "", "", "", "int"), 24);
        e = factory.a("method-execution", (Signature) factory.a("1", "setDefaultAlgorithmId", "org.mp4parser.boxes.iso23001.part7.AbstractTrackEncryptionBox", "int", "defaultAlgorithmId", "", "void"), 28);
        f = factory.a("method-execution", (Signature) factory.a("1", "getDefaultIvSize", "org.mp4parser.boxes.iso23001.part7.AbstractTrackEncryptionBox", "", "", "", "int"), 32);
        g = factory.a("method-execution", (Signature) factory.a("1", "setDefaultIvSize", "org.mp4parser.boxes.iso23001.part7.AbstractTrackEncryptionBox", "int", "defaultIvSize", "", "void"), 36);
        h = factory.a("method-execution", (Signature) factory.a("1", "getDefault_KID", "org.mp4parser.boxes.iso23001.part7.AbstractTrackEncryptionBox", "", "", "", "java.util.UUID"), 40);
        i = factory.a("method-execution", (Signature) factory.a("1", "setDefault_KID", "org.mp4parser.boxes.iso23001.part7.AbstractTrackEncryptionBox", "java.util.UUID", "uuid", "", "void"), 46);
        j = factory.a("method-execution", (Signature) factory.a("1", "equals", "org.mp4parser.boxes.iso23001.part7.AbstractTrackEncryptionBox", "java.lang.Object", DeviceTagInterface.q, "", "boolean"), 76);
        k = factory.a("method-execution", (Signature) factory.a("1", "hashCode", "org.mp4parser.boxes.iso23001.part7.AbstractTrackEncryptionBox", "", "", "", "int"), 90);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 24;
    }

    protected AbstractTrackEncryptionBox(String str) {
        super(str);
    }

    public int e() {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this));
        return this.f3933a;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this, Conversions.a(i2)));
        this.f3933a = i2;
    }

    public int f() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.b;
    }

    public void d(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this, Conversions.a(i2)));
        this.b = i2;
    }

    public UUID g() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        ByteBuffer wrap = ByteBuffer.wrap(this.c);
        wrap.order(ByteOrder.BIG_ENDIAN);
        return new UUID(wrap.getLong(), wrap.getLong());
    }

    public void a(UUID uuid) {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this, (Object) uuid));
        ByteBuffer wrap = ByteBuffer.wrap(new byte[16]);
        wrap.putLong(uuid.getMostSignificantBits());
        wrap.putLong(uuid.getLeastSignificantBits());
        this.c = wrap.array();
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.f3933a = IsoTypeReader.c(byteBuffer);
        this.b = IsoTypeReader.f(byteBuffer);
        this.c = new byte[16];
        byteBuffer.get(this.c);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.a(byteBuffer, this.f3933a);
        IsoTypeWriter.d(byteBuffer, this.b);
        byteBuffer.put(this.c);
    }

    public boolean equals(Object obj) {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this, obj));
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AbstractTrackEncryptionBox abstractTrackEncryptionBox = (AbstractTrackEncryptionBox) obj;
        return this.f3933a == abstractTrackEncryptionBox.f3933a && this.b == abstractTrackEncryptionBox.b && Arrays.equals(this.c, abstractTrackEncryptionBox.c);
    }

    public int hashCode() {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this));
        return (((this.f3933a * 31) + this.b) * 31) + (this.c != null ? Arrays.hashCode(this.c) : 0);
    }
}
