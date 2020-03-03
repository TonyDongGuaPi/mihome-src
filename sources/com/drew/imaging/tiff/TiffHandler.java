package com.drew.imaging.tiff;

import com.drew.lang.RandomAccessReader;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.StringValue;
import java.io.IOException;
import java.util.Set;

public interface TiffHandler {
    @Nullable
    Long a(int i, int i2, long j);

    void a(int i) throws TiffProcessingException;

    void a(int i, byte b);

    void a(int i, double d);

    void a(int i, float f);

    void a(int i, int i2);

    void a(int i, long j);

    void a(int i, @NotNull Rational rational);

    void a(int i, @NotNull StringValue stringValue);

    void a(int i, short s);

    void a(int i, @NotNull byte[] bArr);

    void a(int i, @NotNull double[] dArr);

    void a(int i, @NotNull float[] fArr);

    void a(int i, @NotNull int[] iArr);

    void a(int i, @NotNull long[] jArr);

    void a(int i, @NotNull Rational[] rationalArr);

    void a(int i, @NotNull short[] sArr);

    void a(@NotNull String str);

    boolean a();

    boolean a(int i, @NotNull Set<Integer> set, int i2, @NotNull RandomAccessReader randomAccessReader, int i3, int i4) throws IOException;

    void b();

    void b(int i, int i2);

    void b(int i, @NotNull byte[] bArr);

    void b(int i, @NotNull int[] iArr);

    void b(int i, @NotNull short[] sArr);

    void b(@NotNull String str);

    boolean b(int i);

    void c(int i, int i2);
}
