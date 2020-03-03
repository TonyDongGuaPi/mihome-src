package org.apache.commons.compress.compressors.deflate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;

public class DeflateCompressorOutputStream extends CompressorOutputStream {

    /* renamed from: a  reason: collision with root package name */
    private final DeflaterOutputStream f3320a;
    private final Deflater b;

    public DeflateCompressorOutputStream(OutputStream outputStream) throws IOException {
        this(outputStream, new DeflateParameters());
    }

    public DeflateCompressorOutputStream(OutputStream outputStream, DeflateParameters deflateParameters) throws IOException {
        this.b = new Deflater(deflateParameters.b(), !deflateParameters.a());
        this.f3320a = new DeflaterOutputStream(outputStream, this.b);
    }

    public void write(int i) throws IOException {
        this.f3320a.write(i);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.f3320a.write(bArr, i, i2);
    }

    public void flush() throws IOException {
        this.f3320a.flush();
    }

    public void a() throws IOException {
        this.f3320a.finish();
    }

    public void close() throws IOException {
        try {
            this.f3320a.close();
        } finally {
            this.b.end();
        }
    }
}
