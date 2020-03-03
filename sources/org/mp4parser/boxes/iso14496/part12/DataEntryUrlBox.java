package org.mp4parser.boxes.iso14496.part12;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;

public class DataEntryUrlBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3839a = "url ";
    private static final JoinPoint.StaticPart b = null;

    static {
        e();
    }

    private static void e() {
        Factory factory = new Factory("DataEntryUrlBox.java", DataEntryUrlBox.class);
        b = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.DataEntryUrlBox", "", "", "", "java.lang.String"), 51);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 4;
    }

    public DataEntryUrlBox() {
        super(f3839a);
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
        return "DataEntryUrlBox[]";
    }
}
