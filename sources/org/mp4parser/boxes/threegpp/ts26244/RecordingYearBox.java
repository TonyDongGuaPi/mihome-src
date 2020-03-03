package org.mp4parser.boxes.threegpp.ts26244;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class RecordingYearBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3976a = "yrrc";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    int b;

    static {
        f();
    }

    private static void f() {
        Factory factory = new Factory("RecordingYearBox.java", RecordingYearBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getRecordingYear", "org.mp4parser.boxes.threegpp.ts26244.RecordingYearBox", "", "", "", "int"), 42);
        d = factory.a("method-execution", (Signature) factory.a("1", "setRecordingYear", "org.mp4parser.boxes.threegpp.ts26244.RecordingYearBox", "int", "recordingYear", "", "void"), 46);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 6;
    }

    public RecordingYearBox() {
        super(f3976a);
    }

    public int e() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return this.b;
    }

    public void c(int i) {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this, Conversions.a(i)));
        this.b = i;
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.b = IsoTypeReader.d(byteBuffer);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.b(byteBuffer, this.b);
    }
}
