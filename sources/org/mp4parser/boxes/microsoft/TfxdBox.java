package org.mp4parser.boxes.microsoft;

import cn.com.fmsh.tsm.business.constants.Constants;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class TfxdBox extends AbstractFullBox {
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;

    /* renamed from: a  reason: collision with root package name */
    public long f3941a;
    public long b;

    static {
        g();
    }

    private static void g() {
        Factory factory = new Factory("TfxdBox.java", TfxdBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getFragmentAbsoluteTime", "org.mp4parser.boxes.microsoft.TfxdBox", "", "", "", "long"), 79);
        d = factory.a("method-execution", (Signature) factory.a("1", "getFragmentAbsoluteDuration", "org.mp4parser.boxes.microsoft.TfxdBox", "", "", "", "long"), 83);
    }

    public TfxdBox() {
        super("uuid");
    }

    public byte[] af_() {
        return new byte[]{Constants.TagName.PUBLISH_END_TIME, Ascii.GS, Constants.TagName.CITY_CODE, 5, Constants.TagName.INVOICE_TOKEN, -43, Constants.TagName.TERMINAL_OS_VERSION, -26, Byte.MIN_VALUE, -30, 20, Ascii.GS, -81, -9, 87, Constants.TagName.APP_TYPE};
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) (ag_() == 1 ? 20 : 12);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        if (ag_() == 1) {
            this.f3941a = IsoTypeReader.h(byteBuffer);
            this.b = IsoTypeReader.h(byteBuffer);
            return;
        }
        this.f3941a = IsoTypeReader.b(byteBuffer);
        this.b = IsoTypeReader.b(byteBuffer);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        if (ag_() == 1) {
            IsoTypeWriter.a(byteBuffer, this.f3941a);
            IsoTypeWriter.a(byteBuffer, this.b);
            return;
        }
        IsoTypeWriter.b(byteBuffer, this.f3941a);
        IsoTypeWriter.b(byteBuffer, this.b);
    }

    public long e() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return this.f3941a;
    }

    public long f() {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this));
        return this.b;
    }
}
