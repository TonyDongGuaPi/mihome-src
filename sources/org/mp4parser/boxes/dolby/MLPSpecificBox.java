package org.mp4parser.boxes.dolby;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitReaderBuffer;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitWriterBuffer;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;

public class MLPSpecificBox extends AbstractBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3817a = "dmlp";
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    private static final JoinPoint.StaticPart k = null;
    private static final JoinPoint.StaticPart l = null;
    private static final JoinPoint.StaticPart m = null;
    int b;
    int c;
    int d;
    int e;

    static {
        h();
    }

    private static void h() {
        Factory factory = new Factory("MLPSpecificBox.java", MLPSpecificBox.class);
        f = factory.a("method-execution", (Signature) factory.a("1", "getFormat_info", "org.mp4parser.boxes.dolby.MLPSpecificBox", "", "", "", "int"), 49);
        g = factory.a("method-execution", (Signature) factory.a("1", "setFormat_info", "org.mp4parser.boxes.dolby.MLPSpecificBox", "int", "format_info", "", "void"), 53);
        h = factory.a("method-execution", (Signature) factory.a("1", "getPeak_data_rate", "org.mp4parser.boxes.dolby.MLPSpecificBox", "", "", "", "int"), 57);
        i = factory.a("method-execution", (Signature) factory.a("1", "setPeak_data_rate", "org.mp4parser.boxes.dolby.MLPSpecificBox", "int", "peak_data_rate", "", "void"), 61);
        j = factory.a("method-execution", (Signature) factory.a("1", "getReserved", "org.mp4parser.boxes.dolby.MLPSpecificBox", "", "", "", "int"), 65);
        k = factory.a("method-execution", (Signature) factory.a("1", "setReserved", "org.mp4parser.boxes.dolby.MLPSpecificBox", "int", "reserved", "", "void"), 69);
        l = factory.a("method-execution", (Signature) factory.a("1", "getReserved2", "org.mp4parser.boxes.dolby.MLPSpecificBox", "", "", "", "int"), 73);
        m = factory.a("method-execution", (Signature) factory.a("1", "setReserved2", "org.mp4parser.boxes.dolby.MLPSpecificBox", "int", "reserved2", "", "void"), 77);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 10;
    }

    public MLPSpecificBox() {
        super(f3817a);
    }

    public void a(ByteBuffer byteBuffer) {
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(byteBuffer);
        this.b = bitReaderBuffer.a(32);
        this.c = bitReaderBuffer.a(15);
        this.d = bitReaderBuffer.a(1);
        this.e = bitReaderBuffer.a(32);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        BitWriterBuffer bitWriterBuffer = new BitWriterBuffer(byteBuffer);
        bitWriterBuffer.a(this.b, 32);
        bitWriterBuffer.a(this.c, 15);
        bitWriterBuffer.a(this.d, 1);
        bitWriterBuffer.a(this.e, 32);
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
