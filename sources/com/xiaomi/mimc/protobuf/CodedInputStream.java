package com.xiaomi.mimc.protobuf;

import com.xiaomi.mimc.protobuf.MessageLite;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public final class CodedInputStream {
    private static final int n = 100;
    private static final int o = 67108864;
    private static final int p = 4096;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f11301a;
    private final boolean b;
    private int c;
    private int d;
    /* access modifiers changed from: private */
    public int e;
    private final InputStream f;
    private int g;
    private boolean h = false;
    private int i;
    private int j = Integer.MAX_VALUE;
    private int k;
    private int l = 100;
    private int m = 67108864;
    private RefillCallback q = null;

    private interface RefillCallback {
        void a();
    }

    public static long a(long j2) {
        return (-(j2 & 1)) ^ (j2 >>> 1);
    }

    public static int c(int i2) {
        return (-(i2 & 1)) ^ (i2 >>> 1);
    }

    public static CodedInputStream a(InputStream inputStream) {
        return new CodedInputStream(inputStream, 4096);
    }

    static CodedInputStream a(InputStream inputStream, int i2) {
        return new CodedInputStream(inputStream, i2);
    }

    public static CodedInputStream a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public static CodedInputStream a(byte[] bArr, int i2, int i3) {
        return a(bArr, i2, i3, false);
    }

    static CodedInputStream a(byte[] bArr, int i2, int i3, boolean z) {
        CodedInputStream codedInputStream = new CodedInputStream(bArr, i2, i3, z);
        try {
            codedInputStream.f(i3);
            return codedInputStream;
        } catch (InvalidProtocolBufferException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static CodedInputStream a(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return a(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
        }
        ByteBuffer duplicate = byteBuffer.duplicate();
        byte[] bArr = new byte[duplicate.remaining()];
        duplicate.get(bArr);
        return a(bArr);
    }

    public int a() throws IOException {
        if (D()) {
            this.g = 0;
            return 0;
        }
        this.g = w();
        if (WireFormat.b(this.g) != 0) {
            return this.g;
        }
        throw InvalidProtocolBufferException.invalidTag();
    }

    public void a(int i2) throws InvalidProtocolBufferException {
        if (this.g != i2) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
    }

    public int b() {
        return this.g;
    }

    public boolean b(int i2) throws IOException {
        switch (WireFormat.a(i2)) {
            case 0:
                G();
                return true;
            case 1:
                i(8);
                return true;
            case 2:
                i(w());
                return true;
            case 3:
                c();
                a(WireFormat.a(WireFormat.b(i2), 4));
                return true;
            case 4:
                return false;
            case 5:
                i(4);
                return true;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    public boolean a(int i2, CodedOutputStream codedOutputStream) throws IOException {
        switch (WireFormat.a(i2)) {
            case 0:
                long g2 = g();
                codedOutputStream.r(i2);
                codedOutputStream.b(g2);
                return true;
            case 1:
                long A = A();
                codedOutputStream.r(i2);
                codedOutputStream.d(A);
                return true;
            case 2:
                ByteString n2 = n();
                codedOutputStream.r(i2);
                codedOutputStream.b(n2);
                return true;
            case 3:
                codedOutputStream.r(i2);
                a(codedOutputStream);
                int a2 = WireFormat.a(WireFormat.b(i2), 4);
                a(a2);
                codedOutputStream.r(a2);
                return true;
            case 4:
                return false;
            case 5:
                int z = z();
                codedOutputStream.r(i2);
                codedOutputStream.f(z);
                return true;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public void c() throws java.io.IOException {
        /*
            r1 = this;
        L_0x0000:
            int r0 = r1.a()
            if (r0 == 0) goto L_0x000c
            boolean r0 = r1.b((int) r0)
            if (r0 != 0) goto L_0x0000
        L_0x000c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.protobuf.CodedInputStream.c():void");
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public void a(com.xiaomi.mimc.protobuf.CodedOutputStream r2) throws java.io.IOException {
        /*
            r1 = this;
        L_0x0000:
            int r0 = r1.a()
            if (r0 == 0) goto L_0x000c
            boolean r0 = r1.a((int) r0, (com.xiaomi.mimc.protobuf.CodedOutputStream) r2)
            if (r0 != 0) goto L_0x0000
        L_0x000c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.protobuf.CodedInputStream.a(com.xiaomi.mimc.protobuf.CodedOutputStream):void");
    }

    private class SkippedDataSink implements RefillCallback {
        private int b = CodedInputStream.this.e;
        private ByteArrayOutputStream c;

        private SkippedDataSink() {
        }

        public void a() {
            if (this.c == null) {
                this.c = new ByteArrayOutputStream();
            }
            this.c.write(CodedInputStream.this.f11301a, this.b, CodedInputStream.this.e - this.b);
            this.b = 0;
        }

        /* access modifiers changed from: package-private */
        public ByteBuffer b() {
            if (this.c == null) {
                return ByteBuffer.wrap(CodedInputStream.this.f11301a, this.b, CodedInputStream.this.e - this.b);
            }
            this.c.write(CodedInputStream.this.f11301a, this.b, CodedInputStream.this.e);
            return ByteBuffer.wrap(this.c.toByteArray());
        }
    }

    public double d() throws IOException {
        return Double.longBitsToDouble(A());
    }

    public float e() throws IOException {
        return Float.intBitsToFloat(z());
    }

    public long f() throws IOException {
        return x();
    }

    public long g() throws IOException {
        return x();
    }

    public int h() throws IOException {
        return w();
    }

    public long i() throws IOException {
        return A();
    }

    public int j() throws IOException {
        return z();
    }

    public boolean k() throws IOException {
        return x() != 0;
    }

    public String l() throws IOException {
        int w = w();
        if (w <= this.c - this.e && w > 0) {
            String str = new String(this.f11301a, this.e, w, Internal.f11319a);
            this.e += w;
            return str;
        } else if (w == 0) {
            return "";
        } else {
            if (w > this.c) {
                return new String(l(w), Internal.f11319a);
            }
            j(w);
            String str2 = new String(this.f11301a, this.e, w, Internal.f11319a);
            this.e += w;
            return str2;
        }
    }

    public String m() throws IOException {
        byte[] bArr;
        int w = w();
        int i2 = this.e;
        int i3 = 0;
        if (w <= this.c - i2 && w > 0) {
            bArr = this.f11301a;
            this.e = i2 + w;
            i3 = i2;
        } else if (w == 0) {
            return "";
        } else {
            if (w <= this.c) {
                j(w);
                bArr = this.f11301a;
                this.e = w + 0;
            } else {
                bArr = l(w);
            }
        }
        if (Utf8.a(bArr, i3, i3 + w)) {
            return new String(bArr, i3, w, Internal.f11319a);
        }
        throw InvalidProtocolBufferException.invalidUtf8();
    }

    public void a(int i2, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        if (this.k < this.l) {
            this.k++;
            builder.b(this, extensionRegistryLite);
            a(WireFormat.a(i2, 4));
            this.k--;
            return;
        }
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    public <T extends MessageLite> T a(int i2, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        if (this.k < this.l) {
            this.k++;
            T t = (MessageLite) parser.d(this, extensionRegistryLite);
            a(WireFormat.a(i2, 4));
            this.k--;
            return t;
        }
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    @Deprecated
    public void a(int i2, MessageLite.Builder builder) throws IOException {
        a(i2, builder, (ExtensionRegistryLite) null);
    }

    public void a(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        int w = w();
        if (this.k < this.l) {
            int f2 = f(w);
            this.k++;
            builder.b(this, extensionRegistryLite);
            a(0);
            this.k--;
            g(f2);
            return;
        }
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    public <T extends MessageLite> T a(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        int w = w();
        if (this.k < this.l) {
            int f2 = f(w);
            this.k++;
            T t = (MessageLite) parser.d(this, extensionRegistryLite);
            a(0);
            this.k--;
            g(f2);
            return t;
        }
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    public ByteString n() throws IOException {
        ByteString byteString;
        int w = w();
        if (w <= this.c - this.e && w > 0) {
            if (!this.b || !this.h) {
                byteString = ByteString.copyFrom(this.f11301a, this.e, w);
            } else {
                byteString = ByteString.wrap(this.f11301a, this.e, w);
            }
            this.e += w;
            return byteString;
        } else if (w == 0) {
            return ByteString.EMPTY;
        } else {
            return ByteString.wrap(l(w));
        }
    }

    public byte[] o() throws IOException {
        int w = w();
        if (w > this.c - this.e || w <= 0) {
            return l(w);
        }
        byte[] copyOfRange = Arrays.copyOfRange(this.f11301a, this.e, this.e + w);
        this.e += w;
        return copyOfRange;
    }

    public ByteBuffer p() throws IOException {
        ByteBuffer byteBuffer;
        int w = w();
        if (w <= this.c - this.e && w > 0) {
            if (this.f != null || this.b || !this.h) {
                byteBuffer = ByteBuffer.wrap(Arrays.copyOfRange(this.f11301a, this.e, this.e + w));
            } else {
                byteBuffer = ByteBuffer.wrap(this.f11301a, this.e, w).slice();
            }
            this.e += w;
            return byteBuffer;
        } else if (w == 0) {
            return Internal.d;
        } else {
            return ByteBuffer.wrap(l(w));
        }
    }

    public int q() throws IOException {
        return w();
    }

    public int r() throws IOException {
        return w();
    }

    public int s() throws IOException {
        return z();
    }

    public long t() throws IOException {
        return A();
    }

    public int u() throws IOException {
        return c(w());
    }

    public long v() throws IOException {
        return a(x());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x006a, code lost:
        if (r1[r2] < 0) goto L_0x006c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int w() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.e
            int r1 = r5.c
            if (r1 != r0) goto L_0x0007
            goto L_0x006c
        L_0x0007:
            byte[] r1 = r5.f11301a
            int r2 = r0 + 1
            byte r0 = r1[r0]
            if (r0 < 0) goto L_0x0012
            r5.e = r2
            return r0
        L_0x0012:
            int r3 = r5.c
            int r3 = r3 - r2
            r4 = 9
            if (r3 >= r4) goto L_0x001a
            goto L_0x006c
        L_0x001a:
            int r3 = r2 + 1
            byte r2 = r1[r2]
            int r2 = r2 << 7
            r0 = r0 ^ r2
            if (r0 >= 0) goto L_0x0026
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
            goto L_0x0072
        L_0x0026:
            int r2 = r3 + 1
            byte r3 = r1[r3]
            int r3 = r3 << 14
            r0 = r0 ^ r3
            if (r0 < 0) goto L_0x0033
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
        L_0x0031:
            r3 = r2
            goto L_0x0072
        L_0x0033:
            int r3 = r2 + 1
            byte r2 = r1[r2]
            int r2 = r2 << 21
            r0 = r0 ^ r2
            if (r0 >= 0) goto L_0x0041
            r1 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r1
            goto L_0x0072
        L_0x0041:
            int r2 = r3 + 1
            byte r3 = r1[r3]
            int r4 = r3 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r3 >= 0) goto L_0x0031
            int r3 = r2 + 1
            byte r2 = r1[r2]
            if (r2 >= 0) goto L_0x0072
            int r2 = r3 + 1
            byte r3 = r1[r3]
            if (r3 >= 0) goto L_0x0031
            int r3 = r2 + 1
            byte r2 = r1[r2]
            if (r2 >= 0) goto L_0x0072
            int r2 = r3 + 1
            byte r3 = r1[r3]
            if (r3 >= 0) goto L_0x0031
            int r3 = r2 + 1
            byte r1 = r1[r2]
            if (r1 >= 0) goto L_0x0072
        L_0x006c:
            long r0 = r5.y()
            int r0 = (int) r0
            return r0
        L_0x0072:
            r5.e = r3
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.protobuf.CodedInputStream.w():int");
    }

    private void G() throws IOException {
        if (this.c - this.e >= 10) {
            byte[] bArr = this.f11301a;
            int i2 = this.e;
            int i3 = 0;
            while (i3 < 10) {
                int i4 = i2 + 1;
                if (bArr[i2] >= 0) {
                    this.e = i4;
                    return;
                } else {
                    i3++;
                    i2 = i4;
                }
            }
        }
        H();
    }

    private void H() throws IOException {
        int i2 = 0;
        while (i2 < 10) {
            if (F() < 0) {
                i2++;
            } else {
                return;
            }
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    static int b(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return a(read, inputStream);
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int a(int i2, InputStream inputStream) throws IOException {
        if ((i2 & 128) == 0) {
            return i2;
        }
        int i3 = i2 & 127;
        int i4 = 7;
        while (i4 < 32) {
            int read = inputStream.read();
            if (read != -1) {
                i3 |= (read & 127) << i4;
                if ((read & 128) == 0) {
                    return i3;
                }
                i4 += 7;
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }
        while (i4 < 64) {
            int read2 = inputStream.read();
            if (read2 == -1) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if ((read2 & 128) == 0) {
                return i3;
            } else {
                i4 += 7;
            }
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00b9, code lost:
        if (((long) r1[r0]) < 0) goto L_0x00bb;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long x() throws java.io.IOException {
        /*
            r11 = this;
            int r0 = r11.e
            int r1 = r11.c
            if (r1 != r0) goto L_0x0008
            goto L_0x00bb
        L_0x0008:
            byte[] r1 = r11.f11301a
            int r2 = r0 + 1
            byte r0 = r1[r0]
            if (r0 < 0) goto L_0x0014
            r11.e = r2
            long r0 = (long) r0
            return r0
        L_0x0014:
            int r3 = r11.c
            int r3 = r3 - r2
            r4 = 9
            if (r3 >= r4) goto L_0x001d
            goto L_0x00bb
        L_0x001d:
            int r3 = r2 + 1
            byte r2 = r1[r2]
            int r2 = r2 << 7
            r0 = r0 ^ r2
            if (r0 >= 0) goto L_0x002e
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
            long r0 = (long) r0
        L_0x0029:
            r9 = r0
            r0 = r3
        L_0x002b:
            r2 = r9
            goto L_0x00c0
        L_0x002e:
            int r2 = r3 + 1
            byte r3 = r1[r3]
            int r3 = r3 << 14
            r0 = r0 ^ r3
            if (r0 < 0) goto L_0x003d
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            long r0 = (long) r0
            r9 = r0
            r0 = r2
            goto L_0x002b
        L_0x003d:
            int r3 = r2 + 1
            byte r2 = r1[r2]
            int r2 = r2 << 21
            r0 = r0 ^ r2
            if (r0 >= 0) goto L_0x004c
            r1 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r1
            long r0 = (long) r0
            goto L_0x0029
        L_0x004c:
            long r4 = (long) r0
            int r0 = r3 + 1
            byte r2 = r1[r3]
            long r2 = (long) r2
            r6 = 28
            long r2 = r2 << r6
            long r2 = r2 ^ r4
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 < 0) goto L_0x0061
            r4 = 266354560(0xfe03f80, double:1.315966377E-315)
            long r2 = r2 ^ r4
            goto L_0x00c0
        L_0x0061:
            int r6 = r0 + 1
            byte r0 = r1[r0]
            long r7 = (long) r0
            r0 = 35
            long r7 = r7 << r0
            long r2 = r2 ^ r7
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 >= 0) goto L_0x0077
            r0 = -34093383808(0xfffffff80fe03f80, double:NaN)
            long r0 = r0 ^ r2
        L_0x0074:
            r2 = r0
        L_0x0075:
            r0 = r6
            goto L_0x00c0
        L_0x0077:
            int r0 = r6 + 1
            byte r6 = r1[r6]
            long r6 = (long) r6
            r8 = 42
            long r6 = r6 << r8
            long r2 = r2 ^ r6
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 < 0) goto L_0x008b
            r4 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
            long r2 = r2 ^ r4
            goto L_0x00c0
        L_0x008b:
            int r6 = r0 + 1
            byte r0 = r1[r0]
            long r7 = (long) r0
            r0 = 49
            long r7 = r7 << r0
            long r2 = r2 ^ r7
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 >= 0) goto L_0x009f
            r0 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
            long r0 = r0 ^ r2
            goto L_0x0074
        L_0x009f:
            int r0 = r6 + 1
            byte r6 = r1[r6]
            long r6 = (long) r6
            r8 = 56
            long r6 = r6 << r8
            long r2 = r2 ^ r6
            r6 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
            long r2 = r2 ^ r6
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x00c0
            int r6 = r0 + 1
            byte r0 = r1[r0]
            long r0 = (long) r0
            int r7 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r7 >= 0) goto L_0x0075
        L_0x00bb:
            long r0 = r11.y()
            return r0
        L_0x00c0:
            r11.e = r0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.protobuf.CodedInputStream.x():long");
    }

    /* access modifiers changed from: package-private */
    public long y() throws IOException {
        long j2 = 0;
        for (int i2 = 0; i2 < 64; i2 += 7) {
            byte F = F();
            j2 |= ((long) (F & Byte.MAX_VALUE)) << i2;
            if ((F & 128) == 0) {
                return j2;
            }
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    public int z() throws IOException {
        int i2 = this.e;
        if (this.c - i2 < 4) {
            j(4);
            i2 = this.e;
        }
        byte[] bArr = this.f11301a;
        this.e = i2 + 4;
        return ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
    }

    public long A() throws IOException {
        int i2 = this.e;
        if (this.c - i2 < 8) {
            j(8);
            i2 = this.e;
        }
        byte[] bArr = this.f11301a;
        this.e = i2 + 8;
        return ((((long) bArr[i2 + 7]) & 255) << 56) | (((long) bArr[i2]) & 255) | ((((long) bArr[i2 + 1]) & 255) << 8) | ((((long) bArr[i2 + 2]) & 255) << 16) | ((((long) bArr[i2 + 3]) & 255) << 24) | ((((long) bArr[i2 + 4]) & 255) << 32) | ((((long) bArr[i2 + 5]) & 255) << 40) | ((((long) bArr[i2 + 6]) & 255) << 48);
    }

    private CodedInputStream(byte[] bArr, int i2, int i3, boolean z) {
        this.f11301a = bArr;
        this.c = i3 + i2;
        this.e = i2;
        this.i = -i2;
        this.f = null;
        this.b = z;
    }

    private CodedInputStream(InputStream inputStream, int i2) {
        this.f11301a = new byte[i2];
        this.e = 0;
        this.i = 0;
        this.f = inputStream;
        this.b = false;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public int d(int i2) {
        if (i2 >= 0) {
            int i3 = this.l;
            this.l = i2;
            return i3;
        }
        throw new IllegalArgumentException("Recursion limit cannot be negative: " + i2);
    }

    public int e(int i2) {
        if (i2 >= 0) {
            int i3 = this.m;
            this.m = i2;
            return i3;
        }
        throw new IllegalArgumentException("Size limit cannot be negative: " + i2);
    }

    public void B() {
        this.i = -this.e;
    }

    public int f(int i2) throws InvalidProtocolBufferException {
        if (i2 >= 0) {
            int i3 = i2 + this.i + this.e;
            int i4 = this.j;
            if (i3 <= i4) {
                this.j = i3;
                I();
                return i4;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        throw InvalidProtocolBufferException.negativeSize();
    }

    private void I() {
        this.c += this.d;
        int i2 = this.i + this.c;
        if (i2 > this.j) {
            this.d = i2 - this.j;
            this.c -= this.d;
            return;
        }
        this.d = 0;
    }

    public void g(int i2) {
        this.j = i2;
        I();
    }

    public int C() {
        if (this.j == Integer.MAX_VALUE) {
            return -1;
        }
        return this.j - (this.i + this.e);
    }

    public boolean D() throws IOException {
        return this.e == this.c && !k(1);
    }

    public int E() {
        return this.i + this.e;
    }

    private void j(int i2) throws IOException {
        if (!k(i2)) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }

    private boolean k(int i2) throws IOException {
        if (this.e + i2 <= this.c) {
            throw new IllegalStateException("refillBuffer() called when " + i2 + " bytes were already available in buffer");
        } else if (this.i + this.e + i2 > this.j) {
            return false;
        } else {
            if (this.q != null) {
                this.q.a();
            }
            if (this.f != null) {
                int i3 = this.e;
                if (i3 > 0) {
                    if (this.c > i3) {
                        System.arraycopy(this.f11301a, i3, this.f11301a, 0, this.c - i3);
                    }
                    this.i += i3;
                    this.c -= i3;
                    this.e = 0;
                }
                int read = this.f.read(this.f11301a, this.c, this.f11301a.length - this.c);
                if (read == 0 || read < -1 || read > this.f11301a.length) {
                    throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + read + "\nThe InputStream implementation is buggy.");
                } else if (read > 0) {
                    this.c += read;
                    if ((this.i + i2) - this.m <= 0) {
                        I();
                        if (this.c >= i2) {
                            return true;
                        }
                        return k(i2);
                    }
                    throw InvalidProtocolBufferException.sizeLimitExceeded();
                }
            }
            return false;
        }
    }

    public byte F() throws IOException {
        if (this.e == this.c) {
            j(1);
        }
        byte[] bArr = this.f11301a;
        int i2 = this.e;
        this.e = i2 + 1;
        return bArr[i2];
    }

    public byte[] h(int i2) throws IOException {
        int i3 = this.e;
        if (i2 > this.c - i3 || i2 <= 0) {
            return l(i2);
        }
        int i4 = i2 + i3;
        this.e = i4;
        return Arrays.copyOfRange(this.f11301a, i3, i4);
    }

    private byte[] l(int i2) throws IOException {
        if (i2 > 0) {
            int i3 = this.i + this.e + i2;
            if (i3 > this.m) {
                throw InvalidProtocolBufferException.sizeLimitExceeded();
            } else if (i3 > this.j) {
                i((this.j - this.i) - this.e);
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (this.f != null) {
                int i4 = this.e;
                int i5 = this.c - this.e;
                this.i += this.c;
                this.e = 0;
                this.c = 0;
                int i6 = i2 - i5;
                if (i6 < 4096 || i6 <= this.f.available()) {
                    byte[] bArr = new byte[i2];
                    System.arraycopy(this.f11301a, i4, bArr, 0, i5);
                    while (i5 < bArr.length) {
                        int read = this.f.read(bArr, i5, i2 - i5);
                        if (read != -1) {
                            this.i += read;
                            i5 += read;
                        } else {
                            throw InvalidProtocolBufferException.truncatedMessage();
                        }
                    }
                    return bArr;
                }
                ArrayList<byte[]> arrayList = new ArrayList<>();
                while (i6 > 0) {
                    byte[] bArr2 = new byte[Math.min(i6, 4096)];
                    int i7 = 0;
                    while (i7 < bArr2.length) {
                        int read2 = this.f.read(bArr2, i7, bArr2.length - i7);
                        if (read2 != -1) {
                            this.i += read2;
                            i7 += read2;
                        } else {
                            throw InvalidProtocolBufferException.truncatedMessage();
                        }
                    }
                    i6 -= bArr2.length;
                    arrayList.add(bArr2);
                }
                byte[] bArr3 = new byte[i2];
                System.arraycopy(this.f11301a, i4, bArr3, 0, i5);
                for (byte[] bArr4 : arrayList) {
                    System.arraycopy(bArr4, 0, bArr3, i5, bArr4.length);
                    i5 += bArr4.length;
                }
                return bArr3;
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        } else if (i2 == 0) {
            return Internal.c;
        } else {
            throw InvalidProtocolBufferException.negativeSize();
        }
    }

    public void i(int i2) throws IOException {
        if (i2 > this.c - this.e || i2 < 0) {
            m(i2);
        } else {
            this.e += i2;
        }
    }

    private void m(int i2) throws IOException {
        if (i2 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        } else if (this.i + this.e + i2 <= this.j) {
            int i3 = this.c - this.e;
            this.e = this.c;
            j(1);
            while (true) {
                int i4 = i2 - i3;
                if (i4 > this.c) {
                    i3 += this.c;
                    this.e = this.c;
                    j(1);
                } else {
                    this.e = i4;
                    return;
                }
            }
        } else {
            i((this.j - this.i) - this.e);
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }
}
