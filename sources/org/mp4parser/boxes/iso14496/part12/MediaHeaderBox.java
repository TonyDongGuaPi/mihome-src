package org.mp4parser.boxes.iso14496.part12;

import com.alipay.sdk.util.i;
import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.Date;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.Logger;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.DateHelper;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class MediaHeaderBox extends AbstractFullBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3860a = "mdhd";
    private static Logger b = Logger.a(MediaHeaderBox.class);
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;
    private static final JoinPoint.StaticPart k = null;
    private static final JoinPoint.StaticPart l = null;
    private static final JoinPoint.StaticPart m = null;
    private static final JoinPoint.StaticPart n = null;
    private static final JoinPoint.StaticPart o = null;
    private static final JoinPoint.StaticPart p = null;
    private static final JoinPoint.StaticPart q = null;
    private static final JoinPoint.StaticPart u = null;
    private Date c = new Date();
    private Date d = new Date();
    private long e;
    private long f;
    private String g = "eng";

    private static void k() {
        Factory factory = new Factory("MediaHeaderBox.java", MediaHeaderBox.class);
        h = factory.a("method-execution", (Signature) factory.a("1", "getCreationTime", "org.mp4parser.boxes.iso14496.part12.MediaHeaderBox", "", "", "", "java.util.Date"), 46);
        i = factory.a("method-execution", (Signature) factory.a("1", "setCreationTime", "org.mp4parser.boxes.iso14496.part12.MediaHeaderBox", "java.util.Date", "creationTime", "", "void"), 50);
        u = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.MediaHeaderBox", "", "", "", "java.lang.String"), 123);
        j = factory.a("method-execution", (Signature) factory.a("1", "getModificationTime", "org.mp4parser.boxes.iso14496.part12.MediaHeaderBox", "", "", "", "java.util.Date"), 54);
        k = factory.a("method-execution", (Signature) factory.a("1", "setModificationTime", "org.mp4parser.boxes.iso14496.part12.MediaHeaderBox", "java.util.Date", "modificationTime", "", "void"), 58);
        l = factory.a("method-execution", (Signature) factory.a("1", "getTimescale", "org.mp4parser.boxes.iso14496.part12.MediaHeaderBox", "", "", "", "long"), 62);
        m = factory.a("method-execution", (Signature) factory.a("1", "setTimescale", "org.mp4parser.boxes.iso14496.part12.MediaHeaderBox", "long", "timescale", "", "void"), 66);
        n = factory.a("method-execution", (Signature) factory.a("1", "getDuration", "org.mp4parser.boxes.iso14496.part12.MediaHeaderBox", "", "", "", "long"), 70);
        o = factory.a("method-execution", (Signature) factory.a("1", "setDuration", "org.mp4parser.boxes.iso14496.part12.MediaHeaderBox", "long", "duration", "", "void"), 74);
        p = factory.a("method-execution", (Signature) factory.a("1", "getLanguage", "org.mp4parser.boxes.iso14496.part12.MediaHeaderBox", "", "", "", "java.lang.String"), 78);
        q = factory.a("method-execution", (Signature) factory.a("1", "setLanguage", "org.mp4parser.boxes.iso14496.part12.MediaHeaderBox", "java.lang.String", "language", "", "void"), 82);
    }

    static {
        k();
    }

    public MediaHeaderBox() {
        super("mdhd");
    }

    public Date e() {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this));
        return this.c;
    }

    public void a(Date date) {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this, (Object) date));
        this.c = date;
    }

    public Date f() {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this));
        return this.d;
    }

    public void b(Date date) {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this, (Object) date));
        this.d = date;
    }

    public long g() {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this));
        return this.e;
    }

    public void a(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this, Conversions.a(j2)));
        this.e = j2;
    }

    public long h() {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this));
        return this.f;
    }

    public void b(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this, Conversions.a(j2)));
        this.f = j2;
    }

    public String i() {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this));
        return this.g;
    }

    public void a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this, (Object) str));
        this.g = str;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (ag_() == 1 ? 32 : 20) + 2 + 2;
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        if (ag_() == 1) {
            this.c = DateHelper.a(IsoTypeReader.h(byteBuffer));
            this.d = DateHelper.a(IsoTypeReader.h(byteBuffer));
            this.e = IsoTypeReader.b(byteBuffer);
            this.f = byteBuffer.getLong();
        } else {
            this.c = DateHelper.a(IsoTypeReader.b(byteBuffer));
            this.d = DateHelper.a(IsoTypeReader.b(byteBuffer));
            this.e = IsoTypeReader.b(byteBuffer);
            this.f = (long) byteBuffer.getInt();
        }
        if (this.f < -1) {
            b.b("mdhd duration is not in expected range");
        }
        this.g = IsoTypeReader.l(byteBuffer);
        IsoTypeReader.d(byteBuffer);
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this));
        return "MediaHeaderBox[" + "creationTime=" + e() + i.b + "modificationTime=" + f() + i.b + "timescale=" + g() + i.b + "duration=" + h() + i.b + "language=" + i() + Operators.ARRAY_END_STR;
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        if (ag_() == 1) {
            IsoTypeWriter.a(byteBuffer, DateHelper.a(this.c));
            IsoTypeWriter.a(byteBuffer, DateHelper.a(this.d));
            IsoTypeWriter.b(byteBuffer, this.e);
            byteBuffer.putLong(this.f);
        } else {
            IsoTypeWriter.b(byteBuffer, DateHelper.a(this.c));
            IsoTypeWriter.b(byteBuffer, DateHelper.a(this.d));
            IsoTypeWriter.b(byteBuffer, this.e);
            byteBuffer.putInt((int) this.f);
        }
        IsoTypeWriter.a(byteBuffer, this.g);
        IsoTypeWriter.b(byteBuffer, 0);
    }
}
