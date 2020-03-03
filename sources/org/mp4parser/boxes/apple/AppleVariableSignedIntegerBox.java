package org.mp4parser.boxes.apple;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReaderVariable;
import org.mp4parser.tools.IsoTypeWriterVariable;

public abstract class AppleVariableSignedIntegerBox extends AppleDataBox {
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    long d;
    int e = 1;

    static {
        l();
    }

    private static void l() {
        Factory factory = new Factory("AppleVariableSignedIntegerBox.java", AppleVariableSignedIntegerBox.class);
        f = factory.a("method-execution", (Signature) factory.a("1", "getIntLength", "org.mp4parser.boxes.apple.AppleVariableSignedIntegerBox", "", "", "", "int"), 19);
        g = factory.a("method-execution", (Signature) factory.a("1", "setIntLength", "org.mp4parser.boxes.apple.AppleVariableSignedIntegerBox", "int", "intLength", "", "void"), 23);
        h = factory.a("method-execution", (Signature) factory.a("1", "getValue", "org.mp4parser.boxes.apple.AppleVariableSignedIntegerBox", "", "", "", "long"), 27);
        i = factory.a("method-execution", (Signature) factory.a("1", "setValue", "org.mp4parser.boxes.apple.AppleVariableSignedIntegerBox", "long", "value", "", "void"), 35);
    }

    protected AppleVariableSignedIntegerBox(String str) {
        super(str, 15);
    }

    public int d() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.e;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this, Conversions.a(i2)));
        this.e = i2;
    }

    public long k() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        if (!x()) {
            w();
        }
        return this.d;
    }

    public void a(long j) {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this, Conversions.a(j)));
        if (j <= 127 && j > -128) {
            this.e = 1;
        } else if (j <= 32767 && j > -32768 && this.e < 2) {
            this.e = 2;
        } else if (j > 8388607 || j <= -8388608 || this.e >= 3) {
            this.e = 4;
        } else {
            this.e = 3;
        }
        this.d = j;
    }

    /* access modifiers changed from: protected */
    public byte[] e() {
        int f2 = f();
        ByteBuffer wrap = ByteBuffer.wrap(new byte[f2]);
        IsoTypeWriterVariable.a(this.d, wrap, f2);
        return wrap.array();
    }

    /* access modifiers changed from: protected */
    public void c(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        this.d = IsoTypeReaderVariable.a(byteBuffer, remaining);
        this.e = remaining;
    }

    /* access modifiers changed from: protected */
    public int f() {
        return this.e;
    }
}
