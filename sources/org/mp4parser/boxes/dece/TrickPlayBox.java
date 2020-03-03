package org.mp4parser.boxes.dece;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class TrickPlayBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3811a = "trik";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    private List<Entry> b = new ArrayList();

    static {
        f();
    }

    private static void f() {
        Factory factory = new Factory("TrickPlayBox.java", TrickPlayBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getEntries", "org.mp4parser.boxes.dece.TrickPlayBox", "", "", "", "java.util.List"), 32);
        d = factory.a("method-execution", (Signature) factory.a("1", "setEntries", "org.mp4parser.boxes.dece.TrickPlayBox", "java.util.List", "entries", "", "void"), 36);
        e = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.dece.TrickPlayBox", "", "", "", "java.lang.String"), 62);
    }

    public TrickPlayBox() {
        super(f3811a);
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
        return (long) (this.b.size() + 4);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        while (byteBuffer.remaining() > 0) {
            this.b.add(new Entry(IsoTypeReader.f(byteBuffer)));
        }
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        for (Entry a2 : this.b) {
            IsoTypeWriter.d(byteBuffer, a2.f3812a);
        }
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return "TrickPlayBox" + "{entries=" + this.b + Operators.BLOCK_END;
    }

    public static class Entry {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public int f3812a;

        public Entry() {
        }

        public Entry(int i) {
            this.f3812a = i;
        }

        public int a() {
            return (this.f3812a >> 6) & 3;
        }

        public void a(int i) {
            this.f3812a &= 31;
            this.f3812a = ((i & 3) << 6) | this.f3812a;
        }

        public int b() {
            return this.f3812a & 63;
        }

        public void b(int i) {
            this.f3812a = (i & 63) | this.f3812a;
        }

        public String toString() {
            return "Entry" + "{picType=" + a() + ",dependencyLevel=" + b() + Operators.BLOCK_END;
        }
    }
}
