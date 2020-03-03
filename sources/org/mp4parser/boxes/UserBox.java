package org.mp4parser.boxes;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;

public class UserBox extends AbstractBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3783a = "uuid";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    byte[] b;

    static {
        e();
    }

    private static void e() {
        Factory factory = new Factory("UserBox.java", UserBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.UserBox", "", "", "", "java.lang.String"), 40);
        d = factory.a("method-execution", (Signature) factory.a("1", "getData", "org.mp4parser.boxes.UserBox", "", "", "", "[B"), 47);
        e = factory.a("method-execution", (Signature) factory.a("1", "setData", "org.mp4parser.boxes.UserBox", "[B", "data", "", "void"), 51);
    }

    public UserBox(byte[] bArr) {
        super("uuid", bArr);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) this.b.length;
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return "UserBox[type=" + ae_() + ";userType=" + new String(af_()) + ";contentLength=" + this.b.length + Operators.ARRAY_END_STR;
    }

    public byte[] d() {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this));
        return this.b;
    }

    public void a(byte[] bArr) {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this, (Object) bArr));
        this.b = bArr;
    }

    public void a(ByteBuffer byteBuffer) {
        this.b = new byte[byteBuffer.remaining()];
        byteBuffer.get(this.b);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        byteBuffer.put(this.b);
    }
}
