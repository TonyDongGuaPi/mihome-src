package org.apache.commons.compress.compressors.lzma;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.tukaani.xz.LZMAInputStream;

public class LZMACompressorInputStream extends CompressorInputStream {

    /* renamed from: a  reason: collision with root package name */
    private final InputStream f3326a;

    public LZMACompressorInputStream(InputStream inputStream) throws IOException {
        this.f3326a = new LZMAInputStream(inputStream);
    }

    public int read() throws IOException {
        int read = this.f3326a.read();
        a(read == -1 ? 0 : 1);
        return read;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.f3326a.read(bArr, i, i2);
        a(read);
        return read;
    }

    public long skip(long j) throws IOException {
        return this.f3326a.skip(j);
    }

    public int available() throws IOException {
        return this.f3326a.available();
    }

    public void close() throws IOException {
        this.f3326a.close();
    }

    public static boolean a(byte[] bArr, int i) {
        return bArr != null && i >= 3 && bArr[0] == 93 && bArr[1] == 0 && bArr[2] == 0;
    }
}
