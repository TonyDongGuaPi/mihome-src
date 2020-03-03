package org.mp4parser.boxes.apple;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.RequiresParseDetailAspect;

public class AppleCoverBox extends AppleDataBox {
    private static final int d = 13;
    private static final int e = 14;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private byte[] f;

    static {
        k();
    }

    private static void k() {
        Factory factory = new Factory("AppleCoverBox.java", AppleCoverBox.class);
        g = factory.a("method-execution", (Signature) factory.a("1", "getCoverData", "org.mp4parser.boxes.apple.AppleCoverBox", "", "", "", "[B"), 20);
        h = factory.a("method-execution", (Signature) factory.a("1", "setJpg", "org.mp4parser.boxes.apple.AppleCoverBox", "[B", "data", "", "void"), 24);
        i = factory.a("method-execution", (Signature) factory.a("1", "setPng", "org.mp4parser.boxes.apple.AppleCoverBox", "[B", "data", "", "void"), 28);
    }

    public AppleCoverBox() {
        super("covr", 1);
    }

    public byte[] d() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return this.f;
    }

    public void a(byte[] bArr) {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this, (Object) bArr));
        a(bArr, 13);
    }

    public void b(byte[] bArr) {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this, (Object) bArr));
        a(bArr, 14);
    }

    /* access modifiers changed from: protected */
    public byte[] e() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public void c(ByteBuffer byteBuffer) {
        this.f = new byte[byteBuffer.limit()];
        byteBuffer.get(this.f);
    }

    /* access modifiers changed from: protected */
    public int f() {
        return this.f.length;
    }

    private void a(byte[] bArr, int i2) {
        this.f = bArr;
        this.f3785a = i2;
    }
}
