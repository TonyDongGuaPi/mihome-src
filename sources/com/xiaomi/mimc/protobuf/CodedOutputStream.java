package com.xiaomi.mimc.protobuf;

import com.tencent.tinker.loader.shareutil.ShareElfFile;
import com.xiaomi.mimc.protobuf.Utf8;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class CodedOutputStream extends ByteOutput {
    @Deprecated

    /* renamed from: a  reason: collision with root package name */
    public static final int f11303a = 4;
    public static final int b = 4096;
    private static final Logger c = Logger.getLogger(CodedOutputStream.class.getName());
    /* access modifiers changed from: private */
    public static final boolean d = UnsafeUtil.a();
    /* access modifiers changed from: private */
    public static final long e = UnsafeUtil.c();
    private static final int f = 4;
    private static final int g = 8;
    private static final int h = 10;

    static int a(int i) {
        if (i > 4096) {
            return 4096;
        }
        return i;
    }

    public static int b(double d2) {
        return 8;
    }

    public static int b(float f2) {
        return 4;
    }

    public static int b(boolean z) {
        return 1;
    }

    public static int g(long j) {
        int i;
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if ((-34359738368L & j) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if ((-2097152 & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & -16384) != 0 ? i + 1 : i;
    }

    public static int i(long j) {
        return 8;
    }

    public static int j(long j) {
        return 8;
    }

    public static int k(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (i & ShareElfFile.SectionHeader.A) == 0 ? 4 : 5;
    }

    public static long k(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int m(int i) {
        return 4;
    }

    public static int n(int i) {
        return 4;
    }

    public static int q(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public abstract void a() throws IOException;

    public abstract void a(byte b2) throws IOException;

    public abstract void a(int i, int i2) throws IOException;

    public abstract void a(int i, ByteString byteString) throws IOException;

    public abstract void a(int i, MessageLite messageLite) throws IOException;

    public abstract void a(int i, String str) throws IOException;

    public abstract void a(int i, ByteBuffer byteBuffer) throws IOException;

    public abstract void a(int i, boolean z) throws IOException;

    public abstract void a(int i, byte[] bArr) throws IOException;

    public abstract void a(int i, byte[] bArr, int i2, int i3) throws IOException;

    public abstract void a(MessageLite messageLite) throws IOException;

    public abstract void a(String str) throws IOException;

    public abstract void a(ByteBuffer byteBuffer) throws IOException;

    public abstract void a(byte[] bArr, int i, int i2) throws IOException;

    public abstract int b();

    public abstract void b(int i, int i2) throws IOException;

    public abstract void b(int i, long j) throws IOException;

    public abstract void b(int i, ByteString byteString) throws IOException;

    public abstract void b(int i, MessageLite messageLite) throws IOException;

    public abstract void b(long j) throws IOException;

    public abstract void b(ByteString byteString) throws IOException;

    public abstract void b(ByteBuffer byteBuffer) throws IOException;

    public abstract void b(byte[] bArr, int i, int i2) throws IOException;

    public abstract void c(int i) throws IOException;

    public abstract void c(int i, int i2) throws IOException;

    public abstract int d();

    public abstract void d(int i) throws IOException;

    public abstract void d(int i, long j) throws IOException;

    public abstract void d(long j) throws IOException;

    public abstract void d(ByteBuffer byteBuffer) throws IOException;

    public abstract void e(int i, int i2) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void e(byte[] bArr, int i, int i2) throws IOException;

    public abstract void f(int i) throws IOException;

    public static CodedOutputStream a(OutputStream outputStream) {
        return a(outputStream, 4096);
    }

    public static CodedOutputStream a(OutputStream outputStream, int i) {
        return new OutputStreamEncoder(outputStream, i);
    }

    public static CodedOutputStream a(byte[] bArr) {
        return c(bArr, 0, bArr.length);
    }

    public static CodedOutputStream c(byte[] bArr, int i, int i2) {
        return new ArrayEncoder(bArr, i, i2);
    }

    public static CodedOutputStream c(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return new NioHeapEncoder(byteBuffer);
        }
        return new NioEncoder(byteBuffer);
    }

    @Deprecated
    public static CodedOutputStream a(ByteBuffer byteBuffer, int i) {
        return c(byteBuffer);
    }

    static CodedOutputStream a(ByteOutput byteOutput, int i) {
        if (i >= 0) {
            return new ByteOutputEncoder(byteOutput, i);
        }
        throw new IllegalArgumentException("bufferSize must be positive");
    }

    private CodedOutputStream() {
    }

    public final void d(int i, int i2) throws IOException {
        c(i, q(i2));
    }

    public final void f(int i, int i2) throws IOException {
        e(i, i2);
    }

    public final void a(int i, long j) throws IOException {
        b(i, j);
    }

    public final void c(int i, long j) throws IOException {
        b(i, k(j));
    }

    public final void e(int i, long j) throws IOException {
        d(i, j);
    }

    public final void a(int i, float f2) throws IOException {
        e(i, Float.floatToRawIntBits(f2));
    }

    public final void a(int i, double d2) throws IOException {
        d(i, Double.doubleToRawLongBits(d2));
    }

    public final void g(int i, int i2) throws IOException {
        b(i, i2);
    }

    public final void b(byte b2) throws IOException {
        a(b2);
    }

    public final void b(int i) throws IOException {
        a((byte) i);
    }

    public final void b(byte[] bArr) throws IOException {
        a(bArr, 0, bArr.length);
    }

    public final void d(byte[] bArr, int i, int i2) throws IOException {
        a(bArr, i, i2);
    }

    public final void a(ByteString byteString) throws IOException {
        byteString.writeTo((ByteOutput) this);
    }

    public final void e(int i) throws IOException {
        d(q(i));
    }

    public final void g(int i) throws IOException {
        f(i);
    }

    public final void a(long j) throws IOException {
        b(j);
    }

    public final void c(long j) throws IOException {
        b(k(j));
    }

    public final void e(long j) throws IOException {
        d(j);
    }

    public final void a(float f2) throws IOException {
        f(Float.floatToRawIntBits(f2));
    }

    public final void a(double d2) throws IOException {
        d(Double.doubleToRawLongBits(d2));
    }

    public final void a(boolean z) throws IOException {
        a(z ? (byte) 1 : 0);
    }

    public final void h(int i) throws IOException {
        c(i);
    }

    public final void c(byte[] bArr) throws IOException {
        e(bArr, 0, bArr.length);
    }

    public static int h(int i, int i2) {
        return i(i) + j(i2);
    }

    public static int i(int i, int i2) {
        return i(i) + k(i2);
    }

    public static int j(int i, int i2) {
        return i(i) + l(i2);
    }

    public static int k(int i, int i2) {
        return i(i) + m(i2);
    }

    public static int l(int i, int i2) {
        return i(i) + n(i2);
    }

    public static int f(int i, long j) {
        return i(i) + f(j);
    }

    public static int g(int i, long j) {
        return i(i) + g(j);
    }

    public static int h(int i, long j) {
        return i(i) + h(j);
    }

    public static int i(int i, long j) {
        return i(i) + i(j);
    }

    public static int j(int i, long j) {
        return i(i) + j(j);
    }

    public static int b(int i, float f2) {
        return i(i) + b(f2);
    }

    public static int b(int i, double d2) {
        return i(i) + b(d2);
    }

    public static int b(int i, boolean z) {
        return i(i) + b(z);
    }

    public static int m(int i, int i2) {
        return i(i) + o(i2);
    }

    public static int b(int i, String str) {
        return i(i) + b(str);
    }

    public static int c(int i, ByteString byteString) {
        return i(i) + c(byteString);
    }

    public static int b(int i, byte[] bArr) {
        return i(i) + d(bArr);
    }

    public static int b(int i, ByteBuffer byteBuffer) {
        return i(i) + e(byteBuffer);
    }

    public static int a(int i, LazyFieldLite lazyFieldLite) {
        return i(i) + a(lazyFieldLite);
    }

    public static int c(int i, MessageLite messageLite) {
        return i(i) + b(messageLite);
    }

    public static int d(int i, MessageLite messageLite) {
        return (i(1) * 2) + i(2, i) + c(3, messageLite);
    }

    public static int d(int i, ByteString byteString) {
        return (i(1) * 2) + i(2, i) + c(3, byteString);
    }

    public static int b(int i, LazyFieldLite lazyFieldLite) {
        return (i(1) * 2) + i(2, i) + a(3, lazyFieldLite);
    }

    public static int i(int i) {
        return k(WireFormat.a(i, 0));
    }

    public static int j(int i) {
        if (i >= 0) {
            return k(i);
        }
        return 10;
    }

    public static int l(int i) {
        return k(q(i));
    }

    public static int f(long j) {
        return g(j);
    }

    public static int h(long j) {
        return g(k(j));
    }

    public static int o(int i) {
        return j(i);
    }

    public static int b(String str) {
        int i;
        try {
            i = Utf8.a((CharSequence) str);
        } catch (Utf8.UnpairedSurrogateException unused) {
            i = str.getBytes(Internal.f11319a).length;
        }
        return p(i);
    }

    public static int a(LazyFieldLite lazyFieldLite) {
        return p(lazyFieldLite.d());
    }

    public static int c(ByteString byteString) {
        return p(byteString.size());
    }

    public static int d(byte[] bArr) {
        return p(bArr.length);
    }

    public static int e(ByteBuffer byteBuffer) {
        return p(byteBuffer.capacity());
    }

    public static int b(MessageLite messageLite) {
        return p(messageLite.k());
    }

    static int p(int i) {
        return k(i) + i;
    }

    public final void c() {
        if (b() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public static class OutOfSpaceException extends IOException {
        private static final String MESSAGE = "CodedOutputStream was writing to a flat byte array and ran out of space.";
        private static final long serialVersionUID = -6947486886997889499L;

        OutOfSpaceException() {
            super(MESSAGE);
        }

        OutOfSpaceException(Throwable th) {
            super(MESSAGE, th);
        }

        OutOfSpaceException(String str, Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.: " + str, th);
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(String str, Utf8.UnpairedSurrogateException unpairedSurrogateException) throws IOException {
        c.log(Level.WARNING, "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", unpairedSurrogateException);
        byte[] bytes = str.getBytes(Internal.f11319a);
        try {
            d(bytes.length);
            b(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e2) {
            throw new OutOfSpaceException(e2);
        } catch (OutOfSpaceException e3) {
            throw e3;
        }
    }

    @Deprecated
    public final void e(int i, MessageLite messageLite) throws IOException {
        a(i, 3);
        c(messageLite);
        a(i, 4);
    }

    @Deprecated
    public final void c(MessageLite messageLite) throws IOException {
        messageLite.a(this);
    }

    @Deprecated
    public static int f(int i, MessageLite messageLite) {
        return (i(i) * 2) + d(messageLite);
    }

    @Deprecated
    public static int d(MessageLite messageLite) {
        return messageLite.k();
    }

    @Deprecated
    public final void r(int i) throws IOException {
        d(i);
    }

    @Deprecated
    public final void l(long j) throws IOException {
        b(j);
    }

    @Deprecated
    public static int s(int i) {
        return k(i);
    }

    @Deprecated
    public static int m(long j) {
        return g(j);
    }

    @Deprecated
    public final void t(int i) throws IOException {
        f(i);
    }

    @Deprecated
    public final void n(long j) throws IOException {
        d(j);
    }

    private static class ArrayEncoder extends CodedOutputStream {
        private final byte[] c;
        private final int d;
        private final int e;
        private int f;

        public void a() {
        }

        ArrayEncoder(byte[] bArr, int i, int i2) {
            super();
            if (bArr != null) {
                int i3 = i + i2;
                if ((i | i2 | (bArr.length - i3)) >= 0) {
                    this.c = bArr;
                    this.d = i;
                    this.f = i;
                    this.e = i3;
                    return;
                }
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
            }
            throw new NullPointerException("buffer");
        }

        public final void a(int i, int i2) throws IOException {
            d(WireFormat.a(i, i2));
        }

        public final void b(int i, int i2) throws IOException {
            a(i, 0);
            c(i2);
        }

        public final void c(int i, int i2) throws IOException {
            a(i, 0);
            d(i2);
        }

        public final void e(int i, int i2) throws IOException {
            a(i, 5);
            f(i2);
        }

        public final void b(int i, long j) throws IOException {
            a(i, 0);
            b(j);
        }

        public final void d(int i, long j) throws IOException {
            a(i, 1);
            d(j);
        }

        public final void a(int i, boolean z) throws IOException {
            a(i, 0);
            a(z ? (byte) 1 : 0);
        }

        public final void a(int i, String str) throws IOException {
            a(i, 2);
            a(str);
        }

        public final void a(int i, ByteString byteString) throws IOException {
            a(i, 2);
            b(byteString);
        }

        public final void a(int i, byte[] bArr) throws IOException {
            a(i, bArr, 0, bArr.length);
        }

        public final void a(int i, byte[] bArr, int i2, int i3) throws IOException {
            a(i, 2);
            e(bArr, i2, i3);
        }

        public final void a(int i, ByteBuffer byteBuffer) throws IOException {
            a(i, 2);
            d(byteBuffer.capacity());
            d(byteBuffer);
        }

        public final void b(ByteString byteString) throws IOException {
            d(byteString.size());
            byteString.writeTo((ByteOutput) this);
        }

        public final void e(byte[] bArr, int i, int i2) throws IOException {
            d(i2);
            a(bArr, i, i2);
        }

        public final void d(ByteBuffer byteBuffer) throws IOException {
            if (byteBuffer.hasArray()) {
                a(byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.capacity());
                return;
            }
            ByteBuffer duplicate = byteBuffer.duplicate();
            duplicate.clear();
            a(duplicate);
        }

        public final void a(int i, MessageLite messageLite) throws IOException {
            a(i, 2);
            a(messageLite);
        }

        public final void b(int i, MessageLite messageLite) throws IOException {
            a(1, 3);
            c(2, i);
            a(3, messageLite);
            a(1, 4);
        }

        public final void b(int i, ByteString byteString) throws IOException {
            a(1, 3);
            c(2, i);
            a(3, byteString);
            a(1, 4);
        }

        public final void a(MessageLite messageLite) throws IOException {
            d(messageLite.k());
            messageLite.a((CodedOutputStream) this);
        }

        public final void a(byte b) throws IOException {
            try {
                byte[] bArr = this.c;
                int i = this.f;
                this.f = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e2) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.f), Integer.valueOf(this.e), 1}), e2);
            }
        }

        public final void c(int i) throws IOException {
            if (i >= 0) {
                d(i);
            } else {
                b((long) i);
            }
        }

        public final void d(int i) throws IOException {
            if (!CodedOutputStream.d || b() < 10) {
                while ((i & -128) != 0) {
                    byte[] bArr = this.c;
                    int i2 = this.f;
                    this.f = i2 + 1;
                    bArr[i2] = (byte) ((i & 127) | 128);
                    i >>>= 7;
                }
                try {
                    byte[] bArr2 = this.c;
                    int i3 = this.f;
                    this.f = i3 + 1;
                    bArr2[i3] = (byte) i;
                } catch (IndexOutOfBoundsException e2) {
                    throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.f), Integer.valueOf(this.e), 1}), e2);
                }
            } else {
                long f2 = CodedOutputStream.e + ((long) this.f);
                while ((i & -128) != 0) {
                    UnsafeUtil.a(this.c, f2, (byte) ((i & 127) | 128));
                    this.f++;
                    i >>>= 7;
                    f2 = 1 + f2;
                }
                UnsafeUtil.a(this.c, f2, (byte) i);
                this.f++;
            }
        }

        public final void f(int i) throws IOException {
            try {
                byte[] bArr = this.c;
                int i2 = this.f;
                this.f = i2 + 1;
                bArr[i2] = (byte) (i & 255);
                byte[] bArr2 = this.c;
                int i3 = this.f;
                this.f = i3 + 1;
                bArr2[i3] = (byte) ((i >> 8) & 255);
                byte[] bArr3 = this.c;
                int i4 = this.f;
                this.f = i4 + 1;
                bArr3[i4] = (byte) ((i >> 16) & 255);
                byte[] bArr4 = this.c;
                int i5 = this.f;
                this.f = i5 + 1;
                bArr4[i5] = (byte) ((i >> 24) & 255);
            } catch (IndexOutOfBoundsException e2) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.f), Integer.valueOf(this.e), 1}), e2);
            }
        }

        public final void b(long j) throws IOException {
            if (!CodedOutputStream.d || b() < 10) {
                while ((j & -128) != 0) {
                    byte[] bArr = this.c;
                    int i = this.f;
                    this.f = i + 1;
                    bArr[i] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                }
                try {
                    byte[] bArr2 = this.c;
                    int i2 = this.f;
                    this.f = i2 + 1;
                    bArr2[i2] = (byte) ((int) j);
                } catch (IndexOutOfBoundsException e2) {
                    throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.f), Integer.valueOf(this.e), 1}), e2);
                }
            } else {
                long f2 = CodedOutputStream.e + ((long) this.f);
                while ((j & -128) != 0) {
                    UnsafeUtil.a(this.c, f2, (byte) ((((int) j) & 127) | 128));
                    this.f++;
                    j >>>= 7;
                    f2 = 1 + f2;
                }
                UnsafeUtil.a(this.c, f2, (byte) ((int) j));
                this.f++;
            }
        }

        public final void d(long j) throws IOException {
            try {
                byte[] bArr = this.c;
                int i = this.f;
                this.f = i + 1;
                bArr[i] = (byte) (((int) j) & 255);
                byte[] bArr2 = this.c;
                int i2 = this.f;
                this.f = i2 + 1;
                bArr2[i2] = (byte) (((int) (j >> 8)) & 255);
                byte[] bArr3 = this.c;
                int i3 = this.f;
                this.f = i3 + 1;
                bArr3[i3] = (byte) (((int) (j >> 16)) & 255);
                byte[] bArr4 = this.c;
                int i4 = this.f;
                this.f = i4 + 1;
                bArr4[i4] = (byte) (((int) (j >> 24)) & 255);
                byte[] bArr5 = this.c;
                int i5 = this.f;
                this.f = i5 + 1;
                bArr5[i5] = (byte) (((int) (j >> 32)) & 255);
                byte[] bArr6 = this.c;
                int i6 = this.f;
                this.f = i6 + 1;
                bArr6[i6] = (byte) (((int) (j >> 40)) & 255);
                byte[] bArr7 = this.c;
                int i7 = this.f;
                this.f = i7 + 1;
                bArr7[i7] = (byte) (((int) (j >> 48)) & 255);
                byte[] bArr8 = this.c;
                int i8 = this.f;
                this.f = i8 + 1;
                bArr8[i8] = (byte) (((int) (j >> 56)) & 255);
            } catch (IndexOutOfBoundsException e2) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.f), Integer.valueOf(this.e), 1}), e2);
            }
        }

        public final void a(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.c, this.f, i2);
                this.f += i2;
            } catch (IndexOutOfBoundsException e2) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.f), Integer.valueOf(this.e), Integer.valueOf(i2)}), e2);
            }
        }

        public final void b(byte[] bArr, int i, int i2) throws IOException {
            a(bArr, i, i2);
        }

        public final void a(ByteBuffer byteBuffer) throws IOException {
            int remaining = byteBuffer.remaining();
            try {
                byteBuffer.get(this.c, this.f, remaining);
                this.f += remaining;
            } catch (IndexOutOfBoundsException e2) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.f), Integer.valueOf(this.e), Integer.valueOf(remaining)}), e2);
            }
        }

        public final void b(ByteBuffer byteBuffer) throws IOException {
            a(byteBuffer);
        }

        public final void a(String str) throws IOException {
            int i = this.f;
            try {
                int k = k(str.length() * 3);
                int k2 = k(str.length());
                if (k2 == k) {
                    this.f = i + k2;
                    int a2 = Utf8.a((CharSequence) str, this.c, this.f, b());
                    this.f = i;
                    d((a2 - i) - k2);
                    this.f = a2;
                    return;
                }
                d(Utf8.a((CharSequence) str));
                this.f = Utf8.a((CharSequence) str, this.c, this.f, b());
            } catch (Utf8.UnpairedSurrogateException e2) {
                this.f = i;
                a(str, e2);
            } catch (IndexOutOfBoundsException e3) {
                throw new OutOfSpaceException(e3);
            }
        }

        public final int b() {
            return this.e - this.f;
        }

        public final int d() {
            return this.f - this.d;
        }
    }

    private static final class NioHeapEncoder extends ArrayEncoder {
        private final ByteBuffer c;
        private int d;

        NioHeapEncoder(ByteBuffer byteBuffer) {
            super(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            this.c = byteBuffer;
            this.d = byteBuffer.position();
        }

        public void a() {
            this.c.position(this.d + d());
        }
    }

    private static final class NioEncoder extends CodedOutputStream {
        private final ByteBuffer c;
        private final ByteBuffer d;
        private final int e;

        NioEncoder(ByteBuffer byteBuffer) {
            super();
            this.c = byteBuffer;
            this.d = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.e = byteBuffer.position();
        }

        public void a(int i, int i2) throws IOException {
            d(WireFormat.a(i, i2));
        }

        public void b(int i, int i2) throws IOException {
            a(i, 0);
            c(i2);
        }

        public void c(int i, int i2) throws IOException {
            a(i, 0);
            d(i2);
        }

        public void e(int i, int i2) throws IOException {
            a(i, 5);
            f(i2);
        }

        public void b(int i, long j) throws IOException {
            a(i, 0);
            b(j);
        }

        public void d(int i, long j) throws IOException {
            a(i, 1);
            d(j);
        }

        public void a(int i, boolean z) throws IOException {
            a(i, 0);
            a(z ? (byte) 1 : 0);
        }

        public void a(int i, String str) throws IOException {
            a(i, 2);
            a(str);
        }

        public void a(int i, ByteString byteString) throws IOException {
            a(i, 2);
            b(byteString);
        }

        public void a(int i, byte[] bArr) throws IOException {
            a(i, bArr, 0, bArr.length);
        }

        public void a(int i, byte[] bArr, int i2, int i3) throws IOException {
            a(i, 2);
            e(bArr, i2, i3);
        }

        public void a(int i, ByteBuffer byteBuffer) throws IOException {
            a(i, 2);
            d(byteBuffer.capacity());
            d(byteBuffer);
        }

        public void a(int i, MessageLite messageLite) throws IOException {
            a(i, 2);
            a(messageLite);
        }

        public void b(int i, MessageLite messageLite) throws IOException {
            a(1, 3);
            c(2, i);
            a(3, messageLite);
            a(1, 4);
        }

        public void b(int i, ByteString byteString) throws IOException {
            a(1, 3);
            c(2, i);
            a(3, byteString);
            a(1, 4);
        }

        public void a(MessageLite messageLite) throws IOException {
            d(messageLite.k());
            messageLite.a((CodedOutputStream) this);
        }

        public void a(byte b) throws IOException {
            try {
                this.d.put(b);
            } catch (BufferOverflowException e2) {
                throw new OutOfSpaceException(e2);
            }
        }

        public void b(ByteString byteString) throws IOException {
            d(byteString.size());
            byteString.writeTo((ByteOutput) this);
        }

        public void e(byte[] bArr, int i, int i2) throws IOException {
            d(i2);
            a(bArr, i, i2);
        }

        public void d(ByteBuffer byteBuffer) throws IOException {
            if (byteBuffer.hasArray()) {
                a(byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.capacity());
                return;
            }
            ByteBuffer duplicate = byteBuffer.duplicate();
            duplicate.clear();
            a(duplicate);
        }

        public void c(int i) throws IOException {
            if (i >= 0) {
                d(i);
            } else {
                b((long) i);
            }
        }

        public void d(int i) throws IOException {
            while ((i & -128) != 0) {
                this.d.put((byte) ((i & 127) | 128));
                i >>>= 7;
            }
            try {
                this.d.put((byte) i);
            } catch (BufferOverflowException e2) {
                throw new OutOfSpaceException(e2);
            }
        }

        public void f(int i) throws IOException {
            try {
                this.d.putInt(i);
            } catch (BufferOverflowException e2) {
                throw new OutOfSpaceException(e2);
            }
        }

        public void b(long j) throws IOException {
            while ((-128 & j) != 0) {
                this.d.put((byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            try {
                this.d.put((byte) ((int) j));
            } catch (BufferOverflowException e2) {
                throw new OutOfSpaceException(e2);
            }
        }

        public void d(long j) throws IOException {
            try {
                this.d.putLong(j);
            } catch (BufferOverflowException e2) {
                throw new OutOfSpaceException(e2);
            }
        }

        public void a(byte[] bArr, int i, int i2) throws IOException {
            try {
                this.d.put(bArr, i, i2);
            } catch (IndexOutOfBoundsException e2) {
                throw new OutOfSpaceException(e2);
            } catch (BufferOverflowException e3) {
                throw new OutOfSpaceException(e3);
            }
        }

        public void b(byte[] bArr, int i, int i2) throws IOException {
            a(bArr, i, i2);
        }

        public void a(ByteBuffer byteBuffer) throws IOException {
            try {
                this.d.put(byteBuffer);
            } catch (BufferOverflowException e2) {
                throw new OutOfSpaceException(e2);
            }
        }

        public void b(ByteBuffer byteBuffer) throws IOException {
            a(byteBuffer);
        }

        public void a(String str) throws IOException {
            int position = this.d.position();
            try {
                int k = k(str.length() * 3);
                int k2 = k(str.length());
                if (k2 == k) {
                    int position2 = this.d.position() + k2;
                    this.d.position(position2);
                    c(str);
                    int position3 = this.d.position();
                    this.d.position(position);
                    d(position3 - position2);
                    this.d.position(position3);
                    return;
                }
                d(Utf8.a((CharSequence) str));
                c(str);
            } catch (Utf8.UnpairedSurrogateException e2) {
                this.d.position(position);
                a(str, e2);
            } catch (IllegalArgumentException e3) {
                throw new OutOfSpaceException(e3);
            }
        }

        public void a() {
            this.c.position(this.d.position());
        }

        public int b() {
            return this.d.remaining();
        }

        public int d() {
            return this.d.position() - this.e;
        }

        private void c(String str) throws IOException {
            try {
                Utf8.a((CharSequence) str, this.d);
            } catch (IndexOutOfBoundsException e2) {
                throw new OutOfSpaceException(e2);
            }
        }
    }

    private static abstract class AbstractBufferedEncoder extends CodedOutputStream {
        final byte[] c;
        final int d;
        int e;
        int f;

        AbstractBufferedEncoder(int i) {
            super();
            if (i >= 0) {
                this.c = new byte[Math.max(i, 20)];
                this.d = this.c.length;
                return;
            }
            throw new IllegalArgumentException("bufferSize must be >= 0");
        }

        public final int b() {
            throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
        }

        public final int d() {
            return this.f;
        }

        /* access modifiers changed from: package-private */
        public final void c(byte b) {
            byte[] bArr = this.c;
            int i = this.e;
            this.e = i + 1;
            bArr[i] = b;
            this.f++;
        }

        /* access modifiers changed from: package-private */
        public final void n(int i, int i2) {
            v(WireFormat.a(i, i2));
        }

        /* access modifiers changed from: package-private */
        public final void u(int i) {
            if (i >= 0) {
                v(i);
            } else {
                o((long) i);
            }
        }

        /* access modifiers changed from: package-private */
        public final void v(int i) {
            if (CodedOutputStream.d) {
                long f2 = CodedOutputStream.e + ((long) this.e);
                long j = f2;
                while ((i & -128) != 0) {
                    UnsafeUtil.a(this.c, j, (byte) ((i & 127) | 128));
                    i >>>= 7;
                    j = 1 + j;
                }
                UnsafeUtil.a(this.c, j, (byte) i);
                int i2 = (int) ((1 + j) - f2);
                this.e += i2;
                this.f += i2;
                return;
            }
            while ((i & -128) != 0) {
                byte[] bArr = this.c;
                int i3 = this.e;
                this.e = i3 + 1;
                bArr[i3] = (byte) ((i & 127) | 128);
                this.f++;
                i >>>= 7;
            }
            byte[] bArr2 = this.c;
            int i4 = this.e;
            this.e = i4 + 1;
            bArr2[i4] = (byte) i;
            this.f++;
        }

        /* access modifiers changed from: package-private */
        public final void o(long j) {
            if (CodedOutputStream.d) {
                long f2 = CodedOutputStream.e + ((long) this.e);
                long j2 = f2;
                while ((j & -128) != 0) {
                    UnsafeUtil.a(this.c, j2, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                    j2 = 1 + j2;
                }
                UnsafeUtil.a(this.c, j2, (byte) ((int) j));
                int i = (int) ((1 + j2) - f2);
                this.e += i;
                this.f += i;
                return;
            }
            while ((j & -128) != 0) {
                byte[] bArr = this.c;
                int i2 = this.e;
                this.e = i2 + 1;
                bArr[i2] = (byte) ((((int) j) & 127) | 128);
                this.f++;
                j >>>= 7;
            }
            byte[] bArr2 = this.c;
            int i3 = this.e;
            this.e = i3 + 1;
            bArr2[i3] = (byte) ((int) j);
            this.f++;
        }

        /* access modifiers changed from: package-private */
        public final void w(int i) {
            byte[] bArr = this.c;
            int i2 = this.e;
            this.e = i2 + 1;
            bArr[i2] = (byte) (i & 255);
            byte[] bArr2 = this.c;
            int i3 = this.e;
            this.e = i3 + 1;
            bArr2[i3] = (byte) ((i >> 8) & 255);
            byte[] bArr3 = this.c;
            int i4 = this.e;
            this.e = i4 + 1;
            bArr3[i4] = (byte) ((i >> 16) & 255);
            byte[] bArr4 = this.c;
            int i5 = this.e;
            this.e = i5 + 1;
            bArr4[i5] = (byte) ((i >> 24) & 255);
            this.f += 4;
        }

        /* access modifiers changed from: package-private */
        public final void p(long j) {
            byte[] bArr = this.c;
            int i = this.e;
            this.e = i + 1;
            bArr[i] = (byte) ((int) (j & 255));
            byte[] bArr2 = this.c;
            int i2 = this.e;
            this.e = i2 + 1;
            bArr2[i2] = (byte) ((int) ((j >> 8) & 255));
            byte[] bArr3 = this.c;
            int i3 = this.e;
            this.e = i3 + 1;
            bArr3[i3] = (byte) ((int) ((j >> 16) & 255));
            byte[] bArr4 = this.c;
            int i4 = this.e;
            this.e = i4 + 1;
            bArr4[i4] = (byte) ((int) (255 & (j >> 24)));
            byte[] bArr5 = this.c;
            int i5 = this.e;
            this.e = i5 + 1;
            bArr5[i5] = (byte) (((int) (j >> 32)) & 255);
            byte[] bArr6 = this.c;
            int i6 = this.e;
            this.e = i6 + 1;
            bArr6[i6] = (byte) (((int) (j >> 40)) & 255);
            byte[] bArr7 = this.c;
            int i7 = this.e;
            this.e = i7 + 1;
            bArr7[i7] = (byte) (((int) (j >> 48)) & 255);
            byte[] bArr8 = this.c;
            int i8 = this.e;
            this.e = i8 + 1;
            bArr8[i8] = (byte) (((int) (j >> 56)) & 255);
            this.f += 8;
        }
    }

    private static final class ByteOutputEncoder extends AbstractBufferedEncoder {
        private final ByteOutput g;

        ByteOutputEncoder(ByteOutput byteOutput, int i) {
            super(i);
            if (byteOutput != null) {
                this.g = byteOutput;
                return;
            }
            throw new NullPointerException("out");
        }

        public void a(int i, int i2) throws IOException {
            d(WireFormat.a(i, i2));
        }

        public void b(int i, int i2) throws IOException {
            x(20);
            n(i, 0);
            u(i2);
        }

        public void c(int i, int i2) throws IOException {
            x(20);
            n(i, 0);
            v(i2);
        }

        public void e(int i, int i2) throws IOException {
            x(14);
            n(i, 5);
            w(i2);
        }

        public void b(int i, long j) throws IOException {
            x(20);
            n(i, 0);
            o(j);
        }

        public void d(int i, long j) throws IOException {
            x(18);
            n(i, 1);
            p(j);
        }

        public void a(int i, boolean z) throws IOException {
            x(11);
            n(i, 0);
            c(z ? (byte) 1 : 0);
        }

        public void a(int i, String str) throws IOException {
            a(i, 2);
            a(str);
        }

        public void a(int i, ByteString byteString) throws IOException {
            a(i, 2);
            b(byteString);
        }

        public void a(int i, byte[] bArr) throws IOException {
            a(i, bArr, 0, bArr.length);
        }

        public void a(int i, byte[] bArr, int i2, int i3) throws IOException {
            a(i, 2);
            e(bArr, i2, i3);
        }

        public void a(int i, ByteBuffer byteBuffer) throws IOException {
            a(i, 2);
            d(byteBuffer.capacity());
            d(byteBuffer);
        }

        public void b(ByteString byteString) throws IOException {
            d(byteString.size());
            byteString.writeTo((ByteOutput) this);
        }

        public void e(byte[] bArr, int i, int i2) throws IOException {
            d(i2);
            a(bArr, i, i2);
        }

        public void d(ByteBuffer byteBuffer) throws IOException {
            if (byteBuffer.hasArray()) {
                a(byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.capacity());
                return;
            }
            ByteBuffer duplicate = byteBuffer.duplicate();
            duplicate.clear();
            a(duplicate);
        }

        public void a(int i, MessageLite messageLite) throws IOException {
            a(i, 2);
            a(messageLite);
        }

        public void b(int i, MessageLite messageLite) throws IOException {
            a(1, 3);
            c(2, i);
            a(3, messageLite);
            a(1, 4);
        }

        public void b(int i, ByteString byteString) throws IOException {
            a(1, 3);
            c(2, i);
            a(3, byteString);
            a(1, 4);
        }

        public void a(MessageLite messageLite) throws IOException {
            d(messageLite.k());
            messageLite.a((CodedOutputStream) this);
        }

        public void a(byte b) throws IOException {
            if (this.e == this.d) {
                g();
            }
            c(b);
        }

        public void c(int i) throws IOException {
            if (i >= 0) {
                d(i);
            } else {
                b((long) i);
            }
        }

        public void d(int i) throws IOException {
            x(10);
            v(i);
        }

        public void f(int i) throws IOException {
            x(4);
            w(i);
        }

        public void b(long j) throws IOException {
            x(10);
            o(j);
        }

        public void d(long j) throws IOException {
            x(8);
            p(j);
        }

        public void a(String str) throws IOException {
            int length = str.length() * 3;
            int k = k(length);
            int i = k + length;
            if (i > this.d) {
                byte[] bArr = new byte[length];
                int a2 = Utf8.a((CharSequence) str, bArr, 0, length);
                d(a2);
                b(bArr, 0, a2);
                return;
            }
            if (i > this.d - this.e) {
                g();
            }
            int i2 = this.e;
            try {
                int k2 = k(str.length());
                if (k2 == k) {
                    this.e = i2 + k2;
                    int a3 = Utf8.a((CharSequence) str, this.c, this.e, this.d - this.e);
                    this.e = i2;
                    int i3 = (a3 - i2) - k2;
                    v(i3);
                    this.e = a3;
                    this.f += i3;
                    return;
                }
                int a4 = Utf8.a((CharSequence) str);
                v(a4);
                this.e = Utf8.a((CharSequence) str, this.c, this.e, a4);
                this.f += a4;
            } catch (Utf8.UnpairedSurrogateException e) {
                this.f -= this.e - i2;
                this.e = i2;
                a(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new OutOfSpaceException(e2);
            }
        }

        public void a() throws IOException {
            if (this.e > 0) {
                g();
            }
        }

        public void a(byte[] bArr, int i, int i2) throws IOException {
            a();
            this.g.a(bArr, i, i2);
            this.f += i2;
        }

        public void b(byte[] bArr, int i, int i2) throws IOException {
            a();
            this.g.b(bArr, i, i2);
            this.f += i2;
        }

        public void a(ByteBuffer byteBuffer) throws IOException {
            a();
            int remaining = byteBuffer.remaining();
            this.g.a(byteBuffer);
            this.f += remaining;
        }

        public void b(ByteBuffer byteBuffer) throws IOException {
            a();
            int remaining = byteBuffer.remaining();
            this.g.b(byteBuffer);
            this.f += remaining;
        }

        private void x(int i) throws IOException {
            if (this.d - this.e < i) {
                g();
            }
        }

        private void g() throws IOException {
            this.g.a(this.c, 0, this.e);
            this.e = 0;
        }
    }

    private static final class OutputStreamEncoder extends AbstractBufferedEncoder {
        private final OutputStream g;

        OutputStreamEncoder(OutputStream outputStream, int i) {
            super(i);
            if (outputStream != null) {
                this.g = outputStream;
                return;
            }
            throw new NullPointerException("out");
        }

        public void a(int i, int i2) throws IOException {
            d(WireFormat.a(i, i2));
        }

        public void b(int i, int i2) throws IOException {
            x(20);
            n(i, 0);
            u(i2);
        }

        public void c(int i, int i2) throws IOException {
            x(20);
            n(i, 0);
            v(i2);
        }

        public void e(int i, int i2) throws IOException {
            x(14);
            n(i, 5);
            w(i2);
        }

        public void b(int i, long j) throws IOException {
            x(20);
            n(i, 0);
            o(j);
        }

        public void d(int i, long j) throws IOException {
            x(18);
            n(i, 1);
            p(j);
        }

        public void a(int i, boolean z) throws IOException {
            x(11);
            n(i, 0);
            c(z ? (byte) 1 : 0);
        }

        public void a(int i, String str) throws IOException {
            a(i, 2);
            a(str);
        }

        public void a(int i, ByteString byteString) throws IOException {
            a(i, 2);
            b(byteString);
        }

        public void a(int i, byte[] bArr) throws IOException {
            a(i, bArr, 0, bArr.length);
        }

        public void a(int i, byte[] bArr, int i2, int i3) throws IOException {
            a(i, 2);
            e(bArr, i2, i3);
        }

        public void a(int i, ByteBuffer byteBuffer) throws IOException {
            a(i, 2);
            d(byteBuffer.capacity());
            d(byteBuffer);
        }

        public void b(ByteString byteString) throws IOException {
            d(byteString.size());
            byteString.writeTo((ByteOutput) this);
        }

        public void e(byte[] bArr, int i, int i2) throws IOException {
            d(i2);
            a(bArr, i, i2);
        }

        public void d(ByteBuffer byteBuffer) throws IOException {
            if (byteBuffer.hasArray()) {
                a(byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.capacity());
                return;
            }
            ByteBuffer duplicate = byteBuffer.duplicate();
            duplicate.clear();
            a(duplicate);
        }

        public void a(int i, MessageLite messageLite) throws IOException {
            a(i, 2);
            a(messageLite);
        }

        public void b(int i, MessageLite messageLite) throws IOException {
            a(1, 3);
            c(2, i);
            a(3, messageLite);
            a(1, 4);
        }

        public void b(int i, ByteString byteString) throws IOException {
            a(1, 3);
            c(2, i);
            a(3, byteString);
            a(1, 4);
        }

        public void a(MessageLite messageLite) throws IOException {
            d(messageLite.k());
            messageLite.a((CodedOutputStream) this);
        }

        public void a(byte b) throws IOException {
            if (this.e == this.d) {
                g();
            }
            c(b);
        }

        public void c(int i) throws IOException {
            if (i >= 0) {
                d(i);
            } else {
                b((long) i);
            }
        }

        public void d(int i) throws IOException {
            x(10);
            v(i);
        }

        public void f(int i) throws IOException {
            x(4);
            w(i);
        }

        public void b(long j) throws IOException {
            x(10);
            o(j);
        }

        public void d(long j) throws IOException {
            x(8);
            p(j);
        }

        public void a(String str) throws IOException {
            int i;
            int i2;
            try {
                int length = str.length() * 3;
                int k = k(length);
                int i3 = k + length;
                if (i3 > this.d) {
                    byte[] bArr = new byte[length];
                    int a2 = Utf8.a((CharSequence) str, bArr, 0, length);
                    d(a2);
                    b(bArr, 0, a2);
                    return;
                }
                if (i3 > this.d - this.e) {
                    g();
                }
                int k2 = k(str.length());
                i = this.e;
                if (k2 == k) {
                    this.e = i + k2;
                    int a3 = Utf8.a((CharSequence) str, this.c, this.e, this.d - this.e);
                    this.e = i;
                    i2 = (a3 - i) - k2;
                    v(i2);
                    this.e = a3;
                } else {
                    i2 = Utf8.a((CharSequence) str);
                    v(i2);
                    this.e = Utf8.a((CharSequence) str, this.c, this.e, i2);
                }
                this.f += i2;
            } catch (Utf8.UnpairedSurrogateException e) {
                this.f -= this.e - i;
                this.e = i;
                throw e;
            } catch (ArrayIndexOutOfBoundsException e2) {
                throw new OutOfSpaceException(e2);
            } catch (Utf8.UnpairedSurrogateException e3) {
                a(str, e3);
            }
        }

        public void a() throws IOException {
            if (this.e > 0) {
                g();
            }
        }

        public void a(byte[] bArr, int i, int i2) throws IOException {
            if (this.d - this.e >= i2) {
                System.arraycopy(bArr, i, this.c, this.e, i2);
                this.e += i2;
                this.f += i2;
                return;
            }
            int i3 = this.d - this.e;
            System.arraycopy(bArr, i, this.c, this.e, i3);
            int i4 = i + i3;
            int i5 = i2 - i3;
            this.e = this.d;
            this.f += i3;
            g();
            if (i5 <= this.d) {
                System.arraycopy(bArr, i4, this.c, 0, i5);
                this.e = i5;
            } else {
                this.g.write(bArr, i4, i5);
            }
            this.f += i5;
        }

        public void b(byte[] bArr, int i, int i2) throws IOException {
            a(bArr, i, i2);
        }

        public void a(ByteBuffer byteBuffer) throws IOException {
            int remaining = byteBuffer.remaining();
            if (this.d - this.e >= remaining) {
                byteBuffer.get(this.c, this.e, remaining);
                this.e += remaining;
                this.f += remaining;
                return;
            }
            int i = this.d - this.e;
            byteBuffer.get(this.c, this.e, i);
            int i2 = remaining - i;
            this.e = this.d;
            this.f += i;
            g();
            while (i2 > this.d) {
                byteBuffer.get(this.c, 0, this.d);
                this.g.write(this.c, 0, this.d);
                i2 -= this.d;
                this.f += this.d;
            }
            byteBuffer.get(this.c, 0, i2);
            this.e = i2;
            this.f += i2;
        }

        public void b(ByteBuffer byteBuffer) throws IOException {
            a(byteBuffer);
        }

        private void x(int i) throws IOException {
            if (this.d - this.e < i) {
                g();
            }
        }

        private void g() throws IOException {
            this.g.write(this.c, 0, this.e);
            this.e = 0;
        }
    }
}
