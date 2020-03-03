package org.mp4parser.support;

import com.xiaomi.smarthome.framework.openapi.ApiConst;
import java.nio.ByteBuffer;
import org.mp4parser.FullBox;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public abstract class AbstractFullBox extends AbstractBox implements FullBox {
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;

    /* renamed from: a  reason: collision with root package name */
    private int f4101a;
    private int b;

    static {
        e();
    }

    private static void e() {
        Factory factory = new Factory("AbstractFullBox.java", AbstractFullBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "setVersion", "org.mp4parser.support.AbstractFullBox", "int", "version", "", "void"), 50);
        d = factory.a("method-execution", (Signature) factory.a("1", "setFlags", "org.mp4parser.support.AbstractFullBox", "int", ApiConst.K, "", "void"), 63);
    }

    protected AbstractFullBox(String str) {
        super(str);
    }

    protected AbstractFullBox(String str, byte[] bArr) {
        super(str, bArr);
    }

    @DoNotParseDetail
    public int ag_() {
        if (!this.s) {
            w();
        }
        return this.f4101a;
    }

    public void a(int i) {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this, Conversions.a(i)));
        this.f4101a = i;
    }

    @DoNotParseDetail
    public int d() {
        if (!this.s) {
            w();
        }
        return this.b;
    }

    public void b(int i) {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this, Conversions.a(i)));
        this.b = i;
    }

    /* access modifiers changed from: protected */
    public final long e(ByteBuffer byteBuffer) {
        this.f4101a = IsoTypeReader.f(byteBuffer);
        this.b = IsoTypeReader.c(byteBuffer);
        return 4;
    }

    /* access modifiers changed from: protected */
    public final void f(ByteBuffer byteBuffer) {
        IsoTypeWriter.d(byteBuffer, this.f4101a);
        IsoTypeWriter.a(byteBuffer, this.b);
    }
}
