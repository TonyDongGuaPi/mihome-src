package com.bumptech.glide.gifdecoder;

import android.graphics.Bitmap;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.bumptech.glide.gifdecoder.GifDecoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;

public class StandardGifDecoder implements GifDecoder {
    private static final String f = "StandardGifDecoder";
    private static final int g = 4096;
    private static final int h = -1;
    private static final int i = -1;
    private static final int j = 4;
    private static final int k = 255;
    @ColorInt
    private static final int l = 0;
    private boolean A;
    private int B;
    private int C;
    private int D;
    private int E;
    @Nullable
    private Boolean F;
    @NonNull
    private Bitmap.Config G;
    @ColorInt
    private int[] m;
    @ColorInt
    private final int[] n;
    private final GifDecoder.BitmapProvider o;
    private ByteBuffer p;
    private byte[] q;
    private GifHeaderParser r;
    private short[] s;
    private byte[] t;
    private byte[] u;
    private byte[] v;
    @ColorInt
    private int[] w;
    private int x;
    private GifHeader y;
    private Bitmap z;

    public StandardGifDecoder(@NonNull GifDecoder.BitmapProvider bitmapProvider, GifHeader gifHeader, ByteBuffer byteBuffer) {
        this(bitmapProvider, gifHeader, byteBuffer, 1);
    }

    public StandardGifDecoder(@NonNull GifDecoder.BitmapProvider bitmapProvider, GifHeader gifHeader, ByteBuffer byteBuffer, int i2) {
        this(bitmapProvider);
        a(gifHeader, byteBuffer, i2);
    }

    public StandardGifDecoder(@NonNull GifDecoder.BitmapProvider bitmapProvider) {
        this.n = new int[256];
        this.G = Bitmap.Config.ARGB_8888;
        this.o = bitmapProvider;
        this.y = new GifHeader();
    }

    public int a() {
        return this.y.h;
    }

    public int b() {
        return this.y.i;
    }

    @NonNull
    public ByteBuffer c() {
        return this.p;
    }

    public int d() {
        return this.B;
    }

    public void e() {
        this.x = (this.x + 1) % this.y.e;
    }

    public int a(int i2) {
        if (i2 < 0 || i2 >= this.y.e) {
            return -1;
        }
        return this.y.g.get(i2).m;
    }

    public int f() {
        if (this.y.e <= 0 || this.x < 0) {
            return 0;
        }
        return a(this.x);
    }

    public int g() {
        return this.y.e;
    }

    public int h() {
        return this.x;
    }

    public void i() {
        this.x = -1;
    }

    @Deprecated
    public int j() {
        if (this.y.o == -1) {
            return 1;
        }
        return this.y.o;
    }

    public int k() {
        return this.y.o;
    }

    public int l() {
        if (this.y.o == -1) {
            return 1;
        }
        if (this.y.o == 0) {
            return 0;
        }
        return this.y.o + 1;
    }

