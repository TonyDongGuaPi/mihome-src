package org.mp4parser.boxes.apple;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.RequiresParseDetailAspect;

public class AppleDiskNumberBox extends AppleDataBox {
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    int d;
    short e;

    static {
        l();
    }

    private static void l() {
        Factory factory = new Factory("AppleDiskNumberBox.java", AppleDiskNumberBox.class);
        f = factory.a("method-execution", (Signature) factory.a("1", "getA", "org.mp4parser.boxes.apple.AppleDiskNumberBox", "", "", "", "int"), 16);
        g = factory.a("method-execution", (Signature) factory.a("1", "setA", "org.mp4parser.boxes.apple.AppleDiskNumberBox", "int", "a", "", "void"), 20);
        h = factory.a("method-execution", (Signature) factory.a("1", "getB", "org.mp4parser.boxes.apple.AppleDiskNumberBox", "", "", "", "short"), 24);
        i = factory.a("method-execution", (Signature) factory.a("1", "setB", "org.mp4parser.boxes.apple.AppleDiskNumberBox", "short", "b", "", "void"), 28);
    }

    /* access modifiers changed from: protected */
    public int f() {
        return 6;
    }

    public AppleDiskNumberBox() {
        super("disk", 0);
    }

    public int d() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.d;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this, Conversions.a(i2)));
        this.d = i2;
    }

    public short k() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        return this.e;
    }

    public void a(short s) {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this, Conversions.a(s)));
        this.e = s;
    }

    /* access modifiers changed from: protected */
    public byte[] e() {
        ByteBuffer allocate = ByteBuffer.allocate(6);
        allocate.putInt(this.d);
        allocate.putShort(this.e);
        return allocate.array();
    }

    /* access modifiers changed from: protected */
    public void c(ByteBuffer byteBuffer) {
        this.d = byteBuffer.getInt();
        this.e = byteBuffer.getShort();
    }
}
