package com.drew.lang;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.SuppressWarnings;
import java.io.IOException;

public class ByteArrayReader extends RandomAccessReader {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f5191a;
    private final int b;

    @SuppressWarnings(justification = "Design intent", value = "EI_EXPOSE_REP2")
    public ByteArrayReader(@NotNull byte[] bArr) {
        this(bArr, 0);
    }

    @SuppressWarnings(justification = "Design intent", value = "EI_EXPOSE_REP2")
    public ByteArrayReader(@NotNull byte[] bArr, int i) {
        if (bArr == null) {
            throw new NullPointerException();
        } else if (i >= 0) {
            this.f5191a = bArr;
            this.b = i;
        } else {
            throw new IllegalArgumentException("Must be zero or greater");
        }
    }

    public int a(int i) {
        return i + this.b;
    }

    public long a() {
        return (long) (this.f5191a.length - this.b);
    }

    public byte b(int i) throws IOException {
        a(i, 1);
        return this.f5191a[i + this.b];
    }

    /* access modifiers changed from: protected */
    public void a(int i, int i2) throws IOException {
        if (!b(i, i2)) {
            throw new BufferBoundsException(a(i), i2, (long) this.f5191a.length);
        }
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, int i2) throws IOException {
        return i2 >= 0 && i >= 0 && (((long) i) + ((long) i2)) - 1 < a();
    }

    @NotNull
    public byte[] c(int i, int i2) throws IOException {
        a(i, i2);
        byte[] bArr = new byte[i2];
        System.arraycopy(this.f5191a, i + this.b, bArr, 0, i2);
        return bArr;
    }
}
