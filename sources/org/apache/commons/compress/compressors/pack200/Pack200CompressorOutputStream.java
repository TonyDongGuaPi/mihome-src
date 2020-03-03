package org.apache.commons.compress.compressors.pack200;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.jar.JarInputStream;
import java.util.jar.Pack200;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;

public class Pack200CompressorOutputStream extends CompressorOutputStream {

    /* renamed from: a  reason: collision with root package name */
    private boolean f3332a;
    private final OutputStream b;
    private final StreamBridge c;
    private final Map<String, String> d;

    public Pack200CompressorOutputStream(OutputStream outputStream) throws IOException {
        this(outputStream, Pack200Strategy.IN_MEMORY);
    }

    public Pack200CompressorOutputStream(OutputStream outputStream, Pack200Strategy pack200Strategy) throws IOException {
        this(outputStream, pack200Strategy, (Map<String, String>) null);
    }

    public Pack200CompressorOutputStream(OutputStream outputStream, Map<String, String> map) throws IOException {
        this(outputStream, Pack200Strategy.IN_MEMORY, map);
    }

    public Pack200CompressorOutputStream(OutputStream outputStream, Pack200Strategy pack200Strategy, Map<String, String> map) throws IOException {
        this.f3332a = false;
        this.b = outputStream;
        this.c = pack200Strategy.newStreamBridge();
        this.d = map;
    }

    public void write(int i) throws IOException {
        this.c.write(i);
    }

    public void write(byte[] bArr) throws IOException {
        this.c.write(bArr);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.c.write(bArr, i, i2);
    }

    public void close() throws IOException {
        a();
        try {
            this.c.c();
        } finally {
            this.b.close();
        }
    }

    public void a() throws IOException {
        if (!this.f3332a) {
            this.f3332a = true;
            Pack200.Packer newPacker = Pack200.newPacker();
            if (this.d != null) {
                newPacker.properties().putAll(this.d);
            }
            JarInputStream jarInputStream = null;
            try {
                JarInputStream jarInputStream2 = new JarInputStream(this.c.b());
                try {
                    newPacker.pack(jarInputStream2, this.b);
                } catch (Throwable th) {
                    th = th;
                    jarInputStream = jarInputStream2;
                }
            } catch (Throwable th2) {
                th = th2;
                IOUtils.a((Closeable) jarInputStream);
                throw th;
            }
        }
    }
}
