package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class VideoMediaHeaderBox extends AbstractMediaHeaderBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3913a = "vmhd";
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private int b = 0;
    private int[] c = new int[3];

    static {
        g();
    }

    private static void g() {
        Factory factory = new Factory("VideoMediaHeaderBox.java", VideoMediaHeaderBox.class);
        d = factory.a("method-execution", (Signature) factory.a("1", "getGraphicsmode", "org.mp4parser.boxes.iso14496.part12.VideoMediaHeaderBox", "", "", "", "int"), 39);
        e = factory.a("method-execution", (Signature) factory.a("1", "setGraphicsmode", "org.mp4parser.boxes.iso14496.part12.VideoMediaHeaderBox", "int", "graphicsmode", "", "void"), 43);
        f = factory.a("method-execution", (Signature) factory.a("1", "getOpcolor", "org.mp4parser.boxes.iso14496.part12.VideoMediaHeaderBox", "", "", "", "[I"), 47);
        g = factory.a("method-execution", (Signature) factory.a("1", "setOpcolor", "org.mp4parser.boxes.iso14496.part12.VideoMediaHeaderBox", "[I", "opcolor", "", "void"), 51);
        h = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.VideoMediaHeaderBox", "", "", "", "java.lang.String"), 78);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 12;
    }

    public VideoMediaHeaderBox() {
        super("vmhd");
        b(1);
    }

    public int e() {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this));
        return this.b;
    }

    public void c(int i) {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this, Conversions.a(i)));
        this.b = i;
    }

    public int[] f() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.c;
    }

    public void a(int[] iArr) {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this, (Object) iArr));
        this.c = iArr;
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.d(byteBuffer);
        this.c = new int[3];
        for (int i = 0; i < 3; i++) {
            this.c[i] = IsoTypeReader.d(byteBuffer);
        }
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.b(byteBuffer, this.b);
        for (int b2 : this.c) {
            IsoTypeWriter.b(byteBuffer, b2);
        }
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        return "VideoMediaHeaderBox[graphicsmode=" + e() + ";opcolor0=" + f()[0] + ";opcolor1=" + f()[1] + ";opcolor2=" + f()[2] + Operators.ARRAY_END_STR;
    }
}
