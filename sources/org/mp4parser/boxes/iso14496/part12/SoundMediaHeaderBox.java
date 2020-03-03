package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.payment.data.MibiConstants;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class SoundMediaHeaderBox extends AbstractMediaHeaderBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3891a = "smhd";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    private float b;

    static {
        f();
    }

    private static void f() {
        Factory factory = new Factory("SoundMediaHeaderBox.java", SoundMediaHeaderBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", MibiConstants.bz, "org.mp4parser.boxes.iso14496.part12.SoundMediaHeaderBox", "", "", "", "float"), 36);
        d = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.SoundMediaHeaderBox", "", "", "", "java.lang.String"), 58);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 8;
    }

    public SoundMediaHeaderBox() {
        super("smhd");
    }

    public float e() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return this.b;
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.k(byteBuffer);
        IsoTypeReader.d(byteBuffer);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.c(byteBuffer, (double) this.b);
        IsoTypeWriter.b(byteBuffer, 0);
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this));
        return "SoundMediaHeaderBox[balance=" + e() + Operators.ARRAY_END_STR;
    }
}
