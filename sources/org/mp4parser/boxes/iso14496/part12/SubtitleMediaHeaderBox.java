package org.mp4parser.boxes.iso14496.part12;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.RequiresParseDetailAspect;

public class SubtitleMediaHeaderBox extends AbstractMediaHeaderBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3896a = "sthd";
    private static final JoinPoint.StaticPart b = null;

    static {
        e();
    }

    private static void e() {
        Factory factory = new Factory("SubtitleMediaHeaderBox.java", SubtitleMediaHeaderBox.class);
        b = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.SubtitleMediaHeaderBox", "", "", "", "java.lang.String"), 30);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 4;
    }

    public SubtitleMediaHeaderBox() {
        super(f3896a);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(b, (Object) this, (Object) this));
        return "SubtitleMediaHeaderBox";
    }
}
