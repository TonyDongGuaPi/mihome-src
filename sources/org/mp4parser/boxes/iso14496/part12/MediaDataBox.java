package org.mp4parser.boxes.iso14496.part12;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;

public final class MediaDataBox extends AbstractBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3859a = "mdat";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    ByteBuffer b;

    static {
        e();
    }

    private static void e() {
        Factory factory = new Factory("MediaDataBox.java", MediaDataBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getData", "org.mp4parser.boxes.iso14496.part12.MediaDataBox", "", "", "", "java.nio.ByteBuffer"), 60);
        d = factory.a("method-execution", (Signature) factory.a("1", "setData", "org.mp4parser.boxes.iso14496.part12.MediaDataBox", "java.nio.ByteBuffer", "data", "", "void"), 64);
    }

    public MediaDataBox() {
        super(f3859a);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) this.b.limit();
    }

    public void a(ByteBuffer byteBuffer) {
        this.b = byteBuffer;
        byteBuffer.position(byteBuffer.position() + byteBuffer.remaining());
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        this.b.rewind();
        byteBuffer.put(this.b);
    }

    public ByteBuffer d() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return this.b;
    }

    public void c(ByteBuffer byteBuffer) {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this, (Object) byteBuffer));
        this.b = byteBuffer;
    }
}
