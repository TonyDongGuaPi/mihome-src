package org.mp4parser.boxes.dolby;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitReaderBuffer;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitWriterBuffer;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;

public class EC3SpecificBox extends AbstractBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3815a = "dec3";
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    private static final JoinPoint.StaticPart k = null;
    private static final JoinPoint.StaticPart l = null;
    private static final JoinPoint.StaticPart m = null;
    List<Entry> b = new LinkedList();
    int c;
    int d;

    static {
        g();
    }

    private static void g() {
        Factory factory = new Factory("EC3SpecificBox.java", EC3SpecificBox.class);
        e = factory.a("method-execution", (Signature) factory.a("1", "getContentSize", "org.mp4parser.boxes.dolby.EC3SpecificBox", "", "", "", "long"), 25);
        f = factory.a("method-execution", (Signature) factory.a("1", "getContent", "org.mp4parser.boxes.dolby.EC3SpecificBox", "java.nio.ByteBuffer", "byteBuffer", "", "void"), 65);
        g = factory.a("method-execution", (Signature) factory.a("1", "getEntries", "org.mp4parser.boxes.dolby.EC3SpecificBox", "", "", "", "java.util.List"), 86);
        h = factory.a("method-execution", (Signature) factory.a("1", "setEntries", "org.mp4parser.boxes.dolby.EC3SpecificBox", "java.util.List", "entries", "", "void"), 90);
        i = factory.a("method-execution", (Signature) factory.a("1", "addEntry", "org.mp4parser.boxes.dolby.EC3SpecificBox", "org.mp4parser.boxes.dolby.EC3SpecificBox$Entry", "entry", "", "void"), 94);
        j = factory.a("method-execution", (Signature) factory.a("1", "getDataRate", "org.mp4parser.boxes.dolby.EC3SpecificBox", "", "", "", "int"), 98);
        k = factory.a("method-execution", (Signature) factory.a("1", "setDataRate", "org.mp4parser.boxes.dolby.EC3SpecificBox", "int", "dataRate", "", "void"), 102);
        l = factory.a("method-execution", (Signature) factory.a("1", "getNumIndSub", "org.mp4parser.boxes.dolby.EC3SpecificBox", "", "", "", "int"), 106);
        m = factory.a("method-execution", (Signature) factory.a("1", "setNumIndSub", "org.mp4parser.boxes.dolby.EC3SpecificBox", "int", "numIndSub", "", "void"), 110);
    }

    public EC3SpecificBox() {
        super(f3815a);
    }

    public long ai_() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        long j2 = 2;
        for (Entry entry : this.b) {
            j2 = entry.g > 0 ? j2 + 4 : j2 + 3;
        }
        return j2;
    }

    public void a(ByteBuffer byteBuffer) {
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(byteBuffer);
        this.c = bitReaderBuffer.a(13);
        this.d = bitReaderBuffer.a(3) + 1;
        for (int i2 = 0; i2 < this.d; i2++) {
            Entry entry = new Entry();
            entry.f3816a = bitReaderBuffer.a(2);
            entry.b = bitReaderBuffer.a(5);
            entry.c = bitReaderBuffer.a(5);
            entry.d = bitReaderBuffer.a(3);
            entry.e = bitReaderBuffer.a(1);
            entry.f = bitReaderBuffer.a(3);
            entry.g = bitReaderBuffer.a(4);
            if (entry.g > 0) {
                entry.h = bitReaderBuffer.a(9);
            } else {
                entry.i = bitReaderBuffer.a(1);
            }
            this.b.add(entry);
        }
    }

    public void b(ByteBuffer byteBuffer) {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this, (Object) byteBuffer));
        BitWriterBuffer bitWriterBuffer = new BitWriterBuffer(byteBuffer);
        bitWriterBuffer.a(this.c, 13);
        bitWriterBuffer.a(this.b.size() - 1, 3);
        for (Entry next : this.b) {
            bitWriterBuffer.a(next.f3816a, 2);
            bitWriterBuffer.a(next.b, 5);
            bitWriterBuffer.a(next.c, 5);
            bitWriterBuffer.a(next.d, 3);
            bitWriterBuffer.a(next.e, 1);
            bitWriterBuffer.a(next.f, 3);
            bitWriterBuffer.a(next.g, 4);
            if (next.g > 0) {
                bitWriterBuffer.a(next.h, 9);
            } else {
                bitWriterBuffer.a(next.i, 1);
            }
        }
    }

    public List<Entry> d() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return this.b;
    }

    public void a(List<Entry> list) {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this, (Object) list));
        this.b = list;
    }

    public void a(Entry entry) {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this, (Object) entry));
        this.b.add(entry);
    }

    public int e() {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this));
        return this.c;
    }

    public void a(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this, Conversions.a(i2)));
        this.c = i2;
    }

    public int f() {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this));
        return this.d;
    }

    public void b(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this, Conversions.a(i2)));
        this.d = i2;
    }

    public static class Entry {

        /* renamed from: a  reason: collision with root package name */
        public int f3816a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public int i;

        public String toString() {
            return "Entry{fscod=" + this.f3816a + ", bsid=" + this.b + ", bsmod=" + this.c + ", acmod=" + this.d + ", lfeon=" + this.e + ", reserved=" + this.f + ", num_dep_sub=" + this.g + ", chan_loc=" + this.h + ", reserved2=" + this.i + Operators.BLOCK_END;
        }
    }
}
