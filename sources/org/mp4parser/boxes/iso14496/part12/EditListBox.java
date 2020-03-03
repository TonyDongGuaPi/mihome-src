package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class EditListBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3845a = "elst";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    private List<Entry> b = new LinkedList();

    static {
        f();
    }

    private static void f() {
        Factory factory = new Factory("EditListBox.java", EditListBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getEntries", "org.mp4parser.boxes.iso14496.part12.EditListBox", "", "", "", "java.util.List"), 67);
        d = factory.a("method-execution", (Signature) factory.a("1", "setEntries", "org.mp4parser.boxes.iso14496.part12.EditListBox", "java.util.List", "entries", "", "void"), 71);
        e = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.EditListBox", "", "", "", "java.lang.String"), 107);
    }

    public EditListBox() {
        super(f3845a);
    }

    public List<Entry> e() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return this.b;
    }

    public void a(List<Entry> list) {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this, (Object) list));
        this.b = list;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        if (ag_() == 1) {
            return ((long) (this.b.size() * 20)) + 8;
        }
        return ((long) (this.b.size() * 12)) + 8;
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        int a2 = CastUtils.a(IsoTypeReader.b(byteBuffer));
        this.b = new LinkedList();
        for (int i = 0; i < a2; i++) {
            this.b.add(new Entry(this, byteBuffer));
        }
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.b(byteBuffer, (long) this.b.size());
        for (Entry a2 : this.b) {
            a2.a(byteBuffer);
        }
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return "EditListBox{entries=" + this.b + Operators.BLOCK_END;
    }

    public static class Entry {

        /* renamed from: a  reason: collision with root package name */
        EditListBox f3846a;
        private long b;
        private long c;
        private double d;

        public Entry(EditListBox editListBox, long j, long j2, double d2) {
            this.b = j;
            this.c = j2;
            this.d = d2;
            this.f3846a = editListBox;
        }

        public Entry(EditListBox editListBox, ByteBuffer byteBuffer) {
            if (editListBox.ag_() == 1) {
                this.b = IsoTypeReader.h(byteBuffer);
                this.c = byteBuffer.getLong();
                this.d = IsoTypeReader.i(byteBuffer);
            } else {
                this.b = IsoTypeReader.b(byteBuffer);
                this.c = (long) byteBuffer.getInt();
                this.d = IsoTypeReader.i(byteBuffer);
            }
            this.f3846a = editListBox;
        }

        public long a() {
            return this.b;
        }

        public void a(long j) {
            this.b = j;
        }

        public long b() {
            return this.c;
        }

        public void b(long j) {
            this.c = j;
        }

        public double c() {
            return this.d;
        }

        public void a(double d2) {
            this.d = d2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Entry entry = (Entry) obj;
            return this.c == entry.c && this.b == entry.b;
        }

        public int hashCode() {
            return (((int) (this.b ^ (this.b >>> 32))) * 31) + ((int) (this.c ^ (this.c >>> 32)));
        }

        public void a(ByteBuffer byteBuffer) {
            if (this.f3846a.ag_() == 1) {
                IsoTypeWriter.a(byteBuffer, this.b);
                byteBuffer.putLong(this.c);
            } else {
                IsoTypeWriter.b(byteBuffer, (long) CastUtils.a(this.b));
                byteBuffer.putInt(CastUtils.a(this.c));
            }
            IsoTypeWriter.a(byteBuffer, this.d);
        }

        public String toString() {
            return "Entry{segmentDuration=" + this.b + ", mediaTime=" + this.c + ", mediaRate=" + this.d + Operators.BLOCK_END;
        }
    }
}