    public int m() {
        return this.p.limit() + this.v.length + (this.w.length * 4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00e9, code lost:
        return null;
     */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.graphics.Bitmap n() {
        /*
            r7 = this;
            monitor-enter(r7)
            com.bumptech.glide.gifdecoder.GifHeader r0 = r7.y     // Catch:{ all -> 0x00ea }
            int r0 = r0.e     // Catch:{ all -> 0x00ea }
            r1 = 3
            r2 = 1
            if (r0 <= 0) goto L_0x000d
            int r0 = r7.x     // Catch:{ all -> 0x00ea }
            if (r0 >= 0) goto L_0x003b
        L_0x000d:
            java.lang.String r0 = f     // Catch:{ all -> 0x00ea }
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00ea }
            if (r0 == 0) goto L_0x0039
            java.lang.String r0 = f     // Catch:{ all -> 0x00ea }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ea }
            r3.<init>()     // Catch:{ all -> 0x00ea }
            java.lang.String r4 = "Unable to decode frame, frameCount="
            r3.append(r4)     // Catch:{ all -> 0x00ea }
            com.bumptech.glide.gifdecoder.GifHeader r4 = r7.y     // Catch:{ all -> 0x00ea }
            int r4 = r4.e     // Catch:{ all -> 0x00ea }
            r3.append(r4)     // Catch:{ all -> 0x00ea }
            java.lang.String r4 = ", framePointer="
            r3.append(r4)     // Catch:{ all -> 0x00ea }
            int r4 = r7.x     // Catch:{ all -> 0x00ea }
            r3.append(r4)     // Catch:{ all -> 0x00ea }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00ea }
            android.util.Log.d(r0, r3)     // Catch:{ all -> 0x00ea }
        L_0x0039:
            r7.B = r2     // Catch:{ all -> 0x00ea }
        L_0x003b:
            int r0 = r7.B     // Catch:{ all -> 0x00ea }
            r3 = 0
            if (r0 == r2) goto L_0x00c8
            int r0 = r7.B     // Catch:{ all -> 0x00ea }
            r4 = 2
            if (r0 != r4) goto L_0x0047
            goto L_0x00c8
        L_0x0047:
            r0 = 0
            r7.B = r0     // Catch:{ all -> 0x00ea }
            byte[] r4 = r7.q     // Catch:{ all -> 0x00ea }
            if (r4 != 0) goto L_0x0058
            com.bumptech.glide.gifdecoder.GifDecoder$BitmapProvider r4 = r7.o     // Catch:{ all -> 0x00ea }
            r5 = 255(0xff, float:3.57E-43)
            byte[] r4 = r4.a((int) r5)     // Catch:{ all -> 0x00ea }
            r7.q = r4     // Catch:{ all -> 0x00ea }
        L_0x0058:
            com.bumptech.glide.gifdecoder.GifHeader r4 = r7.y     // Catch:{ all -> 0x00ea }
            java.util.List<com.bumptech.glide.gifdecoder.GifFrame> r4 = r4.g     // Catch:{ all -> 0x00ea }
            int r5 = r7.x     // Catch:{ all -> 0x00ea }
            java.lang.Object r4 = r4.get(r5)     // Catch:{ all -> 0x00ea }
            com.bumptech.glide.gifdecoder.GifFrame r4 = (com.bumptech.glide.gifdecoder.GifFrame) r4     // Catch:{ all -> 0x00ea }
            int r5 = r7.x     // Catch:{ all -> 0x00ea }
            int r5 = r5 - r2
            if (r5 < 0) goto L_0x0074
            com.bumptech.glide.gifdecoder.GifHeader r6 = r7.y     // Catch:{ all -> 0x00ea }
            java.util.List<com.bumptech.glide.gifdecoder.GifFrame> r6 = r6.g     // Catch:{ all -> 0x00ea }
            java.lang.Object r5 = r6.get(r5)     // Catch:{ all -> 0x00ea }
            com.bumptech.glide.gifdecoder.GifFrame r5 = (com.bumptech.glide.gifdecoder.GifFrame) r5     // Catch:{ all -> 0x00ea }
            goto L_0x0075
        L_0x0074:
            r5 = r3
        L_0x0075:
            int[] r6 = r4.o     // Catch:{ all -> 0x00ea }
            if (r6 == 0) goto L_0x007c
            int[] r6 = r4.o     // Catch:{ all -> 0x00ea }
            goto L_0x0080
        L_0x007c:
            com.bumptech.glide.gifdecoder.GifHeader r6 = r7.y     // Catch:{ all -> 0x00ea }
            int[] r6 = r6.c     // Catch:{ all -> 0x00ea }
        L_0x0080:
            r7.m = r6     // Catch:{ all -> 0x00ea }
            int[] r6 = r7.m     // Catch:{ all -> 0x00ea }
            if (r6 != 0) goto L_0x00aa
            java.lang.String r0 = f     // Catch:{ all -> 0x00ea }
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00ea }
            if (r0 == 0) goto L_0x00a6
            java.lang.String r0 = f     // Catch:{ all -> 0x00ea }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ea }
            r1.<init>()     // Catch:{ all -> 0x00ea }
            java.lang.String r4 = "No valid color table found for frame #"
            r1.append(r4)     // Catch:{ all -> 0x00ea }
            int r4 = r7.x     // Catch:{ all -> 0x00ea }
            r1.append(r4)     // Catch:{ all -> 0x00ea }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00ea }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x00ea }
        L_0x00a6:
            r7.B = r2     // Catch:{ all -> 0x00ea }
            monitor-exit(r7)
            return r3
        L_0x00aa:
            boolean r1 = r4.j     // Catch:{ all -> 0x00ea }
            if (r1 == 0) goto L_0x00c2
            int[] r1 = r7.m     // Catch:{ all -> 0x00ea }
            int[] r2 = r7.n     // Catch:{ all -> 0x00ea }
            int[] r3 = r7.m     // Catch:{ all -> 0x00ea }
            int r3 = r3.length     // Catch:{ all -> 0x00ea }
            java.lang.System.arraycopy(r1, r0, r2, r0, r3)     // Catch:{ all -> 0x00ea }
            int[] r1 = r7.n     // Catch:{ all -> 0x00ea }
            r7.m = r1     // Catch:{ all -> 0x00ea }
            int[] r1 = r7.m     // Catch:{ all -> 0x00ea }
            int r2 = r4.l     // Catch:{ all -> 0x00ea }
            r1[r2] = r0     // Catch:{ all -> 0x00ea }
        L_0x00c2:
            android.graphics.Bitmap r0 = r7.a((com.bumptech.glide.gifdecoder.GifFrame) r4, (com.bumptech.glide.gifdecoder.GifFrame) r5)     // Catch:{ all -> 0x00ea }
            monitor-exit(r7)
            return r0
        L_0x00c8:
            java.lang.String r0 = f     // Catch:{ all -> 0x00ea }
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00ea }
            if (r0 == 0) goto L_0x00e8
            java.lang.String r0 = f     // Catch:{ all -> 0x00ea }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ea }
            r1.<init>()     // Catch:{ all -> 0x00ea }
            java.lang.String r2 = "Unable to decode frame, status="
            r1.append(r2)     // Catch:{ all -> 0x00ea }
            int r2 = r7.B     // Catch:{ all -> 0x00ea }
            r1.append(r2)     // Catch:{ all -> 0x00ea }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00ea }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x00ea }
        L_0x00e8:
            monitor-exit(r7)
            return r3
        L_0x00ea:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.StandardGifDecoder.n():android.graphics.Bitmap");
    }

    public int a(@Nullable InputStream inputStream, int i2) {
        if (inputStream != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i2 > 0 ? i2 + 4096 : 16384);
                byte[] bArr = new byte[16384];
                while (true) {
                    int read = inputStream.read(bArr, 0, bArr.length);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.flush();
                a(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                Log.w(f, "Error reading data from stream", e);
            }
        } else {
            this.B = 2;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                Log.w(f, "Error closing stream", e2);
            }
        }
        return this.B;
    }

    public void o() {
        this.y = null;
        if (this.v != null) {
            this.o.a(this.v);
        }
        if (this.w != null) {
            this.o.a(this.w);
        }
        if (this.z != null) {
            this.o.a(this.z);
        }
        this.z = null;
        this.p = null;
        this.F = null;
        if (this.q != null) {
            this.o.a(this.q);
        }
    }

    public synchronized void a(@NonNull GifHeader gifHeader, @NonNull byte[] bArr) {
        a(gifHeader, ByteBuffer.wrap(bArr));
    }

    public synchronized void a(@NonNull GifHeader gifHeader, @NonNull ByteBuffer byteBuffer) {
        a(gifHeader, byteBuffer, 1);
    }

    public synchronized void a(@NonNull GifHeader gifHeader, @NonNull ByteBuffer byteBuffer, int i2) {
        if (i2 > 0) {
            int highestOneBit = Integer.highestOneBit(i2);
            this.B = 0;
            this.y = gifHeader;
            this.x = -1;
            this.p = byteBuffer.asReadOnlyBuffer();
            this.p.position(0);
            this.p.order(ByteOrder.LITTLE_ENDIAN);
            this.A = false;
            Iterator<GifFrame> it = gifHeader.g.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().k == 3) {
                        this.A = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            this.C = highestOneBit;
            this.E = gifHeader.h / highestOneBit;
            this.D = gifHeader.i / highestOneBit;
            this.v = this.o.a(gifHeader.h * gifHeader.i);
            this.w = this.o.b(this.E * this.D);
        } else {
            throw new IllegalArgumentException("Sample size must be >=0, not: " + i2);
        }
    }

    @NonNull
    private GifHeaderParser p() {
        if (this.r == null) {
            this.r = new GifHeaderParser();
        }
        return this.r;
    }

    public synchronized int a(@Nullable byte[] bArr) {
        this.y = p().a(bArr).b();
        if (bArr != null) {
            a(this.y, bArr);
        }
        return this.B;
    }

    public void a(@NonNull Bitmap.Config config) {
        if (config == Bitmap.Config.ARGB_8888 || config == Bitmap.Config.RGB_565) {
            this.G = config;
            return;
        }
        throw new IllegalArgumentException("Unsupported format: " + config + ", must be one of " + Bitmap.Config.ARGB_8888 + " or " + Bitmap.Config.RGB_565);
    }

    private Bitmap a(GifFrame gifFrame, GifFrame gifFrame2) {
        int[] iArr = this.w;
        int i2 = 0;
        if (gifFrame2 == null) {
            if (this.z != null) {
                this.o.a(this.z);
            }
            this.z = null;
            Arrays.fill(iArr, 0);
        }
        if (gifFrame2 != null && gifFrame2.k == 3 && this.z == null) {
            Arrays.fill(iArr, 0);
        }
        if (gifFrame2 != null && gifFrame2.k > 0) {
            if (gifFrame2.k == 2) {
                if (!gifFrame.j) {
                    int i3 = this.y.n;
                    if (gifFrame.o == null || this.y.l != gifFrame.l) {
                        i2 = i3;
                    }
                } else if (this.x == 0) {
                    this.F = true;
                }
                int i4 = gifFrame2.h / this.C;
                int i5 = gifFrame2.f / this.C;
                int i6 = gifFrame2.g / this.C;
                int i7 = (i5 * this.E) + (gifFrame2.e / this.C);
                int i8 = (i4 * this.E) + i7;
                while (i7 < i8) {
                    int i9 = i7 + i6;
                    for (int i10 = i7; i10 < i9; i10++) {
                        iArr[i10] = i2;
                    }
                    i7 += this.E;
                }
            } else if (gifFrame2.k == 3 && this.z != null) {
                this.z.getPixels(iArr, 0, this.E, 0, 0, this.E, this.D);
            }
        }
        c(gifFrame);
        if (gifFrame.i || this.C != 1) {
            b(gifFrame);
        } else {
            a(gifFrame);
        }
        if (this.A && (gifFrame.k == 0 || gifFrame.k == 1)) {
            if (this.z == null) {
                this.z = s();
            }
            this.z.setPixels(iArr, 0, this.E, 0, 0, this.E, this.D);
        }
        Bitmap s2 = s();
        s2.setPixels(iArr, 0, this.E, 0, 0, this.E, this.D);
        return s2;
    }

    private void a(GifFrame gifFrame) {
        GifFrame gifFrame2 = gifFrame;
        int[] iArr = this.w;
        int i2 = gifFrame2.h;
        int i3 = gifFrame2.f;
        int i4 = gifFrame2.g;
        int i5 = gifFrame2.e;
        boolean z2 = this.x == 0;
        int i6 = this.E;
        byte[] bArr = this.v;
        int[] iArr2 = this.m;
        int i7 = 0;
        byte b = -1;
        while (i7 < i2) {
            int i8 = (i7 + i3) * i6;
            int i9 = i8 + i5;
            int i10 = i9 + i4;
            int i11 = i8 + i6;
            if (i11 < i10) {
                i10 = i11;
            }
            byte b2 = b;
            int i12 = gifFrame2.g * i7;
            int i13 = i9;
            while (i13 < i10) {
                byte b3 = bArr[i12];
                int i14 = i2;
                byte b4 = b3 & 255;
                if (b4 != b2) {
                    int i15 = iArr2[b4];
                    if (i15 != 0) {
                        iArr[i13] = i15;
                    } else {
                        b2 = b3;
                    }
                }
                i12++;
                i13++;
                i2 = i14;
                GifFrame gifFrame3 = gifFrame;
            }
            int i16 = i2;
            i7++;
            b = b2;
            gifFrame2 = gifFrame;
        }
        this.F = Boolean.valueOf(this.F == null && z2 && b != -1);
    }

    private void b(GifFrame gifFrame) {
        boolean z2;
        int i2;
        int i3;
        int i4;
        GifFrame gifFrame2 = gifFrame;
        int[] iArr = this.w;
        int i5 = gifFrame2.h / this.C;
        int i6 = gifFrame2.f / this.C;
        int i7 = gifFrame2.g / this.C;
        int i8 = gifFrame2.e / this.C;
        boolean z3 = this.x == 0;
        int i9 = this.C;
        int i10 = this.E;
        int i11 = this.D;
        byte[] bArr = this.v;
        int[] iArr2 = this.m;
        Boolean bool = this.F;
        int i12 = 0;
        int i13 = 0;
        int i14 = 1;
        int i15 = 8;
        while (i13 < i5) {
            if (gifFrame2.i) {
                if (i12 >= i5) {
                    i14++;
                    switch (i14) {
                        case 2:
                            i12 = 4;
                            break;
                        case 3:
                            i12 = 2;
                            i15 = 4;
                            break;
                        case 4:
                            i12 = 1;
                            i15 = 2;
                            break;
                    }
                }
                i2 = i12 + i15;
            } else {
                i2 = i12;
                i12 = i13;
            }
            int i16 = i12 + i6;
            int i17 = i5;
            boolean z4 = i9 == 1;
            if (i16 < i11) {
                int i18 = i16 * i10;
                int i19 = i18 + i8;
                i4 = i6;
                int i20 = i19 + i7;
                int i21 = i18 + i10;
                if (i21 < i20) {
                    i20 = i21;
                }
                i3 = i7;
                int i22 = i13 * i9 * gifFrame2.g;
                if (z4) {
                    for (int i23 = i19; i23 < i20; i23++) {
                        int i24 = iArr2[bArr[i22] & 255];
                        if (i24 != 0) {
                            iArr[i23] = i24;
                        } else if (z3 && bool == null) {
                            bool = true;
                        }
                        i22 += i9;
                    }
                } else {
                    int i25 = ((i20 - i19) * i9) + i22;
                    int i26 = i19;
                    while (i26 < i20) {
                        int i27 = i20;
                        int a2 = a(i22, i25, gifFrame2.g);
                        if (a2 != 0) {
                            iArr[i26] = a2;
                        } else if (z3 && bool == null) {
                            bool = true;
                            i22 += i9;
                            i26++;
                            i20 = i27;
                        }
                        i22 += i9;
                        i26++;
                        i20 = i27;
                    }
                }
            } else {
                i4 = i6;
                i3 = i7;
            }
            i13++;
            i12 = i2;
            i5 = i17;
            i6 = i4;
            i7 = i3;
        }
        if (this.F == null) {
            if (bool == null) {
                z2 = false;
            } else {
                z2 = bool.booleanValue();
            }
            this.F = Boolean.valueOf(z2);
        }
    }

    @ColorInt
    private int a(int i2, int i3, int i4) {
        int i5 = i2;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (i5 < this.C + i2 && i5 < this.v.length && i5 < i3) {
            int i11 = this.m[this.v[i5] & 255];
            if (i11 != 0) {
                i6 += (i11 >> 24) & 255;
                i7 += (i11 >> 16) & 255;
                i8 += (i11 >> 8) & 255;
                i9 += i11 & 255;
                i10++;
            }
            i5++;
        }
        int i12 = i2 + i4;
        int i13 = i12;
        while (i13 < this.C + i12 && i13 < this.v.length && i13 < i3) {
            int i14 = this.m[this.v[i13] & 255];
            if (i14 != 0) {
                i6 += (i14 >> 24) & 255;
                i7 += (i14 >> 16) & 255;
                i8 += (i14 >> 8) & 255;
                i9 += i14 & 255;
                i10++;
            }
            i13++;
        }
        if (i10 == 0) {
            return 0;
        }
        return ((i6 / i10) << 24) | ((i7 / i10) << 16) | ((i8 / i10) << 8) | (i9 / i10);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v7, resolved type: byte} */
    /* JADX WARNING: Incorrect type for immutable var: ssa=short, code=int, for r4v15, types: [short] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(com.bumptech.glide.gifdecoder.GifFrame r33) {
        /*
            r32 = this;
            r0 = r32
            r1 = r33
            if (r1 == 0) goto L_0x000d
            java.nio.ByteBuffer r2 = r0.p
            int r3 = r1.n
            r2.position(r3)
        L_0x000d:
            if (r1 != 0) goto L_0x001a
            com.bumptech.glide.gifdecoder.GifHeader r1 = r0.y
            int r1 = r1.h
            com.bumptech.glide.gifdecoder.GifHeader r2 = r0.y
            int r2 = r2.i
            int r1 = r1 * r2
            goto L_0x0020
        L_0x001a:
            int r2 = r1.g
            int r1 = r1.h
            int r1 = r1 * r2
        L_0x0020:
            byte[] r2 = r0.v
            if (r2 == 0) goto L_0x0029
            byte[] r2 = r0.v
            int r2 = r2.length
            if (r2 >= r1) goto L_0x0031
        L_0x0029:
            com.bumptech.glide.gifdecoder.GifDecoder$BitmapProvider r2 = r0.o
            byte[] r2 = r2.a((int) r1)
            r0.v = r2
        L_0x0031:
            byte[] r2 = r0.v
            short[] r3 = r0.s
            r4 = 4096(0x1000, float:5.74E-42)
            if (r3 != 0) goto L_0x003d
            short[] r3 = new short[r4]
            r0.s = r3
        L_0x003d:
            short[] r3 = r0.s
            byte[] r5 = r0.t
            if (r5 != 0) goto L_0x0047
            byte[] r5 = new byte[r4]
            r0.t = r5
        L_0x0047:
            byte[] r5 = r0.t
            byte[] r6 = r0.u
            if (r6 != 0) goto L_0x0053
            r6 = 4097(0x1001, float:5.741E-42)
            byte[] r6 = new byte[r6]
            r0.u = r6
        L_0x0053:
            byte[] r6 = r0.u
            int r7 = r32.q()
            r8 = 1
            int r9 = r8 << r7
            int r10 = r9 + 1
            int r11 = r9 + 2
            int r7 = r7 + r8
            int r12 = r8 << r7
            int r12 = r12 - r8
            r13 = 0
            r14 = 0
        L_0x0066:
            if (r14 >= r9) goto L_0x0070
            r3[r14] = r13
            byte r15 = (byte) r14
            r5[r14] = r15
            int r14 = r14 + 1
            goto L_0x0066
        L_0x0070:
            byte[] r14 = r0.q
            r15 = -1
            r26 = r7
            r24 = r11
            r25 = r12
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = -1
            r22 = 0
            r23 = 0
        L_0x0089:
            if (r13 >= r1) goto L_0x015e
            if (r16 != 0) goto L_0x009a
            int r16 = r32.r()
            if (r16 > 0) goto L_0x0098
            r3 = 3
            r0.B = r3
            goto L_0x015e
        L_0x0098:
            r20 = 0
        L_0x009a:
            byte r4 = r14[r20]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << r18
            int r19 = r19 + r4
            int r18 = r18 + 8
            int r20 = r20 + 1
            int r16 = r16 + -1
            r4 = r18
            r8 = r21
            r28 = r22
            r27 = r24
            r18 = r17
            r17 = r13
            r13 = r26
        L_0x00b6:
            if (r4 < r13) goto L_0x0145
            r15 = r19 & r25
            int r19 = r19 >> r13
            int r4 = r4 - r13
            if (r15 != r9) goto L_0x00c7
            r13 = r7
            r27 = r11
            r25 = r12
            r8 = -1
        L_0x00c5:
            r15 = -1
            goto L_0x00b6
        L_0x00c7:
            if (r15 != r10) goto L_0x00dc
            r21 = r8
            r26 = r13
            r13 = r17
            r17 = r18
            r24 = r27
            r22 = r28
            r8 = 1
            r15 = -1
            r18 = r4
            r4 = 4096(0x1000, float:5.74E-42)
            goto L_0x0089
        L_0x00dc:
            r0 = -1
            if (r8 != r0) goto L_0x00ed
            byte r8 = r5[r15]
            r2[r18] = r8
            int r18 = r18 + 1
            int r17 = r17 + 1
            r8 = r15
            r28 = r8
        L_0x00ea:
            r0 = r32
            goto L_0x00c5
        L_0x00ed:
            r0 = r27
            if (r15 < r0) goto L_0x00fc
            r29 = r4
            r4 = r28
            byte r4 = (byte) r4
            r6[r23] = r4
            int r23 = r23 + 1
            r4 = r8
            goto L_0x00ff
        L_0x00fc:
            r29 = r4
            r4 = r15
        L_0x00ff:
            if (r4 < r9) goto L_0x010a
            byte r21 = r5[r4]
            r6[r23] = r21
            int r23 = r23 + 1
            short r4 = r3[r4]
            goto L_0x00ff
        L_0x010a:
            byte r4 = r5[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            r30 = r7
            byte r7 = (byte) r4
            r2[r18] = r7
            int r18 = r18 + 1
            int r17 = r17 + 1
        L_0x0117:
            if (r23 <= 0) goto L_0x0124
            int r23 = r23 + -1
            byte r21 = r6[r23]
            r2[r18] = r21
            int r18 = r18 + 1
            int r17 = r17 + 1
            goto L_0x0117
        L_0x0124:
            r31 = r4
            r4 = 4096(0x1000, float:5.74E-42)
            if (r0 >= r4) goto L_0x013b
            short r8 = (short) r8
            r3[r0] = r8
            r5[r0] = r7
            int r0 = r0 + 1
            r7 = r0 & r25
            if (r7 != 0) goto L_0x013b
            if (r0 >= r4) goto L_0x013b
            int r13 = r13 + 1
            int r25 = r25 + r0
        L_0x013b:
            r27 = r0
            r8 = r15
            r4 = r29
            r7 = r30
            r28 = r31
            goto L_0x00ea
        L_0x0145:
            r29 = r4
            r0 = r27
            r24 = r0
            r21 = r8
            r26 = r13
            r13 = r17
            r17 = r18
            r22 = r28
            r18 = r29
            r0 = r32
            r4 = 4096(0x1000, float:5.74E-42)
            r8 = 1
            goto L_0x0089
        L_0x015e:
            r13 = r17
            r0 = 0
            java.util.Arrays.fill(r2, r13, r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.StandardGifDecoder.c(com.bumptech.glide.gifdecoder.GifFrame):void");
    }

    private int q() {
        return this.p.get() & 255;
    }

    private int r() {
        int q2 = q();
        if (q2 <= 0) {
            return q2;
        }
        this.p.get(this.q, 0, Math.min(q2, this.p.remaining()));
        return q2;
    }

    private Bitmap s() {
        Bitmap a2 = this.o.a(this.E, this.D, (this.F == null || this.F.booleanValue()) ? Bitmap.Config.ARGB_8888 : this.G);
        a2.setHasAlpha(true);
        return a2;
    }
}
