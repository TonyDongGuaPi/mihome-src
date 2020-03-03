package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class HintMediaHeaderBox extends AbstractMediaHeaderBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3851a = "hmhd";
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    private int b;
    private int c;
    private long d;
    private long e;

    static {
        i();
    }

    private static void i() {
        Factory factory = new Factory("HintMediaHeaderBox.java", HintMediaHeaderBox.class);
        f = factory.a("method-execution", (Signature) factory.a("1", "getMaxPduSize", "org.mp4parser.boxes.iso14496.part12.HintMediaHeaderBox", "", "", "", "int"), 42);
        g = factory.a("method-execution", (Signature) factory.a("1", "getAvgPduSize", "org.mp4parser.boxes.iso14496.part12.HintMediaHeaderBox", "", "", "", "int"), 46);
        h = factory.a("method-execution", (Signature) factory.a("1", "getMaxBitrate", "org.mp4parser.boxes.iso14496.part12.HintMediaHeaderBox", "", "", "", "long"), 50);
        i = factory.a("method-execution", (Signature) factory.a("1", "getAvgBitrate", "org.mp4parser.boxes.iso14496.part12.HintMediaHeaderBox", "", "", "", "long"), 54);
        j = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.HintMediaHeaderBox", "", "", "", "java.lang.String"), 84);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 20;
    }

    public HintMediaHeaderBox() {
        super("hmhd");
    }

    public int e() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.b;
    }

    public int f() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return this.c;
    }

    public long g() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        return this.d;
    }

    public long h() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.e;
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.d(byteBuffer);
        this.c = IsoTypeReader.d(byteBuffer);
        this.d = IsoTypeReader.b(byteBuffer);
        this.e = IsoTypeReader.b(byteBuffer);
        IsoTypeReader.b(byteBuffer);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.b(byteBuffer, this.b);
        IsoTypeWriter.b(byteBuffer, this.c);
        IsoTypeWriter.b(byteBuffer, this.d);
        IsoTypeWriter.b(byteBuffer, this.e);
        IsoTypeWriter.b(byteBuffer, 0);
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this));
        return "HintMediaHeaderBox{maxPduSize=" + this.b + ", avgPduSize=" + this.c + ", maxBitrate=" + this.d + ", avgBitrate=" + this.e + Operators.BLOCK_END;
    }
}
