package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.mp4parser.IsoFile;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import org.mp4parser.tools.Utf8;

public class HandlerBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3850a = "hdlr";
    public static final Map<String, String> b;
    private static final JoinPoint.StaticPart j = null;
    private static final JoinPoint.StaticPart k = null;
    private static final JoinPoint.StaticPart l = null;
    private static final JoinPoint.StaticPart m = null;
    private static final JoinPoint.StaticPart n = null;
    private static final JoinPoint.StaticPart o = null;
    private String c;
    private String d = null;
    private long e;
    private long f;
    private long g;
    private boolean h = true;
    private long i;

    private static void h() {
        Factory factory = new Factory("HandlerBox.java", HandlerBox.class);
        j = factory.a("method-execution", (Signature) factory.a("1", "getHandlerType", "org.mp4parser.boxes.iso14496.part12.HandlerBox", "", "", "", "java.lang.String"), 78);
        k = factory.a("method-execution", (Signature) factory.a("1", "setHandlerType", "org.mp4parser.boxes.iso14496.part12.HandlerBox", "java.lang.String", "handlerType", "", "void"), 82);
        l = factory.a("method-execution", (Signature) factory.a("1", "getName", "org.mp4parser.boxes.iso14496.part12.HandlerBox", "", "", "", "java.lang.String"), 86);
        m = factory.a("method-execution", (Signature) factory.a("1", "setName", "org.mp4parser.boxes.iso14496.part12.HandlerBox", "java.lang.String", "name", "", "void"), 95);
        n = factory.a("method-execution", (Signature) factory.a("1", "getHumanReadableTrackType", "org.mp4parser.boxes.iso14496.part12.HandlerBox", "", "", "", "java.lang.String"), 99);
        o = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.HandlerBox", "", "", "", "java.lang.String"), 149);
    }

    static {
        h();
        HashMap hashMap = new HashMap();
        hashMap.put("odsm", "ObjectDescriptorStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        hashMap.put("crsm", "ClockReferenceStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        hashMap.put("sdsm", "SceneDescriptionStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        hashMap.put("m7sm", "MPEG7Stream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        hashMap.put("ocsm", "ObjectContentInfoStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        hashMap.put("ipsm", "IPMP Stream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        hashMap.put("mjsm", "MPEG-J Stream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        hashMap.put("mdir", "Apple Meta Data iTunes Reader");
        hashMap.put("mp7b", "MPEG-7 binary XML");
        hashMap.put("mp7t", "MPEG-7 XML");
        hashMap.put("vide", "Video Track");
        hashMap.put("soun", "Sound Track");
        hashMap.put("hint", "Hint Track");
        hashMap.put("appl", "Apple specific");
        hashMap.put("meta", "Timed Metadata track - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        b = Collections.unmodifiableMap(hashMap);
    }

    public HandlerBox() {
        super("hdlr");
    }

    public String e() {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this));
        return this.c;
    }

    public void a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this, (Object) str));
        this.c = str;
    }

    public String f() {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this));
        return this.d;
    }

    public void b(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this, (Object) str));
        this.d = str;
    }

    public String g() {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this));
        return b.get(this.c) != null ? b.get(this.c) : "Unknown Handler Type";
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        if (this.h) {
            return (long) (Utf8.b(this.d) + 25);
        }
        return (long) (Utf8.b(this.d) + 24);
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        this.i = IsoTypeReader.b(byteBuffer);
        this.c = IsoTypeReader.m(byteBuffer);
        this.e = IsoTypeReader.b(byteBuffer);
        this.f = IsoTypeReader.b(byteBuffer);
        this.g = IsoTypeReader.b(byteBuffer);
        if (byteBuffer.remaining() > 0) {
            this.d = IsoTypeReader.a(byteBuffer, byteBuffer.remaining());
            if (this.d.endsWith("\u0000")) {
                this.d = this.d.substring(0, this.d.length() - 1);
                this.h = true;
                return;
            }
            this.h = false;
            return;
        }
        this.h = false;
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.b(byteBuffer, this.i);
        byteBuffer.put(IsoFile.a(this.c));
        IsoTypeWriter.b(byteBuffer, this.e);
        IsoTypeWriter.b(byteBuffer, this.f);
        IsoTypeWriter.b(byteBuffer, this.g);
        if (this.d != null) {
            byteBuffer.put(Utf8.a(this.d));
        }
        if (this.h) {
            byteBuffer.put((byte) 0);
        }
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this));
        return "HandlerBox[handlerType=" + e() + ";name=" + f() + Operators.ARRAY_END_STR;
    }
}
