package com.bumptech.glide.gifdecoder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class GifHeaderParser {

    /* renamed from: a  reason: collision with root package name */
    static final int f4832a = 2;
    static final int b = 10;
    private static final String c = "GifHeaderParser";
    private static final int d = 255;
    private static final int e = 44;
    private static final int f = 33;
    private static final int g = 59;
    private static final int h = 249;
    private static final int i = 255;
    private static final int j = 254;
    private static final int k = 1;
    private static final int l = 28;
    private static final int m = 2;
    private static final int n = 1;
    private static final int o = 128;
    private static final int p = 64;
    private static final int q = 7;
    private static final int r = 128;
    private static final int s = 7;
    private static final int t = 256;
    private final byte[] u = new byte[256];
    private ByteBuffer v;
    private GifHeader w;
    private int x = 0;

    public GifHeaderParser a(@NonNull ByteBuffer byteBuffer) {
        d();
        this.v = byteBuffer.asReadOnlyBuffer();
        this.v.position(0);
        this.v.order(ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public GifHeaderParser a(@Nullable byte[] bArr) {
        if (bArr != null) {
            a(ByteBuffer.wrap(bArr));
        } else {
            this.v = null;
            this.w.d = 2;
        }
        return this;
    }

    public void a() {
        this.v = null;
        this.w = null;
    }

    private void d() {
        this.v = null;
        Arrays.fill(this.u, (byte) 0);
        this.w = new GifHeader();
        this.x = 0;
    }

    @NonNull
    public GifHeader b() {
        if (this.v == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        } else if (p()) {
            return this.w;
        } else {
            i();
            if (!p()) {
                e();
                if (this.w.e < 0) {
                    this.w.d = 1;
                }
            }
            return this.w;
        }
    }

    public boolean c() {
        i();
        if (!p()) {
            a(2);
        }
        return this.w.e > 1;
    }

    private void e() {
        a(Integer.MAX_VALUE);
    }

    private void a(int i2) {
        boolean z = false;
        while (!z && !p() && this.w.e <= i2) {
            int n2 = n();
            if (n2 == 33) {
                int n3 = n();
                if (n3 == 1) {
                    l();
                } else if (n3 != h) {
                    switch (n3) {
                        case 254:
                            l();
                            break;
                        case 255:
                            m();
                            StringBuilder sb = new StringBuilder();
                            for (int i3 = 0; i3 < 11; i3++) {
                                sb.append((char) this.u[i3]);
                            }
                            if (!sb.toString().equals("NETSCAPE2.0")) {
                                l();
                                break;
                            } else {
                                h();
                                break;
                            }
                        default:
                            l();
                            break;
                    }
                } else {
                    this.w.f = new GifFrame();
                    f();
                }
            } else if (n2 == 44) {
                if (this.w.f == null) {
                    this.w.f = new GifFrame();
                }
                g();
            } else if (n2 != 59) {
                this.w.d = 1;
            } else {
                z = true;
            }
        }
    }

    private void f() {
        n();
        int n2 = n();
        this.w.f.k = (n2 & 28) >> 2;
        boolean z = true;
        if (this.w.f.k == 0) {
            this.w.f.k = 1;
        }
        GifFrame gifFrame = this.w.f;
        if ((n2 & 1) == 0) {
            z = false;
        }
        gifFrame.j = z;
        int o2 = o();
        if (o2 < 2) {
            o2 = 10;
        }
        this.w.f.m = o2 * 10;
        this.w.f.l = n();
        n();
    }

    private void g() {
        this.w.f.e = o();
        this.w.f.f = o();
        this.w.f.g = o();
        this.w.f.h = o();
        int n2 = n();
        boolean z = false;
        boolean z2 = (n2 & 128) != 0;
        int pow = (int) Math.pow(2.0d, (double) ((n2 & 7) + 1));
        GifFrame gifFrame = this.w.f;
        if ((n2 & 64) != 0) {
            z = true;
        }
        gifFrame.i = z;
        if (z2) {
            this.w.f.o = b(pow);
        } else {
            this.w.f.o = null;
        }
        this.w.f.n = this.v.position();
        k();
        if (!p()) {
            this.w.e++;
            this.w.g.add(this.w.f);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void h() {
        /*
            r3 = this;
        L_0x0000:
            r3.m()
            byte[] r0 = r3.u
            r1 = 0
            byte r0 = r0[r1]
            r1 = 1
            if (r0 != r1) goto L_0x001f
            byte[] r0 = r3.u
            byte r0 = r0[r1]
            r0 = r0 & 255(0xff, float:3.57E-43)
            byte[] r1 = r3.u
            r2 = 2
            byte r1 = r1[r2]
            r1 = r1 & 255(0xff, float:3.57E-43)
            com.bumptech.glide.gifdecoder.GifHeader r2 = r3.w
            int r1 = r1 << 8
            r0 = r0 | r1
            r2.o = r0
        L_0x001f:
            int r0 = r3.x
            if (r0 <= 0) goto L_0x0029
            boolean r0 = r3.p()
            if (r0 == 0) goto L_0x0000
        L_0x0029:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.GifHeaderParser.h():void");
    }

    private void i() {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < 6; i2++) {
            sb.append((char) n());
        }
        if (!sb.toString().startsWith("GIF")) {
            this.w.d = 1;
            return;
        }
        j();
        if (this.w.j && !p()) {
            this.w.c = b(this.w.k);
            this.w.n = this.w.c[this.w.l];
        }
    }

    private void j() {
        this.w.h = o();
        this.w.i = o();
        int n2 = n();
        this.w.j = (n2 & 128) != 0;
        this.w.k = (int) Math.pow(2.0d, (double) ((n2 & 7) + 1));
        this.w.l = n();
        this.w.m = n();
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0041  */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int[] b(int r10) {
        /*
            r9 = this;
            int r0 = r10 * 3
            byte[] r0 = new byte[r0]
            r1 = 0
            java.nio.ByteBuffer r2 = r9.v     // Catch:{ BufferUnderflowException -> 0x0036 }
            r2.get(r0)     // Catch:{ BufferUnderflowException -> 0x0036 }
            r2 = 256(0x100, float:3.59E-43)
            int[] r2 = new int[r2]     // Catch:{ BufferUnderflowException -> 0x0036 }
            r1 = 0
            r3 = 0
        L_0x0010:
            if (r1 >= r10) goto L_0x004d
            int r4 = r3 + 1
            byte r3 = r0[r3]     // Catch:{ BufferUnderflowException -> 0x0034 }
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r5 = r4 + 1
            byte r4 = r0[r4]     // Catch:{ BufferUnderflowException -> 0x0034 }
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r6 = r5 + 1
            byte r5 = r0[r5]     // Catch:{ BufferUnderflowException -> 0x0034 }
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r7 = r1 + 1
            r8 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            int r3 = r3 << 16
            r3 = r3 | r8
            int r4 = r4 << 8
            r3 = r3 | r4
            r3 = r3 | r5
            r2[r1] = r3     // Catch:{ BufferUnderflowException -> 0x0034 }
            r3 = r6
            r1 = r7
            goto L_0x0010
        L_0x0034:
            r10 = move-exception
            goto L_0x0038
        L_0x0036:
            r10 = move-exception
            r2 = r1
        L_0x0038:
            java.lang.String r0 = "GifHeaderParser"
            r1 = 3
            boolean r0 = android.util.Log.isLoggable(r0, r1)
            if (r0 == 0) goto L_0x0048
            java.lang.String r0 = "GifHeaderParser"
            java.lang.String r1 = "Format Error Reading Color Table"
            android.util.Log.d(r0, r1, r10)
        L_0x0048:
            com.bumptech.glide.gifdecoder.GifHeader r10 = r9.w
            r0 = 1
            r10.d = r0
        L_0x004d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.GifHeaderParser.b(int):int[]");
    }

    private void k() {
        n();
        l();
    }

    private void l() {
        int n2;
        do {
            n2 = n();
            this.v.position(Math.min(this.v.position() + n2, this.v.limit()));
        } while (n2 > 0);
    }

    private void m() {
        this.x = n();
        if (this.x > 0) {
            int i2 = 0;
            int i3 = 0;
            while (i2 < this.x) {
                try {
                    i3 = this.x - i2;
                    this.v.get(this.u, i2, i3);
                    i2 += i3;
                } catch (Exception e2) {
                    if (Log.isLoggable(c, 3)) {
                        Log.d(c, "Error Reading Block n: " + i2 + " count: " + i3 + " blockSize: " + this.x, e2);
                    }
                    this.w.d = 1;
                    return;
                }
            }
        }
    }

    private int n() {
        try {
            return this.v.get() & 255;
        } catch (Exception unused) {
            this.w.d = 1;
            return 0;
        }
    }

    private int o() {
        return this.v.getShort();
    }

    private boolean p() {
        return this.w.d != 0;
    }
}
