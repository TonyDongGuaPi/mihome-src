package com.xiaomi.push;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class jr extends ju {

    /* renamed from: a  reason: collision with root package name */
    protected InputStream f12832a = null;
    protected OutputStream b = null;

    protected jr() {
    }

    public jr(OutputStream outputStream) {
        this.b = outputStream;
    }

    public int a(byte[] bArr, int i, int i2) {
        if (this.f12832a != null) {
            try {
                int read = this.f12832a.read(bArr, i, i2);
                if (read >= 0) {
                    return read;
                }
                throw new jv(4);
            } catch (IOException e) {
                throw new jv(0, (Throwable) e);
            }
        } else {
            throw new jv(1, "Cannot read from null inputStream");
        }
    }

    public void b(byte[] bArr, int i, int i2) {
        if (this.b != null) {
            try {
                this.b.write(bArr, i, i2);
            } catch (IOException e) {
                throw new jv(0, (Throwable) e);
            }
        } else {
            throw new jv(1, "Cannot write to null outputStream");
        }
    }
}
