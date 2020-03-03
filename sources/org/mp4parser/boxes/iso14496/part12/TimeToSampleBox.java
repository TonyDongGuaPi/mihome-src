package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class TimeToSampleBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3898a = "stts";
    static Map<List<Entry>, SoftReference<long[]>> b = new WeakHashMap();
    static final /* synthetic */ boolean d = (!TimeToSampleBox.class.desiredAssertionStatus());
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    List<Entry> c = Collections.emptyList();

    private static void f() {
        Factory factory = new Factory("TimeToSampleBox.java", TimeToSampleBox.class);
        e = factory.a("method-execution", (Signature) factory.a("1", "getEntries", "org.mp4parser.boxes.iso14496.part12.TimeToSampleBox", "", "", "", "java.util.List"), 111);
        f = factory.a("method-execution", (Signature) factory.a("1", "setEntries", "org.mp4parser.boxes.iso14496.part12.TimeToSampleBox", "java.util.List", "entries", "", "void"), 115);
        g = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.TimeToSampleBox", "", "", "", "java.lang.String"), 119);
    }

    static {
        f();
    }

    public TimeToSampleBox() {
        super("stts");
    }

    public static synchronized long[] a(List<Entry> list) {
        long[] jArr;
        synchronized (TimeToSampleBox.class) {
            SoftReference softReference = b.get(list);
            if (softReference != null && (jArr = (long[]) softReference.get()) != null) {
                return jArr;
            }
            long j = 0;
            for (Entry a2 : list) {
                j += a2.a();
            }
            if (!d) {
                if (j > 2147483647L) {
                    throw new AssertionError();
                }
            }
            long[] jArr2 = new long[((int) j)];
            int i = 0;
            for (Entry next : list) {
                int i2 = i;
                int i3 = 0;
                while (((long) i3) < next.a()) {
                    jArr2[i2] = next.b();
                    i3++;
                    i2++;
                }
                i = i2;
            }
            b.put(list, new SoftReference(jArr2));
            return jArr2;
        }
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) ((this.c.size() * 8) + 8);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        int a2 = CastUtils.a(IsoTypeReader.b(byteBuffer));
        this.c = new ArrayList(a2);
        for (int i = 0; i < a2; i++) {
            this.c.add(new Entry(IsoTypeReader.b(byteBuffer), IsoTypeReader.b(byteBuffer)));
        }
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.b(byteBuffer, (long) this.c.size());
        for (Entry next : this.c) {
            IsoTypeWriter.b(byteBuffer, next.a());
            IsoTypeWriter.b(byteBuffer, next.b());
        }
    }

    public List<Entry> e() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return this.c;
    }

    public void b(List<Entry> list) {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this, (Object) list));
        this.c = list;
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return "TimeToSampleBox[entryCount=" + this.c.size() + Operators.ARRAY_END_STR;
    }

    public static class Entry {

        /* renamed from: a  reason: collision with root package name */
        long f3899a;
        long b;

        public Entry(long j, long j2) {
            this.f3899a = j;
            this.b = j2;
        }

        public long a() {
            return this.f3899a;
        }

        public void a(long j) {
            this.f3899a = j;
        }

        public long b() {
            return this.b;
        }

        public void b(long j) {
            this.b = j;
        }

        public String toString() {
            return "Entry{count=" + this.f3899a + ", delta=" + this.b + Operators.BLOCK_END;
        }
    }
}
