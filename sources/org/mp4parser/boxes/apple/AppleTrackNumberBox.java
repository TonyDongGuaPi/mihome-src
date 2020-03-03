package org.mp4parser.boxes.apple;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.RequiresParseDetailAspect;

public class AppleTrackNumberBox extends AppleDataBox {
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    int d;
    int e;

    static {
        l();
    }

    private static void l() {
        Factory factory = new Factory("AppleTrackNumberBox.java", AppleTrackNumberBox.class);
        f = factory.a("method-execution", (Signature) factory.a("1", "getA", "org.mp4parser.boxes.apple.AppleTrackNumberBox", "", "", "", "int"), 16);
        g = factory.a("method-execution", (Signature) factory.a("1", "setA", "org.mp4parser.boxes.apple.AppleTrackNumberBox", "int", "a", "", "void"), 20);
        h = factory.a("method-execution", (Signature) factory.a("1", "getB", "org.mp4parser.boxes.apple.AppleTrackNumberBox", "", "", "", "int"), 24);
        i = factory.a("method-execution", (Signature) factory.a("1", "setB", "org.mp4parser.boxes.apple.AppleTrackNumberBox", "int", "b", "", "void"), 28);
    }

    /* access modifiers changed from: protected */
    public int f() {
        return 8;
    }

    public AppleTrackNumberBox() {
        super("trkn", 0);
    }

    public int d() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.d;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this, Conversions.a(i2)));
        this.d = i2;
    }

    public int k() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        return this.e;
    }

    public void d(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this, Conversions.a(i2)));
        this.e = i2;
    }

    /* access modifiers changed from: protected */
    public byte[] e() {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.putInt(this.d);
        allocate.putInt(this.e);
        return allocate.array();
    }

    /* access modifiers changed from: protected */
    public void c(ByteBuffer byteBuffer) {
        this.d = byteBuffer.getInt();
        this.e = byteBuffer.getInt();
    }
}
