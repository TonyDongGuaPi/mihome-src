package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.IsoFile;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;

public class OriginalFormatBox extends AbstractBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3872a = "frma";
    static final /* synthetic */ boolean b = (!OriginalFormatBox.class.desiredAssertionStatus());
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private String c = "    ";

    private static void e() {
        Factory factory = new Factory("OriginalFormatBox.java", OriginalFormatBox.class);
        d = factory.a("method-execution", (Signature) factory.a("1", "getDataFormat", "org.mp4parser.boxes.iso14496.part12.OriginalFormatBox", "", "", "", "java.lang.String"), 42);
        e = factory.a("method-execution", (Signature) factory.a("1", "setDataFormat", "org.mp4parser.boxes.iso14496.part12.OriginalFormatBox", "java.lang.String", "dataFormat", "", "void"), 47);
        f = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.OriginalFormatBox", "", "", "", "java.lang.String"), 67);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 4;
    }

    static {
        e();
    }

    public OriginalFormatBox() {
        super(f3872a);
    }

    public String d() {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this));
        return this.c;
    }

    public void a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this, (Object) str));
        if (b || str.length() == 4) {
            this.c = str;
            return;
        }
        throw new AssertionError();
    }

    public void a(ByteBuffer byteBuffer) {
        this.c = IsoTypeReader.m(byteBuffer);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        byteBuffer.put(IsoFile.a(this.c));
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return "OriginalFormatBox[dataFormat=" + d() + Operators.ARRAY_END_STR;
    }
}
