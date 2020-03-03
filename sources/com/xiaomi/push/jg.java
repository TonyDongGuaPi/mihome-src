package com.xiaomi.push;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class jg extends jk {
    private static final jp f = new jp();

    /* renamed from: a  reason: collision with root package name */
    protected boolean f12823a = false;
    protected boolean b = true;
    protected int c;
    protected boolean d = false;
    private byte[] g = new byte[1];
    private byte[] h = new byte[2];
    private byte[] i = new byte[4];
    private byte[] j = new byte[8];
    private byte[] k = new byte[1];
    private byte[] l = new byte[2];
    private byte[] m = new byte[4];
    private byte[] n = new byte[8];

    public static class a implements jm {

        /* renamed from: a  reason: collision with root package name */
        protected int f12824a;

        /* renamed from: a  reason: collision with other field name */
        protected boolean f225a;
        protected boolean b;

        public a() {
            this(false, true);
        }

        public a(boolean z, boolean z2) {
            this(z, z2, 0);
        }

        public a(boolean z, boolean z2, int i) {
            this.f225a = false;
            this.b = true;
            this.f225a = z;
            this.b = z2;
            this.f12824a = i;
        }

        public jk a(ju juVar) {
            jg jgVar = new jg(juVar, this.f225a, this.b);
            if (this.f12824a != 0) {
                jgVar.c(this.f12824a);
            }
            return jgVar;
        }
    }

    public jg(ju juVar, boolean z, boolean z2) {
        super(juVar);
        this.f12823a = z;
        this.b = z2;
    }

    private int a(byte[] bArr, int i2, int i3) {
        d(i3);
        return this.e.d(bArr, i2, i3);
    }

    public void a() {
    }

    public void a(byte b2) {
        this.g[0] = b2;
        this.e.b(this.g, 0, 1);
    }

    public void a(int i2) {
        this.i[0] = (byte) ((i2 >> 24) & 255);
        this.i[1] = (byte) ((i2 >> 16) & 255);
        this.i[2] = (byte) ((i2 >> 8) & 255);
        this.i[3] = (byte) (i2 & 255);
        this.e.b(this.i, 0, 4);
    }

    public void a(long j2) {
        this.j[0] = (byte) ((int) ((j2 >> 56) & 255));
        this.j[1] = (byte) ((int) ((j2 >> 48) & 255));
        this.j[2] = (byte) ((int) ((j2 >> 40) & 255));
        this.j[3] = (byte) ((int) ((j2 >> 32) & 255));
        this.j[4] = (byte) ((int) ((j2 >> 24) & 255));
        this.j[5] = (byte) ((int) ((j2 >> 16) & 255));
        this.j[6] = (byte) ((int) ((j2 >> 8) & 255));
        this.j[7] = (byte) ((int) (j2 & 255));
        this.e.b(this.j, 0, 8);
    }

    public void a(jh jhVar) {
        a(jhVar.b);
        a(jhVar.c);
    }

    public void a(ji jiVar) {
        a(jiVar.f12826a);
        a(jiVar.b);
    }

    public void a(jj jjVar) {
        a(jjVar.f12827a);
        a(jjVar.b);
        a(jjVar.c);
    }

    public void a(jp jpVar) {
    }

    public void a(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes.length);
            this.e.b(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException unused) {
            throw new je("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public void a(ByteBuffer byteBuffer) {
        int limit = (byteBuffer.limit() - byteBuffer.position()) - byteBuffer.arrayOffset();
        a(limit);
        this.e.b(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), limit);
    }

    public void a(short s) {
        this.h[0] = (byte) ((s >> 8) & 255);
        this.h[1] = (byte) (s & 255);
        this.e.b(this.h, 0, 2);
    }

    public void a(boolean z) {
        a(z ? (byte) 1 : 0);
    }

    public String b(int i2) {
        try {
            d(i2);
            byte[] bArr = new byte[i2];
            this.e.d(bArr, 0, i2);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new je("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public void b() {
    }

    public void c() {
        a((byte) 0);
    }

    public void c(int i2) {
        this.c = i2;
        this.d = true;
    }

    public void d() {
    }

    /* access modifiers changed from: protected */
    public void d(int i2) {
        if (i2 < 0) {
            throw new je("Negative length: " + i2);
        } else if (this.d) {
            this.c -= i2;
            if (this.c < 0) {
                throw new je("Message length exceeded: " + i2);
            }
        }
    }

    public void e() {
    }

    public jp f() {
        return f;
    }

    public void g() {
    }

    public jh h() {
        byte q = q();
        return new jh("", q, q == 0 ? 0 : r());
    }

    public void i() {
    }

    public jj j() {
        return new jj(q(), q(), s());
    }

    public void k() {
    }

    public ji l() {
        return new ji(q(), s());
    }

    public void m() {
    }

    public jo n() {
        return new jo(q(), s());
    }

    public void o() {
    }

    public boolean p() {
        return q() == 1;
    }

    public byte q() {
        if (this.e.c() >= 1) {
            byte b2 = this.e.a()[this.e.b()];
            this.e.a(1);
            return b2;
        }
        a(this.k, 0, 1);
        return this.k[0];
    }

    public short r() {
        byte[] bArr = this.l;
        int i2 = 0;
        if (this.e.c() >= 2) {
            bArr = this.e.a();
            i2 = this.e.b();
            this.e.a(2);
        } else {
            a(this.l, 0, 2);
        }
        return (short) ((bArr[i2 + 1] & 255) | ((bArr[i2] & 255) << 8));
    }

    public int s() {
        byte[] bArr = this.m;
        int i2 = 0;
        if (this.e.c() >= 4) {
            bArr = this.e.a();
            i2 = this.e.b();
            this.e.a(4);
        } else {
            a(this.m, 0, 4);
        }
        return (bArr[i2 + 3] & 255) | ((bArr[i2] & 255) << 24) | ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2 + 2] & 255) << 8);
    }

    public long t() {
        byte[] bArr = this.n;
        int i2 = 0;
        if (this.e.c() >= 8) {
            bArr = this.e.a();
            i2 = this.e.b();
            this.e.a(8);
        } else {
            a(this.n, 0, 8);
        }
        return ((long) (bArr[i2 + 7] & 255)) | (((long) (bArr[i2] & 255)) << 56) | (((long) (bArr[i2 + 1] & 255)) << 48) | (((long) (bArr[i2 + 2] & 255)) << 40) | (((long) (bArr[i2 + 3] & 255)) << 32) | (((long) (bArr[i2 + 4] & 255)) << 24) | (((long) (bArr[i2 + 5] & 255)) << 16) | (((long) (bArr[i2 + 6] & 255)) << 8);
    }

    public double u() {
        return Double.longBitsToDouble(t());
    }

    public String v() {
        int s = s();
        if (this.e.c() < s) {
            return b(s);
        }
        try {
            String str = new String(this.e.a(), this.e.b(), s, "UTF-8");
            this.e.a(s);
            return str;
        } catch (UnsupportedEncodingException unused) {
            throw new je("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public ByteBuffer w() {
        int s = s();
        d(s);
        if (this.e.c() >= s) {
            ByteBuffer wrap = ByteBuffer.wrap(this.e.a(), this.e.b(), s);
            this.e.a(s);
            return wrap;
        }
        byte[] bArr = new byte[s];
        this.e.d(bArr, 0, s);
        return ByteBuffer.wrap(bArr);
    }
}
