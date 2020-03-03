package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.Utf8;

public class DataEntryUrnBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3840a = "urn ";
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private String b;
    private String c;

    static {
        g();
    }

    private static void g() {
        Factory factory = new Factory("DataEntryUrnBox.java", DataEntryUrnBox.class);
        d = factory.a("method-execution", (Signature) factory.a("1", "getName", "org.mp4parser.boxes.iso14496.part12.DataEntryUrnBox", "", "", "", "java.lang.String"), 40);
        e = factory.a("method-execution", (Signature) factory.a("1", "getLocation", "org.mp4parser.boxes.iso14496.part12.DataEntryUrnBox", "", "", "", "java.lang.String"), 44);
        f = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.DataEntryUrnBox", "", "", "", "java.lang.String"), 67);
    }

    public DataEntryUrnBox() {
        super(f3840a);
    }

    public String e() {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this));
        return this.b;
    }

    public String f() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return this.c;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) (Utf8.b(this.b) + 1 + Utf8.b(this.c) + 1);
    }

    public void a(ByteBuffer byteBuffer) {
        this.b = IsoTypeReader.g(byteBuffer);
        this.c = IsoTypeReader.g(byteBuffer);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        byteBuffer.put(Utf8.a(this.b));
        byteBuffer.put((byte) 0);
        byteBuffer.put(Utf8.a(this.c));
        byteBuffer.put((byte) 0);
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return "DataEntryUrlBox[name=" + e() + ";location=" + f() + Operators.ARRAY_END_STR;
    }
}
