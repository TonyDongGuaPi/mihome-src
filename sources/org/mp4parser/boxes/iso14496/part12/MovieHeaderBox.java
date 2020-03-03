package org.mp4parser.boxes.iso14496.part12;

import com.alipay.sdk.util.i;
import com.brentvatne.react.ReactVideoView;
import com.brentvatne.react.ReactVideoViewManager;
import com.taobao.weex.el.parse.Operators;
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

public class MovieHeaderBox extends AbstractFullBox {
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
    private static final JoinPoint.StaticPart T = null;
    private static final JoinPoint.StaticPart U = null;
    private static final JoinPoint.StaticPart V = null;

    /* renamed from: a  reason: collision with root package name */
    public static final String f3870a = "mvhd";
    private static Logger b = Logger.a(MovieHeaderBox.class);
    private static final JoinPoint.StaticPart q = null;
    private static final JoinPoint.StaticPart u = null;
    private static final JoinPoint.StaticPart v = null;
    private static final JoinPoint.StaticPart w = null;
    private static final JoinPoint.StaticPart x = null;
    private static final JoinPoint.StaticPart y = null;
    private static final JoinPoint.StaticPart z = null;
    private Date c;
    private Date d;
    private long e;
    private long f;
    private double g = 1.0d;
    private float h = 1.0f;
    private Matrix i = Matrix.f4105a;
    private long j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;

