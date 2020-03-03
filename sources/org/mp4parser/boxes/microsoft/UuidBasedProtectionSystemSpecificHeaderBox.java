package org.mp4parser.boxes.microsoft;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
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

public class UuidBasedProtectionSystemSpecificHeaderBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static byte[] f3942a = {-48, Constants.TagName.PAY_CHANNEL_NAME, Constants.TagName.CP_NO, 24, 16, -13, 74, -126, Constants.TagName.CPLC, Constants.TagName.PROMOTION_MESSAGE_DATA, 50, -40, -85, ScriptToolsConst.TagName.ScriptDown, -125, -45};
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    UUID b;
    ProtectionSpecificHeader c;

    private static void i() {
        Factory factory = new Factory("UuidBasedProtectionSystemSpecificHeaderBox.java", UuidBasedProtectionSystemSpecificHeaderBox.class);
        d = factory.a("method-execution", (Signature) factory.a("1", "getSystemId", "org.mp4parser.boxes.microsoft.UuidBasedProtectionSystemSpecificHeaderBox", "", "", "", "java.util.UUID"), 66);
        e = factory.a("method-execution", (Signature) factory.a("1", "setSystemId", "org.mp4parser.boxes.microsoft.UuidBasedProtectionSystemSpecificHeaderBox", "java.util.UUID", "systemId", "", "void"), 70);
        f = factory.a("method-execution", (Signature) factory.a("1", "getSystemIdString", "org.mp4parser.boxes.microsoft.UuidBasedProtectionSystemSpecificHeaderBox", "", "", "", "java.lang.String"), 74);
        g = factory.a("method-execution", (Signature) factory.a("1", "getProtectionSpecificHeader", "org.mp4parser.boxes.microsoft.UuidBasedProtectionSystemSpecificHeaderBox", "", "", "", "org.mp4parser.boxes.microsoft.ProtectionSpecificHeader"), 78);
        h = factory.a("method-execution", (Signature) factory.a("1", "setProtectionSpecificHeader", "org.mp4parser.boxes.microsoft.UuidBasedProtectionSystemSpecificHeaderBox", "org.mp4parser.boxes.microsoft.ProtectionSpecificHeader", "protectionSpecificHeader", "", "void"), 82);
        i = factory.a("method-execution", (Signature) factory.a("1", "getProtectionSpecificHeaderString", "org.mp4parser.boxes.microsoft.UuidBasedProtectionSystemSpecificHeaderBox", "", "", "", "java.lang.String"), 86);
        j = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.microsoft.UuidBasedProtectionSystemSpecificHeaderBox", "", "", "", "java.lang.String"), 91);
    }

    static {
        i();
    }

    public UuidBasedProtectionSystemSpecificHeaderBox() {
        super("uuid", f3942a);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) (this.c.b().limit() + 24);
    }

    public byte[] af_() {
        return f3942a;
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.a(byteBuffer, this.b.getMostSignificantBits());
        IsoTypeWriter.a(byteBuffer, this.b.getLeastSignificantBits());
        ByteBuffer b2 = this.c.b();
        b2.rewind();
        IsoTypeWriter.b(byteBuffer, (long) b2.limit());
        byteBuffer.put(b2);
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        byte[] bArr = new byte[16];
        byteBuffer.get(bArr);
        this.b = UUIDConverter.a(bArr);
        CastUtils.a(IsoTypeReader.b(byteBuffer));
        this.c = ProtectionSpecificHeader.a(this.b, byteBuffer);
    }

    public UUID e() {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this));
        return this.b;
    }

    public void a(UUID uuid) {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this, (Object) uuid));
        this.b = uuid;
    }

    public String f() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.b.toString();
    }

    public ProtectionSpecificHeader g() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return this.c;
    }

    public void a(ProtectionSpecificHeader protectionSpecificHeader) {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this, (Object) protectionSpecificHeader));
        this.c = protectionSpecificHeader;
    }

    public String h() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.c.toString();
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this));
        return "UuidBasedProtectionSystemSpecificHeaderBox" + "{systemId=" + this.b.toString() + ", dataSize=" + this.c.b().limit() + Operators.BLOCK_END;
    }
}
