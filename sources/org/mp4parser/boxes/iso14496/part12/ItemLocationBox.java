package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeReaderVariable;
import org.mp4parser.tools.IsoTypeWriter;
import org.mp4parser.tools.IsoTypeWriterVariable;

public class ItemLocationBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3854a = "iloc";
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
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
    public int b = 8;
    public int c = 8;
    public int d = 8;
    public int e = 0;
    public List<Item> f = new LinkedList();

    static {
        k();
    }

    private static void k() {
        Factory factory = new Factory("ItemLocationBox.java", ItemLocationBox.class);
        g = factory.a("method-execution", (Signature) factory.a("1", "getOffsetSize", "org.mp4parser.boxes.iso14496.part12.ItemLocationBox", "", "", "", "int"), 118);
        h = factory.a("method-execution", (Signature) factory.a("1", "setOffsetSize", "org.mp4parser.boxes.iso14496.part12.ItemLocationBox", "int", "offsetSize", "", "void"), 122);
        q = factory.a("method-execution", (Signature) factory.a("1", "createItem", "org.mp4parser.boxes.iso14496.part12.ItemLocationBox", "int:int:int:long:java.util.List", "itemId:constructionMethod:dataReferenceIndex:baseOffset:extents", "", "org.mp4parser.boxes.iso14496.part12.ItemLocationBox$Item"), 159);
        u = factory.a("method-execution", (Signature) factory.a("1", "createExtent", "org.mp4parser.boxes.iso14496.part12.ItemLocationBox", "long:long:long", "extentOffset:extentLength:extentIndex", "", "org.mp4parser.boxes.iso14496.part12.ItemLocationBox$Extent"), 167);
        i = factory.a("method-execution", (Signature) factory.a("1", "getLengthSize", "org.mp4parser.boxes.iso14496.part12.ItemLocationBox", "", "", "", "int"), 126);
        j = factory.a("method-execution", (Signature) factory.a("1", "setLengthSize", "org.mp4parser.boxes.iso14496.part12.ItemLocationBox", "int", "lengthSize", "", "void"), 130);
        k = factory.a("method-execution", (Signature) factory.a("1", "getBaseOffsetSize", "org.mp4parser.boxes.iso14496.part12.ItemLocationBox", "", "", "", "int"), 134);
        l = factory.a("method-execution", (Signature) factory.a("1", "setBaseOffsetSize", "org.mp4parser.boxes.iso14496.part12.ItemLocationBox", "int", "baseOffsetSize", "", "void"), 138);
        m = factory.a("method-execution", (Signature) factory.a("1", "getIndexSize", "org.mp4parser.boxes.iso14496.part12.ItemLocationBox", "", "", "", "int"), 142);
        n = factory.a("method-execution", (Signature) factory.a("1", "setIndexSize", "org.mp4parser.boxes.iso14496.part12.ItemLocationBox", "int", "indexSize", "", "void"), 146);
        o = factory.a("method-execution", (Signature) factory.a("1", "getItems", "org.mp4parser.boxes.iso14496.part12.ItemLocationBox", "", "", "", "java.util.List"), 150);
        p = factory.a("method-execution", (Signature) factory.a("1", "setItems", "org.mp4parser.boxes.iso14496.part12.ItemLocationBox", "java.util.List", "items", "", "void"), 154);
    }

    public ItemLocationBox() {
        super(f3854a);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        long j2 = 8;
        for (Item a2 : this.f) {
            j2 += (long) a2.a();
        }
        return j2;
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.d(byteBuffer, (this.b << 4) | this.c);
        if (ag_() == 1) {
            IsoTypeWriter.d(byteBuffer, (this.d << 4) | this.e);
        } else {
            IsoTypeWriter.d(byteBuffer, this.d << 4);
        }
        IsoTypeWriter.b(byteBuffer, this.f.size());
        for (Item a2 : this.f) {
            a2.a(byteBuffer);
        }
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        int f2 = IsoTypeReader.f(byteBuffer);
        this.b = f2 >>> 4;
        this.c = f2 & 15;
        int f3 = IsoTypeReader.f(byteBuffer);
        this.d = f3 >>> 4;
        if (ag_() == 1) {
            this.e = f3 & 15;
        }
        int d2 = IsoTypeReader.d(byteBuffer);
        for (int i2 = 0; i2 < d2; i2++) {
            this.f.add(new Item(byteBuffer));
        }
    }

    public int e() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return this.b;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this, Conversions.a(i2)));
        this.b = i2;
    }

    public int f() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.c;
    }

    public void d(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this, Conversions.a(i2)));
        this.c = i2;
    }

    public int g() {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this));
        return this.d;
    }

    public void e(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this, Conversions.a(i2)));
        this.d = i2;
    }

    public int h() {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this));
        return this.e;
    }

    public void f(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this, Conversions.a(i2)));
        this.e = i2;
    }

    public List<Item> i() {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this));
        return this.f;
    }

    public void a(List<Item> list) {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this, (Object) list));
        this.f = list;
    }

    public Item a(int i2, int i3, int i4, long j2, List<Extent> list) {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this, new Object[]{Conversions.a(i2), Conversions.a(i3), Conversions.a(i4), Conversions.a(j2), list}));
        return new Item(i2, i3, i4, j2, list);
    }

    /* access modifiers changed from: package-private */
    public Item c(ByteBuffer byteBuffer) {
        return new Item(byteBuffer);
    }

    public Extent a(long j2, long j3, long j4) {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this, new Object[]{Conversions.a(j2), Conversions.a(j3), Conversions.a(j4)}));
        return new Extent(j2, j3, j4);
    }

    /* access modifiers changed from: package-private */
    public Extent d(ByteBuffer byteBuffer) {
        return new Extent(byteBuffer);
    }

    public class Item {

        /* renamed from: a  reason: collision with root package name */
        public int f3856a;
        public int b;
        public int c;
        public long d;
        public List<Extent> e = new LinkedList();

        public Item(ByteBuffer byteBuffer) {
            this.f3856a = IsoTypeReader.d(byteBuffer);
            if (ItemLocationBox.this.ag_() == 1) {
                this.b = IsoTypeReader.d(byteBuffer) & 15;
            }
            this.c = IsoTypeReader.d(byteBuffer);
            if (ItemLocationBox.this.d > 0) {
                this.d = IsoTypeReaderVariable.a(byteBuffer, ItemLocationBox.this.d);
            } else {
                this.d = 0;
            }
            int d2 = IsoTypeReader.d(byteBuffer);
            for (int i = 0; i < d2; i++) {
                this.e.add(new Extent(byteBuffer));
            }
        }

        public Item(int i, int i2, int i3, long j, List<Extent> list) {
            this.f3856a = i;
            this.b = i2;
            this.c = i3;
            this.d = j;
            this.e = list;
        }

        public int a() {
            int i = (ItemLocationBox.this.ag_() == 1 ? 4 : 2) + 2 + ItemLocationBox.this.d + 2;
            for (Extent a2 : this.e) {
                i += a2.a();
            }
            return i;
        }

        public void a(long j) {
            this.d = j;
        }

        public void a(ByteBuffer byteBuffer) {
            IsoTypeWriter.b(byteBuffer, this.f3856a);
            if (ItemLocationBox.this.ag_() == 1) {
                IsoTypeWriter.b(byteBuffer, this.b);
            }
            IsoTypeWriter.b(byteBuffer, this.c);
            if (ItemLocationBox.this.d > 0) {
                IsoTypeWriterVariable.a(this.d, byteBuffer, ItemLocationBox.this.d);
            }
            IsoTypeWriter.b(byteBuffer, this.e.size());
            for (Extent a2 : this.e) {
                a2.a(byteBuffer);
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Item item = (Item) obj;
            if (this.d == item.d && this.b == item.b && this.c == item.c && this.f3856a == item.f3856a) {
                return this.e == null ? item.e == null : this.e.equals(item.e);
            }
            return false;
        }

        public int hashCode() {
            return (((((((this.f3856a * 31) + this.b) * 31) + this.c) * 31) + ((int) (this.d ^ (this.d >>> 32)))) * 31) + (this.e != null ? this.e.hashCode() : 0);
        }

        public String toString() {
            return "Item{baseOffset=" + this.d + ", itemId=" + this.f3856a + ", constructionMethod=" + this.b + ", dataReferenceIndex=" + this.c + ", extents=" + this.e + Operators.BLOCK_END;
        }
    }

    public class Extent {

        /* renamed from: a  reason: collision with root package name */
        public long f3855a;
        public long b;
        public long c;

        public Extent(long j, long j2, long j3) {
            this.f3855a = j;
            this.b = j2;
            this.c = j3;
        }

        public Extent(ByteBuffer byteBuffer) {
            if (ItemLocationBox.this.ag_() == 1 && ItemLocationBox.this.e > 0) {
                this.c = IsoTypeReaderVariable.a(byteBuffer, ItemLocationBox.this.e);
            }
            this.f3855a = IsoTypeReaderVariable.a(byteBuffer, ItemLocationBox.this.b);
            this.b = IsoTypeReaderVariable.a(byteBuffer, ItemLocationBox.this.c);
        }

        public void a(ByteBuffer byteBuffer) {
            if (ItemLocationBox.this.ag_() == 1 && ItemLocationBox.this.e > 0) {
                IsoTypeWriterVariable.a(this.c, byteBuffer, ItemLocationBox.this.e);
            }
            IsoTypeWriterVariable.a(this.f3855a, byteBuffer, ItemLocationBox.this.b);
            IsoTypeWriterVariable.a(this.b, byteBuffer, ItemLocationBox.this.c);
        }

        public int a() {
            return (ItemLocationBox.this.e > 0 ? ItemLocationBox.this.e : 0) + ItemLocationBox.this.b + ItemLocationBox.this.c;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Extent extent = (Extent) obj;
            return this.c == extent.c && this.b == extent.b && this.f3855a == extent.f3855a;
        }

        public int hashCode() {
            return (((((int) (this.f3855a ^ (this.f3855a >>> 32))) * 31) + ((int) (this.b ^ (this.b >>> 32)))) * 31) + ((int) (this.c ^ (this.c >>> 32)));
        }

        public String toString() {
            return "Extent" + "{extentOffset=" + this.f3855a + ", extentLength=" + this.b + ", extentIndex=" + this.c + Operators.BLOCK_END;
        }
    }
}
