package com.bumptech.glide.load.data;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.IOException;
import java.io.OutputStream;

public final class BufferedOutputStream extends OutputStream {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    private final OutputStream f4838a;
    private byte[] b;
    private ArrayPool c;
    private int d;

    public BufferedOutputStream(@NonNull OutputStream outputStream, @NonNull ArrayPool arrayPool) {
        this(outputStream, arrayPool, 65536);
    }

    @VisibleForTesting
    BufferedOutputStream(@NonNull OutputStream outputStream, ArrayPool arrayPool, int i) {
        this.f4838a = outputStream;
        this.c = arrayPool;
        this.b = (byte[]) arrayPool.a(i, byte[].class);
    }

    public void write(int i) throws IOException {
        byte[] bArr = this.b;
        int i2 = this.d;
        this.d = i2 + 1;
        bArr[i2] = (byte) i;
        b();
    }

    public void write(@NonNull byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public void write(@NonNull byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        do {
            int i4 = i2 - i3;
            int i5 = i + i3;
            if (this.d != 0 || i4 < this.b.length) {
                int min = Math.min(i4, this.b.length - this.d);
                System.arraycopy(bArr, i5, this.b, this.d, min);
                this.d += min;
                i3 += min;
                b();
            } else {
                this.f4838a.write(bArr, i5, i4);
                return;
            }
        } while (i3 < i2);
    }

    public void flush() throws IOException {
        a();
        this.f4838a.flush();
    }

    private void a() throws IOException {
        if (this.d > 0) {
            this.f4838a.write(this.b, 0, this.d);
            this.d = 0;
        }
    }

    private void b() throws IOException {
        if (this.d == this.b.length) {
            a();
        }
    }

    /* JADX INFO: finally extract failed */
    public void close() throws IOException {
        try {
            flush();
            this.f4838a.close();
            c();
        } catch (Throwable th) {
            this.f4838a.close();
            throw th;
        }
    }

    private void c() {
        if (this.b != null) {
            this.c.a(this.b);
            this.b = null;
        }
    }
}
