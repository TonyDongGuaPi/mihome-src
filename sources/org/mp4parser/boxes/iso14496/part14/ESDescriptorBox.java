package org.mp4parser.boxes.iso14496.part14;

import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BaseDescriptor;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.ESDescriptor;
import org.mp4parser.support.RequiresParseDetailAspect;

public class ESDescriptorBox extends AbstractDescriptorBox {
    public static final String c = "esds";
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;

    static {
        i();
    }

    private static void i() {
        Factory factory = new Factory("ESDescriptorBox.java", ESDescriptorBox.class);
        d = factory.a("method-execution", (Signature) factory.a("1", "getEsDescriptor", "org.mp4parser.boxes.iso14496.part14.ESDescriptorBox", "", "", "", "org.mp4parser.boxes.iso14496.part1.objectdescriptors.ESDescriptor"), 35);
        e = factory.a("method-execution", (Signature) factory.a("1", "setEsDescriptor", "org.mp4parser.boxes.iso14496.part14.ESDescriptorBox", "org.mp4parser.boxes.iso14496.part1.objectdescriptors.ESDescriptor", "esDescriptor", "", "void"), 39);
        f = factory.a("method-execution", (Signature) factory.a("1", "equals", "org.mp4parser.boxes.iso14496.part14.ESDescriptorBox", "java.lang.Object", DeviceTagInterface.q, "", "boolean"), 44);
        g = factory.a("method-execution", (Signature) factory.a("1", "hashCode", "org.mp4parser.boxes.iso14496.part14.ESDescriptorBox", "", "", "", "int"), 55);
    }

    public ESDescriptorBox() {
        super(c);
    }

    public ESDescriptor h() {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this));
        return (ESDescriptor) super.f();
    }

    public void a(ESDescriptor eSDescriptor) {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this, (Object) eSDescriptor));
        super.a((BaseDescriptor) eSDescriptor);
    }

    public boolean equals(Object obj) {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this, obj));
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ESDescriptorBox eSDescriptorBox = (ESDescriptorBox) obj;
        return this.b == null ? eSDescriptorBox.b == null : this.b.equals(eSDescriptorBox.b);
    }

    public int hashCode() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        if (this.b != null) {
            return this.b.hashCode();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        ESDescriptor h = h();
        if (h != null) {
            return (long) (h.l() + 4);
        }
        return (long) (this.b.remaining() + 4);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        ESDescriptor h = h();
        if (h != null) {
            byteBuffer.put((ByteBuffer) h.b().rewind());
        } else {
            byteBuffer.put(this.b.duplicate());
        }
    }
}
