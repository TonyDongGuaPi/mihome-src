package org.mp4parser.boxes.dece;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.DoNotParseDetail;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.Utf8;

public class AssetInformationBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3805a = "ainf";
    static final /* synthetic */ boolean d = (!AssetInformationBox.class.desiredAssertionStatus());
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    String b = "";
    String c = "0000";

    private static void h() {
        Factory factory = new Factory("AssetInformationBox.java", AssetInformationBox.class);
        e = factory.a("method-execution", (Signature) factory.a("1", "getApid", "org.mp4parser.boxes.dece.AssetInformationBox", "", "", "", "java.lang.String"), 80);
        f = factory.a("method-execution", (Signature) factory.a("1", "setApid", "org.mp4parser.boxes.dece.AssetInformationBox", "java.lang.String", "apid", "", "void"), 84);
        g = factory.a("method-execution", (Signature) factory.a("1", "getProfileVersion", "org.mp4parser.boxes.dece.AssetInformationBox", "", "", "", "java.lang.String"), 88);
        h = factory.a("method-execution", (Signature) factory.a("1", "setProfileVersion", "org.mp4parser.boxes.dece.AssetInformationBox", "java.lang.String", "profileVersion", "", "void"), 92);
    }

    static {
        h();
    }

    public AssetInformationBox() {
        super(f3805a);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) (Utf8.b(this.b) + 9);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        if (ag_() == 0) {
            byteBuffer.put(Utf8.a(this.c), 0, 4);
            byteBuffer.put(Utf8.a(this.b));
            byteBuffer.put((byte) 0);
            return;
        }
        throw new RuntimeException("Unknown ainf version " + ag_());
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.c = IsoTypeReader.a(byteBuffer, 4);
        this.b = IsoTypeReader.g(byteBuffer);
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
        if (d || (str != null && str.length() == 4)) {
            this.c = str;
            return;
        }
        throw new AssertionError();
    }

    @DoNotParseDetail
    public boolean g() {
        return (d() & 1) == 1;
    }

    @DoNotParseDetail
    public void a(boolean z) {
        int d2 = d();
        if (!(g() ^ z)) {
            return;
        }
        if (z) {
            b(d2 | 1);
        } else {
            b(16777214 & d2);
        }
    }

    public static class Entry {

        /* renamed from: a  reason: collision with root package name */
        public String f3806a;
        public String b;
        public String c;

        public Entry(String str, String str2, String str3) {
            this.f3806a = str;
            this.b = str2;
            this.c = str3;
        }

        public String toString() {
            return "{namespace='" + this.f3806a + Operators.SINGLE_QUOTE + ", profileLevelIdc='" + this.b + Operators.SINGLE_QUOTE + ", assetId='" + this.c + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Entry entry = (Entry) obj;
            return this.c.equals(entry.c) && this.f3806a.equals(entry.f3806a) && this.b.equals(entry.b);
        }

        public int hashCode() {
            return (((this.f3806a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
        }

        public int a() {
            return Utf8.b(this.f3806a) + 3 + Utf8.b(this.b) + Utf8.b(this.c);
        }
    }
}
