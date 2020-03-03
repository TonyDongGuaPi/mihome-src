package org.apache.commons.compress.compressors.lzma;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.LZMAOutputStream;

public class LZMACompressorOutputStream extends CompressorOutputStream {

    /* renamed from: a  reason: collision with root package name */
    private final LZMAOutputStream f3327a;

    public LZMACompressorOutputStream(OutputStream outputStream) throws IOException {
        this.f3327a = new LZMAOutputStream(outputStream, new LZMA2Options(), -1);
    }

    public void write(int i) throws IOException {
        this.f3327a.write(i);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.f3327a.write(bArr, i, i2);
    }

    public void flush() throws IOException {
        this.f3327a.flush();
    }

    public void a() throws IOException {
        this.f3327a.finish();
    }

    public void close() throws IOException {
        this.f3327a.close();
    }
}