    private static void s() {
        Factory factory = new Factory("MovieHeaderBox.java", MovieHeaderBox.class);
        q = factory.a("method-execution", (Signature) factory.a("1", "getCreationTime", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "", "", "", "java.util.Date"), 62);
        u = factory.a("method-execution", (Signature) factory.a("1", "setCreationTime", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "java.util.Date", "creationTime", "", "void"), 66);
        D = factory.a("method-execution", (Signature) factory.a("1", "getVolume", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "", "", "", "float"), 113);
        E = factory.a("method-execution", (Signature) factory.a("1", "setVolume", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "float", ReactVideoViewManager.PROP_VOLUME, "", "void"), 117);
        F = factory.a("method-execution", (Signature) factory.a("1", "getMatrix", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "", "", "", "org.mp4parser.support.Matrix"), 121);
        G = factory.a("method-execution", (Signature) factory.a("1", "setMatrix", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "org.mp4parser.support.Matrix", "matrix", "", "void"), 125);
        H = factory.a("method-execution", (Signature) factory.a("1", "getNextTrackId", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "", "", "", "long"), 129);
        I = factory.a("method-execution", (Signature) factory.a("1", "setNextTrackId", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "long", "nextTrackId", "", "void"), 133);
        J = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "", "", "", "java.lang.String"), 187);
        K = factory.a("method-execution", (Signature) factory.a("1", "getPreviewTime", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "", "", "", "int"), 241);
        L = factory.a("method-execution", (Signature) factory.a("1", "setPreviewTime", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "int", "previewTime", "", "void"), 245);
        M = factory.a("method-execution", (Signature) factory.a("1", "getPreviewDuration", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "", "", "", "int"), 249);
        v = factory.a("method-execution", (Signature) factory.a("1", "getModificationTime", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "", "", "", "java.util.Date"), 74);
        N = factory.a("method-execution", (Signature) factory.a("1", "setPreviewDuration", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "int", "previewDuration", "", "void"), 253);
        O = factory.a("method-execution", (Signature) factory.a("1", "getPosterTime", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "", "", "", "int"), 257);
        P = factory.a("method-execution", (Signature) factory.a("1", "setPosterTime", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "int", "posterTime", "", "void"), 261);
        Q = factory.a("method-execution", (Signature) factory.a("1", "getSelectionTime", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "", "", "", "int"), 265);
        R = factory.a("method-execution", (Signature) factory.a("1", "setSelectionTime", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "int", "selectionTime", "", "void"), 269);
        S = factory.a("method-execution", (Signature) factory.a("1", "getSelectionDuration", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "", "", "", "int"), 273);
        T = factory.a("method-execution", (Signature) factory.a("1", "setSelectionDuration", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "int", "selectionDuration", "", "void"), 277);
        U = factory.a("method-execution", (Signature) factory.a("1", "getCurrentTime", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "", "", "", "int"), 281);
        V = factory.a("method-execution", (Signature) factory.a("1", "setCurrentTime", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "int", ReactVideoView.EVENT_PROP_CURRENT_TIME, "", "void"), 285);
        w = factory.a("method-execution", (Signature) factory.a("1", "setModificationTime", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "java.util.Date", "modificationTime", "", "void"), 78);
        x = factory.a("method-execution", (Signature) factory.a("1", "getTimescale", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "", "", "", "long"), 86);
        y = factory.a("method-execution", (Signature) factory.a("1", "setTimescale", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "long", "timescale", "", "void"), 90);
        z = factory.a("method-execution", (Signature) factory.a("1", "getDuration", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "", "", "", "long"), 94);
        A = factory.a("method-execution", (Signature) factory.a("1", "setDuration", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "long", "duration", "", "void"), 98);
        B = factory.a("method-execution", (Signature) factory.a("1", "getRate", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "", "", "", "double"), 105);
        C = factory.a("method-execution", (Signature) factory.a("1", "setRate", "org.mp4parser.boxes.iso14496.part12.MovieHeaderBox", "double", ReactVideoViewManager.PROP_RATE, "", "void"), 109);
    }

    static {
        s();
    }

    public MovieHeaderBox() {
        super("mvhd");
    }

    public Date e() {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this));
        return this.c;
    }

    public void a(Date date) {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this, (Object) date));
        this.c = date;
        if (DateHelper.a(date) >= IjkMediaMeta.AV_CH_WIDE_RIGHT) {
            a(1);
        }
    }

    public Date f() {
        RequiresParseDetailAspect.a().a(Factory.a(v, (Object) this, (Object) this));
        return this.d;
    }

    public void b(Date date) {
        RequiresParseDetailAspect.a().a(Factory.a(w, (Object) this, (Object) this, (Object) date));
        this.d = date;
        if (DateHelper.a(date) >= IjkMediaMeta.AV_CH_WIDE_RIGHT) {
            a(1);
        }
    }

    public long g() {
        RequiresParseDetailAspect.a().a(Factory.a(x, (Object) this, (Object) this));
        return this.e;
    }

    public void a(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(y, (Object) this, (Object) this, Conversions.a(j2)));
        this.e = j2;
    }

    public long h() {
        RequiresParseDetailAspect.a().a(Factory.a(z, (Object) this, (Object) this));
        return this.f;
    }

    public void b(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(A, (Object) this, (Object) this, Conversions.a(j2)));
        this.f = j2;
        if (j2 >= IjkMediaMeta.AV_CH_WIDE_RIGHT) {
            a(1);
        }
    }

    public double i() {
        RequiresParseDetailAspect.a().a(Factory.a(B, (Object) this, (Object) this));
        return this.g;
    }

    public void a(double d2) {
        RequiresParseDetailAspect.a().a(Factory.a(C, (Object) this, (Object) this, Conversions.a(d2)));
        this.g = d2;
    }

    public float j() {
        RequiresParseDetailAspect.a().a(Factory.a(D, (Object) this, (Object) this));
        return this.h;
    }

    public void a(float f2) {
        RequiresParseDetailAspect.a().a(Factory.a(E, (Object) this, (Object) this, Conversions.a(f2)));
        this.h = f2;
    }

    public Matrix k() {
        RequiresParseDetailAspect.a().a(Factory.a(F, (Object) this, (Object) this));
        return this.i;
    }

    public void a(Matrix matrix) {
        RequiresParseDetailAspect.a().a(Factory.a(G, (Object) this, (Object) this, (Object) matrix));
        this.i = matrix;
    }

    public long l() {
        RequiresParseDetailAspect.a().a(Factory.a(H, (Object) this, (Object) this));
        return this.j;
    }

    public void c(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(I, (Object) this, (Object) this, Conversions.a(j2)));
        this.j = j2;
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (ag_() == 1 ? 32 : 20) + 80;
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
            b.b("mvhd duration is not in expected range");
        }
        this.g = IsoTypeReader.i(byteBuffer);
        this.h = IsoTypeReader.k(byteBuffer);
        IsoTypeReader.d(byteBuffer);
        IsoTypeReader.b(byteBuffer);
        IsoTypeReader.b(byteBuffer);
        this.i = Matrix.a(byteBuffer);
        this.k = byteBuffer.getInt();
        this.l = byteBuffer.getInt();
        this.m = byteBuffer.getInt();
        this.n = byteBuffer.getInt();
        this.o = byteBuffer.getInt();
        this.p = byteBuffer.getInt();
        this.j = IsoTypeReader.b(byteBuffer);
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(J, (Object) this, (Object) this));
        return "MovieHeaderBox[" + "creationTime=" + e() + i.b + "modificationTime=" + f() + i.b + "timescale=" + g() + i.b + "duration=" + h() + i.b + "rate=" + i() + i.b + "volume=" + j() + i.b + "matrix=" + this.i + i.b + "nextTrackId=" + l() + Operators.ARRAY_END_STR;
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
        IsoTypeWriter.c(byteBuffer, (double) this.h);
        IsoTypeWriter.b(byteBuffer, 0);
        IsoTypeWriter.b(byteBuffer, 0);
        IsoTypeWriter.b(byteBuffer, 0);
        this.i.b(byteBuffer);
        byteBuffer.putInt(this.k);
        byteBuffer.putInt(this.l);
        byteBuffer.putInt(this.m);
        byteBuffer.putInt(this.n);
        byteBuffer.putInt(this.o);
        byteBuffer.putInt(this.p);
        IsoTypeWriter.b(byteBuffer, this.j);
    }

    public int m() {
        RequiresParseDetailAspect.a().a(Factory.a(K, (Object) this, (Object) this));
        return this.k;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(L, (Object) this, (Object) this, Conversions.a(i2)));
        this.k = i2;
    }

    public int n() {
        RequiresParseDetailAspect.a().a(Factory.a(M, (Object) this, (Object) this));
        return this.l;
    }

    public void d(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(N, (Object) this, (Object) this, Conversions.a(i2)));
        this.l = i2;
    }

    public int o() {
        RequiresParseDetailAspect.a().a(Factory.a(O, (Object) this, (Object) this));
        return this.m;
    }

    public void e(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(P, (Object) this, (Object) this, Conversions.a(i2)));
        this.m = i2;
    }

    public int p() {
        RequiresParseDetailAspect.a().a(Factory.a(Q, (Object) this, (Object) this));
        return this.n;
    }

    public void f(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(R, (Object) this, (Object) this, Conversions.a(i2)));
        this.n = i2;
    }

    public int q() {
        RequiresParseDetailAspect.a().a(Factory.a(S, (Object) this, (Object) this));
        return this.o;
    }

    public void g(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(T, (Object) this, (Object) this, Conversions.a(i2)));
        this.o = i2;
    }

    public int r() {
        RequiresParseDetailAspect.a().a(Factory.a(U, (Object) this, (Object) this));
        return this.p;
    }

    public void h(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(V, (Object) this, (Object) this, Conversions.a(i2)));
        this.p = i2;
    }
}
