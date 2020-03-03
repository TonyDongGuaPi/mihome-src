package org.mp4parser.boxes.iso14496.part15;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class PriotityRangeBox extends AbstractBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3921a = "svpr";
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    private static final JoinPoint.StaticPart k = null;
    private static final JoinPoint.StaticPart l = null;
    private static final JoinPoint.StaticPart m = null;
    int b = 0;
    int c;
    int d = 0;
    int e;

    static {
        h();
    }

    private static void h() {
        Factory factory = new Factory("PriotityRangeBox.java", PriotityRangeBox.class);
        f = factory.a("method-execution", (Signature) factory.a("1", "getReserved1", "org.mp4parser.boxes.iso14496.part15.PriotityRangeBox", "", "", "", "int"), 45);
        g = factory.a("method-execution", (Signature) factory.a("1", "setReserved1", "org.mp4parser.boxes.iso14496.part15.PriotityRangeBox", "int", "reserved1", "", "void"), 49);
        h = factory.a("method-execution", (Signature) factory.a("1", "getMin_priorityId", "org.mp4parser.boxes.iso14496.part15.PriotityRangeBox", "", "", "", "int"), 53);
        i = factory.a("method-execution", (Signature) factory.a("1", "setMin_priorityId", "org.mp4parser.boxes.iso14496.part15.PriotityRangeBox", "int", "min_priorityId", "", "void"), 57);
        j = factory.a("method-execution", (Signature) factory.a("1", "getReserved2", "org.mp4parser.boxes.iso14496.part15.PriotityRangeBox", "", "", "", "int"), 61);
        k = factory.a("method-execution", (Signature) factory.a("1", "setReserved2", "org.mp4parser.boxes.iso14496.part15.PriotityRangeBox", "int", "reserved2", "", "void"), 65);
        l = factory.a("method-execution", (Signature) factory.a("1", "getMax_priorityId", "org.mp4parser.boxes.iso14496.part15.PriotityRangeBox", "", "", "", "int"), 69);
        m = factory.a("method-execution", (Signature) factory.a("1", "setMax_priorityId", "org.mp4parser.boxes.iso14496.part15.PriotityRangeBox", "int", "max_priorityId", "", "void"), 73);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 2;
    }

    public PriotityRangeBox() {
        super(f3921a);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        IsoTypeWriter.d(byteBuffer, (this.b << 6) + this.c);
        IsoTypeWriter.d(byteBuffer, (this.d << 6) + this.e);
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        this.c = IsoTypeReader.f(byteBuffer);
        this.b = (this.c & 192) >> 6;
        this.c &= 63;
        this.e = IsoTypeReader.f(byteBuffer);
        this.d = (this.e & 192) >> 6;
        this.e &= 63;
    }

    public int d() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.b;
    }

    public void a(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this, Conversions.a(i2)));
        this.b = i2;
    }

    public int e() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        return this.c;
    }

    public void b(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this, Conversions.a(i2)));
        this.c = i2;
    }

    public int f() {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this));
        return this.d;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this, Conversions.a(i2)));
        this.d = i2;
    }

    public int g() {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this));
        return this.e;
    }

    public void d(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this, Conversions.a(i2)));
        this.e = i2;
    }
}
