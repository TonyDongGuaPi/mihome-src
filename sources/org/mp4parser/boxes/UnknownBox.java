package org.mp4parser.boxes;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;

public class UnknownBox extends AbstractBox {
    private static final JoinPoint.StaticPart b = null;
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;

    /* renamed from: a  reason: collision with root package name */
    ByteBuffer f3782a;

    static {
        e();
    }

    private static void e() {
        Factory factory = new Factory("UnknownBox.java", UnknownBox.class);
        b = factory.a("method-execution", (Signature) factory.a("1", "getData", "org.mp4parser.boxes.UnknownBox", "", "", "", "java.nio.ByteBuffer"), 52);
        c = factory.a("method-execution", (Signature) factory.a("1", "setData", "org.mp4parser.boxes.UnknownBox", "java.nio.ByteBuffer", "data", "", "void"), 56);
        d = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.UnknownBox", "", "", "", "java.lang.String"), 61);
    }

    public UnknownBox(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) this.f3782a.limit();
    }

    public void a(ByteBuffer byteBuffer) {
        this.f3782a = byteBuffer;
        byteBuffer.position(byteBuffer.position() + byteBuffer.remaining());
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        this.f3782a.rewind();
        byteBuffer.put(this.f3782a);
    }

    public ByteBuffer d() {
        RequiresParseDetailAspect.a().a(Factory.a(b, (Object) this, (Object) this));
        return this.f3782a;
    }

    public void c(ByteBuffer byteBuffer) {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this, (Object) byteBuffer));
        this.f3782a = byteBuffer;
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this));
        return "UnknownBox{type=" + this.r + Operators.BLOCK_END;
    }
}
