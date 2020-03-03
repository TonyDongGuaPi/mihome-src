package org.mp4parser.boxes.iso14496.part12;

import com.alipay.sdk.util.i;
import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.mp4parser.IsoFile;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.DoNotParseDetail;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class FileTypeBox extends AbstractBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3847a = "ftyp";
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    private String b;
    private long c;
    private List<String> d = Collections.emptyList();

    static {
        g();
    }

    private static void g() {
        Factory factory = new Factory("FileTypeBox.java", FileTypeBox.class);
        e = factory.a("method-execution", (Signature) factory.a("1", "getMajorBrand", "org.mp4parser.boxes.iso14496.part12.FileTypeBox", "", "", "", "java.lang.String"), 85);
        f = factory.a("method-execution", (Signature) factory.a("1", "setMajorBrand", "org.mp4parser.boxes.iso14496.part12.FileTypeBox", "java.lang.String", "majorBrand", "", "void"), 94);
        g = factory.a("method-execution", (Signature) factory.a("1", "getMinorVersion", "org.mp4parser.boxes.iso14496.part12.FileTypeBox", "", "", "", "long"), 104);
        h = factory.a("method-execution", (Signature) factory.a("1", "setMinorVersion", "org.mp4parser.boxes.iso14496.part12.FileTypeBox", "long", "minorVersion", "", "void"), 113);
        i = factory.a("method-execution", (Signature) factory.a("1", "getCompatibleBrands", "org.mp4parser.boxes.iso14496.part12.FileTypeBox", "", "", "", "java.util.List"), 122);
        j = factory.a("method-execution", (Signature) factory.a("1", "setCompatibleBrands", "org.mp4parser.boxes.iso14496.part12.FileTypeBox", "java.util.List", "compatibleBrands", "", "void"), 126);
    }

    public FileTypeBox() {
        super("ftyp");
    }

    public FileTypeBox(String str, long j2, List<String> list) {
        super("ftyp");
        this.b = str;
        this.c = j2;
        this.d = list;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) ((this.d.size() * 4) + 8);
    }

    public void a(ByteBuffer byteBuffer) {
        this.b = IsoTypeReader.m(byteBuffer);
        this.c = IsoTypeReader.b(byteBuffer);
        int remaining = byteBuffer.remaining() / 4;
        this.d = new LinkedList();
        for (int i2 = 0; i2 < remaining; i2++) {
            this.d.add(IsoTypeReader.m(byteBuffer));
        }
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        byteBuffer.put(IsoFile.a(this.b));
        IsoTypeWriter.b(byteBuffer, this.c);
        for (String a2 : this.d) {
            byteBuffer.put(IsoFile.a(a2));
        }
    }

    public String d() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return this.b;
    }

    public void a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this, (Object) str));
        this.b = str;
    }

    public long e() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return this.c;
    }

    public void a(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this, Conversions.a(j2)));
        this.c = j2;
    }

    public List<String> f() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.d;
    }

    public void a(List<String> list) {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this, (Object) list));
        this.d = list;
    }

    @DoNotParseDetail
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FileTypeBox[");
        sb.append("majorBrand=");
        sb.append(d());
        sb.append(i.b);
        sb.append("minorVersion=");
        sb.append(e());
        for (String append : this.d) {
            sb.append(i.b);
            sb.append("compatibleBrand=");
            sb.append(append);
        }
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }
}
