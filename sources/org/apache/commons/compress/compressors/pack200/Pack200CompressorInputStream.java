package org.apache.commons.compress.compressors.pack200;

import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.jar.JarOutputStream;
import java.util.jar.Pack200;
import org.apache.commons.compress.compressors.CompressorInputStream;

public class Pack200CompressorInputStream extends CompressorInputStream {
    private static final byte[] c = {-54, -2, -48, 13};
    private static final int d = c.length;

    /* renamed from: a  reason: collision with root package name */
    private final InputStream f3330a;
    private final StreamBridge b;

    public Pack200CompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, Pack200Strategy.IN_MEMORY);
    }

    public Pack200CompressorInputStream(InputStream inputStream, Pack200Strategy pack200Strategy) throws IOException {
        this(inputStream, (File) null, pack200Strategy, (Map<String, String>) null);
    }

    public Pack200CompressorInputStream(InputStream inputStream, Map<String, String> map) throws IOException {
        this(inputStream, Pack200Strategy.IN_MEMORY, map);
    }

    public Pack200CompressorInputStream(InputStream inputStream, Pack200Strategy pack200Strategy, Map<String, String> map) throws IOException {
        this(inputStream, (File) null, pack200Strategy, map);
    }

    public Pack200CompressorInputStream(File file) throws IOException {
        this(file, Pack200Strategy.IN_MEMORY);
    }

    public Pack200CompressorInputStream(File file, Pack200Strategy pack200Strategy) throws IOException {
        this((InputStream) null, file, pack200Strategy, (Map<String, String>) null);
    }

    public Pack200CompressorInputStream(File file, Map<String, String> map) throws IOException {
        this(file, Pack200Strategy.IN_MEMORY, map);
    }

    public Pack200CompressorInputStream(File file, Pack200Strategy pack200Strategy, Map<String, String> map) throws IOException {
        this((InputStream) null, file, pack200Strategy, map);
    }

    private Pack200CompressorInputStream(InputStream inputStream, File file, Pack200Strategy pack200Strategy, Map<String, String> map) throws IOException {
        Throwable th;
        this.f3330a = inputStream;
        this.b = pack200Strategy.newStreamBridge();
        JarOutputStream jarOutputStream = new JarOutputStream(this.b);
        try {
            Pack200.Unpacker newUnpacker = Pack200.newUnpacker();
            if (map != null) {
                newUnpacker.properties().putAll(map);
            }
            if (file == null) {
                newUnpacker.unpack(new FilterInputStream(inputStream) {
                    public void close() {
                    }
                }, jarOutputStream);
            } else {
                newUnpacker.unpack(file, jarOutputStream);
            }
            jarOutputStream.close();
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }

    public int read() throws IOException {
        return this.b.b().read();
    }

    public int read(byte[] bArr) throws IOException {
        return this.b.b().read(bArr);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.b.b().read(bArr, i, i2);
    }

    public int available() throws IOException {
        return this.b.b().available();
    }

    public boolean markSupported() {
        try {
            return this.b.b().markSupported();
        } catch (IOException unused) {
            return false;
        }
    }

    public void mark(int i) {
        try {
            this.b.b().mark(i);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reset() throws IOException {
        this.b.b().reset();
    }

    public long skip(long j) throws IOException {
        return this.b.b().skip(j);
    }

    public void close() throws IOException {
        try {
            this.b.c();
        } finally {
            if (this.f3330a != null) {
                this.f3330a.close();
            }
        }
    }

    public static boolean a(byte[] bArr, int i) {
        if (i < d) {
            return false;
        }
        for (int i2 = 0; i2 < d; i2++) {
            if (bArr[i2] != c[i2]) {
                return false;
            }
        }
        return true;
    }
}
