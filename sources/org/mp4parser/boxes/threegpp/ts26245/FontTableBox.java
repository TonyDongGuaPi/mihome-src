package org.mp4parser.boxes.threegpp.ts26245;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import org.mp4parser.tools.Utf8;

public class FontTableBox extends AbstractBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3978a = "ftab";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    List<FontRecord> b = new LinkedList();

    static {
        e();
    }

    private static void e() {
        Factory factory = new Factory("FontTableBox.java", FontTableBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getEntries", "org.mp4parser.boxes.threegpp.ts26245.FontTableBox", "", "", "", "java.util.List"), 52);
        d = factory.a("method-execution", (Signature) factory.a("1", "setEntries", "org.mp4parser.boxes.threegpp.ts26245.FontTableBox", "java.util.List", "entries", "", "void"), 56);
    }

    public FontTableBox() {
        super(f3978a);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        int i = 2;
        for (FontRecord a2 : this.b) {
            i += a2.a();
        }
        return (long) i;
    }

    public void a(ByteBuffer byteBuffer) {
        int d2 = IsoTypeReader.d(byteBuffer);
        for (int i = 0; i < d2; i++) {
            FontRecord fontRecord = new FontRecord();
            fontRecord.a(byteBuffer);
            this.b.add(fontRecord);
        }
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        IsoTypeWriter.b(byteBuffer, this.b.size());
        for (FontRecord b2 : this.b) {
            b2.b(byteBuffer);
        }
    }

    public List<FontRecord> d() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return this.b;
    }

    public void a(List<FontRecord> list) {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this, (Object) list));
        this.b = list;
    }

    public static class FontRecord {

        /* renamed from: a  reason: collision with root package name */
        int f3979a;
        String b;

        public FontRecord() {
        }

        public FontRecord(int i, String str) {
            this.f3979a = i;
            this.b = str;
        }

        public void a(ByteBuffer byteBuffer) {
            this.f3979a = IsoTypeReader.d(byteBuffer);
            this.b = IsoTypeReader.a(byteBuffer, IsoTypeReader.f(byteBuffer));
        }

        public void b(ByteBuffer byteBuffer) {
            IsoTypeWriter.b(byteBuffer, this.f3979a);
            IsoTypeWriter.d(byteBuffer, this.b.length());
            byteBuffer.put(Utf8.a(this.b));
        }

        public int a() {
            return Utf8.b(this.b) + 3;
        }

        public String toString() {
            return "FontRecord{fontId=" + this.f3979a + ", fontname='" + this.b + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }
    }
}
