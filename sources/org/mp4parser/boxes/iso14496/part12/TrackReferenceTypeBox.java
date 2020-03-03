package org.mp4parser.boxes.iso14496.part12;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import org.mp4parser.tools.Mp4Arrays;

public class TrackReferenceTypeBox extends AbstractBox {
    private static final JoinPoint.StaticPart b = null;
    private static final JoinPoint.StaticPart c = null;

    /* renamed from: a  reason: collision with root package name */
    long[] f3909a = new long[0];

    static {
        e();
    }

    private static void e() {
        Factory factory = new Factory("TrackReferenceTypeBox.java", TrackReferenceTypeBox.class);
        b = factory.a("method-execution", (Signature) factory.a("1", "getTrackIds", "org.mp4parser.boxes.iso14496.part12.TrackReferenceTypeBox", "", "", "", "[J"), 58);
        c = factory.a("method-execution", (Signature) factory.a("1", "setTrackIds", "org.mp4parser.boxes.iso14496.part12.TrackReferenceTypeBox", "[J", "trackIds", "", "void"), 62);
    }

    public TrackReferenceTypeBox(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) (this.f3909a.length * 4);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        for (long b2 : this.f3909a) {
            IsoTypeWriter.b(byteBuffer, b2);
        }
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        while (byteBuffer.remaining() >= 4) {
            this.f3909a = Mp4Arrays.a(this.f3909a, IsoTypeReader.b(byteBuffer));
        }
    }

    public long[] d() {
        RequiresParseDetailAspect.a().a(Factory.a(b, (Object) this, (Object) this));
        return this.f3909a;
    }

    public void a(long[] jArr) {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this, (Object) jArr));
        this.f3909a = jArr;
    }
}
