package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class CompositionTimeToSample extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3836a = "ctts";
    static final /* synthetic */ boolean c = (!CompositionTimeToSample.class.desiredAssertionStatus());
    private static final JoinPoint.StaticPart d = null;
    private static final JoinPoint.StaticPart e = null;
    List<Entry> b = Collections.emptyList();

    private static void f() {
        Factory factory = new Factory("CompositionTimeToSample.java", CompositionTimeToSample.class);
        d = factory.a("method-execution", (Signature) factory.a("1", "getEntries", "org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample", "", "", "", "java.util.List"), 82);
        e = factory.a("method-execution", (Signature) factory.a("1", "setEntries", "org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample", "java.util.List", "entries", "", "void"), 86);
    }

    static {
        f();
    }

    public CompositionTimeToSample() {
        super(f3836a);
    }

    public static int[] a(List<Entry> list) {
        long j = 0;
        for (Entry a2 : list) {
            j += (long) a2.a();
        }
        if (c || j <= 2147483647L) {
            int[] iArr = new int[((int) j)];
            int i = 0;
            for (Entry next : list) {
                int i2 = i;
                int i3 = 0;
                while (i3 < next.a()) {
                    iArr[i2] = next.b();
                    i3++;
                    i2++;
                }
                i = i2;
            }
            return iArr;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) ((this.b.size() * 8) + 8);
    }

    public List<Entry> e() {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this));
        return this.b;
    }

    public void b(List<Entry> list) {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this, (Object) list));
        this.b = list;
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        int a2 = CastUtils.a(IsoTypeReader.b(byteBuffer));
        this.b = new ArrayList(a2);
        for (int i = 0; i < a2; i++) {
            this.b.add(new Entry(CastUtils.a(IsoTypeReader.b(byteBuffer)), byteBuffer.getInt()));
        }
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.b(byteBuffer, (long) this.b.size());
        for (Entry next : this.b) {
            IsoTypeWriter.b(byteBuffer, (long) next.a());
            byteBuffer.putInt(next.b());
        }
    }

    public static class Entry {

        /* renamed from: a  reason: collision with root package name */
        int f3837a;
        int b;

        public Entry(int i, int i2) {
            this.f3837a = i;
            this.b = i2;
        }

        public int a() {
            return this.f3837a;
        }

        public void a(int i) {
            this.f3837a = i;
        }

        public int b() {
            return this.b;
        }

        public void b(int i) {
            this.b = i;
        }

        public String toString() {
            return "Entry{count=" + this.f3837a + ", offset=" + this.b + Operators.BLOCK_END;
        }
    }
}
