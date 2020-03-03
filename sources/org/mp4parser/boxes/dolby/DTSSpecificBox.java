package org.mp4parser.boxes.dolby;

import com.tencent.smtt.sdk.TbsListener;
import java.nio.ByteBuffer;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitReaderBuffer;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitWriterBuffer;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.DoNotParseDetail;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class DTSSpecificBox extends AbstractBox {
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
    private static final JoinPoint.StaticPart W = null;
    private static final JoinPoint.StaticPart X = null;
    private static final JoinPoint.StaticPart Y = null;
    private static final JoinPoint.StaticPart Z = null;

    /* renamed from: a  reason: collision with root package name */
    public static final String f3814a = "ddts";
    private static final JoinPoint.StaticPart u = null;
    private static final JoinPoint.StaticPart v = null;
    private static final JoinPoint.StaticPart w = null;
    private static final JoinPoint.StaticPart x = null;
    private static final JoinPoint.StaticPart y = null;
    private static final JoinPoint.StaticPart z = null;
    long b;
    long c;
    long d;
    int e;
    int f;
    int g;
    int h;
    int i;
    int j;
    int k;
    int l;
    int m;
    int n;
    int o;
    int p;
    int q;

    static {
        u();
    }

    private static void u() {
        Factory factory = new Factory("DTSSpecificBox.java", DTSSpecificBox.class);
        u = factory.a("method-execution", (Signature) factory.a("1", "getAvgBitRate", "org.mp4parser.boxes.dolby.DTSSpecificBox", "", "", "", "long"), 89);
        v = factory.a("method-execution", (Signature) factory.a("1", "setAvgBitRate", "org.mp4parser.boxes.dolby.DTSSpecificBox", "long", "avgBitRate", "", "void"), 93);
        E = factory.a("method-execution", (Signature) factory.a("1", "getStreamConstruction", "org.mp4parser.boxes.dolby.DTSSpecificBox", "", "", "", "int"), 129);
        F = factory.a("method-execution", (Signature) factory.a("1", "setStreamConstruction", "org.mp4parser.boxes.dolby.DTSSpecificBox", "int", "streamConstruction", "", "void"), 133);
        G = factory.a("method-execution", (Signature) factory.a("1", "getCoreLFEPresent", "org.mp4parser.boxes.dolby.DTSSpecificBox", "", "", "", "int"), 137);
        H = factory.a("method-execution", (Signature) factory.a("1", "setCoreLFEPresent", "org.mp4parser.boxes.dolby.DTSSpecificBox", "int", "coreLFEPresent", "", "void"), 141);
        I = factory.a("method-execution", (Signature) factory.a("1", "getCoreLayout", "org.mp4parser.boxes.dolby.DTSSpecificBox", "", "", "", "int"), 145);
        J = factory.a("method-execution", (Signature) factory.a("1", "setCoreLayout", "org.mp4parser.boxes.dolby.DTSSpecificBox", "int", "coreLayout", "", "void"), 149);
        K = factory.a("method-execution", (Signature) factory.a("1", "getCoreSize", "org.mp4parser.boxes.dolby.DTSSpecificBox", "", "", "", "int"), 153);
        L = factory.a("method-execution", (Signature) factory.a("1", "setCoreSize", "org.mp4parser.boxes.dolby.DTSSpecificBox", "int", "coreSize", "", "void"), 157);
        M = factory.a("method-execution", (Signature) factory.a("1", "getStereoDownmix", "org.mp4parser.boxes.dolby.DTSSpecificBox", "", "", "", "int"), 161);
        N = factory.a("method-execution", (Signature) factory.a("1", "setStereoDownmix", "org.mp4parser.boxes.dolby.DTSSpecificBox", "int", "stereoDownmix", "", "void"), 165);
        w = factory.a("method-execution", (Signature) factory.a("1", "getDTSSamplingFrequency", "org.mp4parser.boxes.dolby.DTSSpecificBox", "", "", "", "long"), 97);
        O = factory.a("method-execution", (Signature) factory.a("1", "getRepresentationType", "org.mp4parser.boxes.dolby.DTSSpecificBox", "", "", "", "int"), 169);
        P = factory.a("method-execution", (Signature) factory.a("1", "setRepresentationType", "org.mp4parser.boxes.dolby.DTSSpecificBox", "int", "representationType", "", "void"), 173);
        Q = factory.a("method-execution", (Signature) factory.a("1", "getChannelLayout", "org.mp4parser.boxes.dolby.DTSSpecificBox", "", "", "", "int"), 177);
        R = factory.a("method-execution", (Signature) factory.a("1", "setChannelLayout", "org.mp4parser.boxes.dolby.DTSSpecificBox", "int", "channelLayout", "", "void"), 181);
        S = factory.a("method-execution", (Signature) factory.a("1", "getMultiAssetFlag", "org.mp4parser.boxes.dolby.DTSSpecificBox", "", "", "", "int"), 185);
        T = factory.a("method-execution", (Signature) factory.a("1", "setMultiAssetFlag", "org.mp4parser.boxes.dolby.DTSSpecificBox", "int", "multiAssetFlag", "", "void"), 189);
        U = factory.a("method-execution", (Signature) factory.a("1", "getLBRDurationMod", "org.mp4parser.boxes.dolby.DTSSpecificBox", "", "", "", "int"), 193);
        V = factory.a("method-execution", (Signature) factory.a("1", "setLBRDurationMod", "org.mp4parser.boxes.dolby.DTSSpecificBox", "int", "LBRDurationMod", "", "void"), (int) Opcodes.dg);
        W = factory.a("method-execution", (Signature) factory.a("1", "getReserved", "org.mp4parser.boxes.dolby.DTSSpecificBox", "", "", "", "int"), 201);
        X = factory.a("method-execution", (Signature) factory.a("1", "setReserved", "org.mp4parser.boxes.dolby.DTSSpecificBox", "int", "reserved", "", "void"), 205);
        x = factory.a("method-execution", (Signature) factory.a("1", "setDTSSamplingFrequency", "org.mp4parser.boxes.dolby.DTSSpecificBox", "long", "DTSSamplingFrequency", "", "void"), 101);
        Y = factory.a("method-execution", (Signature) factory.a("1", "getReservedBoxPresent", "org.mp4parser.boxes.dolby.DTSSpecificBox", "", "", "", "int"), 209);
        Z = factory.a("method-execution", (Signature) factory.a("1", "setReservedBoxPresent", "org.mp4parser.boxes.dolby.DTSSpecificBox", "int", "reservedBoxPresent", "", "void"), (int) TbsListener.ErrorCode.COPY_SRCDIR_ERROR);
        y = factory.a("method-execution", (Signature) factory.a("1", "getMaxBitRate", "org.mp4parser.boxes.dolby.DTSSpecificBox", "", "", "", "long"), 105);
        z = factory.a("method-execution", (Signature) factory.a("1", "setMaxBitRate", "org.mp4parser.boxes.dolby.DTSSpecificBox", "long", "maxBitRate", "", "void"), 109);
        A = factory.a("method-execution", (Signature) factory.a("1", "getPcmSampleDepth", "org.mp4parser.boxes.dolby.DTSSpecificBox", "", "", "", "int"), 113);
        B = factory.a("method-execution", (Signature) factory.a("1", "setPcmSampleDepth", "org.mp4parser.boxes.dolby.DTSSpecificBox", "int", "pcmSampleDepth", "", "void"), 117);
        C = factory.a("method-execution", (Signature) factory.a("1", "getFrameDuration", "org.mp4parser.boxes.dolby.DTSSpecificBox", "", "", "", "int"), 121);
        D = factory.a("method-execution", (Signature) factory.a("1", "setFrameDuration", "org.mp4parser.boxes.dolby.DTSSpecificBox", "int", "frameDuration", "", "void"), 125);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return 20;
    }

    public DTSSpecificBox() {
        super(f3814a);
    }

    public void a(ByteBuffer byteBuffer) {
        this.b = IsoTypeReader.b(byteBuffer);
        this.c = IsoTypeReader.b(byteBuffer);
        this.d = IsoTypeReader.b(byteBuffer);
        this.e = IsoTypeReader.f(byteBuffer);
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(byteBuffer);
        this.f = bitReaderBuffer.a(2);
        this.g = bitReaderBuffer.a(5);
        this.h = bitReaderBuffer.a(1);
        this.i = bitReaderBuffer.a(6);
        this.j = bitReaderBuffer.a(14);
        this.k = bitReaderBuffer.a(1);
        this.l = bitReaderBuffer.a(3);
        this.m = bitReaderBuffer.a(16);
        this.n = bitReaderBuffer.a(1);
        this.o = bitReaderBuffer.a(1);
        this.p = bitReaderBuffer.a(1);
        this.q = bitReaderBuffer.a(5);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        IsoTypeWriter.b(byteBuffer, this.b);
        IsoTypeWriter.b(byteBuffer, this.c);
        IsoTypeWriter.b(byteBuffer, this.d);
        IsoTypeWriter.d(byteBuffer, this.e);
        BitWriterBuffer bitWriterBuffer = new BitWriterBuffer(byteBuffer);
        bitWriterBuffer.a(this.f, 2);
        bitWriterBuffer.a(this.g, 5);
        bitWriterBuffer.a(this.h, 1);
        bitWriterBuffer.a(this.i, 6);
        bitWriterBuffer.a(this.j, 14);
        bitWriterBuffer.a(this.k, 1);
        bitWriterBuffer.a(this.l, 3);
        bitWriterBuffer.a(this.m, 16);
        bitWriterBuffer.a(this.n, 1);
        bitWriterBuffer.a(this.o, 1);
        bitWriterBuffer.a(this.p, 1);
        bitWriterBuffer.a(this.q, 5);
    }

    public long d() {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this));
        return this.d;
    }

    public void a(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(v, (Object) this, (Object) this, Conversions.a(j2)));
        this.d = j2;
    }

    public long e() {
        RequiresParseDetailAspect.a().a(Factory.a(w, (Object) this, (Object) this));
        return this.b;
    }

    public void b(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(x, (Object) this, (Object) this, Conversions.a(j2)));
        this.b = j2;
    }

    public long f() {
        RequiresParseDetailAspect.a().a(Factory.a(y, (Object) this, (Object) this));
        return this.c;
    }

    public void c(long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(z, (Object) this, (Object) this, Conversions.a(j2)));
        this.c = j2;
    }

    public int g() {
        RequiresParseDetailAspect.a().a(Factory.a(A, (Object) this, (Object) this));
        return this.e;
    }

    public void a(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(B, (Object) this, (Object) this, Conversions.a(i2)));
        this.e = i2;
    }

    public int h() {
        RequiresParseDetailAspect.a().a(Factory.a(C, (Object) this, (Object) this));
        return this.f;
    }

    public void b(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(D, (Object) this, (Object) this, Conversions.a(i2)));
        this.f = i2;
    }

    public int i() {
        RequiresParseDetailAspect.a().a(Factory.a(E, (Object) this, (Object) this));
        return this.g;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(F, (Object) this, (Object) this, Conversions.a(i2)));
        this.g = i2;
    }

    public int j() {
        RequiresParseDetailAspect.a().a(Factory.a(G, (Object) this, (Object) this));
        return this.h;
    }

    public void d(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(H, (Object) this, (Object) this, Conversions.a(i2)));
        this.h = i2;
    }

    public int k() {
        RequiresParseDetailAspect.a().a(Factory.a(I, (Object) this, (Object) this));
        return this.i;
    }

    public void e(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(J, (Object) this, (Object) this, Conversions.a(i2)));
        this.i = i2;
    }

    public int l() {
        RequiresParseDetailAspect.a().a(Factory.a(K, (Object) this, (Object) this));
        return this.j;
    }

    public void f(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(L, (Object) this, (Object) this, Conversions.a(i2)));
        this.j = i2;
    }

    public int m() {
        RequiresParseDetailAspect.a().a(Factory.a(M, (Object) this, (Object) this));
        return this.k;
    }

    public void g(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(N, (Object) this, (Object) this, Conversions.a(i2)));
        this.k = i2;
    }

    public int n() {
        RequiresParseDetailAspect.a().a(Factory.a(O, (Object) this, (Object) this));
        return this.l;
    }

    public void h(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(P, (Object) this, (Object) this, Conversions.a(i2)));
        this.l = i2;
    }

    public int o() {
        RequiresParseDetailAspect.a().a(Factory.a(Q, (Object) this, (Object) this));
        return this.m;
    }

    public void i(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(R, (Object) this, (Object) this, Conversions.a(i2)));
        this.m = i2;
    }

    public int p() {
        RequiresParseDetailAspect.a().a(Factory.a(S, (Object) this, (Object) this));
        return this.n;
    }

    public void j(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(T, (Object) this, (Object) this, Conversions.a(i2)));
        this.n = i2;
    }

    public int q() {
        RequiresParseDetailAspect.a().a(Factory.a(U, (Object) this, (Object) this));
        return this.o;
    }

    public void k(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(V, (Object) this, (Object) this, Conversions.a(i2)));
        this.o = i2;
    }

    public int r() {
        RequiresParseDetailAspect.a().a(Factory.a(W, (Object) this, (Object) this));
        return this.q;
    }

    public void l(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(X, (Object) this, (Object) this, Conversions.a(i2)));
        this.q = i2;
    }

    public int s() {
        RequiresParseDetailAspect.a().a(Factory.a(Y, (Object) this, (Object) this));
        return this.p;
    }

    public void m(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(Z, (Object) this, (Object) this, Conversions.a(i2)));
        this.p = i2;
    }

    @DoNotParseDetail
    public int[] t() {
        int i2;
        int i3;
        int o2 = o();
        if ((o2 & 1) == 1) {
            i3 = 1;
            i2 = 4;
        } else {
            i3 = 0;
            i2 = 0;
        }
        if ((o2 & 2) == 2) {
            i3 += 2;
            i2 = i2 | 1 | 2;
        }
        if ((o2 & 4) == 4) {
            i3 += 2;
            i2 = i2 | 16 | 32;
        }
        if ((o2 & 8) == 8) {
            i3++;
            i2 |= 8;
        }
        if ((o2 & 16) == 16) {
            i3++;
            i2 |= 256;
        }
        if ((o2 & 32) == 32) {
            i3 += 2;
            i2 = i2 | 4096 | 16384;
        }
        if ((o2 & 64) == 64) {
            i3 += 2;
            i2 = i2 | 16 | 32;
        }
        if ((o2 & 128) == 128) {
            i3++;
            i2 |= 8192;
        }
        if ((o2 & 256) == 256) {
            i3++;
            i2 |= 2048;
        }
        if ((o2 & 512) == 512) {
            i3 += 2;
            i2 = i2 | 64 | 128;
        }
        if ((o2 & 1024) == 1024) {
            i3 += 2;
            i2 = i2 | 512 | 1024;
        }
        if ((o2 & 2048) == 2048) {
            i3 += 2;
            i2 = i2 | 16 | 32;
        }
        if ((o2 & 4096) == 4096) {
            i3++;
            i2 |= 8;
        }
        if ((o2 & 8192) == 8192) {
            i3 += 2;
            i2 = i2 | 16 | 32;
        }
        if ((o2 & 16384) == 16384) {
            i3++;
            i2 |= 65536;
        }
        if ((32768 & o2) == 32768) {
            i3 += 2;
            i2 = 131072 | 32768 | i2;
        }
        if ((65536 & o2) == 65536) {
            i3++;
        }
        if ((o2 & 131072) == 131072) {
            i3 += 2;
        }
        return new int[]{i3, i2};
    }
}
