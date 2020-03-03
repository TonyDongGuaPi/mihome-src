package com.miuipub.internal.graphics.gif;

import android.graphics.Bitmap;
import com.drew.metadata.exif.ExifDirectoryBase;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Vector;

public class GifDecoder {

    /* renamed from: a  reason: collision with root package name */
    public static final int f8250a = 1048576;
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    protected static final int f = 4096;
    protected int A;
    protected int B;
    protected int C;
    protected Bitmap D;
    protected Bitmap E;
    protected byte[] F = new byte[256];
    protected int G = 0;
    protected int H = 0;
    protected int I = 0;
    protected boolean J = false;
    protected int K = 0;
    protected int L;
    protected short[] M;
    protected byte[] N;
    protected byte[] O;
    protected byte[] P;
    protected Vector<GifFrame> Q;
    private long R;
    private long S = 1048576;
    private boolean T;
    private int U;
    private int V;
    private int W;
    private int X;
    private int[] Y;
    private boolean Z = false;
    private boolean aa = false;
    protected BufferedInputStream g;
    protected int h;
    protected boolean i;
    protected int j;
    protected int k = 1;
    protected int[] l;
    protected int[] m;
    protected int[] n;
    protected int o;
    protected int p;
    protected int q;
    protected int r;
    protected boolean s;
    protected boolean t;
    protected int u;
    protected int v;
    protected int w;
    protected int x;
    protected int y;
    protected int z;

    private void y() {
    }

    public boolean a() {
        return this.T;
    }

    public void a(int i2) {
        this.V = i2;
    }

