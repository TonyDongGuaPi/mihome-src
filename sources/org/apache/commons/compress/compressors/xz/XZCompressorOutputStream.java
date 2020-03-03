package org.apache.commons.compress.compressors.xz;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.XZOutputStream;

public class XZCompressorOutputStream extends CompressorOutputStream {

    /* renamed from: a  reason: collision with root package name */
    private final XZOutputStream f3340a;

    public XZCompressorOutputStream(OutputStream outputStream) throws IOException {
        this.f3340a = new XZOutputStream(outputStream, new LZMA2Options());
    }

    public XZCompressorOutputStream(OutputStream outputStream, int i) throws IOException {
        this.f3340a = new XZOutputStream(outputStream, new LZMA2Options(i));
    }

    public void write(int i) throws IOException {
        this.f3340a.write(i);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.f3340a.write(bArr, i, i2);
    }

    public void flush() throws IOException {
        this.f3340a.flush();
    }

    public void a() throws IOException {
        this.f3340a.finish();
    }

    public void close() throws IOException {
        this.f3340a.close();
    }
}
