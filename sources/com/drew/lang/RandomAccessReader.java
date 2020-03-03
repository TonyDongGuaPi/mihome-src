package com.drew.lang;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.StringValue;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public abstract class RandomAccessReader {

    /* renamed from: a  reason: collision with root package name */
    private boolean f5199a = true;

    public abstract int a(int i);

    public abstract long a() throws IOException;

    /* access modifiers changed from: protected */
    public abstract void a(int i, int i2) throws IOException;

    public abstract byte b(int i) throws IOException;

    /* access modifiers changed from: protected */
    public abstract boolean b(int i, int i2) throws IOException;

    @NotNull
    public abstract byte[] c(int i, int i2) throws IOException;

    public void a(boolean z) {
        this.f5199a = z;
    }

    public boolean b() {
        return this.f5199a;
    }

    public boolean c(int i) throws IOException {
        int i2 = i / 8;
        a(i2, 1);
        if (((b(i2) >> (i % 8)) & 1) == 1) {
            return true;
        }
        return false;
    }

    public short d(int i) throws IOException {
        a(i, 1);
        return (short) (b(i) & 255);
    }

    public byte e(int i) throws IOException {
        a(i, 1);
        return b(i);
    }

    public int f(int i) throws IOException {
        a(i, 2);
        if (this.f5199a) {
            return (b(i + 1) & 255) | ((b(i) << 8) & 65280);
        }
        return (b(i) & 255) | ((b(i + 1) << 8) & 65280);
    }

    public short g(int i) throws IOException {
        a(i, 2);
        if (this.f5199a) {
            return (short) ((((short) b(i + 1)) & 255) | ((((short) b(i)) << 8) & -256));
        }
        return (short) ((((short) b(i)) & 255) | ((((short) b(i + 1)) << 8) & -256));
    }

    public int h(int i) throws IOException {
        a(i, 3);
        if (this.f5199a) {
            return (b(i + 2) & 255) | ((b(i) << 16) & 16711680) | (65280 & (b(i + 1) << 8));
        }
        return (b(i) & 255) | ((b(i + 2) << 16) & 16711680) | (65280 & (b(i + 1) << 8));
    }

    public long i(int i) throws IOException {
        a(i, 4);
        if (this.f5199a) {
            long b = 4278190080L & (((long) b(i)) << 24);
            long b2 = 65280 & (((long) b(i + 2)) << 8);
            return (255 & ((long) b(i + 3))) | b2 | (16711680 & (((long) b(i + 1)) << 16)) | b;
        }
        long b3 = 4278190080L & (((long) b(i + 3)) << 24);
        long b4 = 65280 & (((long) b(i + 1)) << 8);
        return (255 & ((long) b(i))) | b4 | (16711680 & (((long) b(i + 2)) << 16)) | b3;
    }

    public int j(int i) throws IOException {
        a(i, 4);
        if (this.f5199a) {
            return (b(i + 3) & 255) | ((b(i) << 24) & -16777216) | (16711680 & (b(i + 1) << 16)) | (65280 & (b(i + 2) << 8));
        }
        return (b(i) & 255) | ((b(i + 3) << 24) & -16777216) | (16711680 & (b(i + 2) << 16)) | (65280 & (b(i + 1) << 8));
    }

    public long k(int i) throws IOException {
        int i2 = i;
        a(i2, 8);
        if (this.f5199a) {
            return ((((long) b(i)) << 56) & -72057594037927936L) | ((((long) b(i2 + 1)) << 48) & 71776119061217280L) | ((((long) b(i2 + 2)) << 40) & 280375465082880L) | ((((long) b(i2 + 3)) << 32) & 1095216660480L) | ((((long) b(i2 + 4)) << 24) & 4278190080L) | ((((long) b(i2 + 5)) << 16) & 16711680) | ((((long) b(i2 + 6)) << 8) & 65280) | (((long) b(i2 + 7)) & 255);
        }
        return ((((long) b(i2 + 7)) << 56) & -72057594037927936L) | ((((long) b(i2 + 6)) << 48) & 71776119061217280L) | ((((long) b(i2 + 5)) << 40) & 280375465082880L) | ((((long) b(i2 + 4)) << 32) & 1095216660480L) | ((((long) b(i2 + 3)) << 24) & 4278190080L) | ((((long) b(i2 + 2)) << 16) & 16711680) | ((((long) b(i2 + 1)) << 8) & 65280) | (((long) b(i)) & 255);
    }

    public float l(int i) throws IOException {
        a(i, 4);
        if (this.f5199a) {
            byte b = (b(i + 3) & 255) | ((b(i + 2) & 255) << 8);
            double b2 = (double) ((float) (((b(i) & 255) << 8) | (b(i + 1) & 255)));
            double d = (double) b;
            Double.isNaN(d);
            Double.isNaN(b2);
            return (float) (b2 + (d / 65536.0d));
        }
        byte b3 = (b(i) & 255) | ((b(i + 1) & 255) << 8);
        double b4 = (double) ((float) (((b(i + 3) & 255) << 8) | (b(i + 2) & 255)));
        double d2 = (double) b3;
        Double.isNaN(d2);
        Double.isNaN(b4);
        return (float) (b4 + (d2 / 65536.0d));
    }

    public float m(int i) throws IOException {
        return Float.intBitsToFloat(j(i));
    }

    public double n(int i) throws IOException {
        return Double.longBitsToDouble(k(i));
    }

    @NotNull
    public StringValue a(int i, int i2, @Nullable Charset charset) throws IOException {
        return new StringValue(c(i, i2), charset);
    }

    @NotNull
    public String b(int i, int i2, @NotNull Charset charset) throws IOException {
        return new String(c(i, i2), charset.name());
    }

    @NotNull
    public String a(int i, int i2, @NotNull String str) throws IOException {
        byte[] c = c(i, i2);
        try {
            return new String(c, str);
        } catch (UnsupportedEncodingException unused) {
            return new String(c);
        }
    }

    @NotNull
    public String c(int i, int i2, @NotNull Charset charset) throws IOException {
        return new String(d(i, i2), charset.name());
    }

    @NotNull
    public StringValue d(int i, int i2, @Nullable Charset charset) throws IOException {
        return new StringValue(d(i, i2), charset);
    }

    @NotNull
    public byte[] d(int i, int i2) throws IOException {
        byte[] c = c(i, i2);
        int i3 = 0;
        while (i3 < c.length && c[i3] != 0) {
            i3++;
        }
        if (i3 == i2) {
            return c;
        }
        byte[] bArr = new byte[i3];
        if (i3 > 0) {
            System.arraycopy(c, 0, bArr, 0, i3);
        }
        return bArr;
    }
}