    public void b() {
        if (this.Q != null) {
            int size = this.Q.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.Q.elementAt(i2).a();
            }
        }
    }

    public void a(long j2) {
        this.S = j2;
    }

    private static class GifFrame {

        /* renamed from: a  reason: collision with root package name */
        public Bitmap f8251a;
        public int b;

        public GifFrame(Bitmap bitmap, int i) {
            this.f8251a = bitmap;
            this.b = i;
        }

        public void a() {
            if (this.f8251a != null && !this.f8251a.isRecycled()) {
                this.f8251a.recycle();
            }
        }
    }

    public int b(int i2) {
        this.K = -1;
        int c2 = c();
        if (i2 >= 0 && i2 < c2) {
            this.K = this.Q.elementAt(i2).b;
        }
        return this.K;
    }

    public int c() {
        if (this.Q == null) {
            return 0;
        }
        return this.Q.size();
    }

    public Bitmap d() {
        return c(0);
    }

    public int e() {
        return this.k;
    }

    /* access modifiers changed from: protected */
    public void f() {
        int i2;
        if (this.Y == null) {
            this.Y = new int[(this.W * this.X)];
        }
        int i3 = 0;
        if (this.I > 0) {
            if (this.I == 3) {
                int c2 = c() - 2;
                if (c2 > 0) {
                    Bitmap c3 = c(c2 - 1);
                    if (!c3.equals(this.E)) {
                        this.E = c3;
                        this.E.getPixels(this.Y, 0, this.W, 0, 0, this.W, this.X);
                    }
                } else {
                    this.E = null;
                    this.Y = new int[(this.W * this.X)];
                }
            }
            if (this.E != null && this.I == 2) {
                int i4 = !this.J ? this.q : 0;
                int i5 = (this.A * this.W) + this.z;
                for (int i6 = 0; i6 < this.C; i6++) {
                    int i7 = this.B + i5;
                    for (int i8 = i5; i8 < i7; i8++) {
                        this.Y[i8] = i4;
                    }
                    i5 += this.W;
                }
            }
        }
        int i9 = 0;
        int i10 = 1;
        int i11 = 8;
        while (i3 < this.y) {
            if (this.t) {
                if (i9 >= this.y) {
                    i10++;
                    switch (i10) {
                        case 2:
                            i9 = 4;
                            break;
                        case 3:
                            i9 = 2;
                            i11 = 4;
                            break;
                        case 4:
                            i9 = 1;
                            i11 = 2;
                            break;
                    }
                }
                i2 = i9 + i11;
            } else {
                i2 = i9;
                i9 = i3;
            }
            int i12 = i9 + this.w;
            if (i12 < this.X) {
                int i13 = i12 * this.W;
                int i14 = this.v + i13;
                int i15 = this.x + i14;
                if (this.W + i13 < i15) {
                    i15 = this.W + i13;
                }
                int i16 = this.x * i3;
                while (i14 < i15) {
                    int i17 = i16 + 1;
                    int i18 = this.n[this.P[i16] & 255];
                    if (i18 != 0) {
                        this.Y[i14] = i18;
                    }
                    i14++;
                    i16 = i17;
                }
            }
            i3++;
            i9 = i2;
        }
        if (this.U <= this.V && this.D != null && !this.D.isRecycled()) {
            this.D.recycle();
        }
        this.D = Bitmap.createBitmap(this.Y, this.W, this.X, Bitmap.Config.ARGB_8888);
    }

    public Bitmap c(int i2) {
        int c2 = c();
        if (c2 <= 0) {
            return null;
        }
        return this.Q.elementAt(i2 % c2).f8251a;
    }

    public void g() {
        this.Z = true;
        y();
    }

    protected static int a(InputStream inputStream) {
        try {
            return inputStream.read();
        } catch (Exception unused) {
            return -1;
        }
    }

    public static boolean b(InputStream inputStream) {
        int a2;
        if (inputStream == null) {
            return false;
        }
        String str = "";
        for (int i2 = 0; i2 < 6 && (a2 = a(inputStream)) != -1; i2++) {
            str = str + ((char) a2);
        }
        return str.startsWith("GIF");
    }

    public int c(InputStream inputStream) {
        this.T = false;
        if (!this.aa) {
            this.aa = true;
            j();
            if (inputStream != null) {
                this.g = new BufferedInputStream(inputStream);
                try {
                    p();
                    if (!this.Z && !i()) {
                        n();
                        if (c() < 0) {
                            this.h = 1;
                        }
                    }
                } catch (OutOfMemoryError unused) {
                    this.h = 2;
                    b();
                }
            } else {
                this.h = 2;
            }
            if (this.Z) {
                b();
                this.h = 3;
            }
            return this.h;
        }
        throw new IllegalStateException("decoder cannot be called more than once");
    }

    /* access modifiers changed from: protected */
    public void h() {
        int i2;
        int i3;
        byte b2;
        int i4;
        short s2;
        int i5 = this.x * this.y;
        if (this.P == null || this.P.length < i5) {
            this.P = new byte[i5];
        }
        if (this.M == null) {
            this.M = new short[4096];
        }
        if (this.N == null) {
            this.N = new byte[4096];
        }
        if (this.O == null) {
            this.O = new byte[4097];
        }
        int k2 = k();
        int i6 = 1 << k2;
        int i7 = i6 + 1;
        int i8 = i6 + 2;
        int i9 = k2 + 1;
        int i10 = (1 << i9) - 1;
        for (int i11 = 0; i11 < i6; i11++) {
            this.M[i11] = 0;
            this.N[i11] = (byte) i11;
        }
        int i12 = i9;
        int i13 = i8;
        int i14 = i10;
        byte b3 = -1;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        byte b4 = 0;
        int i21 = 0;
        while (i15 < i5) {
            if (i16 == 0) {
                if (i17 >= i12) {
                    byte b5 = i19 & i14;
                    i19 >>= i12;
                    i17 -= i12;
                    if (b5 > i13 || b5 == i7) {
                        break;
                    } else if (b5 == i6) {
                        i12 = i9;
                        i13 = i8;
                        i14 = i10;
                        b3 = -1;
                    } else if (b3 == -1) {
                        this.O[i16] = this.N[b5];
                        b3 = b5;
                        b4 = b3;
                        i16++;
                        i9 = i9;
                    } else {
                        i3 = i9;
                        if (b5 == i13) {
                            i4 = i16 + 1;
                            b2 = b5;
                            this.O[i16] = (byte) b4;
                            s2 = b3;
                        } else {
                            b2 = b5;
                            i4 = i16;
                            s2 = b2;
                        }
                        while (s2 > i6) {
                            this.O[i4] = this.N[s2];
                            s2 = this.M[s2];
                            i4++;
                            i6 = i6;
                        }
                        i2 = i6;
                        byte b6 = this.N[s2] & 255;
                        if (i13 >= 4096) {
                            break;
                        }
                        i16 = i4 + 1;
                        byte b7 = (byte) b6;
                        this.O[i4] = b7;
                        this.M[i13] = (short) b3;
                        this.N[i13] = b7;
                        i13++;
                        if ((i13 & i14) == 0) {
                            if (i13 < 4096) {
                                i12++;
                                i14 += i13;
                            }
                        }
                        b4 = b6;
                        b3 = b2;
                    }
                } else {
                    if (i18 == 0) {
                        i18 = l();
                        if (i18 <= 0) {
                            break;
                        }
                        i20 = 0;
                    }
                    i19 += (this.F[i20] & 255) << i17;
                    i17 += 8;
                    i20++;
                    i18--;
                }
            } else {
                i3 = i9;
                i2 = i6;
                byte b8 = b4;
            }
            i16--;
            this.P[i21] = this.O[i16];
            i15++;
            i21++;
            i9 = i3;
            i6 = i2;
        }
        for (int i22 = i21; i22 < i5; i22++) {
            this.P[i22] = 0;
        }
    }

    /* access modifiers changed from: protected */
    public boolean i() {
        return this.h != 0;
    }

    /* access modifiers changed from: protected */
    public void j() {
        this.h = 0;
        this.Q = new Vector<>();
        this.l = null;
        this.m = null;
    }

    /* access modifiers changed from: protected */
    public int k() {
        try {
            return this.g.read();
        } catch (Exception unused) {
            this.h = 1;
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    public int l() {
        this.G = k();
        int i2 = 0;
        if (this.G > 0) {
            while (i2 < this.G) {
                try {
                    int read = this.g.read(this.F, i2, this.G - i2);
                    if (read == -1) {
                        break;
                    }
                    i2 += read;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (i2 < this.G) {
                this.h = 1;
            }
        }
        return i2;
    }

    /* access modifiers changed from: protected */
    public int[] d(int i2) {
        int i3;
        int i4 = i2 * 3;
        byte[] bArr = new byte[i4];
        try {
            i3 = this.g.read(bArr, 0, bArr.length);
        } catch (Exception e2) {
            e2.printStackTrace();
            i3 = 0;
        }
        if (i3 < i4) {
            this.h = 1;
            return null;
        }
        int[] iArr = new int[256];
        int i5 = 0;
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = i5 + 1;
            int i8 = i7 + 1;
            iArr[i6] = ((bArr[i5] & 255) << 16) | -16777216 | ((bArr[i7] & 255) << 8) | (bArr[i8] & 255);
            i5 = i8 + 1;
        }
        return iArr;
    }

    public int m() {
        if (this.T) {
            return this.U;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void n() {
        this.U = 0;
        boolean z2 = false;
        while (!z2 && !i() && !this.Z) {
            int k2 = k();
            if (k2 != 33) {
                if (k2 == 44) {
                    int size = this.Q.size();
                    q();
                    if (this.Q.size() > size) {
                        this.R += (long) (this.D.getRowBytes() * this.D.getHeight());
                    }
                    if (this.R <= this.S) {
                    }
                } else if (k2 != 59) {
                    this.h = 1;
                } else {
                    this.T = true;
                }
                z2 = true;
            } else {
                int k3 = k();
                if (k3 == 1) {
                    v();
                } else if (k3 != 249) {
                    switch (k3) {
                        case ExifDirectoryBase.g:
                            v();
                            break;
                        case 255:
                            l();
                            String str = "";
                            for (int i2 = 0; i2 < 11; i2++) {
                                str = str + ((char) this.F[i2]);
                            }
                            if (!str.equals("NETSCAPE2.0")) {
                                v();
                                break;
                            } else {
                                s();
                                break;
                            }
                        default:
                            v();
                            break;
                    }
                } else {
                    o();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void o() {
        k();
        int k2 = k();
        this.H = (k2 & 28) >> 2;
        boolean z2 = true;
        if (this.H == 0) {
            this.H = 1;
        }
        if ((k2 & 1) == 0) {
            z2 = false;
        }
        this.J = z2;
        this.K = t() * 10;
        if (this.K <= 0) {
            this.K = 100;
        }
        this.L = k();
        k();
    }

    /* access modifiers changed from: protected */
    public void p() {
        if (!this.Z) {
            String str = "";
            for (int i2 = 0; i2 < 6; i2++) {
                str = str + ((char) k());
            }
            if (!str.startsWith("GIF")) {
                this.h = 1;
                return;
            }
            r();
            if (this.i && !i()) {
                this.l = d(this.j);
                this.p = this.l[this.o];
            }
        }
    }

    /* access modifiers changed from: protected */
    public void q() {
        int i2;
        this.v = t();
        this.w = t();
        this.x = t();
        this.y = t();
        int k2 = k();
        this.s = (k2 & 128) != 0;
        this.u = 2 << (k2 & 7);
        this.t = (k2 & 64) != 0;
        if (this.s) {
            this.m = d(this.u);
            this.n = this.m;
        } else {
            this.n = this.l;
            if (this.o == this.L) {
                this.p = 0;
            }
        }
        if (this.J) {
            i2 = this.n[this.L];
            this.n[this.L] = 0;
        } else {
            i2 = 0;
        }
        if (this.n == null) {
            this.h = 1;
        }
        if (!i()) {
            h();
            v();
            if (!i() && !this.Z) {
                f();
                if (this.U >= this.V) {
                    this.Q.addElement(new GifFrame(this.D, this.K));
                }
                this.U++;
                if (this.J) {
                    this.n[this.L] = i2;
                }
                u();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void r() {
        this.W = t();
        this.X = t();
        int k2 = k();
        this.i = (k2 & 128) != 0;
        this.j = 2 << (k2 & 7);
        this.o = k();
        this.r = k();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void s() {
        /*
            r3 = this;
        L_0x0000:
            r3.l()
            byte[] r0 = r3.F
            r1 = 0
            byte r0 = r0[r1]
            r1 = 1
            if (r0 != r1) goto L_0x001d
            byte[] r0 = r3.F
            byte r0 = r0[r1]
            r0 = r0 & 255(0xff, float:3.57E-43)
            byte[] r1 = r3.F
            r2 = 2
            byte r1 = r1[r2]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << 8
            r0 = r0 | r1
            r3.k = r0
        L_0x001d:
            int r0 = r3.G
            if (r0 <= 0) goto L_0x0027
            boolean r0 = r3.i()
            if (r0 == 0) goto L_0x0000
        L_0x0027:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miuipub.internal.graphics.gif.GifDecoder.s():void");
    }

    /* access modifiers changed from: protected */
    public int t() {
        return k() | (k() << 8);
    }

    /* access modifiers changed from: protected */
    public void u() {
        this.I = this.H;
        this.z = this.v;
        this.A = this.w;
        this.B = this.x;
        this.C = this.y;
        this.E = this.D;
        this.q = this.p;
        this.H = 0;
        this.J = false;
        this.K = 0;
        this.m = null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void v() {
        /*
            r1 = this;
        L_0x0000:
            r1.l()
            int r0 = r1.G
            if (r0 <= 0) goto L_0x000d
            boolean r0 = r1.i()
            if (r0 == 0) goto L_0x0000
        L_0x000d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miuipub.internal.graphics.gif.GifDecoder.v():void");
    }

    public int w() {
        return this.W;
    }

    public int x() {
        return this.X;
    }
}
