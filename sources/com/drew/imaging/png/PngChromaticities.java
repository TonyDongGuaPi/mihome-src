package com.drew.imaging.png;

import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.annotations.NotNull;
import java.io.IOException;

public class PngChromaticities {

    /* renamed from: a  reason: collision with root package name */
    private final int f5183a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;
    private final int h;

    public PngChromaticities(@NotNull byte[] bArr) throws PngProcessingException {
        if (bArr.length == 32) {
            SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
            try {
                this.f5183a = sequentialByteArrayReader.j();
                this.b = sequentialByteArrayReader.j();
                this.c = sequentialByteArrayReader.j();
                this.d = sequentialByteArrayReader.j();
                this.e = sequentialByteArrayReader.j();
                this.f = sequentialByteArrayReader.j();
                this.g = sequentialByteArrayReader.j();
                this.h = sequentialByteArrayReader.j();
            } catch (IOException e2) {
                throw new PngProcessingException((Throwable) e2);
            }
        } else {
            throw new PngProcessingException("Invalid number of bytes");
        }
    }

    public int a() {
        return this.f5183a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public int f() {
        return this.f;
    }

    public int g() {
        return this.g;
    }

    public int h() {
        return this.h;
    }
}
