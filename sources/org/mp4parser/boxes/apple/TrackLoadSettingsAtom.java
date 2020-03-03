package org.mp4parser.boxes.apple;

import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;

public class TrackLoadSettingsAtom extends AbstractBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3803a = "load";
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
        Factory factory = new Factory("TrackLoadSettingsAtom.java", TrackLoadSettingsAtom.class);
        f = factory.a("method-execution", (Signature) factory.a("1", "getPreloadStartTime", "org.mp4parser.boxes.apple.TrackLoadSettingsAtom", "", "", "", "int"), 49);
        g = factory.a("method-execution", (Signature) factory.a("1", "setPreloadStartTime", "org.mp4parser.boxes.apple.TrackLoadSettingsAtom", "int", "preloadStartTime", "", "void"), 53);
        h = factory.a("method-execution", (Signature) factory.a("1", "getPreloadDuration", "org.mp4parser.boxes.apple.TrackLoadSettingsAtom", "", "", "", "int"), 57);
        i = factory.a("method-execution", (Signature) factory.a("1", "setPreloadDuration", "org.mp4parser.boxes.apple.TrackLoadSettingsAtom", "int", "preloadDuration", "", "void"), 61);
        j = factory.a("method-execution", (Signature) factory.a("1", "getPreloadFlags", "org.mp4parser.boxes.apple.TrackLoadSettingsAtom", "", "", "", "int"), 65);
        k = factory.a("method-execution", (Signature) factory.a("1", "setPreloadFlags", "org.mp4parser.boxes.apple.TrackLoadSettingsAtom", "int", "preloadFlags", "", "void"), 69);
        l = factory.a("method-execution", (Signature) factory.a("1", "getDefaultHints", "org.mp4parser.boxes.apple.TrackLoadSettingsAtom", "", "", "", "int"), 73);
        m = factory.a("method-execution", (Signature) factory.a("1", "setDefaultHints", "org.mp4parser.boxes.apple.TrackLoadSettingsAtom", "int", "defaultHints", "", "void"), 77);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 16;
    }

    public TrackLoadSettingsAtom() {
        super("load");
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.b);
        byteBuffer.putInt(this.c);
        byteBuffer.putInt(this.d);
        byteBuffer.putInt(this.e);
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        this.b = byteBuffer.getInt();
        this.c = byteBuffer.getInt();
        this.d = byteBuffer.getInt();
        this.e = byteBuffer.getInt();
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
