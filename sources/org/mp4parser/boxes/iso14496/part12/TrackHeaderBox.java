package org.mp4parser.boxes.iso14496.part12;

import com.alipay.sdk.util.i;
import com.brentvatne.react.ReactVideoViewManager;
import com.drew.metadata.exif.makernotes.OlympusImageProcessingMakernoteDirectory;
import com.taobao.weex.el.parse.Operators;
import com.tencent.smtt.sdk.TbsListener;
import java.nio.ByteBuffer;
import java.util.Date;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.Logger;
import org.mp4parser.support.Matrix;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.DateHelper;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class TrackHeaderBox extends AbstractFullBox {
    private static final JoinPoint.StaticPart A = null;
    private static final JoinPoint.StaticPart B = null;
    private static final JoinPoint.StaticPart C = null;
    private static final JoinPoint.StaticPart D = null;
    private static final JoinPoint.StaticPart E = null;
    private static final JoinPoint.StaticPart F = null;
    private static final JoinPoint.StaticPart G = null;
    private static final JoinPoint.StaticPart H = null;
    private static final JoinPoint.StaticPart I = null;
    private static final JoinPoint.StaticPart J = null;
    private static final JoinPoint.StaticPart K = null;
    private static final JoinPoint.StaticPart L = null;
    private static final JoinPoint.StaticPart M = null;
    private static final JoinPoint.StaticPart N = null;
    private static final JoinPoint.StaticPart O = null;
    private static final JoinPoint.StaticPart P = null;
    private static final JoinPoint.StaticPart Q = null;
    private static final JoinPoint.StaticPart R = null;
    private static final JoinPoint.StaticPart S = null;

    /* renamed from: a  reason: collision with root package name */
    public static final String f3907a = "tkhd";
    private static Logger b = Logger.a(TrackHeaderBox.class);
    private static final JoinPoint.StaticPart m = null;
    private static final JoinPoint.StaticPart n = null;
    private static final JoinPoint.StaticPart o = null;
    private static final JoinPoint.StaticPart p = null;
    private static final JoinPoint.StaticPart q = null;
    private static final JoinPoint.StaticPart u = null;
    private static final JoinPoint.StaticPart v = null;
    private static final JoinPoint.StaticPart w = null;
    private static final JoinPoint.StaticPart x = null;
    private static final JoinPoint.StaticPart y = null;
    private static final JoinPoint.StaticPart z = null;
    private Date c = new Date(0);
    private Date d = new Date(0);
    private long e;
    private long f;
    private int g;
    private int h;
    private float i;
    private Matrix j = Matrix.f4105a;
    private double k;
    private double l;

    private static void s() {
        Factory factory = new Factory("TrackHeaderBox.java", TrackHeaderBox.class);
        m = factory.a("method-execution", (Signature) factory.a("1", "getCreationTime", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "", "", "", "java.util.Date"), 60);
        n = factory.a("method-execution", (Signature) factory.a("1", "setCreationTime", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "java.util.Date", "creationTime", "", "void"), 64);
        z = factory.a("method-execution", (Signature) factory.a("1", "getAlternateGroup", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "", "", "", "int"), 110);
        A = factory.a("method-execution", (Signature) factory.a("1", "setAlternateGroup", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "int", "alternateGroup", "", "void"), 114);
        B = factory.a("method-execution", (Signature) factory.a("1", "getVolume", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "", "", "", "float"), 118);
        C = factory.a("method-execution", (Signature) factory.a("1", "setVolume", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "float", ReactVideoViewManager.PROP_VOLUME, "", "void"), 122);
        D = factory.a("method-execution", (Signature) factory.a("1", "getMatrix", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "", "", "", "org.mp4parser.support.Matrix"), 126);
        E = factory.a("method-execution", (Signature) factory.a("1", "setMatrix", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "org.mp4parser.support.Matrix", "matrix", "", "void"), 130);
        F = factory.a("method-execution", (Signature) factory.a("1", "getWidth", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "", "", "", "double"), 134);
        G = factory.a("method-execution", (Signature) factory.a("1", "setWidth", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "double", "width", "", "void"), 138);
        H = factory.a("method-execution", (Signature) factory.a("1", "getHeight", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "", "", "", "double"), 142);
        I = factory.a("method-execution", (Signature) factory.a("1", "setHeight", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "double", "height", "", "void"), 146);
        o = factory.a("method-execution", (Signature) factory.a("1", "getModificationTime", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "", "", "", "java.util.Date"), 71);
        J = factory.a("method-execution", (Signature) factory.a("1", "getContent", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "java.nio.ByteBuffer", "byteBuffer", "", "void"), 193);
        K = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "", "", "", "java.lang.String"), (int) TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS);
        L = factory.a("method-execution", (Signature) factory.a("1", "isEnabled", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "", "", "", "boolean"), 247);
        M = factory.a("method-execution", (Signature) factory.a("1", "setEnabled", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "boolean", "enabled", "", "void"), 251);
        N = factory.a("method-execution", (Signature) factory.a("1", "isInMovie", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "", "", "", "boolean"), 259);
        O = factory.a("method-execution", (Signature) factory.a("1", "setInMovie", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "boolean", "inMovie", "", "void"), 263);
        P = factory.a("method-execution", (Signature) factory.a("1", "isInPreview", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "", "", "", "boolean"), 271);
        Q = factory.a("method-execution", (Signature) factory.a("1", "setInPreview", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "boolean", "inPreview", "", "void"), 275);
        R = factory.a("method-execution", (Signature) factory.a("1", "isInPoster", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "", "", "", "boolean"), 283);
        S = factory.a("method-execution", (Signature) factory.a("1", "setInPoster", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "boolean", "inPoster", "", "void"), (int) OlympusImageProcessingMakernoteDirectory.I);
        p = factory.a("method-execution", (Signature) factory.a("1", "setModificationTime", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "java.util.Date", "modificationTime", "", "void"), 75);
        q = factory.a("method-execution", (Signature) factory.a("1", "getTrackId", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "", "", "", "long"), 83);
        u = factory.a("method-execution", (Signature) factory.a("1", "setTrackId", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "long", "trackId", "", "void"), 87);
        v = factory.a("method-execution", (Signature) factory.a("1", "getDuration", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "", "", "", "long"), 91);
        w = factory.a("method-execution", (Signature) factory.a("1", "setDuration", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "long", "duration", "", "void"), 95);
        x = factory.a("method-execution", (Signature) factory.a("1", "getLayer", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "", "", "", "int"), 102);
        y = factory.a("method-execution", (Signature) factory.a("1", "setLayer", "org.mp4parser.boxes.iso14496.part12.TrackHeaderBox", "int", "layer", "", "void"), 106);
    }

    static {
        s();
    }

    public TrackHeaderBox() {
        super(f3907a);
    }

    public Date e() {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this));
        return this.c;
    }

    public void a(Date date) {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this, (Object) date));
        this.c = date;
        if (DateHelper.a(date) >= IjkMediaMeta.AV_CH_WIDE_RIGHT) {
            a(1);
        }
    }

    public Date f() {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this));
        return this.d;
    }

    public void b(Date date) {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this, (Object) date));
        this.d = date;
        if (DateHelper.a(date) >= IjkMediaMeta.AV_CH_WIDE_RIGHT) {
            a(1);
        }
    }

    public long g() {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this));
        return this.e;
    }

    public void a(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this, Conversions.a(j2)));
        this.e = j2;
    }

    public long h() {
        RequiresParseDetailAspect.a().a(Factory.a(v, (Object) this, (Object) this));
        return this.f;
    }

    public void b(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(w, (Object) this, (Object) this, Conversions.a(j2)));
        this.f = j2;
        if (j2 >= IjkMediaMeta.AV_CH_WIDE_RIGHT) {
            b(1);
        }
    }

    public int i() {
        RequiresParseDetailAspect.a().a(Factory.a(x, (Object) this, (Object) this));
        return this.g;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(y, (Object) this, (Object) this, Conversions.a(i2)));
        this.g = i2;
    }

    public int j() {
        RequiresParseDetailAspect.a().a(Factory.a(z, (Object) this, (Object) this));
        return this.h;
    }

    public void d(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(A, (Object) this, (Object) this, Conversions.a(i2)));
        this.h = i2;
    }

    public float k() {
        RequiresParseDetailAspect.a().a(Factory.a(B, (Object) this, (Object) this));
        return this.i;
    }

    public void a(float f2) {
        RequiresParseDetailAspect.a().a(Factory.a(C, (Object) this, (Object) this, Conversions.a(f2)));
        this.i = f2;
    }

    public Matrix l() {
        RequiresParseDetailAspect.a().a(Factory.a(D, (Object) this, (Object) this));
        return this.j;
    }

    public void a(Matrix matrix) {
        RequiresParseDetailAspect.a().a(Factory.a(E, (Object) this, (Object) this, (Object) matrix));
        this.j = matrix;
    }

    public double m() {
        RequiresParseDetailAspect.a().a(Factory.a(F, (Object) this, (Object) this));
        return this.k;
    }

    public void a(double d2) {
        RequiresParseDetailAspect.a().a(Factory.a(G, (Object) this, (Object) this, Conversions.a(d2)));
        this.k = d2;
    }

    public double n() {
        RequiresParseDetailAspect.a().a(Factory.a(H, (Object) this, (Object) this));
        return this.l;
    }

    public void b(double d2) {
        RequiresParseDetailAspect.a().a(Factory.a(I, (Object) this, (Object) this, Conversions.a(d2)));
        this.l = d2;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (ag_() == 1 ? 36 : 24) + 60;
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        if (ag_() == 1) {
            this.c = DateHelper.a(IsoTypeReader.h(byteBuffer));
            this.d = DateHelper.a(IsoTypeReader.h(byteBuffer));
            this.e = IsoTypeReader.b(byteBuffer);
            IsoTypeReader.b(byteBuffer);
            this.f = byteBuffer.getLong();
        } else {
            this.c = DateHelper.a(IsoTypeReader.b(byteBuffer));
            this.d = DateHelper.a(IsoTypeReader.b(byteBuffer));
            this.e = IsoTypeReader.b(byteBuffer);
            IsoTypeReader.b(byteBuffer);
            this.f = (long) byteBuffer.getInt();
        }
        if (this.f < -1) {
            b.b("tkhd duration is not in expected range");
        }
        IsoTypeReader.b(byteBuffer);
        IsoTypeReader.b(byteBuffer);
        this.g = IsoTypeReader.d(byteBuffer);
        this.h = IsoTypeReader.d(byteBuffer);
        this.i = IsoTypeReader.k(byteBuffer);
        IsoTypeReader.d(byteBuffer);
        this.j = Matrix.a(byteBuffer);
        this.k = IsoTypeReader.i(byteBuffer);
        this.l = IsoTypeReader.i(byteBuffer);
    }

    public void b(ByteBuffer byteBuffer) {
        RequiresParseDetailAspect.a().a(Factory.a(J, (Object) this, (Object) this, (Object) byteBuffer));
        f(byteBuffer);
        if (ag_() == 1) {
            IsoTypeWriter.a(byteBuffer, DateHelper.a(this.c));
            IsoTypeWriter.a(byteBuffer, DateHelper.a(this.d));
            IsoTypeWriter.b(byteBuffer, this.e);
            IsoTypeWriter.b(byteBuffer, 0);
            byteBuffer.putLong(this.f);
        } else {
            IsoTypeWriter.b(byteBuffer, DateHelper.a(this.c));
            IsoTypeWriter.b(byteBuffer, DateHelper.a(this.d));
            IsoTypeWriter.b(byteBuffer, this.e);
            IsoTypeWriter.b(byteBuffer, 0);
            byteBuffer.putInt((int) this.f);
        }
        IsoTypeWriter.b(byteBuffer, 0);
        IsoTypeWriter.b(byteBuffer, 0);
        IsoTypeWriter.b(byteBuffer, this.g);
        IsoTypeWriter.b(byteBuffer, this.h);
        IsoTypeWriter.c(byteBuffer, (double) this.i);
        IsoTypeWriter.b(byteBuffer, 0);
        this.j.b(byteBuffer);
        IsoTypeWriter.a(byteBuffer, this.k);
        IsoTypeWriter.a(byteBuffer, this.l);
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(K, (Object) this, (Object) this));
        return "TrackHeaderBox[" + "creationTime=" + e() + i.b + "modificationTime=" + f() + i.b + "trackId=" + g() + i.b + "duration=" + h() + i.b + "layer=" + i() + i.b + "alternateGroup=" + j() + i.b + "volume=" + k() + i.b + "matrix=" + this.j + i.b + "width=" + m() + i.b + "height=" + n() + Operators.ARRAY_END_STR;
    }

    public boolean o() {
        RequiresParseDetailAspect.a().a(Factory.a(L, (Object) this, (Object) this));
        return (d() & 1) > 0;
    }

    public void a(boolean z2) {
        RequiresParseDetailAspect.a().a(Factory.a(M, (Object) this, (Object) this, Conversions.a(z2)));
        if (z2) {
            b(d() | 1);
        } else {
            b(d() & -2);
        }
    }

    public boolean p() {
        RequiresParseDetailAspect.a().a(Factory.a(N, (Object) this, (Object) this));
        return (d() & 2) > 0;
    }

    public void b(boolean z2) {
        RequiresParseDetailAspect.a().a(Factory.a(O, (Object) this, (Object) this, Conversions.a(z2)));
        if (z2) {
            b(d() | 2);
        } else {
            b(d() & -3);
        }
    }

    public boolean q() {
        RequiresParseDetailAspect.a().a(Factory.a(P, (Object) this, (Object) this));
        return (d() & 4) > 0;
    }

    public void c(boolean z2) {
        RequiresParseDetailAspect.a().a(Factory.a(Q, (Object) this, (Object) this, Conversions.a(z2)));
        if (z2) {
            b(d() | 4);
        } else {
            b(d() & -5);
        }
    }

    public boolean r() {
        RequiresParseDetailAspect.a().a(Factory.a(R, (Object) this, (Object) this));
        return (d() & 8) > 0;
    }

    public void d(boolean z2) {
        RequiresParseDetailAspect.a().a(Factory.a(S, (Object) this, (Object) this, Conversions.a(z2)));
        if (z2) {
            b(d() | 8);
        } else {
            b(d() & -9);
        }
    }
}
