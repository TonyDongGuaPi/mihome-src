package org.mp4parser.boxes.iso14496.part12;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class StaticChunkOffsetBox extends ChunkOffsetBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3892a = "stco";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    private long[] b = new long[0];

    static {
        f();
    }

    private static void f() {
        Factory factory = new Factory("StaticChunkOffsetBox.java", StaticChunkOffsetBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getChunkOffsets", "org.mp4parser.boxes.iso14496.part12.StaticChunkOffsetBox", "", "", "", "[J"), 38);
        d = factory.a("method-execution", (Signature) factory.a("1", "setChunkOffsets", "org.mp4parser.boxes.iso14496.part12.StaticChunkOffsetBox", "[J", "chunkOffsets", "", "void"), 43);
    }

    public StaticChunkOffsetBox() {
        super(f3892a);
    }

    public long[] e() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return this.b;
    }

    public void a(long[] jArr) {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this, (Object) jArr));
        this.b = jArr;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) ((this.b.length * 4) + 8);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        int a2 = CastUtils.a(IsoTypeReader.b(byteBuffer));
        this.b = new long[a2];
        for (int i = 0; i < a2; i++) {
            this.b[i] = IsoTypeReader.b(byteBuffer);
        }
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.b(byteBuffer, (long) this.b.length);
        for (long b2 : this.b) {
            IsoTypeWriter.b(byteBuffer, b2);
        }
    }
}
