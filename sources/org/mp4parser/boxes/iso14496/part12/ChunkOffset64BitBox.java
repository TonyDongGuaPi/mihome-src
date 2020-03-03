package org.mp4parser.boxes.iso14496.part12;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class ChunkOffset64BitBox extends ChunkOffsetBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3834a = "co64";
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;
    private long[] b;

    static {
        f();
    }

    private static void f() {
        Factory factory = new Factory("ChunkOffset64BitBox.java", ChunkOffset64BitBox.class);
        c = factory.a("method-execution", (Signature) factory.a("1", "getChunkOffsets", "org.mp4parser.boxes.iso14496.part12.ChunkOffset64BitBox", "", "", "", "[J"), 22);
        d = factory.a("method-execution", (Signature) factory.a("1", "setChunkOffsets", "org.mp4parser.boxes.iso14496.part12.ChunkOffset64BitBox", "[J", "chunkOffsets", "", "void"), 27);
    }

    public ChunkOffset64BitBox() {
        super(f3834a);
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
        return (long) ((this.b.length * 8) + 8);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        int a2 = CastUtils.a(IsoTypeReader.b(byteBuffer));
        this.b = new long[a2];
        for (int i = 0; i < a2; i++) {
            this.b[i] = IsoTypeReader.h(byteBuffer);
        }
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.b(byteBuffer, (long) this.b.length);
        for (long a2 : this.b) {
            IsoTypeWriter.a(byteBuffer, a2);
        }
    }
}
