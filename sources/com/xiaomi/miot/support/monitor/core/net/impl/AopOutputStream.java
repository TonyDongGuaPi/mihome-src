package com.xiaomi.miot.support.monitor.core.net.impl;

import com.xiaomi.miot.support.monitor.core.net.i.IStreamCompleteListener;
import java.io.IOException;
import java.io.OutputStream;

public class AopOutputStream extends OutputStream {

    /* renamed from: a  reason: collision with root package name */
    private final String f11472a = "AopOutputStream";
    private final OutputStream b;
    private long c = 0;
    private IStreamCompleteListener d = null;

    public AopOutputStream(OutputStream outputStream) {
        this.b = outputStream;
    }

    public void a(IStreamCompleteListener iStreamCompleteListener) {
        this.d = iStreamCompleteListener;
    }

    public void a() {
        this.d = null;
    }

    private void c() {
        if (this.d != null) {
            this.d.b(this.c);
        }
    }

    private void d() {
        if (this.d != null) {
            this.d.d(this.c);
        }
    }

    public long b() {
        return this.c;
    }

    public void write(int i) throws IOException {
        try {
            this.b.write(i);
            this.c++;
        } catch (IOException e) {
            throw e;
        }
    }

    public void write(byte[] bArr) throws IOException {
        try {
            this.b.write(bArr);
            this.c += (long) bArr.length;
        } catch (IOException e) {
            throw e;
        }
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        try {
            this.b.write(bArr, i, i2);
            this.c += (long) i2;
        } catch (IOException e) {
            throw e;
        }
    }

    public void flush() throws IOException {
        try {
            this.b.flush();
        } catch (IOException e) {
            throw e;
        }
    }

    public void close() throws IOException {
        try {
            this.b.close();
            c();
        } catch (IOException e) {
            d();
            throw e;
        }
    }
}
