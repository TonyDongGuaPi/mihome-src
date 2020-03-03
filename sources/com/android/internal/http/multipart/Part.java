package com.android.internal.http.multipart;

import java.io.IOException;
import java.io.OutputStream;

public abstract class Part {
    @Deprecated
    protected static final String e = "----------------314159265358979323846";
    @Deprecated
    protected static final byte[] f = null;
    protected static final String g = "; charset=";
    protected static final byte[] h = null;
    protected static final String i = "Content-Disposition: form-data; name=";
    protected static final byte[] j = null;
    protected static final String k = "Content-Transfer-Encoding: ";
    protected static final byte[] l = null;
    protected static final String m = "Content-Type: ";
    protected static final byte[] n = null;
    protected static final String o = "\r\n";
    protected static final byte[] p = null;
    protected static final String q = "--";
    protected static final byte[] r = null;
    protected static final String s = "\"";
    protected static final byte[] t = null;

    /* access modifiers changed from: protected */
    public abstract long b() throws IOException;

    /* access modifiers changed from: protected */
    public abstract void b(OutputStream outputStream) throws IOException;

    public abstract String d();

    public abstract String e();

    public abstract String f();

    public abstract String g();

    public Part() {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public static String c() {
        throw new RuntimeException("Stub!");
    }

    /* access modifiers changed from: protected */
    public byte[] h() {
        throw new RuntimeException("Stub!");
    }

    public boolean i() {
        throw new RuntimeException("Stub!");
    }

    /* access modifiers changed from: protected */
    public void c(OutputStream outputStream) throws IOException {
        throw new RuntimeException("Stub!");
    }

    /* access modifiers changed from: protected */
    public void a(OutputStream outputStream) throws IOException {
        throw new RuntimeException("Stub!");
    }

    /* access modifiers changed from: protected */
    public void d(OutputStream outputStream) throws IOException {
        throw new RuntimeException("Stub!");
    }

    /* access modifiers changed from: protected */
    public void e(OutputStream outputStream) throws IOException {
        throw new RuntimeException("Stub!");
    }

    /* access modifiers changed from: protected */
    public void f(OutputStream outputStream) throws IOException {
        throw new RuntimeException("Stub!");
    }

    /* access modifiers changed from: protected */
    public void g(OutputStream outputStream) throws IOException {
        throw new RuntimeException("Stub!");
    }

    public void h(OutputStream outputStream) throws IOException {
        throw new RuntimeException("Stub!");
    }

    public long j() throws IOException {
        throw new RuntimeException("Stub!");
    }

    public String toString() {
        throw new RuntimeException("Stub!");
    }

    public static void a(OutputStream outputStream, Part[] partArr) throws IOException {
        throw new RuntimeException("Stub!");
    }

    public static void a(OutputStream outputStream, Part[] partArr, byte[] bArr) throws IOException {
        throw new RuntimeException("Stub!");
    }

    public static long a(Part[] partArr) throws IOException {
        throw new RuntimeException("Stub!");
    }

    public static long a(Part[] partArr, byte[] bArr) throws IOException {
        throw new RuntimeException("Stub!");
    }
}
