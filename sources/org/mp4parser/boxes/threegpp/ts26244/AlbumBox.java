package org.mp4parser.boxes.threegpp.ts26244;

import com.alipay.sdk.util.i;
import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import org.mp4parser.tools.Utf8;

public class AlbumBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3966a = "albm";
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    private static final JoinPoint.StaticPart k = null;
    private String b;
    private String c;
    private int d;

    static {
        h();
    }

    private static void h() {
        Factory factory = new Factory("AlbumBox.java", AlbumBox.class);
        e = factory.a("method-execution", (Signature) factory.a("1", "getLanguage", "org.mp4parser.boxes.threegpp.ts26244.AlbumBox", "", "", "", "java.lang.String"), 52);
        f = factory.a("method-execution", (Signature) factory.a("1", "setLanguage", "org.mp4parser.boxes.threegpp.ts26244.AlbumBox", "java.lang.String", "language", "", "void"), 56);
        g = factory.a("method-execution", (Signature) factory.a("1", "getAlbumTitle", "org.mp4parser.boxes.threegpp.ts26244.AlbumBox", "", "", "", "java.lang.String"), 60);
        h = factory.a("method-execution", (Signature) factory.a("1", "setAlbumTitle", "org.mp4parser.boxes.threegpp.ts26244.AlbumBox", "java.lang.String", "albumTitle", "", "void"), 64);
        i = factory.a("method-execution", (Signature) factory.a("1", "getTrackNumber", "org.mp4parser.boxes.threegpp.ts26244.AlbumBox", "", "", "", "int"), 68);
        j = factory.a("method-execution", (Signature) factory.a("1", "setTrackNumber", "org.mp4parser.boxes.threegpp.ts26244.AlbumBox", "int", "trackNumber", "", "void"), 72);
        k = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.threegpp.ts26244.AlbumBox", "", "", "", "java.lang.String"), 104);
    }

    public AlbumBox() {
        super(f3966a);
    }

    public String e() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return this.b;
    }

    public void a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this, (Object) str));
        this.b = str;
    }

    public String f() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return this.c;
    }

    public void b(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this, (Object) str));
        this.c = str;
    }

    public int g() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.d;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this, Conversions.a(i2)));
        this.d = i2;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        int i2 = 1;
        int b2 = Utf8.b(this.c) + 6 + 1;
        if (this.d == -1) {
            i2 = 0;
        }
        return (long) (b2 + i2);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.l(byteBuffer);
        this.c = IsoTypeReader.g(byteBuffer);
        if (byteBuffer.remaining() > 0) {
            this.d = IsoTypeReader.f(byteBuffer);
        } else {
            this.d = -1;
        }
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.a(byteBuffer, this.b);
        byteBuffer.put(Utf8.a(this.c));
        byteBuffer.put((byte) 0);
        if (this.d != -1) {
            IsoTypeWriter.d(byteBuffer, this.d);
        }
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this));
        StringBuilder sb = new StringBuilder();
        sb.append("AlbumBox[language=");
        sb.append(e());
        sb.append(i.b);
        sb.append("albumTitle=");
        sb.append(f());
        if (this.d >= 0) {
            sb.append(";trackNumber=");
            sb.append(g());
        }
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }
}
