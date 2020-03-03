package org.apache.commons.compress.compressors.deflate;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;

public class DeflateCompressorInputStream extends CompressorInputStream {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3319a = 120;
    private static final int b = 1;
    private static final int c = 94;
    private static final int d = 156;
    private static final int e = 218;
    private final InputStream f;
    private final Inflater g;

    public DeflateCompressorInputStream(InputStream inputStream) {
        this(inputStream, new DeflateParameters());
    }

    public DeflateCompressorInputStream(InputStream inputStream, DeflateParameters deflateParameters) {
        this.g = new Inflater(!deflateParameters.a());
        this.f = new InflaterInputStream(inputStream, this.g);
    }

    public int read() throws IOException {
        int read = this.f.read();
        a(read == -1 ? 0 : 1);
        return read;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.f.read(bArr, i, i2);
        a(read);
        return read;
    }

    public long skip(long j) throws IOException {
        return this.f.skip(j);
    }

    public int available() throws IOException {
        return this.f.available();
    }

    public void close() throws IOException {
        try {
            this.f.close();
        } finally {
            this.g.end();
        }
    }

    public static boolean a(byte[] bArr, int i) {
        if (i <= 3 || bArr[0] != 120) {
            return false;
        }
        return bArr[1] == 1 || bArr[1] == 94 || bArr[1] == -100 || bArr[1] == -38;
    }
}
