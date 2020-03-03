package com.nostra13.universalimageloader.cache.disc.impl.ext;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

class StrictLineReader implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    private static final byte f8441a = 13;
    private static final byte b = 10;
    private final InputStream c;
    /* access modifiers changed from: private */
    public final Charset d;
    private byte[] e;
    private int f;
    private int g;

    public StrictLineReader(InputStream inputStream, Charset charset) {
        this(inputStream, 8192, charset);
    }

    public StrictLineReader(InputStream inputStream, int i, Charset charset) {
        if (inputStream == null || charset == null) {
            throw new NullPointerException();
        } else if (i < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        } else if (charset.equals(Util.f8443a)) {
            this.c = inputStream;
            this.d = charset;
            this.e = new byte[i];
        } else {
            throw new IllegalArgumentException("Unsupported encoding");
        }
    }

    public void close() throws IOException {
        synchronized (this.c) {
            if (this.e != null) {
                this.e = null;
                this.c.close();
            }
        }
    }

    public String a() throws IOException {
        int i;
        int i2;
        synchronized (this.c) {
            if (this.e != null) {
                if (this.f >= this.g) {
                    b();
                }
                for (int i3 = this.f; i3 != this.g; i3++) {
                    if (this.e[i3] == 10) {
                        if (i3 != this.f) {
                            i2 = i3 - 1;
                            if (this.e[i2] == 13) {
                                String str = new String(this.e, this.f, i2 - this.f, this.d.name());
                                this.f = i3 + 1;
                                return str;
                            }
                        }
                        i2 = i3;
                        String str2 = new String(this.e, this.f, i2 - this.f, this.d.name());
                        this.f = i3 + 1;
                        return str2;
                    }
                }
                AnonymousClass1 r1 = new ByteArrayOutputStream((this.g - this.f) + 80) {
                    public String toString() {
                        try {
                            return new String(this.buf, 0, (this.count <= 0 || this.buf[this.count + -1] != 13) ? this.count : this.count - 1, StrictLineReader.this.d.name());
                        } catch (UnsupportedEncodingException e) {
                            throw new AssertionError(e);
                        }
                    }
                };
                loop1:
                while (true) {
                    r1.write(this.e, this.f, this.g - this.f);
                    this.g = -1;
                    b();
                    i = this.f;
                    while (true) {
                        if (i != this.g) {
                            if (this.e[i] == 10) {
                                break loop1;
                            }
                            i++;
                        }
                    }
                }
                if (i != this.f) {
                    r1.write(this.e, this.f, i - this.f);
                }
                this.f = i + 1;
                String byteArrayOutputStream = r1.toString();
                return byteArrayOutputStream;
            }
            throw new IOException("LineReader is closed");
        }
    }

    private void b() throws IOException {
        int read = this.c.read(this.e, 0, this.e.length);
        if (read != -1) {
            this.f = 0;
            this.g = read;
            return;
        }
        throw new EOFException();
    }
}
