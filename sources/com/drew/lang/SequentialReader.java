package com.drew.lang;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.StringValue;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public abstract class SequentialReader {

    /* renamed from: a  reason: collision with root package name */
    private boolean f5202a = true;

    public abstract long a() throws IOException;

    public abstract void a(long j) throws IOException;

    public abstract void a(@NotNull byte[] bArr, int i, int i2) throws IOException;

    @NotNull
    public abstract byte[] a(int i) throws IOException;

    public abstract byte b() throws IOException;

    public abstract boolean b(long j) throws IOException;

    public abstract int c();

    public void a(boolean z) {
        this.f5202a = z;
    }

    public boolean d() {
        return this.f5202a;
    }

    public short e() throws IOException {
        return (short) (b() & 255);
    }

    public byte f() throws IOException {
        return b();
    }

    public int g() throws IOException {
        if (this.f5202a) {
            return ((b() << 8) & 65280) | (b() & 255);
        }
        return (b() & 255) | (65280 & (b() << 8));
    }

    public short h() throws IOException {
        if (this.f5202a) {
            return (short) (((((short) b()) << 8) & -256) | (((short) b()) & 255));
        }
        return (short) ((((short) b()) & 255) | ((((short) b()) << 8) & -256));
    }

    public long i() throws IOException {
        if (!this.f5202a) {
            return (255 & ((long) b())) | (65280 & (((long) b()) << 8)) | ((((long) b()) << 16) & 16711680) | ((((long) b()) << 24) & 4278190080L);
        }
        long b = 4278190080L & (((long) b()) << 24);
        long b2 = 65280 & (((long) b()) << 8);
        return (255 & ((long) b())) | b2 | (16711680 & (((long) b()) << 16)) | b;
    }

    public int j() throws IOException {
        if (this.f5202a) {
            return ((b() << 24) & -16777216) | (16711680 & (b() << 16)) | (65280 & (b() << 8)) | (b() & 255);
        }
        return (b() & 255) | (65280 & (b() << 8)) | ((b() << 16) & 16711680) | ((b() << 24) & -16777216);
    }

    public long k() throws IOException {
        if (this.f5202a) {
            return ((((long) b()) << 56) & -72057594037927936L) | ((((long) b()) << 48) & 71776119061217280L) | ((((long) b()) << 40) & 280375465082880L) | ((((long) b()) << 32) & 1095216660480L) | ((((long) b()) << 24) & 4278190080L) | ((((long) b()) << 16) & 16711680) | ((((long) b()) << 8) & 65280) | (((long) b()) & 255);
        }
        return (((long) b()) & 255) | ((((long) b()) << 8) & 65280) | ((((long) b()) << 16) & 16711680) | ((((long) b()) << 24) & 4278190080L) | ((((long) b()) << 32) & 1095216660480L) | ((((long) b()) << 40) & 280375465082880L) | ((((long) b()) << 48) & 71776119061217280L) | ((((long) b()) << 56) & -72057594037927936L);
    }

    public float l() throws IOException {
        if (this.f5202a) {
            byte b = ((b() & 255) << 8) | (b() & 255);
            double b2 = (double) ((float) (((b() & 255) << 8) | (b() & 255)));
            double d = (double) b;
            Double.isNaN(d);
            Double.isNaN(b2);
            return (float) (b2 + (d / 65536.0d));
        }
        byte b3 = (b() & 255) | ((b() & 255) << 8);
        double b4 = (double) ((float) ((b() & 255) | ((b() & 255) << 8)));
        double d2 = (double) b3;
        Double.isNaN(d2);
        Double.isNaN(b4);
        return (float) (b4 + (d2 / 65536.0d));
    }

    public float m() throws IOException {
        return Float.intBitsToFloat(j());
    }

    public double n() throws IOException {
        return Double.longBitsToDouble(k());
    }

    @NotNull
    public String b(int i) throws IOException {
        return new String(a(i));
    }

    @NotNull
    public String a(int i, String str) throws IOException {
        byte[] a2 = a(i);
        try {
            return new String(a2, str);
        } catch (UnsupportedEncodingException unused) {
            return new String(a2);
        }
    }

    @NotNull
    public String a(int i, @NotNull Charset charset) throws IOException {
        return new String(a(i), charset);
    }

    @NotNull
    public StringValue b(int i, @Nullable Charset charset) throws IOException {
        return new StringValue(a(i), charset);
    }

    @NotNull
    public String c(int i, Charset charset) throws IOException {
        return d(i, charset).toString();
    }

    @NotNull
    public StringValue d(int i, Charset charset) throws IOException {
        return new StringValue(c(i), charset);
    }

    @NotNull
    public byte[] c(int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < bArr.length) {
            byte b = b();
            bArr[i2] = b;
            if (b == 0) {
                break;
            }
            i2++;
        }
        if (i2 == i) {
            return bArr;
        }
        byte[] bArr2 = new byte[i2];
        if (i2 > 0) {
            System.arraycopy(bArr, 0, bArr2, 0, i2);
        }
        return bArr2;
    }
}
