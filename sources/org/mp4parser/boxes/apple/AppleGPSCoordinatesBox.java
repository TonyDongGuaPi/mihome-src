package org.mp4parser.boxes.apple;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.Utf8;

public class AppleGPSCoordinatesBox extends AbstractBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3788a = "©xyz";
    private static final int d = 5575;
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    String b;
    int c = d;

    static {
        e();
    }

    private static void e() {
        Factory factory = new Factory("AppleGPSCoordinatesBox.java", AppleGPSCoordinatesBox.class);
        e = factory.a("method-execution", (Signature) factory.a("1", "getValue", "org.mp4parser.boxes.apple.AppleGPSCoordinatesBox", "", "", "", "java.lang.String"), 22);
        f = factory.a("method-execution", (Signature) factory.a("1", "setValue", "org.mp4parser.boxes.apple.AppleGPSCoordinatesBox", "java.lang.String", "iso6709String", "", "void"), 26);
        g = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.apple.AppleGPSCoordinatesBox", "", "", "", "java.lang.String"), 52);
    }

    public AppleGPSCoordinatesBox() {
        super(f3788a);
    }

    public String d() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return this.b;
    }

    public void a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this, (Object) str));
        this.c = d;
        this.b = str;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) (Utf8.b(this.b) + 4);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        byteBuffer.putShort((short) this.b.length());
        byteBuffer.putShort((short) this.c);
        byteBuffer.put(Utf8.a(this.b));
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        int i = byteBuffer.getShort();
        this.c = byteBuffer.getShort();
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        this.b = Utf8.a(bArr);
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return "AppleGPSCoordinatesBox[" + this.b + Operators.ARRAY_END_STR;
    }
}
