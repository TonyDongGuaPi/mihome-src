package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class ProgressiveDownloadInformationBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3873a = "pdin";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    List<Entry> b = Collections.emptyList();

    static {
        f();
    }

    private static void f() {
        Factory factory = new Factory("ProgressiveDownloadInformationBox.java", ProgressiveDownloadInformationBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getEntries", "org.mp4parser.boxes.iso14496.part12.ProgressiveDownloadInformationBox", "", "", "", "java.util.List"), 38);
        d = factory.a("method-execution", (Signature) factory.a("1", "setEntries", "org.mp4parser.boxes.iso14496.part12.ProgressiveDownloadInformationBox", "java.util.List", "entries", "", "void"), 42);
        e = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.ProgressiveDownloadInformationBox", "", "", "", "java.lang.String"), 57);
    }

    public ProgressiveDownloadInformationBox() {
        super(f3873a);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) ((this.b.size() * 8) + 4);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        for (Entry next : this.b) {
            IsoTypeWriter.b(byteBuffer, next.a());
            IsoTypeWriter.b(byteBuffer, next.b());
        }
    }

    public List<Entry> e() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return this.b;
    }

    public void a(List<Entry> list) {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this, (Object) list));
        this.b = list;
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = new LinkedList();
        while (byteBuffer.remaining() >= 8) {
            this.b.add(new Entry(IsoTypeReader.b(byteBuffer), IsoTypeReader.b(byteBuffer)));
        }
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return "ProgressiveDownloadInfoBox{entries=" + this.b + Operators.BLOCK_END;
    }

    public static class Entry {

        /* renamed from: a  reason: collision with root package name */
        long f3874a;
        long b;

        public Entry(long j, long j2) {
            this.f3874a = j;
            this.b = j2;
        }

        public long a() {
            return this.f3874a;
        }

        public void a(long j) {
            this.f3874a = j;
        }

        public long b() {
            return this.b;
        }

        public void b(long j) {
            this.b = j;
        }

        public String toString() {
            return "Entry{rate=" + this.f3874a + ", initialDelay=" + this.b + Operators.BLOCK_END;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Entry entry = (Entry) obj;
            return this.b == entry.b && this.f3874a == entry.f3874a;
        }

        public int hashCode() {
            return (((int) (this.f3874a ^ (this.f3874a >>> 32))) * 31) + ((int) (this.b ^ (this.b >>> 32)));
        }
    }
}
