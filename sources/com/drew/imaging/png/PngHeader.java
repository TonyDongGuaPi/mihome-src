package com.drew.imaging.png;

import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.annotations.NotNull;
import java.io.IOException;

public class PngHeader {

    /* renamed from: a  reason: collision with root package name */
    private int f5187a;
    private int b;
    private byte c;
    @NotNull
    private PngColorType d;
    private byte e;
    private byte f;
    private byte g;

    public PngHeader(@NotNull byte[] bArr) throws PngProcessingException {
        if (bArr.length == 13) {
            SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
            try {
                this.f5187a = sequentialByteArrayReader.j();
                this.b = sequentialByteArrayReader.j();
                this.c = sequentialByteArrayReader.f();
                byte f2 = sequentialByteArrayReader.f();
                PngColorType fromNumericValue = PngColorType.fromNumericValue(f2);
                if (fromNumericValue != null) {
                    this.d = fromNumericValue;
                    this.e = sequentialByteArrayReader.f();
                    this.f = sequentialByteArrayReader.f();
                    this.g = sequentialByteArrayReader.f();
                    return;
                }
                throw new PngProcessingException("Unexpected PNG color type: " + f2);
            } catch (IOException e2) {
                throw new PngProcessingException((Throwable) e2);
            }
        } else {
            throw new PngProcessingException("PNG header chunk must have 13 data bytes");
        }
    }

    public int a() {
        return this.f5187a;
    }

    public int b() {
        return this.b;
    }

    public byte c() {
        return this.c;
    }

    @NotNull
    public PngColorType d() {
        return this.d;
    }

    public byte e() {
        return this.e;
    }

    public byte f() {
        return this.f;
    }

    public byte g() {
        return this.g;
    }
}
