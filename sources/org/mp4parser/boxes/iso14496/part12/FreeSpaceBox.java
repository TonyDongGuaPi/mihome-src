package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;

public class FreeSpaceBox extends AbstractBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3849a = "skip";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    byte[] b;

    static {
        e();
    }

    private static void e() {
        Factory factory = new Factory("FreeSpaceBox.java", FreeSpaceBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getData", "org.mp4parser.boxes.iso14496.part12.FreeSpaceBox", "", "", "", "[B"), 42);
        d = factory.a("method-execution", (Signature) factory.a("1", "setData", "org.mp4parser.boxes.iso14496.part12.FreeSpaceBox", "[B", "data", "", "void"), 46);
        e = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.FreeSpaceBox", "", "", "", "java.lang.String"), 61);
    }

    public FreeSpaceBox() {
        super(f3849a);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) this.b.length;
    }

    public byte[] d() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return this.b;
    }

    public void a(byte[] bArr) {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this, (Object) bArr));
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

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return "FreeSpaceBox[size=" + this.b.length + ";type=" + ae_() + Operators.ARRAY_END_STR;
    }
}
