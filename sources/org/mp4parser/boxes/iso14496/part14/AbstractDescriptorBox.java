package org.mp4parser.boxes.iso14496.part14;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BaseDescriptor;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.ObjectDescriptorFactory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;

public class AbstractDescriptorBox extends AbstractFullBox {
    private static Logger c = Logger.getLogger(AbstractDescriptorBox.class.getName());
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;

    /* renamed from: a  reason: collision with root package name */
    protected BaseDescriptor f3915a;
    protected ByteBuffer b;

    private static void h() {
        Factory factory = new Factory("AbstractDescriptorBox.java", AbstractDescriptorBox.class);
        d = factory.a("method-execution", (Signature) factory.a("1", "getData", "org.mp4parser.boxes.iso14496.part14.AbstractDescriptorBox", "", "", "", "java.nio.ByteBuffer"), 42);
        e = factory.a("method-execution", (Signature) factory.a("1", "setData", "org.mp4parser.boxes.iso14496.part14.AbstractDescriptorBox", "java.nio.ByteBuffer", "data", "", "void"), 46);
        f = factory.a("method-execution", (Signature) factory.a("1", "getDescriptor", "org.mp4parser.boxes.iso14496.part14.AbstractDescriptorBox", "", "", "", "org.mp4parser.boxes.iso14496.part1.objectdescriptors.BaseDescriptor"), 62);
        g = factory.a("method-execution", (Signature) factory.a("1", "setDescriptor", "org.mp4parser.boxes.iso14496.part14.AbstractDescriptorBox", "org.mp4parser.boxes.iso14496.part1.objectdescriptors.BaseDescriptor", "descriptor", "", "void"), 66);
        h = factory.a("method-execution", (Signature) factory.a("1", "getDescriptorAsString", "org.mp4parser.boxes.iso14496.part14.AbstractDescriptorBox", "", "", "", "java.lang.String"), 70);
    }

    static {
        h();
    }

    public AbstractDescriptorBox(String str) {
        super(str);
    }

    public ByteBuffer e() {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this));
        return this.b;
    }

    public void c(ByteBuffer byteBuffer) {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this, (Object) byteBuffer));
        this.b = byteBuffer;
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        this.b.rewind();
        byteBuffer.put(this.b);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) (this.b.limit() + 4);
    }

    public BaseDescriptor f() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.f3915a;
    }

    public void a(BaseDescriptor baseDescriptor) {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this, (Object) baseDescriptor));
        this.f3915a = baseDescriptor;
    }

    public String g() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        return this.f3915a.toString();
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = byteBuffer.slice();
        byteBuffer.position(byteBuffer.position() + byteBuffer.remaining());
        try {
            this.b.rewind();
            this.f3915a = ObjectDescriptorFactory.a(-1, this.b.duplicate());
        } catch (IOException e2) {
            c.log(Level.WARNING, "Error parsing ObjectDescriptor", e2);
        } catch (IndexOutOfBoundsException e3) {
            c.log(Level.WARNING, "Error parsing ObjectDescriptor", e3);
        }
    }
}
