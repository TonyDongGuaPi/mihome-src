package org.mp4parser.boxes.dece;

import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import org.mp4parser.tools.Utf8;

public class ContentInformationBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3809a = "cinf";
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    private static final JoinPoint.StaticPart k = null;
    private static final JoinPoint.StaticPart l = null;
    private static final JoinPoint.StaticPart m = null;
    private static final JoinPoint.StaticPart n = null;
    private static final JoinPoint.StaticPart o = null;
    private static final JoinPoint.StaticPart p = null;
    private static final JoinPoint.StaticPart q = null;
    private static final JoinPoint.StaticPart u = null;
    private static final JoinPoint.StaticPart v = null;
    private static final JoinPoint.StaticPart w = null;
    private static final JoinPoint.StaticPart x = null;
    private static final JoinPoint.StaticPart y = null;
    String b;
    String c;
    String d;
    String e;
    String f;
    Map<String, String> g = new LinkedHashMap();
    Map<String, String> h = new LinkedHashMap();

    static {
        l();
    }

    private static void l() {
        Factory factory = new Factory("ContentInformationBox.java", ContentInformationBox.class);
        i = factory.a("method-execution", (Signature) factory.a("1", "getMimeSubtypeName", "org.mp4parser.boxes.dece.ContentInformationBox", "", "", "", "java.lang.String"), 114);
        j = factory.a("method-execution", (Signature) factory.a("1", "setMimeSubtypeName", "org.mp4parser.boxes.dece.ContentInformationBox", "java.lang.String", "mimeSubtypeName", "", "void"), 118);
        v = factory.a("method-execution", (Signature) factory.a("1", "getBrandEntries", "org.mp4parser.boxes.dece.ContentInformationBox", "", "", "", "java.util.Map"), 154);
        w = factory.a("method-execution", (Signature) factory.a("1", "setBrandEntries", "org.mp4parser.boxes.dece.ContentInformationBox", "java.util.Map", "brandEntries", "", "void"), 158);
        x = factory.a("method-execution", (Signature) factory.a("1", "getIdEntries", "org.mp4parser.boxes.dece.ContentInformationBox", "", "", "", "java.util.Map"), 162);
        y = factory.a("method-execution", (Signature) factory.a("1", "setIdEntries", "org.mp4parser.boxes.dece.ContentInformationBox", "java.util.Map", "idEntries", "", "void"), 166);
        k = factory.a("method-execution", (Signature) factory.a("1", "getProfileLevelIdc", "org.mp4parser.boxes.dece.ContentInformationBox", "", "", "", "java.lang.String"), 122);
        l = factory.a("method-execution", (Signature) factory.a("1", "setProfileLevelIdc", "org.mp4parser.boxes.dece.ContentInformationBox", "java.lang.String", "profileLevelIdc", "", "void"), 126);
        m = factory.a("method-execution", (Signature) factory.a("1", "getCodecs", "org.mp4parser.boxes.dece.ContentInformationBox", "", "", "", "java.lang.String"), 130);
        n = factory.a("method-execution", (Signature) factory.a("1", "setCodecs", "org.mp4parser.boxes.dece.ContentInformationBox", "java.lang.String", "codecs", "", "void"), 134);
        o = factory.a("method-execution", (Signature) factory.a("1", "getProtection", "org.mp4parser.boxes.dece.ContentInformationBox", "", "", "", "java.lang.String"), 138);
        p = factory.a("method-execution", (Signature) factory.a("1", "setProtection", "org.mp4parser.boxes.dece.ContentInformationBox", "java.lang.String", "protection", "", "void"), 142);
        q = factory.a("method-execution", (Signature) factory.a("1", "getLanguages", "org.mp4parser.boxes.dece.ContentInformationBox", "", "", "", "java.lang.String"), 146);
        u = factory.a("method-execution", (Signature) factory.a("1", "setLanguages", "org.mp4parser.boxes.dece.ContentInformationBox", "java.lang.String", "languages", "", "void"), 150);
    }

    public ContentInformationBox() {
        super(f3809a);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        long b2 = ((long) (Utf8.b(this.b) + 1)) + 4 + ((long) (Utf8.b(this.c) + 1)) + ((long) (Utf8.b(this.d) + 1)) + ((long) (Utf8.b(this.e) + 1)) + ((long) (Utf8.b(this.f) + 1)) + 1;
        for (Map.Entry next : this.g.entrySet()) {
            b2 = b2 + ((long) (Utf8.b((String) next.getKey()) + 1)) + ((long) (Utf8.b((String) next.getValue()) + 1));
        }
        long j2 = b2 + 1;
        for (Map.Entry next2 : this.h.entrySet()) {
            j2 = j2 + ((long) (Utf8.b((String) next2.getKey()) + 1)) + ((long) (Utf8.b((String) next2.getValue()) + 1));
        }
        return j2;
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.c(byteBuffer, this.b);
        IsoTypeWriter.c(byteBuffer, this.c);
        IsoTypeWriter.c(byteBuffer, this.d);
        IsoTypeWriter.c(byteBuffer, this.e);
        IsoTypeWriter.c(byteBuffer, this.f);
        IsoTypeWriter.d(byteBuffer, this.g.size());
        for (Map.Entry next : this.g.entrySet()) {
            IsoTypeWriter.c(byteBuffer, (String) next.getKey());
            IsoTypeWriter.c(byteBuffer, (String) next.getValue());
        }
        IsoTypeWriter.d(byteBuffer, this.h.size());
        for (Map.Entry next2 : this.h.entrySet()) {
            IsoTypeWriter.c(byteBuffer, (String) next2.getKey());
            IsoTypeWriter.c(byteBuffer, (String) next2.getValue());
        }
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.g(byteBuffer);
        this.c = IsoTypeReader.g(byteBuffer);
        this.d = IsoTypeReader.g(byteBuffer);
        this.e = IsoTypeReader.g(byteBuffer);
        this.f = IsoTypeReader.g(byteBuffer);
        int f2 = IsoTypeReader.f(byteBuffer);
        while (true) {
            int i2 = f2 - 1;
            if (f2 <= 0) {
                break;
            }
            this.g.put(IsoTypeReader.g(byteBuffer), IsoTypeReader.g(byteBuffer));
            f2 = i2;
        }
        int f3 = IsoTypeReader.f(byteBuffer);
        while (true) {
            int i3 = f3 - 1;
            if (f3 > 0) {
                this.h.put(IsoTypeReader.g(byteBuffer), IsoTypeReader.g(byteBuffer));
                f3 = i3;
            } else {
                return;
            }
        }
    }

    public String e() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.b;
    }

    public void a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this, (Object) str));
        this.b = str;
    }

    public String f() {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this));
        return this.c;
    }

    public void b(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this, (Object) str));
        this.c = str;
    }

    public String g() {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this));
        return this.d;
    }

    public void c(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this, (Object) str));
        this.d = str;
    }

    public String h() {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this));
        return this.e;
    }

    public void d(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this, (Object) str));
        this.e = str;
    }

    public String i() {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this));
        return this.f;
    }

    public void e(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this, (Object) str));
        this.f = str;
    }

    public Map<String, String> j() {
        RequiresParseDetailAspect.a().a(Factory.a(v, (Object) this, (Object) this));
        return this.g;
    }

    public void a(Map<String, String> map) {
        RequiresParseDetailAspect.a().a(Factory.a(w, (Object) this, (Object) this, (Object) map));
        this.g = map;
    }

    public Map<String, String> k() {
        RequiresParseDetailAspect.a().a(Factory.a(x, (Object) this, (Object) this));
        return this.h;
    }

    public void b(Map<String, String> map) {
        RequiresParseDetailAspect.a().a(Factory.a(y, (Object) this, (Object) this, (Object) map));
        this.h = map;
    }

    public static class BrandEntry {

        /* renamed from: a  reason: collision with root package name */
        String f3810a;
        String b;

        public BrandEntry(String str, String str2) {
            this.f3810a = str;
            this.b = str2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            BrandEntry brandEntry = (BrandEntry) obj;
            if (this.f3810a == null ? brandEntry.f3810a == null : this.f3810a.equals(brandEntry.f3810a)) {
                return this.b == null ? brandEntry.b == null : this.b.equals(brandEntry.b);
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = (this.f3810a != null ? this.f3810a.hashCode() : 0) * 31;
            if (this.b != null) {
                i = this.b.hashCode();
            }
            return hashCode + i;
        }
    }
}
