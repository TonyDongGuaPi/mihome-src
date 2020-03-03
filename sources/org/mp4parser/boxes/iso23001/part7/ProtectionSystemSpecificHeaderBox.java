package org.mp4parser.boxes.iso23001.part7;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import org.mp4parser.tools.UUIDConverter;

public class ProtectionSystemSpecificHeaderBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3936a = "pssh";
    public static byte[] b = UUIDConverter.a(UUID.fromString("A2B55680-6F43-11E0-9A3F-0002A5D5C51B"));
    public static byte[] c = UUIDConverter.a(UUID.fromString("edef8ba9-79d6-4ace-a3c8-27dcd51d21ed"));
    public static byte[] d = UUIDConverter.a(UUID.fromString("9A04F079-9840-4286-AB92-E65BE0885F95"));
    static final /* synthetic */ boolean h = (!ProtectionSystemSpecificHeaderBox.class.desiredAssertionStatus());
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    private static final JoinPoint.StaticPart k = null;
    private static final JoinPoint.StaticPart l = null;
    private static final JoinPoint.StaticPart m = null;
    private static final JoinPoint.StaticPart n = null;
    byte[] e;
    byte[] f;
    List<UUID> g = new ArrayList();

    private static void h() {
        Factory factory = new Factory("ProtectionSystemSpecificHeaderBox.java", ProtectionSystemSpecificHeaderBox.class);
        i = factory.a("method-execution", (Signature) factory.a("1", "getKeyIds", "org.mp4parser.boxes.iso23001.part7.ProtectionSystemSpecificHeaderBox", "", "", "", "java.util.List"), 52);
        j = factory.a("method-execution", (Signature) factory.a("1", "setKeyIds", "org.mp4parser.boxes.iso23001.part7.ProtectionSystemSpecificHeaderBox", "java.util.List", "keyIds", "", "void"), 56);
        k = factory.a("method-execution", (Signature) factory.a("1", "getSystemId", "org.mp4parser.boxes.iso23001.part7.ProtectionSystemSpecificHeaderBox", "", "", "", "[B"), 60);
        l = factory.a("method-execution", (Signature) factory.a("1", "setSystemId", "org.mp4parser.boxes.iso23001.part7.ProtectionSystemSpecificHeaderBox", "[B", "systemId", "", "void"), 64);
        m = factory.a("method-execution", (Signature) factory.a("1", "getContent", "org.mp4parser.boxes.iso23001.part7.ProtectionSystemSpecificHeaderBox", "", "", "", "[B"), 69);
        n = factory.a("method-execution", (Signature) factory.a("1", "setContent", "org.mp4parser.boxes.iso23001.part7.ProtectionSystemSpecificHeaderBox", "[B", "content", "", "void"), 73);
    }

    static {
        h();
    }

    public ProtectionSystemSpecificHeaderBox(byte[] bArr, byte[] bArr2) {
        super(f3936a);
        this.e = bArr2;
        this.f = bArr;
    }

    public ProtectionSystemSpecificHeaderBox() {
        super(f3936a);
    }

    public List<UUID> e() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.g;
    }

    public void a(List<UUID> list) {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this, (Object) list));
        this.g = list;
    }

    public byte[] f() {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this));
        return this.f;
    }

    public void a(byte[] bArr) {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this, (Object) bArr));
        if (h || bArr.length == 16) {
            this.f = bArr;
            return;
        }
        throw new AssertionError();
    }

    public byte[] g() {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this));
        return this.e;
    }

    public void b(byte[] bArr) {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this, (Object) bArr));
        this.e = bArr;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        long length = (long) (this.e.length + 24);
        return ag_() > 0 ? length + 4 + ((long) (this.g.size() * 16)) : length;
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        if (h || this.f.length == 16) {
            byteBuffer.put(this.f, 0, 16);
            if (ag_() > 0) {
                IsoTypeWriter.b(byteBuffer, (long) this.g.size());
                for (UUID a2 : this.g) {
                    byteBuffer.put(UUIDConverter.a(a2));
                }
            }
            IsoTypeWriter.b(byteBuffer, (long) this.e.length);
            byteBuffer.put(this.e);
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.f = new byte[16];
        byteBuffer.get(this.f);
        if (ag_() > 0) {
            int a2 = CastUtils.a(IsoTypeReader.b(byteBuffer));
            while (true) {
                int i2 = a2 - 1;
                if (a2 <= 0) {
                    break;
                }
                byte[] bArr = new byte[16];
                byteBuffer.get(bArr);
                this.g.add(UUIDConverter.a(bArr));
                a2 = i2;
            }
        }
        long b2 = IsoTypeReader.b(byteBuffer);
        this.e = new byte[byteBuffer.remaining()];
        byteBuffer.get(this.e);
        if (!h && b2 != ((long) this.e.length)) {
            throw new AssertionError();
        }
    }
}
