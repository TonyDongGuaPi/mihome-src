package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;

public abstract class ChunkOffsetBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    private static final JoinPoint.StaticPart f3835a = null;

    static {
        f();
    }

    private static void f() {
        Factory factory = new Factory("ChunkOffsetBox.java", ChunkOffsetBox.class);
        f3835a = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.ChunkOffsetBox", "", "", "", "java.lang.String"), 18);
    }

    public abstract void a(long[] jArr);

    public abstract long[] e();

    public ChunkOffsetBox(String str) {
        super(str);
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(f3835a, (Object) this, (Object) this));
        return String.valueOf(getClass().getSimpleName()) + "[entryCount=" + e().length + Operators.ARRAY_END_STR;
    }
}
