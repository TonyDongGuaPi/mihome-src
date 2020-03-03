package org.apache.commons.compress.compressors.gzip;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import org.apache.commons.compress.compressors.CompressorOutputStream;

public class GzipCompressorOutputStream extends CompressorOutputStream {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3323a = 8;
    private static final int b = 16;
    private final OutputStream c;
    private final Deflater d;
    private final byte[] e;
    private boolean f;
    private final CRC32 g;

    public GzipCompressorOutputStream(OutputStream outputStream) throws IOException {
        this(outputStream, new GzipParameters());
    }

    public GzipCompressorOutputStream(OutputStream outputStream, GzipParameters gzipParameters) throws IOException {
        this.e = new byte[512];
        this.g = new CRC32();
        this.c = outputStream;
        this.d = new Deflater(gzipParameters.a(), true);
        a(gzipParameters);
    }

    private void a(GzipParameters gzipParameters) throws IOException {
        String c2 = gzipParameters.c();
        String d2 = gzipParameters.d();
        ByteBuffer allocate = ByteBuffer.allocate(10);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putShort(-29921);
        int i = 8;
        allocate.put((byte) 8);
        if (c2 == null) {
            i = 0;
        }
        allocate.put((byte) (i | (d2 != null ? 16 : 0)));
        allocate.putInt((int) (gzipParameters.b() / 1000));
        int a2 = gzipParameters.a();
        if (a2 == 9) {
            allocate.put((byte) 2);
        } else if (a2 == 1) {
            allocate.put((byte) 4);
        } else {
            allocate.put((byte) 0);
        }
        allocate.put((byte) gzipParameters.e());
        this.c.write(allocate.array());
        if (c2 != null) {
            this.c.write(c2.getBytes("ISO-8859-1"));
            this.c.write(0);
        }
        if (d2 != null) {
            this.c.write(d2.getBytes("ISO-8859-1"));
            this.c.write(0);
        }
    }

    private void b() throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putInt((int) this.g.getValue());
        allocate.putInt(this.d.getTotalIn());
        this.c.write(allocate.array());
    }

    public void write(int i) throws IOException {
        write(new byte[]{(byte) (i & 255)}, 0, 1);
    }

    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.d.finished()) {
            throw new IOException("Cannot write more data, the end of the compressed data stream has been reached");
        } else if (i2 > 0) {
            this.d.setInput(bArr, i, i2);
            while (!this.d.needsInput()) {
                c();
            }
            this.g.update(bArr, i, i2);
        }
    }

    private void c() throws IOException {
        int deflate = this.d.deflate(this.e, 0, this.e.length);
        if (deflate > 0) {
            this.c.write(this.e, 0, deflate);
        }
    }

    public void a() throws IOException {
        if (!this.d.finished()) {
            this.d.finish();
            while (!this.d.finished()) {
                c();
            }
            b();
        }
    }

    public void flush() throws IOException {
        this.c.flush();
    }

    public void close() throws IOException {
        if (!this.f) {
            a();
            this.d.end();
            this.c.close();
            this.f = true;
        }
    }
}
