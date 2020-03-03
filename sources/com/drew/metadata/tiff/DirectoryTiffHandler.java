package com.drew.metadata.tiff;

import com.drew.imaging.tiff.TiffHandler;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.ErrorDirectory;
import com.drew.metadata.Metadata;
import com.drew.metadata.StringValue;
import java.util.Stack;

public abstract class DirectoryTiffHandler implements TiffHandler {

    /* renamed from: a  reason: collision with root package name */
    private final Stack<Directory> f5266a = new Stack<>();
    @Nullable
    protected Directory b;
    protected final Metadata c;
    @Nullable
    private Directory d;

    protected DirectoryTiffHandler(Metadata metadata, @Nullable Directory directory) {
        this.c = metadata;
        this.d = directory;
    }

    public void b() {
        this.b = this.f5266a.empty() ? null : this.f5266a.pop();
    }

    /* access modifiers changed from: protected */
    public void a(@NotNull Class<? extends Directory> cls) {
        try {
            Directory directory = (Directory) cls.newInstance();
            if (this.b != null) {
                this.f5266a.push(this.b);
                directory.a(this.b);
            } else if (this.d != null) {
                directory.a(this.d);
                this.d = null;
            }
            this.b = directory;
            this.c.a(this.b);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        }
    }

    public void a(@NotNull String str) {
        c().a(str);
    }

    public void b(@NotNull String str) {
        c().a(str);
    }

    @NotNull
    private Directory c() {
        if (this.b != null) {
            return this.b;
        }
        ErrorDirectory errorDirectory = (ErrorDirectory) this.c.b(ErrorDirectory.class);
        if (errorDirectory != null) {
            return errorDirectory;
        }
        a((Class<? extends Directory>) ErrorDirectory.class);
        return this.b;
    }

    public void a(int i, @NotNull byte[] bArr) {
        this.b.a(i, bArr);
    }

    public void a(int i, @NotNull StringValue stringValue) {
        this.b.a(i, stringValue);
    }

    public void a(int i, @NotNull Rational rational) {
        this.b.a(i, rational);
    }

    public void a(int i, @NotNull Rational[] rationalArr) {
        this.b.a(i, rationalArr);
    }

    public void a(int i, float f) {
        this.b.a(i, f);
    }

    public void a(int i, @NotNull float[] fArr) {
        this.b.a(i, fArr);
    }

    public void a(int i, double d2) {
        this.b.a(i, d2);
    }

    public void a(int i, @NotNull double[] dArr) {
        this.b.a(i, dArr);
    }

    public void a(int i, byte b2) {
        this.b.a(i, (int) b2);
    }

    public void b(int i, @NotNull byte[] bArr) {
        this.b.a(i, bArr);
    }

    public void a(int i, short s) {
        this.b.a(i, (int) s);
    }

    public void a(int i, @NotNull short[] sArr) {
        this.b.b(i, (Object) sArr);
    }

    public void a(int i, int i2) {
        this.b.a(i, i2);
    }

    public void b(int i, @NotNull short[] sArr) {
        this.b.b(i, (Object) sArr);
    }

    public void b(int i, int i2) {
        this.b.a(i, i2);
    }

    public void a(int i, @NotNull int[] iArr) {
        this.b.b(i, (Object) iArr);
    }

    public void c(int i, int i2) {
        this.b.a(i, i2);
    }

    public void b(int i, @NotNull int[] iArr) {
        this.b.a(i, iArr);
    }

    public void a(int i, long j) {
        this.b.a(i, j);
    }

    public void a(int i, @NotNull long[] jArr) {
        this.b.b(i, (Object) jArr);
    }
}
