package org.mp4parser.boxes.dece;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.Utf8;

public class BaseLocationBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3808a = "bloc";
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    String b = "";
    String c = "";

    static {
        g();
    }

    private static void g() {
        Factory factory = new Factory("BaseLocationBox.java", BaseLocationBox.class);
        d = factory.a("method-execution", (Signature) factory.a("1", "getBaseLocation", "org.mp4parser.boxes.dece.BaseLocationBox", "", "", "", "java.lang.String"), 44);
        e = factory.a("method-execution", (Signature) factory.a("1", "setBaseLocation", "org.mp4parser.boxes.dece.BaseLocationBox", "java.lang.String", "baseLocation", "", "void"), 48);
        f = factory.a("method-execution", (Signature) factory.a("1", "getPurchaseLocation", "org.mp4parser.boxes.dece.BaseLocationBox", "", "", "", "java.lang.String"), 52);
        g = factory.a("method-execution", (Signature) factory.a("1", "setPurchaseLocation", "org.mp4parser.boxes.dece.BaseLocationBox", "java.lang.String", "purchaseLocation", "", "void"), 56);
        h = factory.a("method-execution", (Signature) factory.a("1", "equals", "org.mp4parser.boxes.dece.BaseLocationBox", "java.lang.Object", DeviceTagInterface.q, "", "boolean"), 86);
        i = factory.a("method-execution", (Signature) factory.a("1", "hashCode", "org.mp4parser.boxes.dece.BaseLocationBox", "", "", "", "int"), 100);
        j = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.dece.BaseLocationBox", "", "", "", "java.lang.String"), 107);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 1028;
    }

    public BaseLocationBox() {
        super(f3808a);
    }

    public BaseLocationBox(String str, String str2) {
        super(f3808a);
        this.b = str;
        this.c = str2;
    }

    public String e() {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this));
        return this.b;
    }

    public void a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this, (Object) str));
        this.b = str;
    }

    public String f() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.c;
    }

    public void b(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this, (Object) str));
        this.c = str;
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.g(byteBuffer);
        byteBuffer.get(new byte[((256 - Utf8.b(this.b)) - 1)]);
        this.c = IsoTypeReader.g(byteBuffer);
        byteBuffer.get(new byte[((256 - Utf8.b(this.c)) - 1)]);
        byteBuffer.get(new byte[512]);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        byteBuffer.put(Utf8.a(this.b));
        byteBuffer.put(new byte[(256 - Utf8.b(this.b))]);
        byteBuffer.put(Utf8.a(this.c));
        byteBuffer.put(new byte[(256 - Utf8.b(this.c))]);
        byteBuffer.put(new byte[512]);
    }

    public boolean equals(Object obj) {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this, obj));
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BaseLocationBox baseLocationBox = (BaseLocationBox) obj;
        if (this.b == null ? baseLocationBox.b == null : this.b.equals(baseLocationBox.b)) {
            return this.c == null ? baseLocationBox.c == null : this.c.equals(baseLocationBox.c);
        }
        return false;
    }

    public int hashCode() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        int i2 = 0;
        int hashCode = (this.b != null ? this.b.hashCode() : 0) * 31;
        if (this.c != null) {
            i2 = this.c.hashCode();
        }
        return hashCode + i2;
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this));
        return "BaseLocationBox{baseLocation='" + this.b + Operators.SINGLE_QUOTE + ", purchaseLocation='" + this.c + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
